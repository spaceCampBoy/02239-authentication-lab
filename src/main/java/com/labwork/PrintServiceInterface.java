package com.labwork;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintServiceInterface extends Remote {
	
	String print(String filename, String printer, String token) throws RemoteException; // prints file filename on the specified printer
	void queue(String printer) throws RemoteException;   // lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
	void topQueue(String printer, int job) throws RemoteException;   // moves job to the top of the queue
	void start() throws RemoteException;   // starts the print server
	void stop() throws RemoteException;   // stops the print server
	void restart() throws RemoteException;   // stops the print server, clears the print queue and starts the print server again
	void status(String printer) throws RemoteException;  // prints status of printer on the user's display
	void readConfig(String parameter) throws RemoteException;   // prints the value of the parameter on the user's display
	void setConfig(String parameter, String value) throws RemoteException;   // sets the parameter to value
}


