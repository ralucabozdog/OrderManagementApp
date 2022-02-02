package presentation.view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de selectare a unei operatii pe tabelul Customer din baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class ClientFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta cu mesajul de titlu al ferestrei de operatii pe client
     */
    private JLabel clientsMessage;
    /**
     * Eticheta ce indica alegerea urmatoarei operatii pe tabelul Customer
     */
    private JLabel chooseOperation;
    /**
     * Buton prin actionarea caruia se deschide fereastra de adaugare a unui nou client in baza de date
     */
    private JButton addClient;
    /**
     * Buton prin actionarea caruia se deschide fereastra de editare client
     */
    private JButton editClient;
    /**
     * Buton prin actionarea caruia se deschide fereastra de stergere client
     */
    private JButton deleteClient;
    /**
     * Buton prin actionarea caruia se deschide fereastra de vizualizare a datelor tuturor clientilor
     */
    private JButton viewAllClients;

    /**
     * Constructorul ferestrei pentru selectarea operatiei dorite asupra tabelului Customer
     */
    public ClientFrame() {
        setTitle("Clients");
        setBounds(100, 100, 500, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        clientsMessage = new JLabel("Clients");
        clientsMessage.setHorizontalAlignment(SwingConstants.CENTER);
        clientsMessage.setForeground(new Color(188, 143, 143));
        clientsMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        clientsMessage.setBounds(0, 30, 480, 52);
        panel.add(clientsMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\ClientFrame.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 120);
        panel.add(lblNewLabel1);

        chooseOperation = new JLabel("What next?");
        chooseOperation.setHorizontalAlignment(SwingConstants.LEFT);
        chooseOperation.setForeground(new Color(188, 143, 143));
        chooseOperation.setFont(new Font("Copper Black", Font.BOLD, 22));
        chooseOperation.setBounds(67, 210, 250, 40);
        panel.add(chooseOperation);

        addClient = new JButton("Add new client");
        addClient.setForeground(new Color(255, 255, 255));
        addClient.setFont(new Font("Copper Black", Font.BOLD, 25));
        addClient.setBackground(new Color(188, 143, 143));
        addClient.setBounds(125, 280, 250, 50);
        panel.add(addClient);

        editClient = new JButton("Edit client");
        editClient.setForeground(new Color(255, 255, 255));
        editClient.setFont(new Font("Copper Black", Font.BOLD, 25));
        editClient.setBackground(new Color(188, 143, 143));
        editClient.setBounds(125, 360, 250, 50);
        panel.add(editClient);

        deleteClient = new JButton("Delete client");
        deleteClient.setForeground(new Color(255, 255, 255));
        deleteClient.setFont(new Font("Copper Black", Font.BOLD, 25));
        deleteClient.setBackground(new Color(188, 143, 143));
        deleteClient.setBounds(125, 440, 250, 50);
        panel.add(deleteClient);

        viewAllClients = new JButton("View all clients");
        viewAllClients.setForeground(new Color(255, 255, 255));
        viewAllClients.setFont(new Font("Copper Black", Font.BOLD, 25));
        viewAllClients.setBackground(new Color(188, 143, 143));
        viewAllClients.setBounds(125, 520, 250, 50);
        panel.add(viewAllClients);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de adaugare a unui client
     * @param add ActionListener-ul folosit
     */
    public void addAddClientListener(ActionListener add){
        this.addClient.addActionListener(add);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de modificare client
     * @param edit ActionListener-ul folosit
     */
    public void addEditClientListener(ActionListener edit){
        this.editClient.addActionListener(edit);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de stergere client
     * @param delete ActionListener-ul folosit
     */
    public void addDeleteClientListener(ActionListener delete){
        this.deleteClient.addActionListener(delete);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de vizualizare a clientilor
     * @param view ActionListener-ul folosit
     */
    public void addViewClientsListener(ActionListener view){
        this.viewAllClients.addActionListener(view);
    }
}
