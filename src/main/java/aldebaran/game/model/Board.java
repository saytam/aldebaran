package aldebaran.game.model;

import java.util.*;
import java.util.function.Consumer;

public class Board {

    private Map<Position, Tile> tiles;

    private List<Unit> units;

    private Map<Position, Wall> horizontalWalls;
    private Map<Position, Wall> verticalWalls;

    private Consumer<Unit> onUnitMoved;
    private Random random = new Random(1243823);

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
                TileType type = TileType.GRASS;
                switch (random.nextInt(3)) {
                    case 0:
                        break;
                    case 1:
                        type = TileType.ROCK;
                        break;
                    default:
                        type = TileType.SAND;
                }
                Tile tile = new Tile(type, position);
                tiles.put(position, tile);
            }
        }
    }

    private void initWalls(int width, int heigth) {
        this.horizontalWalls = new HashMap<>();
        this.verticalWalls = new HashMap<>();
        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < heigth + 1; j++) {
                Position position = new Position(i, j);
                if (!(i == width)) {
                    if (random.nextInt(3) % 3 == 0) {
                        horizontalWalls.put(position, new Wall(position, WallOrientation.HORIZONTAL));
                    }
                }
                if (!(j == heigth)) {
                    if (random.nextInt(3) % 3 == 0) {
                        verticalWalls.put(position, new Wall(position, WallOrientation.VERTICAL));
                    }
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

    public Map<Position, Wall> getHorizontalWalls() {
        return horizontalWalls;
    }

    public Map<Position, Wall> getVerticalWalls() {
        return verticalWalls;
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
