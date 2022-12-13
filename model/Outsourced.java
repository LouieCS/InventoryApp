package model;

/** This class extends the Part class and is for outsourced parts.
 *
 * @author Louie Sanchez
 *
 */
public class Outsourced extends Part {

    private String companyName;

    /** This is the constructor for Outsourced parts.
     * @param id the id of an Outsourced part
     * @param name the name of an Outsourced part
     * @param price the price of an Outsourced part
     * @param stock the stock of an Outsourced part
     * @param min the min of an Outsourced part
     * @param max the max of an Outsourced part
     * @param companyName the company name of an Outsourced part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** This gets the company name of an Outsourced part.
     * @return the company name of an Outsourced part
     */
    public String getCompanyName() {
        return companyName;
    }

    /** This sets the company name of an Outsourced part.
     * @param companyName the company name an Outsourced part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
