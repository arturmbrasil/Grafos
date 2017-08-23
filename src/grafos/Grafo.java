package grafos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Grafo {
	ArrayList<Vertice> vertices;
	ArrayList<Aresta> arestas;

	public Grafo() {
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
	}

	public void addVertices(int n) {
		for (int i = 1; i <= n; i++) {
			Vertice v = new Vertice();

			v.setNome(JOptionPane.showInputDialog("Nome do vertice " + i));
			vertices.add(v);
		}
	}

	public void addArestas(int v1, int v2, boolean valorado, boolean orientado) {
		Aresta e = new Aresta();
		e.setNome(JOptionPane.showInputDialog("Nome da aresta " + (arestas.size() + 1)));
		e.setOrientado(orientado);
		e.setValorado(valorado);
		e.setV1(vertices.get(v1));
		e.setV2(vertices.get(v2));
		if (valorado) {
			e.setPeso(Double.parseDouble(JOptionPane.showInputDialog("Peso da aresta " + (arestas.size() + 1))));
		}
		if (orientado) {

		}
		arestas.add(e);
		System.out.println(arestas);
	}

	public String nomeAresta(int n) {
		return arestas.get(n).nome;
	}

	public Double pesoAresta(int n) {
		return arestas.get(n).peso;
	}
	
	public String listaDeArestas(boolean valorado){
		String msg = "######## LISTA DE ARESTAS ########\n[ ";
		
		for (Aresta a : arestas){
			msg += "[ " + a.v1.getNome() + "," + a.v2.getNome() ;
			if (valorado){
				msg += "," + a.getPeso();
			}
			msg += "],";
		}
		
		msg = msg.substring(0, msg.length() - 1);
		msg += " ]\n";
		msg += "##################################\n";
		return msg;
	}
	
	public String matrizAdj(boolean valorado, boolean orientado){
		double[][] matriz;
		String msg = "######## MATRIZ DE ADJACENCIA ########\n";
		int tamanho = vertices.size();
		matriz = new double[tamanho][tamanho];
		
		for(Aresta a : arestas){
			int v1 = vertices.indexOf(a.getV1());
			int v2 = vertices.indexOf(a.getV2());
			
			if(valorado & orientado){
					matriz[v1][v2] = a.getPeso();				
			}
			else if(!valorado & orientado){
				matriz[v1][v2] = 999;	
			}
			else if(valorado & !orientado){
				matriz[v1][v2] = a.getPeso();
				matriz[v2][v1] = a.getPeso();
			}
			else if(!valorado & !orientado){
				matriz[v1][v2] = 999;
				matriz[v2][v1] = 999;
			}
		}
			
		for (int i=0 ; i<matriz.length ; i++){
			msg += vertices.get(i).getNome();
				
			for (int j=0 ; j<matriz.length ; j++){
				if(valorado){
					msg += " | " + matriz[i][j] + " ";
				}else if(matriz[i][j] == 999){
					msg += " | " + "1" + " ";					
				}else{
					msg += " | " + "0" + " ";					
				}
			}
			msg += "\n";
		}
	
		msg += "##################################\n";
		return msg;
	}

}
