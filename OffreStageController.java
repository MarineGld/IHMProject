import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class OffreStageController implements Initializable {

        @FXML
        private TableView<OffreStage> Table;
        @FXML
        private TableColumn<OffreStage, String> debutStage;

        @FXML
        private TableColumn<OffreStage, Integer> duree;

        @FXML
        private TableColumn<OffreStage, String> nomStructure;

        @FXML
        private TableColumn<OffreStage, Integer> promo;

        @FXML
        private TableColumn<OffreStage, String> sujetStage;



        public ObservableList<OffreStage> data = FXCollections.observableArrayList();
        @FXML
        public void viewStage(){
            try (Connection connection = Database.connect(Database.location)){
                String sql = "SELECT * FROM mesStages";
                PreparedStatement stat = connection.prepareStatement(sql);
                ResultSet rs = stat.executeQuery();
                while(rs.next()) {
                    data.add(new OffreStage(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));
                }
                connection.close();


        } catch(Exception e){
                e.printStackTrace();
            }

                nomStructure.setCellValueFactory(new PropertyValueFactory<OffreStage, String>("nomStructure"));
                sujetStage.setCellValueFactory(new PropertyValueFactory<OffreStage, String>("sujetStage"));
                debutStage.setCellValueFactory(new PropertyValueFactory<OffreStage, String>("debutStage"));
                duree.setCellValueFactory(new PropertyValueFactory<OffreStage, Integer>("duree"));
                promo.setCellValueFactory(new PropertyValueFactory<OffreStage, Integer>("promo"));
                Table.setItems(data);
            }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
        private Accueil accueil;
    public OffreStageController(Accueil accueil) {
            this.accueil = accueil;
        }

        public void goToAccueil (ActionEvent e) throws IOException {
            Button button = (Button) e.getSource();
            FXMLLoader newScene = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            newScene.setController(accueil.getAccueilController());
            Stage leStage = (Stage) button.getScene().getWindow();
            leStage.setScene(new Scene(newScene.load()));
        }


    }