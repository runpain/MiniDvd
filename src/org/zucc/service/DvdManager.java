package org.zucc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.zucc.dao.DvdDao;
import org.zucc.entity.Dvd;

public class DvdManager {

	Scanner input=new Scanner(System.in);
	DvdDao dvddao = new DvdDao();


	public void find(){
		dvddao.findAll();
	}
	
	public void add(){
		dvddao.addDvd(null);
	}
	
	public void borrowDvd() throws ParseException{
		System.out.println("请输入DVD的ID: ");
		int id=input.nextInt();
		Dvd dvd=new Dvd();
		dvd=dvddao.findById(id);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		if(dvd.getState()==1&&dvd.getName()!=null){
			System.out.println("DVD已借出！！");
		}else if(dvd.getState()==0){
		System.out.println("请输入借出日期（年-月-日）: ");
	    String date=input.next();
	    Date date1=sf.parse(date);
		dvd.setBorrowDate(date1);
		dvd.setState(1);
		dvd.setReturnDate(null);
		dvd.setBorrowCount(dvd.getBorrowCount()+1);
		dvddao.updateDvd(dvd);
		}else{
			System.out.println("DVD不存在！！");
		}
	}
		
  

	private void close() {
		// TODO Auto-generated method stub
		
	}

	public void returnDvd() throws ParseException{
    	Date date=new Date();
		java.sql.Date rdate=new java.sql.Date(date.getTime());
    	System.out.print("请输入要归还DVD id:");
		int id = input.nextInt();
		Dvd dvd=dvddao.findById(id);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		if(dvd.getState()==0 &&dvd.getName()!=null){
			 System.out.println("DVD已归还！！");
		 }else if(dvd.getState()==1&&dvd.getName()!=null){
		 System.out.println("请输入归还日期（年-月-日）: ");
	     String date2=input.next();
	     Date date1=sf.parse(date2);	   
		 dvd.setReturnDate(date1);		
		 System.out.println("支付日租金："+(dvd.getReturnDate().getTime()-dvd.getBorrowDate().getTime())/(1000*24*60*60));		 
		 dvd.setBorrowDate(null);
		 dvd.setState(0);	 
		 dvddao.updateDvd(dvd);
		 }else{
				System.out.println("DVD不存在！！");
			}
	}
	
	public void dvdTop(){
		System.out.println("------热门Dvd排行榜------");
		System.out.println(
    			"序号 " + "\t" + "状态(0借出1未借出)" + "\t" + "名称" + "\t\t" + "借出日期"+ "\t\t" + "归还日期"+"\t\t"+"借出次数");
		List<Dvd> list=dvddao.findAll();
    	Collections.sort(list);  	
     }		
	

	
	public void delete(){
		System.out.print("请输入要删除的DVD id:");
		int id = input.nextInt();
		Dvd dvd=dvddao.findById(id);
		/*if(dvd.getName()!=null){
			System.out.println("DVD已经被借出");
		}else if(dvd.getState()==1){*/
			dvddao.deleteDvd(id);
			/*}else {
				System.out.println("该DVD不存在");*/
			}	
		}
	
  

