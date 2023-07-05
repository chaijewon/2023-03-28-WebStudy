package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.GoodsVO;
public class GoodsDAO {
  private Connection conn;
  private PreparedStatement ps;
  private CreateDataBase db=new CreateDataBase();
  private String[] tab={"","goods_all","goods_best","goods_new","goods_special"};
  private static GoodsDAO dao;
  // 싱글턴
  public static GoodsDAO newInstance()
  {
	  if(dao==null)
		  dao=new GoodsDAO();
	  return dao;
  }
  // 목록 출력 
  public List<GoodsVO> goodsListData(int page,int type)
  {
	  List<GoodsVO> list=new ArrayList<GoodsVO>();
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT no,goods_poster,goods_price,goods_name,num "
				    +"FROM (SELECT no,goods_poster,goods_price,goods_name,rownum as num "
				    +"FROM (SELECT no,goods_poster,goods_price,goods_name "
				    +"FROM "+tab[type]+" ORDER BY no ASC)) "
				    +"WHERE num BETWEEN ? AND ?";
		  ps=conn.prepareStatement(sql);
		  int rowSize=12;
		  int start=(rowSize*page)-(rowSize-1);
		  int end=rowSize*page;
		  
		  ps.setInt(1, start);
		  ps.setInt(2, end);
		  
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  GoodsVO vo=new GoodsVO();
			  vo.setNo(rs.getInt(1));
			  vo.setGoods_poster(rs.getString(2));
			  vo.setGoods_price(rs.getString(3));
			  vo.setGoods_name(rs.getString(4));
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
  // 총페이지 
  public int goodsTotalPage(int type)
  {
	  int total=0;
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT CEIL(COUNT(*)/12.0) FROM "+tab[type];
		  ps=conn.prepareStatement(sql);
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







