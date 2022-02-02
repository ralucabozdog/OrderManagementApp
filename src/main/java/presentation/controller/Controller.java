package presentation.controller;
import bll.types.CustomerBLL;
import bll.types.ProductBLL;
import bll.validators.InvalidDataException;
import bll.validators.ValidatePositiveDouble;
import bll.validators.ValidatePositiveInteger;
import dao.CustomerDAO;
import dao.PlacedOrderDAO;
import dao.ProductDAO;
import dao.RequestDAO;
import model.Customer;
import model.PlacedOrder;
import model.Product;
import model.Request;
import presentation.view.ErrorPopUp;
import presentation.view.WelcomeFrame;
import presentation.view.client.*;
import presentation.view.order.OrderFrame;
import presentation.view.product.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clasa pentru realizarea conexiunii dintre Model si View
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class Controller {
    private WelcomeFrame welcomeFrame;
    private ClientFrame clientFrame;
    private ProductFrame productFrame;
    private OrderFrame orderFrame;

    private AddClient addClient;
    private EditClient editClient;
    private DeleteClient deleteClient;


    private AddProduct addProduct;
    private DeleteProduct deleteProduct;
    private EditProduct editProduct;

    /**
     * Constructorul clasei Controller
     * @param welcomeFrame fereastra de pornire a programului
     */
    public Controller(WelcomeFrame welcomeFrame){
        this.welcomeFrame = welcomeFrame;
        this.welcomeFrame.addClientListener(new OpeningClientFrame());
        this.welcomeFrame.addProductListener(new OpeningProductFrame());
        this.welcomeFrame.addOrderListener(new OpeningOrderFrame());
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de alegere a operatiilor pe tabelul Customer
     */
    class OpeningClientFrame implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientFrame = new ClientFrame();
            clientFrame.addAddClientListener(new OpeningAddClient());
            clientFrame.addEditClientListener(new OpeningEditClient());
            clientFrame.addDeleteClientListener(new OpeningDeleteClient());
            clientFrame.addViewClientsListener(new OpeningViewClient());
            clientFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de alegere a operatiilor pe tabelul Product
     */
    class OpeningProductFrame implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            productFrame = new ProductFrame();
            productFrame.addAddProductListener(new OpeningAddProduct());
            productFrame.addEditProductListener(new OpeningEditProduct());
            productFrame.addDeleteProductListener(new OpeningDeleteProduct());
            productFrame.addViewProductsListener(new OpeningViewProduct());
            productFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de alegere a operatiilor pe tabelul Order
     */
    class OpeningOrderFrame implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerBLL customerBLL = new CustomerBLL();
            ProductBLL productBLL = new ProductBLL();
            List<Customer> customerList = customerBLL.findAllCustomers();
            List<Product> productList = productBLL.findAllProducts();
            orderFrame = new OrderFrame(customerList, productList);
            orderFrame.addOrderListener(new PlacingOrder());
            orderFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de adaugare a unui client
     */
    class OpeningAddClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            addClient = new AddClient();
            addClient.addAddListener(new PerformingAddClient());
            addClient.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de editare client
     */
    class OpeningEditClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerDAO customerDAO = new CustomerDAO();
            List<Customer> customerList = customerDAO.findAll();
            editClient = new EditClient(customerList);
            editClient.addEditListener(new PerformingEditClient());
            editClient.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de stergere client
     */
    class OpeningDeleteClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerDAO customerDAO = new CustomerDAO();
            List<Customer> customerList = customerDAO.findAll();
            deleteClient = new DeleteClient();
            deleteClient.buildTable(customerList);
            deleteClient.addDeleteListener(new PerformingDeleteClient());
            deleteClient.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de vizualizare a tuturor clientilor
     */
    class OpeningViewClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerDAO customerDAO = new CustomerDAO();
            List<Customer> customerList = customerDAO.findAll();
            ViewClient viewClient = new ViewClient();
            viewClient.buildTable(customerList);
            viewClient.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de adaugare a unui produs in baza de date
     */
    class OpeningAddProduct implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            addProduct = new AddProduct();
            addProduct.addAddListener(new PerformingAddProduct());
            addProduct.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de editare produs
     */
    class OpeningEditProduct implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.findAll();
            editProduct = new EditProduct(productList);
            editProduct.addEditListener(new PerformingEditProduct());
            editProduct.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de stergere produs
     */
    class OpeningDeleteProduct implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.findAll();
            deleteProduct = new DeleteProduct();
            deleteProduct.buildTable(productList);
            deleteProduct.addDeleteListener(new PerformingDeleteProduct());
            deleteProduct.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de vizualizare a tuturor produselor din baza de date
     */
    class OpeningViewProduct implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.findAll();
            ViewProduct viewProduct = new ViewProduct();
            viewProduct.buildTable(productList);
            viewProduct.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de adaugare a unui client in baza de date
     */
    class PerformingAddClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = addClient.getFirstName().getText();
            String lastName = addClient.getLastName().getText();
            String email = addClient.getEmail().getText();
            String phone = addClient.getPhoneNumber().getText();
            String address = addClient.getAddress().getText();

            Customer customerToInsert = new Customer(firstName, lastName, email, phone, address);
            CustomerBLL customerBLL = new CustomerBLL();
            try {
                customerBLL.validate(customerToInsert);
                customerBLL.insertCustomer(customerToInsert);
                addClient.setVisible(false);
            } catch (InvalidDataException invalidDataException) {
                new ErrorPopUp(invalidDataException.getMessage()).setVisible(true);
            }
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de editare client
     */
    class PerformingEditClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Customer>clientsToEdit = editClient.selectedRows();
            CustomerDAO productDAO = new CustomerDAO();
            for(Customer c : clientsToEdit){
                productDAO.update(c.getId(), c);
            }
            editClient.setVisible(false);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de stergere client
     */
    class PerformingDeleteClient implements ActionListener {

        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Integer>clientsToDelete = deleteClient.selectedRows();
            CustomerDAO customerDAO = new CustomerDAO();
            for(Integer i : clientsToDelete){
                customerDAO.delete(i);
            }
            deleteClient.setVisible(false);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de adaugare a unui produs in baza de date
     */
    class PerformingAddProduct implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = addProduct.getProductName().getText();
            Double price = -1.1;
            int quantity = -1;
            if (!addProduct.getQuantity().getText().isEmpty())
                if (!addProduct.getPrice().getText().isEmpty()) {
                    try {
                        new ValidatePositiveInteger().validate(addProduct.getQuantity().getText());
                        quantity = Integer.parseInt(addProduct.getQuantity().getText());
                        new ValidatePositiveDouble().validate(addProduct.getPrice().getText());
                        price = Double.parseDouble(addProduct.getPrice().getText());
                        Product productToInsert = new Product(productName, price, quantity);
                        ProductBLL productBLL = new ProductBLL();
                        productBLL.validate(productToInsert);
                        productBLL.insertProduct(productToInsert);
                        addProduct.setVisible(false);
                    } catch (InvalidDataException invalidDataException) {
                        new ErrorPopUp(invalidDataException.getMessage()).setVisible(true);
                    }
                } else {
                    new ErrorPopUp("Fill in price field").setVisible(true);
                }
            else {
                new ErrorPopUp("Fill in quantity field").setVisible(true);
            }
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de editare produs
     */
    class PerformingEditProduct implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Product>productsToEdit = editProduct.selectedRows();
            ProductDAO productDAO = new ProductDAO();
            for(Product p : productsToEdit){
                productDAO.update(p.getId(), p);
            }
            editProduct.setVisible(false);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de stergere produs
     */
    class PerformingDeleteProduct implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Integer>productsToDelete = deleteProduct.selectedRows();
            ProductDAO productDAO = new ProductDAO();
            for(Integer i : productsToDelete){
                productDAO.delete(i);
            }
            deleteProduct.setVisible(false);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de adaugare a unei comenzi in baza de date
     */
    class PlacingOrder implements ActionListener{
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e){
            List<Customer> customerList = orderFrame.selectedClientRows();
            List<Product> productList = orderFrame.selectedProductRows();
            RequestDAO requestDAO = new RequestDAO();
            PlacedOrderDAO placedOrderDAO = new PlacedOrderDAO();

            for(Customer customer : customerList){
                List<PlacedOrder> placedOrders = placedOrderDAO.findAll();
                int currentNumber = placedOrders.size();
                String str = new String("Order " + (currentNumber + 1));
                try {
                    FileWriter fileWriter = new FileWriter(str + ".txt" , true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(str + "\n\n" + customer.getFirstName() + ", thank you for your order!\n\nWe will provide further details by mail, to " + customer.getEmail() + ".\nYour products will be delivered to " + customer.getAddress() + " in no time.\nOn the day of the delivery, we will send a text message to " + customer.getPhone() + " to let you know your products are near.\n\nHere is a quick overview of your order:\n\n");

                    Double total = 0.0;
                    int i = 1;
                    for(Product product : productList){
                        Request request = new Request(customer.getId(), product.getId(), product.getQuantity());
                        total = total + product.getPrice() * product.getQuantity();
                        bufferedWriter.write("Product " + i + ":    " + product.getProductName() + "        " + product.getPrice() + " RON      x " + product.getQuantity() + "\n");
                        requestDAO.insert(request);
                        i++;
                    }
                    placedOrderDAO.insert(new PlacedOrder(total));
                    bufferedWriter.write("\nTOTAL:      " + total + " RON");
                    bufferedWriter.close();
                    fileWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
            }
            orderFrame.setVisible(false);
        }
    }
}
