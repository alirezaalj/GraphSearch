package ir.alirezaalijani.dfs;

import ir.alirezaalijani.share.Node;
import ir.alirezaalijani.share.Vertex;

import java.util.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/6/2022
 */
public class DFS {

    private Node dfs(Vertex start,String vertexName){
        Stack<Node> stack= new Stack<>();
        Node root=new Node(start,null);
        root.getVertex().setVisited(true);
        stack.push(root);
        while (!stack.isEmpty()){
            Node node=stack.pop();
            System.out.println("Actual node:"+node.getVertex().getName());
            for (Vertex v:node.getVertex().getNeighbourList()){
                if( !v.isVisited() ){
                    v.setVisited(true);
                    Node n=new Node(v,node);
                    if (v.getName().equals(vertexName)){
                        return n;
                    }
                    node.addChildren(n,1);
                    stack.push(n);
                }
            }
        }
        return null;
    }

    public List<Vertex> search(Vertex start, String vertexName){
        Node find=dfs(start,vertexName);
        if (find!=null){
            List<Vertex> pathList=new ArrayList<>();
            System.out.print("find vertex : "+find.getVertex().getName());
            pathList.add(find.getVertex());
            while (find.getParent()!=null){
                find=find.getParent();
                pathList.add(find.getVertex());
                System.out.print(" <- "+find.getVertex().getName());
            }
            Collections.reverse(pathList);
            return pathList;
        }
        return null;
    }
}
