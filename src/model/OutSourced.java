package model;
/** This is the Outsourced Class.
 * This class extends Part and creates a part in this instance.
 * */
public class OutSourced extends Part{

    private String companyName;
/** This is the Outsourced class constructor.
 * This creates a part of the outsourced instance.
 * @param id The Outsourced part ID.
 * @param name The Outsourced part name.
 * @param price The Outsourced part name.
 * @param stock The Outsourced part stock level.
 * @param min The Outsourced part min stock level.
 * @param max The Outsourced part max stock level.
 * @param companyName The Outsourced part company name.
 * */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {

        super(id,name,price,stock,min,max);
        this.companyName = companyName;
        /*
        setPartID(id);
        setPartName(name);
        setPartPrice(price);
        setPartInStock(stock);
        setMin(min);
        setMax(max);
        this.companyName = companyName;
         */
    }
/** This is the getter for Company Name.
 * This method retrieves the company name of the Outsourced part.
 * @return companyName The company name associated with the part.
 * */
    public String getCompanyName() {
        return companyName;
    }
/** This is the setter for Company Name.
 * This method sets the company name for the Outsourced part.
 * @param companyName The company name to be set.
 * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}
