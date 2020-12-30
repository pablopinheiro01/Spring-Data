package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeDeTrabalhoService {
	
	private Boolean system = true;

	private final UnidadeTrabalhoRepository repo;

	public CrudUnidadeDeTrabalhoService(UnidadeTrabalhoRepository repo) {
		this.repo = repo;
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
		System.out.println("Salvando nova unidade");
		UnidadeDeTrabalho unidade = criaUnidade(scanner);
		repo.save(unidade);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Atualizacao de Registro");
		System.out.println("Digite o id numerico ");
		Integer id = 0;
		id = scanner.nextInt();
		UnidadeDeTrabalho unidade = criaUnidade(scanner);
		unidade.setId(id);
		repo.save(unidade);
		System.out.println("Atualizado!");
	}
	
	private void visualizar() {
		Iterable<UnidadeDeTrabalho> all = repo.findAll();
		
		all.forEach(unidade -> System.out.println(unidade));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o id numerico ");
		Integer id = 0;
		id = scanner.nextInt();
		repo.deleteById(id);
		System.out.println("Deletado com sucesso!");
	}
	
	public UnidadeDeTrabalho criaUnidade(Scanner scanner) {
		System.out.println("Descrição da unidade");
		String descricao = "";
		descricao = scanner.next();
		
		System.out.println("Digite o endereço completo");
		String endereco = "";
		endereco = scanner.next();
		
		UnidadeDeTrabalho unidade = new UnidadeDeTrabalho();
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);

		return unidade;
	}

}
