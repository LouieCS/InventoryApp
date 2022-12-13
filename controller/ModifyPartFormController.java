package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lsc482.lsc482.InventoryApplication;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.util.Optional;

import static model.Inventory.updatePart;

/** This class creates a form for a user to modify the information of an existing part in the inventory.
 *
 * @author Louie Sanchez
 *
 */
public class ModifyPartFormController {

    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup addPartRadioGroup;
    @FXML
    private Label partMachineIdLBL;
    @FXML
    private Label partIdLBL;
    @FXML
    private TextField partIdTXT;
    @FXML
    private TextField partNameTXT;
    @FXML
    private TextField partCostTXT;
    @FXML
    private TextField partMaxTXT;
    @FXML
    private TextField partMinTXT;
    @FXML
    private TextField partCompanyNameTXT;
    @FXML
    private Label partCompanyNameLBL;
    @FXML
    private TextField partMachineIdTXT;
    @FXML
    private TextField partInventoryTXT;
    @FXML
    private RadioButton inhouseRADIOBTN;
    @FXML
    private RadioButton outsourcedRADIOBTN;
    @FXML
    private Label errorText;

    /** This makes the machine label and machine textfield visible for a user to input the information of an inhouse part. */
    @FXML
    protected void onActionInhouse() {
        partMachineIdLBL.setVisible(true);
        partMachineIdTXT.setVisible(true);
        partCompanyNameLBL.setVisible(false);
        partCompanyNameTXT.setVisible(false);
    }

    /** This makes the company label and company textfield visible for a user to input the information of an outsourced part. */
    @FXML
    protected void onActionOutsourced() {
        partMachineIdLBL.setVisible(false);
        partMachineIdTXT.setVisible(false);
        partCompanyNameLBL.setVisible(true);
        partCompanyNameTXT.setVisible(true);
    }

    /** This saves a user's input of the textfields and updates the part's information. After updating the part, a user is redirected back to the main form. */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(partIdTXT.getText());
            String name = partNameTXT.getText();
            int stock = Integer.parseInt(partInventoryTXT.getText());
            double price = Double.parseDouble(partCostTXT.getText());
            int max = Integer.parseInt(partMaxTXT.getText());
            int min = Integer.parseInt(partMinTXT.getText());

            if (max < min || stock < min || max < stock) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Min should be less than Max; and Inv should be between those two values.");
                alert.showAndWait();
                return;
            }

            if(inhouseRADIOBTN.isSelected()) {
                partCompanyNameTXT.clear();
                int machineId = Integer.parseInt(partMachineIdTXT.getText());
                updatePart(id, new InHouse(id,  name,  price,  stock,  min,  max, machineId));
            }
            else {
                partMachineIdTXT.clear();
                String companyName = partCompanyNameTXT.getText();
                updatePart(id, new Outsourced(id,  name,  price,  stock,  min,  max, companyName));
            }

            // Return back to main menu upon updating part info
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

    /** This obtains the current information of an existing part and displays it in the textfields.
     * @param part the part to obtain information about
     */
    public void sendPart(Part part) {
        partIdTXT.setText(String.valueOf(part.getId()));
        partNameTXT.setText(part.getName());
        partInventoryTXT.setText(String.valueOf(part.getStock()));
        partCostTXT.setText(String.valueOf(part.getPrice()));
        partMaxTXT.setText(String.valueOf(part.getMax()));
        partMinTXT.setText(String.valueOf(part.getMin()));

        if(part instanceof InHouse) {
            addPartRadioGroup.selectToggle(inhouseRADIOBTN);
            partMachineIdLBL.setVisible(true);
            partMachineIdTXT.setVisible(true);
            partCompanyNameLBL.setVisible(false);
            partCompanyNameTXT.setVisible(false);
            partMachineIdTXT.setText((String.valueOf(((InHouse) part).getMachineID())));
        }
        if(part instanceof Outsourced) {
            addPartRadioGroup.selectToggle(outsourcedRADIOBTN);
            partMachineIdLBL.setVisible(false);
            partMachineIdTXT.setVisible(false);
            partCompanyNameLBL.setVisible(true);
            partCompanyNameTXT.setVisible(true);
            partCompanyNameTXT.setText((String.valueOf(((Outsourced) part).getCompanyName())));
        }
    }

}