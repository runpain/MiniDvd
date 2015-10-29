package org.zucc.entity;

import java.util.Date;

public class Dvd implements Comparable<Dvd>{
		private int id;
		private int state;
		private Date borrowDate;
		private Date returnDate;
		private String name;
		private int borrowCount;
		public int getBorrowCount() {
			return borrowCount;
		}
		public void setBorrowCount(int borrowCount) {
			this.borrowCount = borrowCount;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public Date getBorrowDate() {
			return borrowDate;
		}
		public void setBorrowDate(Date borrowDate) {
			this.borrowDate = borrowDate;
		}
		public Date getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		public Dvd() {
			super();
		}
		public Dvd(String name) {
			super();
			this.name = name;
		}
		@Override
		public String toString() {
			return "Dvd [borrowCount=" + borrowCount + ", borrowDate="
					+ borrowDate + ", id=" + id + ", name=" + name
					+ ", returnDate=" + returnDate + ", state=" + state + "]";
		}
		@Override
		public int compareTo(Dvd d) {
			// TODO Auto-generated method stub
			return this.borrowCount-d.borrowCount;
		}
	
		
}
