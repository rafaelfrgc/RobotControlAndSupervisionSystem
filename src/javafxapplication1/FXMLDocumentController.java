package javafxapplication1;

import static java.lang.Character.getNumericValue;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 *
 * @author Rafael
 */
public class FXMLDocumentController implements Initializable {
    
    //Instantiates a StatsUpdateRunnable object for change in methods below
    Server server = new Server();
    
    
    //Anchor panes
    
    @FXML private AnchorPane anchor_pane_Background;
    @FXML private AnchorPane anchor_pane_CameraSimulator;
    
    //Grid panes
    
    @FXML private GridPane grid_pane_MainControls;
    @FXML private GridPane grid_pane_Stats;
    @FXML private GridPane grid_pane_CameraSimulator;
    @FXML private GridPane grid_pane_SteeringAngleInput;
    @FXML private GridPane grid_pane_PriorityList;
    @FXML private GridPane grid_pane_ManualControls;
    
    //Control and input buttons
    
    @FXML private Button button_Start;
    @FXML private Button button_Pause;
    @FXML private Button button_Stop;
    @FXML private Button button_SumAngle;
    @FXML private Button button_SubtractAngle;
    @FXML private Button button_AlignWheels;
    @FXML private Button button_SendData;
    
    //Input and output text fields
    
    @FXML private TextField text_field_StereoCamera;
    @FXML private TextField text_field_OmniCamera;
    @FXML private TextField text_field_DecimalStereo;
    @FXML private TextField text_field_DecimalOmni;
    @FXML private TextField text_field_SteeringAngle;
    @FXML private TextField text_field_SendData;
    @FXML private TextField text_field_Feedback;
    
    //Static labels (Don't change)
    
    @FXML private Label label_Controls;
    @FXML private Label label_Stats;
    @FXML private Label label_CameraSimulator;
    @FXML private Label label_PriorityList;
    
    //Number labels
    
    @FXML private Label label_ManualControl;
    @FXML private Label label_RollNumber;
    @FXML private Label label_YawNumber;
    @FXML private Label label_PitchNumber;
    @FXML private Label label_CompassNumber;
    @FXML private Label label_LatitudeNumber;
    @FXML private Label label_LongitudeNumber;
    @FXML private Label label_HeightNumber;
    @FXML private Label label_CurrentSpeedNumber;
    @FXML private Label label_TraveledDistanceNumber;
    @FXML private Label label_SteeringAngleNumber;
    @FXML private Label label_SteeringAngleOutputNumber;
    
    //Warning labels
    
    @FXML private Label label_RamStatusWarning;
    @FXML private Label label_SideTiltAngleWarning;
    @FXML private Label label_FrontTiltAngleWarning;
    @FXML private Label label_OdLeWarning;
    @FXML private Label label_OdCenterWarning;
    @FXML private Label label_OdLdWarning;
    @FXML private Label label_OpLeWarning;
    @FXML private Label label_OpCenterWarning;
    @FXML private Label label_OpLdWarning;
    @FXML private Label label_FrontWarning;
    @FXML private Label label_BackWarning;
    @FXML private Label label_LeftSideWarning;
    @FXML private Label label_RightSideWarning;
    @FXML private Label label_P1Warning;
    @FXML private Label label_P2Warning;
    @FXML private Label label_P3Warning;
    @FXML private Label label_P4Warning;
    
    //Miscellaenous labels
    
    @FXML private Label label_TimeAndDateMiscellaneous;
    
    //Texts
    
