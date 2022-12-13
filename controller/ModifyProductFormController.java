package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lsc482.lsc482.InventoryApplication;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.*;
import static model.Inventory.lookupPart;

/** This class creates a form for a user to modify the information of an existing product in the inventory.
 *
 * @author Louie Sanchez
 *
 */
public class ModifyProductFormController implements Initializable {

    Stage stage;
    Parent scene;

    Product selectedProduct;
    @FXML
    private ObservableList<Part> selectedProductAssociatedParts = FXCollections.observableArrayList();
    @FXML
    private TextField productIdTXT;
    @FXML
    private TextField productNameTXT;
    @FXML
    private TextField productInventoryTXT;
    @FXML
    private TextField productPriceTXT;
    @FXML
    private TextField productMaxTXT;
    @FXML
    private TextField productMinTXT;
    @FXML
    private TextField searchPartsTableTXT;
    @FXML
    private TableColumn<Part, Integer> partIdCOL;
    @FXML
    private TableColumn<Part, String> partNameCOL;
    @FXML
    private TableColumn<Part, Double> partCostCOL;
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCOL;
    @FXML
    private TableView<Part> associatedPartsTableView;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn associatedPartIdCOL;
    @FXML
    private TableColumn associatedPartNameCOL;
    @FXML
    private TableColumn associatedPartInventoryLevelCOL;
    @FXML
    private TableColumn associatedPartCostCOL;
    @FXML
    private Label errorTextSearchBar;
    @FXML
    private Label errorText;

    /** This redirects a user back to the main form. */
    @FXML
    void onActionMainForm (ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will not save any changes. Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(InventoryApplication.class.getResource("mainform-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /** This saves a user's input of the textfields and updates the product's information. After updating the product, a user is redirected back to the main form. */
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(productIdTXT.getText());
            String name = productNameTXT.getText();
            Double price = Double.parseDouble(productPriceTXT.getText());
            int stock = Integer.parseInt(productInventoryTXT.getText());
            int max = Integer.parseInt(productMaxTXT.getText());
            int min = Integer.parseInt(productMinTXT.getText());

            if (max < min || stock < min || max < stock) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Min should be less than Max; and Inv should be between those two values.");
                alert.showAndWait();
                return;
            }

            Product updateSelectedProduct = new Product(id, name, price, stock, min, max);
            for(Part part : selectedProductAssociatedParts) {
                updateSelectedProduct.addAssociatedPart(part);
            }
            updateProduct(id, updateSelectedProduct);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(InventoryApplication.class.getResource("mainform-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid values into the text fields.");
            alert.showAndWait();
        }
    }

    /** This adds a user selected part from the parts table to the associated list of a product. */
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
        selectedProductAssociatedParts.add(partsTableView.getSelectionModel().getSelectedItem());
        associatedPartsTableView.setItems(selectedProductAssociatedParts);
    }

    /** This removes a user selected part from the the associated list of a product. */
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the associated part. Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            selectedProductAssociatedParts.remove(associatedPartsTableView.getSelectionModel().getSelectedItem());
        }
    }

    /** This lets a user search the parts table by part ID or part name. */
    @FXML
    void onActionSearchParts(ActionEvent event) {
        try {
            errorTextSearchBar.setText("");
            String query = searchPartsTableTXT.getText();
            ObservableList<Part> parts = lookupPart(query);

            if (parts.size() == 0) {
                int idQuery = Integer.parseInt(query);
                parts = lookupPart(idQuery);
            }

            partsTableView.setItems(parts);
            searchPartsTableTXT.setText("");

            if(parts.size() == 0) {
                errorTextSearchBar.setText("Part not found.");
                partsTableView.setItems(getAllParts());
            }
        }
        catch(NumberFormatException exception) {
            errorTextSearchBar.setText("Part not found.");
            partsTableView.setItems(getAllParts());
        }
    }

    /** This obtains the current information of an existing product and displays it in the textfields.
     * @param product the product to obtain information about
     */
    public void sendProduct(Product product) {
        selectedProduct = product;
        productIdTXT.setText(String.valueOf(selectedProduct.getId()));
        productNameTXT.setText(selectedProduct.getName());
        productPriceTXT.setText(String.valueOf(selectedProduct.getPrice()));
        productInventoryTXT.setText(String.valueOf(selectedProduct.getStock()));
        productMaxTXT.setText(String.valueOf(selectedProduct.getMax()));
        productMinTXT.setText(String.valueOf(selectedProduct.getMin()));
        for(Part part : selectedProduct.getAllAssociatedParts()) {
            selectedProductAssociatedParts.add(part);
            associatedPartsTableView.setItems(selectedProductAssociatedParts);
        }
    }

    /** This initializes the parts and associated parts tables. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partsTableView.setItems(Inventory.getAllParts());
        partIdCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        partCostCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryLevelCOL.setCellValueFactory(new PropertyValueFactory<>("stock"));

        associatedPartIdCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartCostCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartInventoryLevelCOL.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

}