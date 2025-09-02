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
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

/**
 * FXML Controller class for the fxml file "Inventaire.fxml"<br><br>
 * It handles all user input and logic for the GUI of "Inventaire" page<br>
 * It displays the list of products in the stock, with their state<br>
 * Includes fiters<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class InventaireController implements Initializable {

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }
    /**
     * Collection containing the list of products in the database
     * "Inventaire.xlsx" for further manipulation We use it to construct the
     * table view
     */
    private ArrayList<ArrayList<String>> sourceData = new ArrayList();
    private ArrayList<String> ligne = new ArrayList();
    private int numligne = 0;
    private ExcelWorksheet myworksheet;
    /**
     * Workbook of the excel file "Inventaire.xlsx" which is our main database
     * of the stock
     */
    private ExcelFile myworkbook;

    private static boolean idexist = true;
    /**
     * The table field represents the table view that displays the list of all
     * products in the stock ie : Inventory
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
            ExcelFile workbook = ExcelFile.load("Inventaire_Template.xlsx");

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

            MFXTableColumn<Produit> idColumn = new MFXTableColumn<>("Référence", true, Comparator.comparing(Produit::getId));
            MFXTableColumn<Produit> sousCategcColumn = new MFXTableColumn<>("Sous-Catégorie", true, Comparator.comparing(Produit::getSousCategorie));
            MFXTableColumn<Produit> fabriColumn = new MFXTableColumn<>("Fabriquant", true, Comparator.comparing(Produit::getFabriquant));
            MFXTableColumn<Produit> descripColumn = new MFXTableColumn<>("Déscription", true, Comparator.comparing(Produit::getDescription));
            MFXTableColumn<Produit> quantiteColumn = new MFXTableColumn<>("Quantité", true, Comparator.comparing(Produit::getQuantite));
            MFXTableColumn<Produit> nompColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Produit::getNom_prod));
            MFXTableColumn<Produit> prixUColumn = new MFXTableColumn<>("Prix Unitaire", true, Comparator.comparing(Produit::getPrix_unit));
            MFXTableColumn<Produit> categColumn = new MFXTableColumn<>("Catégorie", true, Comparator.comparing(Produit::getNom_categorie));
            MFXTableColumn<Produit> etatColumn = new MFXTableColumn<>("Etat", true, Comparator.comparing(Produit::getEtat));

            quantiteColumn.setMinWidth(80);
            quantiteColumn.setMaxWidth(80);
            etatColumn.setMinWidth(80);

            etatColumn.setColumnResizable(false);
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

            prixUColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getPrix_unit) {
                {
                    setAlignment(Pos.CENTER_RIGHT);
                }
            });
            prixUColumn.setAlignment(Pos.CENTER_RIGHT);
            etatColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getEtat));

            table.getTableColumns().addAll(idColumn, categColumn, sousCategcColumn, nompColumn, fabriColumn, descripColumn, quantiteColumn, prixUColumn, etatColumn);
            table.getFilters().addAll(
                    new StringFilter<>("Réference", Produit::getId),
                    new StringFilter<>("Catégorie", Produit::getNom_categorie),
                    new StringFilter<>("Sous-Catégorie", Produit::getSousCategorie),
                    new StringFilter<>("Nom", Produit::getNom_prod),
                    new StringFilter<>("Fabricant", Produit::getFabriquant),
                    new StringFilter<>("Déscritption", Produit::getDescription),
                    new IntegerFilter<>("Quantité", Produit::getQuantite),
                    new DoubleFilter<>("Prix Unitaire", Produit::getPrix_unit),
                    new StringFilter<>("Etat", Produit::getEtat)
            );
            /*     table.getSelectionModel().selectionProperty().addListener((MapChangeListener<? super Integer, ? super Produit>) change -> {
                    Produit per= (Produit) table.getSelectionModel().getSelectedValues().get(0);
                    System.out.println(per.toString());
             
             });*/ //Get selected row

            ObservableList<Produit> data = FXCollections.observableArrayList();
            int j = 1;

            for (ArrayList<String> row : sourceData) {
                if ((Integer.parseInt(row.get(6)) > 50)) {

                    data.add(new Produit(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), Integer.parseInt(row.get(6)), Integer.parseInt(row.get(7)), "Bien"));
                    myworksheet.getCell(j, 8).setValue("Bien");
                } else if ((Integer.parseInt(row.get(6))) == 0) {
                    data.add(new Produit(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), Integer.parseInt(row.get(6)), Integer.parseInt(row.get(7)), "Rupture"));
                    myworksheet.getCell(j, 8).setValue("Rupture");
                } else {
                    data.add(new Produit(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), Integer.parseInt(row.get(6)), Integer.parseInt(row.get(7)), "Critique"));
                    myworksheet.getCell(j, 8).setValue("Critique");
                }

                j++;
            }

            table.setItems(data);
            myworkbook.save("Inventaire_Template.xlsx");

        } catch (IOException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
