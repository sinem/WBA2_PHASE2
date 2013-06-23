package Ressourcen;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

@Path("/Nachrichten")
public class Nachrichten{
	public XMLworker xmlWork;

    public Nachrichten() throws JAXBException{
		this.xmlWork = new XMLworker();
	}
    
    
    @GET 
	@Produces(MediaType.APPLICATION_XML)
	public Nachrichten getNachricht() throws JAXBException, SAXException{
		return this.xmlWork.unmarshalNachrichten();
	}


	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	public String postNachrichten(
			@QueryParam("nachricht") String nachricht) {

		if (nachricht == null) {
			throw new IllegalArgumentException("Nachricht darf nicht leer sein.");
		}
		
		return nachricht;
	}


@DELETE
@Path("/delete")
public Nachrichten deleteNachrichten() {
	
	return null;

	}
}
