package com.capgemini.com.wallet.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static Connection con;
	
	static{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				//String url="jdbc:oracle:thin:@10.51.103.201:1521:orcl11g";
				String url="jdbc:mysql://localhost:3306/wallet";
				String user="root";
				String password="root";
				con= DriverManager.getConnection(url,user,password);
				
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
		
	}
	
	public static Connection getConnection(){
		
		return con;
		}
}
