package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class of Parts and Products
 *
 * @author Nicholas rains
 */

public class Inventory {
    public static int nextId = 1;
    /**
     * An observable list of all the parts in the inventory
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * An observable list of all the products in the inventory
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add a new part to the inventory
     *
     * @param newPart the part being added to the inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Add a new product to the inventory
     *
     * @param newProduct the product being added to the inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Search for the part in the inventory by partID
     *
     * @param partID the partID of the part that is being searched
     * @return The part that is being searched
     */
    public static Part lookupPart(int partID) {
        for(Part part: allParts){
            if(part.getId() == partID){
                return part;
            }
        }

        return null;
    }

    /**
     * Search for the product in the inventory by Product ID
     *
     * @param productId the productID of the product that is being searched
     * @return The product that is being searched
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    /**
     * Search for the part in the inventory by name
     *
     * @param partName the name of the product that is being searched
     * @return an observable list of parts being searched
     */
    public static  ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partSearched = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                partSearched.add(part);
            }

        }
        return partSearched;
    }

    /**
     * Search for the product in the inventory by name
     *
     * @param productName the name of the product that is being searched
     * @return an observable list with the product that is being searched
     *
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> prodSearched = FXCollections.observableArrayList();
        for(Product product: allProducts){
            if(product.getName().contains(productName)){
                prodSearched.add(product);
            }
        }
        return prodSearched;
    }
    /**
     * Updates the part in the inventory
     * @param index index of the part being updated
     * @param selectedPart the part that is being updated
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    /**
     * Updates the product in the inventory
     * @param index index of the product that is being updated
     * @param newProduct product that is being updated
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    /**
     * Deletes the part from the inventory
     * @param selectedPart the part that is to be deleted from the inventory
     * @return True if the part is deleted False if the product does not exist
     */
    public static boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Deletes the product from the inventory
     * @param selectedProduct The product that is to be deleted from the inventory
     * @return True if the product is deleted and False if the product does not exist
     */
    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Gets all the parts from the inventory
     * @return all parts in the parts observable list
     */
    public static ObservableList<Part> getsAllParts(){return allParts;}
    /**
     * Gets all the products from the inventory
     */
    public static ObservableList<Product> getAllProducts(){return allProducts;}




}



