package controller;

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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.*;

/** This class creates two separate tables for parts and products. The user is able to view, add, modify, and delete the items in both tables.
 *
 * @author Louie Sanchez
 *
 */
public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Part, Integer> partIdCOL;
    @FXML
    private TableColumn<Part, String> partNameCOL;
    @FXML
    private TableColumn<Part, Double> partCostCOL;
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCOL;
    @FXML
    private TableColumn<Product, Integer> productIdCOL;
    @FXML
    private TableColumn<Product, String> productNameCOL;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelCOL;
    @FXML
    private TableColumn<Product, Double> productCostCOL;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TextField searchPartsTableTXT;
    @FXML
    private TextField searchProductsTableTXT;
    @FXML
    private Label errorText;

    /** This lets a user search the parts table by part ID or part name. */
    @FXML
    void onActionSearchParts(ActionEvent event) {
        try {
            errorText.setText("");
            String query = searchPartsTableTXT.getText();
            ObservableList<Part> parts = lookupPart(query);

            if (parts.size() == 0) {
                int idQuery = Integer.parseInt(query);
                parts = lookupPart(idQuery);
            }

            partsTableView.setItems(parts);
            searchPartsTableTXT.setText("");

            if(parts.size() == 0) {
                errorText.setText("Part not found.");
                partsTableView.setItems(getAllParts());
                searchPartsTableTXT.setText("");
            }
        }
        catch(NumberFormatException exception) {
            errorText.setText("Part not found.");
            partsTableView.setItems(getAllParts());
            searchPartsTableTXT.setText("");
        }
    }

    /** This lets a user search the products table by product ID or product name. */
    @FXML
    void onActionSearchProducts(ActionEvent event) {
        try {
            errorText.setText("");
            String query = searchProductsTableTXT.getText();
            ObservableList<Product> products = lookupProduct(query);


            if(products.size() == 0) {
                int idQuery = Integer.parseInt(query);
                products = lookupProduct(idQuery);
            }

            productsTableView.setItems(products);
            searchProductsTableTXT.setText("");

            if(products.size() == 0) {
                errorText.setText("Product not found.");
                productsTableView.setItems(getAllProducts());
                searchProductsTableTXT.setText("");
            }
        }
        catch(NumberFormatException exception) {
            errorText.setText("Product not found.");
            productsTableView.setItems(getAllProducts());
            searchProductsTableTXT.setText("");
        }
    }

    /** This brings a user to the add part form. */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(InventoryApplication.class.getResource("addpartform-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This brings a user to the modify part form. */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryApplication.class.getResource("modifypartform-view.fxml"));
            loader.load();

            ModifyPartFormController MPMController = loader.getController();
            MPMController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select an item in the table.");
            alert.showAndWait();
        }
    }

    /** This deletes a user selected part from the parts table. */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        errorText.setText("");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected part. Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if(partsTableView.getSelectionModel().getSelectedItem() == null) {
                    errorText.setText("Part was not deleted.");
                }
                else {
                    deletePart(partsTableView.getSelectionModel().getSelectedItem());
                }
            }
            catch (NullPointerException exception) {
                errorText.setText("Part was not deleted.");
            }
        }

    }

    /** This brings a user to the add product form. */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(InventoryApplication.class.getResource("addproductform-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This brings a user to the modify part form. */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryApplication.class.getResource("modifyproductform-view.fxml"));
            loader.load();

            ModifyProductFormController MPMController = loader.getController();
            MPMController.sendProduct(productsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select an item in the table.");
            alert.showAndWait();
        }
    }

    /** This deletes a user selected product from the products table. */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        errorText.setText("");
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected product. Do you want to continue?");

        Optional<ButtonType> result = alert1.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if(productsTableView.getSelectionModel().getSelectedItem() == null) {
                    errorText.setText("Product was not deleted.");
                }
                else {
                    Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
                    if(selectedProduct.getAllAssociatedParts().isEmpty()) {
                        deleteProduct(selectedProduct);
                    }
                    else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setContentText("Products with associated parts can not be deleted.");
                        errorText.setText("Product was not deleted.");
                        alert2.showAndWait();
                    }
                }
            }
            catch (NullPointerException exception) {
                errorText.setText("Product was not deleted.");
            }
        }
    }

    /** This closes the application. */
    @FXML
    void onActionExitApp(ActionEvent event) {
        System.exit(0);
    }

    /** This initializes the parts and products tables. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partsTableView.setItems(Inventory.getAllParts());

        partIdCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        partCostCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryLevelCOL.setCellValueFactory(new PropertyValueFactory<>("stock"));


        productsTableView.setItems(Inventory.getAllProducts());

        productIdCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCostCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInventoryLevelCOL.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

}