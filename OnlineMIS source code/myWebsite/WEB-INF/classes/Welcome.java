//validator


package com.rush;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Welcome extends HttpServlet{
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//�õ�session
		HttpSession hs=req.getSession(true);
		String val=(String)hs.getAttribute("pass"); //��Ϊ�ұ���Object����
		String name="";
		String passwd="";
		//�ж�
		if(val==null){
			try {
				//���session��û���û���Ϣ����ô��ȥ������û��cookie
				//�ӿͻ��˵õ�����cookie��Ϣ
				//================��cookie===============================
				Cookie [] allCookies=req.getCookies();
				int i=0;
				//���allCookies��Ϊ��...
				if(allCookies!=null){	
					//����ȡ��cookie
					for(i=0;i<allCookies.length;i++){		
						//����ȡ��
						Cookie temp=allCookies[i];		
						if(temp.getName().equals("myname")){				
							//�õ�cookie��ֵ
						 	name=temp.getValue();							
						}else if(temp.getName().equals("mypasswd")){
							passwd=temp.getValue();
						}
					}
					System.out.println("N="+name+", P="+passwd);
					if(!name.equals("")&&!passwd.equals("")){
						//��loginvalidator������֤
						res.sendRedirect("loginvalidator?username="+name+"&password="+passwd);
						return;
					}				
				}
				//=======================================================
				
				res.sendRedirect("login");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	
		
		
		
		String username=(String)hs.getAttribute("name");
		
		
		//ҵ���߼�
		try{
			PrintWriter pw=res.getWriter();
			
			pw.println("<body><center>");
			
			//��servlet����ʾͼƬ
			pw.println("<body bgcolor=#24E2FF>");
			pw.println("<img src='imgs/welcome.gif'>G<hr><center><br>");
			pw.println("<h1><font face='Verdana'>Manage User<font></h1>");
			
		
			
			//������
			pw.println("<br><a href=login>Back to Login</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=main?username="+username+">Main Menu<br>");
			
			
			
			
			
			
			
			
		
			//=================================��ҳ��ʾ���=============================
			int pageSize=3;//һҳ��ʾ������¼
			int pageNow=1;//ϣ����ʾ�ڼ�ҳ
		
			//��̬����pageNow
			String sPageNow=req.getParameter("pageNowNew");
			if(sPageNow!=null){
				pageNow=Integer.parseInt(sPageNow);
				if(pageNow<=0){
					pageNow=1;
				}
			}
			
			//����UserBeanControl UserBeanControl 
			UserBeanControl ubc=new UserBeanControl();
			ArrayList al=ubc.getResultByPage(pageNow,pageSize);
			
	
			pw.println("<table border=1");
			pw.println("<tr bgcolor=silver><th>id</th><th>name</th><th>passwd</th><th>mail</th><th>grade</th><th>Modify</th><th><font color=red>Delete</font></th>");
			
			//����һ��������ɫ
			String[] mycol={"white","silver"};
			
			for(int i=0; i<al.size(); i++){
				UserBean ub=(UserBean)al.get(i);
				pw.println("<tr bgcolor="+mycol[i%2]+">");
				pw.println("<td>"+ub.getUserId()+"</td>");
				pw.println("<td>"+ub.getUserName()+"</td>");
				pw.println("<td>"+ub.getPasswd()+"</td>");
				pw.println("<td>"+ub.getMail()+"</td>");
				pw.println("<td>"+ub.getGrade()+"</td>");
				pw.println("<td><a href=update?uId="+ub.getUserId()+"&uName="+ub.getUserName()+"&uPass="+ub.getPasswd()+"&uMail="+ub.getMail()+"&uGrade="+ub.getGrade()+">Update</a></td>");
				//onclick��js��һ��˫����Ӧ�¼�
				pw.println("<td><a href=delusercl?userid="+ub.getUserId()+" onclick=\"return window.confirm('Are you sure to delete this user?')\">Delete</a></td>");
			}
			pw.println("</table>");
			//=========================================================================
			
			
			
			
			//==================================ҳ��===================================
			if(pageNow!=1){ //��һҳ
				 pw.println("<a href=welcome?pageNowNew="+(pageNow-1)+">LastPage</a>");
			}
			int pageCount=ubc.getPageCount();
			for(int i=1; i<=pageCount; i++){
				pw.println("<a href=welcome?pageNowNew="+i+">"+i+"</a>");
			}
			
			if(pageNow!=pageCount){ //��һҳ
				 pw.println("<a href=welcome?pageNowNew="+(pageNow+1)+">NextPage</a>");
			}
			//-----------------------------------��ת-----------------------------------
			//����ʵ������һ����
			pw.println("<form action=welcome>");
			pw.println("<input type=text name=pageNowNew>");
			pw.println("<input type=submit value=go><br>");
			pw.println("</from>");
			//--------------------------------------------------------------------------
			//==========================================================================
			
			//��ʾ�û���ip��ַ
			pw.println("<br><br><i>This website has been visited "+this.getServletContext().getAttribute("visitTimes").toString()+" times</i><br>");
			pw.println("Your IP address is : "+req.getRemoteAddr()+"<br>");
			pw.println("Your computer name is : "+req.getRemoteHost()+"<br>");
			
			
			
			
			
	
			pw.println("</center><hr><img src=imgs/fox.gif>");
			pw.println("</body>");
		
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
   
    public void doPost(HttpServletRequest req, HttpServletResponse res){
    	//����doPost��doGet�ͺ϶�Ϊһ��
    	this.doGet(req,res);
    }
    
}