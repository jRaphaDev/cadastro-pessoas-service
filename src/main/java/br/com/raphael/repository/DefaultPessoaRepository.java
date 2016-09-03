package br.com.raphael.repository;

import java.util.List;
import java.util.UUID;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.raphael.model.Pessoa;

@Named
@Transactional
public class DefaultPessoaRepository implements PessoaRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	private static final Logger log = LoggerFactory.getLogger(PessoaRepository.class);
		
	@Override
	public void create(Pessoa pessoa) {
		try {
			entityManager.persist(pessoa);
			log.debug("Repository : Entity created successfully.");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
	}

	@Override
	public Pessoa findById(UUID id) throws Exception {
		try {
			Pessoa pessoa = entityManager.find(Pessoa.class, id);
			if (pessoa == null) {
				log.error("Repository : Entity was not found.");
				throw new Exception();
			}
			log.debug("Repository : Entity was found successfully.");
			return pessoa;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Pessoa> find() {
		try {
			Query query = entityManager.createQuery("FROM Pessoa");
			@SuppressWarnings("unchecked")
			List<Pessoa> list = query.getResultList();
			log.debug("Repository : Entity was listed successfully.");
			return list;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void update(UUID id, Pessoa pessoa) throws Exception {
		try {
			findById(id);
			if (!id.equals(pessoa.getId())) {
				log.error("Repository : id doesn't belongs to entity");
				throw new Exception();
			}
			log.debug("Repository : Entity was merge successfully.");
			entityManager.merge(pessoa);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void delete(UUID id) throws Exception {
		try {
			Pessoa pessoa = findById(id);
			log.debug("Repository : Entity was removed successfully.");
			entityManager.remove(pessoa);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
