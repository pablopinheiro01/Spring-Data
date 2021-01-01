package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>{
	
	List<Funcionario> findByNome(String nome);
	//este metodo esta em devired query
//	List<Funcionario> findByNameAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);

	//o mesmo metodo acima mas em query fora do padrao deviredquery]
	//para referenciar o parametro de entrada do metodo eu uso :nome do parametro como abaixo
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome and f.salario >= :salario and f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate data);
	
	//vamos executar uma query como e executada no banco de dados mysql
	//e preciso informar ao spring data que esta query e nativa usando o value= e , nativeQuery=true
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	// vamos utilizar no spring data projecao para retornar somente os dados solicitados
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
