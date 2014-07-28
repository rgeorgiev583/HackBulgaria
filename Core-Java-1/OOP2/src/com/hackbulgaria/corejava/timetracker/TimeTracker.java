package com.hackbulgaria.corejava.timetracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeTracker {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
	private Map<String, Long> startTimes, endTimes;
	private Map<String, ArrayList<Long>> updateTimes;
	
	private static String getDate(long time) {
		return dateFormat.format(new Date(time));
	}
	
	public TimeTracker() {
		this.startTimes = new HashMap<String, Long>();
		this.endTimes = new HashMap<String, Long>();
		this.updateTimes = new HashMap<String, ArrayList<Long>>();
	}
	
	public void start(String task) {
		startTimes.put(task, System.currentTimeMillis());
	}
	
	public void update(String task) throws Exception {
		if (!startTimes.containsKey(task)) {
			throw new Exception("Task not found!");
		} else {
			if (!updateTimes.containsKey(task)) {
				updateTimes.put(task, new ArrayList<Long>());
			}
			
			updateTimes.get(task).add(System.currentTimeMillis());
		}
	}
	
	public void finish(String task) throws Exception {
		if (!startTimes.containsKey(task)) {
			throw new Exception("Task not found!");
		} else {
			endTimes.put(task, System.currentTimeMillis());
		}
	}
	
	public String getReport(String task) throws Exception {
		if (!startTimes.containsKey(task)) {
			throw new Exception("Task not found!");
		} else {
			String message;
			
			if (!endTimes.containsKey(task)) {
				message = String.format("Operation %s took %d ms so far.\n%s started\n", task,
						System.currentTimeMillis() - startTimes.get(task), getDate(startTimes.get(task)));
			} else {
				message = String.format("Operation %s took %d ms.\n%s started\n%s finished\n", task,
						endTimes.get(task) - startTimes.get(task), getDate(startTimes.get(task)), getDate(endTimes.get(task)));
			}
			
			return message;
		}
	}
	
	public static void main(String[] args) {
		TimeTracker ttr = new TimeTracker();
		ttr.start("wat");
		try {
			Thread.sleep(70000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ttr.update("wat");
			ttr.finish("wat");
			System.out.println(ttr.getReport("wat"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
