
public class QuestionData {
	private String m_string;
	private String m_sparqlQuery;
	
	public QuestionData() {
		// empty body
	}
	
	public QuestionData(String string, String sparqlQuery) {
		m_string = string;
		m_sparqlQuery = sparqlQuery;
	}
	
	public String getString() {
		return m_string;
	}
	
	public void setString(String string) {
		m_string = string;
	}
	
	public String getSparqlQuery() {
		return m_sparqlQuery;
	}
	
	public void setSparqlQuery(String sparqlQuery) {
		m_sparqlQuery = sparqlQuery;
	}
}
