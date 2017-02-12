package aldebaran.game.helper;

import aldebaran.game.model.Board;
import aldebaran.game.model.Position;
import aldebaran.game.model.Tile;
import aldebaran.game.model.Wall;

import java.util.Optional;

public class WallsForTile {
    public static Optional<Wall> topOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getHorizontalWalls().get(position));
    }

    public Optional<Wall> bottomOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getHorizontalWalls().get(new Position(position.getX(), position.getY() +1)));
    }

    public Optional<Wall> leftOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getVerticalWalls().get(position));
    }

    public Optional<Wall> rightOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getVerticalWalls().get(new Position(position.getX() +1, position.getY())));
    }
}
