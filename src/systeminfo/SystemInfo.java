package systeminfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author AlexandreSCorreia
 */
public class SystemInfo extends Application {
    
    @Override
     public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("scanner.png")));
        primaryStage.setTitle("Ferramenta para capturar dados da máquina");
        primaryStage.setResizable(false);
        // https://www.programcreek.com/java-api-examples/?class=javafx.stage.Stage&method=setOnCloseRequest
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
