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
		System.out.println("\t\t��ӭʹ������DVD������");
		System.out.println("--------------------------------------------");
		System.out.println("\t\t1.��    ��   DVD");
		System.out.println("\t\t2.��    ��   DVD");
		System.out.println("\t\t3.ɾ    ��   DVD");
		System.out.println("\t\t4.��    ��   DVD");
		System.out.println("\t\t5.��    ��   DVD");
		System.out.println("\t\t6.��    ��   DVD");
		System.out.println("\t\t7.��    ��   DVD");
		System.out.println("--------------------------------------------");
		System.out.print("��ѡ��");
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
			System.out.println("ллʹ������DVD!");
			tag=false;
			break;
		default:
			System.out.println("������������������....");
			tag=true;
		}
		}while(tag);
	}	
	
	public void reMenu() throws ParseException{
		boolean flag=false;
		System.out.print("����0���أ�");
		do{
		if(input.nextInt()==0){
			startMenu();
		}else{
			System.out.println("����������������������...");
			flag=true;
		}
		}while(flag);
	}
	
}
