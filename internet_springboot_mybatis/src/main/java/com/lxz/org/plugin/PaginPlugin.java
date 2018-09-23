package com.lxz.org.plugin;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import com.lxz.org.pojo.PageParams;

@Intercepts({
	@Signature(args = {Connection.class}, 
			   method = "prepare", 
			   type = StatementHandler.class)
})
public class PaginPlugin implements Interceptor {
	
	private Integer defaultPage;//默认页码
	private Integer defaultPageSize;//默认每页条数
	private Boolean defaultUseFlag;//默认是否启动插件
	private Boolean defaultCheckFlag;//默认是否检测当前页码的正确性
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler stmtHandler=getUnProxyObject(invocation);
		MetaObject metaStatementHandler=SystemMetaObject.forObject(stmtHandler);
		String sql =(String) metaStatementHandler.getValue("delegate.boundSql.sql");
		//不是select语句
		if(!checkSelect(sql)) {
			return invocation.proceed();
		}
		BoundSql boundSql=(BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		Object parameterObject =boundSql.getParameterObject();
		PageParams pageParams= getPageParams(parameterObject);
		//获取分页参数，获取不到时候使用默认值
		Integer pageNum=pageParams.getPage()==null?this.defaultPage:pageParams.getPage();
		Integer pageSize=pageParams.getPageSize()==null? this.defaultPageSize:pageParams.getPageSize();
		Boolean useFlag=pageParams.getUseFlag()==null? this.defaultUseFlag:pageParams.getUseFlag();
		Boolean checkFlag=pageParams.getCheckFlag()==null? this.defaultCheckFlag:pageParams.getCheckFlag();
		
		//不使用分页插件
		if(useFlag) {
			return invocation.proceed();
		}
		
		int total=getTotal(invocation,metaStatementHandler,boundSql);
		//回填总数到分页参数里
		setTotalToPageParams(pageParams,total,pageSize);
		//检查当前页码的有效性
		checkPage(checkFlag,pageNum,pageParams.getTotalPage());
		//修改SQL
		return changSQL(invocation,metaStatementHandler,boundSql,pageNum,pageSize);
	}
	
