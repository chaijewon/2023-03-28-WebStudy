package com.sist.dao;
import java.util.*;

import oracle.jdbc.OracleTypes;

import java.sql.*;
public class StudentDAO {
   // 연결 
   private Connection conn;
   // 함수(프로시저) 호출 
   private CallableStatement cs;
   // URL
   private final String URL="jdbc:oracle:thin:@localhost:1521:xe";
   // Singleton
   private static StudentDAO dao;
   // Driver등록 
   public StudentDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex) {}
   }
   // 싱글턴 사용 
   public static StudentDAO newInstance()
   {
	   if(dao==null)
		   dao=new StudentDAO();
	   return dao;
   }
   // 데이터 추가 
   /*
    *   CREATE OR REPLACE PROCEDURE studentInsert(
		   pName student.name%TYPE,
		   pKor student.kor%TYPE,
		   pEng student.eng%TYPE,
		   pMath student.math%TYPE
		)
		IS
		BEGIN
		   INSERT INTO student VALUES(
		     (SELECT NVL(MAX(hakbun)+1,1) FROM student),
		     pName,pKor,pEng,pMath
		   );
		   COMMIT;
		END;
		/
    */
   public void studentInsert(StudentVO vo)
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   // 함수 호출만 하면 된다 
		   String sql="{CALL studentInsert(?,?,?,?)}";
		   cs=conn.prepareCall(sql);//ERP => 메뉴얼 
		   // ?에 값을 채운 다음 실행 
		   cs.setString(1, vo.getName());
		   cs.setInt(2,vo.getKor());
		   cs.setInt(3,vo.getEng());
		   cs.setInt(4,vo.getMath());
		   // 실행 요청 
		   cs.executeQuery(); // cs.executeQuery()로 실행한다 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   try
		   {
			   if(cs!=null) cs.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
   }
   // 데이터 수정 
   /*
    *   CREATE OR REPLACE PROCEDURE studentUpdate(
		   pHakbun student.hakbun%TYPE,
		   pName student.name%TYPE,
		   pKor student.kor%TYPE,
		   pEng student.eng%TYPE,
		   pMath student.math%TYPE
		)
		IS
		BEGIN
		  UPDATE student SET
		  name=pName,kor=pKor,eng=pEng
		  WHERE hakbun=pHakbun;
		  COMMIT;
		END;
		/
    */
   public void studentUpdate(StudentVO vo)
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   String sql="{CALL studentUpdate(?,?,?,?,?)}";
		   cs=conn.prepareCall(sql);
		   //?에 값을 채운다 
		   cs.setInt(1, vo.getHakbun());
		   cs.setString(2, vo.getName());
		   cs.setInt(3,vo.getKor());
		   cs.setInt(4,vo.getEng());
		   cs.setInt(5,vo.getMath());
		   // 실행 요청 
		   cs.executeQuery();
		   // 모든 테이블의 데이터를 페이징
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   try
		   {
			   if(cs!=null) cs.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
   }
   // 데이터 삭제
   /*
    *   CREATE OR REPLACE PROCEDURE studentDelete(
		    pHakbun student.hakbun%TYPE
		)
		IS
		BEGIN 
		  DELETE FROM student
		  WHERE hakbun=pHakbun;
		  COMMIT;
		END;
		/
    */
   public void studentDelete(int hakbun)
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   String sql="{CALL studentDelete(?)}";
		   cs=conn.prepareCall(sql);
		   // ?에 값을 채운다 
		   cs.setInt(1, hakbun);
		   // 실행 요청 
		   cs.executeQuery();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   try
		   {
			   if(cs!=null) cs.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
   }
   // 데이터 상세
   /*
    *   CREATE OR REPLACE PROCEDURE studentDetailData(
		   pHakbun IN student.hakbun%TYPE,
		   pName OUT student.name%TYPE,
		   pKor OUT student.kor%TYPE,
		   pEng OUT student.eng%TYPE,
		   pMath OUT student.math%TYPE
		)
		IS
		BEGIN
		   SELECT name,kor,eng,math INTO pName,pKor,pEng,pMath
		   FROM student
		   WHERE hakbun=pHakbun;
		END;
		/
    */
   public StudentVO studentDetail(int hakbun)
   {
	   StudentVO vo=new StudentVO();
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   String sql="{CALL studentDetailData(?,?,?,?,?)}";
		   cs=conn.prepareCall(sql);
		   //?에 값을 채운다 
		   cs.setInt(1, hakbun);
		   // OUT 변수일 경우에 => 메모리에 저장한다
		   cs.registerOutParameter(2, OracleTypes.VARCHAR);
		   cs.registerOutParameter(3, OracleTypes.INTEGER);
		   cs.registerOutParameter(4, OracleTypes.INTEGER);
		   cs.registerOutParameter(5, OracleTypes.INTEGER);
		   // 실행 요청 
		   cs.executeQuery();
		   vo.setName(cs.getString(2));
		   vo.setKor(cs.getInt(3));
		   vo.setEng(cs.getInt(4));
		   vo.setMath(cs.getInt(5));
		   vo.setHakbun(hakbun);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   try
		   {
			   if(cs!=null) cs.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   return vo;
   }
   // 데이터 전체 
   /*
    *   CREATE OR REPLACE PROCEDURE studentListData(
		   pResult OUT SYS_REFCURSOR 
		)
		IS 
		BEGIN
		   OPEN pResult FOR
		     SELECT * FROM student;
		END;
		/
    */
   public List<StudentVO> studentListData()
   {
	   List<StudentVO> list=new ArrayList<StudentVO>();
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   String sql="{CALL studentListData(?)}";
		   cs=conn.prepareCall(sql);
		   //?에 값을 채운다 
		   // registerOutParameter => 저장해 주는 공간 
		   cs.registerOutParameter(1, OracleTypes.CURSOR);
		   /*
		    *    NUMBER => INTEGER/DOUBLE 
		    *    VARCHAR2,CHAR => VARCHAR 
		    *    CURSOR => CURSOR 
		    */
		   cs.executeQuery();
		   // 결과값을 받는다 
		   ResultSet rs=(ResultSet)cs.getObject(1);
		   // Cursor => ResultSet변환 
		   while(rs.next())
		   {
			   StudentVO vo=new StudentVO();
			   vo.setHakbun(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setKor(rs.getInt(3));
			   vo.setEng(rs.getInt(4));
			   vo.setMath(rs.getInt(5));
			   vo.setTotal(rs.getInt(6));
			   vo.setAvg(rs.getDouble(7));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   try
		   {
			   if(cs!=null) cs.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   return list;
   }
   // 중복 코드가 있는 여부 => 공통 모듈 (메소드화 처리 , 클래스화)
}
