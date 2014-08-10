package com.hackbulgaria.corejava.crawler.parallel;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

public class WebsiteNavigator {
    public URL currentPageURL;
	public final URL indexURL;
    public final List<URL> visitedURLs;
    public final boolean debug;

	public WebsiteNavigator(URL indexURL) {
        this.currentPageURL = indexURL;
		this.indexURL = indexURL;
		this.visitedURLs = new ArrayList<URL>();
		this.debug = false;
	}

    public WebsiteNavigator(URL indexURL, boolean debug) {
        this.currentPageURL = indexURL;
        this.indexURL = indexURL;
        this.visitedURLs = new ArrayList<URL>();
        this.debug = debug;
    }

    public URL getCurrentPageURL() {
        return currentPageURL;
    }
	
	public URL getIndexURL() {
		return indexURL;
	}

	public void goToPage(URI pageURI) throws MalformedURLException, URISyntaxException {
		currentPageURL = currentPageURL.toURI().resolve(pageURI).toURL();
	}
	
	public void goToIndex() {
		currentPageURL = indexURL;
	}
    
    public void goToMatchingLinkURL(String needle) {
        WebsiteCrawler crawler = new WebsiteCrawler(currentPageURL, needle, debug);
        crawler.start();
        
        try {
            crawler.join();
            currentPageURL = crawler.currentPageURL;
            visitedURLs.addAll(crawler.visitedURLs);
        } catch (InterruptedException e) {
            if (debug) {
                e.printStackTrace();
            } else {
                System.err.println("ERROR:  Sorry, a thread-related problem occurred.  Halting program execution.");
            }
        }
    }
}
