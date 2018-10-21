package com.capgemini.wallat.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.service.WalletService;

public class testWallet {
	
	WalletBean wb=new WalletBean();
	WalletBean wb1=new WalletBean();
	WalletService ws=new WalletService();
	
	@Before
		public void before() {
		
		wb.setName("Ayush");
		wb.setAge(12);
		wb.setEmail("patidarayush11@gmail.com");
		wb.setMobile("9962775502");
		wb.setBalance(400);
		
	}
	
	@Test
	public void testPassAddAccount(){
		wb.setUid(1234);
		assertEquals(1234,ws.addUserDetails(wb));
	}
	
	@Test
	public void testFailAddAccount(){
		wb.setUid(1234);
		assertNotEquals(1235,ws.addUserDetails(wb));
	}
	

	@Test
	public void testPassDeposit(){
		wb1.setUid(1236);
		
		ws.addUserDetails(wb);
		int bal=(int)ws.deposit(wb1, 200);
		int check=(int) wb1.getBalance();  //Int Because the assertEquals(double , double) is deprecated.
		assertEquals(bal,check);
	}
	
	@Test
		public void testFailDeposit() {
			wb.setUid(1237);
			ws.addUserDetails(wb);
			//int bal=(int)ws.deposit(wb, 200);
			int check=(int) wb.getBalance();
			
			assertNotEquals(200,check);
		
		
	}
	@Test
	 	public void testPassWithdraw() 
		{
	 		wb.setUid(1238);
	 		ws.addUserDetails(wb);
	 		int bal=(int)ws.withdraw(wb, 200);
	 		assertEquals(200, bal);
	 	}
	
	@Test
		public void testFailWithdraw() 
		{
			wb.setUid(1239);
			ws.addUserDetails(wb);
			int bal=(int)ws.withdraw(wb, 200);
			assertNotEquals(100,bal);
		}
	
	@Test
		public void testPassFundTransfer() 
		{
			wb1.setName("Piyush");
			wb1.setAge(19);
			wb1.setEmail("piyush@gmail.com");
			wb1.setMobile("9962774403");
			wb1.setBalance(1500);
			wb1.setUid(1240);
			ws.addUserDetails(wb1);
			wb.setUid(1241);
			ws.addUserDetails(wb);
			
			ws.fundTransfer(1240, 1241, 200);
			int bal=(int) ws.showBalance(1240);
			
			assertEquals(1300,bal);
		}
	
	@Test 
		public void testFailFundTransfer() 
		{
			
			wb1.setName("Piyush");
			wb1.setAge(19);
			wb1.setEmail("piyush@gmail.com");
			wb1.setMobile("9962774403");
			wb1.setBalance(1600);
			wb1.setUid(1242);
			ws.addUserDetails(wb1);
			wb.setUid(1243);
			ws.addUserDetails(wb);
			
			ws.fundTransfer(1242, 1243, 100);
			int bal=(int) ws.showBalance(1242);
			assertNotEquals(1600,bal);
		}
	
	@Test
		public void testPassShowBalance() 
		{
			wb.setUid(1244);
			ws.addUserDetails(wb);
			int bal=(int) ws.showBalance(1244);
			assertEquals(400,bal);
		}
	
	@Test
		public void testFailShowBalance() 
		{
			wb.setUid(1245);
			ws.addUserDetails(wb);
			int bal=(int) ws.showBalance(1245);
			assertNotEquals(500,bal);
		}
	
}
