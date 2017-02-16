package aldebaran.game.model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position northOf(Position position) {
        return new Position(position.getX(), position.getY() - 1);
    }

    public static Position southOf(Position position) {
        return new Position(position.getX(), position.getY() + 1);
    }

    public static Position eastOf(Position position) {
        return new Position(position.getX() + 1, position.getY());
    }

    public static Position westOf(Position position) {
        return new Position(position.getX() - 1, position.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
