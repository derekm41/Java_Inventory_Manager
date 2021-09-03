package model;
/** This is the InHouse Class.
 * The InHouse class extends Part and creates new parts of the Inhouse instance. */
public class InHouse extends Part{

    private int machineID;
/** The InHouse class constructor.
 * @param id InHouse part ID.
 * @param name InHouse Name.
 * @param price InHouse price.
 * @param stock InHouse part inventory level.
 * @param min InHouse minimum inventory level.
 * @param max InHouse maximum inventory level.
 * @param machineID InHouse machine ID.
 * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        //I saw super being used in a video. Essentially does the same this as the commented out code below.
        super(id,name,price,stock,min,max);
        this.machineID = machineID;
        /*
        setPartID(id);
        setPartName(name);
        setPartPrice(price);
        setPartInStock(stock);
        setMin(min);
        setMax(max);
        this.machineID = machineID;
        */
    }
/** This is the getter for Machine ID.
 * This method retrieves the machine ID for any part.
 * @return machineID The machine ID for the part.
 * */
    public int getMachineID() {

        return machineID;
    }
/** This is the seter for machine ID.
 * This method sets the machine ID to a specified part.
 * @param machineID The id to be set.
 * */
    public void setMachineID(int machineID) {

        this.machineID = machineID;
    }


}
