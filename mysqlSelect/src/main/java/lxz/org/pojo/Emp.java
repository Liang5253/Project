package lxz.org.pojo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Emp implements Serializable{
	/*
	 * SQL> desc emp2
	Name     Type         Nullable Default Comments 
	-------- ------------ -------- ------- -------- 
	EMPNO    NUMBER(4)                              
	ENAME    VARCHAR2(10) Y                         
	JOB      VARCHAR2(9)  Y                         
	MGR      NUMBER(4)    Y                         
	HIREDATE DATE         Y                         
	SAL      NUMBER(7,2)  Y                         
	COMM     NUMBER(7,2)  Y                         
	DEPTNO   NUMBER(2)    Y     
	 * */
		private Integer empno;
		private String ename;
		private String job;
		@JsonInclude(value=Include.NON_NULL)
		private Integer mgr;
		@JsonFormat(pattern="yyyy年MM月dd日 : HH:mm:ss",timezone="GMT+8",locale="zh")
		private Date hiredate;
		private Double sal;
		@JsonInclude(value=Include.NON_NULL)
		private Double comm;
		private Integer deptno;
		
		
		public Integer getEmpno() {
			return empno;
		}
		public void setEmpno(Integer empno) {
			this.empno = empno;
		}
		public String getEname() {
			return ename;
		}
		public void setEname(String ename) {
			this.ename = ename;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		public Integer getMgr() {
			return mgr;
		}
		public void setMgr(Integer mgr) {
			this.mgr = mgr;
		}
		public Date getHiredate() {
			return hiredate;
		}
		public void setHiredate(Date hiredate) {
			this.hiredate = hiredate;
		}
		public Double getSal() {
			return sal;
		}
		public void setSal(Double sal) {
			this.sal = sal;
		}
		public Double getComm() {
			return comm;
		}
		public void setComm(Double comm) {
			this.comm = comm;
		}
		public Integer getDeptno() {
			return deptno;
		}
		public void setDeptno(Integer deptno) {
			this.deptno = deptno;
		}
		@Override
		public String toString() {
			return "Emp [表ID=" + empno + ", 员工姓名=" + ename + ", 员工职位=" + job + ", 员工上司编号=" + mgr + ", 员工入职日期=" + hiredate
					+ ", 员工工资=" + sal + ", 员工资金=" + comm + ", 部门编号=" + deptno + "]";
		}
		
		
}
