package com.sist.main;
import java.util.*;

import com.sist.dbconn.CreateDataBase;

import java.sql.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        CreateDataBase db=new CreateDataBase();
        Connection conn=null;
        PreparedStatement ps=null;
        try
        {
        	conn=db.getConnection();
        	String sql="SELECT empno,ename,job FROM emp";
        	ps=conn.prepareStatement(sql);
        	ResultSet rs=ps.executeQuery();
        	while(rs.next())
        	{
        		System.out.println(rs.getInt(1)+" "
        				+rs.getString(2)+" "
        				+rs.getString(3));
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
	}

}
