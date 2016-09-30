package com.rush;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AddUserCl extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		
		PrintWriter out = response.getWriter();
		UserBeanControl ubc=new UserBeanControl();
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		// 调用UserDAO中的删除用户的方法
		if (ubc.addUser(username, passwd, email, grade))
		{
			// 修改成功
			response.sendRedirect("ok");
		} else
		{
			// 修改失败
			response.sendRedirect("error");
		}

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}

}
