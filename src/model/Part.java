package model;

/** This is the abstract Part Class.
 This class creates, gets, and sets parts.
 */
public abstract class Part {
    protected int partID;
    protected String partName;
    protected double partPrice;
    protected int partInStock;
    protected int min;
    protected int max;
/** The Part Constructor method.
 * Initiates a part when called.
 @param partID The part id.
 @param partName The part name.
 @param partPrice The part price.
 @param partInStock The stock level of the part.
 @param min The minimum stock level.
 @param max The maximum stock level.
 */
    public Part(int partID, String partName, double partPrice, int partInStock, int min, int max){

        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partInStock = partInStock;
        this.min = min;
        this.max = max;
    }

    /** The method to get the part ID.
     * This method gets the part ID of specified part.
     * @return partID variable.
     */
    public int getPartID() {
        return partID;
    }
    /** The part Id setter.
     * This method sets PartID to passed part ID parameter.
     * @param partID Part ID to be set.
     * */
    public void setPartID(int partID) {
        this.partID = partID;
    }
    /** The method to get the part name.
     * This method gets the part name of specified part.
     * @return partName variable.
     */
    public String getPartName() {
        return partName;
    }
    /** The Part Name setter.
     * This method sets partName to passed part name parameter.
     * @param partName Part name to be set.
     * */
    public void setPartName(String partName) {
        this.partName = partName;
    }
    /** The method to get the part price.
     * This method gets the part price of specified part.
     * @return partPrice variable.
     */
    public double getPartPrice() {
        return partPrice;
    }
    /** The Part Price setter.
     * This method sets partPrice to passed part price parameter.
     * @param partPrice Part price to be set.
     * */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
    /** The method to get the part inventory level.
     * This method gets the part inventory level of specified part.
     * @return partInStock variable.
     */
    public int getPartInStock() {
        return partInStock;
    }
    /** The Part Stock level setter.
     * This method sets partInStock to passed partInStock parameter.
     * @param partInStock Part in stock to be set.
     * */
    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }
    /** The method to get the part minimum.
     * This method gets the part minimum of specified part.
     * @return min variable.
     */
    public int getMin() {
        return min;
    }
    /** The Part minimum setter.
     * This method sets min to passed min parameter.
     * @param min Part minimum to be set.
     * */
    public void setMin(int min) {
        this.min = min;
    }
    /** The method to get the part maximum.
     * This method gets the part maximum of specified part.
     * @return maximum variable.
     */
    public int getMax() {
        return max;
    }
    /** The Part Maximum setter.
     * This method sets max to passed part max parameter.
     * @param max Part maximum to be set.
     * */
    public void setMax(int max) {
        this.max = max;
    }

}

