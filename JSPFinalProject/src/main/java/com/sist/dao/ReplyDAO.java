package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;

import oracle.jdbc.OracleTypes;
/*
 *     try
 *     {
 *        getConnection()
 *        insert 
 *        update 
 *        insert 
 *        commit()
 *     }catch(Exception ex)
 *     {
 *        rollback()
 *        ex.printStackTrace();
 *     }
 *     finally
 *     {
 *        disConnection()
 *     }
 */
public class ReplyDAO {
  private Connection conn;
  private CallableStatement cs;
  private CreateDataBase db=new CreateDataBase();
  private static ReplyDAO dao;
  
  public static ReplyDAO newInstance()
  {
	  if(dao==null)
		  dao=new ReplyDAO();
	  return dao;
  }
  /*
   *   TYPE 
     1. 맛집
     2. 명소
     3. 자연
     4. 쇼핑
     5. GOODS ALL
     6. GOODS BEST
     7. GOODS NEW
     8. GOODS SPECIAL
   */
  // 데이터 읽기 
  /*
   *   CREATE OR REPLACE PROCEDURE replyListData(
		   pNo NUMBER,
		   pType NUMBER,
		   pResult OUT SYS_REFCURSOR
		)
		IS
		BEGIN
		   OPEN pResult FOR
		     SELECT no,type,cno,id,name,msg,regdate
		     FROM reply_all
		     WHERE type=pType AND cno=pNo;
		END;
		/
   */
  public List<ReplyVO> replyListData(int type,int cno)
  {
	  List<ReplyVO> list=new ArrayList<ReplyVO>();
	  try
	  {
		  conn=db.getConnection();
		  String sql="{CALL replyListData(?,?,?)}";
		  cs=conn.prepareCall(sql);
		  cs.setInt(1, cno);
		  cs.setInt(2,type);
		  cs.registerOutParameter(3, OracleTypes.CURSOR);
		  // 실행
		  cs.executeQuery();
		  ResultSet rs=(ResultSet)cs.getObject(3);
		  // no,type,cno,id,name,msg,regdate
		  while(rs.next())
		  {
			  ReplyVO vo=new ReplyVO();
			  vo.setNo(rs.getInt(1));
			  vo.setType(rs.getInt(2));
			  vo.setCno(rs.getInt(3));
			  vo.setId(rs.getString(4));
			  vo.setName(rs.getString(5));
			  vo.setMsg(rs.getString(6));
			  vo.setDbday(rs.getString(7));
			  list.add(vo);
		  }
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  db.disConnection(conn, cs);
	  }
	  return list;
  }
  // 댓글 추가 
  /*
   *   CREATE OR REPLACE PROCEDURE replyInsert(
		   pType reply_all.type%TYPE,
		   pCno reply_all.cno%TYPE,
		   pId reply_all.id%TYPE,
		   pName reply_all.name%TYPE,
		   pMsg reply_all.msg%TYPE
		)
		IS
		BEGIN
		   INSERT INTO reply_all VALUES(
		      ra_no_seq.nextval,
		      pType,pCno,pId,pName,pMsg,SYSDATE
		   );
		   COMMIT;
		END;
		/
   */
  public void replyInsert(ReplyVO vo)
  {
	  try
	  {
		  conn=db.getConnection();
		  String sql="{CALL replyInsert(?,?,?,?,?)}";
		  cs=conn.prepareCall(sql);
		  cs.setInt(1, vo.getType());
		  cs.setInt(2, vo.getCno());
		  cs.setString(3, vo.getId());
		  cs.setString(4, vo.getName());
		  cs.setString(5, vo.getMsg());
		  
		  cs.executeQuery();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  db.disConnection(conn, cs);
	  }
  }
  /*
   *   CREATE OR REPLACE PROCEDURE replyUpdate(
		     pNo reply_all.no%TYPE,
		     pMsg reply_all.msg%TYPE
		)
		IS
		BEGIN
		    UPDATE reply_all SET
		    msg=pMsg
		    WHERE no=pNo;
		    COMMIT;
		END;
		/
		-- 삭제
		CREATE OR REPLACE PROCEDURE replyDelete(
		    pNo reply_all.no%TYPE
		)
		IS
		BEGIN
		   DELETE FROM reply_all
		   WHERE no=pNo;
		   COMMIT;
		END;
		/
   */
  // 수정
  public void replyUpdate(int no,String msg)
  {
	  try
	  {
		  conn=db.getConnection();
		  String sql="{CALL replyUpdate(?,?)}";
		  cs=conn.prepareCall(sql);
		  cs.setInt(1, no);
		  cs.setString(2, msg);
		  cs.executeQuery();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  db.disConnection(conn, cs);
	  }
  }
  // 삭제
  public void replyDelete(int no)
  {
	  try
	  {
		  conn=db.getConnection();
		  String sql="{CALL replyDelete(?)}";
		  cs=conn.prepareCall(sql);
		  cs.setInt(1, no);
		  cs.executeQuery();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  db.disConnection(conn, cs);
	  }
  }
}





