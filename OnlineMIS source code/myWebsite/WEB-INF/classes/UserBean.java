//这是UserBean，和users表映射。就是成员变量和数据库的字段映射
//类本身是整users个表，类的一个对象表示users表的一条记录

package com.rush;


public class UserBean {

    private int userId;
    private String userName;
    private String passwd;
    private String mail;
    private int grade;
    
    public void setUserId(int userId){
    	this.userId=userId;
    }
    
    public int getUserId(){
    	return this.userId;
    }
    
    public void setUserName(String userName){
    	this.userName=userName;
    }
    
    public String getUserName(){
    	return this.userName;
    }
    
    public void setPasswd(String passwd){
    	this.passwd=passwd;
    }
    
    public String getPasswd(){
    	return this.passwd;
    }
    
    public void setMail(String mail){
    	this.mail=mail;
    }
    
    public String getMail(){
    	return this.mail;
    }
    
    public void setGrade(int grade){
    	this.grade=grade;
    }
    
    public int getGrade(){
    	return this.grade;
    }
    
}