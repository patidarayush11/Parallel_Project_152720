package com.capgemini.wallet.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.dao.JDBCWallatDao;
import com.capgemini.wallet.dao.WalletDao;

public class WalletService implements IWalletService 
{
	WalletDao dao =new WalletDao();
	WalletBean wb=new WalletBean();
	JDBCWallatDao jdbc =new JDBCWallatDao();
	
	static String namePattern= "[A-Z][a-z]{2,}";
	static String uidPattern="[1-9][0-9]{3}";
	static String mobPattern="[6-9][0-9]{9}";
	static String emailPattern="^[\\w-\\+]+(\\.[\\w]+)*@[\\w]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	
	@Override
	public int addUserDetails(WalletBean user) 
	{
		return jdbc.addUserDetails(user);
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
				if(!jdbc.validateUserID(uid))
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
		return jdbc.getUserDetails(uid);
	}

	@Override
	public double showBalance(int uid) 
	{
		return jdbc.showBalance(uid);
	}

	@Override
	public double deposit(WalletBean user, double amount) 
	{
		
		user.setBalance(user.getBalance()+amount);
		//System.out.println("Check :"+user.getBalance());
		return jdbc.deposit(user, amount);
			
	}

	@Override
	public double fundTransfer(int uid, int uid1 ,double amount) 
	{
		WalletBean temp,temp1;
		//WalletService ws=new WalletService();
		temp=(jdbc.fundTransfer(uid, uid1)).get(0);
		temp1=(jdbc.fundTransfer(uid, uid1)).get(1);
		String amt=""+amount;
	//	String str=temp.getName()+" ("+temp.getUid()+")"+ " transferred "+amount+" to "+temp1.getName()+" ("+temp1.getUid()+")";
		String str=temp.getName()+" transferred "+amt+" to "+temp1.getName();
		
		
		/*ArrayList<String> tempList,tempList1=new ArrayList<String>();
		tempList=temp.getTransactions();
		tempList1=temp1.getTransactions();
		tempList.add(str);
		tempList1.add(str);
		temp.setTransactions(tempList);
		temp1.setTransactions(tempList1);
		
		temp.setBalance(temp.getBalance()-amount);
		temp1.setBalance(temp1.getBalance()+amount);*/
		jdbc.updateFundTransfer(temp,temp1,str);
		//System.out.print("Amount transferred"+ dao.getMap().toString());
		
		
		return 0;
	}

	@Override
	public void printTransactions(int uid) {
		// TODO Auto-generated method stub
		 jdbc.printTransactions(uid);
		
	}

@Override
	public double withdraw(WalletBean user ,double amount) 
	{
		    user.setBalance(user.getBalance()-amount);
			return jdbc.withdraw(user, amount);
	}
	
	public WalletBean getObject(int uid) throws SQLException
	{
				return jdbc.getObject(uid);
	}

}
