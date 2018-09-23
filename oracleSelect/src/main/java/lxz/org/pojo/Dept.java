package lxz.org.pojo;

import java.io.Serializable;

public class Dept implements Serializable {
/**
 *  DEPTNO NUMBER(2)                              
	DNAME  VARCHAR2(14) Y                         
	LOC    VARCHAR2(13) Y  
 */
	
	private int deptno;
	private String dname;
	private String loc;
	
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
}
	
	