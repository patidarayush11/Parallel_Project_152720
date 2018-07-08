package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.WalletBean;

public interface IWalletService {
	public int addUserDetails(WalletBean user);
	
	public WalletBean getUserDetails(int uid);
	public double showBalance(int uid);
	public double deposit(WalletBean user, double amount);
	public double fundTransfer(int uid,int uid1, double amount);
	public void printTransactions(int uid);
	public double withdraw(WalletBean user,double amount);
}
