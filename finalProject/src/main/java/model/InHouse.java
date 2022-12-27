package model;

/**
 * InHouse class that inherits traits from the Part class
 * @author Nicholas Rains
 */

public class InHouse extends Part{
    private int machineId;
    /**
     * Constructor for the InHouse object
     * @param id the id of the in house object
     * @param name the name of the in house object
     * @param price the price of the in house object
     * @param stock the amount of the in house object in stock
     * @param min the min amount required of the in house object in stock
     * @param max the max amount allowed of the in house object in stock
     * @param machineId the machine ID of the in house object
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
      super(id, name, price, stock, min, max);
      this.machineId = machineId;
    }
    /**
     * setter for the machine ID of the part
     * @param machineId the machineId of the part
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    /**
     * a getter for the machine ID of the part
     * @return the machineID of the part
     */
    public int getMachineId(){
        return machineId;
    }

}