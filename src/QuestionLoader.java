import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QuestionLoader {
	public static List<QuestionData> getQuestions(String questionFile) {
		List<QuestionData> questionList = new LinkedList<QuestionData>();
		String string;
		String query;
		
		try {
			// Setup the parser
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			// Read the XML file
			File inputFile = new File(questionFile);
			InputStream inputStream = new FileInputStream(inputFile);

			// Parse the XML file
			Document doc = builder.parse(inputStream);

			// Create an XPath instance
			XPath xPath = XPathFactory.newInstance().newXPath();

			// Evaluate simple expressions
			String stringExpr = "/dataset/question/string";
			String queryExpr = "/dataset/question/query";
			NodeList stringList = (NodeList)xPath.compile(stringExpr).evaluate(doc, XPathConstants.NODESET);
			NodeList queryList = (NodeList)xPath.compile(queryExpr).evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < stringList.getLength(); i++) {
				string = stringList.item(i).getTextContent().trim();
				query = queryList.item(i).getTextContent().trim();
				questionList.add(new QuestionData(string, query));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return questionList;
	}
	
	public static void main(String[] args) {
		String file = "question/qald-4_biomedical_train.xml";
		List<QuestionData> questions = QuestionLoader.getQuestions(file);
		
		for(QuestionData data : questions) {
			System.out.println( data.getString() );
			System.out.println( data.getSparqlQuery() );
		}
	}
}