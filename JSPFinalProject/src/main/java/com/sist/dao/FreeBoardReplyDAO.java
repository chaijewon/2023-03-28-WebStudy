package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;
public class FreeBoardReplyDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateDataBase db=new CreateDataBase();
   private static FreeBoardReplyDAO dao;
   
   // 싱글턴 
   public static FreeBoardReplyDAO newInstance()
   {
	   if(dao==null)
		   dao=new FreeBoardReplyDAO();
	   return dao;
   }
   // 목록 출력 bno:게시물번호 
   public List<FreeBoardReplyVO> replyListData(int bno)
   {
	   List<FreeBoardReplyVO> list=
			   new ArrayList<FreeBoardReplyVO>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT no,bno,id,name,msg,TO_CHAR(regdate,'yyyy-MM-dd HH24:MI:SS'),"
				     +"group_tab "
				     +"FROM project_freeboard_reply "
				     +"WHERE bno=? "
				     +"ORDER BY group_id DESC,group_step ASC";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, bno);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FreeBoardReplyVO vo=new FreeBoardReplyVO();
			   vo.setNo(rs.getInt(1));
			   vo.setBno(rs.getInt(2));
			   vo.setId(rs.getString(3));
			   vo.setName(rs.getString(4));
			   vo.setMsg(rs.getString(5));
			   vo.setDbday(rs.getString(6));
			   vo.setGroup_tab(rs.getInt(7));
			   
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
   // 수정 
   // 삭제 => 트랜잭션 적용 (일괄처리) => commit() , catch => rollback()
   // 댓글 입력 
   /*
    *    no NUMBER,
   bno NUMBER,
   id VARCHAR2(20),
   name VARCHAR2(51) CONSTRAINT pfr_name_nn NOT NULL,
   msg CLOB CONSTRAINT pfr_msg_nn NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   group_id NUMBER,
   group_step NUMBER DEFAULT 0,
   group_tab NUMBER DEFAULT 0,
   root NUMBER DEFAULT 0,
   depth NUMBER DEFAULT 0,
   CONSTRAINT pfr_no_pk PRIMARY KEY(no),
   CONSTRAINT pfr_no_fk FOREIGN KEY(bno)
   REFERENCES project_freeboard(no),
   CONSTRAINT pfr_id_fk FOREIGN KEY(id)
   REFERENCES project_member(id)
);
CREATE SEQUENCE pfr_no_seq
    */
   public void replyInsert(FreeBoardReplyVO vo)
   {
	   try
	   {
		   conn=db.getConnection();
		   String sql="INSERT INTO project_freeboard_reply(no,bno,id,name,msg,group_id) "
				     +"VALUES(pfr_no_seq.nextval,?,?,?,?,"
				     +"(SELECT NVL(MAX(group_id)+1,1) FROM project_freeboard_reply))";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getBno());
		   ps.setString(2, vo.getId());
		   ps.setString(3, vo.getName());
		   ps.setString(4, vo.getMsg());
		   ps.executeUpdate();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, null);
	   }
   }
   // 대댓글 입력 => 트랜잭션 적용 (일괄처리) => commit() , catch => rollback()
   
}






