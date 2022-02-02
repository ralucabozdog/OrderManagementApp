package presentation.view.product;

import model.Product;
import presentation.view.ErrorPopUp;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Clasa pentru crearea modelului abstract de tabel al JTable-ului din interfata grafica de editare produs in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class EditTableModelProduct extends AbstractTableModel {
    /**
     * Lista tuturor produselor din tabelul Customer al bazei de date
     */
    private List<Product> productList;
    /**
     * "Matricea" de String-uri ce retine datele despre un produs pe un rand, cu fiecare variabila instanta pe cate o coloana
     */
    private String[][] products;
    /**
     * Lista numelor coloanelor tabelului
     */
    private String[] columnNames;

    /**
     * Constructorul clasei EditTableModelProduct
     * @param productList lista tuturor produselor din tabelul Product al bazei de date
     */
    @SuppressWarnings("unchecked")
    public EditTableModelProduct(List<Product> productList)
    {
        this.productList = productList;
        int cols = 3;
        int rows = productList.size();

        products = new String[rows][cols];
        columnNames = new String[cols];

        columnNames[0] = new String("productName");
        columnNames[1] = new String("price");
        columnNames[2] = new String("quantity");
        int j = 0;
        for(Product t: productList){
            products[j][0] = t.getProductName();
            products[j][1] = t.getPrice().toString();
            products[j][2] = Integer.toString(t.getQuantity());
            j++;
        }
    }

    /**
     * Metoda pentru aflarea numelui unei coloane in functie de indicele sau
     * @param column indicele coloanei
     * @return numele coloanei cu indicele column
     */
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    /**
     * Metoda pentru aflarea numarului total de coloane al tabelului
     * @return numarul total de coloane din tabel
     */
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    /**
     * Metoda pentru aflarea numarului total de linii al tabelului
     * @return numarul de linii din tabel
     */
    @Override
    public int getRowCount()
    {
        return productList.size();
    }

    /**
     * Metoda pentru returnarea obiectului aflat in celula de la intersectia dintre o anumita linie si o anumita coloana
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     * @return obiectul aflat in celula de la intersectia dintre linia rowIndex si coloana columnIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Product row = productList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getProductName();
        }
        else if(1 == columnIndex) {
            return row.getPrice();
        }
        else if(2 == columnIndex) {
            return row.getQuantity();
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Product row = productList.get(rowIndex);
        if(0 == columnIndex) {
            if(aValue.toString().length() != 0){
                row.setProductName((String) aValue);
            }
            else{
                new ErrorPopUp("Product name cannot be null").setVisible(true);
            }

        }
        else if(1 == columnIndex) {
            try{
                row.setPrice(Double.parseDouble((String)aValue));
            }catch(NumberFormatException e){
                new ErrorPopUp("Price has to be a real number").setVisible(true);
            }
        }
        else if(2 == columnIndex) {
            try{
                row.setQuantity(Integer.parseInt((String)aValue));
            }catch(NumberFormatException e){
                new ErrorPopUp("Quantity has to be an integer").setVisible(true);
            }
        }
    }

    /**
     * Metoda pentru stabilirea statutului de editabil/non-editabil al unei celule
     * @param rowIndex randul pe care se afla celula
     * @param columnIndex coloana pe care se afla celula
     * @return true daca celula este editabila; false in caz contrar
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
}
