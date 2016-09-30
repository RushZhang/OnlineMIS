//处理删除某个用户

package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class DelUserCl extends HttpServlet {
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//业务逻辑
		try{
			//调用UserBeanControl删除用户的方法
			UserBeanControl ubc=new UserBeanControl();
			//接受从welcome.java中传递的ID号
			String id=req.getParameter("userid");
			if(ubc.delUser(id)){
				//删除成功
				res.sendRedirect("ok");
			}else{
				//删除失败
				res.sendRedirect("error");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//这样doPost和doGet就合二为一了
    	this.doGet(req,res);
    }
    
}
