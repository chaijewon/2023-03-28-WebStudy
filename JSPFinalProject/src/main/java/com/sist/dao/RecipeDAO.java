package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.common.*;
public class RecipeDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateDataBase db=new CreateDataBase();
   private static RecipeDAO dao;
   
   // 싱글턴 
   public static RecipeDAO newInstance()
   {
	   if(dao==null)
		   dao=new RecipeDAO();
	   return dao;
   }
   // 목록 출력 
   public List<RecipeVO> recipeListData(int page)
   {
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   // recipe_no_pk
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,poster,title,num "
				     +"FROM (SELECT no,poster,title,rownum as num "
				     +"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no,poster,title "
				     +"FROM recipe)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   //?에 값을 채운다 
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   RecipeVO vo=new RecipeVO();
			   vo.setNo(rs.getInt(1));
			   vo.setPoster(rs.getString(2));
			   vo.setTitle(rs.getString(3));
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
   public int recipeRowCount()
   {
	   int count=0;
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT COUNT(*) FROM recipe";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   count=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return count;
   }
   // 쉐프 
   public List<ChefVO> chefListData(int page)
   {
	   List<ChefVO> list=new ArrayList<ChefVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT chef,poster,mem_cont1,mem_cont2,mem_cont4,mem_cont3,num "
				     +"FROM (SELECT chef,poster,mem_cont1,mem_cont2,mem_cont4,mem_cont3,rownum as num "
				     +"FROM (SELECT chef,poster,mem_cont1,mem_cont2,mem_cont4,mem_cont3 "
				     +"FROM chef)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   //?에 값을 채운다 
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   ChefVO vo=new ChefVO();
			   vo.setChef(rs.getString(1));
			   vo.setPoster(rs.getString(2));
			   vo.setMem_cont1(rs.getString(3));
			   vo.setMem_cont2(rs.getString(4));
			   vo.setMem_cont7(rs.getString(5));
			   vo.setMem_cont3(rs.getString(6));
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
   public int chefTotalPage()
   {
	   int total=0;
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM chef";
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
   // 쉐프의 레시피 목록 
   public List<RecipeVO> chefMakeRecipeData(int page,String chef)
   {
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,poster,title,chef,num "
				     +"FROM (SELECT no,poster,title,chef,rownum as num "
				     +"FROM (SELECT no,poster,title,chef "
				     +"FROM recipe WHERE chef=?)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setString(1, chef);
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   RecipeVO vo=new RecipeVO();
			   vo.setNo(rs.getInt(1));
			   vo.setPoster(rs.getString(2));
			   vo.setTitle(rs.getString(3));
			   vo.setChef(rs.getString(4));
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
   public int chefListTotalPage(String chef)
   {
	   int total=0;
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM recipe "
				     +"WHERE chef=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, chef);
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
   public List<RecipeVO> chefFindRecipeData(int page,String chef,String fd)
   {
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,poster,title,chef,num "
				     +"FROM (SELECT no,poster,title,chef,rownum as num "
				     +"FROM (SELECT no,poster,title,chef "
				     +"FROM recipe WHERE chef=? AND title LIKE '%'||?||'%')) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setString(1, chef);
		   ps.setString(2, fd);
		   ps.setInt(3, start);
		   ps.setInt(4, end);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   RecipeVO vo=new RecipeVO();
			   vo.setNo(rs.getInt(1));
			   vo.setPoster(rs.getString(2));
			   vo.setTitle(rs.getString(3));
			   vo.setChef(rs.getString(4));
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
   public int chefFindTotalPage(String chef,String fd)
   {
	   int total=0;
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM recipe "
				     +"WHERE chef=? AND title LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, chef);
		   ps.setString(2, fd);
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





