package com.hackbulgaria.corejava.crawler.parallel;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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

	public WebsiteCrawler(URL indexURL, String needle) {
	    this.currentPageURL = indexURL;
        this.needle = needle;
		this.visitedURLs = Collections.synchronizedList(new ArrayList<URL>());
		this.pageCrawlers = new CopyOnWriteArrayList<WebpageCrawler>();
		this.monitor = new Object();
	}
	
	@Override
    public void run() {
        WebpageCrawler initCrawler = new WebpageCrawler(currentPageURL, needle, this);
        
        synchronized (monitor) {
            try {
                initCrawler.start();
                monitor.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
	}
	
	public static void main(String[] args) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        long startTime = System.currentTimeMillis();
        System.err.printf("Started at %s...\n", dateFormat.format(startTime));
		try {
            System.out.println(findDeep(new URL(args[0]), args[1]));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.err.printf("Finished at %s.\n", dateFormat.format(endTime));
        System.err.printf("Time elapsed (ms): %d\n", endTime - startTime);
	}
}
