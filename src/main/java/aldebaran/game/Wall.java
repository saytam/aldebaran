package aldebaran.game;

public class Wall {
    private int x;
    private int y;
    private WallOrientation orientation;

    public Wall(int x, int y, WallOrientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public WallOrientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }
}
