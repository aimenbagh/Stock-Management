package poo.main_project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.gembox.spreadsheet.CellValueType;
import com.gembox.spreadsheet.ExcelCell;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelRow;
import com.gembox.spreadsheet.ExcelRowCollection;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import static io.github.palexdev.materialfx.utils.others.FunctionalStringConverter.converter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.shaded.apache.poi.ss.usermodel.Workbook;
import poo.main_project.Produit;

/**
 * FXML Controller class for the fxml file "Achat.fxml".<br><br>
 * It handles all user input and logic for the GUI of "Approvisionnement"
 * page:<br>
 * Adds a bought quantity for existing products.<br>
 * Adds a bought quantity for a new product.<br>
 * Displays the list of made purchases.<br>
 *
 * @author E01 G1 INF2 HIS.
 */
public class AchatController implements Initializable {

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    /**
     * The table field represents the table view that displays the list of all
     * new buys
     */
    @FXML
    private MFXTableView table;
    /**
     * The Fieldref field is a combobox which items are all of the IDS of
     * existing products in our database "Inventaire.fxml". The IDS are loaded
     * by code in this class
     */
    @FXML
    private MFXFilterComboBox Fieldref;
    /**
     * The field button is the button "Confirmer" that triggers the buying
     * operation
     */
    @FXML
    private MFXButton bouttonconfirmer;
    @FXML
    private MFXTextField fieldcateg;
    @FXML
    private MFXTextField fieldnom;
    @FXML
    private MFXTextField fieldfabric;
    @FXML
    private MFXTextField fielddescription;
    @FXML
    private MFXTextField fieldqte;
    @FXML
    private MFXTextField fieldprixU;
    @FXML
    private MFXTextField fieldscateg;
    @FXML
    private MFXDatePicker datepick;
    @FXML
    private MFXTextField facture;
    /**
     * The field combofournisseur is a combobox containing a list of items which
     * are all of the suppliers of the store<br>
     * Contains the names of the suppliers stored in "Fournisseur.xlsx"
     */
    @FXML
    private MFXFilterComboBox combofournisseur;
    @FXML
    private Label labelentreprise;
    @FXML
    private Label labelreso;
    @FXML
    private Label labelnumfix;
    @FXML
    private Label labeladresse;
    @FXML
    private Label labelmail;
    @FXML
    private Label labelnumtel;
    private MFXGenericDialog dialogContent3;
    private MFXStageDialog dialog3;
    @FXML
    private AnchorPane grid;
    /**
     * Collection containing the list of products in the database
     * "Inventaire.xlsx" for further manipulation<br>
     * We use it to construct the table view and alter the quantity of a product
     * after a buying opperaton<br>
     */
    private ArrayList<ArrayList<String>> sourceData = new ArrayList();
    /**
     * Collection storing the list of suppliers<br>
     * We use it to populate the combobox containing the list of all suppliers
     * "fournisseur"<br>
     */
    private ArrayList<ArrayList<String>> sourceDatafournisseur = new ArrayList();
    private ArrayList<String> ligne = new ArrayList();
    private ArrayList<String> lignef = new ArrayList();
    private int numligne = 0;
    private int numligne2 = 0;

    private ExcelWorksheet myworksheet;
    /**
     * Workbook of the excel file "Inventaire.xlsx" which is our main database
     * of the stock
     */
    private ExcelFile myworkbook;
    private ExcelWorksheet myjournalsheet;
    /**
     * Workbook of the excel file Journal.xlsx storing the movements in the
     * stock after completing the buying opperation in the form of a bill
     */
    private ExcelFile myjournalbook;
    private ExcelWorksheet myfournisseursheet;
    /**
     * Workbook of the excel file storing the portfolio of providers
     * Fournisseurs.xlsx
     */
    private ExcelFile myfournisseurbook;
    private static boolean idexist = true;

