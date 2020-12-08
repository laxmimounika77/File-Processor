package com.advancedprogramming.fileprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Download File From The Server", urlPatterns = { "/parseXml" })
public class XmlParserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static int BUFFER_SIZE = 1024 * 100;
	public static final String UPLOAD_DIR = "uploadedFiles";
	
	XmlParser xmlParser = new XmlParser();
	/***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/***** Get The Absolute Path Of The File To Be Downloaded *****/
		String fileName = request.getParameter("fileName"),
				applicationPath = getServletContext().getRealPath(""),
				downloadPath = applicationPath + File.separator + UPLOAD_DIR,
				filePath = downloadPath + File.separator + fileName;

		File file = new File(filePath);
		if (file.exists()) {
				
			xmlParser.parseXML(filePath);
			
			
		} else {

			/***** Set Response Content Type *****/
			response.setContentType("text/html");

			/***** Print The Response *****/
			response.getWriter().println("<h3>File "+ fileName +" Is Not Present .....!</h3>");
		}
	}
}