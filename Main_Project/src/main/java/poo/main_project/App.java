package poo.main_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * JavaFX App The main class of application.<br><br>
 * The program starts from here and loads the first fxml scene of
 * authentification<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class App extends Application {

    private static double xoffset = 0;
    private static double yoffset = 0;

    /**
     * The main scene to load at the start of the program
     */
    public static Scene scene;

    /**
     * Starts the program Sets the parameters of the window Loads the first
     * scene at the start of the program
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");

        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));//Original primary

        scene = new Scene(root, 735, 440);//Original :primary dimensions :735, 440//secondary 950,675.8

        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed((MouseEvent event) -> {
            xoffset = event.getSceneX();
            yoffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xoffset);
            stage.setY(event.getScreenY() - yoffset);
        });
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        PrimaryController.loginStage = stage;

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
