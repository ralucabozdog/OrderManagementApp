package model;

/**
 * Clasa pentru "traducerea" in java din baza de date a tuplelor din tabelul PlacedOrder
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class PlacedOrder {
    private int id;
    private Double total;

    /**
     * Constructorul cu toti parametrii ai clasei PlacedOrder
     * @param id valoarea cheii primare a obiectului PlacedOrder contruit
     * @param total valoarea totalului comenzii (care poate contine oricate produse)
     */
    public PlacedOrder(int id, Double total){
        this.id = id;
        this.total = total;
    }

    /**
     * Constructorul cu un singur parametru al clasei PlacedOrder
     * @param total valoarea totalului comenzii
     */
    public PlacedOrder(Double total){
        this.total = total;
    }

    /**
     * Constructorul fara parametri ai clasei PlacedOrder
     */
    public PlacedOrder(){

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
     * Metoda getter a variabilei instanta total
     * @return valoarea totala a comezii curente
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Metoda setter a variabilei instanta total
     * @param total noua valoare a parametrului total
     */
    public void setTotal(Double total) {
        this.total = total;
    }
}
