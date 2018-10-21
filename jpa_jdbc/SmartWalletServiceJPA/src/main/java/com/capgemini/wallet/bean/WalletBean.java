package com.capgemini.wallet.bean;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="smart_wallet")
public class WalletBean {
	@Id
	private int uid;
	
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@Column(name="mobile")
	private String mobile;
	@Column(name="email")
	private String email;
	@Column(name="pass")
	private String pass;
	@Column(name="balance")
	private double balance;
	/*private ArrayList<String> transactions=new ArrayList<String>();
	
	
	

	public ArrayList<String> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<String> transactions) {
		this.transactions = transactions;
	}*/
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUid() {
		return uid;
	}
	
	@Override
	public String toString() {
		return "WalletBean [name=" + name + ", age=" + age + ", mobile="
				+ mobile + ", uid=" + uid + ", email=" + email + ", balance="
				+ balance + "]";
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
