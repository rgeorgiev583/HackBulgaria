package com.hackbulgaria.corejava.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCrawler {
	private URL indexURL, currentPageURL;
	private List<URL> visitedURLs;
	
	public WebsiteCrawler(URL indexURL) {
		this.indexURL = indexURL;
		currentPageURL = indexURL;
		visitedURLs = new ArrayList<URL>();
	}
	
	public URL getIndexURL() {
		return indexURL;
	}

	public URL getCurrentPageURL() {
		return currentPageURL;
	}

	public void goToPage(URI pageURI) throws MalformedURLException, URISyntaxException {
		currentPageURL = currentPageURL.toURI().resolve(pageURI).toURL();
		visitedURLs.clear();
	}
	
	public void goToIndex() {
		currentPageURL = indexURL;
		visitedURLs.clear();
	}
	
	private static String getContent(URL url) throws IOException {
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
	
	public static List<URL> getAllLinkURLs(URL pageURL) throws IOException, URISyntaxException {
		ArrayList<URL> pageLinkList = new ArrayList<>();
		String htmlLinkRegex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern htmlLinkPattern = Pattern.compile(htmlLinkRegex);
		String pageContent = getContent(pageURL);
		
		if (pageContent != null) {
			Matcher pageLinkMatcher = htmlLinkPattern.matcher(pageContent);
			
			while (pageLinkMatcher.find()) {
				URI matchingURI = new URI(pageLinkMatcher.group(1));
				URL resolvedURL = null;
				
				if (matchingURI.isAbsolute() && matchingURI.getHost() == pageURL.getHost()) {
					resolvedURL = matchingURI.toURL();
				} else if (!matchingURI.isAbsolute()) {
					resolvedURL = pageURL.toURI().resolve(matchingURI).toURL();
				}
				
				if (resolvedURL != null)
					pageLinkList.add(resolvedURL);
			}
			
			return pageLinkList;
		} else {
			return null;
		}
	}

	public void goToFirstMatchingLinkURL(URL pageURL, String needle) throws IOException, URISyntaxException {
		visitedURLs.add(pageURL);
		/// <debug>
		//System.err.println(pageURL);
		/// </debug>
		String pageContent = getContent(pageURL);
		
		if (pageContent != null) {
			if (pageContent.indexOf(needle) != -1) {
				currentPageURL = pageURL;
			} else {
				List<URL> pageLinkURLs = getAllLinkURLs(pageURL);
				
				if (pageLinkURLs != null && !pageLinkURLs.isEmpty()) {
					for (URL linkURL : pageLinkURLs) {
						if (!visitedURLs.contains(linkURL)) {
							goToFirstMatchingLinkURL(linkURL, needle);
						}
					}
				}
			}
		}
	}
	
	public void goToMatchingLinkURL(String needle) throws IOException, URISyntaxException {
		goToFirstMatchingLinkURL(currentPageURL, needle);
	}
	
	
	public static String findDeep(URL indexURL, String needle) throws IOException, URISyntaxException {
		WebsiteCrawler crawler = new WebsiteCrawler(indexURL);
		crawler.goToMatchingLinkURL(needle);
		return crawler.getCurrentPageURL().toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(findDeep(new URL(args[0]), args[1]));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
