//����ɾ��ĳ���û�

package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class UpdateCl extends HttpServlet {
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		try{
			//����UserBeanControlɾ���û��ķ���
			UserBeanControl ubc=new UserBeanControl();
			//���ܴ�welcome.java�д��ݵ�ID��
			String newid=req.getParameter("newId");
			String newpassword=req.getParameter("newPassword");
			String newemail=req.getParameter("newEmail");
			String newgrade=req.getParameter("newGrade");
			if(ubc.updateUser(newid,newpassword,newemail,newgrade)){
				//���ĳɹ�
				res.sendRedirect("ok");
			}else{
				//����ʧ��
				res.sendRedirect("error");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//����doPost��doGet�ͺ϶�Ϊһ��
    	this.doGet(req,res);
    }
    
}
