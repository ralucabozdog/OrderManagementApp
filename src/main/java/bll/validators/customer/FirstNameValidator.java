package bll.validators.customer;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Customer;

/**
 * Clasa pentru validarea campului FistName al unui client
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class FirstNameValidator implements Validator<Customer> {

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param customer clientul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unui prenume invalid
     */
    @Override
    public void validate(Customer customer) throws InvalidDataException {
        if(customer.getFirstName().length() == 0)
            throw new InvalidDataException("Fill in first name field");
        if(customer.getFirstName().length() > 45)
            throw new InvalidDataException("First name has at most 45 characters");
    }
}
