package presentation.view.client;

import model.Customer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru crearea interfetei grafice de editare client in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class EditClient extends JFrame {
    /**
     * Tabelul in care sunt vizibili toti clientii din baza de date si se pot face editari asupra acestora
     */
    private JTable table;
    /**
     * Buton prin actonarea caruia se realizeaza actualizarea bazei de date cu noile valori din tabel
     */
    private JButton btnEdit;
    /**
     * Modelul abstract al tabelului de clienti
     */
    private EditTableModelClient tableModel;
    /**
     * Lista tuturor clientilor din tabelul Customer al bazei de date
     */
    private List<Customer> clientList;

    /**
     * Constructorul ferestrei pentru introducerea datelor de editare a clientilor in baza de date
     * @param objectList lista tuturor clientilor din baza de date
     */
    public EditClient(List<Customer> objectList){
        setTitle("Edit clients");
        this.clientList = objectList;
        tableModel = new EditTableModelClient(objectList);
        table = new JTable(tableModel);
        table.setBounds(30, 40, 200, 300);
        table.setRowHeight(30);
        table.setBackground(new Color(186, 200, 222));
        table.setFont(new Font("Copper Black", Font.PLAIN, 18));
        table.setSelectionBackground(new Color(188, 143, 143));

        JTextField auxiliary = new JTextField();
        auxiliary.setFont(new Font("Copper Black", Font.PLAIN, 18));
        auxiliary.setForeground(new Color(188, 143, 143));
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setCellEditor(new DefaultCellEditor(auxiliary));
        }

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(188, 143, 143));
        header.setFont(new Font("Copper Black", Font.BOLD, 18));

        JScrollPane sp = new JScrollPane(table);
        add(sp);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnEdit = new JButton("Edit");
        btnEdit.setForeground(new Color(255, 255, 255));
        btnEdit.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnEdit.setBackground(new Color(188, 143, 143));
        btnEdit.setBounds(170, 480, 150, 50);
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(900, 60));
        p2.setBackground(new Color(186, 200, 222));
        p2.add(btnEdit);
        this.add(p2, BorderLayout.SOUTH);
    }

    /**
     * Metoda ce returneaza randurile selectate din tabelul ce contine toti clientii; se vor selecta randurile editate
     * @return lista clientilor selectati (editati) din tabelul ce contine toti clientii
     */
    public List<Customer> selectedRows(){
        List<Customer> list = new ArrayList<Customer>();

        int[] vector = table.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            Customer objectToUpdate = clientList.get(vector[i]);
            list.add(objectToUpdate);
        }
        return list;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de inregistrare a modificarilor de date ale clientilor in baza de date
     * @param edit ActionListener-ul folosit
     */
    public void addEditListener(ActionListener edit){
        this.btnEdit.addActionListener(edit);
    }

}
