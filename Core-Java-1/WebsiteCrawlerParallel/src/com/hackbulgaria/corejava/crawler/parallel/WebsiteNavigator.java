package com.hackbulgaria.corejava.crawler.parallel;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class WebsiteNavigator {
	public final URL indexURL;
    public final List<URL> visitedURLs;	
    public URL currentPageURL;

	public WebsiteNavigator(URL indexURL) {
		this.indexURL = indexURL;
		this.currentPageURL = indexURL;
		this.visitedURLs = new ArrayList<URL>();
	}
	
	public URL getIndexURL() {
		return indexURL;
	}

	public URL getCurrentPageURL() {
		return currentPageURL;
	}

	public void goToPage(URI pageURI) throws MalformedURLException, URISyntaxException {
		currentPageURL = currentPageURL.toURI().resolve(pageURI).toURL();
	}
	
	public void goToIndex() {
		currentPageURL = indexURL;
	}
    
    public void goToMatchingLinkURL(String needle) {
        WebsiteCrawler crawler = new WebsiteCrawler(currentPageURL, needle);
        crawler.start();
        
        try {
            crawler.join();
            currentPageURL = crawler.currentPageURL;
            visitedURLs.addAll(crawler.visitedURLs);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            long startTime = System.currentTimeMillis();
            System.err.printf("Started at %s...\n", dateFormat.format(startTime));
            WebsiteNavigator navigator;
            navigator = new WebsiteNavigator(new URL(args[0]));
            navigator.goToMatchingLinkURL(args[1]);
    		System.out.println(navigator.currentPageURL);
            long endTime = System.currentTimeMillis();
            System.err.printf("Finished at %s.\n", dateFormat.format(endTime));
            System.err.printf("Time elapsed (ms): %d\n", endTime - startTime);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
