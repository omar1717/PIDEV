/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneControllerAla {

 private Stage stage;
 private Scene scene;
 private Parent root;
 
 public void switchToScene1(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("GBillet.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
  public void switchToScene2(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("Main2.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
  public void switchToScene3(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("IntAdminPRORES.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
  public void switchToScene4(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("gestionUP21.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
   public void switchToScene5(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("GSB21.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
      public void switchToScene6(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("affichefacture.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
            public void switchToScene7(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("InterfaceBoutique.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
}