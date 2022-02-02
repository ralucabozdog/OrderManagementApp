package start;

import dao.CustomerDAO;
import dao.ProductDAO;
import dao.RequestDAO;
import model.Customer;
import model.Product;
import model.Request;
import presentation.controller.Controller;
import presentation.view.WelcomeFrame;
import presentation.view.client.*;
import presentation.view.order.OrderFrame;
import presentation.view.product.*;

import java.util.List;

/**
 * Clasa principala a programului, singura clasa runnable
 *
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class MainClass {
    /**
     * Metoda main a programului
     * @param args argumentele liniei de comanda
     */
    public static void main(String[] args){
        WelcomeFrame welcomeFrame = new WelcomeFrame();

        Controller controller = new Controller(welcomeFrame);
        welcomeFrame.setVisible(true);
    }
}
