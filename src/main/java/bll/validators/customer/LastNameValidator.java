package bll.validators.customer;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Customer;

/**
 * Clasa pentru validarea campului LastName al unui client
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class LastNameValidator implements Validator<Customer> {

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param customer clientul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unui nume de familie invalid
     */
    @Override
    public void validate(Customer customer) throws InvalidDataException {
        if(customer.getLastName().length() == 0)
            throw new InvalidDataException("Fill in last name field");
        if(customer.getLastName().length() > 45)
            throw new InvalidDataException("Last name has at most 45 characters");
    }
}
