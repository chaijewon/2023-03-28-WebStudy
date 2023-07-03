package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.common.*;
public class FoodDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateDataBase db=new CreateDataBase();
   private static FoodDAO dao;
   
   // 싱글턴
   public static FoodDAO newInstance()
   {
	   if(dao==null)
		   dao=new FoodDAO();
	   return dao;
   }
   
   // 기능 => 카테고리 
   public List<CategoryVO> foodCategoryListData()
   {
	   List<CategoryVO> list=new ArrayList<CategoryVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT /*+ INDEX_ASC(food_category fc_cno_pk)*/ cno,title,subject,poster "
				     +"FROM food_category";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   CategoryVO vo=new CategoryVO();
			   vo.setCno(rs.getInt(1));
			   vo.setTitle(rs.getString(2));
			   vo.setSubject(rs.getString(3));
			   vo.setPoster(rs.getString(4));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return list;
   }
   // 지역별 맛집 찾기 
   public List<FoodVO> foodLocationFindData(String fd,int page)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT fno,poster,name,num "
				     +"FROM (SELECT fno,poster,name,rownum as num "
				     +"FROM (SELECT /*+ INDEX_ASC(food_location fl_fno_pk)*/fno,poster,name "
				     +"FROM food_location WHERE address LIKE '%'||?||'%')) "
				     +"WHERE num BETWEEN ? AND ?";
		   int rowSize=12;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, fd);
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   try
			   {
			     FoodVO vo=new FoodVO();
			     vo.setFno(rs.getInt(1));
			     String poster=rs.getString(2);
			     poster=poster.substring(0,poster.indexOf("^"));
			     poster=poster.replace("#", "&");
			     vo.setPoster(poster);
			     vo.setName(rs.getString(3));
			     list.add(vo);
			   }catch(Exception ex) {}
		   }
		   rs.close();	     
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return list;
   }
   // 지역별 맛집 => 총페이지 
   public int foodLoactionTotalPage(String fd)
   {
	   int total=0;
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/12.0) "
				     +"FROM food_location "
				     +"WHERE address LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, fd);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   total=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return total;
   }
 }
