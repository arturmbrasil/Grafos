package show_grafos;

public class Aresta {
	String nome;
	Vertice v1, v2;
	Double peso;
	boolean valorado;
	boolean orientado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Vertice getV1() {
		return v1;
	}

	public void setV1(Vertice v1) {
		this.v1 = v1;
	}

	public Vertice getV2() {
		return v2;
	}

	public void setV2(Vertice v2) {
		this.v2 = v2;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public boolean isValorado() {
		return valorado;
	}

	public void setValorado(boolean valorado) {
		this.valorado = valorado;
	}

	public boolean isOrientado() {
		return orientado;
	}

	public void setOrientado(boolean orientado) {
		this.orientado = orientado;
	}

	@Override
	public String toString() {
		return "Aresta [nome=" + nome + ", v1=" + v1 + ", v2=" + v2 + ", peso=" + peso + ", valorado=" + valorado
				+ ", orientado=" + orientado + "]";
	}

}
