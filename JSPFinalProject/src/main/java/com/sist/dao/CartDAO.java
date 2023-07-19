package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;
public class CartDAO {
   private Connection conn; // DataSource (driver,username,password,url)
   private PreparedStatement ps; // SQL ==> ResultSet => VO,List
   private CreateDataBase db=new CreateDataBase();
   private static CartDAO dao; //Spring => Singleton이 기본
   private String[] tab={"","goods_all","goods_best","goods_new","goods_special"}; 
   public static CartDAO newInstance()
   {
	   if(dao==null)
		   dao=new CartDAO();
	   return dao;
   }
   /*
    *   CART_NO  NOT NULL NUMBER       
		GOODS_NO          NUMBER       
		TYPE              NUMBER       
		AMOUNT            NUMBER       
		PRICE             NUMBER       
		ID                VARCHAR2(20) 
		ISCHECK           NUMBER       
		ISSALE            NUMBER       
		REGDATE           DATE
    */
   public void cartInsert(CartVO vo)
   {
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT COUNT(*) "
				     +"FROM project_cart "
				     +"WHERE goods_no=? AND type=? AND issale<>1";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getGoods_no());
		   ps.setInt(2, 1);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   int count=rs.getInt(1);
		   rs.close();
		   
		   if(count!=0)
		   {
			   sql="UPDATE project_cart SET "
				  +"amount=amount+"+vo.getAmount()
				  +"WHERE goods_no=? AND type=? AND id=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, vo.getGoods_no());
			   ps.setInt(2, 1);
			   ps.setString(3, vo.getId());
			   ps.executeUpdate();
		   }
		   else
		   {
			   sql="INSERT INTO project_cart(cart_no,goods_no,type,amount,price,id) "
					     +"VALUES(pc_cno_seq.nextval,?,?,?,?,?)";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1,vo.getGoods_no());
			   ps.setInt(2, 1);
			   ps.setInt(3, vo.getAmount());
			   ps.setInt(4, vo.getPrice());
			   ps.setString(5, vo.getId());
			   ps.executeUpdate();
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
   }
   public List<CartVO> mypageCartListData(String id)
   {
	   List<CartVO> list=new ArrayList<CartVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT cart_no,goods_no,"
				     +"(SELECT goods_name FROM goods_all WHERE no=pc.goods_no) as goods_name,"
				     +"(SELECT goods_poster FROM goods_all WHERE no=pc.goods_no) as goods_poster,"
				     +"(SELECT goods_price FROM goods_all WHERE no=pc.goods_no) as goods_price, "
				     +"amount,regdate,issale,ischeck,price "
				     +"FROM project_cart pc "
				     +"WHERE id=? AND issale<>1 "
				     +"ORDER BY cart_no DESC";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   CartVO vo=new CartVO();
			   vo.setCart_no(rs.getInt(1));
			   vo.setGoods_no(rs.getInt(2));
			   vo.setGoods_name(rs.getString(3));
			   vo.setGoods_poster(rs.getString(4));
			   vo.setGoods_price(rs.getString(5));
			   vo.setAmount(rs.getInt(6));
			   vo.setRegdate(rs.getDate(7));
			   vo.setIssale(rs.getInt(8));
			   vo.setIscheck(rs.getInt(9));
			   vo.setPrice(rs.getInt(10));
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
   public List<CartVO> adminpageCartListData(String id)
   {
	   List<CartVO> list=new ArrayList<CartVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT cart_no,goods_no,"
				     +"(SELECT goods_name FROM goods_all WHERE no=pc.goods_no) as goods_name,"
				     +"(SELECT goods_poster FROM goods_all WHERE no=pc.goods_no) as goods_poster,"
				     +"(SELECT goods_price FROM goods_all WHERE no=pc.goods_no) as goods_price, "
				     +"amount,regdate,issale,ischeck,price "
				     +"FROM project_cart pc "
				     +"WHERE id=? AND issale=1 "
				     +"ORDER BY cart_no DESC";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   CartVO vo=new CartVO();
			   vo.setCart_no(rs.getInt(1));
			   vo.setGoods_no(rs.getInt(2));
			   vo.setGoods_name(rs.getString(3));
			   vo.setGoods_poster(rs.getString(4));
			   vo.setGoods_price(rs.getString(5));
			   vo.setAmount(rs.getInt(6));
			   vo.setRegdate(rs.getDate(7));
			   vo.setIssale(rs.getInt(8));
			   vo.setIscheck(rs.getInt(9));
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
   // AdminOk
   public void cartBuy(int no)
   {
	   try
	   {
		   conn=db.getConnection();
		   String sql="UPDATE project_cart SET "
				     +"issale=1 "
				     +"WHERE cart_no=?";
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
   // 구매 취소
   public void cartCancel(int no)
   {
	   try
	   {
		   conn=db.getConnection();
		   String sql="DELETE FROM project_cart "
				     +"WHERE cart_no=?";
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
   public List<CartVO> mypageCartBuyListData(String id)
   {
	   List<CartVO> list=new ArrayList<CartVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT cart_no,goods_no,"
				     +"(SELECT goods_name FROM goods_all WHERE no=pc.goods_no) as goods_name,"
				     +"(SELECT goods_poster FROM goods_all WHERE no=pc.goods_no) as goods_poster,"
				     +"(SELECT goods_price FROM goods_all WHERE no=pc.goods_no) as goods_price, "
				     +"amount,regdate,issale,ischeck,price "
				     +"FROM project_cart pc "
				     +"WHERE id=? AND issale=1 "
				     +"ORDER BY cart_no DESC";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   CartVO vo=new CartVO();
			   vo.setCart_no(rs.getInt(1));
			   vo.setGoods_no(rs.getInt(2));
			   vo.setGoods_name(rs.getString(3));
			   vo.setGoods_poster(rs.getString(4));
			   vo.setGoods_price(rs.getString(5));
			   vo.setAmount(rs.getInt(6));
			   vo.setRegdate(rs.getDate(7));
			   vo.setIssale(rs.getInt(8));
			   vo.setIscheck(rs.getInt(9));
			   vo.setPrice(rs.getInt(10));
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
