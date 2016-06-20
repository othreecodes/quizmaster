/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmaster;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.jfoenix.converters.DialogTransitionConverter;
import javafx.stage.StageStyle;

/**
 *
 * @author UCHENNA
 */
public class QuizMaster extends Application {

    private static void startQuizForFile(String arg) {
      
     //   System.out.println(arg);
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
          Scene scene = new Scene(new JFXDecorator(stage, root), 800, 800);
//        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setTitle("QUIZ - MASTER");
     
        stage.show();
        stage.getIcons().add(new Image(getClass().getResource("icon.png").toString()));
        stage.setMaximized(true);
         stage.setOnCloseRequest(e ->{
            e.consume();
           int value = JOptionPane.showConfirmDialog(null, "Are You Sure you Want to Quit?");
            if (value==JOptionPane.YES_OPTION){
                System.exit(0);
               
            }
        
            
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //  startQuizForFile(args[0]);
        launch(args);
    }
    
}
