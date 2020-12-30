package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private Boolean system = true;

	private final CrudCargoService cargoService;
	
	//injeto a dependencia do obeto repository
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
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
			
			int action = scanner.nextInt();
			
			if(action == 1) {
				cargoService.inicial(scanner);
				
			}else {
				system = false;
			}
		}
		
	}

}
