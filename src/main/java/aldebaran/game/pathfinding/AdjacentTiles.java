package aldebaran.game.pathfinding;

import aldebaran.game.model.Board;
import aldebaran.game.model.Position;
import aldebaran.game.model.Tile;
import aldebaran.game.model.Wall;

import java.util.Optional;

public class AdjacentTiles {

    public static Optional<Tile> northOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        Wall wall = board.getHorizontalWalls().get(position);
        if (wall != null){
            return Optional.empty();
        }
        return Optional.ofNullable(board.getTiles().get(Position.northOf(position)));
    }

    public static Optional<Tile> southOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        Position position1 = Position.southOf(position);
        Wall wall = board.getHorizontalWalls().get(position1);
        if (wall != null){
            return Optional.empty();
        }
        return Optional.ofNullable(board.getTiles().get(position1));
    }

    public static Optional<Tile> eastOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        Position position1 = Position.eastOf(position);
        Wall wall = board.getVerticalWalls().get(position1);
        if (wall != null){
            return Optional.empty();
        }
        return Optional.ofNullable(board.getTiles().get(position1));
    }

    public static Optional<Tile> westOf(Board board, Tile tile) {
        Position position = tile.getPosition();
        Wall wall = board.getVerticalWalls().get(position);
        if (wall != null){
            return Optional.empty();
        }
        return Optional.ofNullable(board.getTiles().get(Position.westOf(position)));
    }

}
