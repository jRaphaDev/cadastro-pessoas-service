package br.com.raphael.repository;

import java.util.List;
import java.util.UUID;

import br.com.raphael.model.Pessoa;

public interface PessoaRepository {

	void create(Pessoa pessoa);
	
	Pessoa findById(UUID id) throws Exception;
	
	List<Pessoa> find();
	
	void update(UUID id, Pessoa pessoa) throws Exception;
	
	void delete(UUID id) throws Exception;
	
}
