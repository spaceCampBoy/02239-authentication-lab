package com.labwork;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class PrintService implements PrintServiceInterface {
	
	private AuthService authServ;
	
	//list of printers
	private HashMap<String, Printer> printers;
	
	public PrintService(AuthService authServ) throws RemoteException {
		super();
		UnicastRemoteObject.exportObject(this, 0);
		this.authServ = authServ;
		
		//
		//create list of printers every time????
		//
	}
	
	@Override
	public String print(String filename, String printer, String token) throws RemoteException {

		if (!authServ.checkSessionToken(token)) {
			return "user is not logged in (no valid session token found)";
		}
		else {	
			Printer activePrinter = printers.get(printer);
			activePrinter.print(filename);
			return "'"+filename+"' is printed at '"+printer+"' using valid token '"+token+"'" ;
		}
			
	}



	@Override
	public void queue(String printer) throws RemoteException {
		Printer activePrinter = printers.get(printer);
		activePrinter.getQueue();
	}



	@Override
	public void topQueue(String printer, int job) throws RemoteException {
		Printer activePrinter = printers.get(printer);
		activePrinter.topQueue(job);
	}



	@Override
	public void start() throws RemoteException {
		//		check if service is not running already
		//		set ON status for every printer?
	}



	@Override
	public void stop() throws RemoteException {
		//       check if service is actually running, so there is something to stop
		//		 set OFF status for every printer
		//		 clear queue of every printer  -- maybe not??
		
	}



	@Override
	public void restart() throws RemoteException {
		//check if running atm
		//stop()
		//clear queue of every printer
		//start()
	
	}



	@Override
	public void status(String printer) throws RemoteException {
		Printer activePrinter = printers.get(printer);
		activePrinter.getStatus();
		
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
