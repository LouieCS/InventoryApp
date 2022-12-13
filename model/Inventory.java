package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is for the Parts and Products lists.
 *
 * @author Louie Sanchez
 *
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /** This adds a new part to the Parts list.
     * @param newPart The new part to add
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** This adds a new product to the Products list.
     * @param newProduct The new product to add
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This searches the Parts list by part ID.
     * @param partId The part ID to search in the list
     * @return results of the part ID search
     */
    public static ObservableList<Part> lookupPart(int partId){
        ObservableList<Part> partIdSearch = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part : allParts) {
            if (part.getId() == partId) {
                partIdSearch.add(part);
            }
        }

        return partIdSearch;
    }

    /** This searches the Parts list by part name.
     * @param partName The part name to search in the list
     * @return results of the part name search
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partNameSearch = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        partName = partName.toLowerCase();

        for(Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName)) {
                partNameSearch.add(part);
            }
        }

        return partNameSearch;
    }

    /** This searches the Products list by product ID.
     * @param productId The product ID to search in the list
     * @return results of the product ID search
     */
    public static ObservableList<Product> lookupProduct(int productId){
        ObservableList<Product> productIdSearch = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for(Product product : allProducts) {
            if (product.getId() == productId) {
                productIdSearch.add(product);
            }
        }

        return productIdSearch;
    }

    /** This searches the Products list by product name.
     * @param productName The product name to search in the list
     * @return results of the product name search
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> productNameSearch = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        productName = productName.toLowerCase();

        for(Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName)) {
                productNameSearch.add(product);
            }
        }

        return productNameSearch;
    }

    /** This updates a part in the Parts list.
     * @param id The ID of the part to update
     * @param updatedPart The part's new information
     */
    public static void updatePart(int id, Part updatedPart) {
        int index = -1;

        for(Part part : Inventory.getAllParts()) {
            index++;

            if(part.getId() == id) {
                Inventory.getAllParts().set(index, updatedPart);
            }
        }
    }

    /** This updates a product in the Products list.
     * @param id The ID of the product to update
     * @param updatedProduct The product's new information
     */
    public static void updateProduct(int id, Product updatedProduct) {
        int index = -1;

        for(Product product : Inventory.getAllProducts()) {
            index++;

            if(product.getId() == id) {
                Inventory.getAllProducts().set(index, updatedProduct);
            }
        }
    }

    /** This deletes a part from the Parts list.
     * @param part The part to be removed
     * @return the Parts list with the part removed
     */
    public static boolean deletePart(Part part) {
        if (allParts.contains(part)) {
            return Inventory.getAllParts().remove(part);
        }
        else {
            return false;
        }
    }

    /** This deletes a product from the Products list.
     * @param product The product to be removed
     * @return the Products list with the product removed
     */
    public static boolean deleteProduct(Product product) {
        if (allProducts.contains(product)) {
            return Inventory.getAllProducts().remove(product);
        }
        else {
            return false;
        }
    }

    /** Obtains all parts from the parts list.
     * @return all parts from the Parts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Obtains all products from the products list.
     * @return all products from the Products list
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