    @FXML private Text text_Messages;
    @FXML private Text text_WarningMessagesText;
    @FXML private Text text_RamStatus;
    @FXML private Text text_SideTiltAngle;
    @FXML private Text text_FrontTiltAngle;
    @FXML private Text text_TimeAndDate;
    @FXML private Text text_Roll;
    @FXML private Text text_Pitch;
    @FXML private Text text_Yaw;
    @FXML private Text text_Compass;
    @FXML private Text text_Latitude;
    @FXML private Text text_Longitude;
    @FXML private Text text_Height;
    @FXML private Text text_CurrentSpeed;
    @FXML private Text text_TravelledDistance;
    @FXML private Text text_SteeringAngleStats;
    @FXML private Text text_StereoCamera;
    @FXML private Text text_OmniCamera;
    @FXML private Text text_DecimalOmni;
    @FXML private Text text_DecimalStereo;
    @FXML private Text text_SteeringAngleInput;
    @FXML private Text text_SystemFeedback;
    
    
    /**
     * Method responsible for setting warning labels color, style, and possible error messages
     * @param label_toChange label to be changed
     * @param codeDigit Binary digit from code to set label to valid or invalid
     */
    private void change_Omni_labelWarningColor(Label label_toChange, char codeDigit){

        if(codeDigit == '1') label_toChange.setStyle("-fx-background-color: green; -fx-border-color: black;");
        else if(codeDigit == '0') label_toChange.setStyle("-fx-background-color: red;  -fx-border-color: black;");
        else change_Omni_AllLabelsWarningError();
    }
    
    /**
     * Method responsible for setting warning labels color, style, and possible error messages
     * @param label_toChange label to be changed
     * @param codeDigit Binary digit from code to set label to valid or invalid
     */
    private void change_Stereo_labelWarningColor(Label label_toChange, char codeDigit){

        if(codeDigit == '1') label_toChange.setStyle("-fx-background-color: green; -fx-border-color: black;");
        else if(codeDigit == '0') label_toChange.setStyle("-fx-background-color: red;  -fx-border-color: black;");
        else change_Stereo_AllLabelsWarningError();
    }
    
    /**
     * Method for setting all labels in their error state
     */
    private void change_Omni_AllLabelsWarningError(){
         label_FrontWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         label_FrontWarning.setText("Erro");
         
         label_RightSideWarning.setText("Erro");
         label_RightSideWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_BackWarning.setText("Erro");
         label_BackWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_LeftSideWarning.setText("Erro");
         label_LeftSideWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         text_field_OmniCamera.setDisable(true);
    }
    
    /**
     * Method for setting all labels in their error state
     */
    private void change_Stereo_AllLabelsWarningError(){
         
         label_OdLeWarning.setText("Erro");
         label_OdLeWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_OdCenterWarning.setText("Erro");
         label_OdCenterWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_OdLdWarning.setText("Erro");
         label_OdLdWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_OpLeWarning.setText("Erro");
         label_OpLeWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_OpCenterWarning.setText("Erro");
         label_OpCenterWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
         
         label_OpLdWarning.setText("Erro");
         label_OpLdWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");

         text_field_StereoCamera.setDisable(true);
    }
    
    /**
     * Method for resetting all labels to their original state
     * @param lastDigit last digit of input binary code
     */
    private void reset_Stereo_AllLabelsWarning(int lastDigit){
        
        if(lastDigit == 0){
            label_OpLeWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
            label_OpCenterWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
            label_OpLdWarning.setStyle("-fx-background-color: red; -fx-border-color: black;"); 
        }
        else if(lastDigit== 1){
            label_OdLeWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
            label_OdCenterWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");
            label_OdLdWarning.setStyle("-fx-background-color: red; -fx-border-color: black;");            
        }
    }
    
    /**
     * Method for verifying which label to change state
     * @param currentIndex Index of binary digit being evaluated
     * @param codeDigit binary digit being evaluated
     */
    private void verifiy_Omni_label(int currentIndex, char codeDigit){
        switch(currentIndex){
            case 0:
                change_Omni_labelWarningColor(label_FrontWarning, codeDigit);
                break;
            case 1:
                change_Omni_labelWarningColor(label_RightSideWarning, codeDigit);
                break;
            case 2:
                change_Omni_labelWarningColor(label_BackWarning, codeDigit);
                break;
            case 3:
                change_Omni_labelWarningColor(label_LeftSideWarning, codeDigit);
                break;
            default:
                change_Omni_AllLabelsWarningError();
                break;
        }
    }
    
