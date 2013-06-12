package RestfulWebService;

import com.sun.jersey.api.client.*;

public class AppClient {
	public static void main(String[] args) {
		String url = "http://localhost:1906";

	      url = url + "/Module";

	      WebResource wrs = Client.create().resource(url);

	      System.out.println( wrs.accept("application/xml").get(String.class));
	}
}