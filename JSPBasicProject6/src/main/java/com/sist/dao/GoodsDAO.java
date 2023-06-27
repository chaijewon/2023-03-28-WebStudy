package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;

public class GoodsDAO {
	// 연결 객체 얻기 
		private Connection conn;
		// SQL송수신 
		private PreparedStatement ps;
		// 싱글턴
		private static GoodsDAO dao;
		// 출력 갯수
		private final int ROW_SIZE=20;
		// Pool영역에서 Connection객체를 얻어 온다 
		public void getConnection()
		{
			// Connection 객체 주소를 => 메모리에 저장 
			// 저장 공간 => POOL 영역 (디렉토리형식으로 저장) => JNDI
			// 루트 => 저장 공간 
			// java://env/comp => C드라이버 => jdbc/oracle
			
		   try
		   {
			  // 1. 탐색기를 연다 
			  Context init=new InitialContext();
			  // 2. C드라이버 연결 
			  Context cdriver=(Context)init.lookup("java://comp/env");
			  // lookup => 문자열(key) => 객체 찾기 (RMI) => Socket
			  // 3. Connection 객체 찾기 
			  DataSource ds=(DataSource)cdriver.lookup("jdbc/oracle");
			  // 4. Connection주소를 연결
			  conn=ds.getConnection();
			  // 282page 
			  // => 오라클 연결 시간을 줄인다 
			  // => Connection 객체가 일정하게 생성 => 관리 
		   }catch(Exception ex) 
		   {
			   ex.printStackTrace();
		   }
			
		}
		// Connection객체 사용후에 반환 
		public void disConnection()
		{
			try
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {}
		}
		
		public static GoodsDAO newInstance()
		{
			if(dao==null)
				dao=new GoodsDAO();
			return dao;
		}
		// 로그인
		public String isLogin(String id,String pwd)
		{
			String result="";
			try
			{
				getConnection();
				//1. id존재여부 확인
				String sql="SELECT COUNT(*) FROM jspMember "
						  +"WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int count=rs.getInt(1);
				rs.close();
				//2. 비밀번호 확인 
				if(count==0)
				{
					result="NOID";
				}
				else //ID가 존재 
				{
					sql="SELECT pwd,name "
					   +"FROM jspMember "
					   +"WHERE id=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, id);
					rs=ps.executeQuery();
					rs.next();
					String db_pwd=rs.getString(1);
					String name=rs.getString(2);
					rs.close();
					
					if(db_pwd.equals(pwd))//로그인 
					{
						result=name;
					}
					else
					{
						result="NOPWD";
					}
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			return result;
		}
		// 목록
		public List<GoodsBean> goodsListData(int page)
		{
			List<GoodsBean> list=
					new ArrayList<GoodsBean>();
			try
			{
				getConnection();
				String sql="SELECT no,goods_name,goods_poster,num "
						  +"FROM (SELECT no,goods_name,goods_poster,rownum as num "
						  +"FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/no,goods_name,goods_poster "
						  +"FROM goods_all)) "
						  +"WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				int rowSize=12;
				int start=(rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				ps.setInt(1, start);
				ps.setInt(2, end);
				// 실행
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					GoodsBean vo=new GoodsBean();
					vo.setNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setPoster(rs.getString(3));
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
		public int goodsTotalPage()
		{
			int total=0;
			try
			{
				getConnection();
				String sql="SELECT CEIL(COUNT(*)/12.0) FROM goods_all";
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
				disConnection();// 반환
			}
			return total;
		}
		// 상세보기 
		// 장바구니 => session 
		// 구매 (X)
}
