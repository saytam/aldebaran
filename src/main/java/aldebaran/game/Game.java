package aldebaran.game;

import aldebaran.game.model.Board;
import aldebaran.game.model.Tile;
import aldebaran.game.model.Unit;

import java.util.Random;

public class Game {

    enum State {
        ROLL, MOVEMENT
    }

    private Board board;

    private State state = State.ROLL;
    private Unit activeUnit = null;
    private Integer lastRoll = 0;

    public Game() {
        this.board = new Board(10, 10);
        this.activeUnit = board.getUnits().get(0);
    }

    public Board getBoard() {
        return board;
    }

    public void handleClick(Tile tile){
        if (state == State.MOVEMENT) {
            Unit unit = tile.getUnit();
            System.out.println("tile = [" + tile + "]");
            if (unit == null && activeUnit != null) {
                board.moveUnit(activeUnit, tile);
            }
//            if (activeUnit == null && unit != null) {
//                activeUnit = unit;
//            }
        } else {
            System.out.println("Not in movement mode");
        }
    }

    public Integer handleRoll(){
        if (state == State.ROLL){
            int result = new Random().nextInt(12)+1;
            System.out.println("roll was:"+result);
            state = State.MOVEMENT;
            lastRoll = result;
            return result;
        }
        System.out.println("Not in rolling mode");
        return null;
    }
}
