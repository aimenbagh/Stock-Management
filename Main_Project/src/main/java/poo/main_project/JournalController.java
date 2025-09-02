/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package poo.main_project;

import com.gembox.spreadsheet.CellValueType;
import com.gembox.spreadsheet.ExcelCell;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

/**
 * FXML Controller class for the fxml file "Journal.fxml"<br><br>
 * It handles all user input and logic for the GUI of "Journal" page<br>
 * It displays the list of all ins and outs of the stock in the form of
 * bills<br>
 * Includes fiters<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class JournalController implements Initializable {

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }
    /**
     * Collection containing the list of products in the database "Journal.xlsx"
     * for further manipulation We use it to construct the table view
     */
    private ArrayList<ArrayList<String>> sourceData = new ArrayList();
    private ArrayList<String> ligne = new ArrayList();
    private int numligne = 0;
    private ExcelWorksheet myworksheet;
    /**
     * Workbook of the excel file "Journal.xlsx"
     */
    private ExcelFile myworkbook;

    private static boolean idexist = true;
    /**
     * The table field represents the table view that displays the list of the
     * movements of the stock
     */
    @FXML
    private MFXTableView table;

    /**
     * This method is automatically called after the fxml file has been
     * loaded<br><br>
     * Initilizes the controller class, sets up the table view  <br>
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            ExcelFile workbook = ExcelFile.load("Journal.xlsx");

            myworkbook = workbook;
            ExcelWorksheet worksheet = workbook.getWorksheet(0);
            myworksheet = worksheet;
            for (int row = 0; row < myworksheet.getUsedCellRange(true).getLastRowIndex(); row++) {
                ArrayList<String> ligne2 = new ArrayList();
                for (int column = 0; column < 10; column++) {
                    ExcelCell cell = worksheet.getCell(row + 1, column);
                    if (cell.getValueType() != CellValueType.NULL) {
                        ligne2.add(cell.getValue().toString());
                    }
                }
                sourceData.add(ligne2);
            }

            MFXTableColumn<Facture> dateColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(Facture::getDate));
            MFXTableColumn<Facture> factureColumn = new MFXTableColumn<>("Facture", true, Comparator.comparing(Facture::getIdfacture));
            MFXTableColumn<Facture> idColumn = new MFXTableColumn<>("Référence", true, Comparator.comparing(Facture::getId));
            MFXTableColumn<Facture> categColumn = new MFXTableColumn<>("Catégorie", true, Comparator.comparing(Facture::getCategorie));
            MFXTableColumn<Facture> souscategColumn = new MFXTableColumn<>("Sous-Catégorie", true, Comparator.comparing(Facture::getSouscategorie));
            MFXTableColumn<Facture> nomfColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Facture::getNom));

            MFXTableColumn<Facture> quantiteColumn = new MFXTableColumn<>("Qty", true, Comparator.comparing(Facture::getQuantite));
            MFXTableColumn<Facture> valeurColumn = new MFXTableColumn<>("Valeur", true, Comparator.comparing(Facture::getValeur));

            dateColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getDate));
            factureColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getIdfacture));
            idColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getId));
            categColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getCategorie));
            souscategColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getSouscategorie));
            nomfColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getNom));

            quantiteColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getQuantite) {
                {
                    setAlignment(Pos.CENTER_LEFT);
                }
            });
            quantiteColumn.setAlignment(Pos.CENTER_LEFT);

            valeurColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Facture::getValeur) {
                {
                    setAlignment(Pos.CENTER_LEFT);

                }
            });
            valeurColumn.setAlignment(Pos.CENTER_LEFT);

            table.getTableColumns().addAll(dateColumn, factureColumn, idColumn, categColumn, souscategColumn, nomfColumn, quantiteColumn, valeurColumn);
            table.getFilters().addAll(
                    new StringFilter<>("Date", Facture::getDate),
                    new StringFilter<>("Facture", Facture::getIdfacture),
                    new StringFilter<>("Référence", Facture::getId),
                    new StringFilter<>("Catégorie", Facture::getCategorie),
                    new StringFilter<>("Sous-Catégorie", Facture::getSouscategorie),
                    new StringFilter<>("Nom", Facture::getNom),
                    new StringFilter<>("Qty", Facture::getQuantite),
                    new StringFilter<>("Valeur", Facture::getValeur)
            );
            /*     table.getSelectionModel().selectionProperty().addListener((MapChangeListener<? super Integer, ? super Facture>) change -> {
                    Facture per= (Facture) table.getSelectionModel().getSelectedValues().get(0);
                    System.out.println(per.toString());
             
             });*/ //Get selected row

            ObservableList<Facture> data = FXCollections.observableArrayList();

            for (ArrayList<String> row : sourceData) {

                data.add(new Facture(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8)));

            }

            table.setItems(data);

        } catch (IOException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }

}
