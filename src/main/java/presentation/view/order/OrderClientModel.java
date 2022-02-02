package presentation.view.order;

import model.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Clasa pentru crearea modelului abstract de tabel al JTable-ului de clienti din interfata grafica de introducere
 * a unei comenzi in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class OrderClientModel extends AbstractTableModel {
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
     * Constructorul clasei OrderClientModel
     * @param clientList lista tuturor clientilor din tabelul Customer al bazei de date
     */
    @SuppressWarnings("unchecked")
    public OrderClientModel(List<Customer> clientList) {
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
     * Metoda pentru stabilirea statutului de editabil/non-editabil al unei celule
     * @param rowIndex randul pe care se afla celula
     * @param columnIndex coloana pe care se afla celula
     * @return true daca celula este editabila; false in caz contrar
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
