package aldebaran.game.helper;

import aldebaran.game.model.Board;
import aldebaran.game.model.Tile;
import aldebaran.game.pathfinding.AdjacentTiles;

import java.util.*;

public class CalculateAccessibleTiles {

    public static Set<Tile> forTile(Tile startTile, Board board, int movement){
        Set<Tile> accessibleTiles = new HashSet<>();
        accessibleTiles.add(startTile);
        int step = 1;
        while (step <= movement){
            Optional<Tile> northernTile = AdjacentTiles.northOf(board, startTile);
            northernTile.ifPresent(accessibleTiles::add);
            Optional<Tile> easternTile = AdjacentTiles.eastOf(board, startTile);
            easternTile.ifPresent(accessibleTiles::add);
            Optional<Tile> southernTile = AdjacentTiles.southOf(board, startTile);
            southernTile.ifPresent(accessibleTiles::add);
            Optional<Tile> westernTile = AdjacentTiles.westOf(board, startTile);
            westernTile.ifPresent(accessibleTiles::add);
        }
        return accessibleTiles;
    }


}
