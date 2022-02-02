package model;

/**
 * Clasa pentru "traducerea" in java din baza de date a tuplelor din tabelul Request
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class Request {
    private int id;
    private int idCustomer;
    private int idProduct;
    private int orderedQuantity;

    /**
     * Constructorul cu toti parametrii clasei Request
     * @param id valoarea cheii primare a obiectului Request construit
     * @param idCustomer id-ul clientului pentru care se plaseaza comanda
     * @param idProduct id-ul produsului comandat
     * @param orderedQuantity cantitatea comandata
     */
    public Request(int id, int idCustomer, int idProduct, int orderedQuantity) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.orderedQuantity = orderedQuantity;
    }

    /**
     * Constructorul cu toti parametrii in afara de id-ul Request-ului construit
     * @param idCustomer id-ul clientului pentru care se plaseaza comanda
     * @param idProduct id-ul produsului comandat
     * @param orderedQuantity cantitatea comandata
     */
    public Request(int idCustomer, int idProduct, int orderedQuantity) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.orderedQuantity = orderedQuantity;
    }

    /**
     * Constructorul fara parametri ai clasei Request
     */
    public Request(){

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
     * Metoda getter a variabilei instanta idCustomer
     * @return id-ul clientului pentru care s-a plasat comanda
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * Metoda setter a variabilei instanta idCustomer
     * @param idCustomer noua valoare a parametrului idCustomer
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * Metoda getter a variabilei instanta idProduct
     * @return id-ul produsului comandat
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Metoda setter a variabilei instanta idProduct
     * @param idProduct noua valoare a parametrului idProduct
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Metoda getter a variabilei instanta orderedQuantity
     * @return cantitatea comandata
     */
    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    /**
     * Metoda setter a variabilei instanta orderedQuantity
     * @param orderedQuantity noua valoare a parametrului orderedQuantity
     */
    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
}
