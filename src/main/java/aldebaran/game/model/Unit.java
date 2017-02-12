package aldebaran.game.model;

public class Unit {

    private UnitType type;

    private Tile tile;

    public Unit(UnitType unitType){
        this.type = unitType;
    }

    public UnitType getType() {
        return type;
    }

    public void setTile(Tile tile){
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "type=" + type +
                '}';
    }
}
