package model;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.*;

/** This is the Inventory Class.
 * This Class creates inventory functionality for parts and products.
 * */
public class Inventory {
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();;
    /** This is the add part method.
     * This method adds a new part to a list of all parts.
     * @param newPart The new part to be added.
     * */
    public static void addPart(Part newPart) { allParts.add(newPart);}
    /** This is the add product method.
     * This method adds a new product to a list of all products.
     * @param newProduct The new product to be added.
     * */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    /** The lookup part (int) method.
     * This method searches through the all parts list to find specified parts.
     * @param partId The int value ID.
     * @return part The part that was found.
     * */
    public static Part lookupPart(int partId) {
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getPartID() == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }
    /** The lookup product (int) method.
     * This method searches through the all products list to find specified products.
     * @param productId The product Id to be searched.
     * @return Product The product found by search.
     * */
    public static Product lookupProduct(int productId) {
        for(int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).getId() == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }
    /** The lookup Part (string) method.
     * This method searches the all parts list to find a list of specified parts.
     * @param partName The name of the part to be searched.
     * @return tempOlist The list of found parts.
     * */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> tempOList = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getPartName().contains(partName)) {
                tempOList.add(allParts.get(i));
            }
        }return tempOList;
    }

    /** The lookup product (string) method.
     * This method searches the all products list to find a list of specified products.
     * @param productName The product name to be searched.
     * @return tempObservableList The list of products found.
     * */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> tempObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).getName().contains(productName)) {
                    tempObservableList.add(allProducts.get(i));
            }
        } return tempObservableList;
    }
    /** The update part method.
     * This method updates the part in the all parts list.
     * @param index The index of the part in the all parts list.
     * @param selectedPart The new part to be updated.
     * */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    /** The update product method.
     * This method updates the product in the all products list.
     * @param index The index of the product in the all products list.
     * @param newProduct The new product to be updated.
     * */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
    /** The delete part method.
     * This method deletes the part from the all parts list.
     * @param selectedPart The part to be deleted.
     * @return true To confirm deletion.
     * */
    public static boolean deletePart(Part selectedPart) {
       allParts.remove(selectedPart);
       return true;
    }
    /** The delete product method.
     * This method deletes the product for the all products list.
     * @param selectedProduct The product to be deleted.
     * @return true To confirm deletion.
     * */
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }
    /** The getter for all products.
     * This method gets the all products list.
     * @return allProducts The list of all products.
     * */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    /** The getter for all parts.
     * This method gets the all parts list.
     * @return allParts The list of all parts.
     * */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

}
