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

	//Adiciona os Vertices no Grafo 
	public void addVertices(int n) {
		for (int i = 1; i <= n; i++) {
			Vertice v = new Vertice();

			v.setNome(JOptionPane.showInputDialog("Nome do vertice " + i));
			vertices.add(v);
		}
	}
	
	//Adiciona as Arestas no Grafo 
	public void addArestas(int v1, int v2, boolean valorado, boolean orientado) {
		Aresta e = new Aresta();
		String nomeAresta;
		do{
			nomeAresta = JOptionPane.showInputDialog("Nome da aresta " + (arestas.size() + 1));
		}while (nomeAresta.equals(""));
		e.setNome(nomeAresta);
		e.setOrientado(orientado);
		e.setValorado(valorado);
		e.setV1(vertices.get(v1));
		e.setV2(vertices.get(v2));
		if (valorado) {
			try {
				e.setPeso(Double.parseDouble(JOptionPane.showInputDialog("Peso da aresta " + (arestas.size() + 1))));				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Tente novamente, valor invalido");
				e2.printStackTrace();
				return ;
			}
		}
		arestas.add(e);
	}

	public String nomeAresta(int n) {
		return arestas.get(n).nome;
	}

	public Double pesoAresta(int n) {
		return arestas.get(n).peso;
	}
	
	// LISTA DE ARESTAS
	public String listaDeArestas(boolean valorado){
		String msg = "############## LISTA DE ARESTAS ##############\n[ ";
		
		for (Aresta a : arestas){
			msg += "[ " + a.v1.getNome() + "," + a.v2.getNome() ;
			if (valorado){
				msg += "," + a.getPeso();
			}
			msg += "],";
		}
		//Deleta o ultimo caractere da String msg
		if(msg.length()>2)
			msg = msg.substring(0, msg.length() - 1);
		msg += " ]\n\n\n\n";
		return msg;
	}
	
	// MATRIZ DE ADJACENCIA
	public String matrizAdj(boolean valorado, boolean orientado){
		double[][] matriz;
		String msg = "############ MATRIZ DE ADJACENCIA ############\n";
		int tamanho = vertices.size();
		//Cria a matriz |V| X |V|
		matriz = new double[tamanho][tamanho];
		
		//Para cada aresta pega cada vertice e coloca na matriz
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
		
		// Monta a msg para mostrar a matriz na tela
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
	
		msg += "\n\n\n";
		return msg;
	}
	
	// LISTA DE ADJACENCIA
	public String listaAdj(){
		String msg = "############ LISTA DE ADJACENCIA #############\n[ "; 
		for(Vertice v : vertices){
			msg += v.mostraListaAdj() + ",\n";
		}
		//Deleta os 2 ultimos caracteres da String msg
		if(msg.length()>2)
			msg = msg.substring(0, msg.length() - 2);
		
		msg += " ]\n\n\n\n";
		return msg;		
	}
	
	// MATRIZ DE INCIDENCIA
	public String matrizIncidencia(boolean orientado){
		int[][] matriz;
		String msg = "############ MATRIZ DE INCIDENCIA ############\n\nVertices: ";
		int n = vertices.size();
		int m = arestas.size();
		//Cria a matriz |n| X |m|
		matriz = new int[n][m];

		//Preenche a matriz
		for(Aresta a : arestas){
			int v1 = vertices.indexOf(a.getV1());
			int v2 = vertices.indexOf(a.getV2());
			int aresta = arestas.indexOf(a);
			
			//Se o grafo não for orientado
			if(!orientado){
				if(v1==v2){ //Se for um laço
					matriz[v1][aresta] = 2;
				}else{						
					matriz[v1][aresta] = 1;
					matriz[v2][aresta] = 1;
				}
					
			}
			//Se for orientado
			else{
				if(v1==v2){ //Se for um laço
					matriz[v1][aresta] = 1;
				}else{
					matriz[v1][aresta] = 1;
					matriz[v2][aresta] = -1;	
				}
			}
		}
			
		// Monta a msg para mostrar a matriz na tela
		for(Aresta a : arestas){
			msg += a.getNome() + " | ";
		}
			
		msg += "\n";
			
		for (int i=0 ; i<vertices.size() ; i++){
			msg += vertices.get(i).getNome() + " | ";
				
			for (int j=0 ; j<arestas.size() ; j++){		
				msg += " | " + matriz[i][j] + " | ";				
			}
			msg += "\n";
		}
		
		msg += "\n\n";
		return msg;
	}
		
	//Dijkstra
	public String Dijkstra(Vertice origem, Boolean orientado){
		String msg = "########## Dijkstra ##########\n\nVertices        : ";
		//Inicialização
		Boolean[] perm = new Boolean[vertices.size()];
		Double[] distancia = new Double[vertices.size()];
		String[]  caminho = new String[vertices.size()];
		for(Vertice v : vertices){
				perm[vertices.indexOf(v)] = false;
				distancia[vertices.indexOf(v)] = Double.MAX_VALUE;
				msg+= v.getNome() + "    ";
		}
		
		perm[vertices.indexOf(origem)] = true;
		distancia[vertices.indexOf(origem)] = 0.0;
		
		//Verifica os adjacentes ao vertice de origem
		for(Vertice z : origem.listaAdj){
			if(distancia[vertices.indexOf(z)] > ((distancia[vertices.indexOf(origem)]) + buscaPesoAresta(origem, z, orientado)) ){
				distancia[vertices.indexOf(z)] = (distancia[vertices.indexOf(origem)]) + buscaPesoAresta(origem, z, orientado);
				caminho[vertices.indexOf(z)] = origem.getNome();	
			}
		}
		
		for (int i = 0; i < vertices.size(); i++) {
			Double dist = Double.MAX_VALUE;
			Vertice proxVert = null;
			for (Vertice ve : vertices) {
				if(perm[vertices.indexOf(ve)] == false & (distancia[vertices.indexOf(ve)] < dist)){
					dist = distancia[vertices.indexOf(ve)];
					proxVert = vertices.get(vertices.indexOf(ve));
				}
			}
			
			if(proxVert != null){
				perm[vertices.indexOf(proxVert)] = true;
	
				for(Vertice z : proxVert.listaAdj){
					if(perm[vertices.indexOf(z)] == false){
						if(distancia[vertices.indexOf(z)] > ((distancia[vertices.indexOf(proxVert)]) + buscaPesoAresta(proxVert, z, orientado)) ){
							distancia[vertices.indexOf(z)] = (distancia[vertices.indexOf(proxVert)]) + buscaPesoAresta(proxVert, z, orientado);
							caminho[vertices.indexOf(z)] = proxVert.getNome();	
						}						
					}
				}			
			}
			else{
				
				msg += "\nDistancia      : ";
				for(Double x : distancia){
					if(x == Double.MAX_VALUE){
						msg+= "∞  ";
					}
					else{
						msg+= x + "  ";						
					}
				}
				msg += "\nCaminho       : "; 
				for(String x : caminho){
					if(x == null){
						msg+= "--  ";						
					}
					else{
						msg+= x + "  ";						
					}
				}
				
				return msg;
			}
		}
		
		return "";
	
	}
	
	public Double buscaPesoAresta(Vertice origem, Vertice adjacente, Boolean orientado){
		Double peso = null;
		
		for(Aresta a : arestas){
			if(origem == a.getV1() & adjacente == a.getV2()){
				peso = a.getPeso();
			}
		}			
		
		if(!orientado){
			for(Aresta a : arestas){
				if(origem == a.getV2() & adjacente == a.getV1()){
					peso = a.getPeso();
				}
			}			
		}
		
		if(peso != null){
			return peso;			
		}
		
		else{
			JOptionPane.showMessageDialog(null, "Ocorreu um erro");
			return -1.0;
		}
		
	}
	

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}
	
	

}
