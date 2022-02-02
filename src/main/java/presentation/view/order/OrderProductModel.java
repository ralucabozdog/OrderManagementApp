package presentation.view.order;

import bll.validators.InvalidDataException;
import bll.validators.product.QuantityValidator;
import model.Product;
import presentation.view.ErrorPopUp;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Clasa pentru crearea modelului abstract de tabel al JTable-ului de produse din interfata grafica de introducere
 * a unei comenzi in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class OrderProductModel extends AbstractTableModel {
    /**
     * Lista tuturor clientilor din tabelul Product al bazei de date
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
     * Constructorul clasei OrderProductModel
     * @param productList lista tuturor produselor din tabelul Product al bazei de date
     */
    @SuppressWarnings("unchecked")
    public OrderProductModel(List<Product> productList)
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
        if(2 == columnIndex) {
            try{
                int val = Integer.parseInt((String)aValue);
                Product product = new Product();
                product.setQuantity(val);
                QuantityValidator productValidator = new QuantityValidator();
                try {
                    productValidator.validate(product);
                    if(product.getQuantity() <= row.getQuantity())
                        row.setQuantity(val);
                    else{
                        StringBuilder sb = new StringBuilder();
                        sb.append("We only have ");
                        sb.append(row.getQuantity());
                        sb.append(" ");
                        sb.append(row.getProductName());
                        sb.append(" in our store");
                        new ErrorPopUp(sb.toString()).setVisible(true);
                    }
                } catch (InvalidDataException invalidDataException) {
                    new ErrorPopUp("Quantity has to be a positive integer").setVisible(true);
                }
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
        if(columnIndex == (columnNames.length - 1))
            return true;
        return false;
    }
}
