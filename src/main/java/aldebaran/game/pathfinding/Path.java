package aldebaran.game.pathfinding;

import aldebaran.game.model.Tile;

import java.util.List;

public class Path {
    private List<Node> nodes;


    public Path(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Tile nextTile() {
        assert nodes != null;
        assert nodes.size() > 1;
        return nodes.get(1).getTile();
    }
}
