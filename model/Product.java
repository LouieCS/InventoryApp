package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This abstract class is for products.
 *
 * @author Louie Sanchez
 *
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** This is the constructor for Product.
     * @param id the id of a product
     * @param name the name of a product
     * @param price the price of a product
     * @param stock the stock of a product
     * @param min the min of a product
     * @param max the max of a product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This gets the ID of a product.
     * @return the ID of a product
     */
    public int getId() {
        return id;
    }

    /** This sets the ID of a product.
     * @param id The ID of a product
     */
    public void setId(int id) {
        this.id = id;
    }

    /** This gets the name of a product.
     * @return the name of a product
     */
    public String getName() {
        return name;
    }

    /** This sets the name of a product.
     * @param name The name of a product
     */
    public void setName(String name) {
        this.name = name;
    }

    /** This gets the price of a product.
     * @return the price of a product
     */
    public double getPrice() {
        return price;
    }

    /** This sets the price of a product.
     * @param price The price of a product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** This gets the stock of a product.
     * @return the stock of a product
     */
    public int getStock() {
        return stock;
    }

    /** This sets the stock of a product.
     * @param stock The stock of a product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This gets the minimum of a product.
     * @return the min of a product
     */
    public int getMin() {
        return min;
    }

    /** This sets the minimum of a product.
     * @param min The min of a product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** This gets the maximum of a product.
     * @return the max of a product
     */
    public int getMax() {
        return max;
    }

    /** This sets the maximum of a product.
     * @param max The max of a product
     */
    public void setMax(int max) {
        this.max = max;
    }


    /** This adds a part from the Parts list to the Associated Parts list.
     * @param part The part to be added
     */
    public void addAssociatedPart(Part part) {

        associatedParts.add(part);
    }

    /** This deletes a part from the Associated Parts list.
     * @param part The part to be removed
     * @return Associated Parts list with the part removed
     */
    public boolean deleteAssociatedPart(Part part) {
        if (associatedParts.contains(part)) {
            return associatedParts.remove(part);
        }
        else {
            return false;
        }
    }

    /** Obtains all parts from the Associated Parts list.
     * @return all parts from the Associated Parts list
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
