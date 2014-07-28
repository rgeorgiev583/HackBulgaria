package com.hackbulgaria.corejava.mfpprinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class MostFrequentParticipantsPrinter {
	public static Map<String, Integer> getMostFrequentParticipants(JSONArray jsonCoursesDatabase) throws JSONException {
		Map<String, Integer> mostFrequentParticipants = new HashMap<String, Integer>();
		int length = jsonCoursesDatabase.length();
		
		for (int i = 0; i < length; i++) {
			JSONObject courseParticipant = jsonCoursesDatabase.getJSONObject(i);
			String participantName = courseParticipant.getString("student_name");
			
			if (mostFrequentParticipants.containsKey(participantName)) {
				mostFrequentParticipants.put(participantName, mostFrequentParticipants.get(participantName) + 1);
			} else {
				mostFrequentParticipants.put(participantName, 0);
			}
		}
		
		return mostFrequentParticipants;
	}
	
	private static String getTextContent(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		if (connection.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				response.append("\n");
			}
			
			in.close();
			return response.toString();
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		try {
			Map<String, Integer> mostFrequentParticipants =
					getMostFrequentParticipants(new JSONArray(getTextContent(new URL(args[0]))));
			ValueComparator mostFrequentParticipantsComparator = new ValueComparator(mostFrequentParticipants);
			SortedMap<String, Integer> mostFrequentParticipantsSorted =
					new TreeMap<String, Integer>(mostFrequentParticipantsComparator);
			mostFrequentParticipantsSorted.putAll(mostFrequentParticipants);
			Iterator<Entry<String, Integer> > mostFrequentParticipantsSortedIterator =
					mostFrequentParticipantsSorted.entrySet().iterator();
			
			while (mostFrequentParticipantsSortedIterator.hasNext()) {
				Entry<String, Integer> participant = mostFrequentParticipantsSortedIterator.next();
				System.out.println(String.format("%s: %s", participant.getKey(), participant.getValue()));
			}
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
