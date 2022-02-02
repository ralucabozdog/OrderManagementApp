package model;

/**
 * Clasa pentru "traducerea" in java din baza de date a tuplelor din tabelul Product
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class Product {
    private int id;
    private String productName;
    private Double price;
    private int quantity;

    /**
     * Constructorul cu toti parametrii clasei Product
     * @param id valoarea cheii primare a obiectului Product construit
     * @param productName numele produsului
     * @param price pretul produsului
     * @param quantity cantitatea disponibila pe stoc
     */
    public Product(int id, String productName, Double price, int quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructorul fara parametrul id
     * @param productName numele produsului
     * @param price pretul produsului
     * @param quantity cantitatea disponibila pe stoc
     */
    public Product(String productName, Double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructorul fara parametri ai clasei Product
     */
    public Product(){

    }

    /**
     * Metoda getter a variabilei instanta id
     * @return id-ul comezii curente
     */
    public int getId() {
        return id;
    }

    /**
     * Metoda setter a variabilei instanta id
     * @param id noua valoare a parametrului id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metoda getter a variabilei instanta productName
     * @return numele produsului curent
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Metoda setter a numelui de produs
     * @param productName noul nume al produsului
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Metoda getter a variabile instanta price
     * @return pretul produsului
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Metoda setter a variabile instanta price
     * @param price noul pret al produsului
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Metoda getter a cantitatii produsului
     * @return cantitatea produsului
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Metoda setter a cantitatii produsului
     * @param quantity noua cantitate a produsului
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
