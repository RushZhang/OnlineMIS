//����ɾ��ĳ���û�

package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class DelUserCl extends HttpServlet {
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		try{
			//����UserBeanControlɾ���û��ķ���
			UserBeanControl ubc=new UserBeanControl();
			//���ܴ�welcome.java�д��ݵ�ID��
			String id=req.getParameter("userid");
			if(ubc.delUser(id)){
				//ɾ���ɹ�
				res.sendRedirect("ok");
			}else{
				//ɾ��ʧ��
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
