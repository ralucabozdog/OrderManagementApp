package bll.types;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import bll.validators.product.PriceValidator;
import bll.validators.product.ProductNameValidator;
import bll.validators.product.QuantityValidator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru validarea tuturor variabilelor instanta ale unui obiect de tip Product, si pentru realizarea unor
 * operatii in baza de date prin intermediu ProductDAO
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class ProductBLL {
    /**
     * Lista validatorilor unui obiect Product
     */
    private List<Validator<Product>> validators;
    /**
     * Variabila pentru realizarea operatiilor asupra tabelului Product al bazei de date
     */
    private ProductDAO productDAO;

    /**
     * Constructorul fara parametri al clasei Product BLL construieste lista validatorilor campurilor oricarui produs
     */
    public ProductBLL(){
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductNameValidator());
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());

        productDAO = new ProductDAO();
    }

    /**
     * Metoda pentru validarea variabilelor instanta ale unui produs
     * @param p produsul ale carui variabile instanta urmeaza a fi validate
     * @throws InvalidDataException tip propriu de exceptie generat in cazul unei inconsistente a campurilor produsului
     */
    public void validate(Product p) throws InvalidDataException{
        for(Validator<Product> validator : validators){
            validator.validate(p);
        }
    }

    /**
     * Metoda pentru adaugarea unui produs in baza de date in tabelul Product
     * @param p produsul ce se insereaza in baza de date
     */
    public void insertProduct(Product p){
        productDAO.insert(p);
    }

    /**
     * Metoda ce returneaza toate produsele din tabelul Product al bazei de date
     * @return lista produselor din baza de date
     */
    public List<Product> findAllProducts(){
        return productDAO.findAll();
    }
}
