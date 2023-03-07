  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class HomePageController implements Initializable {

    @FXML
    private ImageView imageslider;

    String images[] ={"file:/../src/Images/1.jpg",
        "file:/../src/Images/2.jpg",
    "file:/../src/Images/3.jpg",
    "file:/../src/Images/4.jpg",
            "file:/../src/Images/5.jpg"
    };
    @FXML
    private JFXButton hprofile;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton boutique;
    @FXML
    private JFXButton articles;
    @FXML
    private JFXButton artists;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SliderThread st = new SliderThread();
       st.start();
    }    

    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
          if(event.getSource() == hprofile ){
           
          
           sc.switchToScene4(event);
    }
            if(event.getSource() == events ){
           
          
           sc.switchToScene13(event);
    }
            if (event.getSource()== articles){
                sc.switchToScene19(event);
            }
            if (event.getSource()== artists) {
                sc.switchToScene20(event);
            }
               if (event.getSource()== boutique) {
                sc.switchToScene25(event);
            }
    }
    
    
    class SliderThread extends Thread{
        int i=0;
        @Override
        public void run(){
            try{
                while (true){
                    sleep(5000);
                    if(i>=images.length)
                        i=0;
                    imageslider.setImage(new Image(images[i]));
                    i++;
                }
            }catch (InterruptedException e){
                        throw new RuntimeException(e.getMessage());
                        }
            }
        }
                
    }
    