	//改写SQL满足分页的需求
	/**
	 * 修改当前查询的SQL
	 * @param invocation
	 * @param metaStatementHandler
	 * @param boundSql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 * @throws InvocationTargetException 
	 */
	private Object changSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, Integer pageNum,
			Integer pageSize) throws InvocationTargetException, Exception {
		//获取当前需要执行的SQL
		String sql=(String) metaStatementHandler.getValue("delegate.boundSql.sql");
		//修改SQL,这里是mysql
		String newSql="select * from ("+sql+") $_paging_table limit ?,?";
		//修改当前需要执行的SQL
		metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
		//相当于调用StatementHandler的prepare方法，预编译了当前SQL并设置原有的参数，但是少了两个分页参数，它返回的是一个preparedStatement对象
		PreparedStatement ps=(PreparedStatement) invocation.proceed();
		//计算SQL总参数个数
		int count=ps.getParameterMetaData().getParameterCount();
		//设置两个分布参数
		ps.setInt(count-1,(pageNum - 1)*pageSize);
		ps.setInt(count, pageSize);
		return ps;
	}

	/**
	 * 判断当前页码是否大于最大页码
	 * @param checkFlag
	 * @param pageNum
	 * @param totalPage
	 * @throws Throwable 
	 */
	private void checkPage(Boolean checkFlag, Integer pageNum, Integer totalPage) throws Throwable {
		// TODO Auto-generated method stub
		if(checkFlag) {
			if(pageNum>totalPage) {
				throw new Exception("查询失败，查询页码【"+pageNum+"】大于总页数【"+totalPage+"】！！");
			}
		}
	}

	/**
	 * 回填总条数和总页数到分页参数
	 * @param pageParams
	 * @param total
	 * @param pageSize
	 */
	private void setTotalToPageParams(PageParams pageParams, int total, Integer pageSize) {
		pageParams.setTotal(total);
		//计算总页数
		int totalPage=total % pageSize==0? total/pageSize:total/pageSize+1;
		pageParams.setTotalPage(totalPage);
	}

	/**
	  * 获取总数
	 * @param invocation
	 * @param metaStatementHandler
	 * @param boundSql
	 * @return
	 * @throws Throwable 
	 */
	private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql) throws Throwable {
		//获取当前的mappedStatement
		MappedStatement mappedStatement=(MappedStatement)metaStatementHandler.getValue("dalegate.mappedStatement");
		//配置对象
		Configuration cfg=mappedStatement.getConfiguration();
		//当前需要执行的SQL
		String sql=(String)metaStatementHandler.getValue("dalegate.boundSql.sql");
		//改写为统计总数的SQL，这里是MySql数据库规范
		String countSql="select count(*) as total from ("+sql+") $_paging";
		//获取拦截方法参数，我们知道是Connection对象
		Connection connection=(Connection) ivt.getArgs()[0];
		PreparedStatement ps=null;
		int total=0;
		try {
			//预编译统计总数SQL 
			ps=connection.prepareStatement(countSql);
			//构建统计总数BoundSql
			BoundSql countBoundSql=new BoundSql(cfg,countSql,boundSql.getParameterMappings(),boundSql.getParameterObject());
			//构建Mybatis的ParameterHandler用来设置总SQL 的参数
			ParameterHandler handler=new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
			//设置总SQL参数
			handler.setParameters(ps);
			//执行查询
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				total=rs.getInt("total");
			}
		}finally {
			//这里不能关闭Connection,否则后续的SQL就没法继续了
			if(ps!=null) {
				ps.close();
			}
		}
		System.err.println("总条数："+total);
		return total;
	}


	/**
	 * 分解分页参数，这里支持使用Map 和 @param 注解传递参数，或者POJO继承PageParams,这三种方式者是允许的
	 * @param parameterObject --sql允许参数
	 * @return 分页参数
	 */
	private PageParams getPageParams(Object parameterObject) {
			if(parameterObject==null) {
				return null;
			}
			PageParams pageParams=null;
			//支持Map参数和MyBatis的@Param注解参数
			
			if(pageParams instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> paramMap=(Map<String,Object>)parameterObject;
				Set<String> keySet=paramMap.keySet();
				Iterator<String> iterator=keySet.iterator();
				while(iterator.hasNext()) {
					String key=iterator.next();
					Object value=paramMap.get(key);
					if(value instanceof PageParams) {
						return (PageParams)value;
					}
				}
			}else if(parameterObject instanceof PageParams) {
				//继承方式
				pageParams=(PageParams)parameterObject;
			}
			return pageParams;
	}

	/**
	 * 从代理对象中分离出真实对象
	 * @param invocation
	 * @return
	 */
	private StatementHandler getUnProxyObject(Invocation ivt) {
		StatementHandler statementHandler=(StatementHandler)ivt.getTarget();
		MetaObject metaStatementHandler=SystemMetaObject.forObject(statementHandler);
		//分离代理对象链（由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分离出最原始的目标类）
		Object object=null;
		while(metaStatementHandler.hasGetter("h")) {
			object=metaStatementHandler.getValue("h");
		}
		if(object==null)
		{
			return statementHandler;
		}
		return (StatementHandler) object;
	}
	
	/**
	 * 查检是否select 语句
	 * @param sql
	 * @return
	 */
	private boolean checkSelect(String sql) {
		String trimSql =sql.trim();
		int idx = trimSql.toLowerCase().indexOf("select");
		return idx==0;
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties props) {
		// TODO Auto-generated method stub
		String strDefaultpage=props.getProperty("default.page","1");
		String strDefaultPageSize=props.getProperty("default.pageSize","50");
		String strDefaultUseFlag=props.getProperty("default.useFlag","false");
		String strDefaultcheckFlag=props.getProperty("default.checkFlag","false");
		
		this.defaultPage=Integer.parseInt(strDefaultpage);
		this.defaultPageSize=Integer.parseInt(strDefaultPageSize);
		this.defaultUseFlag=Boolean.parseBoolean(strDefaultUseFlag);
		this.defaultCheckFlag=Boolean.parseBoolean(strDefaultcheckFlag);
		
	}

	public Integer getDefaultpage() {
		return defaultPage;
	}

	public void setDefaultpage(Integer defaultpage) {
		this.defaultPage = defaultpage;
	}

	public Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(Integer defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public Boolean getDefaultUseFlag() {
		return defaultUseFlag;
	}

	public void setDefaultUseFlag(Boolean defaultUseFlag) {
		this.defaultUseFlag = defaultUseFlag;
	}

	public Boolean getDefaultCheckFlag() {
		return defaultCheckFlag;
	}

	public void setDefaultCheckFlag(Boolean defaultCheckFlag) {
		this.defaultCheckFlag = defaultCheckFlag;
	}

	
	
	
}
