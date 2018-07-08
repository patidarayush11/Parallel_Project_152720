package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.dao.WalletDao;

public class WalletService implements IWalletService 
{
	WalletDao dao =new WalletDao();
	WalletBean wb=new WalletBean();
	
	static String namePattern= "[A-Z][a-z]{2,}";
	static String uidPattern="[1-9][0-9]{3}";
	static String mobPattern="[6-9][0-9]{9}";
	static String emailPattern="^[\\w-\\+]+(\\.[\\w]+)*@[\\w]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	
	@Override
	public int addUserDetails(WalletBean user) 
	{
		return dao.addUserDetails(user);
	}

	public boolean validateName(String name)
	{
		boolean flag=false;
			if(name.matches(namePattern))
				flag=true;
		
			else
				flag=false;
		
		return flag;
	}
	
	public boolean validateUid(int uid)
	{
	
		boolean flag =false;
			if((String.valueOf(uid)).matches(uidPattern))
			{
				if((!(dao.getMap()).containsKey(uid)))
					flag=true;
			}
			else
				flag=false;
		
		return flag;
		
	}
	
	public boolean validateAge(int age)
	{
		boolean flag;
			if(age<=0 ||age>110)
				flag=false;
	
			else
				flag=true;
		
		return flag;
	}
	
	public boolean validateMobile(String mob)
	{
		boolean flag;
			if(mob.matches(mobPattern))
				flag=true;
		
			else
				flag=false;
		
		return flag;
		
	}

	public boolean validateEmail(String email)
	{
		boolean flag;
			if(String.valueOf(email).matches(emailPattern))
				flag=true;
			
			else 
				flag=false;
		
		return flag;
	}
	
	@Override
	public WalletBean getUserDetails(int uid) 
	{
		return dao.getUserDetails(uid);
	}

	@Override
	public double showBalance(int uid) 
	{
		return dao.showBalance(uid);
	}

	@Override
	public double deposit(WalletBean user, double amount) 
	{
		
		user.setBalance(user.getBalance()+amount);
		//System.out.println("Check :"+user.getBalance());
		return dao.deposit(user, amount);
			
	}

	@Override
	public double fundTransfer(int uid, int uid1 ,double amount) 
	{
		// TODO Auto-generated method stub
		WalletBean temp,temp1;
		WalletService ws=new WalletService();
		temp=(dao.fundTransfer(uid, uid1)).get(0);
		temp1=(dao.fundTransfer(uid, uid1)).get(1);
		ws.withdraw(temp, amount);
		ws.deposit(temp1,amount);
		//String str=uid+" transferred "+amount+" to "+uid1;
		System.out.print("Amount transferred"+ dao.getMap().toString());
		
		return 0;
	}

	@Override
	public void printTransactions(int uid) {
		// TODO Auto-generated method stub
		 dao.printTransactions(uid);
		
	}

@Override
	public double withdraw(WalletBean user ,double amount) 
	{
		    user.setBalance(user.getBalance()-amount);
			return dao.withdraw(user, amount);
	}
	public WalletBean getObject(int uid)
	{
		return dao.getObject(uid );
	}

}
