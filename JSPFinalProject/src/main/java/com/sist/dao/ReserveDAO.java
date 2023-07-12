package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;
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
   // 예약 등록 
   /*
    *   no NUMBER,
	   id VARCHAR2(20),
	   fno NUMBER,
	   rday VARCHAR2(30) CONSTRAINT ri_day_nn NOT NULL,
	   rtime VARCHAR2(30) CONSTRAINT ri_time_nn NOT NULL,
	   inwon VARCHAR2(30) CONSTRAINT ri_inwon_nn NOT NULL,
	   rok CHAR(1) DEFAULT 'n',
	   regdate DATE DEFAULT SYSDATE,
    */
   //JDBC => 성공(commit) => 실패 (rollback)
   public void reserve_ok(ReserveVO vo)
   {
	   try
	   {
		   conn=db.getConnection();
		   String sql="INSERT INTO reserve_info VALUES("
				     +"ri_no_seq.nextval,?,?,?,?,?,'n',SYSDATE)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getId());
		   ps.setInt(2, vo.getFno());
		   ps.setString(3, vo.getRday());
		   ps.setString(4, vo.getRtime());
		   ps.setString(5, vo.getInwon());
		   ps.executeUpdate();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
   }
   // 예약현황 출력 
   public List<ReserveVO> reserveInfoData(String id)
   {
	   List<ReserveVO> list=new ArrayList<ReserveVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,fno,rday,rtime,inwon,TO_CHAR(regdate,'yyyy-MM-dd hh24:mi:ss'),"
				     +"foodGetPoster(fno),foodGetName(fno),foodGetPhone(fno),rok,id "
				     +"FROM reserve_info "
				     +"WHERE id=? "
				     +"ORDER BY no DESC";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   ReserveVO vo=new ReserveVO();
			   vo.setNo(rs.getInt(1));
			   vo.setFno(rs.getInt(2));
			   vo.setRday(rs.getString(3));
			   vo.setRtime(rs.getString(4));
			   vo.setInwon(rs.getString(5));
			   vo.setDbday(rs.getString(6));
			   String poster=rs.getString(7);
			   poster=poster.substring(0,poster.indexOf("^"));
			   poster=poster.replace("#", "&");
			   vo.setPoster(poster);
			   vo.setName(rs.getString(8));
			   vo.setTel(rs.getString(9));
			   vo.setRok(rs.getString(10));
			   vo.setId(rs.getString(11));
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
   public List<ReserveVO> reserveAdminData()
   {
	   List<ReserveVO> list=new ArrayList<ReserveVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,fno,rday,rtime,inwon,TO_CHAR(regdate,'yyyy-MM-dd hh24:mi:ss'),"
				     +"foodGetPoster(fno),foodGetName(fno),foodGetPhone(fno),rok,id "
				     +"FROM reserve_info "
				     +"ORDER BY no DESC";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   ReserveVO vo=new ReserveVO();
			   vo.setNo(rs.getInt(1));
			   vo.setFno(rs.getInt(2));
			   vo.setRday(rs.getString(3));
			   vo.setRtime(rs.getString(4));
			   vo.setInwon(rs.getString(5));
			   vo.setDbday(rs.getString(6));
			   String poster=rs.getString(7);
			   poster=poster.substring(0,poster.indexOf("^"));
			   poster=poster.replace("#", "&");
			   vo.setPoster(poster);
			   vo.setName(rs.getString(8));
			   vo.setTel(rs.getString(9));
			   vo.setRok(rs.getString(10));
			   vo.setId(rs.getString(11));
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
   public void reserveOk(int no)
   {
	   try
	   {
		   conn=db.getConnection();
		   String sql="UPDATE reserve_info SET "
				     +"rok='y' "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ps.executeUpdate();
	   }catch(Exception ex)
	   {
		  ex.printStackTrace();   
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
   }
   // 예약 정보 
   public ReserveVO reserveInfoData(int no)
   {
	   ReserveVO vo=new ReserveVO();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,rday,rtime,inwon,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
				     +"FROM reserve_info "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setNo(rs.getInt(1));
		   vo.setRday(rs.getString(2));
		   vo.setRtime(rs.getString(3));
		   vo.setInwon(rs.getString(4));
		   vo.setDbday(rs.getString(5));
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return vo;
   }
   // 맛집 정보 
   // public FoodVO findByFno(int fno);
   public FoodVO reserveFoodInfoData(int fno)
   {
	   FoodVO vo=new FoodVO();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT name,poster,address,phone,type,price,parking,menu,score "
				     +"FROM food_house "
				     +"WHERE fno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, fno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setName(rs.getString(1));
		   vo.setPoster(rs.getString(2));
		   vo.setAddress(rs.getString(3));
		   vo.setPhone(rs.getString(4));
		   vo.setType(rs.getString(5));
		   vo.setPrice(rs.getString(6));
		   vo.setParking(rs.getString(7));
		   vo.setMenu(rs.getString(8));
		   vo.setScore(rs.getDouble(9));
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return vo;
   }
} 








