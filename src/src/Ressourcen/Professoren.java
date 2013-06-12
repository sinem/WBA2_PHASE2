package Ressourcen;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

@Path("/Professoren")
public class Professoren{
	public XMLworker xmlWork;

    public Professoren() throws JAXBException{
		this.xmlWork = new XMLworker();
	}
    
    
    @GET 
	@Produces(MediaType.APPLICATION_XML)
	public Professoren getProfessoren() throws JAXBException, SAXException{
		
    	return this.xmlWork.unmarshalProfessoren();
	}
}