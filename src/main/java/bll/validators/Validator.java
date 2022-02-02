package bll.validators;

/**
 * Interfata a carei unica metoda este folosita pentru validarea datelor ce se doreste a fi introduse in baza de date
 * @param <T> tip generic (Customer, Product, String, etc.)
 */
public interface Validator<T> {

    /**
     * Metoda a ceri implementare va fi data doar in clasele ce implementeaza aceasta interfata
     * @param t obiectul de tip generic T ale carui campuri vor fi validate
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unei inconsistente a datelor
     */
    public void validate(T t) throws InvalidDataException;
}
