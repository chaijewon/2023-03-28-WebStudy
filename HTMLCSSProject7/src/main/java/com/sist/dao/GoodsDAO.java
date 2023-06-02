package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbconn.*;
public class GoodsDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static GoodsDAO dao;
   private CreateDataBase db=new CreateDataBase();
   public static GoodsDAO newInstance()
   {
	   if(dao==null)
		   dao=new GoodsDAO();
	   return dao;
   }
   // 기능 
   // 1. 목록 => 12
   // 2. 상세 => 디자인된 파일로 출력 
   /*
    *    NO                                        NOT NULL NUMBER
		 GOODS_NAME                                NOT NULL VARCHAR2(1000)
		 GOODS_SUB                                          VARCHAR2(1000)
		 GOODS_PRICE                               NOT NULL VARCHAR2(50)
		 GOODS_DISCOUNT                                     NUMBER
		 GOODS_FIRST_PRICE                                  VARCHAR2(20)
		 GOODS_DELIVERY                            NOT NULL VARCHAR2(20)
		 GOODS_POSTER                                       VARCHAR2(260)
		 HIT                                                NUMBER
    */
   public GoodsVO goodsDetailData(int no)
   {
	   GoodsVO vo=new GoodsVO();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,goods_name,goods_sub,"
				     +"goods_price,goods_discount,"
				     +"goods_first_price,goods_delivery,"
				     +"goods_poster "
				     +"FROM goods_all "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next(); // 데이터가 출력된 위치에 커서 이동 
		   vo.setNo(rs.getInt(1));
		   vo.setName(rs.getString(2));
		   vo.setSub(rs.getString(3));
		   vo.setPrice(rs.getString(4));
		   vo.setDiscount(rs.getInt(5));
		   vo.setFirst_price(rs.getString(6));
		   vo.setDelivery(rs.getString(7));
		   vo.setPoster(rs.getString(8));
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
