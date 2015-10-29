package org.zucc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.zucc.entity.Dvd;

import java.sql.PreparedStatement;

import javax.print.DocFlavor.INPUT_STREAM;

public class DvdDao {
	Connection conn = null;
	//Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement psmt = null;
//	Scanner input=new Scanner(System.in);
	public void myClose(){
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}      
	}
	
	public DvdDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Œ¥’“µΩ«˝∂Ø£°");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addDvd(Dvd dvd) {	
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dvd", "root", "root");
			String sql = "insert into dvd(name) values(?)";
			psmt =  conn.prepareStatement(sql);
			psmt.setString(1, dvd.getName());
			System.out.println(psmt);
//			String sql = "insert into dvd(name) values ('" + dvd.getName()
//					+ "')";
//			System.out.println(sql);
			psmt.execute();
		} catch (SQLException e) {
			System.out.println("≤Â»Î ß∞‹");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myClose();
		}
	}
	
	public void updateDvd(Dvd dvd) {	
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dvd", "root", "root");
			//stmt = conn.createStatement();
			String sql = "update dvd set name =?,state=?,borrowdate=?,returndate=?,borrowcount=? where id =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,dvd.getName());
			psmt.setInt(2, dvd.getState());
			java.sql.Date sqlBorrow = dvd.getBorrowDate()==null?null:new java.sql.Date(dvd.getBorrowDate().getTime());
			psmt.setDate(3, sqlBorrow);
			java.sql.Date sqlReturn = dvd.getReturnDate()==null?null:new java.sql.Date(dvd.getReturnDate().getTime());
			psmt.setDate(4,sqlReturn );
			psmt.setInt(5, dvd.getBorrowCount());
			psmt.setInt(6, dvd.getId());
			System.out.println(psmt);
//			StringBuilder sql = new StringBuilder("update dvd set name='");
//			sql.append(dvd.getName());
//			sql.append("',state=");
//			sql.append(dvd.getState());
//			sql.append(",borrowdate=");
//			java.sql.Date sqlborrow = null;
//			if (dvd.getBorrowDate() != null) {
//				sql.append("'");
//				sqlborrow = new java.sql.Date (dvd.getBorrowDate().getTime());	
//			}
//			sql.append(sqlborrow);
//			if (dvd.getBorrowDate() != null) {
//				sql.append("'");
//				}
//			sql.append(",returndate=");
//			java.sql.Date sqlreturn =null;
//			if (dvd.getReturnDate()!=null) {
//				sql.append("'");
//				sqlreturn = new java.sql.Date(dvd.getReturnDate().getTime());		
//			}
//			sql.append(sqlreturn);
//			if (dvd.getReturnDate()!=null) {
//				sql.append("'");
//			}
//			sql.append(" where id = ");
//			sql.append(dvd.getId());
//			System.out.println(sql);
			psmt.execute();
		} catch (SQLException e) {
			System.out.println("∏¸–¬ ß∞‹");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myClose();
		}
	}
	
	public Dvd findById(int id) {	
		Dvd dvd = new Dvd();
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dvd", "root", "root");
			String sql = "select * from dvd where id =?";	
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if(rs.next()){
				dvd.setId(rs.getInt("id"));
				dvd.setName(rs.getString("name"));
				dvd.setBorrowDate(rs.getDate("borrowdate"));
				dvd.setReturnDate(rs.getDate("returndate"));
				dvd.setState(rs.getInt("state"));
				dvd.setBorrowCount(rs.getInt("borrowcount"));			}	
		} catch (SQLException e) {
			System.out.println("—∞’“ ß∞‹");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myClose();       
		}
		 return dvd;
	}

	public List<Dvd> findAll() {
		List<Dvd> list=new ArrayList<Dvd>();
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dvd", "root", "root");	
			String sql = "select * from dvd";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Dvd dvd = new Dvd();
				dvd.setId(rs.getInt("id"));
				dvd.setName(rs.getString("name"));
				dvd.setBorrowDate(rs.getDate("borrowdate"));
				dvd.setReturnDate(rs.getDate("returndate"));
				dvd.setState(rs.getInt("state"));
				dvd.setBorrowCount(rs.getInt("borrowcount"));	
				list.add(dvd);
				
					  System.out.println(dvd);
			}	
		} catch (SQLException e) {
			System.out.println("≤È—Ø ß∞‹");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myClose();			
		}
		return list;
	}
	
	public void deleteDvd(int id) {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dvd", "root", "root");
			String sql = "delete from dvd where id =?";			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.execute();
		} catch (SQLException e) {
			System.out.println("…æ≥˝ ß∞‹");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			myClose();
		}	
	}
	
	public List<Dvd> findByName(String name) {
		List<Dvd> list=new ArrayList<Dvd>();
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dvd", "root", "root");		
			String sql = "select * from dvd where name =?";
			psmt =  conn.prepareStatement(sql);
			psmt.setString(1, name);
			System.out.println(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Dvd dvd = new Dvd();
				dvd.setId(rs.getInt("id"));
				dvd.setName(rs.getString("name"));
				dvd.setBorrowDate(rs.getDate("borrowdate"));
				dvd.setReturnDate(rs.getDate("returndate"));
				dvd.setState(rs.getInt("state"));
				list.add(dvd);
			}	
		} catch (SQLException e) {
			System.out.println("≤È’“ ß∞‹");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myClose();			
		}
		return list;
	}	
}
