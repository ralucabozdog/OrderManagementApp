package presentation.view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de adaugare a unui nou client in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class AddClient extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta mesajului de titlu al ferestrei
     */
    private JLabel addClientMessage;
    /**
     * Eticheta ce indica locul de introducere a prenumelui clientului
     */
    private JLabel labelFirstName;
    /**
     * JTextField de introducere a prenumelui clientului
     */
    private JTextField firstName;
    /**
     * Eticheta ce indica locul de introducere a numelui de familie al clientului
     */
    private JLabel labelLastName;
    /**
     * JTextField de introducere a numelui de familie al clientului
     */
    private JTextField lastName;
    /**
     * Eticheta ce indica locul de introducere a email-ului clientului
     */
    private JLabel labelEmail;
    /**
     * JTextField de introducere a email-ului clientului
     */
    private JTextField email;
    /**
     * Eticheta ce indica locul de introducere a numarului de telefon al clientului
     */
    private JLabel labelPhoneNumber;
    /**
     * JTextField de introducere a numarului de telefon al clientului
     */
    private JTextField phoneNumber;
    /**
     * Eticheta ce indica locul de introducere a adresei clientului
     */
    private JLabel labelAddress;
    /**
     * JTextField de introducere a adresei clientului
     */
    private JTextField address;
    /**
     * Buton pentru adaugarea in baza de date a clientului cu datele introduse
     */
    private JButton btnAdd;


    /**
     * Constructorul ferestrei pentru introducerea datelor de creare a unui nou client in baza de date
     */
    public AddClient() {
        setTitle("New client");
        setBounds(100, 100, 500, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        addClientMessage = new JLabel("Add new client");
        addClientMessage.setHorizontalAlignment(SwingConstants.CENTER);
        addClientMessage.setForeground(new Color(188, 143, 143));
        addClientMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        addClientMessage.setBounds(0, 30, 480, 52);
        panel.add(addClientMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\AddNewClient.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 120);
        panel.add(lblNewLabel1);

        labelFirstName = new JLabel("First name: ");
        labelFirstName.setHorizontalAlignment(SwingConstants.LEFT);
        labelFirstName.setForeground(new Color(188, 143, 143));
        labelFirstName.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelFirstName.setBounds(30, 220, 250, 30);
        panel.add(labelFirstName);

        firstName = new JTextField();
        firstName.setForeground(new Color(188, 143, 143));
        firstName.setFont(new Font("Copper Black", Font.BOLD, 20));
        firstName.setColumns(10);
        firstName.setBounds(170, 220, 280, 30);
        panel.add(firstName);

        labelLastName = new JLabel("Last name: ");
        labelLastName.setHorizontalAlignment(SwingConstants.LEFT);
        labelLastName.setForeground(new Color(188, 143, 143));
        labelLastName.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelLastName.setBounds(30, 270, 250, 30);
        panel.add(labelLastName);

        lastName = new JTextField();
        lastName.setForeground(new Color(188, 143, 143));
        lastName.setFont(new Font("Copper Black", Font.BOLD, 20));
        lastName.setColumns(10);
        lastName.setBounds(170, 270, 280, 30);
        panel.add(lastName);

        labelEmail = new JLabel("Email: ");
        labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
        labelEmail.setForeground(new Color(188, 143, 143));
        labelEmail.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelEmail.setBounds(30, 320, 250, 30);
        panel.add(labelEmail);

        email = new JTextField();
        email.setForeground(new Color(188, 143, 143));
        email.setFont(new Font("Copper Black", Font.BOLD, 20));
        email.setColumns(10);
        email.setBounds(170, 320, 280, 30);
        panel.add(email);

        labelPhoneNumber = new JLabel("Phone: ");
        labelPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
        labelPhoneNumber.setForeground(new Color(188, 143, 143));
        labelPhoneNumber.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelPhoneNumber.setBounds(30, 370, 250, 30);
        panel.add(labelPhoneNumber);

        phoneNumber = new JTextField();
        phoneNumber.setForeground(new Color(188, 143, 143));
        phoneNumber.setFont(new Font("Copper Black", Font.BOLD, 20));
        phoneNumber.setColumns(10);
        phoneNumber.setBounds(170, 370, 280, 30);
        panel.add(phoneNumber);

        labelAddress = new JLabel("Address: ");
        labelAddress.setHorizontalAlignment(SwingConstants.LEFT);
        labelAddress.setForeground(new Color(188, 143, 143));
        labelAddress.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelAddress.setBounds(30, 420, 250, 30);
        panel.add(labelAddress);

        address = new JTextField();
        address.setForeground(new Color(188, 143, 143));
        address.setFont(new Font("Copper Black", Font.BOLD, 20));
        address.setColumns(10);
        address.setBounds(170, 420, 280, 30);
        panel.add(address);


        btnAdd = new JButton("Add");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnAdd.setBackground(new Color(188, 143, 143));
        btnAdd.setBounds(170, 480, 150, 50);
        panel.add(btnAdd);
    }

    /**
     * Metoda getter pentru campul in care se introduce prenumele clientului
     * @return JTextField-ul in care se introduce prenumele clientului
     */
    public JTextField getFirstName() {
        return firstName;
    }

    /**
     * Metoda getter pentru campul in care se introduce numele de familie al clientului
     * @return JTextField-ul in care se introduce numele de familie al clientului
     */
    public JTextField getLastName() {
        return lastName;
    }

    /**
     * Metoda getter pentru campul in care se introduce email-ul clientului
     * @return JTextField-ul in care se introduce email-ul clientului
     */
    public JTextField getEmail() {
        return email;
    }

    /**
     * Metoda getter pentru campul in care se introduce numarul de telefon al clientului
     * @return JTextField-ul in care se introduce numarul de telefon al clientului
     */
    public JTextField getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Metoda getter pentru campul in care se introduce adresa clientului
     * @return JTextField-ul in care se introduce adresa clientului
     */
    public JTextField getAddress(){
        return address;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de adaugare a unui client in baza de date
     * @param add ActionListener-ul folosit
     */
    public void addAddListener(ActionListener add){
        this.btnAdd.addActionListener(add);
    }
}
