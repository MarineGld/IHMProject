import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Accueil extends Application {
    private OffreStageController offreStageController;
    private AccueilController accueilController;



    public static void main(String[] args) {
        launch();
        //  System.out.println(Database.getAllStage());
    }
    @Override
    public void start(Stage stage) throws Exception {
        offreStageController = new OffreStageController(this);
        accueilController = new AccueilController(this);

        FXMLLoader rootAcc = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        rootAcc.setController(this.getAccueilController());
        stage.setScene(new Scene(rootAcc.load()));
        stage.setTitle("GPh-internships");
        stage.show();

    }


    public OffreStageController getOffreStageController() {
        return offreStageController;
    }

    public AccueilController getAccueilController() {
        return accueilController;
    }


}
