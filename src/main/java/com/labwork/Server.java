package com.labwork;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	
	public static void main(String[] args) throws RemoteException {
		try {
			
			LocateRegistry.createRegistry(1099);

//			Authentication Server
			AuthService authServ = new AuthService();
			Naming.rebind("auth", authServ);	
			
//			Printing jobs Server
			PrintService printServ = new PrintService(authServ);
			Naming.rebind("print", printServ);
			
			System.out.println("Main server started...");
		}
		catch (Exception ex) { ex.printStackTrace(); }	
	}
}
