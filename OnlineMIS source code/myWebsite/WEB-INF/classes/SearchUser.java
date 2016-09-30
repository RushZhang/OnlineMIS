package com.rush;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SearchUser extends HttpServlet
{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		
		
		PrintWriter out = response.getWriter();
		
		//得到在服务器端的session内存中的信息(3个属性),该信息是
		//在登录成功时,设置的
		HttpSession hs = request.getSession(true);
		String username =(String) hs.getAttribute("name");
	//	List myList = (List)hs.getAttribute("myList");
		
		out.println("<html>");
		out.println("<body bgcolor=#FF1210><center>");
		out.println("<img src='imgs/welcome.gif'><hr><center><br>");
			
		
		out.println("<h1><font face='Verdana'>Search User<font></h1>");
		out.println("<br><a href=main?username="+username+">Main menu</a>");
		
		
		String name = request.getParameter("username");
		System.out.println(name);
		
		String type = request.getParameter("radtype");
		System.out.println(type);//1表示模糊查找,2表示精确查找
		
	
		//==========================分页功能==========================
		int pageSize = 5;//一页显示几条记录 
		int pageNow = 1;//希望显示第几页
		
		UserBeanControl ubc = new UserBeanControl();
		
		
		
		//动态的接收pageNow
		String sPageNow = request.getParameter("pageNow");
		
		if(sPageNow != null)
		{
			pageNow = Integer.parseInt(sPageNow);
		}

		out.println("<form action=searchuser>");
		out.println("Input User Name: <input type=text name=username>");
		out.println("<input type=submit value=Search ><br>");
		out.println("<input type=radio name=radtype value=1 >fuzz check");
		out.println("<input type=radio name=radtype value=2 checked>exact search");
		out.println("</form>");
		/*
		List list = null;
		
		if(myList != null)
		{
			list =userdao.getResusltByPage(myList,pageNow,pageSize);
			
		}*/
		
		ArrayList al = ubc.getResultByPage(type,name,pageNow, pageSize);
		
		out.println("<table border =1>");
		
		out.println("<tr bgcolor=pink><th>User ID</th><th>User Name</th><th>User Password</th><th>Email</th><th>Grade</th></tr>");
		
		//定义一个颜色数组
		String[] myCol = {"silver","pink"};
		
		if(al != null)
		{
			for(int i = 0;i<al.size();i++)
			{
				UserBean user =(UserBean) al.get(i);
				out.println("<tr bgcolor="+myCol[i%2]+">");
				out.println("<td>"+user.getUserId()+"</td>");
				out.println("<td>"+user.getUserName()+"</td>");
				out.println("<td>"+user.getPasswd()+"</td>");
				out.println("<td>"+user.getMail()+"</td>");
				out.println("<td>"+user.getGrade()+"</td>");
				out.println("</tr>");
				}
		}
		
		out.println("</table><br><br>");
			//上一页
			if(pageNow !=1)
			out.println("<a href=searchuser?pageNow="+(pageNow-1)+"&username="+name+"&radtype="+type+" >Last Page</a>");
			
			//显示超链接
			for(int j = pageNow;j<=pageNow+pageSize-1; j ++)
			{
				out.println("<a href=searchuser?pageNow="+j+"&username="+name+"&radtype="+type+" > <"+j+"> </a>");
			}
			
			int pageCount = ubc.getPageCount();
			//下一页
			if(pageNow != pageCount)
			out.println("<a href=searchuser?pageNow="+(pageNow+1)+"&username="+name+"&radtype="+type+" >Next Page</a><br><br>");
			
			out.println("</center><hr><img src=imgs/blackdog.gif>");
			out.println("</center></body>");
			out.println("</html>");
			out.flush();
			out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request, response);
	}


}
