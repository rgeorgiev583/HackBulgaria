package com.hackbulgaria.corejava.aggregator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.io.FeedException;

/**
 * Servlet implementation class SimpleNewsAggregator
 */
@WebServlet(description = "A simple RSS-based news feed aggregator.", urlPatterns = { "/*" })
public class SimpleNewsAggregator extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SimpleNewsAggregator() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final URL generalBulgariaNewsFeedUrl = new URL("http://www.dnevnik.bg/rss/");
		final URL technologyNewsFeedUrl = new URL("http://www.kaldata.com/rosebud/rss.php");
		final URL sportNewsFeedUrl = new URL("http://www.sportal.bg/uploads/rss_category_2.xml");
		
		response.setContentType("text/html");
		
		try (PrintWriter out = response.getWriter()) {
			switch (request.getPathInfo()) {
			case "/":
			case "/general":
			case "/bulgaria":
				FeedWriter.writeFeed(generalBulgariaNewsFeedUrl, out, "България");
			case "/technology":
				FeedWriter.writeFeed(technologyNewsFeedUrl, out, "технологии");
			case "/sport":
				FeedWriter.writeFeed(sportNewsFeedUrl, out, "спорт");
			}
		} catch (IllegalArgumentException | FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
