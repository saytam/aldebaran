package aldebaran.game;

import aldebaran.game.model.Board;
import aldebaran.game.model.Position;
import aldebaran.game.model.Tile;
import aldebaran.game.model.Unit;
import aldebaran.game.pathfinding.AdjacentTiles;
import aldebaran.game.pathfinding.Graph;
import aldebaran.game.pathfinding.Node;

import java.util.*;

public class Game {

    enum State {
        ROLL, MOVEMENT
    }

    private Board board;
    private Graph graph;

    private State state = State.ROLL;
    private Unit activeUnit = null;
    private Integer lastRoll = 0;
    private int usedMovement = 0;

    public Game() {
        this.board = new Board(10, 10);
        this.activeUnit = board.getUnits().get(0);
        initPathfindingGraph();
    }

    private void initPathfindingGraph() {
        this.graph = new Graph();
        this.board.getTiles().forEach(((position, tile) -> {
            Node node = new Node(tile);
            Optional<Tile> northernTile = AdjacentTiles.northOf(board, tile);
            northernTile.ifPresent(adjacentTile -> {
                node.addNeighbour(new Node(adjacentTile));
            });
            Optional<Tile> easternTile = AdjacentTiles.eastOf(board, tile);
            easternTile.ifPresent(adjacentTile -> {
                node.addNeighbour(new Node(adjacentTile));
            });
            Optional<Tile> southernTile = AdjacentTiles.southOf(board, tile);
            southernTile.ifPresent(adjacentTile -> {
                node.addNeighbour(new Node(adjacentTile));
            });
            Optional<Tile> westernTile = AdjacentTiles.westOf(board, tile);
            westernTile.ifPresent(adjacentTile -> {
                node.addNeighbour(new Node(adjacentTile));
            });
            graph.addNode(position, node);
        }));
    }

    public Board getBoard() {
        return board;
    }

    public void handleClick(Tile tile) {
        if (state == State.MOVEMENT) {
            Unit unit = tile.getUnit();
            System.out.println("tile = [" + tile + "]");
            if (unit == null && activeUnit != null) {

                moveSelectedUnit(tile);
                board.moveUnit(activeUnit, tile);
            }
//            if (activeUnit == null && unit != null) {
//                activeUnit =
//            }
        } else {
            System.out.println("Not in movement mode");
        }
    }

    private void moveSelectedUnit(Tile targetTile) {
        final Integer INFINITY = Integer.MAX_VALUE;
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        Position sourcePosition = this.activeUnit.getTile().getPosition();
        List<Node> unvisited = new LinkedList<>();

        final Node source = graph.nodeAtPosition(sourcePosition);
        distance.put(source, 0);
        previous.put(source, null);

        graph.getNodes().forEach(((position, node) -> {
            if (!source.getTile().equals(node.getTile())) {
                distance.put(node, INFINITY);
                previous.put(node, null);
            }
            unvisited.add(node);
        }));

        while (!unvisited.isEmpty()) {
            Node nodeWithMinDistance = findNodeWithMinDistance(unvisited, distance);
            if (nodeWithMinDistance.getTile().equals(targetTile)){
                return;
            }
            unvisited.remove(nodeWithMinDistance);
            for (Node neighbour : nodeWithMinDistance.getNeighbours()) {
                Integer newDistance = distance.get(nodeWithMinDistance) + 1;
                if (newDistance < distance.get(neighbour)) {
                    distance.put(neighbour, newDistance);
                    previous.put(neighbour, nodeWithMinDistance);
                }
            }
        }
    }

    private Node findNodeWithMinDistance(List<Node> unvisited, Map<Node, Integer> distance) {
        Node minDistanceNode = null;
        for (Node possible: unvisited){
            if (minDistanceNode == null || distance.get(possible) < distance.get(minDistanceNode)){
                minDistanceNode = possible;
            }
        }
        return minDistanceNode;
//       return distance.entrySet().stream()
//                .filter(entry -> unvisited.contains(entry.getKey()))
//                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
//                .findFirst()
//                .map(Map.Entry::getKey).get();
    }

    public Integer handleRoll() {
        if (state == State.ROLL) {
            int result = new Random().nextInt(12) + 1;
            System.out.println("roll was:" + result);
            state = State.MOVEMENT;
            lastRoll = result;
            return result;
        }
        System.out.println("Not in rolling mode");
        return null;
    }


}
