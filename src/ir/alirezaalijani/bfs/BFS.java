package ir.alirezaalijani.bfs;

import ir.alirezaalijani.share.Vertex;
import ir.alirezaalijani.share.Node;

import java.util.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/6/2022
 */
public class BFS {

    private Node bfs(Vertex start, String vertexName){
        Queue<Node> queue=new LinkedList<>();
        Node root=new Node(start,null);
        root.getVertex().setVisited(true);
        queue.add(root);
        while (!queue.isEmpty()){
            Node node=queue.remove();
            System.out.println("visit node:"+node.getVertex().getName());
            for (Vertex v:node.getVertex().getNeighbourList()){
                if (!v.isVisited()){
                    v.setVisited(true);
                    Node n=new Node(v,node);
                    if (v.getName().equals(vertexName)){
                        return n;
                    }
                    node.addChildren(n,1);
                    queue.add(new Node(v,node));
                }
            }
        }
        return null;
    }

    public List<Vertex> search(Vertex start,String vertexName){
        Node find= bfs(start,vertexName);
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
