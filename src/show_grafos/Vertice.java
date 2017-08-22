package show_grafos;

public class Vertice {
	String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Vertice [nome=" + nome + "]";
	}

}
