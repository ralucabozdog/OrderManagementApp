package bll.validators.product;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Product;

/**
 * Clasa pentru validarea campului Price al unui produs
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class PriceValidator implements Validator<Product> {

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param product produsul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unui pret invalid
     */
    @Override
    public void validate(Product product) throws InvalidDataException {
        if(product.getPrice() < 0)
            throw new InvalidDataException("Fill in price with a positive number");
    }
}
