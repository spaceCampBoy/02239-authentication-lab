package com.labwork;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class logWriter {
	
	public void out(String methodname , String username, String token, String comment) {
	 	Path path = Paths.get("logfile.txt");
	 	long lines = 0;
	 	try {
			lines = Files.lines(path).count();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	 	
	    FileWriter myWriter;
		try {
			myWriter = new FileWriter("logfile.txt", true);
			myWriter.write((lines-2)+"   |   " +new Date() + "   |   " + methodname +"|   " + username+"   |   "+token +"   |   "+ comment+ "\n");
			myWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //true tells to append data.
	  
}
}
