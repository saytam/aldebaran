package aldebaran.game.helper;

import aldebaran.game.Board;
import aldebaran.game.Tile;
import aldebaran.game.Wall;

import java.util.Optional;

public class WallsForTile {
    public static Optional<Wall> topOf(Board board, Tile tile) {
//        int tileX = tile.getX();
//        int tileY = tile.getY();
//        int wallX = tileX;
//        int wallY = tileY * 2;
//        return board.getWalls().stream().filter(wall -> wall.getX() == wallX && wall.getY() == wallY).findFirst();
        return Optional.empty();
    }

    public Optional<Wall> bottomOf(Tile tile) {
        return Optional.empty();
    }

    public Optional<Wall> leftOf(Tile tile) {
        return Optional.empty();
    }

    public Optional<Wall> rightOf(Tile tile) {
        return Optional.empty();
    }
}
