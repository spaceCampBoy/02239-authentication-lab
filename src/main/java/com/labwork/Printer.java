package com.labwork;

import java.util.HashMap;
import java.util.Iterator;

public class Printer {
	
	
	private String name;
	private Status stat;
	private HashMap<Integer, String> queue;
	
	private enum Status {
		ON,
		OFF,
		PRINTING
	}
	
	public Printer (String name) {
		this.name = name;
		stat = Status.ON;
		queue = new HashMap<Integer, String>();
	}
	
	private void addToQueue (String filename) {
		queue.put(queue.size()+1, filename);
	}
	
	private void removeFromQueue (String filename) {
		Iterator<HashMap.Entry<Integer, String> > iterator = queue.entrySet().iterator();
		while (iterator.hasNext()) {
			 HashMap.Entry<Integer, String> entry = iterator.next();
			 if (filename.equals(entry.getValue())) {
	                // Remove this entry from HashMap
	                iterator.remove();
	            }
		}
	}
	
	public void clearQueue() {
		queue.clear();
	}
	
	// moves job to the top of the queue
	public void topQueue(int job) {
		HashMap<Integer, String> updatedQueue = new HashMap<Integer, String>();
		
		String filenameOfJob = queue.get(job); //name of file of the given job#
		queue.remove(job); //remove that job from queue
		updatedQueue.put(1,filenameOfJob); //adding given job to the new queue
		for (String value : queue.values()) {
		    updatedQueue.put(updatedQueue.size()+1, value);
		}
		queue.clear();
		queue.putAll(updatedQueue); //putAll needs empty hashmap
	}
	
	public HashMap<Integer, String> getQueue () {
		return queue; 
	}
	
	public String getStatus() {
		return stat.name();
	}
	
	public void setStatus(String s) {
		if (s.equals("ON")) {
			stat = Status.ON;
		}
		if (s.equals("OFF")) {
			stat = Status.OFF;
		}
		
		if (s.equals("PRINTING")) {
			stat = Status.PRINTING;
		}
		
		
	}
	
	private String getName() {
		return name;
	}
	
	public void print(String filename) {
		addToQueue(filename);
		setStatus("PRINTING");
		System.out.println("(server): "+ "'"+filename+"' is printed at '"+ getName() +"' using token '");
		removeFromQueue(filename);
		setStatus("ON");
	}
}
