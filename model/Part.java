package model;

/** This abstract class is for parts.
 *
 * @author Louie Sanchez
 *
 */
public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the constructor for Part.
     * @param id the id of a part
     * @param name the name of a part
     * @param price the price of a part
     * @param stock the stock of a part
     * @param min the min of a part
     * @param max the max of a part
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This gets the ID of a part.
     * @return the ID of a part
     */
    public int getId() {
        return id;
    }

    /** This sets the ID of a part.
     * @param id The id of a part
     */
    public void setId(int id) {
        this.id = id;
    }

    /** This gets the name of a part.
     * @return the name of a part
     */
    public String getName() {
        return name;
    }

    /** This sets the name of a part.
     * @param name The name of a part
     */
    public void setName(String name) {
        this.name = name;
    }

    /** This gets the price of a part.
     * @return the price of a part
     */
    public double getPrice() {
        return price;
    }

    /** This sets the price of a part.
     * @param price The price of a part
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** This gets the stock of a part.
     * @return the stock of a part
     */
    public int getStock() {
        return stock;
    }

    /** This sets the stock of a part.
     * @param stock The stock of a part
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This gets the minimum of a part.
     * @return the min of a part
     */
    public int getMin() {
        return min;
    }

    /** This sets the minimum of a part.
     * @param min The min of a part
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** This gets the maximum of a part.
     * @return the max of a part
     */
    public int getMax() {
        return max;
    }

    /** This sets the maximum of a part.
     * @param max The max of a part
     */
    public void setMax(int max) {
        this.max = max;
    }
}
