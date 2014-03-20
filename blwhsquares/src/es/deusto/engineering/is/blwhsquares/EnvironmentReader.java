package es.deusto.engineering.is.blwhsquares;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.engineering.is.blwhsquares.Environment.Square;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;
/**
 * to conform the environment
 */
public class EnvironmentReader extends StateXMLReader {

	//the initial problem environment state
	private State environment;
	
	/**
	 * @param xml file path
	 */
	public EnvironmentReader(String fileXML) {
		super(fileXML);
	}

	public State getState() {
		return environment;
	}

	public void startElement(String arg0, String arg1, String qname,
			Attributes attrs) throws SAXException {
		if(qname.equals("is:lineofsquares")){
			this.environment = new Environment(Integer.parseInt(attrs.getValue("length")));
		}
		else if(qname.equals("is:white")){
			((Environment)this.environment).addSquare(Square.WHITE);
		}
		else if(qname.equals("is:black")){
			((Environment)this.environment).addSquare(Square.BLACK);
		}

	}

}
