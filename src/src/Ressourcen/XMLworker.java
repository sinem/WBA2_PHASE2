package Ressourcen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;


public class XMLworker {
	JAXBContext module;
	JAXBContext professoren;
	JAXBContext nachrichten;

	/**
	 * Kontext fuer marshal und unmarshall
	 * @throws JAXBException
	 */
	public XMLworker() throws JAXBException{
		this.module = JAXBContext.newInstance(generated.Module.class);
		this.nachrichten = JAXBContext.newInstance(generated.Nachrichten.class);
		this.professoren = JAXBContext.newInstance(generated.Professoren.class);
		
	}


	/**
	 * Methode zum Auslesen aus der App-XML-Datei
	 * @return module
	 * @throws JAXBException
	 */
	public Module unmarshalModul() throws JAXBException{
		Unmarshaller unmarshal = module.createUnmarshaller();
		Module module = (Module) unmarshal.unmarshal(new File("src/XSD_XML/Module.xml"));

		return module;
	}

	/**
	 * Methode zum Schreiben in die App-XML-Datei
	 * @param module
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalModul(Module modul) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/XSD_XML/Nachrichten.xsd"));
		Marshaller marshal = module.createMarshaller();
		marshal.setSchema(schema);
        marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/XSD_XML/Nachrichten.xml");
		marshal.marshal(modul, file);
	}


	/**
	 * Methode zum Auslesen aus der App-XML-Datei
	 * @return Nachrichten
	 * @throws JAXBException
	 */
	public Nachrichten unmarshalNachrichten() throws JAXBException{
		Unmarshaller unmarshal = nachrichten.createUnmarshaller();
		Nachrichten nachrichten = (Nachrichten) unmarshal.unmarshal(new File("src/XSD_XML/Nachrichten.xml"));

		return nachrichten;
	}

	/**
	 * Methode zum Schreiben in die App-XML-Datei
	 * @param Nachrichtenliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalNachrichten(Nachrichten nachrichtenliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/XSD_XML/Nachrichten.xsd"));
		Marshaller marshal = nachrichten.createMarshaller();
		marshal.setSchema(schema);
		marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/XSD_XML/Nachrichten.xml");
		marshal.marshal(nachrichtenliste, file);
	}

	/**
	 * Methode zum Auslesen aus der App-XML-Datei
	 * @return Professoren
	 * @throws JAXBException
	 */
	public Professoren unmarshalProfessoren() throws JAXBException{
		Unmarshaller unmarshal = professoren.createUnmarshaller();
		Professoren professoren = (Professoren) unmarshal.unmarshal(new File("src/XSD_XML/Professoren.xml"));

		return professoren;
	}

	/**
	 * Methode zum Schreiben in die App-XML-Datei
	 * @param Professoren
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalProfessoren(Professoren professorenliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/XSD_XML/Professoren.xsd"));
		Marshaller marshal = professoren.createMarshaller();
		marshal.setSchema(schema);
		marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/XSD_XML/Professoren.xml");
		marshal.marshal(professorenliste, file);
	}
}