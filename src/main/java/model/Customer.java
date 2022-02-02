package model;

/**
 * Clasa pentru "traducerea" in java din baza de date a tuplelor din tabelul Customer
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    /**
     * Constructor cu toti parametrii clasei Customer
     * @param id id-ul clientului
     * @param firstName prenumele clientului
     * @param lastName numele de familie al clientului
     * @param email email-ul clientului
     * @param phone numarul de telefon al clientului
     * @param address adresa clientului
     */
    public Customer(int id, String firstName, String lastName, String email, String phone, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Constructor cu toti parametrii in afara de id
     * @param firstName prenumele clientului
     * @param lastName numele de familie al clientului
     * @param email email-ul clientului
     * @param phone numarul de telefon al clientului
     * @param address adresa clientului
     */
    public Customer(String firstName, String lastName, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Constructrul fara parametri al clasei Customer
     */
    public Customer(){

    }

    /**
     * Metoda getter a variabilei instanta id
     * @return id-ul clientului curent
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
     * Metoda getter a variabilei instanta firstName
     * @return prenumele clientului curent
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Metoda setter a variabilei instanta firstName
     * @param firstName noua valoare a parametrului firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Metoda getter a variabilei instanta lastName
     * @return numele de familie al clientului curent
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Metoda setter a variabilei instanta lastName
     * @param lastName noua valoare a parametrului lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Metoda getter a variabilei instanta email
     * @return adresa de email a clientului curent
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metoda setter a variabilei instanta email
     * @param email noua valoare a parametrului email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda getter a variabilei instanta phone
     * @return numarul de telefon al clientului curent
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Metoda setter a variabilei instanta phone
     * @param phone noua valoare a parametrului phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Metoda getter a variabilei instanta address
     * @return adresa clientului curent
     */
    public String getAddress() {
        return address;
    }

    /**
     * Metoda setter a variabilei instanta address
     * @param address noua valoare a parametrului address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
