//validator


package com.rush;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Welcome extends HttpServlet{
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//得到session
		HttpSession hs=req.getSession(true);
		String val=(String)hs.getAttribute("pass"); //因为右边是Object类型
		String name="";
		String passwd="";
		//判断
		if(val==null){
			try {
				//如果session中没有用户信息，那么再去看看有没有cookie
				//从客户端得到所有cookie信息
				//================读cookie===============================
				Cookie [] allCookies=req.getCookies();
				int i=0;
				//如果allCookies不为空...
				if(allCookies!=null){	
					//从中取出cookie
					for(i=0;i<allCookies.length;i++){		
						//依次取出
						Cookie temp=allCookies[i];		
						if(temp.getName().equals("myname")){				
							//得到cookie的值
						 	name=temp.getValue();							
						}else if(temp.getName().equals("mypasswd")){
							passwd=temp.getValue();
						}
					}
					System.out.println("N="+name+", P="+passwd);
					if(!name.equals("")&&!passwd.equals("")){
						//到loginvalidator处理验证
						res.sendRedirect("loginvalidator?username="+name+"&password="+passwd);
						return;
					}				
				}
				//=======================================================
				
				res.sendRedirect("login");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	
		
		
		
		String username=(String)hs.getAttribute("name");
		
		
		//业务逻辑
		try{
			PrintWriter pw=res.getWriter();
			
			pw.println("<body><center>");
			
			//在servlet中显示图片
			pw.println("<body bgcolor=#24E2FF>");
			pw.println("<img src='imgs/welcome.gif'>G<hr><center><br>");
			pw.println("<h1><font face='Verdana'>Manage User<font></h1>");
			
		
			
			//超链接
			pw.println("<br><a href=login>Back to Login</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=main?username="+username+">Main Menu<br>");
			
			
			
			
			
			
			
			
		
			//=================================分页显示表格=============================
			int pageSize=3;//一页显示几条记录
			int pageNow=1;//希望显示第几页
		
			//动态接受pageNow
			String sPageNow=req.getParameter("pageNowNew");
			if(sPageNow!=null){
				pageNow=Integer.parseInt(sPageNow);
				if(pageNow<=0){
					pageNow=1;
				}
			}
			
			//调用UserBeanControl UserBeanControl 
			UserBeanControl ubc=new UserBeanControl();
			ArrayList al=ubc.getResultByPage(pageNow,pageSize);
			
	
			pw.println("<table border=1");
			pw.println("<tr bgcolor=silver><th>id</th><th>name</th><th>passwd</th><th>mail</th><th>grade</th><th>Modify</th><th><font color=red>Delete</font></th>");
			
			//定义一个数组颜色
			String[] mycol={"white","silver"};
			
			for(int i=0; i<al.size(); i++){
				UserBean ub=(UserBean)al.get(i);
				pw.println("<tr bgcolor="+mycol[i%2]+">");
				pw.println("<td>"+ub.getUserId()+"</td>");
				pw.println("<td>"+ub.getUserName()+"</td>");
				pw.println("<td>"+ub.getPasswd()+"</td>");
				pw.println("<td>"+ub.getMail()+"</td>");
				pw.println("<td>"+ub.getGrade()+"</td>");
				pw.println("<td><a href=update?uId="+ub.getUserId()+"&uName="+ub.getUserName()+"&uPass="+ub.getPasswd()+"&uMail="+ub.getMail()+"&uGrade="+ub.getGrade()+">Update</a></td>");
				//onclick是js的一个双击响应事件
				pw.println("<td><a href=delusercl?userid="+ub.getUserId()+" onclick=\"return window.confirm('Are you sure to delete this user?')\">Delete</a></td>");
			}
			pw.println("</table>");
			//=========================================================================
			
			
			
			
			//==================================页码===================================
			if(pageNow!=1){ //上一页
				 pw.println("<a href=welcome?pageNowNew="+(pageNow-1)+">LastPage</a>");
			}
			int pageCount=ubc.getPageCount();
			for(int i=1; i<=pageCount; i++){
				pw.println("<a href=welcome?pageNowNew="+i+">"+i+"</a>");
			}
			
			if(pageNow!=pageCount){ //下一页
				 pw.println("<a href=welcome?pageNowNew="+(pageNow+1)+">NextPage</a>");
			}
			//-----------------------------------跳转-----------------------------------
			//这里实际上是一个表单
			pw.println("<form action=welcome>");
			pw.println("<input type=text name=pageNowNew>");
			pw.println("<input type=submit value=go><br>");
			pw.println("</from>");
			//--------------------------------------------------------------------------
			//==========================================================================
			
			//显示用户的ip地址
			pw.println("<br><br><i>This website has been visited "+this.getServletContext().getAttribute("visitTimes").toString()+" times</i><br>");
			pw.println("Your IP address is : "+req.getRemoteAddr()+"<br>");
			pw.println("Your computer name is : "+req.getRemoteHost()+"<br>");
			
			
			
			
			
	
			pw.println("</center><hr><img src=imgs/fox.gif>");
			pw.println("</body>");
		
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//这样doPost和doGet就合二为一了
    	this.doGet(req,res);
    }
    
}