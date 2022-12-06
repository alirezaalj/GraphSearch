package ir.alirezaalijani.bfs;

import ir.alirezaalijani.share.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

	public List<Vertex> bfs(Vertex root, String name){
		if (root==null) return null;

		Queue<Vertex> queue = new LinkedList<>();
		List<Vertex> traversal=new ArrayList<>();
		root.setVisited(true);
		queue.add(root);
		
		while( !queue.isEmpty() ){
			
			Vertex actualVertex = queue.remove();
			traversal.add(actualVertex);
			for( Vertex v : actualVertex.getNeighbourList() ){
				if( !v.isVisited() ){
					v.setVisited(true);
					if (v.getName().equals(name)){
						traversal.add(v);
						return traversal;
					}
					queue.add(v);
				}
			}			
		}
		return null;
	}
}
