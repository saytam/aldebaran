package aldebaran.game;

public class Game {

    enum State {
        PLAYER_TURN
    }

    private Board board;

    private State state = State.PLAYER_TURN;
    private Unit activeUnit = null;

    private boolean hasChanged = false;

    public Game() {
        this.board = new Board(10, 10);
        this.activeUnit = board.getUnits().get(0);
    }

    public Board getBoard() {
        return board;
    }

    public void handleClick(Tile tile){
        Unit unit = tile.getUnit();
        System.out.println("tile = [" + tile + "]");
        if (unit == null && activeUnit != null){
            board.moveUnit(activeUnit, tile);
        }
        if (activeUnit == null && unit != null){
            activeUnit = unit;
        }
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }
}
