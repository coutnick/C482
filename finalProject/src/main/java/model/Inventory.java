package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class of Parts and Products
 *
 * @author Nicholas rains
 */

public class Inventory {
    /**
     * An observable list of all the parts in the inventory
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * An observable list of all the products in the inventory
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *Gets an observable list of all parts in the inventory
     *
     * @return A list of all parts
     */
    public static ObservableList<Part> getAllParts(){
        return getAllParts();
    }
    /**
     * Adds a part to the inventory
     *
     * @param newPart The part to add
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    /**
     * Adds a product to the inventory
     * @param newProduct The product to add
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }






}