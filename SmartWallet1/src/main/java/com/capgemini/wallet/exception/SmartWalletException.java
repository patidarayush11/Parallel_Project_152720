package com.capgemini.wallet.exception;

public class SmartWalletException extends Exception {
	String message;
	
	public SmartWalletException(String msg) 
	{
		message=msg;
	}
	
	@Override
	public String getMessage()
	{
		return message;
	}

}
