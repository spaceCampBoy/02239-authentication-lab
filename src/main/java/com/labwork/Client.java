package com.labwork;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class Client {
	private PrintServiceInterface printService;
	private AuthServiceInterface authService;
	private static String mySessToken;
	
	
	public Client() throws MalformedURLException, RemoteException, NotBoundException {
		printService = (PrintServiceInterface) Naming.lookup("rmi://localhost:1099/print");
		authService = (AuthServiceInterface) Naming.lookup("rmi://localhost:1099/auth");
		mySessToken = null;
		System.out.println("Client started");
	}
	
	
	private void login(String username, String password) throws MalformedURLException, RemoteException, NotBoundException, GeneralSecurityException {

		mySessToken = authService.login(username, password);
		System.out.println("(client): I am user: '" + username + "'  with password: '" + password + 
							"'and I got session token: '" + mySessToken + "'");		
	}
	
	
	private String print(String filename, String printer) throws RemoteException {
		String res = printService.print(filename, printer, mySessToken);
		System.out.println("(client): "+res);
		return res;
	}
	
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, GeneralSecurityException {
	
		Client rmc = new Client();
		Scanner keyb = new Scanner(System.in);
		String input = "";
		String[] inSpl = input.split(" ");
		
		while(!inSpl[0].equals("Exit")) {
			input = keyb.nextLine();
			inSpl = input.split(" ");
			
			switch (inSpl[0]) {
				case "login":
				{
					
					rmc.login(inSpl[1], inSpl[2]);
					break;
				}
				
				case "print":
				{
					rmc.print(inSpl[1], inSpl[2]); //fileName, printerName
					break;
				}
			}
					

		}
		keyb.close();
	}
}



