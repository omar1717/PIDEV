
package user;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Entities.event;
import SERVICES.eventservice;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class EventfController implements Initializable {

    @FXML
    private TableColumn<event, String> DDCOL;

    @FXML
    private TableColumn<event, String> DFCOL;

    private TableColumn<event, String> NACOL;

    @FXML
    private TableColumn<event, String> NSCOL;
    
    private FilteredList<event> listeFiltree;
    private ObservableList<event> listee;
    
    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<event, String> TECOL;

    @FXML
    private AnchorPane frontimg;
    @FXML
    private ImageView front;
    @FXML
    private TableView<event> table;
      
        @FXML
    private Button btnPDF;
  @FXML
private Button btnparticiper;
    @FXML
    private Button sortButton;
    @FXML
    private Button refreshbtn;
    @FXML
    private Button back;
 


  public void show() {
    ObservableList<event> listee = FXCollections.observableArrayList();
    eventservice es = new eventservice();
    listee = es.afficherevent();



    TECOL.setCellValueFactory(new PropertyValueFactory<>("type_event"));

    NSCOL.setCellValueFactory(new PropertyValueFactory<>("nbre_salle"));
    DDCOL.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
    DFCOL.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

    table.setItems(listee);
}





    
    @FXML
    void exportToPdf(ActionEvent event) {
        
      
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:/Gen PDF/events.pdf"));
            document.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumns().size());
            // add table headers
            for (TableColumn<event, ?> column : table.getColumns()) {
                pdfTable.addCell(column.getText());
            }
            // add table rows
            for (event evt : table.getItems()) {
                pdfTable.addCell(evt.getType_event());
         
                pdfTable.addCell(evt.getNbre_salle());
                //pdfTable.addCell(evt.getDate_deb().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                //pdfTable.addCell(evt.getDate_fin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                pdfTable.addCell(evt.getDate_deb().toString());
                 pdfTable.addCell(evt.getDate_fin().toString());
            }
            document.add(pdfTable);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        
    }
    
    @FXML
    void onComboBoxChanged(ActionEvent eventt) {
    String option = combobox.getValue();
    System.out.println("ComboBox changed");       

    // Appliquer le filtre sur la liste complète des articles
    if (option.equals("film")) {
        listeFiltree.setPredicate(event -> event.getType_event().equals("film"));
    } else if (option.equals("ceremonie")) {
        listeFiltree.setPredicate(event -> event.getType_event().equals("ceremonie"));
    } else if (option.equals("fete")) {
        listeFiltree.setPredicate(event -> event.getType_event().equals("fete"));
    } else {
        listeFiltree.setPredicate(null);
    }
        table.refresh();

    System.out.println(listeFiltree);
}

    @FXML
     void participer(ActionEvent event) {
    
      try
    {
    
          Parent root= FXMLLoader.load(getClass().getResource("participation.fxml"));
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     catch (Exception e) {
          // e.printstackTrace (); 
     }
    
    }
     
      @FXML
  void handleSortButtonAction(ActionEvent event) {
      table.getSortOrder().clear(); // Clear any existing sort order
    DDCOL.setSortType(TableColumn.SortType.ASCENDING);
    table.getSortOrder().add(DDCOL);
    }
     
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        combobox.getItems().addAll("film", "ceremonie", "fete");
    eventservice a = new eventservice();
    ObservableList<event> listee = FXCollections.observableArrayList();

        listee=a.afficherevent();

    // Créer une liste filtrée vide
    listeFiltree = new FilteredList<>(listee);

    // Ajouter les colonnes du TableView

    TECOL.setCellValueFactory(new PropertyValueFactory<>("type_event"));

    
    NSCOL.setCellValueFactory(new PropertyValueFactory<>("nbre_salle"));

    DDCOL.setCellValueFactory(new PropertyValueFactory<>("date_deb"));

    DFCOL.setCellValueFactory(new PropertyValueFactory<>("date_fin"));


    // Afficher les articles dans le TableView
    table.setItems(listeFiltree);     
        

            }

    @FXML
    void refresh(ActionEvent event) {
        show();
        
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
          SceneController sc = new SceneController();
          sc.switchToScene9(event);
    }

   

}
