package br.com.leilao.entities;

public class Lance {
	
	private int numeroArtigo;
	private Float valorLance;
	
	public Lance(int numeroArtigo, Float valorLance) {
		this.numeroArtigo = numeroArtigo;
		this.valorLance = valorLance;
	}

	public int getNumeroArtigo() {
		return numeroArtigo;
	}

	public void setNumeroArtigo(int numeroArtigo) {
		this.numeroArtigo = numeroArtigo;
	}

	public Float getValorLance() {
		return valorLance;
	}

	public void setValorLance(Float valorLance) {
		this.valorLance = valorLance;
	}
	
	
}
