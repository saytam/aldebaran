package aldebaran.game.model;

import aldebaran.game.pathfinding.Path;

public class Unit {

    private UnitType type;

    private Tile tile;

    private Path path;

    private Integer movement;

    public Unit(UnitType unitType) {
        this.type = unitType;
    }

    public UnitType getType() {
        return type;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Integer getMovement() {
        return movement;
    }

    public void setMovement(Integer movement) {
        this.movement = movement;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "type=" + type +
                '}';
    }
}
