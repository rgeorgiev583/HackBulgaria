package com.hackbulgaria.corejava.mcpnprinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class MultipleCourseParticipantNamesPrinter {
	public static List<String> getMultipleCourseParticipantNames(JSONArray jsonCoursesDatabase) throws JSONException {
		List<String> multipleCourseParticipantNames = new ArrayList<String>();
		int length = jsonCoursesDatabase.length();
		
		for (int i = 0; i < length; i++) {
			JSONObject courseParticipant = jsonCoursesDatabase.getJSONObject(i);
			
			if (courseParticipant.getJSONArray("courses").length() > 1) {
				multipleCourseParticipantNames.add(courseParticipant.getString("name"));
			}
		}
		
		return multipleCourseParticipantNames;
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
			List<String> multipleCourseParticipantNames =
					getMultipleCourseParticipantNames(new JSONArray(getTextContent(new URL(args[0]))));
			
			for (String courseParticipant : multipleCourseParticipantNames) {
				System.out.println(courseParticipant);
			}
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
