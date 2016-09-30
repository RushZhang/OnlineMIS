package com.rush;

import javax.servlet.*;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;



public class first  implements Servlet{

  
	//该函数用于初始化该servlet，类似于类的构造函数
	//只会被调用一次（当用户第一次访问该servlet时被调用）
	public void init(ServletConfig parm1) throws ServletException {
		System.out.println("init it");
	}


	public ServletConfig getServletConfig() {
		return null;
	}



	//这个函数用于处理业务逻辑，程序员应当把业务逻辑代码写在这儿
	//这个函数会被调用多次，每当用户访问这个servlet，就会被调用
	//req用于获得客户端（浏览器）信息，res用于向客户端（浏览器）返回信息
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service it");//向控制台打印
		//从res中得到PrintWriter
		PrintWriter pw=res.getWriter();//向浏览器打印
		pw.println("Hello,world");
	}

	
	public String getServletInfo() {
		return "";
	}




	//销毁servlet实例（释放内存）,三种情况会调用destory
	//1.reload该servlet(webApps) 2.关闭Tomcat 3.关机	
	public void destroy() {
		System.out.println("destory");
	}
    
    
}