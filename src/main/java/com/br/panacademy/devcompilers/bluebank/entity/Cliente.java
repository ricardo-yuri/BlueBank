package com.br.panacademy.devcompilers.bluebank.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.br.panacademy.devcompilers.bluebank.enums.TipoCliente;

@Entity(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id_cliente")
	private Long id;

	@Column(length = 150)
	private String nome;

	@Column(nullable = false, unique = true, length = 15)
	private String cpf;

	@Column(length = 20)
	private String rg;

	@Column(columnDefinition = "boolean default false")
	private boolean deletado;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contato")
	private Contato contato;

	@Column(nullable = false)
	private TipoCliente tipoCliente;

	@OneToMany
	@JoinColumn(name = "id_conta")
	private List<Conta> conta;

	@Column
	@CreationTimestamp
	private LocalDateTime createAt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Conta> getConta() {
		return conta;
	}

	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", endereco=" + endereco
				+ ", tipoCliente=" + tipoCliente + ", createAt=" + createAt + "]";
	}
}
