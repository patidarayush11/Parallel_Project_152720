package com.capgemini.com.wallet.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.capgemini.wallet.bean.WalletBean;
import com.capgemini.wallet.exception.SmartWalletException;
import com.capgemini.wallet.service.WalletService;

public class Client {

	public static void main(String[] args) throws IOException, SmartWalletException 
	{
		String name,email;
		int uid;
		int age;
		String mob,pass;
		boolean flag,flag1,flag2,flag3,flag4,flag5;
		boolean flag6=false;
		double amount;
		
		WalletService ws = new WalletService();
		Scanner sc=new Scanner(System.in);
		int cont;
		int welcomeChoice;
		int choice;
		int cont1;
		
		try{		
			do
				{
					System.out.println("\n*************Welcome to Smart Wallet*************");
					System.out.print("1. Sign Up\n 2. Login\n");
					System.out.print("\nPlease Enter a Choice : ");
					welcomeChoice= sc.nextInt();
					System.out.println("\n******************************");
					
					if(welcomeChoice==1)
					{
						WalletBean wb=new WalletBean();
					
						do
						{
							System.out.print("Enter your name :");
							name=sc.next();
							flag=ws.validateName(name);
								if(flag==true)
								{
									
									System.out.print("******************************\n");
									wb.setName(name);
								}
								
								else
									flag=false;	
					
						}while(flag==false);
					
					do
					{
						System.out.print("Enter uid :");
						uid=sc.nextInt();
						flag1=ws.validateUid(uid);
						
						if(flag1==true)
						{
							System.out.print("******************************\n");
							wb.setUid(uid);
						}

						else
						{
							System.out.println("Duplicate id");
							flag1=false;
						}
					
					}while(flag1==false);
						System.out.println("Set a Password :");
						wb.setPass(sc.next());
						System.out.print("******************************\n");
					
						do
						{
							System.out.print("Enter your age :");
							age=sc.nextInt();
							flag3=ws.validateAge(age);
								if(flag3==true)
								{
									System.out.print("******************************\n");
									wb.setAge(age);
								}
					
					
								else
									flag3=false;
						
						}while(flag3==false);
					
					
						do
						{
							System.out.print("Enter your mobile number :");
							mob=sc.next();
							flag4=ws.validateMobile(mob);
								if(flag4==true)
								{
									System.out.print("******************************\n");
									wb.setMobile(mob);
						
								}
								else
									flag4=false;
						}while(flag4==false);
					
						do
						{
							System.out.print("Enter you Email :");
							email=sc.next();
							flag5=ws.validateEmail(email);
							if(flag5==true)
							{
								System.out.print("******************************\n");
								wb.setEmail(email);
							}
							else
								flag5=false;
						}while(flag5==false);
					
						ws.addUserDetails(wb);
					
				}
					
				else
				{
					WalletBean ref;
					System.out.println("Enter your Id");
					uid=sc.nextInt();
					ref=ws.getObject(uid);
						if(ref.getUid()!=0)
						{
							System.out.println("Enter your password :");
							pass=sc.next();
								if(ref.getPass().equals(pass))
								{
									do
									{
										System.out.println("\n*************Smart Wallet*************");
										System.out.println("Choose an operation");
										System.out.println("1. Deposit ");
										System.out.println("2. Withdraw ");
										System.out.println("3. Fund Transfer");
										System.out.println("4. Print Transactions");
										System.out.println("5. Balance Enquiry");
										System.out.println("6. Exit");
										System.out.println("******************************");
										System.out.println("Enter your choice :");
										choice=sc.nextInt();
								
									
										switch(choice){
				
											case 1:
												System.out.print("Enter the amount to deposit :");
												amount=sc.nextDouble();
						
												System.out.print("Enter your id: ");
												uid=sc.nextInt();
												do{
													System.out.println("Enter your password");
													InputStreamReader isr=new InputStreamReader(System.in);
													BufferedReader br=new BufferedReader(isr);
													String pass1=br.readLine();
													
														WalletBean temp;
														temp=ws.getObject(uid);
														if((temp.getPass().equals(pass1))){
															ws.deposit(temp,amount);
															flag6=true;
														}
														else{
															System.out.println("Incorrect Password");
															flag6=false;
														}
												}while(flag6==false);
												break;
						
											case 2:
												System.out.print("Enter the amount to Withdraw :");
												amount=sc.nextDouble();
												System.out.print("Enter your id: ");
												uid=sc.nextInt();
												do{
													System.out.println("Enter your password");
													InputStreamReader isr=new InputStreamReader(System.in);
													BufferedReader br=new BufferedReader(isr);
													String pass1=br.readLine();
													
														WalletBean temp1;
														temp1=ws.getObject(uid);
														if((temp1.getPass().equals(pass1))){
															ws.withdraw(temp1,amount);
															flag6=true;
														}
														else{
															System.out.println("Incorrect Password");
															flag6=false;
														}
												}while(flag6==false);
												
												break;
				
					
											case 3:
												System.out.print("Enter your uid :");
												uid=sc.nextInt();
												System.out.print("Enter the uid of person to whom you want to transfer the money :");
												int uid2=sc.nextInt();
												System.out.println("Enter the amount :");
												amount=sc.nextDouble();
												ws.fundTransfer(uid, uid2, amount);
												break;
					
											case 4:
						
												System.out.println("Enter your id: ");
												uid=sc.nextInt();
												ws.printTransactions(uid);
												break;
				
											case 5:
												//System.out.println("Confirm your id to check balance :");	
												double showBal=ws.showBalance(uid);
												System.out.println("Your balance :"+showBal);
												break;
											
											case 6:
												System.out.println("Thanks");
												System.exit(0);
												break;
				
											default:
												System.out.println("Enter something");
										}
			    
										System.out.println("Do you want to continue (1.Yes / 2. No):");
										cont=sc.nextInt();
			    
									}while(cont==1);
			
								
								}
								else
									System.out.println("Incorrect Password");
						}
						
						else{
							System.out.println("No such id found");
							
						}
				
				}
						System.out.println("Do you want to exit (1.Yes / 2.No)? ");
						cont1=sc.nextInt();
						if(cont1==1)
							System.exit(0);
				}while(cont1==2);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}

			
		

	

