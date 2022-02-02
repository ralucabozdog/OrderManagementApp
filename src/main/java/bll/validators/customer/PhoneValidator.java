package bll.validators.customer;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Customer;

import java.util.regex.Pattern;

/**
 * Clasa pentru validarea campului Phone al unui client
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class PhoneValidator implements Validator<Customer> {
    private static final String PHONE_NUMBER_PATTERN = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param customer clientul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unui numar de telefon invalid
     */
    @Override
    public void validate(Customer customer) throws InvalidDataException {
        if(customer.getPhone().length() == 0)
            throw new InvalidDataException("Fill in phone field");
        if(customer.getPhone().length() > 12)
            throw new InvalidDataException("Phone has at most 12 characters");

        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        if (!pattern.matcher(customer.getPhone()).matches()) {
            throw new InvalidDataException("Not a valid phone number");
        }
    }
}
