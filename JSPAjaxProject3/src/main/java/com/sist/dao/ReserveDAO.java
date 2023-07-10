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
		   String sql="SELECT poster,name,phone,reserve_day,fno "
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
			   vo.setFno(rs.getInt(5));
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
   // 예약 => 맛집마다 가능한 날을 읽어 온다 
   public String foodReserveDay(int fno)
   {
	   String result="";
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT reserve_day FROM food_house "
				     +"WHERE fno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, fno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   result=rs.getString(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return result;
   }
   public String reserve_day_time(int tno)
   {
	   String result="";
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT time FROM reserve_day "
				     +"WHERE rno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, tno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   result=rs.getString(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return result;
   }
   public String reserve_get_time(int tno)
   {
	   String result="";
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT time FROM reserve_time "
				     +"WHERE tno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, tno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   result=rs.getString(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return result;
   }
} 
