package br.com.alura.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String cep;
	
	private BigDecimal salario;
	
	private LocalDate dataContratacao;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "funcionarios_unidades", 
		joinColumns = {@JoinColumn(name="fk_funcionarios")}, 
		inverseJoinColumns = {@JoinColumn(name = "fk_unidade")} )
	private List<UnidadeDeTrabalho> unidadeDeTrabalho;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<UnidadeDeTrabalho> getUnidadeDeTrabalho() {
		return unidadeDeTrabalho;
	}

	public void setUnidadeDeTrabalho(List<UnidadeDeTrabalho> unidadeDeTrabalho) {
		this.unidadeDeTrabalho = unidadeDeTrabalho;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	
	@Override
	public String toString() {
		return "Nome: "+this.nome+" salario: "+this.salario+" Cargo: "+this.cargo.getDescricao() 
		+ " Data de contratacao: "+this.dataContratacao.getDayOfMonth()+"/"+this.dataContratacao.getMonthValue()+"/"+this.dataContratacao.getYear();
	}
}
