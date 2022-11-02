package com.labwork;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;

public interface AuthServiceInterface extends Remote {
	String login(String username, String password) throws RemoteException, GeneralSecurityException ;  //returns string which is a SessionToken
}
