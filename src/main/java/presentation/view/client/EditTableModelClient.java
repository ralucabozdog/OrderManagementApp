package presentation.view.client;

import bll.validators.InvalidDataException;
import bll.validators.customer.EmailValidator;
import bll.validators.customer.PhoneValidator;
import model.Customer;
import presentation.view.ErrorPopUp;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Clasa pentru crearea modelului abstract de tabel al JTable-ului din interfata grafica de editare client in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class EditTableModelClient extends AbstractTableModel {
    /**
     * Lista tuturor clientilor din tabelul Customer al bazei de date
     */
    private List<Customer> clientList;
    /**
     * "Matricea" de String-uri ce retine datele despre un client pe un rand, cu fiecare variabila instanta pe cate o coloana
     */
    private String[][] clients;
    /**
     * Lista numelor coloanelor tabelului
     */
    private String[] columnNames;

    /**
     * Constructorul clasei EditTableModelClient
     * @param clientList lista tuturor clientilor din tabelul Customer al bazei de date
     */
    @SuppressWarnings("unchecked")
    public EditTableModelClient(List<Customer> clientList) {
        this.clientList = clientList;
        int cols = 5;
        int rows = clientList.size();

        clients = new String[rows][cols];
        columnNames = new String[cols];

        columnNames[0] = new String("firstName");
        columnNames[1] = new String("lastName");
        columnNames[2] = new String("email");
        columnNames[3] = new String("phone");
        columnNames[4] = new String("address");
        int j = 0;
        for (Customer t : clientList) {
            clients[j][0] = t.getFirstName();
            clients[j][1] = t.getLastName();
            clients[j][2] = t.getEmail();
            clients[j][3] = t.getPhone();
            clients[j][4] = t.getAddress();
            j++;
        }
    }

    /**
     * Metoda pentru aflarea numelui unei coloane in functie de indicele sau
     * @param column indicele coloanei
     * @return numele coloanei cu indicele column
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Metoda pentru aflarea numarului total de coloane al tabelului
     * @return numarul total de coloane din tabel
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Metoda pentru aflarea numarului total de linii al tabelului
     * @return numarul de linii din tabel
     */
    @Override
    public int getRowCount() {
        return clientList.size();
    }

    /**
     * Metoda pentru returnarea obiectului aflat in celula de la intersectia dintre o anumita linie si o anumita coloana
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     * @return obiectul aflat in celula de la intersectia dintre linia rowIndex si coloana columnIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer row = clientList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getFirstName();
        } else if (1 == columnIndex) {
            return row.getLastName();
        } else if (2 == columnIndex) {
            return row.getEmail();
        } else if (3 == columnIndex) {
            return row.getPhone();
        } else if (4 == columnIndex) {
            return row.getAddress();
        }
        return null;
    }

    /**
     * Metoda pentru setarea unei noi valori in celula de la intersectiadintre o anumita linie si o anumita coloana
     * @param aValue valoarea cu care se va actualiza celula
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Customer row = clientList.get(rowIndex);
        if (0 == columnIndex) {
            if(aValue.toString().length() != 0) row.setFirstName((String) aValue);
            else new ErrorPopUp("First name cannot be null").setVisible(true);
        } else if (1 == columnIndex) {
            if(aValue.toString().length() != 0) row.setLastName((String) aValue);
            else new ErrorPopUp("Last name cannot be null").setVisible(true);
        } else if (2 == columnIndex) {
            if(aValue.toString().length() != 0){
                Customer c= new Customer();
                c.setEmail((String) aValue);
                try {
                    new EmailValidator().validate(c);
                    row.setEmail((String) aValue);
                } catch (InvalidDataException invalidDataException) { new ErrorPopUp("Email is not valid").setVisible(true); }
            }
            else new ErrorPopUp("Email cannot be null").setVisible(true);
        } else if (3 == columnIndex) {
            if(aValue.toString().length() != 0){
                Customer c= new Customer();
                c.setPhone((String) aValue);
                try {
                    new PhoneValidator().validate(c);
                    row.setPhone((String) aValue);
                } catch (InvalidDataException invalidDataException) { new ErrorPopUp("Phone number is not valid").setVisible(true); }
            }
            else new ErrorPopUp("Phone number cannot be null").setVisible(true);
        } else if (4 == columnIndex) {
            if(aValue.toString().length() != 0)
                row.setAddress((String) aValue);
            else new ErrorPopUp("Address cannot be null").setVisible(true);
        }
    }

    /**
     * Metoda pentru stabilirea statutului de editabil/non-editabil al unei celule
     * @param rowIndex randul pe care se afla celula
     * @param columnIndex coloana pe care se afla celula
     * @return true daca celula este editabila; false in caz contrar
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
