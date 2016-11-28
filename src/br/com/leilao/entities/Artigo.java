package br.com.leilao.entities;

public class Artigo {
	
	private String descricao;
	private Float valorMinimo;
	private boolean isOpen;
	private int numero;
	
	public Artigo(String descricao, Float valorMinimo, boolean isOpen, int numero) {
		this.descricao = descricao;
		this.valorMinimo = valorMinimo;
		this.isOpen = isOpen;
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(Float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
