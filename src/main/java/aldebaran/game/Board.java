package aldebaran.game;

import java.util.*;
import java.util.function.Consumer;

public class Board {

    private Map<Position, Tile> tiles;

    private List<Unit> units;

    private List<Wall> walls;

    private Consumer<Unit> onUnitMoved;

    public Board(int width, int heigth) {
        initTiles(width, heigth);
        initWalls(width, heigth);
        this.units = new ArrayList<>();
        units.add(new Unit(UnitType.DUMMY));
        placeUnit(units.get(0), tiles.get(new Position(0, 0)));
    }

    private void initTiles(int width, int heigth) {
        this.tiles = new HashMap<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                Position position = new Position(i, j);
                Tile tile = new Tile(TileType.SAND, position);
                tiles.put(position, tile);
            }
        }
    }

    private void initWalls(int width, int heigth) {
        Random random = new Random();
        this.walls = new ArrayList<>();
        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < heigth * 2 + 1; j++) {
                WallOrientation orientation = WallOrientation.VERTICAL;
                if (j % 2 == 0) {
                    orientation = WallOrientation.HORIZONTAL;
                }
                if (!(orientation == WallOrientation.HORIZONTAL && i == width)) {
                    walls.add(new Wall(i, j, orientation));
                }
            }
        }
    }

    public Map<Position, Tile> getTiles() {
        return tiles;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void placeUnit(Unit unit, Tile tile) {
        unit.setTile(tile);
        tile.addUnit(unit);
    }

    public void removeUnit(Unit unit, Tile tile) {
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
