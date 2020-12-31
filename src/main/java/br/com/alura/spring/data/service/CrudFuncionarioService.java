package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository repo;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, 
			CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.repo = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de cargo deseja executar ?");
			System.out.println("0 - Sair");
			System.out.println(" 1 - Salvar ");
			System.out.println(" 2 - Atualizar ");
			System.out.println(" 3 - Visualizar ");
			System.out.println("4 - Deletar ");
			int action = scanner.nextInt();
			
			switch(action) {
				case 1:
					salvar(scanner);
					break;
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletar(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
		salvar(scanner);
	}

	private void salvar(Scanner scanner) {
		System.out.println("Salvando novo Funcionario");
		Funcionario funcionario = criaFuncionario(scanner);

		List<UnidadeDeTrabalho> unidades = unidade(scanner);

		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();

		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeDeTrabalho(unidades);


		repo.save(funcionario);
		System.out.println("Salvo");
	}
	
	
	private void atualizar(Scanner scanner) {
		System.out.println("Atualizacao de Registro");
		System.out.println("Digite o id numerico ");
		Integer id = 0;
		id = scanner.nextInt();
		Funcionario funcionario = criaFuncionario(scanner);
		funcionario.setId(id);
		repo.save(funcionario);
		System.out.println("Atualizado!");
	}
	
	private void visualizar() {
		Iterable<Funcionario> all = repo.findAll();
		
		all.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o id numerico ");
		Integer id = 0;
		id = scanner.nextInt();
		repo.deleteById(id);
		System.out.println("Deletado com sucesso!");
	}
	
	public Funcionario criaFuncionario(Scanner scanner) {
		System.out.println("Nome do peão");
		String nome = "";
		nome = scanner.next();
		
		System.out.println("Digite o cep");
		String cep = "";
		cep = scanner.next();
		
		System.out.println("Digite o salario com ponto exemplo: 1500.23");
		Double salario = 0.0;
		salario = scanner.nextDouble();
		
		System.out.println("Digite a data de contratação exemplo: 21/02/2020 ");
		String data = "";
		data = scanner.next();
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nome);
		funcionario.setCep(cep);
		funcionario.setSalario( new BigDecimal(salario));
        funcionario.setDataContratacao(LocalDate.parse(data, formatter));

		return funcionario;
	}
	
	private List<UnidadeDeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeDeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
	
}
