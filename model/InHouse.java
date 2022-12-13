package model;

/** This class extends the Part class and is for InHouse parts.
 *
 * @author Louie Sanchez
 *
 */
public class InHouse extends Part {

    private int machineID;

    /** This is the constructor for InHouse parts.
     * @param id the id of an InHouse part
     * @param name the name of an InHouse part
     * @param price the price of an InHouse part
     * @param stock the stock of an InHouse part
     * @param min the min of an InHouse part
     * @param max the max of an InHouse part
     * @param machineID the machineID of an InHouse part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** This gets the machineID of an inhouse part.
     * @return the machineID of an inhouse part
     */
    public int getMachineID() {
        return machineID;
    }

    /** This sets the machineID of an inhouse part.
     * @param machineID the machineID an inhouse part
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
