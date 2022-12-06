package ir.alirezaalijani.app;

import ir.alirezaalijani.bfs.BFS;
import ir.alirezaalijani.bfs.BreadthFirstSearch;
import ir.alirezaalijani.dfs.DFS;
import ir.alirezaalijani.dfs.DepthFirstSearch;
import ir.alirezaalijani.share.Vertex;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 12/4/2022
 */
public class MainGuiController {

    public MainGuiController() {
        vertexMap = new HashMap<>();
        lineMap = new HashMap<>();
    }

    @FXML
    public AnchorPane rootPane;
    @FXML
    public TextField txtVertexNameSearch;
    @FXML
    private TextField txtVertexName;
    @FXML
    public ComboBox<String> cbSearchType;
    private Label firstNode;
    private Label secondNode;
    private Label startNode;
    private final Map<Label, Vertex> vertexMap;
    private final Map<String, Line> lineMap;
    private boolean connectAction = false;

    @FXML
    void initialize() {
        cbSearchType.getItems()
                .addAll("BFS","DFS");
    }

    @FXML
    public void onFind(MouseEvent mouseEvent) {
        printVertex();
        String sType = cbSearchType.getSelectionModel().getSelectedItem();
        if (sType.equals("BFS")){
            System.out.println("------ BFS Search ------");
//            BreadthFirstSearch bfs = new BreadthFirstSearch();
//            List<Vertex> traversal = bfs.bfs(vertexMap.get(startNode), txtVertexNameSearch.getText().trim());
            BFS bfs = new BFS();
            List<Vertex> traversal = bfs.search(vertexMap.get(startNode), txtVertexNameSearch.getText().trim());
            traversalResult(traversal);
        } else if (sType.equals("DFS")) {
            System.out.println("------ DFS Search ------");
//            DepthFirstSearch dfs = new DepthFirstSearch();
//            List<Vertex> traversal = dfs.dfs(vertexMap.get(startNode), txtVertexNameSearch.getText().trim());
            DFS dfs = new DFS();
            List<Vertex> traversal = dfs.search(vertexMap.get(startNode), txtVertexNameSearch.getText().trim());
            traversalResult(traversal);
        }
    }

    private void traversalResult(List<Vertex> traversal) {
        if (traversal != null) {
            System.out.print("\nFind Vertex With Name "+txtVertexNameSearch.getText().trim()+" By visiting: ");
            for (Vertex vertex : traversal) {
                System.out.print(vertex.getName() + " ");
                Label label = findVertex(vertex);
                if (label != null&&startNode!=label) {
                    label.setStyle("-fx-background-color: #78d9b4; -fx-background-radius: 35;");
                }
            }
        } else {
            System.out.println("Vertex "+txtVertexNameSearch.getText().trim()+" Not found.");
        }
    }

