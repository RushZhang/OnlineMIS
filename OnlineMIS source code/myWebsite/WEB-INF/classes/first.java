package com.rush;

import javax.servlet.*;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;



public class first  implements Servlet{

  
	//�ú������ڳ�ʼ����servlet����������Ĺ��캯��
	//ֻ�ᱻ����һ�Σ����û���һ�η��ʸ�servletʱ�����ã�
	public void init(ServletConfig parm1) throws ServletException {
		System.out.println("init it");
	}


	public ServletConfig getServletConfig() {
		return null;
	}



	//����������ڴ���ҵ���߼�������ԱӦ����ҵ���߼�����д�����
	//��������ᱻ���ö�Σ�ÿ���û��������servlet���ͻᱻ����
	//req���ڻ�ÿͻ��ˣ����������Ϣ��res������ͻ��ˣ��������������Ϣ
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service it");//�����̨��ӡ
		//��res�еõ�PrintWriter
		PrintWriter pw=res.getWriter();//���������ӡ
		pw.println("Hello,world");
	}

	
	public String getServletInfo() {
		return "";
	}




	//����servletʵ�����ͷ��ڴ棩,������������destory
	//1.reload��servlet(webApps) 2.�ر�Tomcat 3.�ػ�	
	public void destroy() {
		System.out.println("destory");
	}
    
    
}