package ir.alirezaalijani.share;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/6/2022
 */
public class Node {
    private Vertex vertex;
    private boolean visited;
    private Node parent;
    private Map<Node,Integer> children;

    public Node(Vertex vertex,Node parent) {
        children =new HashMap<>();
        this.vertex=vertex;
        this.parent=parent;
    }

    public Node(String name){

    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addChildren(Node node,int cost){
        children.put(node,cost);
    }

    public void addChildren(Node node){
        children.put(node,0);
    }
    public Vertex getVertex() {
        return vertex;
    }

    public Node getParent() {
        return parent;
    }

    public Map<Node, Integer> getChildren() {
        return children;
    }
}
