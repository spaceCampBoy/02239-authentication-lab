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
	private HashMap<String, String> config;
	private static final String NOTAUTH_MESSAGE = "User is not authenticated. Please log in";
	private static final String ACT_SERVICE_MESSAGE = "Service is already running";
	private static final String NOACT_SERVICE_MESSAGE = "Service is not running right now";
	private static final String NOACCESS_RIGHTS = "Denied! User does not have rights to call this operation";
	
	private boolean PrintServiceIsActive;
	
	public PrintService(AuthService authServ) throws RemoteException {
		super();
		UnicastRemoteObject.exportObject(this, 0);
		this.authServ = authServ;		
		printers = new HashMap<>();
		config = new  HashMap<String, String>();
		printers.put("printer1", new Printer("printer1"));
		printers.put("printer2", new Printer("printer2"));
		PrintServiceIsActive = true;
	}
	
	@Override
	public String print(String filename, String printer, String token) throws RemoteException {

		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		if (!authServ.checkRightsAcess(token, "print")) {
			return NOACCESS_RIGHTS;
		}
	
		Printer activePrinter = printers.get(printer);
		activePrinter.print(filename);
		return "'"+filename+"' is printed at '" + printer;
		
			
	}



	@Override
	public String queue(String printer, String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		
		if (!authServ.checkRightsAcess(token, "queue")) {
			return NOACCESS_RIGHTS;
		}
		
		Printer activePrinter = printers.get(printer);
		return activePrinter.getQueue().toString();
		
	}



	@Override
	public String topQueue(String printer, int job, String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		if (!authServ.checkRightsAcess(token, "topQueue")) {
			return NOACCESS_RIGHTS;
		}
		
		Printer activePrinter = printers.get(printer);
		if (activePrinter.getQueue().size() < job) {
			return "No such job number. Queue has " + activePrinter.getQueue().size() +" jobs";
		}
		else {
			activePrinter.topQueue(job);
			return "Print job " + job + " is moved to top of the queue at " + printer;
		}
		
		
	}



	@Override
	public String start(String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		if (!authServ.checkRightsAcess(token, "start")) {
			return NOACCESS_RIGHTS;
		}
		if (PrintServiceIsActive) {
			return ACT_SERVICE_MESSAGE; 
		}
		for (Printer printer : printers.values()) {
		    printer.setStatus("ON");
		}
		PrintServiceIsActive = true;
		return "Print service is running";
	}



	@Override
	public String stop(String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		if (!authServ.checkRightsAcess(token, "stop")) {
			return NOACCESS_RIGHTS;
		}
		if (!PrintServiceIsActive) {
			return NOACT_SERVICE_MESSAGE; 
		}
		for (Printer printer : printers.values()) {
		    printer.setStatus("OFF");
		}
		PrintServiceIsActive = false;
		return "Print service is stopped";
	}



	@Override
	public String restart(String token) throws RemoteException {
		
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		if (!authServ.checkRightsAcess(token, "restart")) {
			return NOACCESS_RIGHTS;
		}
		if (!PrintServiceIsActive) {
			return NOACT_SERVICE_MESSAGE; 
		}
		for (Printer printer : printers.values()) {
			printer.clearQueue();
		    printer.setStatus("ON");
		}
		PrintServiceIsActive = true;
		return "Print service is restarted";
	}



	@Override
	public String status(String printer, String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		if (!authServ.checkRightsAcess(token, "status")) {
			return NOACCESS_RIGHTS;
		}
		
		Printer activePrinter = printers.get(printer);
		return activePrinter.getStatus();
		
	}



	@Override
	public String readConfig(String parameter, String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		else if (!authServ.checkRightsAcess(token, "readConfig")) {
			return NOACCESS_RIGHTS;
		}
		else {
			return "Value of parameter '" + parameter + "' is '" + config.get(parameter) + "'";
		}
		
	}



	@Override
	public String setConfig(String parameter, String value, String token) throws RemoteException {
		if (!authServ.checkSessionToken(token)) {
			return NOTAUTH_MESSAGE;
		}
		else if (!authServ.checkRightsAcess(token, "setConfig")) {
			return NOACCESS_RIGHTS;
		}
		else {
			config.put(parameter, value);
			return "Value '" + value + "' is set for parameter '" + parameter + "'";
		}
	}

}
