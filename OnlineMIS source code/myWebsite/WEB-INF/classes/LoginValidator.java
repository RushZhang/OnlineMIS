//validator
package com.rush;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginValidator extends HttpServlet {
//================================��дinit��destroy��������=================================================================================================
//********************************��ΪservletContext���ڴ��в��������ٶȿ죬����ʱ��destroy��һ����д�ش����ļ�*********************************************	
	//��дinit����
	public void init(){
		try {
			
			//��ȡ����
			FileReader f=new FileReader("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 5.0\\webapps\\myWebsite\\myCounter.txt");
			BufferedReader br=new BufferedReader(f);
			String numVal=br.readLine();
			br.close();
			//timesֵ���뵽servletcontext����Ϊ���������Ҫ�������˿�������Ҫ��servletcontext
			this.getServletContext().setAttribute("visitTimes",numVal);
			
			System.out.println("init is being called ");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//��дdestory����
	public void destroy(){
		try {
			
			//�ٽ��µĴ���д�ص��ļ�
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
	
	//����get����
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		//ҵ���߼�
		
		Connection ct=null;//��������Դ��Ҫ����try��ߣ�����������finally�ر�
		Statement sm=null;
		ResultSet rs=null;
		
		try{
			//�����û���������
			String u1=req.getParameter("username");//��Login�пؼ�������
			String p1=req.getParameter("password");//��Login�пؼ�������
			
			//����UserBeanControl: 1.����һ������2.ʹ��һ�������� 
			UserBeanControl ubc=new UserBeanControl();
			
			if(ubc.checkUser(u1,p1)){
			
					//��ĺϷ���
					String keep=req.getParameter("keep");
					//�û���ѡ��cookie
					if(keep!=null){
						//��cookie���û���Ϣ�����ڿͻ���
						//1.����cookie
						Cookie name=new Cookie("myname",u1);
						Cookie pass=new Cookie("mypasswd",p1);
						//2.����ʱ��
						name.setMaxAge(14*24*3600);
						pass.setMaxAge(14*24*3600);
						//3.��д���ͻ���
						res.addCookie(name);
						res.addCookie(pass);
					}
					
	
					
					//==================session===================
					//����֤�ɹ�����Ϣд��session
					//��һ�����õ�session
					HttpSession hs=req.getSession(true);
					//�޸�session�Ĵ���ʱ��(Ĭ��30����)����λ����
					hs.setMaxInactiveInterval(20*60);
					//����session��ֵ��value
					hs.setAttribute("pass","ok");
					hs.setAttribute("name",u1);
					//============================================
					
					
					
					//==================�ӷ��ʴ���================
					//��servletContext��visitTime��Ӧ��ֵ++
					String times=this.getServletContext().getAttribute("visitTimes").toString();
					//��times++�����·Żص�servlet
					this.getServletContext().setAttribute("visitTimes",(Integer.parseInt(times)+1)+"");
					//============================================
					
					
				
					//��ת��Main
					res.sendRedirect("main?username="+u1);	
			
			}else{
				//˵���û���������
				//���Ϸ�
				
				//��ת
				res.sendRedirect("login?info=nameError");//дҪ����servlet���Ǹ�url
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
    	//����doPost��doGet�ͺ϶�Ϊһ��
    	this.doGet(req,res);
    }
    
}