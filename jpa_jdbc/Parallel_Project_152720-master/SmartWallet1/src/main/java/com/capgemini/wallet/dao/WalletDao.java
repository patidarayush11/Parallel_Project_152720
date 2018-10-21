package com.capgemini.wallet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.exception.SmartWalletException;
import com.capgemini.wallet.service.WalletService;

public class WalletDao implements IWalletDao{
	Map <Integer ,WalletBean> map=new HashMap<Integer ,WalletBean>();
	Map<Integer, ArrayList<String>> transactions=new HashMap<Integer, ArrayList<String>>();
	WalletBean wb= new WalletBean();
	
	@Override
	public int addUserDetails(WalletBean user) {
		
		try {
				if(map.containsKey(user.getUid())){
					System.out.println("Duplicate Id ");
					return 0;
				}
			
				else{
					map.put(user.getUid(),user);
					System.out.print("Your Details updated Successfully");
					System.out.print("Contents :"+map.toString());
				}
			}
		
		catch(NullPointerException e) 
		{
			e.getMessage();
		}
		return user.getUid();
	}
	
	public Map getMap(){
		return map;
		
	}
	
	@Override
	public WalletBean getUserDetails(int uid) {
		System.out.println("Map :"+map);
		return null;
	}

	@Override
	public double showBalance(int uid) {
		WalletBean ref=new WalletBean();
		ref=map.get(uid);
		double balance;
		balance=ref.getBalance();
		System.out.println("Current Balance :"+balance);
		return balance;
	}

	

	@Override
	public ArrayList<WalletBean> fundTransfer(int uid, int uid1) {
		
		List <WalletBean> al=new ArrayList<WalletBean>();
		if(map.containsKey(uid) && map.containsKey(uid1)){
			al.add(map.get(uid));
			al.add(map.get(uid1));
			return (ArrayList<WalletBean>) al;
		}
		else{
			System.out.println("Transaction Failed check user ids!!");
			return null;
		}
	}
	
	public void updateFundTransfer(WalletBean wb,WalletBean wb1){
		map.put(wb.getUid(), wb);
		map.put(wb1.getUid(),wb1);
		
	}

	@Override
	public void printTransactions(int uid) {
		System.out.println("*****Transactions******");
		WalletBean temp=new WalletBean();
		temp=map.get(uid);
		
		System.out.print("Transactions :"+temp.getTransactions().toString());
		
	}

	@Override
	public double withdraw(WalletBean user, double amount) 
	{
		map.put(user.getUid(), user);
		System.out.println("Rs. "+amount+" withdrawn "+"from account id "+user.getUid());
		System.out.println("Updated balance "+"(" +user.getUid()+") :"+user.getBalance());
		ArrayList<String> tempList=new ArrayList<String>();
		tempList=user.getTransactions();
		String str="Self withdraw :"+ -amount;
		tempList.add(str);
		user.setTransactions(tempList);
		return user.getBalance();
	}

	@Override
	public double deposit(WalletBean user, double amount) 
	{
		map.put(user.getUid(), user);
		System.out.println("Rs. "+amount+" deposited "+"to account id "+user.getUid());
		System.out.println("Updated balance "+"("+user.getUid()+") :"+user.getBalance());
		ArrayList<String> tempList=new ArrayList<String>();
		tempList=user.getTransactions();
		String str="Self Deposit :"+ +amount;
		tempList.add(str);
		user.setTransactions(tempList);
		
		return user.getBalance();
	}

	public WalletBean getObject(int uid) 
	{
		WalletBean temp;
		temp=map.get(uid);
		return temp;
	}

 
}
