package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class Update extends HttpServlet {
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		try{
			PrintWriter pw=res.getWriter();
			pw.println("<html>");
			pw.println("<body bgcolor=#FFEFA1>");
			pw.println("<img src='imgs/welcome.gif'><hr><center><br>");
			
			pw.println("<h1><font face='Verdana'>Update User<font></h1>");
			pw.println("<form action=updatecl>");
			pw.println("<table border=1>");
			//ע��readonly�ؼ���, colspan��ʾ���������
			pw.println("<tr><td>id</td><td><input readonly type=text name=newId value="+req.getParameter("uId")+"></td></tr>");
			pw.println("<tr><td>name</td><td><input readonly type=text value="+req.getParameter("uName")+"></td></tr>");
			pw.println("<tr><td>password</td><td><input type=text name=newPassword value="+req.getParameter("uPass")+"></td></tr>");
			pw.println("<tr><td>email</td><td><input type=text name=newEmail value="+req.getParameter("uMail")+"></td></tr>");
			pw.println("<tr><td>grade</td><td><input type=text name=newGrade value="+req.getParameter("uGrade")+"></td></tr>");
			pw.println("<tr><td colspan=2><input type=submit value= Update It ></td></tr>");
			pw.println("</table></form>");
			
			pw.println("</center><hr><img src=imgs/hippo.gif>");
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
