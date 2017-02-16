package aldebaran.game.model;

public class Tile {
    private Position position;
    private TileType type;

    private Unit unit;

    public Tile(TileType type, Position pos) {
        this.type = type;
        this.position = pos;
        this.unit = null;
    }

    public Position getPosition(){
        return position;
    }

    public TileType getType() {
        return type;
    }

    public void addUnit(Unit unit){
        this.unit = unit;
    }

    public void removeUnit(){
        this.unit = null;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "position=" + position +
                ", type=" + type +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return position.equals(tile.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}


