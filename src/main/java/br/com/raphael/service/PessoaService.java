package br.com.raphael.service;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.raphael.model.Pessoa;

@Path("/pessoa")
public interface PessoaService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response create(@Context UriInfo uriInfo, Pessoa pessoa);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Response findById(@PathParam("id") UUID id);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response find();
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Response update(@PathParam("id") UUID id, Pessoa pessoa);
	
	@DELETE
	@Path("/{id}")
	Response delete(@PathParam("id") UUID id);
	
}
