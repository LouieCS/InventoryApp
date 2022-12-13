package lsc482.lsc482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

import java.io.IOException;

/** This class creates an inventory system app for various features and functionality based on business requirements.
 *
 * @author Louie Sanchez
 *
 */
public class InventoryApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        /** This loads the main form as the initial scene when the program is run. */
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("mainform-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 350);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main method. This is the first method that gets called when the program is run.
     * @param args
     */
    public static void main(String[] args) {
        // Sample parts data
        Outsourced outsourced1 = new Outsourced(1, "Brakes", 35.49, 23, 5, 100, "SCBikes");
        Outsourced outsourced2 = new Outsourced(2, "Saddle", 29.49, 9, 5, 20, "Comfy Seats");
        Outsourced outsourced3 = new Outsourced(3, "Pedal", 19.49, 16, 10, 100, "Pedal To The Metal");
        InHouse inhouse1 = new InHouse(4, "Frame", 1099.49, 27, 10, 40, 2);
        InHouse inhouse2 = new InHouse(5, "Chain", 18.49, 11, 5, 20, 1);
        // Adds sample data to inventory list
        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);
        Inventory.addPart(outsourced3);
        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);


        // Sample products data
        Product product1 = new Product(1000, "Mountain bike", 2999.49, 2, 1, 5);
        Product product2 = new Product(1001, "Road bike", 2709.49, 3, 2, 5);
        Product product3 = new Product(1002, "Unicycle", 78.49, 3, 0, 5);
        // Adds sample data to inventory list
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);


        launch();
    }

}