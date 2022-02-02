package bll.validators.product;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Product;

/**
 * Clasa pentru validarea campului Quantity al unui produs
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class QuantityValidator implements Validator<Product> {
    /**
     * Implementarea metodei de validare din interfata Validator
     * @param product produsul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unei cantitati invalide
     */
    @Override
    public void validate(Product product) throws InvalidDataException {
        if(product.getQuantity() < 0)
            throw new InvalidDataException("Quantity is a natural number");
    }
}
