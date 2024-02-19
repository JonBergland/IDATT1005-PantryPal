package no.ntnu.idatt1005;

import no.ntnu.idatt1005.view.MyWindow;

/**
 * Use this class to start the application
 * @author nilstes
 */
public class MyApp {

    /**
     * Main method for my application
     */
    public static void main(String[] args) throws Exception {
        MyWindow window = new MyWindow("The Window");
        window.setVisible(true);
   }  
}
