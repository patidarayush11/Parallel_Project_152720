package com.capgemini.wallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.service.IWalletService;

public class JPAWalletDao implements IWalletService {
	private EntityManager entityManager;
	
	public JPAWalletDao() {
		entityManager = JPAUtil.getEntityManager();
		System.out.println("Connected to Smart Wallet!!!");
	}

	@Override
	public int addUserDetails(WalletBean user) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user.getUid();
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

		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		return user.getBalance();
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
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		return user.getBalance();
	}

	public boolean validateUserID(int uid) {
		if((entityManager.find(WalletBean.class, uid)).getUid()== uid){
			System.out.println(""+(entityManager.find(WalletBean.class, uid)).getUid());
			return true;
		}
		return false;
	}

	public WalletBean getObject(int uid) {
		
		return entityManager.find(WalletBean.class, uid);
		
	}


}
