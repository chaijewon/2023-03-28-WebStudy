package com.sist.dao;
// DBCP 
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class ReplyBoardDAO {
    // 연결 객체 
	private Connection conn;
	// SQL 전송 = 결과값 받기
	private PreparedStatement ps;
	// 싱글턴 
	private static ReplyBoardDAO dao;
	
	public static ReplyBoardDAO newInstance()
	{
		if(dao==null)
			dao=new ReplyBoardDAO();
		return dao;
	}
	
	// 주소값을 얻기 
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	// 반환 
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// 기능 수행 
	// 1. 목록
	public List<ReplyBoardVO> boardListData(int page)
	{
		List<ReplyBoardVO> list=
				new ArrayList<ReplyBoardVO>();
		try
		{
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num "
					  +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
					  +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
					  +"FROM replyBoard ORDER BY group_id DESC,group_step ASC)) "
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
			disConnection();
		}
		return list;
	}
	// 1-1. 총페이지
	public int boardRowCount()
	{
		int count=0;
		try
		{
			getConnection();
			String sql="SELECT COUNT(*) FROM replyBoard";
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
			disConnection();
		}
		return count;
	}
	// 2. 상세보기 
	public ReplyBoardVO boardDetailData(int no)
	{
		ReplyBoardVO vo=new ReplyBoardVO();
		try
		{
			getConnection();
			String sql="UPDATE replyBoard SET "
					  +"hit=hit+1 "
					  +"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			/// 조회수 증가 
			
			sql="SELECT no,name,subject,content,regdate,hit "
			   +"FROM replyBoard "
			   +"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			rs.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	// 3. 추가
	public void boardInsert(ReplyBoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) "
					  +"VALUES(rb_no_seq.nextval,?,?,?,?,"
					  +"(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			// 실행 
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 4. 수정 
	public ReplyBoardVO boardUpdateData(int no)
	{
		ReplyBoardVO vo=new ReplyBoardVO();
		try
		{
			getConnection();
			String sql="SELECT no,name,subject,content,regdate,hit "
			   +"FROM replyBoard "
			   +"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			rs.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	// 5. 삭제
	// 6. 답변 
	public void replyInsert(int root,ReplyBoardVO vo)
	{
		// 상위 게시물의 정보 => group_id, group_step , group_tab
		// group_step을 증가 
		// insert
		// depth 증가 
		/*
		 *                 gi gs gt
		 *     AAAAAAAA     3  0  0
		 *       =>KKKKK    3  1  1
		 *       =>DDDDDD   3  2  1
		 *       
		 *       =>BBBBBB   3  3  1
		 *        =>CCCCCC  3  4  2
		 *       
		 *       
		 *       
		 */
		try
		{
			getConnection();
			String sql="SELECT group_id,group_step,group_tab "
					  +"FROM replyBoard "
					  +"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, root);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);
			rs.close();
			
			sql="UPDATE replyBoard SET "
			   +"group_step=group_step+1 "
			   +"WHERE group_id=? AND group_step>?";
			// 답변형의 핵신 SQL
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			
			// 추가 
			sql="INSERT INTO replyBoard VALUES(rb_no_seq.nextval,?,"
			   +"?,?,?,SYSDATE,0,?,?,?,?,0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, root);
			ps.executeUpdate();
			
			// depth 증가 
			sql="UPDATE replyBoard SET "
			   +"depth=depth+1 "
			   +"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, root);
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 7. 검색 
}
