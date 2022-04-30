import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        @FXML
        private TextField Saisie_Nom;

        @FXML
        private TextField Saisie_Sujet;

        @FXML
        private TextField Saisie_Duree;

        @FXML
        private TextField Saisie_Date;

        @FXML
        private RadioButton Boutton_L3;

        @FXML
        private RadioButton Boutton_M1;

        @FXML
        private RadioButton Boutton_M2;

        @FXML
        private AnchorPane Carre_bleu;

        @FXML
        private Button Boutton_Supprimer;

        @FXML
        private Button Boutton_Ajouter;

        @FXML
        private Button Boutton_Valider;

        @FXML
        private Button Boutton_Annuler;



        public ObservableList<OffreStage> data = FXCollections.observableArrayList();
        @FXML
        public void viewStage(){
            try (Connection connection = Database.connect(Database.location)){
                String sql = "SELECT * FROM mesStages";
                PreparedStatement stat = connection.prepareStatement(sql);
                ResultSet rs = stat.executeQuery();
                data.clear();
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

         public void clickLigne (ActionEvent e) {
            TableRow ligne = (TableRow) e.getSource();
            int id = ligne.getIndex();
            OffreStage lestage = data.get(id);
            modif(lestage);
    }

        public void addStage (ActionEvent e) {
          Button button = (Button) e.getSource();
            OffreStage lestage = data.get(1);
            modif(lestage);
          Carre_bleu.setVisible(true);

    }

        public void actAnnuler (ActionEvent e) {
            Button button = (Button) e.getSource();
            Carre_bleu.setVisible(false);
            Boutton_Ajouter.setVisible(true);
    }

        public void actValider (ActionEvent e) {
            Button button = (Button) e.getSource();

            Carre_bleu.setVisible(false);
            Boutton_Ajouter.setVisible(true);
    }

        public void modif (OffreStage lestage) {
            Carre_bleu.setVisible(true);
            Saisie_Nom.setText(lestage.getNomStructure());
            Saisie_Sujet.setText(lestage.getSujetStage());
            Saisie_Duree.setText(String.valueOf(lestage.getDuree()));
            Saisie_Date.setText(lestage.getDebutStage());
            if(lestage.getPromo()=="L3") { Boutton_L3.setSelected(true); }
            if(lestage.getPromo()=="M1") { Boutton_M1.setSelected(true); }
            if(lestage.getPromo()=="M2") { Boutton_M2.setSelected(true); }
            Boutton_Supprimer.setVisible(true);
    }

    }