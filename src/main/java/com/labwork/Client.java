package com.labwork;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
	
	
	private String login(String username, String password) throws MalformedURLException, RemoteException, NotBoundException {

		String sessToken = authService.login(username, password);
		System.out.println("(client): I am user: '" + username + "'  with password: '" + password + 
							"'and I got session token: '" + sessToken + "'");		
		mySessToken = sessToken;
		return sessToken;
	}
	
	
	private String print(String filename, String printer) throws RemoteException {
		String res = printService.print(filename, printer, mySessToken);
		System.out.println("(client): "+res);
		return res;
	}
	
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
	
		Client rmc = new Client();
		Scanner keyb = new Scanner(System.in);
		String input = "";
		while(!input.equals("Exit")) {
			input = keyb.nextLine();
			
			
			switch (input) {
				case "login":
				{
					rmc.login("admin", "admin");
					break;
				}
				
				case "print":
				{
					rmc.print("fileToPrint", "Printer1");
					break;
				}
			}
					

		}
		keyb.close();
	}
}



