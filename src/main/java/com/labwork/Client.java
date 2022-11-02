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
	private static final String LACK_PARAMS_MESSAGE = "error: Not all parameters entered";
	
	public Client() throws MalformedURLException, RemoteException, NotBoundException {
		printService = (PrintServiceInterface) Naming.lookup("rmi://localhost:1099/print");
		authService = (AuthServiceInterface) Naming.lookup("rmi://localhost:1099/auth");
		mySessToken = null;
		System.out.println("Client started. Please login");
	}
	
	
	private void login(String username, String password) throws MalformedURLException, RemoteException, NotBoundException, GeneralSecurityException {
		mySessToken = authService.login(username, password);
		if (mySessToken != null) {
			System.out.println("(client): Logged in successfully");
		}
		else {
			System.out.println("(client): Login unsuccessful");
		}
	}
	
	private void print(String filename, String printer) throws RemoteException {
		String res = printService.print(filename, printer, mySessToken);
		System.out.println("(client): " + res);
	}
	
	private void queue(String printer) throws RemoteException {
		String res = printService.queue(printer, mySessToken);
		System.out.println("(client): " + res);
	}
	public void topQueue(String printer, String job) throws RemoteException {
		String res = printService.topQueue(printer, Integer.parseInt(job), mySessToken);
		System.out.println("(client): " + res);
	}
	private void start() throws RemoteException {
		String res = printService.start(mySessToken);
		System.out.println("(client): " + res);
	}
	private void stop() throws RemoteException {
		String res = printService.stop(mySessToken);
		System.out.println("(client): " + res);
	}
	private void restart() throws RemoteException {
		String res = printService.restart(mySessToken);
		System.out.println("(client): " + res);
	}
	private void status(String printer) throws RemoteException {
		String res = printService.status(printer, mySessToken);
		System.out.println("(client): " + res);
	}
	private void readConfig(String parameter) throws RemoteException {
		String res = printService.readConfig(parameter, mySessToken);
		System.out.println("(client): " + res);
	}
	private void setConfig(String parameter, String value) throws RemoteException {
		String res = printService.setConfig(parameter, value, mySessToken);
		System.out.println("(client): " + res);
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
					if (inSpl.length < 3) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.login(inSpl[1], inSpl[2]);
					break;
					
				case "print":
					if (inSpl.length < 3) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.print(inSpl[1], inSpl[2]); //fileName, printerName
					break;
					
				case "queue":
					if (inSpl.length < 2) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.queue(inSpl[1]);
					break;
				
				case "topQueue":
					if (inSpl.length < 3) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.topQueue(inSpl[1], inSpl[2]); 
					break;
				
				case "start":
					rmc.start();
					break;
					
				case "stop":
					rmc.stop();
					break;
				
				case "restart":
					rmc.restart();
					break;
					
				case "status":
					if (inSpl.length < 2) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.status(inSpl[1]);
					break;
					
				case "readConfig":
					if (inSpl.length < 2) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.readConfig(inSpl[1]);
					break;
				
				case "setConfig":
					if (inSpl.length < 3) {
						System.out.println(LACK_PARAMS_MESSAGE);
						break;
					}
					rmc.setConfig(inSpl[1], inSpl[2]);
					break;
					
				default:
					System.out.println("No such command");
			}
					

		}
		keyb.close();
	}
}



