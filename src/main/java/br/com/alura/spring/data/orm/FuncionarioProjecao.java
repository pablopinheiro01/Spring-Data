package br.com.alura.spring.data.orm;

//vamos trabalhar com projecao do Spring data para retornar dados especificos de uma consulta
public interface FuncionarioProjecao {
	
	Integer getId();
	String getNome();
	Double getSalario();

}
