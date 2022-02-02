package presentation.view.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de adaugare a unui nou produs in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class AddProduct extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta mesajului de titlu al ferestrei
     */
    private JLabel addProductMessage;
    /**
     * Eticheta ce indica locul de introducere a numelui produsului
     */
    private JLabel labelProductName;
    /**
     * JTextField de introducere a numelui produsului
     */
    private JTextField productName;
    /**
     * Eticheta ce indica locul de introducere a pretului produsului
     */
    private JLabel labelPrice;
    /**
     * JTextField de introducere a pretului produsului
     */
    private JTextField price;
    /**
     * Eticheta ce indica locul de introducere a cantitatii produsului
     */
    private JLabel labelQuantity;
    /**
     * JTextField de introducere a cantitatii produsului
     */
    private JTextField quantity;
    /**
     * Buton pentru adaugarea in baza de date a produsului cu datele introduse
     */
    private JButton btnAdd;

    /**
     * Constructorul ferestrei pentru introducerea datelor de creare a unui nou produs in baza de date
     */
    public AddProduct() {
        setTitle("New product");
        setBounds(100, 100, 500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        addProductMessage = new JLabel("Add new product");
        addProductMessage.setHorizontalAlignment(SwingConstants.CENTER);
        addProductMessage.setForeground(new Color(188, 143, 143));
        addProductMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        addProductMessage.setBounds(0, 30, 480, 52);
        panel.add(addProductMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\AddNewProduct.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 130);
        panel.add(lblNewLabel1);

        labelProductName = new JLabel("Name: ");
        labelProductName.setHorizontalAlignment(SwingConstants.LEFT);
        labelProductName.setForeground(new Color(188, 143, 143));
        labelProductName.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelProductName.setBounds(85, 240, 250, 30);
        panel.add(labelProductName);

        productName = new JTextField();
        productName.setForeground(new Color(188, 143, 143));
        productName.setFont(new Font("Copper Black", Font.BOLD, 20));
        productName.setColumns(10);
        productName.setBounds(205, 240, 200, 30);
        panel.add(productName);

        labelPrice = new JLabel("Price: ");
        labelPrice.setHorizontalAlignment(SwingConstants.LEFT);
        labelPrice.setForeground(new Color(188, 143, 143));
        labelPrice.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelPrice.setBounds(85, 290, 250, 30);
        panel.add(labelPrice);

        price = new JTextField();
        price.setForeground(new Color(188, 143, 143));
        price.setFont(new Font("Copper Black", Font.BOLD, 20));
        price.setColumns(10);
        price.setBounds(205, 290, 200, 30);
        panel.add(price);

        labelQuantity = new JLabel("Quantity: ");
        labelQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        labelQuantity.setForeground(new Color(188, 143, 143));
        labelQuantity.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelQuantity.setBounds(85, 340, 150, 30);
        panel.add(labelQuantity);

        quantity = new JTextField();
        quantity.setForeground(new Color(188, 143, 143));
        quantity.setFont(new Font("Copper Black", Font.BOLD, 20));
        quantity.setColumns(10);
        quantity.setBounds(205, 340, 200, 30);
        panel.add(quantity);

        btnAdd = new JButton("Add");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnAdd.setBackground(new Color(188, 143, 143));
        btnAdd.setBounds(170, 410, 150, 50);
        panel.add(btnAdd);
    }

    /**
     * Metoda getter pentru campul in care se introduce numele produsului
     * @return JTextField-ul in care se introduce numele prodsului
     */
    public JTextField getProductName() {
        return productName;
    }

    /**
     * Metoda getter pentru campul in care se introduce pretul produsului
     * @return JTextField-ul in care se introduce pretul prodsului
     */
    public JTextField getPrice() {
        return price;
    }

    /**
     * Metoda getter pentru campul in care se introduce cantitatea produsului
     * @return JTextField-ul in care se introduce cantitatea prodsului
     */
    public JTextField getQuantity() {
        return quantity;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de adaugare a unui produs in baza de date
     * @param add ActionListener-ul folosit
     */
    public void addAddListener(ActionListener add){
        this.btnAdd.addActionListener(add);
    }
}
