package aldebaran.game.pathfinding;

import aldebaran.game.model.Position;
import aldebaran.game.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Tile tile;
    private List<Node> neighbours = new ArrayList<>();

    public void addNeighbour(Node node){
        neighbours.add(node);
    }

    public Node(Tile tile){
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        return "Node{" +
                "tile=" + tile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return tile.equals(node.tile);
    }

    @Override
    public int hashCode() {
        return tile.hashCode();
    }
}