    /**
     * This method is automatically called after the fxml file has been
     * loaded<br><br>
     * Initilizes the controller class, sets up the table view and all of the
     * other controls in the fxml file <br>
     * It also contains all of the logic listed below :<br>
     * Loads the sourceData from the database "Inventaire.xlsx" <br>
     * Loads the sourceDatafournisseur from the database "Fournisseur.xlsx"<br>
     * Defines the logic of the selling operation<br>
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Platform.runLater(() -> {
                this.dialogContent3 = MFXGenericDialogBuilder.build()
                        .setContentText("Quantité nul ou inferieur à 0.\nVeuillez réesayer")
                        .makeScrollable(true)
                        .get();
                this.dialog3 = MFXGenericDialogBuilder.build(dialogContent3)
                        .toStageDialogBuilder()
                        .initOwner((Stage) grid.getScene().getWindow())
                        .initModality(Modality.APPLICATION_MODAL)
                        .setDraggable(true)
                        .setTitle("Dialogs Preview")
                        .setOwnerNode(grid)
                        .setScrimPriority(ScrimPriority.WINDOW)
                        .setScrimOwner(true)
                        .get();

                dialogContent3.addActions(
                        Map.entry(new MFXButton("Confirmer"), event -> {
                            dialog3.close();
                        }),
                        Map.entry(new MFXButton("Annuler"), event -> {
                            dialog3.close();

                        })
                );

                dialogContent3.setMaxSize(400, 200);

            });

            bouttonconfirmer.setDisable(true);

            ExcelFile workbook = ExcelFile.load("Inventaire_Template.xlsx");
            ExcelWorksheet worksheet = workbook.getWorksheet(0);
            myworkbook = workbook;
            myworksheet = worksheet;

            ExcelFile journalbook = ExcelFile.load("Journal.xlsx");
            ExcelWorksheet journalsheet = journalbook.getWorksheet(0);
            myjournalbook = journalbook;
            myjournalsheet = journalsheet;

            ExcelFile fournisseurbook = ExcelFile.load("Fournisseurs.xlsx");
            ExcelWorksheet fournisseursheet = fournisseurbook.getWorksheet(0);
            myfournisseurbook = fournisseurbook;
            myfournisseursheet = fournisseursheet;

            for (int row = 0; row < myworksheet.getUsedCellRange(true).getLastRowIndex(); row++) {
                ArrayList<String> ligne2 = new ArrayList();
                for (int column = 0; column < 9; column++) {
                    ExcelCell cell = worksheet.getCell(row + 1, column);
                    if (cell.getValueType() != CellValueType.NULL) {
                        ligne2.add(cell.getValue().toString());
                    }
                }
                sourceData.add(ligne2);
            }
            for (int row = 0; row < myfournisseursheet.getUsedCellRange(true).getLastRowIndex(); row++) {
                ArrayList<String> lign = new ArrayList();
                for (int column = 0; column < 6; column++) {
                    ExcelCell cell = myfournisseursheet.getCell(row + 1, column);
                    if (cell.getValueType() != CellValueType.NULL) {
                        lign.add(cell.getValue().toString());
                    }
                }
                sourceDatafournisseur.add(lign);

            }

            MFXTableColumn<Produit> idColumn = new MFXTableColumn<>("Référence", true);
            MFXTableColumn<Produit> sousCategcColumn = new MFXTableColumn<>("Sous-Catégorie", true);
            MFXTableColumn<Produit> fabriColumn = new MFXTableColumn<>("Fabriquant", true);
            MFXTableColumn<Produit> descripColumn = new MFXTableColumn<>("Déscription", true);
            MFXTableColumn<Produit> quantiteColumn = new MFXTableColumn<>("Quantité", true);
            MFXTableColumn<Produit> nompColumn = new MFXTableColumn<>("Nom", true);
            MFXTableColumn<Produit> prixTColumn = new MFXTableColumn<>("Prix-Totale", true);
            MFXTableColumn<Produit> categColumn = new MFXTableColumn<>("Catégorie", true);
            MFXTableColumn<Produit> prixUColumn = new MFXTableColumn<>("Prix-Unitaire", true);

            idColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getId));
            categColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getNom_categorie));
            quantiteColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getQuantite) {
                {
                    setAlignment(Pos.CENTER_RIGHT);
                }
            });
            quantiteColumn.setAlignment(Pos.CENTER_RIGHT);

            sousCategcColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getSousCategorie));
            nompColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getNom_prod));
            descripColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getDescription));
            fabriColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getFabriquant));
            prixTColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getPrix_tot) {
                {
                    setAlignment(Pos.CENTER_RIGHT);
                }
            });
            prixTColumn.setAlignment(Pos.CENTER_RIGHT);
            prixUColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getPrix_unit) {
                {
                    setAlignment(Pos.CENTER_RIGHT);
                }
            });
            prixUColumn.setAlignment(Pos.CENTER_RIGHT);

            table.getTableColumns().addAll(idColumn, categColumn, sousCategcColumn, nompColumn, fabriColumn, descripColumn, quantiteColumn, prixUColumn, prixTColumn);
            ObservableList<String> data = FXCollections.observableArrayList();
            for (ArrayList<String> row : sourceData) {
                data.add(row.get(0));

            }
            ObservableList<String> datafounisseur = FXCollections.observableArrayList();
            for (ArrayList<String> row : sourceDatafournisseur) {
                datafounisseur.add(row.get(0));

            }
            StringConverter<String> converter2 = FunctionalStringConverter.to(person -> (person == null) ? "" : person);
            Function<String, Predicate<String>> filterFunction2 = s -> person -> StringUtils.containsIgnoreCase(converter2.toString(person), s);
            combofournisseur.setItems(datafounisseur);
            combofournisseur.setConverter(converter2);
            combofournisseur.setFilterFunction(filterFunction2);
            combofournisseur.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                String item = combofournisseur.getSelectedItem().toString();
                lignef = searchedrow2(sourceDatafournisseur, item);
                labelentreprise.setText(item);
                labelreso.setText(lignef.get(1));
                labelnumtel.setText(lignef.get(2));
                labelnumfix.setText(lignef.get(3));
                labeladresse.setText(lignef.get(4));
                labelmail.setText(lignef.get(5));

            });

            StringConverter<String> converter = FunctionalStringConverter.to(person -> (person == null) ? "" : person);
            Function<String, Predicate<String>> filterFunction = s -> person -> StringUtils.containsIgnoreCase(converter.toString(person), s);
            Fieldref.setItems(data);
            Fieldref.setConverter(converter);
            Fieldref.setFilterFunction(filterFunction);

            Fieldref.setEditable(true);
            Fieldref.setOnCancel(s -> Fieldref.setText((String) Fieldref.getSelectedItem()));
            Fieldref.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                bouttonconfirmer.setDisable(true);

            });

            Fieldref.setOnCommit(s -> {
                bouttonconfirmer.setDisable(false);
                if (!data.contains(s.toString())) // Si la reference du produit et donc le produit lui meme 
                //n'existe pasdans la base de donné
                {
                    // mettre idexit a faux et Ajouter une ligne dans la table excel avec le bouton confirmer
                    idexist = false;

                    data.add(s.toString());// To add it to the item list of the combobox

                } else {
                    //Modifier la propriete quantite dans la ligne de reference selectionné avec le bouton confirmer
                    idexist = true;

                    ligne = searchedrow(sourceData, s.toString());

                    fieldcateg.setText(ligne.get(1));
                    fieldscateg.setText(ligne.get(2));
                    fieldnom.setText(ligne.get(3));
                    fieldfabric.setText(ligne.get(4));
                    fielddescription.setText(ligne.get(5));

                    fieldprixU.setText(ligne.get(7));

                }
                Fieldref.selectItem(s);
                bouttonconfirmer.setDisable(false);

            });

        } catch (IOException ex) {
            Logger.getLogger(AchatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Event handler for the button "Confirmer" which triggers the buy
     * operation<br><br>
     * It alters the stock and creates a new row in the tableview<br>
     *
     * @param event the event object
     */

