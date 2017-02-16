package aldebaran.game.pathfinding;

import aldebaran.game.model.Board;
import aldebaran.game.model.Position;
import aldebaran.game.model.Tile;
import aldebaran.game.model.Wall;

import java.util.Optional;

public class AdjacentTiles {

    public static Optional<Tile> northOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getTiles().get(Position.northOf(position)));
    }

    public static Optional<Tile> southOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getTiles().get(Position.southOf(position)));
    }

    public static Optional<Tile> eastOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getTiles().get(Position.eastOf(position)));
    }

    public static Optional<Tile> westOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        return Optional.ofNullable(board.getTiles().get(Position.westOf(position)));
    }

}
