package io.openliberty.guides.inventory.filter;

import java.util.Collections;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.info.License;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;

public class InventoryOASFilter implements OASFilter {

	@Override
	public APIResponse filterAPIResponse( APIResponse apiResponse ) {
		if( "Missing description".equals(apiResponse.getDescription()) ) {
			apiResponse.setDescription("Invalid hostname or the system may not be running on the particular host.");
		}
		return apiResponse;
	}
	
	@Override
	public void filterOpenAPI( OpenAPI openAPI ) {
		openAPI.setInfo(
				OASFactory.createObject(Info.class)
				.title("Inventory App")
				.version("1.0")
				.description("App for storing JVM system properties of various hosts.")
				.license(OASFactory.createObject(License.class)
							.name("Eclipse Public License - v 1.0")
							.url("https://www.eclipse.org/legal/epl-v10.html"))
		);
		openAPI.addServer(
				OASFactory.createServer()
					.url("http://localhost:{port}")
					.description("Simple Open Liberty")
					.variables(Collections.singletonMap("port", 
							OASFactory.createServerVariable()
							.defaultValue("9080")
							.description("Server HTTP port.")))
		);
	}
	
}