    @FXML
    void onAddVertex(MouseEvent event) {
        createVertex(txtVertexName.getText());
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !txtVertexName.getText().trim().equals("")) {
            createVertex(txtVertexName.getText());
        }
    }

    private void drawLine() {
        for (Map.Entry<Label, Vertex> v : vertexMap.entrySet()) {
            Label node = v.getKey();
            for (Vertex to : v.getValue().getNeighbourList()) {
                Label secondNode = findVertex(to);
                if (secondNode != null) {
                    String lineName = node.getText() + secondNode.getText();
                    Line line = lineMap.get(lineName);
                    if (line == null) {
                        line = new Line(node.getLayoutX() + 35, node.getLayoutY() + 35,
                                secondNode.getLayoutX() + 35, secondNode.getLayoutY() + 35);
                        rootPane.getChildren().add(line);
                        lineMap.put(lineName, line);
                    } else {
                        line.setStartX(node.getLayoutX() + 35);
                        line.setStartY(node.getLayoutY() + 35);
                        line.setEndX(secondNode.getLayoutX() + 35);
                        line.setEndY(secondNode.getLayoutY() + 35);
                    }
                    line.toBack();

                }
            }
        }
    }

    private Label findVertex(Vertex v) {
        for (Map.Entry<Label, Vertex> item : vertexMap.entrySet()) {
            if (Objects.equals(v, item.getValue())) {
                return item.getKey();
            }
        }
        return null;
    }

    private void createVertex(String name) {
        Label label = new Label(name);
        label.toFront();
        Vertex vertex = new Vertex(label.getText());
        final Delta dragDelta = new Delta();
        label.setStyle("-fx-background-color: #d97878; -fx-background-radius: 35;");
        label.setPrefHeight(70);
        label.setPrefWidth(70);
        label.setLayoutY(100);
        label.setLayoutX(100);
        label.setAlignment(Pos.CENTER);

        label.setOnMousePressed(mouseEvent -> {
            if (!connectAction) {
                dragDelta.x = label.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = label.getLayoutY() - mouseEvent.getSceneY();
                label.setCursor(Cursor.MOVE);
                drawLine();
            }
        });
        label.setOnMouseReleased(mouseEvent -> label.setCursor(Cursor.HAND));

        label.setOnMouseDragged(mouseEvent -> {
            if (!connectAction) {
                label.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                label.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
                drawLine();
            }
        });

        label.setOnMouseClicked(clickEvent -> {
            if (connectAction) {
                secondNode = label;
                if (firstNode != null) {
                    if (lineMap.get(firstNode.getText() + secondNode.getText()) == null) {
                        System.out.println(label.getText());
                        vertexMap.get(firstNode).addNeighbour(vertexMap.get(secondNode));
                    }
                }
                firstNode = null;
                secondNode = null;
                connectAction = false;
                drawLine();
            }
        });

        label.setOnMouseEntered(mouseEvent -> label.setCursor(Cursor.HAND));

        ContextMenu contextMenu = new ContextMenu();

        MenuItem connectTo = new MenuItem("Connect To");

        connectTo.setOnAction(e -> {
            connectAction = true;
            firstNode = label;
            System.out.print("connect " + label.getText() + " to ");
        });
        MenuItem delete = new MenuItem("Delete");

        delete.setOnAction(e -> {
            String nameToDelete = label.getText();
            Vertex vertexToRemove = vertexMap.get(label);
            if (startNode==label){
                startNode=null;
            }
            vertexMap.remove(label);
            rootPane.getChildren().remove(label);
            System.out.println("remove vertex:" + nameToDelete);
            List<String> listToDelete = lineMap
                    .keySet().stream()
                    .filter(line -> line.contains(nameToDelete))
                    .collect(Collectors.toList());
            listToDelete.forEach(item -> {
                Line line = lineMap.get(item);
                System.out.println("remove line:" + item);
                rootPane.getChildren().remove(line);
                lineMap.remove(item);
            });
            vertexMap.forEach((key, value) -> value.getNeighbourList().remove(vertexToRemove));
        });

        MenuItem setStart = new MenuItem("Set Start");
        setStart.setOnAction(event -> {
            if (startNode != null) {
                startNode.setStyle("-fx-background-color: #d97878; -fx-background-radius: 35;");
            }
            startNode=label;
            label.setStyle("-fx-background-color: #b960dc; -fx-background-radius: 35;");
        });
        contextMenu.getItems().add(connectTo);
        contextMenu.getItems().add(delete);
        contextMenu.getItems().add(setStart);

        label.setContextMenu(contextMenu);

        rootPane.getChildren()
                .add(label);
        vertexMap.put(label, vertex);
        txtVertexName.setText("");
    }

    private void printVertex() {
        vertexMap.values()
                .forEach(vertex -> {
                    System.out.println("vertex:" + vertex.getName());
                    vertex.getNeighbourList()
                            .forEach(neighbour -> System.out.print("\t" + neighbour.getName()));
                    System.out.println();
                });
    }

    @FXML
    public void onReset(MouseEvent mouseEvent) {
        startNode=null;
        vertexMap
                .forEach((key,val) -> {
                    key.setStyle("-fx-background-color: #d97878; -fx-background-radius: 35;");
                    val.setVisited(false);
                });
    }

    static class Delta {
        double x, y;
    }

}
