package com.hackbulgaria.corejava.crawler.parallel;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebsiteCrawler extends Thread {
    public URL currentPageURL;
    public final String needle;
    public final List<URL> visitedURLs;
    public final List<WebpageCrawler> pageCrawlers;
    public final Object monitor;
    public final boolean debug;

	public WebsiteCrawler(URL indexURL, String needle) {
	    this.currentPageURL = indexURL;
        this.needle = needle;
		this.visitedURLs = Collections.synchronizedList(new ArrayList<URL>());
		this.pageCrawlers = new CopyOnWriteArrayList<WebpageCrawler>();
		this.monitor = new Object();
		this.debug = false;
	}
	
    public WebsiteCrawler(URL indexURL, String needle, boolean debug) {
        this.currentPageURL = indexURL;
        this.needle = needle;
        this.visitedURLs = Collections.synchronizedList(new ArrayList<URL>());
        this.pageCrawlers = new CopyOnWriteArrayList<WebpageCrawler>();
        this.monitor = new Object();
        this.debug = debug;
    }
	
	@Override
    public void run() {
        WebpageCrawler initCrawler = new WebpageCrawler(currentPageURL, needle, this);
        
        synchronized (monitor) {
            try {
                initCrawler.start();
                monitor.wait();
            } catch (InterruptedException e) {
                if (debug) {
                    e.printStackTrace();
                } else {
                    System.err.println("ERROR:  Sorry, a thread-related problem occurred.  Halting program execution.");
                }
            }
        }
        
        pageCrawlers.clear();
        for (WebpageCrawler crawler : pageCrawlers) {
            crawler.interrupt();
        }
    }

    public static String findDeep(URL indexURL, String needle) {
		WebsiteCrawler crawler = new WebsiteCrawler(indexURL, needle);
		crawler.start();
		
		try {
            crawler.join();
            return crawler.currentPageURL.toString();
        } catch (InterruptedException e) {
            System.err.println("ERROR:  Sorry, a thread-related problem occurred.  Halting program execution.");
            return null;
        }
	}
    
    public static String findDeep(URL indexURL, String needle, boolean debug) {
        WebsiteCrawler crawler = new WebsiteCrawler(indexURL, needle, debug);
        crawler.start();
        
        try {
            crawler.join();
            return crawler.currentPageURL.toString();
        } catch (InterruptedException e) {
            if (debug) {
                e.printStackTrace();
            } else {
                System.err.println("ERROR:  Sorry, a thread-related problem occurred.  Halting program execution.");
            }
            
            return null;
        }
    }
}
