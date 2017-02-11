package aldebaran;

import aldebaran.engine.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {

//        ApplicationContext context = new AnnotationConfigApplicationContext();
        GameEngine gameEngine = new GameEngine(theStage);
        gameEngine.start();
    }
}
