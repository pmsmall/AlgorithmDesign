package com.mine.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlHelper {
	static DocumentBuilder builder;
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	public static Document readXML(File file) throws ParserConfigurationException, SAXException, IOException {
		builder = factory.newDocumentBuilder();
		return builder.parse(file);
	}

	public static Document getDefaultXML() throws ParserConfigurationException, SAXException, IOException {
		builder = factory.newDocumentBuilder();
		return builder.newDocument();
	}

	public static void saveXML(Document document, File file) throws FileNotFoundException, TransformerException {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transform = tFactory.newTransformer();
		transform.setOutputProperty(OutputKeys.ENCODING, "utf8");
		transform.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(document);
		PrintWriter pw = new PrintWriter(new FileOutputStream(file));
		StreamResult result = new StreamResult(pw);
		transform.transform(source, result);
	}
}
