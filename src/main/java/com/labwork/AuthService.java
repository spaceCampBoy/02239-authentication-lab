package com.labwork;

import java.lang.reflect.Array;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AuthService implements AuthServiceInterface {

	private ArrayList<String> tokenList;
	
	public AuthService() throws RemoteException {
		super();
		tokenList = new ArrayList<String>();
		UnicastRemoteObject.exportObject(this, 0);
	}
	 
	
	
	@Override
	public String login(String username, String password) throws RemoteException {
		
		// Todo: VERIFY LOGIN AND PASSWORD
		boolean verified = true;
		if (verified) {
			System.out.println("(server): user '"+username+"' is verified");
			return createSessionToken();
		}
		else {
			return null;
		}
	}

	
	
	public String createSessionToken() {
		//Todo: GENERATE SESSION TOKEN
		String sessToken =  "sessionTokenForUser1";
		tokenList.add(sessToken);
		return sessToken;
	}
}

	