import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OffreStageController{
    private Accueil accueil;
    public OffreStageController(Accueil accueil){
        this.accueil = accueil;
    }

    public void goToAccueil(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        FXMLLoader newScene = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        newScene.setController(accueil.getAccueilController());
        Stage leStage =(Stage) button.getScene().getWindow();
        leStage.setScene(new Scene(newScene.load()));
}
        }