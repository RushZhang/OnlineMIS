//�����ɹ�����

package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class Error extends HttpServlet {
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		try{
			PrintWriter pw=res.getWriter();
			pw.println("<html>");
			pw.println("<body bgcolor=#00FF5A>");
			pw.println("<img src='imgs/welcome.gif'><hr><center><br>");
			
			pw.println("<h1><font face='Verdana' color='red'>Something Wrong<font></h1>");
			
			pw.println("</center><hr><img src=imgs/fox.gif>");
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
