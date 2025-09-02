package poo.main_project;

import com.gembox.spreadsheet.SpreadsheetInfo;
import com.gembox.spreadsheet.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static poo.main_project.App.scene;

/**
 * FXML Controller class for the fxml file "primary.fxml"<br><br>
 * It's the authentification page's controller<br>
 * This class handles all user input and logic for the GUI of "Authentification"
 * page<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class PrimaryController {

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    private static double xoffset = 0;
    private static double yoffset = 0;

    /**
     * The main stage of the authentification page
     */
    public static Stage loginStage;

    /**
     * The main scene of the authentification page
     */
    public static Scene scenes;

    @FXML
    private MFXTextField username;
    @FXML
    private MFXPasswordField password;
    @FXML
    private Label message;
    @FXML
    private MFXButton exitButt;
    @FXML
    private ImageView exiticon;
    @FXML
    private MFXButton loginbutt;

    /**
     * Event handler of the connexion button Sets up input controls for the
     * user and password fields Allows to switch to the main application after a
     * successful authentification
     */
    @FXML
    private void switchToSecondary() throws IOException {
        username.setStyle("-fx-border-color:transparent;-fx-border-width:1.5 1.5 1.5 1.5; -fx-border-radius:26;");
        password.setStyle("-fx-border-color:transparent;-fx-border-width:1.5 1.5 1.5 1.5;-fx-border-radius:26;");

        if (username.getText().equals("bachir") && password.getText().equals("bachir2023")) {
            loginStage.close();
            Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
            Stage stage = new Stage();

            stage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed((MouseEvent event) -> {
                xoffset = event.getSceneX();
                yoffset = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xoffset);
                stage.setY(event.getScreenY() - yoffset);
            });
            scenes = new Scene(root, 950, 675.85);
            scenes.setFill(Color.TRANSPARENT);
            stage.setScene(scenes);
            stage.show();
            SecondaryController.MainStage = stage;

        } else {

            message.setStyle("-fx-text-fill:red;");
            if (username.getText().equals("lobster") == false) {
                message.setStyle("-fx-text-fill:red;-fx-font-size:14;");
                message.setText("Identifiant ou Mot de passe erroné.\n               Veuillez ressayer");
                username.setStyle("-fx-border-color:red;-fx-border-width:1.5 1.5 1.5 1.5; -fx-border-radius:26;");

            }
            if (password.getText().equals("gulag") == false) {
                message.setStyle("-fx-text-fill:red;-fx-font-size:14;");
                message.setText("Identifiant ou Mot de passe erroné.\n               Veuillez ressayer");
                password.setStyle("-fx-border-color:red;-fx-border-width:1.5 1.5 1.5 1.5;-fx-border-radius:26;");
            }
            if (username.getText().isBlank()) {
                username.setStyle("-fx-border-color:red;-fx-border-width:1.5 1.5 1.5 1.5; -fx-border-radius:26;");
                message.setStyle("-fx-font-size:14;-fx-text-fill:red;");
                message.setText("Champ(s) manquant(s).\n    Veuillez ressayer");
            }
            if (password.getText().isBlank()) {
                password.setStyle("-fx-border-color:red;-fx-border-width:1.5 1.5 1.5 1.5; -fx-border-radius:26;");
                message.setStyle("-fx-font-size:14;-fx-text-fill:red;");
                message.setText("Champ(s) manquant(s).\n    Veuillez ressayer");
            }

        }

    }

    @FXML
    private void exitwindow(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void butteffectin(MouseEvent event) {

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(1);
        if (exitButt.isHover()) {
            exiticon.setEffect(colorAdjust);
        } else {
            loginbutt.setEffect(colorAdjust);
        }
    }

    @FXML
    private void butteffectout(MouseEvent event) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0);
        exiticon.setEffect(colorAdjust);
        loginbutt.setEffect(colorAdjust);

    }

}
