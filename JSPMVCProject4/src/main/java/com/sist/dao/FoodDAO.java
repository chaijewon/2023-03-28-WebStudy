package com.sist.dao;
import java.sql.*;
import java.util.*;

/*
 *   1. 드라이버 등록
 *      ------ 오라클 연결하는 라이브러리 (ojdbc8.jar)
 *      OracleDriver => 메모리할당 
 *   2. 오라클 연결 
 *      Connection 
 *   3. SQL문장을 전송 
 *      PreparedStatement
 *   4. SQL문장 실행 요청 
 *      = executeUpdate() => INSERT , UPDATE , DELETE
 *        --------------- COMMIT (AutoCommit)
 *      = executeQuery() => SELECT
 *        -------------- 결과값을 가지고 온다 
 *                       -----
 *                       ResultSet
 *        ResultSet 
 *         String sql="SELECT id,name,sex,age ";
 *        ----------------------------
 *           id     name   sex   age 
 *        ----------------------------
 *          aaa     홍길동   남자   20 | first() => next()
 *                                    위치변경     위치변경후 데이터 읽기
 *      getString(1) getString(2) getString(3) getInt(4)
 *      getString("id") => mybatis 
 *        ----------------------------
 *          bbb     심청이   여자   23
 *      getString(1) getString(2) getString(3) getInt(4)
 *        ----------------------------
 *          ccc     박문수   남자   27 | last() => previous()
 *      getString(1) getString(2) getString(3) getInt(4)
 *        ----------------------------
 *         | 커서위치 
 *   5. 닫기 
 *      생성 반대로 닫는다 
 *      rs.close() , ps.close(), conn.close() 
 *  -------------------------- 오라클 연결 (Servlet => JSP)
 *   
 */
