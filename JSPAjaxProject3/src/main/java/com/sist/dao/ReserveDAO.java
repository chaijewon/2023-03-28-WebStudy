package com.sist.dao;
import java.util.*;
import java.sql.*;
public class ReserveDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateDataBase db=new CreateDataBase();
   private static ReserveDAO dao;
   
   // 싱글턴 
   public static ReserveDAO newInstance()
   {
	   if(dao==null)
		   dao=new ReserveDAO();
	   return dao;
   }
   // 맛집 읽기 
   public List<FoodVO> foodReserveData(String type)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT poster,name,phone,reserve_day "
				     +"FROM food_house "
				     +"WHERE type LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, type);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   String poster=rs.getString(1);
			   poster=poster.substring(0,poster.indexOf("^"));
			   poster=poster.replace("#", "&");
			   vo.setPoster(poster);
			   vo.setName(rs.getString(2));
			   vo.setPhone(rs.getString(3));
			   vo.setReserve_day(rs.getString(4));
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
} 
