package ir.alirezaalijani.app;

import ir.alirezaalijani.bfs.BreadthFirstSearch;
import ir.alirezaalijani.dfs.DepthFirstSearch;
import ir.alirezaalijani.share.Vertex;

import java.util.*;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/4/2022
 */
public class MainConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert Nods (split by ','):");

        String nodesName = scanner.nextLine();

        List<Vertex> vertices = new ArrayList<>();
        for (String v : nodesName.split(",")) {
            vertices.add(new Vertex(v));
        }

        System.out.println("\n-------------- insert mat ---------------\n");
        System.out.print("   ");
        for (Vertex vertex : vertices) {
            System.out.print(vertex.getName() + " ");
        }
        System.out.println();


        List<String[]> mat = new ArrayList<>(vertices.size());

        for (Vertex node : vertices) {
            System.out.print(node.getName() + "  ");
            String line = scanner.nextLine();
            String[] row = line.split(" ");
            for (int i = 0; i < row.length; i++) {
                if (row[i].equals("1")) {
                    Vertex neighbor = vertices.get(i);
                    if (!neighbor.equals(node)) {
                        node.addNeighbour(neighbor);
                    }
                }
            }
            mat.add(row);
        }

        System.out.println();
        for (String[] row : mat) {
            System.out.print("  ");
            for (String index : row) {
                System.out.print(" " + index);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Select Search Type (BFS,DFS):");
        String searchType=scanner.nextLine();
        System.out.print("Insert Vertex To Find:");
        String vertexToFind = scanner.nextLine();

        if (searchType.equals("BFS")){
            System.out.println("------ BFS Search ------");
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            List<Vertex> traversal = bfs.bfs(vertices.get(0), vertexToFind);
            traversalResult(traversal,vertexToFind);
        } else if (searchType.equals("DFS")) {
            System.out.println("------ DFS Search ------");
            DepthFirstSearch dfs = new DepthFirstSearch();
            List<Vertex> traversal = dfs.dfs(vertices.get(0), vertexToFind);
            traversalResult(traversal,vertexToFind);
        }

    }
    private static void traversalResult(List<Vertex> traversal,String vertexToFind){
        if (traversal != null) {
            System.out.print("Find Vertex With Name " + vertexToFind + " By visiting: ");
            for (Vertex vertex : traversal) {
                System.out.print(vertex.getName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("Vertex " + vertexToFind + " Not found.");
        }
    }
}
