package com.labwork;

import java.util.HashMap;

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
	
	private String getName() {
		return name;
	}
	
	public void print(String filename) {
		addToQueue(filename);
		System.out.println("(server): "+ "'"+filename+"' is printed at '"+ getName() +"' using token '");
	}
}
