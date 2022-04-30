import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {
    private Accueil accueil;



    public AccueilController(Accueil accueil){
        this.accueil = accueil;
    }

    public void goToOffreStage(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        FXMLLoader newScene = new FXMLLoader(getClass().getResource("OffreStage.fxml"));
        newScene.setController(accueil.getOffreStageController());
        Stage leStage =(Stage) button.getScene().getWindow();
        leStage.setScene(new Scene(newScene.load()));
        accueil.getOffreStageController().viewStage();
    }
}
