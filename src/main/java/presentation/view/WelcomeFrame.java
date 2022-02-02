package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea primei ferestre vizualizate de user la deschiderea aplicatiei, fereastra din care se poate alege
 * daca urmatoarea operatie va fi asupra tabelului de clienti, produse sau comenzi
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class WelcomeFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce contine mesajul de bun-venit
     */
    private JLabel welcomeMessage;
    /**
     * Eticheta ce contine indicatia de alegere a urmatoarei sectiuni de functionare a programului
     */
    private JLabel chooseOperation;
    /**
     * Buton de selectare ca urmatoarea operatie sa fie una asupra tabelului Customer
     */
    private JButton clientOperations;
    /**
     * Buton de selectare ca urmatoarea operatie sa fie una asupra tabelului Produc
     */
    private JButton productOperations;
    /**
     * Buton de selectare ca urmatoarea operatie sa fie una asupra tabelului Order
     */
    private JButton placeOrder;

    /**
     * Constructorul clasei WelcomeFrame
     */
    public WelcomeFrame() {
        setTitle("Online store");
        setBounds(100, 100, 500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        welcomeMessage = new JLabel("Welcome to our online store!");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessage.setForeground(new Color(188, 143, 143));
        welcomeMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        welcomeMessage.setBounds(0, 30, 480, 52);
        panel.add(welcomeMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\WelcomeFrame.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 130);
        panel.add(lblNewLabel1);

        chooseOperation = new JLabel("Where to?");
        chooseOperation.setHorizontalAlignment(SwingConstants.LEFT);
        chooseOperation.setForeground(new Color(188, 143, 143));
        chooseOperation.setFont(new Font("Copper Black", Font.BOLD, 22));
        chooseOperation.setBounds(67, 210, 250, 40);
        panel.add(chooseOperation);

        clientOperations = new JButton("Open Clients");
        clientOperations.setForeground(new Color(255, 255, 255));
        clientOperations.setFont(new Font("Copper Black", Font.BOLD, 25));
        clientOperations.setBackground(new Color(188, 143, 143));
        clientOperations.setBounds(125, 280, 250, 50);
        panel.add(clientOperations);

        productOperations = new JButton("Open Products");
        productOperations.setForeground(new Color(255, 255, 255));
        productOperations.setFont(new Font("Copper Black", Font.BOLD, 25));
        productOperations.setBackground(new Color(188, 143, 143));
        productOperations.setBounds(125, 360, 250, 50);
        panel.add(productOperations);

        placeOrder = new JButton("Place an order");
        placeOrder.setForeground(new Color(255, 255, 255));
        placeOrder.setFont(new Font("Copper Black", Font.BOLD, 25));
        placeOrder.setBackground(new Color(188, 143, 143));
        placeOrder.setBounds(125, 440, 250, 50);
        panel.add(placeOrder);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de alegere ca urmatoarea operatie sa fie asupra tabelului Customer
     * @param client ActionListener-ul folosit
     */
    public void addClientListener(ActionListener client){
        this.clientOperations.addActionListener(client);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de alegere ca urmatoarea operatie sa fie asupra tabelului Product
     * @param product ActionListener-ul folosit
     */
    public void addProductListener(ActionListener product){
        this.productOperations.addActionListener(product);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de alegere ca urmatoarea operatie sa fie asupra tabelului Order
     * @param order ActionListener-ul folosit
     */
    public void addOrderListener(ActionListener order){
        this.placeOrder.addActionListener(order);
    }
}
