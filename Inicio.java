/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urnaeletronica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author max
 */
public class Inicio extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
    semLogin(primaryStage);
        //comLogin(primaryStage);       
    }
    
    public void semLogin(Stage stage) {
        Parent root;
        //recuperaStage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("Tela2.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Urna Eletrônica - 2018");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void comLogin(Stage stage) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            //stage.initStyle(StageStyle.TRANSPARENT);
            //stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
