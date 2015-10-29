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
		System.out.println("������DVD��ID: ");
		int id=input.nextInt();
		Dvd dvd=new Dvd();
		dvd=dvddao.findById(id);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		if(dvd.getState()==1&&dvd.getName()!=null){
			System.out.println("DVD�ѽ������");
		}else if(dvd.getState()==0){
		System.out.println("�����������ڣ���-��-�գ�: ");
	    String date=input.next();
	    Date date1=sf.parse(date);
		dvd.setBorrowDate(date1);
		dvd.setState(1);
		dvd.setReturnDate(null);
		dvd.setBorrowCount(dvd.getBorrowCount()+1);
		dvddao.updateDvd(dvd);
		}else{
			System.out.println("DVD�����ڣ���");
		}
	}
		
  

	private void close() {
		// TODO Auto-generated method stub
		
	}

	public void returnDvd() throws ParseException{
    	Date date=new Date();
		java.sql.Date rdate=new java.sql.Date(date.getTime());
    	System.out.print("������Ҫ�黹DVD id:");
		int id = input.nextInt();
		Dvd dvd=dvddao.findById(id);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		if(dvd.getState()==0 &&dvd.getName()!=null){
			 System.out.println("DVD�ѹ黹����");
		 }else if(dvd.getState()==1&&dvd.getName()!=null){
		 System.out.println("������黹���ڣ���-��-�գ�: ");
	     String date2=input.next();
	     Date date1=sf.parse(date2);	   
		 dvd.setReturnDate(date1);		
		 System.out.println("֧�������"+(dvd.getReturnDate().getTime()-dvd.getBorrowDate().getTime())/(1000*24*60*60));		 
		 dvd.setBorrowDate(null);
		 dvd.setState(0);	 
		 dvddao.updateDvd(dvd);
		 }else{
				System.out.println("DVD�����ڣ���");
			}
	}
	
	public void dvdTop(){
		System.out.println("------����Dvd���а�------");
		System.out.println(
    			"��� " + "\t" + "״̬(0���1δ���)" + "\t" + "����" + "\t\t" + "�������"+ "\t\t" + "�黹����"+"\t\t"+"�������");
		List<Dvd> list=dvddao.findAll();
    	Collections.sort(list);  	
     }		
	

	
	public void delete(){
		System.out.print("������Ҫɾ����DVD id:");
		int id = input.nextInt();
		Dvd dvd=dvddao.findById(id);
		/*if(dvd.getName()!=null){
			System.out.println("DVD�Ѿ������");
		}else if(dvd.getState()==1){*/
			dvddao.deleteDvd(id);
			/*}else {
				System.out.println("��DVD������");*/
			}	
		}
	
  
