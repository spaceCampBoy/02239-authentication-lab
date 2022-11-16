package com.labwork;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintServiceInterface extends Remote {

	String print(String filename, String printer, String token) throws RemoteException; // prints file filename on the specified printer

	String print(String username, String filename, String printer, String token) throws RemoteException;

	String queue(String printer, String token) throws RemoteException;   // lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
	String topQueue(String printer, int job, String token) throws RemoteException;   // moves job to the top of the queue
	String start(String token) throws RemoteException;   // starts the print server
	String stop(String token) throws RemoteException;   // stops the print server
	String restart(String token) throws RemoteException;   // stops the print server, clears the print queue and starts the print server again
	String status(String printer, String token) throws RemoteException;  // prints status of printer on the user's display
	String readConfig(String parameter,String token) throws RemoteException;   // prints the value of the parameter on the user's display
	String setConfig(String parameter, String value, String token) throws RemoteException;   // sets the parameter to value
}