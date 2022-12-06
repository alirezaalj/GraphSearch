package ir.alirezaalijani.dfs;
import ir.alirezaalijani.share.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class DepthFirstSearch {

	public List<Vertex> dfs(Vertex root,String name){
		Stack<Vertex> stack = new Stack<>();
		List<Vertex> traversal=new ArrayList<>();
		stack.add(root);
		root.setVisited(true);

		while( !stack.isEmpty() ){

			Vertex actualVertex = stack.pop();
			System.out.print(actualVertex+"->");
			traversal.add(actualVertex);
			for( Vertex v : actualVertex.getNeighbourList() ){
				if( !v.isVisited() ){
					v.setVisited(true);
					if (v.getName().equals(name)){
						traversal.add(v);
						return traversal;
					}
					stack.push(v);
				}
			}
		}
		return null;
	}
}
