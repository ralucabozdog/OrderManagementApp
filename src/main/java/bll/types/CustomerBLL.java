package bll.types;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import bll.validators.customer.*;
import dao.CustomerDAO;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru validarea tuturor campurilor clasei Customer si pentru apelarea metodelor de interactiune cu baza de date
 * prin intermediul CustomerDAO
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class CustomerBLL {
    /**
     * Lista validatorilor unui obiect Customer
     */
    private List<Validator<Customer>> validators;
    /**
     * Variabila pentru realizarea operatiilor asupra tabelului Customer al bazei de date
     */
    private CustomerDAO customerDAO;

    /**
     * Constructorul fara parametri al clasei CustomerBLL creaza lista de validatori ai variabilelor instanta ale clasei
     */
    public CustomerBLL(){
        validators = new ArrayList<Validator<Customer>>();
        validators.add(new FirstNameValidator());
        validators.add(new LastNameValidator());
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
        validators.add(new AddressValidator());

        customerDAO = new CustomerDAO();
    }

    /**
     * Metoda de validare a campurilor unui client
     * @param c clientul ale carui variabile instanta se doreste a fi validate
     * @throws InvalidDataException tip propriu de exceptie ce indica o eroare in formatul variabilelor instanta
     */
    public void validate(Customer c) throws InvalidDataException {
        for(Validator<Customer> validator : validators){
            validator.validate(c);
        }
    }

    /**
     * Metoda de inserare a unui client in tabelul Customer din baza de date
     * @param c clientul care se doreste a fi inserat
     */
    public void insertCustomer(Customer c){
        customerDAO.insert(c);
    }

    /**
     * Metoda ce returneaza lista tuturor clientilor din tabelul Customer al bazei de date
     * @return lista clientilor existenti in baza de date
     */
    public List<Customer> findAllCustomers(){
        return customerDAO.findAll();
    }
}