    @FXML
    private void confirmer(ActionEvent event) throws IOException {
        if (idexist) {
            if (Integer.parseInt(fieldqte.getText()) > 0) {

                int oldvalue = myworksheet.getCell(numligne, 6).getIntValue();
                myworksheet.getCell(numligne, 6).setValue(oldvalue + Integer.parseInt(fieldqte.getText()));
                myworkbook.save("Inventaire_Template.xlsx");
                ObservableList<Produit> datatab = FXCollections.observableArrayList();
                int prixt = Integer.parseInt(fieldprixU.getText()) * Integer.parseInt(fieldqte.getText());

                //  datatab.add(new Produit(Fieldref.getText(),fieldcateg.getText(),fieldscateg.getText(),fieldnom.getText(),fieldfabric.getText(),fielddescription.getText(),Integer.parseInt(fieldqte.getText()),prixt));
                table.getItems().add(new Produit(Fieldref.getText(), fieldcateg.getText(), fieldscateg.getText(), fieldnom.getText(), fieldfabric.getText(), fielddescription.getText(), Integer.parseInt(fieldqte.getText()), Integer.parseInt(fieldprixU.getText()), prixt));
                int lastjindex = myjournalsheet.getUsedCellRange(true).getLastRowIndex();

                myjournalsheet.getCell(lastjindex + 1, 0).setValue(datepick.getValue().toString());
                myjournalsheet.getCell(lastjindex + 1, 1).setValue(facture.getText());
                myjournalsheet.getCell(lastjindex + 1, 2).setValue(Fieldref.getText());
                myjournalsheet.getCell(lastjindex + 1, 3).setValue(fieldcateg.getText());
                myjournalsheet.getCell(lastjindex + 1, 4).setValue(fieldscateg.getText());
                myjournalsheet.getCell(lastjindex + 1, 5).setValue(fieldnom.getText());
                myjournalsheet.getCell(lastjindex + 1, 6).setValue(fieldfabric.getText());
                myjournalsheet.getCell(lastjindex + 1, 7).setValue("+" + fieldqte.getText());
                int prixtt = Integer.parseInt(fieldprixU.getText()) * Integer.parseInt(fieldqte.getText());
                myjournalsheet.getCell(lastjindex + 1, 8).setValue("+" + prixtt);

                myjournalbook.save("Journal.xlsx");

            } else {
                MFXFontIcon warnIcon = new MFXFontIcon("mfx-do-not-enter-circle", 18);
                warnIcon.setColor(Color.RED);
                dialogContent3.setHeaderIcon(warnIcon);
                dialogContent3.setHeaderText("Erreur");
                dialog3.showDialog();
            }
        } else {
            if (Integer.parseInt(fieldqte.getText()) > 0) {

                int prixt = Integer.parseInt(fieldprixU.getText()) * Integer.parseInt(fieldqte.getText());

                table.getItems().add(new Produit(Fieldref.getText(), fieldcateg.getText(), fieldscateg.getText(), fieldnom.getText(), fieldfabric.getText(), fielddescription.getText(), Integer.parseInt(fieldqte.getText()), Integer.parseInt(fieldprixU.getText()), prixt));
                int lastrowindex = myworksheet.getUsedCellRange(true).getLastRowIndex();
                myworksheet.getCell(lastrowindex + 1, 0).setValue(Fieldref.getText());
                myworksheet.getCell(lastrowindex + 1, 1).setValue(fieldcateg.getText());
                myworksheet.getCell(lastrowindex + 1, 2).setValue(fieldscateg.getText());
                myworksheet.getCell(lastrowindex + 1, 3).setValue(fieldnom.getText());
                myworksheet.getCell(lastrowindex + 1, 4).setValue(fieldfabric.getText());
                myworksheet.getCell(lastrowindex + 1, 5).setValue(fielddescription.getText());
                myworksheet.getCell(lastrowindex + 1, 6).setValue(Integer.parseInt(fieldqte.getText()));
                myworksheet.getCell(lastrowindex + 1, 7).setValue(Integer.parseInt(fieldprixU.getText()));
                myworkbook.save("Inventaire_Template.xlsx");
                String info[] = new String[]{Fieldref.getText(), fieldcateg.getText(), fieldscateg.getText(), fieldnom.getText(), fieldfabric.getText(), fielddescription.getText(), fieldqte.getText(), fieldprixU.getText()};
                ArrayList<String> ligne3 = new ArrayList<String>(Arrays.asList(info));
                sourceData.add(ligne3);

                int lastjindex = myjournalsheet.getUsedCellRange(true).getLastRowIndex();

                myjournalsheet.getCell(lastjindex + 1, 0).setValue(datepick.getValue().toString());
                myjournalsheet.getCell(lastjindex + 1, 1).setValue(facture.getText());
                myjournalsheet.getCell(lastjindex + 1, 2).setValue(Fieldref.getText());
                myjournalsheet.getCell(lastjindex + 1, 3).setValue(fieldcateg.getText());
                myjournalsheet.getCell(lastjindex + 1, 4).setValue(fieldscateg.getText());
                myjournalsheet.getCell(lastjindex + 1, 5).setValue(fieldnom.getText());
                myjournalsheet.getCell(lastjindex + 1, 6).setValue(fieldfabric.getText());
                myjournalsheet.getCell(lastjindex + 1, 7).setValue("+" + fieldqte.getText());
                int prixtt = Integer.parseInt(fieldprixU.getText()) * Integer.parseInt(fieldqte.getText());
                myjournalsheet.getCell(lastjindex + 1, 8).setValue("+" + prixtt);

                myjournalbook.save("Journal.xlsx");

            } else {
                MFXFontIcon warnIcon = new MFXFontIcon("mfx-do-not-enter-circle", 18);
                warnIcon.setColor(Color.RED);
                dialogContent3.setHeaderIcon(warnIcon);
                dialogContent3.setHeaderText("Erreur");
                dialog3.showDialog();
            }
        }
    }

