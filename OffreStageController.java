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

    @FXML
    private ToggleGroup Promobutton;


    public ObservableList<OffreStage> data = FXCollections.observableArrayList();

    @FXML
    public void viewStage() {
        try (Connection connection = Database.connect(Database.location)) {
            String sql = "SELECT * FROM mesStages;";
            PreparedStatement stat = connection.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            data.clear();
            while (rs.next()) {
                data.add(new OffreStage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));
            }
            connection.close();


        } catch (Exception e) {
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

    @FXML
    public void goToAccueil(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        FXMLLoader newScene = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        newScene.setController(accueil.getAccueilController());
        Stage leStage = (Stage) button.getScene().getWindow();
        leStage.setScene(new Scene(newScene.load()));
    }

    @FXML
    public void actAjouter(ActionEvent e) {
        Button button = (Button) e.getSource();
        Carre_bleu.setVisible(true);
        Saisie_Nom.setText("");
        Saisie_Sujet.setText("");
        Saisie_Duree.setText("");
        Saisie_Date.setText("");
        Boutton_L3.setSelected(false);
        Boutton_M1.setSelected(false);
        Boutton_M2.setSelected(false);
        Boutton_Supprimer.setVisible(false);

    }

    @FXML
    public void actAnnuler(ActionEvent e) {
        Button button = (Button) e.getSource();
        Carre_bleu.setVisible(false);
        /*  Boutton_Ajouter.setVisible(true); */
    }

    @FXML
    public void actValider(ActionEvent e) {
        Button button = (Button) e.getSource();
        Carre_bleu.setVisible(false);
        if (Boutton_Supprimer.isVisible()) { addModifyStage(2); }
        else { addModifyStage(1); }
        viewStage();
        /* Boutton_Ajouter.setVisible(true); */
    }

    @FXML
    public void actSupprimer(ActionEvent e) {
        Button button = (Button) e.getSource();
        Carre_bleu.setVisible(false);
        deleteStage();
        viewStage();
        /*Boutton_Ajouter.setVisible(true);*/
    }

    @FXML
    public void modifSetUp() {
        if (Table.getSelectionModel().getSelectedItem() != null) {
            OffreStage lestage = (OffreStage) Table.getSelectionModel().getSelectedItem();

            Carre_bleu.setVisible(true);
            Saisie_Nom.setText(lestage.getNomStructure());
            Saisie_Sujet.setText(lestage.getSujetStage());
            Saisie_Duree.setText(String.valueOf(lestage.getDuree()));
            Saisie_Date.setText(lestage.getDebutStage());
            if (lestage.getPromo() == "L3") {
                Boutton_L3.setSelected(true);
            } else if (lestage.getPromo() == "M1") {
                Boutton_M1.setSelected(true);
            } else if (lestage.getPromo() == "M2") {
                Boutton_M2.setSelected(true);
            }
            Boutton_Supprimer.setVisible(true);
            /*Boutton_Ajouter.setVisible(false);*/
        }

    }

    @FXML
    public void deleteStage() {
        try (Connection connection = Database.connect(Database.location)) {
            OffreStage lestage = (OffreStage) Table.getSelectionModel().getSelectedItem();
            String idStage = String.valueOf(lestage.getId());
            String sql = "DELETE FROM mesStages WHERE ID = ";
            PreparedStatement stat = connection.prepareStatement(sql+idStage);
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNum(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    public void addModifyStage(int choix) {
        try (Connection connection = Database.connect(Database.location)) {
            boolean valide = true;

            String leNom = Saisie_Nom.getText();
            if (leNom=="") { valide = false;}

            String leSujet = Saisie_Sujet.getText();
            if (leSujet=="") { valide = false;}

            String laDuree = "";
            if (isNum(Saisie_Duree.getText())) {
                laDuree = Saisie_Duree.getText();
            } else { valide = false;}

            String laDate = Saisie_Date.getText();
            if (leSujet=="") { valide = false;}

            String laPromo = "0";
            if (Boutton_L3.isSelected()) {
                laPromo = "1";
            } else if (Boutton_M1.isSelected()) {
                laPromo = "2";
            } else if (Boutton_M2.isSelected()){
                laPromo = "3";
            } else { valide = false;}

            if (valide) {
                String sql = "";
                if (choix==1) { sql = "INSERT INTO mesStages VALUES (null, '" + leNom + "', '" + leSujet + "', " + laDuree + ", '" + laDate + "', " + laPromo + ")"; }
                else {
                    OffreStage lestage = (OffreStage) Table.getSelectionModel().getSelectedItem();
                    String idStage = String.valueOf(lestage.getId());
                    sql = "UPDATE mesStages SET nomStructure = '" + leNom + "', sujetStage = '" + leSujet + "', duree =" + laDuree + ", debutStage = '" + laDate + "', promo = " + laPromo + " where id = " + idStage;
                }
                PreparedStatement stat = connection.prepareStatement(sql);
                stat.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}