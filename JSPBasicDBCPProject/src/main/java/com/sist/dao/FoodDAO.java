package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;// 데이터베이스의 정보 = DataSource
import javax.naming.*;//jdbc/oracle이란 이름의 객체주소 찾기 Context 
public class FoodDAO {
   // 연결 객체 
   private Connection conn;
   // SQL 송수신 
   private PreparedStatement ps;
   // 싱글턴 객체
   private static FoodDAO dao;
   
   // 사용자 요청 => Connection 객체를 얻어온다 (미리 연결)
   /*
    *    XML은 대소문자를 구분, 태그를 반드시 열고 닫기를 한다 
    *    속성값은 반드시 ""를 사용한다 (React) => 태그는 HTML => 문법 XML
    *                                       ---------------------
    *                                         jsx
    *     <br> => <br/> 
    *    -----------------
    *    XML => 설정 파일 
    *           server.xml , web.xml  => tomcat에서 사용
    *           ----------------
    *           application.xml
    *           ---------------- Spring
    *           config.xml
    *           mapper.xml
    *           ---------------- myBatis
    *    <Context> => 프로젝트의 정보 
    *    <Resource
           driverClassName="oracle.jdbc.driver.OracleDriver"
           url="jdbc:oracle:thin:@localhost:1521:XE"
           username="hr"
           password="happy" => Connection생성시 필요 정보 
           -------------------------------------------------
           maxActive="10" : POOL의 Connection객체를 다 사용이 되면 추가
           maxIdle="8" : POOL안에 Connection 객체 저장 갯수 
           maxWait="-1" : Connection을 다 사용시에 
                          반환 대기 시간 (-무한)
           name="jdbc/oracle" : Connection객체를 찾기 위한 이름 (key)
                                lookup(key) => 객체 반환 
           auth="Container" : DBCP를 관리 => 톰캣 
           type="javax.sql.DataSource" => Connection주소를 얻은 경우에 
                                          DataSource는 데이터베이스 전체 연결 정보 
        />
    *    POOL : Connection 관리 영역 
    *    -----------------------------------------------
    *      객체주소                               사용여부 
    *    -----------------------------------------------
    *     conn=DriverManager.getConnection()     false => 객체얻기 => true => 반환 (false)
    *    -----------------------------------------------
    *     conn=DriverManager.getConnection()     false
    *    -----------------------------------------------
    *     conn=DriverManager.getConnection()     false
    *    -----------------------------------------------
    *     conn=DriverManager.getConnection()     false
    *    -----------------------------------------------
    *     conn=DriverManager.getConnection()     false
    *    -----------------------------------------------
    */
   public void getConnection()
   {
	   try
	   {
		   // JNDI연결 => 저장 공간 (가상 디렉토리) Java Naming Directory Interface
		   Context init=new InitialContext();
		   // POOL => java://comp/env 
		   Context cdriver=(Context)init.lookup("java://comp/env");
		   // 저장된 Connection => jdbc/oracle 
		   DataSource ds=(DataSource)cdriver.lookup("jdbc/oracle");
		   // Coonection 주소 저장 
		   conn=ds.getConnection();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // 사용이 종료 => 반환 => 재사용이 가능 
   public void disConnection()
   {
	   // connection을 close하면 반환 => commons-dbcp.jar
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   // 싱글턴 (한개의 객체만 사용) => Spring(기본)
   // 클래스 관리자 => 클래스(Component) => 관리(Container)
   public static FoodDAO newInstance()
   {
	   if(dao==null)
		   dao=new FoodDAO();
	   return dao; 
   }
   // 기능 (맛집 찾기) 
   public List<FoodBean> foodFindData(int page,String fd)
   {
	   List<FoodBean> list=
			   new ArrayList<FoodBean>();
	   try
	   {
		   // Connection주소 얻기
		   getConnection();
		   String sql="SELECT fno,name,poster,address,num "
				     +"FROM (SELECT fno,name,poster,address,rownum as num "
				     +"FROM (SELECT fno,name,poster,address "
				     +"FROM food_location "
				     +"WHERE address LIKE '%'||?||'%')) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, fd);
		   int rowSize=12;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   
		   // 실행
		   ResultSet rs=ps.executeQuery();
		   // 서울특별시 강서구 강서로 344 지번 서울시 강서구 내발산동 657-1
		   // 강서구 강서로 344 지번 서울시 강서구 내발산동 657-1
		   while(rs.next())
		   {
			   FoodBean vo=new FoodBean();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   String poster=rs.getString(3);
			   poster=poster.substring(0,poster.indexOf("^"));
			   poster=poster.replace("#", "&");
			   vo.setPoster(poster);
			   String addr=rs.getString(4);
			   String addr1=addr.substring(addr.indexOf(" "));
			   addr1=addr1.trim();
			   String addr2=addr1.substring(0,addr1.indexOf(" "));
			   vo.setAddress(addr2);
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();// 반환 
	   }
	   return list;
   }
   public int foodTotalpage(String addr)
   {
	   int total=0;
	   try
	   {
		   getConnection();
		   String sql="SELECT CEIL(COUNT(*)/12.0) "
				     +"FROM food_location "
				     +"WHERE address LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, addr);
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
		   disConnection();
	   }
	   return total;
   }
   
}
