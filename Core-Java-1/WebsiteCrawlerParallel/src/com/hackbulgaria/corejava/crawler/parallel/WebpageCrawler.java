package com.hackbulgaria.corejava.crawler.parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebpageCrawler extends Thread {
    private final URL pageURL;
    private final String needle;
    private final WebsiteCrawler websiteCrawler;
    
    public WebpageCrawler(URL pageURL, String needle, WebsiteCrawler websiteCrawler) {
        this.pageURL = pageURL;
        this.needle = needle;
        this.websiteCrawler = websiteCrawler;
        this.websiteCrawler.pageCrawlers.add(this);
    }

    public URL getPageURL() {
        return pageURL;
    }

    public String getNeedle() {
        return needle;
    }
    
    public WebsiteCrawler getWebsiteCrawler() {
        return websiteCrawler;
    }

    private static synchronized String getContent(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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

    public void goToFirstMatchingLinkURL() throws IOException, URISyntaxException {
        websiteCrawler.visitedURLs.add(pageURL);
        /// <debug>
        //System.err.println(pageURL);
        /// </debug>
        String pageContent = getContent(pageURL);
        
        if (pageContent != null) {
            if (pageContent.indexOf(needle) != -1) {
                websiteCrawler.currentPageURL = pageURL;
                
                synchronized (websiteCrawler.monitor) {
                    websiteCrawler.monitor.notify();
                }
                //
            } else {
                List<URL> pageLinkURLs = getAllLinkURLs(pageURL);
                
                if (pageLinkURLs != null && !pageLinkURLs.isEmpty()) {
                    for (URL linkURL : pageLinkURLs) {
                        if (!websiteCrawler.visitedURLs.contains(linkURL)) {
                            new WebpageCrawler(linkURL, needle, websiteCrawler).start();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            goToFirstMatchingLinkURL();
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
