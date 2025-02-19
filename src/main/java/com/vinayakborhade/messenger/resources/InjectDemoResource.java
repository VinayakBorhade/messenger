package com.vinayakborhade.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	
	@GET
	@Path("/context")
	public String getParamsUsingContext(
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers
			) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "path: " + path + " cookies " + cookies;
	}
}
