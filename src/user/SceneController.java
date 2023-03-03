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


public class SceneController {

 private Stage stage;
 private Scene scene;
 private Parent root;
 
 public void switchToScene1(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 
 public void switchToScene2(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 
  public void switchToScene3(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
  public void switchToScene4(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
  
  
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
public void switchToScene5(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Password.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }

public void switchToScene6(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("vote.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
public void switchToScene7(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Avotes.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
public void switchToScene8(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Resp.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show(); 
 }
public void switchToScene9(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show(); 
 }
public void switchToScene10(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show(); 
 }
public void switchToScene11(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show(); 
 }


}