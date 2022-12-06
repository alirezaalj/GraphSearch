package ir.alirezaalijani.test;

import ir.alirezaalijani.share.Node;
import ir.alirezaalijani.share.Vertex;

import java.util.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/6/2022
 */
public class GraphSearch {
    public static Node bfs(Node root, Vertex vertex){
        Queue<Node> queue=new LinkedList<>();
        root.getVertex().setVisited(true);
        queue.add(root);

        while (!queue.isEmpty()){
            Node node=queue.remove();
            System.out.println("Actual node:"+node.getVertex().getName());
            for (Vertex v:node.getVertex().getNeighbourList()){
                if (!v.isVisited()){
                    v.setVisited(true);
                    Node n=new Node(v,node);
                    if (v.equals(vertex)){
                        return n;
                    }
                    node.addChildren(n,1);
                    queue.add(new Node(v,node));
                }
            }
        }
        return null;
    }

    public static Node dfs(Node root,Vertex vertex){
        Stack<Node> stack= new Stack<>();
        root.getVertex().setVisited(true);
        stack.push(root);
        while (!stack.isEmpty()){
            Node node=stack.pop();
            System.out.println("Actual node:"+node.getVertex().getName());
            for (Vertex v:node.getVertex().getNeighbourList()){
                if( !v.isVisited() ){
                    v.setVisited(true);
                    Node n=new Node(v,node);
                    if (v.equals(vertex)){
                        return n;
                    }
                    node.addChildren(n,1);
                    stack.push(n);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

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
       // vertexD.addNeighbour(vertexE);
        vertexD.addNeighbour(vertexF);

        vertexE.addNeighbour(vertexF);
        vertexE.addNeighbour(vertexB);

        vertexF.addNeighbour(vertexE);

        Node root=new Node(vertexA,null);

//        System.out.println("--------- BFS ---------");
//        Node find = bfs(root,vertexB);
//        if (find!=null){
//            System.out.print("find vertex : "+find.getVertex().getName());
//            while (find.getParent()!=null){
//                find=find.getParent();
//                System.out.print(" <- "+find.getVertex().getName());
//            }
//        }

        System.out.println("\n\n--------- DFS ---------");
        Node find = dfs(root,vertexB);
        if (find!=null){
            System.out.print("find vertex : "+find.getVertex().getName());
            while (find.getParent()!=null){
                find=find.getParent();
                System.out.print(" <- "+find.getVertex().getName());
            }
        }
    }
}