    /**
     * Searches for the row in the databases with a specific id. Ie the product
     * with the specified ID<br><br>
     * Takes dataS : Our data source containing all of the products in the
     * database "Inventaire.xlsx" plus the ID of the product we're looking
     * for<br>
     * Returns an arraylist which is a row in the databse containing the product
     * with the specified ID<br>
     * Returns its index
     */
    public ArrayList<String> searchedrow(ArrayList<ArrayList<String>> dataS, String str) {
        ArrayList<String> rw = new ArrayList();
        int j = 1;
        for (ArrayList<String> row : dataS) {

            if (row.get(0).equals(str)) {
                numligne = j;
                rw = (ArrayList) row.clone();
            }
            j++;
        }

        return rw;

    }

    /**
     * Searches for the row in the databases "Fournisseurs.xlxsx" with the
     * specified name in str <br><br>
     * Takes dataS: Our data source containing all of the providers
     * "fournisseur" and the name of the provider we're looking for<br><br>
     * Returns an arraylist which is a row in the database containing the
     * provider with the specified name
     */
    public ArrayList<String> searchedrow2(ArrayList<ArrayList<String>> dataS, String str) {
        ArrayList<String> rw = new ArrayList();

        for (ArrayList<String> row : dataS) {

            if (row.get(0).equals(str)) {

                rw = (ArrayList) row.clone();
            }

        }

        return rw;

    }

}
