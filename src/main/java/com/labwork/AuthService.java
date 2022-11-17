package com.labwork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;


import com.lambdaworks.crypto.SCryptUtil;

public class AuthService implements AuthServiceInterface {

	private HashMap<String, Timestamp> tokenList;
	private HashMap<String,ArrayList<String>> usersAccessOper;
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


	public AuthService() throws RemoteException {
		super();
		tokenList = new HashMap<>();
		usersAccessOper = new HashMap<>();
		UnicastRemoteObject.exportObject(this, 0);
	}

	
	@Override
	public String login(String username, String password) throws RemoteException, GeneralSecurityException  {
		//get the password from file
		String storedPass = returnUserStoredPass(username);

		if ((storedPass == null) || (!SCryptUtil.check(password, storedPass))) {
			System.out.println("(server): Incorrect credentials");	
			return null;
		}
		else {
			System.out.println("(server): User '"+ username +"' is verified");
			
			ArrayList<String> userOperations = returnUserOperations(username);
			String sessToken = createSessionToken();
			
			
			tokenList.put(sessToken, Timestamp.from(Instant.now()));
			usersAccessOper.put(sessToken, userOperations);
			
			return sessToken;
		}
	}


	private String returnUserStoredPass(String username) {
		String hashedUserPass = null;
		try {
			File myObj = new File("./src/main/java/com/labwork/userlist.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] lineSplit = data.split(":");

				if (lineSplit[0].equals(username) ) {
					hashedUserPass = lineSplit[1];
					break;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return hashedUserPass;
	}
	
	
	
	private ArrayList<String> returnUserOperations(String username) {
		ArrayList<String> userOperations = null;
		String userRole = getUserRole(username);
		
		if (userRole != null) {
			userOperations = getRoleOperations(userRole);
		}
		
		return userOperations;
	}
	
	
	private String getUserRole(String username) {
		String userRole = null;
		try {
			File myObj = new File("./src/main/java/com/labwork/user-roles.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] lineSplit = data.split(":");

				if (lineSplit[0].equals(username) ) {
					userRole = lineSplit[1];
					break;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return userRole;
	}
	
	private ArrayList<String> getRoleOperations(String role) {
		ArrayList<String> roleOperations = null;
		try {
			File myObj = new File("./src/main/java/com/labwork/rbac-descr.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] lineSplit = data.split(":");

				if (lineSplit[0].equals(role) ) {
					roleOperations = new ArrayList<String>(Arrays.asList(lineSplit[1].split(",")));
					break;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}	
		return roleOperations;
	}

	public String createSessionToken() {
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		String sessToken =  base64Encoder.encodeToString(randomBytes);
		return sessToken;
	}


	public Boolean checkSessionToken(String token) {
		if (token == null) {
			return false;
		}
		else {
			long milliseconds1 = tokenList.get(token).getTime();
			long milliseconds2 = Timestamp.from(Instant.now()).getTime();
			long diff = milliseconds2 - milliseconds1;
			boolean diffAccept = diff / (60 * 1000) < 1;
			if (diffAccept) {
				tokenList.put(token, Timestamp.from(Instant.now()));
			}
			return tokenList.containsKey(token) && diffAccept;
		}
	}
	
	public Boolean checkRightsAcess(String token, String operName) {
		return usersAccessOper.get(token).contains(operName);
	}
	
	
}

	