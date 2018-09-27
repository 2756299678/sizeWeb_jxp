package model;

public class User { 
	private String username,pwd;
	private int qNum,eNum,rNum;
	
	public User(){}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
    
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd=pwd;
	}
	
	public int getQnum()
	{
		return qNum;
	}
	public void setQnum(int qNum)
	{
		this.qNum=qNum;
	}
	public int getEnum()
	{
		return eNum;
	}
	public void setEnum(int eNum)
	{
		this.eNum=eNum;
	}
	public int getRnum()
	{
		return rNum;
	}
	public void setRnum(int rNum)
	{
		this.rNum=rNum;
	}
}
