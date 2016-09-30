//这是一个处理类，用来处理user表（也就是操作UserBean）
//业务逻辑在这里

package com.rush;

import java.sql.*;
import java.util.*;


public class UserBeanControl {

    private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private int pageCount=0;//一共有几页（可计算出）
	
	
	//================================删除用户===================================
	public boolean delUser(String id){
		boolean b=false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			String sql="delete from users where userid='"+id+"'";
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();//返回的是int值，代表收到影响的行
			if(num==1){
				//删除成功
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
	
	
	
	
	
	//================================修改用户===================================
	public boolean updateUser(String id, String passwd, String email, String grade ){
		boolean b=false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			String sql="update users set passwd='"+passwd+"' , email='"+email+"', grade='"+grade+"' where userId="+id;
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();//返回的是int值，代表收到影响的行
			if(num==1){
				//删除成功
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
	
	
	
	
	
	
	//=================================增加用户==================================
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
				//删除成功
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
	
	
	
	
	
	
	//============================返回pageCount===================================
	public int getPageCount(){
		return this.pageCount;
	}
	//============================================================================
	
	
	//=============================分页显示=======================================
	public ArrayList getResultByPage(int pageNow, int pageSize){
		
		ArrayList al=new ArrayList();
		
		try{
			
			int rowCount=0;//一共有多少条记录（查表得到）
				
			//------------------------------得到row Count-------------------------
			ConnDB cd=new ConnDB();
			ct=cd.getConn();
			ps=ct.prepareStatement("select count(*) from users");
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				rowCount=rs.getInt(1);
			}
			//--------------------------------------------------------------------
			
			//计算pageCount
			if(rowCount%pageSize==0){
				pageCount=rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;
			}
			
			
			ps=ct.prepareStatement(
				"select top "+pageSize+" * from users where userId not in (select top "+pageSize*(pageNow-1)+" userId from users)");
			rs=ps.executeQuery();
			
			while(rs.next()){
				//将rs中每一条记录都封装到UserBean ub
				UserBean ub=new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setMail(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				//装进去
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
	
	
	
	
	
	
	/**=============================精确查找和模糊查找========================================
	 * @param type 查找类型 1表示模糊查找,2表示精确查找
	 * @param name 查找对象的名称
	 * @param pageNow 当前第几页
	 * @param pageSize	每页显示页数
	 * @return
	 */
	public ArrayList getResultByPage(String type,String name,int pageNow,int pageSize)
	{
		ArrayList al2=new ArrayList();
		
		try{
			int type_int = 0;//初始化type_int=0
		
			if(type != null)
			{
				//如果type不为空,即用户点击了精确或模糊查找,则让该String类型转换为int类型
				//如果type为空则让 type_int 为0,下面代码去处理(返回一个空数组)
				type_int = Integer.parseInt(type);//1代表模糊查找,2代表精确查找
			}
			
			int rowCount = 0;//共有几条记录
						
			String sql = "select count(*) from users where username='"+name+"'";
			
			//根据username,返回总的记录数
			//------------------------------得到row Count-------------------------
				ConnDB cd=new ConnDB();
				ct=cd.getConn();
				ps=ct.prepareStatement("select count(*) from users");
				
				rs=ps.executeQuery();
				
				if(rs.next()){
					rowCount=rs.getInt(1);
				}
			//--------------------------------------------------------------------
				
			//------------------------------计算pageCount-------------------------
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
			
			//2表示是精确查找,返回精确查找后的数组
			if(type_int == 2)
			{
				sql2 = "select top "+pageSize+" * from users where userId not in " +
				"(select top "+ pageSize*(pageNow-1)+" userId from users where username='"+name+"') and username='"+name+"'";
				
				
				ps=ct.prepareStatement(sql2);
				
				rs=ps.executeQuery();
				
					
				while(rs.next()){
					//将rs中每一条记录都封装到UserBean ub
					UserBean ub2=new UserBean();
					ub2.setUserId(rs.getInt(1));
					ub2.setUserName(rs.getString(2));
					ub2.setPasswd(rs.getString(3));
					ub2.setMail(rs.getString(4));
					ub2.setGrade(rs.getInt(5));
					//装进去
					al2.add(ub2);
				}		
				
			}
			//1表示是模糊查找,返回模糊查找后的数组
			else if(type_int == 1)
			{
				sql2 = "select top "+pageSize+"  * from users where userId not in " +
				"(select top "+ pageSize*(pageNow-1)+" userId from users where username like '%"+name+"%') and username like '%"+name+"%'";
				
				System.out.println(sql2);
				
				ps=ct.prepareStatement(sql2);
				
				rs=ps.executeQuery();
					
				while(rs.next()){
					//将rs中每一条记录都封装到UserBean ub
					UserBean ub2=new UserBean();
					ub2.setUserId(rs.getInt(1));
					ub2.setUserName(rs.getString(2));
					ub2.setPasswd(rs.getString(3));
					ub2.setMail(rs.getString(4));
					ub2.setGrade(rs.getInt(5));
					//装进去
					al2.add(ub2);
				}	
						
			}
		
			//0表示 没有出入type类型,即用户第一次登陆该查询界面,返回一个空数组
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
	
	
	
	/*==========================================查找===========================================
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//====================================验证用户=============================================
	public boolean checkUser(String u, String p){
		boolean b=false;
		try{
			//得到链接
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
	
	
	
	
	//===================================关闭资源==============================================
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