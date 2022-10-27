package com.labwork;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PrintService implements PrintServiceInterface {
	
	
	public PrintService() throws RemoteException {
		super();
		UnicastRemoteObject.exportObject(this, 0);
	}
	

	@Override
	public String print(String filename, String printer, String token) throws RemoteException {
//		Todo: check if the token is valid. Need to use Auth service for that
//		Todo: find out what exactly has to be done/returned from this func
		System.out.println("(server): "+ "'"+filename+"' is printed at '"+printer+"' using token '"+token+"'");
		return "'"+filename+"' is printed at '"+printer+"' using token '"+token+"'" ;
		
	}



	@Override
	public void queue(String printer) throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void topQueue(String printer, int job) throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void start() throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void restart() throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void status(String printer) throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void readConfig(String parameter) throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setConfig(String parameter, String value) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


}
