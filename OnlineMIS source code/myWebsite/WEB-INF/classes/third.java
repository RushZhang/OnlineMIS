package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class third extends HttpServlet {
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		try{
			PrintWriter pw=res.getWriter();
			pw.println("hello,http");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//����doPost��doGet�ͺ϶�Ϊһ��
    	this.doGet(req,res);
    }
    
}