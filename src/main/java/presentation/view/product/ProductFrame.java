package presentation.view.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de selectare a unei operatii pe tabelul Product din baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class ProductFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta cu mesajul de titlu al ferestrei de operatii pe produs
     */
    private JLabel productsMessage;
    /**
     * Eticheta ce indica alegerea urmatoarei operatii pe tabelul Product
     */
    private JLabel chooseOperation;
    /**
     * Buton prin actionarea caruia se deschide fereastra de adaugare a unui nou produs in baza de date
     */
    private JButton addProduct;
    /**
     * Buton prin actionarea caruia se deschide fereastra de editare produs
     */
    private JButton editProduct;
    /**
     * Buton prin actionarea caruia se deschide fereastra de stergere produs
     */
    private JButton deleteProduct;
    /**
     * Buton prin actionarea caruia se deschide fereastra de vizualizare a datelor tuturor produselor
     */
    private JButton viewAllProducts;

    /**
     * Constructorul ferestrei pentru selectarea operatiei dorite asupra tabelului Product
     */
    public ProductFrame() {
        setTitle("Products");
        setBounds(100, 100, 500, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        productsMessage = new JLabel("Products");
        productsMessage.setHorizontalAlignment(SwingConstants.CENTER);
        productsMessage.setForeground(new Color(188, 143, 143));
        productsMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        productsMessage.setBounds(0, 30, 500, 52);
        panel.add(productsMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\ProductFrame.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 130);
        panel.add(lblNewLabel1);

        chooseOperation = new JLabel("What next?");
        chooseOperation.setHorizontalAlignment(SwingConstants.LEFT);
        chooseOperation.setForeground(new Color(188, 143, 143));
        chooseOperation.setFont(new Font("Copper Black", Font.BOLD, 22));
        chooseOperation.setBounds(67, 210, 250, 40);
        panel.add(chooseOperation);

        addProduct = new JButton("Add new product");
        addProduct.setForeground(new Color(255, 255, 255));
        addProduct.setFont(new Font("Copper Black", Font.BOLD, 25));
        addProduct.setBackground(new Color(188, 143, 143));
        addProduct.setBounds(125, 280, 250, 50);
        panel.add(addProduct);

        editProduct = new JButton("Edit product");
        editProduct.setForeground(new Color(255, 255, 255));
        editProduct.setFont(new Font("Copper Black", Font.BOLD, 25));
        editProduct.setBackground(new Color(188, 143, 143));
        editProduct.setBounds(125, 360, 250, 50);
        panel.add(editProduct);

        deleteProduct = new JButton("Delete product");
        deleteProduct.setForeground(new Color(255, 255, 255));
        deleteProduct.setFont(new Font("Copper Black", Font.BOLD, 25));
        deleteProduct.setBackground(new Color(188, 143, 143));
        deleteProduct.setBounds(125, 440, 250, 50);
        panel.add(deleteProduct);

        viewAllProducts = new JButton("View all products");
        viewAllProducts.setForeground(new Color(255, 255, 255));
        viewAllProducts.setFont(new Font("Copper Black", Font.BOLD, 25));
        viewAllProducts.setBackground(new Color(188, 143, 143));
        viewAllProducts.setBounds(125, 520, 250, 50);
        panel.add(viewAllProducts);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de adaugare a unui produs
     * @param add ActionListener-ul folosit
     */
    public void addAddProductListener(ActionListener add){
        this.addProduct.addActionListener(add);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de modificare produs
     * @param edit ActionListener-ul folosit
     */
    public void addEditProductListener(ActionListener edit){
        this.editProduct.addActionListener(edit);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de stergere produs
     * @param delete ActionListener-ul folosit
     */
    public void addDeleteProductListener(ActionListener delete){
        this.deleteProduct.addActionListener(delete);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de vizualizare a produselor
     * @param view ActionListener-ul folosit
     */
    public void addViewProductsListener(ActionListener view){
        this.viewAllProducts.addActionListener(view);
    }
}
