package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeDeTrabalhoService;
import br.com.alura.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private Boolean system = true;

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeService;
	private final RelatoriosService relatorioService;
	
	//injeto a dependencia do obeto repository
	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeDeTrabalhoService unidadeService, RelatoriosService relatorioService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
		this.relatorioService = relatorioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	//executado apos a finalizacao do main
	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação você quer executar ?");
			System.out.println(" 0 - Sair");
			System.out.println(" 1 - Cargo");
			System.out.println(" 2 - Funcionario ");
			System.out.println(" 3 - Unidade de Trabalho");
			System.out.println(" 4 - Relatorio");
			
			int action = scanner.nextInt();
			
			switch(action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2: 
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			default:
				break;
			}
		}
		
	}

}
