//validator
package com.rush;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginValidator extends HttpServlet {
//================================重写init和destroy用来计数=================================================================================================
//********************************因为servletContext在内存中操作所以速度快，结束时（destroy）一次性写回磁盘文件*********************************************	
	//重写init函数
	public void init(){
		try {
			
			//读取计数
			FileReader f=new FileReader("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 5.0\\webapps\\myWebsite\\myCounter.txt");
			BufferedReader br=new BufferedReader(f);
			String numVal=br.readLine();
			br.close();
			//times值放入到servletcontext，因为浏览次数是要被所有人看，所以要放servletcontext
			this.getServletContext().setAttribute("visitTimes",numVal);
			
			System.out.println("init is being called ");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//重写destory函数
	public void destroy(){
		try {
			
			//再将新的次数写回到文件
			FileWriter fw=new FileWriter("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 5.0\\webapps\\myWebsite\\myCounter.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(this.getServletContext().getAttribute("visitTimes").toString());
			bw.close();
			
			System.out.println("destroy!!!!!!!!!!!!!!!!! is being called ");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
//=============================================================================================================================================================	
	
	//处理get请求
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//业务逻辑
		
		Connection ct=null;//这三个资源需要放在try外边，这样才能在finally关闭
		Statement sm=null;
		ResultSet rs=null;
		
		try{
			//接收用户名和密码
			String u1=req.getParameter("username");//是Login中控件的名字
			String p1=req.getParameter("password");//是Login中控件的名字
			
			//调用UserBeanControl: 1.创建一个对象；2.使用一个方法； 
			UserBeanControl ubc=new UserBeanControl();
			
			if(ubc.checkUser(u1,p1)){
			
					//真的合法了
					String keep=req.getParameter("keep");
					//用户还选了cookie
					if(keep!=null){
						//用cookie把用户信息保存在客户端
						//1.创建cookie
						Cookie name=new Cookie("myname",u1);
						Cookie pass=new Cookie("mypasswd",p1);
						//2.设置时间
						name.setMaxAge(14*24*3600);
						pass.setMaxAge(14*24*3600);
						//3.回写到客户端
						res.addCookie(name);
						res.addCookie(pass);
					}
					
	
					
					//==================session===================
					//将验证成功的信息写入session
					//第一步：得到session
					HttpSession hs=req.getSession(true);
					//修改session的存在时间(默认30分钟)，单位是秒
					hs.setMaxInactiveInterval(20*60);
					//设置session的值和value
					hs.setAttribute("pass","ok");
					hs.setAttribute("name",u1);
					//============================================
					
					
					
					//==================加访问次数================
					//将servletContext中visitTime对应的值++
					String times=this.getServletContext().getAttribute("visitTimes").toString();
					//对times++再重新放回到servlet
					this.getServletContext().setAttribute("visitTimes",(Integer.parseInt(times)+1)+"");
					//============================================
					
					
				
					//跳转到Main
					res.sendRedirect("main?username="+u1);	
			
			}else{
				//说明用户名不存在
				//不合法
				
				//跳转
				res.sendRedirect("login?info=nameError");//写要到的servlet的那个url
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(sm!=null) sm.close();
				if(ct!=null) ct.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//这样doPost和doGet就合二为一了
    	this.doGet(req,res);
    }
    
}