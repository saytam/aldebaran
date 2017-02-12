package aldebaran.engine;

import aldebaran.game.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GameEngine {

    enum Phase {
        READY, ANIMATION, PAUSED
    }

    private static final double TILE_HEIGHT = 80;
    private static final double TILE_WIDTH = 80;

    private Stage stage;

    private Map<Tile, StackPane> gameTiles = new HashMap<>();

    private Map<Unit, StackPane> gameUnits = new HashMap<>();

    private Map<Wall, StackPane> gameWalls = new HashMap<>();

    private Game game;

    private Phase phase = Phase.READY;

    public GameEngine(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        game = new Game();
        Board board = game.getBoard();
        board.setOnUnitMoved(onUnitMovedCallback());
        Scene scene = new Scene(draw(board), 1080, 920, Color.BLACK);

        stage.setScene(scene);
        stage.show();
    }

    private Consumer<Unit> onUnitMovedCallback() {
        return (Unit unit) -> {
            System.out.println("move unit: " + unit);
            this.phase = Phase.ANIMATION;
            StackPane stackPane = gameUnits.get(unit);
            double x_offset = unit.getTile().getPosition().getX() * TILE_WIDTH;
            double y_offset = unit.getTile().getPosition().getY() * TILE_HEIGHT;
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    new KeyValue(stackPane.translateXProperty(), x_offset),
                    new KeyValue(stackPane.translateYProperty(), y_offset)));
            timeline.play();
            timeline.setOnFinished(event -> {
                this.phase = Phase.READY;
            });
        };
    }

    private Group draw(Board board) {
        Group mapGroup = new Group();
        System.out.println("GameEngine.draw");
        drawTiles(board);
        drawWalls(board);
        drawUnits(board);

        gameTiles.forEach((tile, stackPane) -> {
            mapGroup.getChildren().add(stackPane);
            registerEventHandlers(tile, stackPane);
        });

        gameWalls.forEach((wall, stackPane) -> {
            mapGroup.getChildren().add(stackPane);
        });

        gameUnits.forEach((unit, stackPane) -> {
            mapGroup.getChildren().add(stackPane);
        });
        return mapGroup;
    }

    private void drawTiles(Board board) {
        board.getTiles().forEach((pos,tile) -> {
            StackPane tileStack = buildGameTile(tile);
            gameTiles.put(tile, tileStack);
        });
    }

    private void drawWalls(Board board) {
        board.getHorizontalWalls().forEach((position,wall) -> {
            StackPane tileStack = buildWall(wall);
            gameWalls.put(wall, tileStack);
        });
        board.getVerticalWalls().forEach((position,wall) -> {
            StackPane tileStack = buildWall(wall);
            gameWalls.put(wall, tileStack);
        });
    }

    private void drawUnits(Board board) {
        board.getUnits().forEach(unit -> {
            StackPane stackPane = buildGameUnit(unit);
            gameUnits.put(unit, stackPane);
        });
    }

    private StackPane buildGameUnit(Unit unit) {
        double x_offset = unit.getTile().getPosition().getX() * TILE_WIDTH;
        double y_offset = unit.getTile().getPosition().getY() * TILE_HEIGHT;
        ImageView imageView = imageForUnit(unit.getType());
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        StackPane stackPane = new StackPane();
        stackPane.setLayoutX(x_offset);
        stackPane.setLayoutY(y_offset);
        stackPane.setDisable(true);
        stackPane.getChildren().add(imageView);
        return stackPane;
    }

    private StackPane buildWall(Wall wall) {
//        System.out.println("wall = [" + wall + "]");
        double x_offset = wall.getPosition().getX() * TILE_WIDTH;
        double y_offset = wall.getPosition().getY() * TILE_HEIGHT - 8;
        if (wall.getOrientation() == WallOrientation.VERTICAL) {
            x_offset -= 8;
            y_offset += 8;
        }
        ImageView imageView = getWallTextureFor(wall);
        StackPane stackPane = new StackPane();
        stackPane.setLayoutX(x_offset);
        stackPane.setLayoutY(y_offset);
        stackPane.setDisable(true);
        stackPane.getChildren().add(imageView);
        return stackPane;
    }

    private void registerEventHandlers(Tile tile, StackPane stackPane) {
        stackPane.setOnMouseEntered(event -> highlight(stackPane));
        stackPane.setOnMouseExited(event -> unHighlight(stackPane));
        stackPane.setOnMouseClicked(event -> handleClick(tile));
    }

    private void handleClick(Tile tile) {
        if (phase == Phase.READY) {
            game.handleClick(tile);
        }
    }

    private StackPane buildGameTile(Tile tile) {
        StackPane tileStack = new StackPane();
        Polygon square = new Polygon();
        double x_offset = tile.getPosition().getX() * TILE_WIDTH;
        double y_offset = tile.getPosition().getY() * TILE_HEIGHT;

        tileStack.setLayoutX(x_offset);
        tileStack.setLayoutY(y_offset);

        square.getPoints().addAll(x_offset, y_offset,
                x_offset + TILE_WIDTH, y_offset,
                x_offset + TILE_WIDTH, y_offset + TILE_HEIGHT,
                x_offset, y_offset + TILE_HEIGHT);
        square.setFill(getPatternForCode(tile.getType()));

        tileStack.getChildren().add(square);
        return tileStack;
    }

    private void highlight(StackPane source) {
        source.setEffect(new Lighting());
    }

    private void unHighlight(StackPane source) {
        source.setEffect(null);
    }

    private ImagePattern getPatternForCode(TileType tileType) {
        switch (tileType) {
            case GRASS:
                return new ImagePattern(new Image("assets/textures/tiles/grass1.png"));
            case SAND:
                return new ImagePattern(new Image("assets/textures/tiles/sand1.png"));
            default:
                return new ImagePattern(new Image("assets/textures/tiles/rock1.png"));
        }
    }

    private ImageView imageForUnit(UnitType unitType) {
        ImageView imageView;
        imageView = new ImageView("assets/images/placeholder.png");
        return imageView;
    }

    private ImageView getWallTextureFor(Wall wall) {
        switch (wall.getOrientation()) {
            case HORIZONTAL:
                return new ImageView(new Image("assets/textures/walls/wall_H_EW.png"));
            default:
                return new ImageView(new Image("assets/textures/walls/wall_V_NS.png"));
        }
    }
}
