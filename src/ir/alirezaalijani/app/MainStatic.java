package ir.alirezaalijani.app;

import ir.alirezaalijani.bfs.BreadthFirstSearch;
import ir.alirezaalijani.share.Vertex;

import java.util.List;
import java.util.Scanner;

public class MainStatic {

	public static void main(String[] args) {

		BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
		
		Vertex vertexA = new Vertex("A");
		Vertex vertexB = new Vertex("B");
		Vertex vertexC = new Vertex("C");
		Vertex vertexD = new Vertex("D");
		Vertex vertexE = new Vertex("E");
		Vertex vertexF = new Vertex("F");

		vertexA.addNeighbour(vertexC);
		vertexA.addNeighbour(vertexD);

		vertexC.addNeighbour(vertexF);

		vertexD.addNeighbour(vertexC);
		vertexD.addNeighbour(vertexE);
		vertexD.addNeighbour(vertexF);

		vertexE.addNeighbour(vertexF);
		vertexE.addNeighbour(vertexB);

		vertexF.addNeighbour(vertexE);

		List<Vertex> traversal = breadthFirstSearch.bfs(vertexA,"B");
		if (traversal!=null){
			System.out.print("Find Vertex With Name B By visiting: ");
			for (Vertex vertex :traversal){
				System.out.print(vertex.getName()+" ");
			}
		}else {
			System.out.println("Vertex B Not found.");
		}

	}
}
