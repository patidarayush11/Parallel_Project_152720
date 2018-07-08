package com.capgemini.wallet.dao;

import java.util.ArrayList;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.exception.SmartWalletException;

public interface IWalletDao {
	public int addUserDetails(WalletBean user)  throws SmartWalletException;
	public WalletBean getUserDetails(int uid)  throws SmartWalletException;
	public double showBalance(int uid);
	public double deposit(WalletBean user,double amount);
	public ArrayList<WalletBean> fundTransfer(int wb1,int wb2);
	public void printTransactions(int uid);
	public double withdraw(WalletBean user,double amount);
	
}
