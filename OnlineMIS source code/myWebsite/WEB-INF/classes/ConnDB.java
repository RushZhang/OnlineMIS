//从数据库中得到链接
package com.rush;

import java.sql.*;

public class ConnDB {

    private Connection ct=null;
    
    public Connection getConn(){
    	try{
    		String url="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=servlet";
    		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
    		ct=DriverManager.getConnection(url,"sa","");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return ct;
    }
    
    
}