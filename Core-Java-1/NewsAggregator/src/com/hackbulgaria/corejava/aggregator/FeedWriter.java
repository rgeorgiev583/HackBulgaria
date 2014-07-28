package com.hackbulgaria.corejava.aggregator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedWriter {
    public static void writeFeed(URL rssXmlFileUrl, PrintWriter out, String description) throws IllegalArgumentException, FeedException, IOException {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(rssXmlFileUrl));
		List<SyndEntry> entries = feed.getEntries();
		Iterator<SyndEntry> entriesIterator = entries.iterator();
		
		out.printf("<html>\n\t" +
				  	"<head>\n\t\t<title>Последни новини от днес</title>\n\t</head>\n\t" +
			  		"<body>\n\t\t<h1>Последните новини от %s:</h1>\n\t\t" +
				  		"<ul>\n\t\t", description);
		
		while (entriesIterator.hasNext()) {
			SyndEntry entry = entriesIterator.next();
			out.printf("<li>\n\t\t\t<h2><a href=\"%s\">%s</a></h2>\n\t\t\t%s</li>\n\t\t",
					entry.getLink(), entry.getTitle(), entry.getDescription());
		}
		
		out.print("\n\t</body>\n</html>\n");
    }
}
