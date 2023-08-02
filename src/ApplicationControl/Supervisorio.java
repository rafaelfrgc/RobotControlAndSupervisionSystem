package ApplicationControl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class responsible for starting the JavaFX application and loading the FXML file with the GUI elements and controller class (main class)
 * @author Rafael
 */
public class Supervisorio extends Application {
    
    /**
     * Start method to be called on main main method
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml")); // Load FXML file
        Parent root = loader.load(); 
 
        Scene scene = new Scene(root); // Create scene with FXML file elements 
        
        stage.setScene(scene); // Set scene to stage
        stage.show(); // Show stage
        
        stage.setOnCloseRequest(event -> { // Close the JavaFX application when the window is closed
            Platform.exit();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}