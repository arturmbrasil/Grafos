package grafos;

import java.util.ArrayList;

import grafos.Grafo.Node;

public class Vertice {
	String nome;
	ArrayList<Vertice> listaAdj = new ArrayList<Vertice>();
	Node no;
	
	
	public Node getNo() {
		return no;
	}

	public void setNo(Node no) {
		this.no = no;
	}

	//Retorna uma string com a Lista de Adjancencia do Vertice
	public String mostraListaAdj(){
		String msg = "[";
		for(Vertice v : listaAdj){
			msg += v.getNome() + ", ";
		}
		//Deleta os 2 ultimos caracteres da String msg
		if(msg.length()>2)
			msg = msg.substring(0, msg.length() - 2);
		
		msg += "]";

		return msg;
	}
	
	//Adiciona vertice adjacente na lista de vertices adjacentes
	public void addListaAjd(Vertice v){
			listaAdj.add(v);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Vertice> getListaAdj() {
		return listaAdj;
	}

	public void setListaAdj(ArrayList<Vertice> listaAdj) {
		this.listaAdj = listaAdj;
	}

	@Override
	public String toString() {
		return "Vertice [nome=" + nome + "]";
	}

}
