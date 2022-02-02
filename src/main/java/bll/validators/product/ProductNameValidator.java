package bll.validators.product;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Product;

/**
 * Clasa pentru validarea campului ProductName al unui produs
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class ProductNameValidator implements Validator<Product> {

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param product produsul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unui nume invalid de produs
     */
    @Override
    public void validate(Product product) throws InvalidDataException {
        if(product.getProductName().length() == 0)
            throw new InvalidDataException("Fill in product name field");
        if(product.getProductName().length() > 80)
            throw new InvalidDataException("Product name has at most 80 characters");
    }
}