public class FoodDAO {
   //기능 => INSERT => 데이터수집 (파일)
   private Connection conn;// 오라클 연결 객체 (데이터베이스 연결) 
   private PreparedStatement ps;// SQL문장 전송 / 결과값 읽기
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   //  mySQL => jdbc:mysql://localhost/mydb
   private static FoodDAO dao; // 싱글턴 패턴 
   // DAO객체를 한개만 사용이 가능하게 만든다 
   // 드라이버 설치 => 소프트웨어 (메모리 할당 요청) Class.forName()
   // 클래스의 정보를 전송 
   // 드라이버 설치는 1번만 수행 
   public FoodDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex){}
   }
   // 오라클 연결 
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   // => 오라클 전송 : conn hr/happy
	   }catch(Exception ex){}
   }
   // 오라클 연결 종료 
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
		   // => 오라클 전송 : exit
	   }catch(Exception ex){}
   }
   // DAO객체를 1개만 생성해서 사용 => 메모리 누수현상을 방지 (싱글턴 패턴)
   // 싱글턴 / 팩토리 => 면접 (스프링:패턴 8개) 
   public static FoodDAO newInstance()
   {
	   // newInstance() , getInstance() => 싱글턴 
	   if(dao==null)
		   dao=new FoodDAO();
	   return dao;
   }
   // ============= 기본 셋팅 (모든 DAO)
   // 기능 
   // 1. 데이터 수집 (INSERT)
   /*
    *   Statement => SQL=> 생성과 동시에 데이터 추가 
    *             "'"+name+"','"+sex+"','"+...
    *   PreparedStatement => 미리 SQL문장을 만들고 나중에 값을 채운다 
    *     => default 
    *   CallableStatement => Procedure호출 
    */
   public void foodCategoryInsert(CategoryVO vo)
   {
	   try
	   {
		   //1. 연결 
		   getConnection();
		   //2. SQL문장 생성 
		   String sql="INSERT INTO food_category VALUES("
				     +"fc_cno_seq.nextval,?,?,?,?)";
		   /*
		    *    "'"+vo.getTitle()+"','"
		    *    
		    *    INSERT ~ VALUES('홍길동','서울'
		    */
		   //3. SQL문장을 오라클 전송 
		   ps=conn.prepareStatement(sql);
		   // 3-1 => ?에 값을 채운다 
		   ps.setString(1, vo.getTitle());// "'"+vo.getTitle()+"'"
		   ps.setString(2, vo.getSubject());
		   ps.setString(3, vo.getPoster());
		   ps.setString(4, vo.getLink());
		   // 단점 => 번호가 잘못되면 오류 발생 , 데이터형이 다르면 오류 
		   // IN,OUT ~
		   //4. SQL문장을 실행 명령 => SQL문장을 작성하고 => 
		   ps.executeUpdate();
	   }catch(Exception ex) 
	   {
		   ex.printStackTrace();// 에러 확인 
	   }
	   finally
	   {
		   disConnection();// 오라클 연결 해제 => 무조건 
	   }
   }
   //1-1 => 실제 맛집 정보 저장 
   /*
    *  fno NUMBER,
	   cno NUMBER,
	   name VARCHAR2(100) CONSTRAINT fh_name_nn NOT NULL,
	   score NUMBER(2,1),
	   address VARCHAR2(300) CONSTRAINT fh_addr_nn NOT NULL,
	   phone VARCHAR2(20) CONSTRAINT fh_phone_nn NOT NULL,
	   type VARCHAR2(30) CONSTRAINT fh_type_nn NOT NULL,
	   price VARCHAR2(30),
	   parking VARCHAR2(30),
	   time VARCHAR2(20),
	   menu CLOB,
	   good NUMBER,
	   soso NUMBER,
	   bad NUMBER,
	   poster VARCHAR2(4000) CONSTRAINT fh_poster_nn NOT NULL,
    */
   public void foodDataInsert(FoodVO vo)
   {
	   try
	   {
		   //1. 오라클 연결
		   getConnection();
		   //2. SQL문장 제작 
		   String sql="INSERT INTO food_house VALUES("
				     +"fh_fno_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   //3. 오라클 전송 
		   ps=conn.prepareStatement(sql);
		   //4. ?에 값을 채운다 
		   ps.setInt(1, vo.getCno());
		   ps.setString(2, vo.getName());
		   ps.setDouble(3, vo.getScore());
		   ps.setString(4, vo.getAddress());
		   ps.setString(5, vo.getPhone());
		   ps.setString(6, vo.getType());
		   ps.setString(7, vo.getPrice());
		   ps.setString(8, vo.getParking());
		   ps.setString(9, vo.getTime());
		   ps.setString(10, vo.getMenu());
		   ps.setInt(11, vo.getGood());
		   ps.setInt(12, vo.getSoso());
		   ps.setInt(13, vo.getBad());
		   ps.setString(14, vo.getPoster());
		   
		   // 실행 요청 
		   ps.executeUpdate(); // commit() 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();//오류확인
	   }
	   finally
	   {
		   disConnection();//오라클 닫기
	   }
   }
   // 2. SELECT => 전체 데이터 읽기 => 30개 (한개당 => CategoryVO)
   // => Collection,배열 => 브라우저로 30개를 전송
   // 브라우저 <==> 오라클 (X) 
   // 브라우저 <==> 자바  <==> 오라클 => Spring Cloud 
   // Collection , 메소드 제작 
   public List<CategoryVO> foodCategoryData()
   {
	   List<CategoryVO> list=new ArrayList<CategoryVO>();
	   try
	   {
		   //1. 오라클 연결
		   getConnection();
		   //2. SQL문장 
		   String sql="SELECT cno,title,subject,poster,link "
				     +"FROM food_category";
		   //3. 오라클 전송
		   ps=conn.prepareStatement(sql);
		   //4. 실행후 결과값 받기
		   ResultSet rs=ps.executeQuery();
		   // rs에 있는 데이터를 list에 저장 
		   while(rs.next())
		   {
			   CategoryVO vo=new CategoryVO();
			   vo.setCno(rs.getInt(1));
			   vo.setTitle(rs.getString(2));
			   vo.setSubject(rs.getString(3));
			   String poster=rs.getString(4);
			   poster=poster.replace("#", "&");
			   vo.setPoster(poster);
			   vo.setLink("https://www.mangoplate.com"+rs.getString(5));
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // 오라클 닫기 
		   disConnection();
	   }
	   return list;
   }
   
   // 카테고리에 해당되는 데이터를 읽는다 
   public CategoryVO foodCategoryInfoData(int cno)
   {
	   CategoryVO vo=new CategoryVO();
	   try
	   {
		   getConnection();
		   String sql="SELECT title,subject "
				     +"FROM food_category "
				     +"WHERE cno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, cno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setTitle(rs.getString(1));
		   vo.setSubject(rs.getString(2));
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
   // 카테고리에 해당되는 맛집 읽기
   public List<FoodVO> foodCategoryListData(int cno)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT fno,name,score,poster,address,phone,type "
				     +"FROM food_house "
				     +"WHERE cno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1,cno);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setScore(rs.getDouble(3));
			   vo.setPhone(rs.getString(6));
			   vo.setType(rs.getString(7));
			   String poster=rs.getString(4);
			   poster=poster.substring(0,poster.indexOf("^"));
			   poster=poster.replace("#", "&");
			   vo.setPoster(poster);
			   String address=rs.getString(5);
			   address=address.substring(0,address.indexOf("지번"));
			   vo.setAddress(address);
			   
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
   // 3. 상세보기 => WHERE 
   public FoodVO foodDetailData(int fno)
   {
	   FoodVO vo=new FoodVO();
	   try
	   {
		   getConnection();
		   String sql="SELECT fno,cno,name,score,poster,address,"
				     +"type,parking,time,menu,phone,price "
				     +"FROM food_house "
				     +"WHERE fno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, fno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setFno(rs.getInt(1));
		   vo.setCno(rs.getInt(2));
		   vo.setName(rs.getString(3));
		   vo.setScore(rs.getDouble(4));
		   vo.setPoster(rs.getString(5));
		   vo.setAddress(rs.getString(6));
		   vo.setType(rs.getString(7));
		   vo.setParking(rs.getString(8));
		   vo.setTime(rs.getString(9));
		   vo.setMenu(rs.getString(10));
		   vo.setPhone(rs.getString(11));
		   vo.setPrice(rs.getString(12));
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
   
   
   
}












