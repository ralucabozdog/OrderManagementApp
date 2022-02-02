package bll.validators;

import java.util.regex.Pattern;

/**
 * Clasa pentru validarea introducerii unui numar de tip Double
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class ValidatePositiveDouble implements Validator<String>{
    private static final String DOUBLE_PATTERN = "^(?:0|[1-9][0-9]*)\\.[0-9]+$";

    /**
     * Implementarea metodei de validare a intrfetei Validator
     * @param s stringul care se vrea a fi validat ca Double pozitiv
     * @throws InvalidDataException exceptie proprie, aruncata in cazul in care String-ul dat nu este un Double
     */
    @Override
    public void validate(String s) throws InvalidDataException {
        Pattern pattern = Pattern.compile(DOUBLE_PATTERN);
        if (!pattern.matcher(s).matches()) {
            throw new InvalidDataException("Price has to be a positive real number");
        }
    }
}
