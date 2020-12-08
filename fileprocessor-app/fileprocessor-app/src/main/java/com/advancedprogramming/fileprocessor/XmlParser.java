package com.advancedprogramming.fileprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {

public void parseXML(String xmlFilePath) {
	
	System.out.println("Parsing XML");
	System.out.println("Xml File "+xmlFilePath);
	try {
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlFilePath);
	doc.getDocumentElement().normalize();
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
	System.out.println("----------------------------");
	NodeList directoryList = doc.getElementsByTagName("LibraryDirectory");
	String directoryPath = "";
	for(int i = 0 ; i< directoryList.getLength(); i++) {
		Node directoryNode = directoryList.item(i);
		 if (directoryNode.getNodeType() == Node.ELEMENT_NODE)
		 {
		    //Print each employee's detail
		    Element dElement = (Element) directoryNode;
		    System.out.println("Directory Name  "    + dElement.getAttribute("name"));
		    String dName =dElement.getAttribute("name");
		    if(i== 0) {
		    	directoryPath = dName;
		    	
		    } else {
		    	
		    	directoryPath = directoryPath + "\\" + dName;
		    	
		    }
		    
		 }
		
		
		
	}
	System.out.println(directoryPath);
	new File(directoryPath).mkdirs();
	NodeList nList = doc.getElementsByTagName("file");
	for (int temp = 0; temp < nList.getLength(); temp++)
	{
	 Node node = nList.item(temp);
	 System.out.println("");    //Just a separator
	 if (node.getNodeType() == Node.ELEMENT_NODE)
	 {
	    //Print each employee's detail
	    Element eElement = (Element) node;
	    System.out.println("File Name  "    + eElement.getAttribute("name"));
	    String fileName = eElement.getAttribute("name");
	    String fileContent = eElement.getTextContent();
	    System.out.println(fileContent);
	    stringToFile(fileContent,directoryPath+"\\"+fileName);
	 }
	}
	} catch(Exception e) {
		
		e.printStackTrace();
	}
	
}

private void stringToFile( String text, String fileName )
{
try
{
   File file = new File( fileName );

   // if file doesnt exists, then create it 
   if ( ! file.exists( ) )
   {
       file.createNewFile( );
   }

   FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
   BufferedWriter bw = new BufferedWriter( fw );
   bw.write( text );
   bw.close( );
}
catch( IOException e )
{
System.out.println("Error: " + e);
e.printStackTrace( );
}
} //End method stringToFile

}
