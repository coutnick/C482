package model;

/**
 * OutSourced class that inherits traits from the Part class
 * @author Nicholas Rains
 */

public class Outsourced extends Part{
    private String companyName;

    /**
     * Constructor for the Outsourced object
     * @param id id of the part
     * @param name name of the part
     * @param price price of the part
     * @param stock amount of the part in stock
     * @param min min amount required of the part in stock
     * @param max max amount allowed of the part in stock
     * @param companyName the name of the company the part comes from
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /**
     * Setter of the companies name the part is coming from
     * @param companyName the name of the company the part is coming from
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    /**
     * Getter for the name of the company the part is coming from
     * @return The name of the company the part is coming from
     */
    public String getCompanyName(){
        return companyName;
    }

}