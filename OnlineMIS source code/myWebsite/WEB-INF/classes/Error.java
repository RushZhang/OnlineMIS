//操作成功界面

package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class Error extends HttpServlet {
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//业务逻辑
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
    	//这样doPost和doGet就合二为一了
    	this.doGet(req,res);
    }
    
}
