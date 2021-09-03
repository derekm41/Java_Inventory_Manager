package main;

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
import model.InHouse;
import model.Part;
import model.Inventory;
import model.OutSourced;
import model.Product;
 */

/** This class creates an application that initiates the app and passes sample data to the application.
 * FUTURE ENHANCEMENTS If I were to update the program. I would do it centered around method be more universal.
 * There are methods such as the search that I designed to be very specific to that class controller and ended up
 * having to type a lot of redundant code. Another issue is data only continues as the program is open. Connecting
 * to some kind of data base or way to store the data outside of the program would be more beneficial. Adding the ability
 * to create and add a part from the add product screen could be beneficial as new products may have associated
 * parts that are also new. Warnings that would check when stock level gets close to min that way more parts could be
 * made/outsourced.
*/

public class Main extends Application {
    /** This class starts the program.
     The primary stage, scene and loader are initiated to open the program.
     @param primaryStage The primary stage of the program.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Inventory inv = new Inventory();
        addSampleData();

        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
        Scene primaryScene = new Scene(root);
        primaryStage.setTitle("Inventory Application");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    /** This is the main method.
     This is the first method that gets called when you run the java program.
     @param args The args parameter.
     */
    public static void main(String[] args) {
        launch(args);
    }
    /** This method adds sample data into the program.
     The sample data will be used to test functionality of java program.
     */
    void addSampleData() {
        //Add InHouse Parts
        Part a1 = new InHouse(2, "Part A1", 2.99, 10, 5, 100, 101);
        Part a2 = new InHouse(3, "Part A2", 4.99, 11, 5, 100, 103);
        Part b = new InHouse(1, "Part B", 3.00, 0, 5, 100, 102);
        Inventory.addPart(a1);
        Inventory.addPart(a2);
        Inventory.addPart(b);
        Inventory.addPart(new InHouse(4, "Part A3", 5.00, 15, 5, 100, 104));
        Inventory.addPart(new InHouse(5, "Part A4", 6.99, 5, 5, 100, 105));
        //Add OutSourced Parts
        Part o1 = new OutSourced(6, "Part 01", 2.99, 10, 5, 100, "Acme Co.");
        Part p = new OutSourced(7, "Part P", 3.99, 9, 5, 100, "Acme Co.");
        Part q = new OutSourced(8, "Part Q", 2.99, 10, 5, 100, "Florida Co.");
        Inventory.addPart(o1);
        Inventory.addPart(p);
        Inventory.addPart(q);
        Inventory.addPart(new OutSourced(9, "Part R", 2.99, 10, 5, 100, "Florida Co."));
        Inventory.addPart(new OutSourced(10, "Part 02", 2.99, 10, 5, 100, "NY Co."));
        //Add allProducts
        Product prod1 = new Product(100, "Product 1", 9.99, 20, 5, 100);
        prod1.addAssociatedPart(a1);
        prod1.addAssociatedPart(o1);
        Inventory.addProduct(prod1);
        Product prod2 = new Product(200, "Product 2", 9.99, 29, 5, 100);
        prod2.addAssociatedPart(a2);
        prod2.addAssociatedPart(p);
        Inventory.addProduct(prod2);
        Product prod3 = new Product(300, "Product 3", 9.99, 30, 5, 100);
        prod3.addAssociatedPart(b);
        prod3.addAssociatedPart(q);
        Inventory.addProduct(prod3);
        Inventory.addProduct(new Product(400, "Product 4", 29.99, 20, 5, 100));
        Inventory.addProduct(new Product(500, "Product 5", 29.99, 9, 5, 100));
    }



}
