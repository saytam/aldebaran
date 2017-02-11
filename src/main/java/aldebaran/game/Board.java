package aldebaran.game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Board {

    private List<Tile> tiles;

    private List<Unit> units;

    private Consumer<Unit> onUnitMoved;

    public Board(int width, int heigth){
        this.tiles = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                Tile tile = new Tile(TileType.SAND, i, j);
                tiles.add(tile);
            }
        }

        this.units = new ArrayList<>();
        units.add(new Unit(UnitType.DUMMY));
        placeUnit(units.get(0), tiles.get(0));
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void placeUnit(Unit unit, Tile tile){
        unit.setTile(tile);
        tile.addUnit(unit);
    }

    public void removeUnit(Unit unit, Tile tile){
        unit.setTile(null);
        tile.removeUnit();
    }

    public void moveUnit(Unit unit, Tile tile) {
        Tile oldTile = unit.getTile();
        System.out.println("moving unit [" + unit + "], from tile  [" + oldTile + "] to tile [" + tile + "]");
        removeUnit(unit, oldTile);
        placeUnit(unit, tile);
        onUnitMoved.accept(unit);
    }

    public void setOnUnitMoved(Consumer<Unit> onUnitMoved) {
        this.onUnitMoved = onUnitMoved;
    }
}
