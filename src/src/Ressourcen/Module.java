package Ressourcen;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;




@Path("/Module")
public class Module{
	public XMLworker xmlWork;

    public Module() throws JAXBException{
		this.xmlWork = new XMLworker();
	}
    
    
    @GET 
	@Produces("application/xml")
	public Module getModul() throws JAXBException, SAXException{
    	return this.xmlWork.unmarshalModul();
	}

	@GET 
	@Path("/Module/{id}")
	@Produces("application/xml")
	public Module getModul(@PathParam("id") int S_id) throws JAXBException, SAXException{

		Module mod = new Module();

		Module returnModul = new Module();
		Module mdl = this.xmlWork.unmarshalModul();

	
				return mdl;
	}
	
	@POST
	@Path("/Module/add")
	@Consumes(MediaType.APPLICATION_XML)
	public String postModule(
			@QueryParam("modul") String modul) {

		if (modul == null) {
			throw new IllegalArgumentException("Modulbezeichnung darf nicht leer sein.");
		}
		
		return modul;
	}

}
