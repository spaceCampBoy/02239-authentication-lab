package com.labwork;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthServiceInterface extends Remote {
	String login(String username, String password) throws RemoteException;  //returns string which is a SessionToken
}
