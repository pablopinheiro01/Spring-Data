package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Caso queira ignorar digite NULL no campo solicitado");
		System.out.println("Digite um nome:");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("---------------------");
		
		System.out.println("digite o CEP");
		String cep = scanner.next();
		
		if(cep.equalsIgnoreCase("NULL")) {
			cep = null;
		}
		
		System.out.println("---------------------");
		
		System.out.println("digite a data de contratacao");
		String data = scanner.next();
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		System.out.println("---------------------");
		
		System.out.println("digite o salario");
		System.out.println("Caso queira ignorar digite 0");
		Double salario = scanner.nextDouble();
		
		if(salario == 0) {
			salario = null;
		}
		
		System.out.println("Realizando pesquisa com os valores: ");
		System.out.println(" "+nome+" "+data+" "+salario+" "+cep);
		
		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(
						SpecificationFuncionario.nome(nome)
						.or(SpecificationFuncionario.cep(cep))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						));
		funcionarios.forEach(System.out::println);
	}
	
}
