package aldebaran.game.pathfinding;

import aldebaran.game.model.Position;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<Position, Node> nodes = new HashMap<>();

    public void addNode(Position pos, Node node) {
        nodes.put(pos, node);
    }

    public Map<Position, Node> getNodes() {
        return nodes;
    }

    public Node nodeAtPosition(Position position) {
        return nodes.get(position);
    }
}
