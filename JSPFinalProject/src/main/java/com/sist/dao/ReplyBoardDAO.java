package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;
public class ReplyBoardDAO {
  private Connection conn;
  private PreparedStatement ps;
  private CreateDataBase db=new CreateDataBase();
  private static ReplyBoardDAO dao;
  
  public static ReplyBoardDAO newInstance()
  {
	  if(dao==null)
		  dao=new ReplyBoardDAO();
	  return dao;
  }
  /*
      try
	  {
		  conn=db.getConnection();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  db.disConnection(conn, ps);
	  }
   */
  // 사용자 
  // 목록 
  public List<ReplyBoardVO> replyBoardListData(int page)
  {
	  List<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num  "
				    +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
				    +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
				    +"FROM project_replyBoard ORDER BY group_id DESC,group_step ASC)) "
				    +"WHERE num BETWEEN ? AND ?";
		  ps=conn.prepareStatement(sql);
		  int rowSize=10;
		  int start=(rowSize*page)-(rowSize-1);
		  int end=rowSize*page;
		  
		  ps.setInt(1, start);
		  ps.setInt(2, end);
		  
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  ReplyBoardVO vo=new ReplyBoardVO();
			  vo.setNo(rs.getInt(1));
			  vo.setSubject(rs.getString(2));
			  vo.setName(rs.getString(3));
			  vo.setDbday(rs.getString(4));
			  vo.setHit(rs.getInt(5));
			  vo.setGroup_tab(rs.getInt(6));
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
  public int replyBoardTotalPage()
  {
	  int total=0;
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT CEIL(COUNT(*)/10.0) FROM project_replyBoard";
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
  // pr_no_seq
  // 묻기 (새글)
  /*
   *    NO         NOT NULL NUMBER   == seq      
		ID                  VARCHAR2(20)   
		NAME       NOT NULL VARCHAR2(51)   
		SUBJECT    NOT NULL VARCHAR2(1000) 
		CONTENT    NOT NULL CLOB           
		REGDATE             DATE        def    
		HIT                 NUMBER      def 
		GROUP_ID            NUMBER         
		GROUP_STEP          NUMBER      def   
		GROUP_TAB           NUMBER      def   
		ISREPLY             NUMBER      def
   */
  public void replyBoardInsert(ReplyBoardVO vo)
  {
	  try
	  {
		  conn=db.getConnection();
		  String sql="INSERT INTO project_replyboard(no,id,name,subject,content,group_id) "
				    +"VALUES(pr_no_seq.nextval,?,?,?,?,"
				    +"(SELECT NVL(MAX(group_id)+1,1) FROM project_replyboard))";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1, vo.getId());
		  ps.setString(2, vo.getName());
		  ps.setString(3,vo.getSubject());
		  ps.setString(4, vo.getContent());
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
  // 삭제 
  public void replyBoardDelete(int no)
  {
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT group_id,isreply "
				    +"FROM project_replyboard "
				    +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  int gi=rs.getInt(1);
		  int isreply=rs.getInt(2);
		  rs.close();
		  
		  if(isreply==1)
		  {
			  sql="DELETE FROM project_replyboard "
			     +"WHERE group_id=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, gi);
			  ps.executeUpdate();
		  }
		  else
		  {
			  sql="DELETE FROM project_replyboard "
					     +"WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
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
  // 내용 
  public ReplyBoardVO replyBoardDetailData(int no)
  {
	  ReplyBoardVO vo=new ReplyBoardVO();
	  try
	  {
		  conn=db.getConnection();
		  String sql="UPDATE project_replyboard SET "
				    +"hit=hit+1 "
				    +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ps.executeUpdate();
		  
		  sql="SELECT no,name,id,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit "
		     +"FROM project_replyboard "
		     +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  vo.setNo(rs.getInt(1));
		  vo.setName(rs.getString(2));
		  vo.setId(rs.getString(3));
		  vo.setSubject(rs.getString(4));
		  vo.setContent(rs.getString(5));
		  vo.setDbday(rs.getString(6));
		  vo.setHit(rs.getInt(7));
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
  // 수정 
  public ReplyBoardVO replyBoardUpdatelData(int no)
  {
	  ReplyBoardVO vo=new ReplyBoardVO();
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT no,name,id,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit "
		     +"FROM project_replyboard "
		     +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  vo.setNo(rs.getInt(1));
		  vo.setName(rs.getString(2));
		  vo.setId(rs.getString(3));
		  vo.setSubject(rs.getString(4));
		  vo.setContent(rs.getString(5));
		  vo.setDbday(rs.getString(6));
		  vo.setHit(rs.getInt(7));
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
  public void replyBoardUpdate(ReplyBoardVO vo)
  {
	  try
	  {
		  conn=db.getConnection();
		  String sql="UPDATE project_replyboard SET "
				    +"subject=? , content=? "
				    +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1, vo.getSubject());
		  ps.setString(2, vo.getContent());
		  ps.setInt(3, vo.getNo());
		  
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
  // 관리자 
  // 목록 
  public List<ReplyBoardVO> replyBoardAdminListData(int page)
  {
	  List<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,isreply,group_step,num "
				    +"FROM (SELECT no,subject,name,regdate,hit,isreply,group_step,rownum as num "
				    +"FROM (SELECT no,subject,name,regdate,hit,isreply,group_step "
				    +"FROM project_replyboard ORDER BY group_id DESC)) "
				    +"WHERE num BETWEEN ? AND ? "
				    +"AND group_step=0";
		  ps=conn.prepareStatement(sql);
		  int rowSize=10;
		  int start=(rowSize*page)-(rowSize-1);
		  int end=rowSize*page;
		  ps.setInt(1, start);
		  ps.setInt(2, end);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  ReplyBoardVO vo=new ReplyBoardVO();
			  vo.setNo(rs.getInt(1));
			  vo.setSubject(rs.getString(2));
			  vo.setName(rs.getString(3));
			  vo.setDbday(rs.getString(4));
			  vo.setHit(rs.getInt(5));
			  vo.setIsreply(rs.getInt(6));
			  list.add(vo);
		  }
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
  public int replyBoardAdminTotalPage()
  {
	  int total=0;
	  try
	  {
		  conn=db.getConnection();
		  String sql="SELECT CEIL(COUNT(*)/10.0) "
				    +"FROM project_replyboard "
				    +"WHERE group_step=0";
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
  // 답변 (새글)
  // 삭제
  // 수정 
}







