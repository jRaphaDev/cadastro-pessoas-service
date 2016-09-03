package br.com.raphael.service;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.raphael.model.Pessoa;
import br.com.raphael.repository.PessoaRepository;
import br.com.raphael.util.FailureResponseBuilder;
import br.com.raphael.util.StatusException;

public class DefaultPesssoaService implements PessoaService {

	private PessoaRepository pessoaRepository;
	private Validator validator;
	private static final Logger log = LoggerFactory.getLogger(DefaultPesssoaService.class);
	
	@Inject
	public DefaultPesssoaService(PessoaRepository pessoaRepository, Validator validator) {
		this.pessoaRepository = pessoaRepository;
		this.validator = validator;
	}
	
	@Override
	public Response create(UriInfo uriInfo, Pessoa pessoa) {
		try {
			Set<ConstraintViolation<Pessoa>> error = validator.validate(pessoa);
			//get errors
			if (!error.isEmpty()) {
				String errors = ServiceUtil.getErrors(error);
				log.error(errors);
				return new FailureResponseBuilder().
						toResponse(new StatusException(Status.BAD_REQUEST.getStatusCode(), errors));
			}
			//insert
			pessoaRepository.create(pessoa);
			log.debug("Entity was created successfully.");
			URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(pessoa.getId())).build();
			return Response.created(uri).entity(pessoa.getId()).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).
					entity(e.getMessage()).build();
		}
	}

	@Override
	public Response findById(UUID id) {
		try {
			//find by id
			Pessoa pessoa = pessoaRepository.findById(id);
			if (pessoa == null) {
				log.error("Entity was not found.");
				return new FailureResponseBuilder().
						toResponse(new StatusException(Status.NOT_FOUND.getStatusCode(), 
								"Entity was not found."));
			}
			log.debug("Entity was found successffully.");
			return Response.ok(pessoa).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).
					entity(e.getMessage()).build();
		}
	}

	@Override
	public Response find() {
		try {
			//find
			List<Pessoa> list = pessoaRepository.find();
			log.debug("Entity was listed successfully.");
			return Response.ok(list).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).
					entity(e.getMessage()).build();
		}
	}

	@Override
	public Response update(UUID id, Pessoa pessoa) {
		try {
			//update
			pessoaRepository.update(id, pessoa);
			log.debug("Entity was merged.");
			return Response.noContent().build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).
					entity(e.getMessage()).build();
		}
	}

	@Override
	public Response delete(UUID id) {
		try {
			//delete
			pessoaRepository.delete(id);
			log.debug("Entity was removed.");
			return Response.noContent().build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).
					entity(e.getMessage()).build();
		}
	}
	
}
