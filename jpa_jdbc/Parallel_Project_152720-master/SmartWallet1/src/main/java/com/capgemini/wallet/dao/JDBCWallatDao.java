package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.capgemini.com.wallet.util.DBUtil;
import com.capgemini.wallet.bean.WalletBean;

public class JDBCWallatDao implements IWalletDao {
	Connection con;
	
	public JDBCWallatDao() {
		con= DBUtil.getConnection();
		System.out.println("Connected to Smart Wallet!!!");
	}
	
	
	@Override
	public int addUserDetails(WalletBean user) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO smart_wallet values(?,?,?,?,?,?,?)";
		
		try{	
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, user.getName());
				pstmt.setInt(2,user.getAge());
				pstmt.setString(3, user.getMobile());
				pstmt.setInt(4,user.getUid());
				pstmt.setString(5,user.getEmail());
				pstmt.setString(6, user.getPass());
				pstmt.setDouble(7, user.getBalance());
				
				int row=pstmt.executeUpdate();
				
				if(row>0)
					System.out.println("Your data is stored successfully!!");
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public WalletBean getUserDetails(int uid) {
		// TODO Auto-generated method stub
		WalletBean wb=new WalletBean();
		if(validateUserID(uid))
		{
			try{
				String sql="SELECT * FROM smart_wallet WHERE uid=?";
				PreparedStatement pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, uid);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()){
					wb.setName(rs.getString(1));
					wb.setAge(rs.getInt(2));
					wb.setMobile(rs.getString(3));
					wb.setUid(rs.getInt(4));
					wb.setPass(rs.getString(5));
					wb.setBalance(rs.getDouble(6));
					
				}
				//System.out.println("Id details :"+rs.getString(1));
			}
		
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		else
			System.out.println("No such Id found");
		
		return wb;
	}
	
	//Check if Patient Id exists in database
	public boolean validateUserID(int userId)
	{
		// TODO Auto-generated method stub
		boolean flag = false;
		String query = "SELECT * FROM smart_wallet WHERE uid = ?";
		try
		{
			PreparedStatement pstmt = con.prepareStatement(query);	
			pstmt. setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
			if(res.next())
				flag = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public double showBalance(int uid) {
		String sql="SELECT balance from smart_wallet WHERE uid=?";
				
				try {
					PreparedStatement pstmt = con.prepareStatement(sql);	
					pstmt. setInt(1, uid);
					ResultSet res = pstmt.executeQuery();
					if(res.next())
						return res.getDouble(1);
				}
				catch(SQLException e){
					e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double deposit(WalletBean user, double amount) {
		// TODO Auto-generated method stub
		String sql="UPDATE smart_wallet SET balance =? WHERE uid=?";
		try{	
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setDouble(1, user.getBalance());
			pstmt.setInt(2,user.getUid());
			
			
			int row=pstmt.executeUpdate();
			
			if(row>0) {
				System.out.println("Deposited successfully!!");
				String str="Self deposit :"+ +amount;
				String sql1="INSERT INTO transactions values(?,?)";
				PreparedStatement pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, user.getUid());
				pstmt1.setString(2, str);
				
				pstmt1.execute();
				/*ArrayList<String> tempList=new ArrayList<String>();
				tempList=user.getTransactions();
				String str="Self withdraw :"+ -amount;
				tempList.add(str);
				user.setTransactions(tempList);*/
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public ArrayList<WalletBean> fundTransfer(int uid1, int uid2)  {
		ArrayList<WalletBean> ls=new ArrayList<WalletBean>();
		try {
			ls.add(getObject(uid1));
			ls.add(getObject(uid2));
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public void printTransactions(int uid) {
		String sql="SELECT * FROM transactions WHERE id=?";
		
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
				System.out.println(rs.getString(2));
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}


	@Override
	public double withdraw(WalletBean user, double amount) {
		String sql="UPDATE smart_wallet SET balance =? WHERE uid=?";
		try{	
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setDouble(1, user.getBalance());
			pstmt.setInt(2,user.getUid());
			
			int row=pstmt.executeUpdate();
			
			if(row>0) {
				System.out.println("Withdrawn successfully!!");
				
				String str="Self Withdrawn :"+ -amount;
				String sql1="INSERT INTO transactions values(?,?)";
				PreparedStatement pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, user.getUid());
				pstmt1.setString(2, str);
				
				pstmt1.execute();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return user.getBalance();
	}
	
	public WalletBean getObject(int uid) throws SQLException 
	{
		WalletBean temp=new WalletBean();
		String sql="SELECT * from smart_wallet WHERE uid=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,uid);
		ResultSet res = pstmt.executeQuery();
		
		if(res.next()) {
		
		temp.setName(res.getString(1));
		temp.setAge(res.getInt(2));
		temp.setMobile(res.getString(3));
		temp.setUid(res.getInt(4));
		temp.setEmail(res.getString(5));
		temp.setPass(res.getString(6));
		temp.setBalance(res.getDouble(7));
		}
		
		//System.out.println(temp.toString());
		return temp;
	}


	public void updateFundTransfer(WalletBean temp, WalletBean temp1,String str) {
	
		String sql="UPDATE smart_wallet SET balance =? where uid=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(2, temp.getUid());
			pstmt.setDouble(1, temp.getBalance());
			pstmt.execute();
			
			String sql1="INSERT INTO transactions values(?,?)";
			PreparedStatement pstmt2=con.prepareStatement(sql1);
			pstmt2.setInt(1, temp.getUid());
			pstmt2.setString(2, str);
			pstmt2.execute();
			
			
			
			PreparedStatement pstmt1=con.prepareStatement(sql);
			pstmt1.setDouble(1, temp1.getBalance());
			pstmt1.setInt(2, temp1.getUid());
			pstmt1.execute();
			
			String sql2="INSERT INTO transactions values(?,?)";
			PreparedStatement pstmt3=con.prepareStatement(sql2);
			pstmt3.setInt(1, temp1.getUid());
			pstmt3.setString(2, str);
			pstmt3.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

