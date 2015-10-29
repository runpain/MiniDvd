package org.zucc.view;

import java.text.ParseException;
import java.util.Scanner;

import org.zucc.dao.DvdDao;
import org.zucc.service.DvdManager;



public class DvdMenu {

	Scanner input=new Scanner(System.in);
	DvdManager dm = new DvdManager();
	
	public void startMenu() throws ParseException{
		boolean tag=false;
		do{
		System.out.println("\t\t欢迎使用迷您DVD管理器");
		System.out.println("--------------------------------------------");
		System.out.println("\t\t1.新    增   DVD");
		System.out.println("\t\t2.查    看   DVD");
		System.out.println("\t\t3.删    除   DVD");
		System.out.println("\t\t4.借    出   DVD");
		System.out.println("\t\t5.归    还   DVD");
		System.out.println("\t\t6.排    行   DVD");
		System.out.println("\t\t7.退    出   DVD");
		System.out.println("--------------------------------------------");
		System.out.print("请选择：");
		int choice=input.nextInt();
		switch (choice) {		
		case 1:
			dm.add();
			reMenu();
			tag=false;
			break;
		case 2:
			dm.find();
			reMenu();
			tag=false;
			break;
		case 3:
			dm.delete();
			reMenu();
			tag=false;
			break;
		case 4:
			dm.borrowDvd();
			reMenu();
			tag=false;
			break;
		case 5:
			dm.returnDvd();
			reMenu();
			tag=false;
			break;
		case 6:
			dm.dvdTop();
			reMenu();
			tag=false;
			break;
		case 7:
			System.out.println("谢谢使用迷你DVD!");
			tag=false;
			break;
		default:
			System.out.println("输入有误，请重新输入....");
			tag=true;
		}
		}while(tag);
	}	
	
	public void reMenu() throws ParseException{
		boolean flag=false;
		System.out.print("输入0返回：");
		do{
		if(input.nextInt()==0){
			startMenu();
		}else{
			System.out.println("您的输入有误，请重新输入...");
			flag=true;
		}
		}while(flag);
	}
	
}
