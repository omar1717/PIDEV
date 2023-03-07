package user;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.facture;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import Utils.MyConnection;

public class AfficheFactureController {

    @FXML
    private Button btnSend;

    @FXML
    private Button btnValider;

    @FXML
    private TextField TFMail;

    @FXML
    private TextField TFSommePrix;

    @FXML
    private TableView<facture> tableAchats;

    @FXML
    private TableColumn<facture, String> colNom;

    @FXML
    private TableColumn<facture, Integer> colRef;

    @FXML
    private TableColumn<facture, Double> colPrix;

    private final ObservableList<facture> achats = FXCollections.observableArrayList();

    @FXML
    private Button btnImprimer;
    @FXML
    private Button RetourVersB;
   

    @FXML
    private void onSendBtnClick(ActionEvent event) throws AddressException, MessagingException {
        String x= TFMail.getText();
        
  String username = "ibrahim.alaeddine@esprit.tn";
String password = "223JMT2318";
String attachmentPath = "C:\\Users\\MSI\\Desktop\\PIDEVGIT\\Facture.pdf";
String to = x;

// create properties object to configure email server and protocol
Properties properties = new Properties();
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.ssl.trust", "*");
properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

// create session with authentication information
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
});

// prepare email message
try {
    
    //api pour verifier ladresse mail existe ou non
          
    
    
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(
        Message.RecipientType.TO,
        InternetAddress.parse(to)
    );
    message.setSubject("Invoice");
    
    // create the message body part
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Please find attached your invoice.");
    
    // create the attachment part
    MimeBodyPart attachmentPart = new MimeBodyPart();
    FileDataSource fileDataSource = new FileDataSource(attachmentPath) {
        @Override
        public String getContentType() {
            return "application/pdf";
        }
    };
    attachmentPart.setDataHandler(new DataHandler(fileDataSource));
    attachmentPart.setFileName(fileDataSource.getName());

    // create the multipart/related part
    MimeMultipart multipart = new MimeMultipart("related");
    multipart.addBodyPart(messageBodyPart);
    multipart.addBodyPart(attachmentPart);

    // add the multipart/related part to the message
    message.setContent(multipart);
    
    // send email
    Transport.send(message);

    // show confirmation message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Email sent");
    alert.setHeaderText(null);
    alert.setContentText("Email sent successfully.");
    alert.showAndWait();

} catch (MessagingException ex) {
    // show error message if email fails to send
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Email error");
    alert.setHeaderText(null);
    alert.setContentText("Failed to send email.");
    alert.showAndWait();
    System.out.println(ex);
}

   
    }
 public void ShowFacture(){
     getachatList();
        colNom.setCellValueFactory(new PropertyValueFactory<facture, String>("Nom_Achat"));
        colRef.setCellValueFactory(new PropertyValueFactory<facture, Integer> ("Ref_Achete"));
        colPrix.setCellValueFactory(new PropertyValueFactory<facture, Double> ("PrixU_Achat"));

        tableAchats.setItems(getachatList());
        double somme = 0;
for (facture f : tableAchats.getItems()) {
    somme += f.getPrixU_Achat();
    if (somme > 5){somme=(somme*50)/100;}
}

TFSommePrix.setText(String.valueOf(somme));

     
}
 
    public ObservableList<facture> getachatList(){
          Connection myconn = MyConnection.getInstance().getConnexion();
        ObservableList<facture> getachatList = FXCollections.observableArrayList();

        String query ="Select * FROM facture where Mail= ?  ";

        try 
        {
         PreparedStatement ste= myconn.prepareStatement(query);
         ste.setString(1,TFMail.getText());
           ResultSet rs = ste.executeQuery();

          while (rs.next())
          {
            facture  f = new facture(rs.getString(2) ,rs.getInt(3),rs.getDouble(4)); 
              getachatList.add(f);
          }
        }catch(SQLException ex){
        }
        return getachatList;
    }

    @FXML
    private void OnBtnClick(ActionEvent event) {
        if (event.getSource()== btnValider){
            ShowFacture();
}}
    @FXML
      private void imprimer(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
       
        if (event.getSource()== btnImprimer ){
        imprimer();
}}
   
   
    private void imprimer() throws SQLException, FileNotFoundException, DocumentException {
 String x= TFMail.getText();
 String y= TFSommePrix.getText();
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream("C:/Gen PDF/Facture.pdf"));
        document.open();
       document.addCreationDate();
        document.add(new Paragraph("Cher Client " + x + ", Voici votre facture."));
        
         document.add(new Paragraph("\n"));
        PdfPTable table = new PdfPTable(3);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Nom Achat"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Référence Achat"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Prix Unitaire"));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
                for (facture f : tableAchats.getItems()) {
                    table.addCell(f.getNom_Achat());
                    table.addCell(String.valueOf(f.getRef_Achete()));
                    table.addCell(String.valueOf(f.getPrixU_Achat()));
                    
}

            
document.add(table);
         document.add(new Paragraph("\n"));
         document.add(new Paragraph("Votre total a Payer " + y+"EN DINAR TUNISIAN" ));
document.close();


}
    @FXML
     public void retournerVersBoutique (ActionEvent e) throws IOException{
      SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene7(e);

}
}


