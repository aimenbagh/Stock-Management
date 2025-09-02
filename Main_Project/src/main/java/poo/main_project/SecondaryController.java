package poo.main_project;

import com.gembox.spreadsheet.*;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.NotificationPos;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import io.github.palexdev.materialfx.notifications.base.INotification;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.event.MenuEvent;
import org.kordamp.ikonli.javafx.FontIcon;
import static poo.main_project.App.loadFXML;

/**
 * FXML Controller class for the fxml file "secondary.fxml"<br><br>
 * It's the main page's controller It handles all user input and logic for the
 * GUI of the main page<br>
 * Contains the main layout of the app including the menu<br>
 * Allows to switch between scenes<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class SecondaryController implements Initializable {

    private boolean isdrawnin;

    /**
     * The main stage
     */
    public static Stage MainStage;

    private String scenename = "Accueil";

    @FXML
    private ToggleGroup menu;
    @FXML
    private Button mainexit;
    @FXML
    private Button mainminiize;
    @FXML
    private ImageView mainexiticon;
    @FXML
    private ImageView mainminiizeicon;
    @FXML
    private ToggleButton AccueuilButton;
    @FXML
    private Label navigation_label;
    @FXML
    private Label logolabel;
    @FXML
    private VBox pane1;
    @FXML
    private ImageView myimage;
    @FXML
    private FontIcon navigation_icon;
    @FXML
    private AnchorPane search_bar;
    @FXML
    private MFXTextField s;
    @FXML
    private AnchorPane Mainpane;
    @FXML
    private AnchorPane myscene;

    @FXML
    private void exitwindow(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizewindow(ActionEvent event) {
        MainStage = (Stage) mainminiize.getScene().getWindow();
        MainStage.setIconified(true);

    }

    @FXML
    private void mainbutteffectout(MouseEvent event) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0);
        mainexiticon.setEffect(colorAdjust);
        mainminiizeicon.setEffect(colorAdjust);
    }

    @FXML
    private void mainbutteffectin(MouseEvent event) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(1);
        if (mainexit.isHover()) {
            mainexiticon.setEffect(colorAdjust);
        } else {
            mainminiizeicon.setEffect(colorAdjust);
        }
    }
    private static final EventHandler<MouseEvent> consumeMouseEventFilter = (MouseEvent mouseEvent) -> {
        if (((Toggle) mouseEvent.getSource()).isSelected()) {

            mouseEvent.consume();
        }
    };

    private static void addConsumeMouseEventFilter(Toggle toggle) {
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_PRESSED, consumeMouseEventFilter);
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_RELEASED, consumeMouseEventFilter);
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_CLICKED, consumeMouseEventFilter);
    }

    /**
     *
     * @param toggleGroup
     */
    private static void alwaysOneSelected(final ToggleGroup toggleGroup) {
        toggleGroup.getToggles().addListener((ListChangeListener.Change<? extends Toggle> tog) -> {
            while (tog.next()) {

                for (final Toggle addedToggle : tog.getAddedSubList()) {

                    addConsumeMouseEventFilter(addedToggle);
                }
            }
        });
        toggleGroup.getToggles().forEach(SecondaryController::addConsumeMouseEventFilter);
    }

    /**
     * This method is automatically called after the fxml file has been loaded
     * Initilizes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AnchorPane firstpane;
        try {
            firstpane = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            myscene.getChildren().setAll(firstpane);
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        isdrawnin = false;
        ObservableList<Toggle> tog = menu.getToggles();
        alwaysOneSelected(menu);
        navigation_label.setStyle("-fx-font-family:Open Sans ;");
        logolabel.setText("Gestion\rDe Stock");
        navigation_label.setText("Accueil");
        //Set the navigation label accprding to the toggle selected(armed)
        menu.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) -> {
            if (menu.getSelectedToggle() != null) {
                navigation_label.setText(((ToggleButton) menu.getSelectedToggle()).getText());
            }

        });

    }

    @FXML
    private void drawIn(ActionEvent event) throws IOException {
        if (!isdrawnin) {

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.3), pane1);
            translateTransition1.setByX(-160);
            translateTransition1.play();

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.3), logolabel);
            translateTransition2.setByX(-200);
            translateTransition2.play();
            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.3), search_bar);
            translateTransition3.setByX(-135);
            translateTransition3.play();
            TranslateTransition translateTransition4 = new TranslateTransition(Duration.seconds(0.3), navigation_icon);
            translateTransition4.setByX(-135);
            translateTransition4.play();
            TranslateTransition translateTransition5 = new TranslateTransition(Duration.seconds(0.3), navigation_label);
            translateTransition5.setByX(-135);
            translateTransition5.play();

            myimage.setVisible(true);
            isdrawnin = true;

        }
        menu.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) -> {
            if (menu.getSelectedToggle() != null) {
                scenename = ((ToggleButton) menu.getSelectedToggle()).getText();
            }

        });
        AnchorPane pane;

        switch (navigation_label.getText()) {
            case "Approvisionnement":

                pane = FXMLLoader.load(getClass().getResource("Achat.fxml"));
                break;
            case "Vente":

                pane = FXMLLoader.load(getClass().getResource("Vente.fxml"));
                break;
            case "Inventaire":
                pane = FXMLLoader.load(getClass().getResource("Inventaire.fxml"));
                break;
            case "Journal":
                pane = FXMLLoader.load(getClass().getResource("Journal.fxml"));
                break;

            default:
                throw new AssertionError();
        }

        myscene.getChildren().setAll(pane);
        myscene.setVisible(true);

    }

    @FXML
    private void drawOut(ActionEvent event) throws IOException {

        if (isdrawnin) {
            myimage.setVisible(false);

            myscene.setVisible(false);

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.3), pane1);
            translateTransition1.setByX(+160);
            translateTransition1.play();

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.3), logolabel);
            translateTransition2.setByX(+200);
            translateTransition2.play();
            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.3), search_bar);
            translateTransition3.setByX(+135);
            translateTransition3.play();
            TranslateTransition translateTransition4 = new TranslateTransition(Duration.seconds(0.3), navigation_icon);
            translateTransition4.setByX(+135);
            translateTransition4.play();
            TranslateTransition translateTransition5 = new TranslateTransition(Duration.seconds(0.3), navigation_label);
            translateTransition5.setByX(+135);
            translateTransition5.play();
            isdrawnin = false;

        }

        AnchorPane pane;

        switch (navigation_label.getText()) {
            case "Client":
                pane = FXMLLoader.load(getClass().getResource("Client.fxml"));
                break;
            case "Fournisseur":
                pane = FXMLLoader.load(getClass().getResource("Fournisseur.fxml"));
                break;
            case "Accueil":
                pane = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                break;
            default:
                throw new AssertionError();
        }
        myscene.getChildren().setAll(pane);
        myscene.setVisible(true);
    }
}
