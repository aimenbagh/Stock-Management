package poo.main_project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.gembox.spreadsheet.CellValueType;
import com.gembox.spreadsheet.ExcelCell;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import com.sun.source.tree.BreakTree;
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
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * FXML Controller class for the fxml file "Vente.fxml"<br><br>
 * It all user input and logic for the GUI of "Vente" page<br>
 * Sells a quantity of an existing product<br>
 * Displays the list of made sells<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class VenteController implements Initializable {

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }
    /**
     * The table field represents the table view that displays the list of all
     * new sells "Vente"
     */

    @FXML
    private MFXTableView table;
    @FXML
    private MFXTextField fieldqte;
    /**
     * The Fieldref field is a combobox which items are all of the IDS of
     * existing products in our database "Inventaire.fxml". The IDS are loaded
     * by code in this class
     */
    @FXML
    private MFXFilterComboBox Fieldref;
    @FXML
    private MFXTextField fieldprixU;
    /**
     * The field button is the button "Confirmer" that triggers the sell
     * operation
     */
    @FXML
    private MFXButton bouttonconfirmer;
    @FXML
    private Label categlabel;
    @FXML
    private Label scateglabel;
    @FXML
    private Label nomlabel;
    @FXML
    private Label fabriclabel;
    @FXML
    private Label descriplabel;
    @FXML
    private MFXDatePicker datepick;
    @FXML
    private MFXTextField facture;
    /**
     * The field combofclient field is a combobox containing a list of items
     * which are all of the clients of the store Contains the names of the
     * clients stored in "Clients.xlsx"
     */
    @FXML
    private MFXFilterComboBox comboclient;
    @FXML
    private Label labelnomclient;
    @FXML
    private Label labelnomrespo;
    @FXML
    private Label labelnumtel;
    @FXML
    private Label labelnumfix;
    @FXML
    private Label labeladresse;
    @FXML
    private Label labelmail;
    private MFXGenericDialog dialogContent;
    private MFXGenericDialog dialogContent1;
    private MFXGenericDialog dialogContent2;
    private MFXGenericDialog dialogContent3;
    private MFXStageDialog dialog;
    private MFXStageDialog dialog1;
    private MFXStageDialog dialog2;
    private MFXStageDialog dialog3;

    @FXML
    private AnchorPane grid;

    /**
     * Collection containing the list of products in the database
     * "Inventaire.xlsx" for further manipulation We use it to construct the
     * table view and alter the quantity of a product after a sell opperaton
     */
    private ArrayList<ArrayList<String>> sourceData = new ArrayList();
    /**
     * Collection storing the list of clients from "Clients.xlsx" We use it to
     * populate the combobox containing the list of all clients
     */
    private ArrayList<ArrayList<String>> sourceDataclient = new ArrayList();
    private ArrayList<String> ligne = new ArrayList();
    private ArrayList<String> lignec = new ArrayList();
    private int numligne = 0;

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
    private ExcelWorksheet myclientsheet;
    /**
     * Workbook of the excel file storing the portfolio of clients
     * "Clients.xlsx"
     */
    private ExcelFile myclientbook;
    private ActionEvent myevent;

    static boolean idexist = true;

    /**
     * This method is automatically called after the fxml file has been
     * loaded<br><br>
     * Initilizes the controller class, sets up the table view and all of the
     * other controls in the fxml file <br>
     * It also contains all of the logic listed below :<br>
     * Loads the sourceData from the database "Inventaire.xlsx" <br>
     * Loads the sourceDatafournisseur from the database "Fournisseur.xlsx"<br>
     * Defines the logic of the buying operation
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Platform.runLater(() -> {

                this.dialogContent = MFXGenericDialogBuilder.build()
                        .setContentText("\n\nLe stock de ce produit sera écoulé\nVoulez vous toujour effectuer\nvotre opération ?")
                        .makeScrollable(true)
                        .get();
                this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                        .toStageDialogBuilder()
                        .initOwner((Stage) grid.getScene().getWindow())
                        .initModality(Modality.APPLICATION_MODAL)
                        .setDraggable(true)
                        .setTitle("Dialogs Preview")
                        .setOwnerNode(grid)
                        .setScrimPriority(ScrimPriority.WINDOW)
                        .setScrimOwner(true)
                        .get();

                dialogContent.addActions(
                        Map.entry(new MFXButton("Confirmer"), event -> {
                            dialog.close();
                        }),
                        Map.entry(new MFXButton("Annuler"), event -> {
                            myevent.consume();

                            dialog.close();

                        })
                );

                dialogContent.setMaxSize(400, 200);

                this.dialogContent1 = MFXGenericDialogBuilder.build()
                        .setContentText("\n\nLe stock de ce produit va atteindre une quantité critique \n inferieur à 50.\n Voulez vous toujour effectuer cette opération ?")
                        .makeScrollable(true)
                        .get();
                this.dialog1 = MFXGenericDialogBuilder.build(dialogContent1)
                        .toStageDialogBuilder()
                        .initOwner((Stage) grid.getScene().getWindow())
                        .initModality(Modality.APPLICATION_MODAL)
                        .setDraggable(true)
                        .setTitle("Dialogs Preview")
                        .setOwnerNode(grid)
                        .setScrimPriority(ScrimPriority.WINDOW)
                        .setScrimOwner(true)
                        .get();

                dialogContent1.addActions(
                        Map.entry(new MFXButton("Confirmer"), event -> {
                            dialog1.close();
                        }),
                        Map.entry(new MFXButton("Annuler"), event -> {
                            myevent.consume();

                            dialog1.close();

                        })
                );

                dialogContent1.setMaxSize(400, 200);

                this.dialogContent2 = MFXGenericDialogBuilder.build()
                        .setContentText("\n\nQuantité en stock non suffisante pour effectuer votre achat")
                        .makeScrollable(true)
                        .get();
                this.dialog2 = MFXGenericDialogBuilder.build(dialogContent2)
                        .toStageDialogBuilder()
                        .initOwner((Stage) grid.getScene().getWindow())
                        .initModality(Modality.APPLICATION_MODAL)
                        .setDraggable(true)
                        .setTitle("Dialogs Preview")
                        .setOwnerNode(grid)
                        .setScrimPriority(ScrimPriority.WINDOW)
                        .setScrimOwner(true)
                        .get();

                dialogContent2.addActions(
                        Map.entry(new MFXButton("Confirmer"), event -> {
                            dialog2.close();
                        }),
                        Map.entry(new MFXButton("Annuler"), event -> {

                            dialog2.close();

                        })
                );

                dialogContent2.setMaxSize(400, 200);

                this.dialogContent3 = MFXGenericDialogBuilder.build()
                        .setContentText("Quantité nul.\nVeuillez réesayer")
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
            myworksheet = worksheet;
            myworkbook = workbook;

            ExcelFile journalbook = ExcelFile.load("Journal.xlsx");
            ExcelWorksheet journalsheet = journalbook.getWorksheet(0);
            myjournalbook = journalbook;
            myjournalsheet = journalsheet;

            ExcelFile clientbook = ExcelFile.load("Clients.xlsx");
            ExcelWorksheet clientsheet = clientbook.getWorksheet(0);
            myclientbook = clientbook;
            myclientsheet = clientsheet;

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
            for (int row = 0; row < myclientsheet.getUsedCellRange(true).getLastRowIndex(); row++) {
                ArrayList<String> lign = new ArrayList();
                for (int column = 0; column < 6; column++) {
                    ExcelCell cell = myclientsheet.getCell(row + 1, column);
                    if (cell.getValueType() != CellValueType.NULL) {
                        lign.add(cell.getValue().toString());
                    }
                }
                sourceDataclient.add(lign);

            }

            MFXTableColumn<Produit> idColumn = new MFXTableColumn<>("Référence", true);
            MFXTableColumn<Produit> sousCategcColumn = new MFXTableColumn<>("Sous-Catégorie", true);
            MFXTableColumn<Produit> fabriColumn = new MFXTableColumn<>("Fabriquant", true);
            MFXTableColumn<Produit> descripColumn = new MFXTableColumn<>("Déscription", true);
            MFXTableColumn<Produit> quantiteColumn = new MFXTableColumn<>("Quantité", true);
            MFXTableColumn<Produit> nompColumn = new MFXTableColumn<>("Nom", true);
            MFXTableColumn<Produit> prixTColumn = new MFXTableColumn<>("Prix-Totale", true);
            MFXTableColumn<Produit> categColumn = new MFXTableColumn<>("Catégorie", true);
            MFXTableColumn<Produit> prixUColumn = new MFXTableColumn<>("Prix-Totale", true);

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
            prixUColumn.setAlignment(Pos.CENTER_RIGHT);
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

            ObservableList<String> dataclient = FXCollections.observableArrayList();
            for (ArrayList<String> row : sourceDataclient) {
                dataclient.add(row.get(0));

            }

            StringConverter<String> converter2 = FunctionalStringConverter.to(person -> (person == null) ? "" : person);
            Function<String, Predicate<String>> filterFunction2 = s -> person -> StringUtils.containsIgnoreCase(converter2.toString(person), s);
            comboclient.setItems(dataclient);
            comboclient.setConverter(converter2);
            comboclient.setFilterFunction(filterFunction2);
            comboclient.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                String item = comboclient.getSelectedItem().toString();
                lignec = searchedrow2(sourceDataclient, item);
                labelnomclient.setText(item);
                labelnomrespo.setText(lignec.get(1));
                labelnumtel.setText(lignec.get(2));
                labelnumfix.setText(lignec.get(3));
                labeladresse.setText(lignec.get(4));
                labelmail.setText(lignec.get(5));

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

                if (!data.contains(s.toString())) // Le produit n'existe pas
                {
                    // mettre idexit a faux 
                    idexist = false;
                    bouttonconfirmer.setDisable(true);

                } else {
                    //Modifier la propriete quantite dans la ligne de reference selectionné avec le bouton confirmer
                    idexist = true;
                    bouttonconfirmer.setDisable(false);
                    Fieldref.selectItem(s);

                    ligne = searchedrow(sourceData, s.toString());

                    categlabel.setText(ligne.get(1));
                    scateglabel.setText(ligne.get(2));
                    nomlabel.setText(ligne.get(3));
                    fabriclabel.setText(ligne.get(4));
                    descriplabel.setText(ligne.get(5));

                    fieldprixU.setText(ligne.get(7));

                }

            });

        } catch (IOException ex) {
            Logger.getLogger(AchatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Event handler for the button "Confirmer" which triggers the sell
     * operation It alters the stock and creates a new row in the tableview
     *
     * @param event the event object
     */
    @FXML
    private void confirmer(ActionEvent event) throws IOException {
        if (idexist) {
            if (Integer.parseInt(fieldqte.getText()) > 0) {
                myevent = event;

                int oldvalue = myworksheet.getCell(numligne, 6).getIntValue();
                if ((oldvalue - Integer.parseInt(fieldqte.getText())) >= 0) {

                    if ((oldvalue - Integer.parseInt(fieldqte.getText())) < 50) {
                        if ((oldvalue - Integer.parseInt(fieldqte.getText())) == 0) {
                            MFXFontIcon warnIcon = new MFXFontIcon("mfx-do-not-enter-circle", 18);
                            warnIcon.setColor(Color.RED);
                            dialogContent.setHeaderIcon(warnIcon);
                            dialogContent.setHeaderText("Avertissement");

                            dialog.showAndWait();
                            if (event.isConsumed()) {
                                return;
                            }

                        } else {
                            MFXFontIcon warnIcon = new MFXFontIcon("mfx-exclamation-circle-filled", 18);
                            warnIcon.setColor(Color.DARKORANGE);
                            dialogContent1.setHeaderIcon(warnIcon);
                            dialogContent1.setHeaderText("Avertissement");
                            dialog1.showAndWait();
                            if (event.isConsumed()) {
                                return;
                            }
                        }

                    }
                    myworksheet.getCell(numligne, 6).setValue(oldvalue - Integer.parseInt(fieldqte.getText()));
                    myworkbook.save("Inventaire_Template.xlsx");
                    ObservableList<Produit> datatab = FXCollections.observableArrayList();
                    int prixt = Integer.parseInt(fieldprixU.getText()) * Integer.parseInt(fieldqte.getText());

                    //  datatab.add(new Produit(Fieldref.getText(),fieldcateg.getText(),fieldscateg.getText(),fieldnom.getText(),fieldfabric.getText(),fielddescription.getText(),Integer.parseInt(fieldqte.getText()),prixt));
                    table.getItems().add(new Produit(Fieldref.getText(), categlabel.getText(), scateglabel.getText(), nomlabel.getText(), fabriclabel.getText(), descriplabel.getText(), Integer.parseInt(fieldqte.getText()), Integer.parseInt(fieldprixU.getText()), prixt));
                    int lastjindex = myjournalsheet.getUsedCellRange(true).getLastRowIndex();

                    myjournalsheet.getCell(lastjindex + 1, 0).setValue(datepick.getValue().toString());
                    myjournalsheet.getCell(lastjindex + 1, 1).setValue(facture.getText());
                    myjournalsheet.getCell(lastjindex + 1, 2).setValue(Fieldref.getText());
                    myjournalsheet.getCell(lastjindex + 1, 3).setValue(categlabel.getText());
                    myjournalsheet.getCell(lastjindex + 1, 4).setValue(scateglabel.getText());
                    myjournalsheet.getCell(lastjindex + 1, 5).setValue(nomlabel.getText());
                    myjournalsheet.getCell(lastjindex + 1, 6).setValue(fabriclabel.getText());
                    myjournalsheet.getCell(lastjindex + 1, 7).setValue("-" + fieldqte.getText());
                    int prixtt = Integer.parseInt(fieldprixU.getText()) * Integer.parseInt(fieldqte.getText());
                    myjournalsheet.getCell(lastjindex + 1, 8).setValue("-" + prixtt);

                    myjournalbook.save("Journal.xlsx");

                } else {
                    MFXFontIcon warnIcon = new MFXFontIcon("mfx-do-not-enter-circle", 18);
                    warnIcon.setColor(Color.RED);
                    dialogContent2.setHeaderIcon(warnIcon);
                    dialogContent2.setHeaderText("Erreur");
                    dialog2.showDialog();
                }

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
     * database "Inventaire.xlsx" and the ID of the product we're looking
     * for<br>
     * returns an ArrayList of strings which is a row in the databse containing
     * the product with the specified ID<br>
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
     * Searches for the row in the databases "Clients.xlxsx" with the specific
     * name Takes : Our data source containing all of the providers "client" and
     * the name of the cleint we're looking for Retruns an ArrayList of strings
     * which is a row in the database containing clients with the specified name
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
