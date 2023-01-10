package ir.alirezaalijani.app;

import ir.alirezaalijani.share.Node;
import ir.alirezaalijani.share.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/26/2022
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int size= scanner.nextInt();
        int[][] mat=new int[size][size];
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                mat[i][j] = scanner.nextInt();
            }
        }

        List<Node> list=new ArrayList<>();

        for (int i=0;i<size;i++){
            Node node=new Node(String.valueOf(i));
            list.add(node);
            for (int j=0;j<size;j++) {
                if (mat[i][j]==1){
                    node.addChildren(new Node(String.valueOf(j)));
                }
            }
        }
    }
}
