package com.rush;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddUser extends HttpServlet
{
	
 
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		// req :获得客户端(浏览器)的信息
		// res：向客户端返回信息
		HttpSession hs=req.getSession(true);
		String name=(String)hs.getAttribute("name");


		PrintWriter out = res.getWriter();

		
		out.println("<html>");
		out.println("<body bgcolor=#ECFF58><center>");
		
		out.println("<img src='imgs/welcome.gif'><hr><center><br>");
			
		out.println("<h1><font face='Verdana'>Add User<font></h1>");
		
		out.println("<br><a href=main?username="+name+">Main menu</a>");
	

		out.println("<form action=addusercl method=get>");

		out.println("<table border=1>");

		out.println(
				"<tr>" +
				"<td>User Name</td>" +
				"<td><input type=text name=username ></td>" +
				"</tr>"
					);

		out.println(
				"<tr>" + 
				"<td>Password</td>"+
				"<td><input type=text name=passwd></td>" + 
				"</tr>"
					);

		out.println(
				"<tr>" + 
				"<td>Email</td>"+
				"<td><input type=text name=email></td>" + 
				"</tr>"
					);
		
		out.println(
				"<tr>" + 
				"<td>Grade</td>"+
				"<td><input type=text name=grade></td>" + 
				"</tr>"
					);
		
		out.println("<tr><td><input type=submit value=Submit></td><td><input type=reset value=Reset></td></tr>");
		out.println("</table>");

		out.println("</form>");
		
		out.println("</center><hr><img src=imgs/pig.gif>");
		out.println("<hr></center></body>");
		out.println("</html>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}

}
