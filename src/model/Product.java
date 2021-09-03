package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This is the Product Class.
 * This creates products.
 * */
public class Product{
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** The Product Constructor.
     * Initializes a product when called.
     * @param id Product ID.
     * @param name Product Name.
     * @param price Product Price.
     * @param stock Product Inventory Level.
     * @param min Product Minimum Inventory Level.
     * @param max Product Maximum Inventory Level.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        associatedParts = FXCollections.observableArrayList();

    }

    /** This is the add Associated Part method.
     * This method adds a given part to the product's associatedParts list.
     * @param part The part to be added.
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    /** This is the delete Associated Part method.
     * This method deletes a given part from the product's associatedParts list.
     * @param selectedAssociatedPart The part to be deleted.
     * @return true to confirm deletion.
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
      associatedParts.remove(selectedAssociatedPart);
      return true;
    }
    /** This is the get all Associated Parts method.
     * This method retrieves all parts associated with any given product.
     * @return associatedParts The parts list associated with the product.
     * */
    public ObservableList<Part> getAllAssociatedParts() {
       return this.associatedParts;

    }
    /** The product get id method.
     * This method retrieves the id of a product.
     * @return id The id connected to the product.
     * */
        public int getId () {
            return id;
        }
    /** This is the ID set method.
     * This method sets the ID to the new passed parameter.
     * @param id The id to be set.
     * */
        public void setId ( int id){
            this.id = id;
        }
    /** The product get name method.
     * This method retrieves the name of a product.
     * @return name The name connected to the product.
     * */
        public String getName () {
            return name;
        }
    /** This is the name set method.
     * This method sets the name to the new passed parameter.
     * @param name The name to be set.
     * */
        public void setName (String name){
            this.name = name;
        }
    /** The product get price method.
     * This method retrieves the price of a product.
     * @return price The price connected to the product.
     * */
        public double getPrice () {
            return price;
        }
    /** This is the price set method.
     * This method sets the price to the new passed parameter.
     * @param price The price to be set.
     * */
        public void setPrice ( double price){
            this.price = price;
        }
    /** The product get stock method.
     * This method retrieves the stock level of a product.
     * @return stock The stock level connected to the product.
     * */
        public int getStock () {
            return stock;
        }
    /** This is the stock set method.
     * This method sets the stock to the new passed parameter.
     * @param stock The stock to be set.
     * */
        public void setStock ( int stock){
            this.stock = stock;
        }
    /** The product get min stock level method.
     * This method retrieves the min stock level of a product.
     * @return min The min stock level connected to the product.
     * */
        public int getMin () {
            return min;
        }
    /** This is the min inventory level set method.
     * This method sets the min inventory level to the new passed parameter.
     * @param min The min inventory level to be set.
     * */
        public void setMin ( int min){
            this.min = min;
        }
    /** The product get max stock level method.
     * This method retrieves the max stock level of a product.
     * @return max The max stock level connected to the product.
     * */
        public int getMax () {
            return max;
        }
    /** This is the max inventory level set method.
     * This method sets the max inventory level to the new passed parameter.
     * @param max The max inventory level to be set.
     * */
        public void setMax ( int max){
            this.max = max;
        }

}


