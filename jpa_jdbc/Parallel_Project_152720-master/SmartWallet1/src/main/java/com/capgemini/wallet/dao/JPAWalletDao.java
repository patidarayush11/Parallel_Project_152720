package com.capgemini.wallet.dao;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.service.IWalletService;

public class JPAWalletDao implements IWalletService {

	@Override
	public int addUserDetails(WalletBean user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WalletBean getUserDetails(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double showBalance(int uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double deposit(WalletBean user, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fundTransfer(int uid, int uid1, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printTransactions(int uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double withdraw(WalletBean user, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

}
