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
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class for the fxml file "client.fxml"<br><br>
 * It handles all user input and logic for the GUI of "Client" page<br>
 * It displays the list of clients and allows to extend it<br>
 *
 * @author E01 G1 INF2 HIS
 */
public class ClientController implements Initializable {

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    /**
     * The table field represents the table view that displays the list of all
     * clients
     */
    @FXML
    private MFXTableView table;
    @FXML
    private MFXTextField fieldtel;
    @FXML
    private MFXTextField fieldresponsable;
    @FXML
    private MFXTextField fieldfix;
    @FXML
    private MFXTextField fieldadresse;
    @FXML
    private MFXTextField fieldmail;
    @FXML
    private MFXTextField fieldclient;
    /**
     * The field that represents the button ajouter "client"
     */
    @FXML
    private MFXButton ajouterbutt;

    /**
     * Collection storing the list of clients from "Clients.xlsx" We use it to
     * consturct the tableview
     */
    private ArrayList<ArrayList<String>> sourceData = new ArrayList();
    private ArrayList<String> ligne = new ArrayList();

    private ExcelWorksheet myworksheet;
    /**
     * Workbook of the excel file storing the portfolio of clients
     * "Clients.xlsx"
     */
    private ExcelFile myworkbook;

    /**
     * This method is automatically called after the fxml file has been
     * loaded<br><br>
     * Initilizes the controller class, sets up the table view and all of the
     * other controls in the fxml file <br>
     * It also contains all of the logic listed below :<br>
     * Loads the sourceData from the database "Client.xlsx"<br>
     * Adds a new clients to that databas if so desired<br>
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            ExcelFile workbook = ExcelFile.load("Clients.xlsx");
            myworkbook = workbook;
            ExcelWorksheet worksheet = workbook.getWorksheet(0);
            myworksheet = worksheet;

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

            MFXTableColumn<Client> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Client::getNomClient));
            MFXTableColumn<Client> responsColumn = new MFXTableColumn<>("Résponsable", true, Comparator.comparing(Client::getResponsable));
            MFXTableColumn<Client> numColumn = new MFXTableColumn<>("Numero Mobile", true, Comparator.comparing(Client::getNumtelmobil));
            MFXTableColumn<Client> fixColumn = new MFXTableColumn<>("Numero Fix", true, Comparator.comparing(Client::getNumtelfix));
            MFXTableColumn<Client> adresseColumn = new MFXTableColumn<>("Adresse", true, Comparator.comparing(Client::getAdresse));
            MFXTableColumn<Client> mailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Client::getEmail));

            nomColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Client::getNomClient));
            responsColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Client::getResponsable));
            numColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Client::getNumtelmobil));
            fixColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Client::getNumtelfix));
            adresseColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Client::getAdresse));
            mailColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Client::getEmail));
            table.getTableColumns().addAll(nomColumn, responsColumn, numColumn, fixColumn, adresseColumn, mailColumn);

            table.getFilters().addAll(
                    new StringFilter<>("Nom", Client::getNomClient),
                    new StringFilter<>("Résponsable", Client::getResponsable),
                    new StringFilter<>("Numero Mobile", Client::getNumtelmobil),
                    new StringFilter<>("Numero Fix", Client::getNumtelfix),
                    new StringFilter<>("Adresse", Client::getAdresse),
                    new StringFilter<>("Email", Client::getEmail)
            );
            ObservableList<Client> data = FXCollections.observableArrayList();
            for (ArrayList<String> row : sourceData) {

                data.add(new Client(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)));

            }
            table.setItems(data);

        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Event handler for the button "Ajouter" which adds a new line in the table
     * view of clients as well as its database
     *
     * @param event the event object
     */
    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        if (!fieldadresse.getText().equals("") && !fieldclient.getText().equals("") && !fieldfix.getText().equals("") && !fieldmail.getText().equals("") && !fieldresponsable.getText().equals("") && !fieldtel.getText().equals("")) {

            table.getItems().add(new Client(fieldclient.getText(), fieldresponsable.getText(), fieldtel.getText(), fieldfix.getText(), fieldadresse.getText(), fieldmail.getText()));

            int lastfindex = myworksheet.getUsedCellRange(true).getLastRowIndex();
            myworksheet.getCell(lastfindex + 1, 0).setValue(fieldclient.getText());
            myworksheet.getCell(lastfindex + 1, 1).setValue(fieldresponsable.getText());
            myworksheet.getCell(lastfindex + 1, 2).setValue(fieldtel.getText());
            myworksheet.getCell(lastfindex + 1, 3).setValue(fieldfix.getText());
            myworksheet.getCell(lastfindex + 1, 4).setValue(fieldadresse.getText());
            myworksheet.getCell(lastfindex + 1, 5).setValue(fieldmail.getText());
            myworkbook.save("Clients.xlsx");

        }
    }

}
