package presentation.view.order;

import model.Customer;
import model.Product;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru crearea interfetei grafice de introducere a unei comenzi in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class OrderFrame extends JFrame {
    /**
     * JPanel al etichetei cu masejul de alegere a unui client
     */
    private JPanel panelChooseClient;
    /**
     * JScrollPane al tabelului de clienti
     */
    private JScrollPane scrollTableClient;
    /**
     * Panel al zonei de alegere client din fereastra
     */
    private JPanel panelClient;
    /**
     * Lista tuturor clientilor din baza de date
     */
    private List<Customer> clientList;
    /**
     * Modelul de tabel pentru tabelul de clienti
     */
    private OrderClientModel clientModel;
    /**
     * JPanel al etichetei cu masejul de alegere a unui produs
     */
    private JPanel panelChooseProduct;
    /**
     * JScrollPane al tabelului de produse
     */
    private JScrollPane scrollTableProduct;
    /**
     * Panel al zonei de alegere produs din fereastra
     */
    private JPanel panelProduct;
    /**
     * Lista tuturor produselor din baza de date
     */
    private List<Product> productList;
    /**
     * Modelul de tabel pentru tabelul de produse
     */
    private OrderProductModel productModel;
    /**
     * JPanel al butonului de introducere a comenzii
     */
    private JPanel panelOrder;
    /**
     * Tabelul de clienti
     */
    private JTable tableClient;
    /**
     * Tabelul de produse
     */
    private JTable tableProduct;
    /**
     * Eticheta ce indica faptul ca se poate selecta clientul
     */
    private JLabel labelClient;
    /**
     * Eticheta ce indica faptul ca se poate selecta produsul
     */
    private JLabel labelProduct;
    /**
     * Butonul prin actionarea caruia se inregisreaza noile comenzi in baza de date
     */
    private JButton buttonOrder;

    /**
     * Constructorul ferestrei pentru introducerea unei noi comenzi in baza de date
     * @param customerList lista tuturor clientilor din baza de date
     * @param productList lista tuturor produselor din baza de date
     */
    public OrderFrame(List<Customer> customerList, List<Product> productList){
        setTitle("Place an order");
        setBackground(new Color(186, 200, 222));
        setSize(1800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.panelClient = new JPanel(new BorderLayout());

        this.clientList = customerList;
        clientModel = new OrderClientModel(clientList);
        tableClient = new JTable(clientModel);
        tableClient.setBounds(30, 40, 200, 300);
        tableClient.setRowHeight(30);
        tableClient.setBackground(new Color(186, 200, 222));
        tableClient.setFont(new Font("Copper Black", Font.PLAIN, 18));
        tableClient.setSelectionBackground(new Color(188, 143, 143));
        tableClient.setDefaultEditor(Object.class, null);

        JTableHeader header = tableClient.getTableHeader();
        header.setBackground(new Color(188, 143, 143));
        header.setFont(new Font("Copper Black", Font.BOLD, 18));

        scrollTableClient = new JScrollPane(tableClient);
        this.panelClient.add(scrollTableClient, BorderLayout.CENTER);

        labelClient = new JLabel("Choose client");
        labelClient.setForeground(new Color(188, 143, 143));
        labelClient.setFont(new Font("Copper Black", Font.BOLD, 25));
        labelClient.setPreferredSize(new Dimension(250, 50));

        this.panelChooseClient = new JPanel();
        panelChooseClient.setPreferredSize(new Dimension(900, 60));
        panelChooseClient.setBackground(new Color(186, 200, 222));
        panelChooseClient.add(labelClient);
        this.panelClient.add(panelChooseClient, BorderLayout.NORTH);
        this.add(panelClient, BorderLayout.WEST);

        this.panelProduct = new JPanel(new BorderLayout());
        this.productList = productList;
        this.productModel = new OrderProductModel(productList);
        tableProduct = new JTable(productModel);
        tableProduct.setBounds(30, 40, 200, 300);
        tableProduct.setRowHeight(30);
        tableProduct.setBackground(new Color(186, 200, 222));
        tableProduct.setFont(new Font("Copper Black", Font.PLAIN, 18));
        tableProduct.setSelectionBackground(new Color(188, 143, 143));
        tableProduct.setDefaultEditor(Object.class, null);

        JTextField auxiliary = new JTextField();
        auxiliary.setFont(new Font("Copper Black", Font.PLAIN, 18));
        auxiliary.setForeground(new Color(188, 143, 143));

        TableColumn col = tableProduct.getColumnModel().getColumn(tableProduct.getColumnCount() - 1);
        col.setCellEditor(new DefaultCellEditor(auxiliary));

        JTableHeader header1 = tableProduct.getTableHeader();
        header1.setBackground(new Color(188, 143, 143));
        header1.setFont(new Font("Copper Black", Font.BOLD, 18));

        scrollTableProduct = new JScrollPane(tableProduct);
        this.panelProduct.add(scrollTableProduct, BorderLayout.CENTER);

        labelProduct = new JLabel("Choose product");
        labelProduct.setForeground(new Color(188, 143, 143));
        labelProduct.setFont(new Font("Copper Black", Font.BOLD, 25));
        labelProduct.setPreferredSize(new Dimension(250, 50));

        panelChooseProduct = new JPanel();
        panelChooseProduct.setPreferredSize(new Dimension(884, 60));
        panelChooseProduct.setBackground(new Color(186, 200, 222));
        panelChooseProduct.add(labelProduct);

        this.panelProduct.add(panelChooseProduct, BorderLayout.NORTH);
        this.add(panelProduct, BorderLayout.EAST);

        buttonOrder = new JButton("Order");
        buttonOrder.setForeground(new Color(255, 255, 255));
        buttonOrder.setFont(new Font("Copper Black", Font.BOLD, 25));
        buttonOrder.setBackground(new Color(188, 143, 143));
        buttonOrder.setPreferredSize(new Dimension(250, 50));

        this.panelOrder = new JPanel();
        this.panelOrder.add(buttonOrder);
        this.panelOrder.setPreferredSize(new Dimension(1800, 60));
        this.panelOrder.setBackground(new Color(186, 200, 222));
        this.add(panelOrder, BorderLayout.SOUTH);
    }

    /**
     * Metoda ce returneaza randurile selectate din tabelul ce contine toti clientii; se vor selecta clientii ce comanda
     * @return lista clientilor selectati din tabelul ce contine toti clientii
     */
    public List<Customer> selectedClientRows(){
        List<Customer> list = new ArrayList<Customer>();

        int[] vector = tableClient.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            Customer objectToUpdate = clientList.get(vector[i]);
            list.add(objectToUpdate);
        }
        return list;
    }

    /**
     * Metoda ce returneaza randurile selectate din tabelul ce contine toate produsele; se vor produsele comandate
     * @return lista produselor selectate din tabelul ce contine toate produsele
     */
    public List<Product> selectedProductRows(){
        List<Product> list = new ArrayList<Product>();

        int[] vector = tableProduct.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            Product objectToUpdate = productList.get(vector[i]);
            list.add(objectToUpdate);
        }
        return list;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de inregistrare de noi comenzi in baza de date
     * @param add ActionListener-ul folosit
     */
    public void addOrderListener(ActionListener add){
        this.buttonOrder.addActionListener(add);
    }

}
