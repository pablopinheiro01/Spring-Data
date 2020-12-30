package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Cargo;

//primeiro parametro qual a base que queremos manipular
//segundo parametro qual o tipo do ID
@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{

	
}
