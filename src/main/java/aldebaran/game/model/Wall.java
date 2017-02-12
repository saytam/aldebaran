package aldebaran.game.model;

public class Wall {
    private Position position;
    private WallOrientation orientation;

    public Wall(Position position, WallOrientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public WallOrientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "position=" + position +
                ", orientation=" + orientation +
                '}';
    }
}
