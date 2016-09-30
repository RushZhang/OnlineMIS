package com.rush;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Main extends HttpServlet {
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private int userid=0;
	
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		
		try{
			String username=req.getParameter("username").toString();
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			ps=ct.prepareStatement("select userid from users where username = '"+username+"'");
			rs=ps.executeQuery();
			
			if(rs.next()){
				userid=rs.getInt(1);
			}
			
			PrintWriter pw=res.getWriter();
			pw.println("<html>");
			pw.println("<body bgcolor=#FF1069>");
			pw.println("<img src='imgs/welcome.gif'>&nbsp;<img src=imgs/"+userid+".jpg width=20px height=20px><strong>Hello, "+username+"<strong><img src='imgs/logo.png' align=right><br><br><br><hr><center><br>");
			pw.println("<h1><font face='Verdana'>Main Menu<font></h1>");
			pw.println("<a href=welcome><font face='Dotum'>Manage User<font></a><br>");
			pw.println("<a href=adduser><font face='Dotum'>Create User<font></a><br>");
			pw.println("<a href=searchuser><font face='Dotum'>Search User<font></a><br>");
			pw.println("<a href=login><font face='Dotum'>Log out<font></a><br>");
			
			
			//显示用户的ip地址
			pw.println("<br><br><br><i>This website has been visited "+this.getServletContext().getAttribute("visitTimes").toString()+" times</i><br>");
			pw.println("Your IP address is : "+req.getRemoteAddr()+"<br>");
			pw.println("Your computer name is : "+req.getRemoteHost()+"<br>");
			
			pw.println("</center><hr><img src=imgs/orangefox.gif>");
			pw.println("</body>");
			pw.println("</html>");
			System.out.println("YEAH"+req.getParameter("username"));
			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//这样doPost和doGet就合二为一了
    	this.doGet(req,res);
    }
    
}
