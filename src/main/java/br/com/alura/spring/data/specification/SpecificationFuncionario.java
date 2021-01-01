package br.com.alura.spring.data.specification;


import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.orm.Funcionario;

//classe de tratamento de specification com SpringData
public class SpecificationFuncionario {
	
	public static Specification<Funcionario> nome(String nome){
		//nao preciso criar os atributos de root como utilizado no jpa
		
		return(root,criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%"+ nome +"%");
	}
	
	//mudei apenas a operacao usando equal
	public static Specification<Funcionario> cep(String cep){
		return(root,criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("cep"), cep);
	}
	
	//mudei apenas a operacao usando greatherThan
	public static Specification<Funcionario> salario(Double salario){
		return(root,criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.get("nome"),salario);
	}
	
	public static Specification<Funcionario> dataContratacao(LocalDate data){
		return(root,criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.get("dataContratacao"),data);
	}
	
}