    /**
     * Method for verifying Stereo Camera labels and changing its state accordingly
     * @param lastDigit Integer that marks the last Binary digit of the input code 
     * @param currentIndex current index of digit being verified 
     * @param codeDigit current index code digit 
     */
    private void verify_Stereo_label(int lastDigit, int currentIndex, char codeDigit){
        switch(lastDigit){
            case 0:
                reset_Stereo_AllLabelsWarning(lastDigit);
                switch(currentIndex){
                    case 0://OdLeWarning
                        change_Stereo_labelWarningColor(label_OdLeWarning, codeDigit);
                        break;
                    case 1://OdCenterWarning
                        change_Stereo_labelWarningColor(label_OdCenterWarning, codeDigit);
                        break;
                    case 2://OdLdWarning
                        change_Stereo_labelWarningColor(label_OdLdWarning, codeDigit);
                        break;
                    default:
                        change_Stereo_AllLabelsWarningError();
                        break;
                    }
                break;
            case 1:
                reset_Stereo_AllLabelsWarning(lastDigit);
                switch(currentIndex){
                    case 0://OpLeWarning
                        change_Stereo_labelWarningColor(label_OpLeWarning, codeDigit);
                        break;
                    case 1://OpCenterWarning
                        change_Stereo_labelWarningColor(label_OpCenterWarning, codeDigit);
                        break;
                    case 2://OpLdWarning
                        change_Stereo_labelWarningColor(label_OpLdWarning, codeDigit);
                        break;
                    default:
                        change_Stereo_AllLabelsWarningError();
                        break;
                    }
                break;
            default:
                change_Stereo_AllLabelsWarningError();
                break;
        }
    }
    
    /**
     * Method for controlling Omnidirectional camera warning labels
     */
    public void omni_camera_warning_colorControl(){
        
        String code = text_field_OmniCamera.getText();
        
        if(code.length() == 4){
            char[] numbers = new char[4]; //Code character array
            code.getChars(0, 4, numbers, 0);
        
            for(int i = 0; i < 4;  i++) verifiy_Omni_label(i, numbers[i]);
        }
        else change_Omni_AllLabelsWarningError();
    }
     
    public void stereo_camera_warning_colorControl(){
         String code = text_field_StereoCamera.getText();       
         if(code.length() == 4){
            char[] numbers = new char[4]; //Code character array
            code.getChars(0, 4, numbers, 0);
            int lastIndex = getNumericValue(numbers[3]);

            for(int i = 0; i < 3;  i++){
                verify_Stereo_label(lastIndex, i, numbers[i]);
            }
        }
        else change_Stereo_AllLabelsWarningError();
     }
     
    public void startDataCollection(){
         server.setController(this);
        //Instantiates a statsUpdateThread thread to start a server thread when requested
        Thread statsUpdateServerThread = new Thread(server);
        statsUpdateServerThread.start();
     }
     
    public void stopDataCollection(){
         server.interrupt();
     }
    
    public void stopProgram(){
        System.exit(0);   
    }
    
    public void updateLabel(String[] data) {
        Platform.runLater(() -> {
            label_RollNumber.setText(data[2]);
            label_YawNumber.setText(data[0]);
            label_PitchNumber.setText(data[1]);
         });
    }
    
    public void resetAllStatsLabels(){
         Platform.runLater(() -> {
            label_RollNumber.setText("No data");
            label_YawNumber.setText("No data");
            label_PitchNumber.setText("No data");
        });
     }
      
    public void setWarningMessages(int MessageFlag, int tries){
        Platform.runLater(() -> {     
            switch(MessageFlag){
                case 0:
                    text_WarningMessagesText.setText("Connection to server successfull!");
                    break;
                case 1:
                    text_WarningMessagesText.setText("Server cannot estabilish connection to client, retrying in 5 seconds, " + tries + " tries remaining...");
                    break;
                case 2:
                    text_WarningMessagesText.setText("Could not connect to server, please restart the program!");
                    break;
                case 3:
                    text_WarningMessagesText.setText("Could not create socket, server cannot start, please restart the program!");
                    break;
                case 4:
                    text_WarningMessagesText.setText("Server paused, please restart with client connected!");
            }
        });      
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    } 
}
