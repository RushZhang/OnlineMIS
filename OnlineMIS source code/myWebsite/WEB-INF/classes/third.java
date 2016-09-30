package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class third extends HttpServlet {
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//业务逻辑
		try{
			PrintWriter pw=res.getWriter();
			pw.println("hello,http");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//这样doPost和doGet就合二为一了
    	this.doGet(req,res);
    }
    
}