package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class for the project that is dependent on the Part class
 *
 * @author Nicholas Rains
 */

public class Product{

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * A constructor for a product object
     *
     * @param id The id of the product
     * @param name The name of the product
     * @param price The price of the product
     * @param stock The amount of the product in stock
     * @param min The minimum amount that needs to be in stock
     * @param max the maximum amount that needs to be in stock
     *
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**
     * Setter for the ID
     * @param id the id of the product
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Setter for the name of a product
     * @param name the name of the product
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Setter for the price of a product
     * @param price the price of a product
     */
    public void setPrice(double price){
        this.price = price;
    }
    /**
     * Setter for the amount of a product in stock
     * @param stock the amount of a product in stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }
    /**
     * Setter for the minimum amount allowed of a product in stock
     * @param min the minimum amount allowed of a product in stock
     */
    public void setMin(int min){
        this.min = min;
    }
    /**
     * Setter for the maximum amount allowed of a product in stock
     * @param max the maximum amount allowed of a product in stock
     */
    public void setMax(int max){
        this.max = max;
    }
    /**
     * Getter for the products ID
     * @return ID of the product
     */
    public int getId(){
        return id;
    }
    /** Getter for the products name
     * @return the products name
     */
    public String getName(){
        return name;
    }
    /**
     *  Getter for the products price
     * @return the price of the product
     */
    public double getPrice(){
        return price;
    }
    /**
     * Getter for the products stock
     * @return the amount of the product in stock
     */
    public int getStock(){
        return stock;
    }
    /**
     * Getter for the min amount allowed in stock
     * @return the min amount allowed in stock
     */
    public int getMin(){
        return min;
    }
    /**
     * Getter for the max amount allowed in stock
     * @return the max amount allowed in stock
     */
    public int getMax(){
        return max;
    }
    /**
     * Adds a product to the associated parts list
     * @param part a part object
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    /**
     * Deletes a product from the associated parts list if it is there
     * @param selectedAssociatedPart a part that is trying to be removed
     * @return true if the part is deleted
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if(associatedParts.contains(selectedAssociatedPart)){
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }
    /**
     * Getter for a list of associated parts for the product
     * @return list of parts
     */
    public ObservableList<Part> getAssociatedParts() {return associatedParts;}

}