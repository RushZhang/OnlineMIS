package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class Login extends HttpServlet {
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		try{
			PrintWriter pw=res.getWriter();
			pw.println("<html>");
			pw.println("<body bgcolor=#00FF5A>");
			pw.println("<img src='imgs/welcome.gif'><img src='imgs/logo.png' align=right><br><br><br><hr><center><br>");
			//�õ�error��Ϣ
			String info=req.getParameter("info");
			if(info!=null){
				pw.println("<h1><font color='red'>Your Username does not match your Password</font></h1>");
			}
			pw.println("<h1><font face='Verdana'>LOGIN HERE<font></h1>");
			pw.println("<form action=loginvalidator method=post>");//action���Ҳ���Լ�Ҫ��ת��url
			pw.println("<font face='Dotum'>Username: </font><input type=text name=username><br>");
			pw.println("<font face='Dotum'>Password: </font><input type=password name=password><br>");
			pw.println("<input type=checkbox name=keep value=2>Save password for 2 weeks<br>");
			pw.println("<input type=submit value=Enter!><br>");
			pw.println("</form>");
			pw.println("</center><hr><img src=imgs/whitefox.gif>");
			pw.println("</body>");
			pw.println("</html>");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//����doPost��doGet�ͺ϶�Ϊһ��
    	this.doGet(req,res);
    }
    
}
