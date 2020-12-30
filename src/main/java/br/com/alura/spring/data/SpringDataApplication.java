package br.com.alura.spring.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CargoRepository repository;
	
	//injeto a dependencia do obeto repository
	public SpringDataApplication(CargoRepository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	//executado apos a finalizacao do main
	@Override
	public void run(String... args) throws Exception {
		//apos a injecao de dependencia eu crio um cargo
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor de Software");
		
		repository.save(cargo);
	}

}
