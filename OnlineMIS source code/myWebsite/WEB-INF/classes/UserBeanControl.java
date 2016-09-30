//����һ�������࣬��������user��Ҳ���ǲ���UserBean��
//ҵ���߼�������

package com.rush;

import java.sql.*;
import java.util.*;


public class UserBeanControl {

    private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private int pageCount=0;//һ���м�ҳ���ɼ������
	
	
	//================================ɾ���û�===================================
	public boolean delUser(String id){
		boolean b=false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			String sql="delete from users where userid='"+id+"'";
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();//���ص���intֵ�������յ�Ӱ�����
			if(num==1){
				//ɾ���ɹ�
				b=true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	//===========================================================================
	
	
	
	
	
	//================================�޸��û�===================================
	public boolean updateUser(String id, String passwd, String email, String grade ){
		boolean b=false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			String sql="update users set passwd='"+passwd+"' , email='"+email+"', grade='"+grade+"' where userId="+id;
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();//���ص���intֵ�������յ�Ӱ�����
			if(num==1){
				//ɾ���ɹ�
				b=true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	//===========================================================================
	
	
	
	
	
	
	//=================================�����û�==================================
	public boolean addUser(String name,String pwd,String email,String grade)
	{
		boolean b = false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			String sql = "insert into users(username,passwd,email,grade) values ('"+name+"', '"+pwd+"', '"+email+"' ,'"+grade+"')";
			System.out.println(sql);
			ps=ct.prepareStatement(sql);
			int num = ps.executeUpdate();
			if(num==1){
				//ɾ���ɹ�
				b=true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	//============================================================================
	
	
	
	
	
	
	//============================����pageCount===================================
	public int getPageCount(){
		return this.pageCount;
	}
	//============================================================================
	
	
	//=============================��ҳ��ʾ=======================================
	public ArrayList getResultByPage(int pageNow, int pageSize){
		
		ArrayList al=new ArrayList();
		
		try{
			
			int rowCount=0;//һ���ж�������¼�����õ���
				
			//------------------------------�õ�row Count-------------------------
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			ps=ct.prepareStatement("select count(*) from users");
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				rowCount=rs.getInt(1);
			}
			//--------------------------------------------------------------------
			
			//����pageCount
			if(rowCount%pageSize==0){
				pageCount=rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;
			}
			
			
			ps=ct.prepareStatement(
				"select top "+pageSize+" * from users where userId not in (select top "+pageSize*(pageNow-1)+" userId from users)");
			rs=ps.executeQuery();
			
			while(rs.next()){
				//��rs��ÿһ����¼����װ��UserBean ub
				UserBean ub=new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setMail(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				//װ��ȥ
				al.add(ub);
			}		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return al;
	}
	//========================================================================================
	
	
	
	
	
	
	/**=============================��ȷ���Һ�ģ������========================================
	 * @param type �������� 1��ʾģ������,2��ʾ��ȷ����
	 * @param name ���Ҷ��������
	 * @param pageNow ��ǰ�ڼ�ҳ
	 * @param pageSize	ÿҳ��ʾҳ��
	 * @return
	 */
	public ArrayList getResultByPage(String type,String name,int pageNow,int pageSize)
	{
		ArrayList al2=new ArrayList();
		
		try{
			int type_int = 0;//��ʼ��type_int=0
		
			if(type != null)
			{
				//���type��Ϊ��,���û�����˾�ȷ��ģ������,���ø�String����ת��Ϊint����
				//���typeΪ������ type_int Ϊ0,�������ȥ����(����һ��������)
				type_int = Integer.parseInt(type);//1����ģ������,2����ȷ����
			}
			
			int rowCount = 0;//���м�����¼
						
			String sql = "select count(*) from users where username='"+name+"'";
			
			//����username,�����ܵļ�¼��
			//------------------------------�õ�row Count-------------------------
				ConnDB cd=new ConnDB();
				ct=cd.getConn();
				ps=ct.prepareStatement("select count(*) from users");
				
				rs=ps.executeQuery();
				
				if(rs.next()){
					rowCount=rs.getInt(1);
				}
			//--------------------------------------------------------------------
				
			//------------------------------����pageCount-------------------------
			if(rowCount%pageSize == 0)
			{
				pageCount = rowCount/pageSize;
			}
			else
			{
				pageCount = rowCount/pageSize + 1;	
			}
			//--------------------------------------------------------------------
			
			String sql2 ="";
			
			//2��ʾ�Ǿ�ȷ����,���ؾ�ȷ���Һ������
			if(type_int == 2)
			{
				sql2 = "select top "+pageSize+" * from users where userId not in " +
				"(select top "+ pageSize*(pageNow-1)+" userId from users where username='"+name+"') and username='"+name+"'";
				
				
				ps=ct.prepareStatement(sql2);
				
				rs=ps.executeQuery();
				
					
				while(rs.next()){
					//��rs��ÿһ����¼����װ��UserBean ub
					UserBean ub2=new UserBean();
					ub2.setUserId(rs.getInt(1));
					ub2.setUserName(rs.getString(2));
					ub2.setPasswd(rs.getString(3));
					ub2.setMail(rs.getString(4));
					ub2.setGrade(rs.getInt(5));
					//װ��ȥ
					al2.add(ub2);
				}		
				
			}
			//1��ʾ��ģ������,����ģ�����Һ������
			else if(type_int == 1)
			{
				sql2 = "select top "+pageSize+"  * from users where userId not in " +
				"(select top "+ pageSize*(pageNow-1)+" userId from users where username like '%"+name+"%') and username like '%"+name+"%'";
				
				System.out.println(sql2);
				
				ps=ct.prepareStatement(sql2);
				
				rs=ps.executeQuery();
					
				while(rs.next()){
					//��rs��ÿһ����¼����װ��UserBean ub
					UserBean ub2=new UserBean();
					ub2.setUserId(rs.getInt(1));
					ub2.setUserName(rs.getString(2));
					ub2.setPasswd(rs.getString(3));
					ub2.setMail(rs.getString(4));
					ub2.setGrade(rs.getInt(5));
					//װ��ȥ
					al2.add(ub2);
				}	
						
			}
		
			//0��ʾ û�г���type����,���û���һ�ε�½�ò�ѯ����,����һ��������
			else if(type_int == 0)
			{
				return null;
			}
			
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return al2;
		
		
		
		
	}
	//=========================================================================================
	
	
	
	/*==========================================����===========================================
	 * @param pageNow
	 * @param pageSize
	 * @return List
	 */
	/*public List search(String username,int radSearch)
	{
		List userListByName = null;
				
		String sql1 ="";
		
		if(radSearch == 2)
		{
			sql1 = "select  * from users where username =? ";
		}
		if(radSearch == 1)
		{
			sql1 = "select * from users where username like %?%";
		}
				
		return userListByName = dealDateBase.query(sql1,new UserMapping(),username);
	}
	*/
	//=========================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//====================================��֤�û�=============================================
	public boolean checkUser(String u, String p){
		boolean b=false;
		try{
			//�õ�����
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			ps=ct.prepareStatement("select top 1 passwd from users where username=?");
			ps.setString(1,u);
			rs=ps.executeQuery();
			if(rs.next()){
				String dbPasswd=rs.getString(1);
				if(dbPasswd.equals(p)){
					b=true;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		
		return b;
	}
	//=========================================================================================
	
	
	
	
	//===================================�ر���Դ==============================================
	public void close(){
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
			if(ct!=null){
				ct.close();
				ct=null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	//=========================================================================================
    
    
}