//操作成功界面

package com.rush;

import javax.servlet.http.*;
import java.io.*;

public class Ok extends HttpServlet {
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		
		HttpSession hs=req.getSession(true);
		String name=(String)hs.getAttribute("name"); 
		
		try{
			PrintWriter pw=res.getWriter();
			pw.println("<html>");
			pw.println("<body bgcolor=#BD00E6>");
			pw.println("<img src='imgs/welcome.gif'><hr><center><br>");
			
			pw.println("<h1><font face='Verdana'>You have successfully changed the item<font></h1>");
			pw.println("<a href=main?username="+name+">Main Menu</a>&nbsp;&nbsp;&nbsp;<a href=welcome>Continue</a>");
			
			pw.println("</center><hr><img src=imgs/dog.gif>");
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
