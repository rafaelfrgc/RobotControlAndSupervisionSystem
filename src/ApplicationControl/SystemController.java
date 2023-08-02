package ApplicationControl;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.nepas.sbg.*;

/**
 * Class responsible for controlling the GUI elements and calling the methods from the SBG library to communicate with the SBG and the cameras.
 * Observation: All GUI elements are declared her. However, a minority of them are really used in the application. For future development, these can be manipulated or removed as needed.
 * Observation: The platform.runLater() method is used to update the GUI elements from the SBG library thread and cameras. This is necessary because the GUI elements can only be updated from the JavaFX thread.
 * @author Rafael
 */
public class SystemController implements Initializable {
    
    SBG sbg = new SBG(); // Create SBG object to communicate with the SBG 

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
    
    @FXML private Button button_Connect;
    @FXML private Button button_Pause;
    @FXML private Button button_Close;
    @FXML private Button button_Resume;
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
     * Method associated to the button Connect, where a connection to the SBG is attempted
     */
    public void connectToSBG(){
         sbg.setController(this);
         sbg.connect();
     }
     
    /**
     * Method associated to the button Close, where the connection to the SBG closed
     */
    public void closeConnectionToSBG(){
         sbg.close();
     }
    
    /**
     * Method associated to the button Resume, where the data collection from the SBG is started
     */
    public void resumeDataCollection(){
        sbg.resume();
    }
    
    /**
     *  Method associated to the button Resume, where the data collection from the SBG is paused
     */
    public void pauseDataCollection(){
        sbg.pause();
    }
    
   /**
    * Method associated to the button Stop, where the program is forcefully closed 
    */
    public void stopProgram(){
        System.exit(0);   
    }
    
    /**
     * Method for updating in real time the data in the Roll, Yaw and Pitch labels received from the SBG thread
     * @param data Array of strings containing the data
     */
    public void updateLabel(SBGOutput data) {
        Platform.runLater(() -> { // Run from JavaFX GUI thread 
            label_RollNumber.setText(String.format("%.3f°", data.stateEuler[0]*180/3.1415));
            label_YawNumber.setText(String.format("%.3f°", data.stateEuler[2]*180/3.1415));
            label_PitchNumber.setText(String.format("%.3f°", data.stateEuler[1]*180/3.1415));
         });
    }
    
    /**
     * Method for resetting the data in the Roll, Yaw and Pitch labels to their original state
     */
    public void resetAllStatsLabels(){
         Platform.runLater(() -> {
            label_RollNumber.setText("No data");
            label_YawNumber.setText("No data");
            label_PitchNumber.setText("No data");
        });
     }


    /**
     * Method for instantiating an OmniCamera object and calling is control method for setting label colors according to input
     */
    public void omniCameraControl(){
        OmniCamera omniCamera = new OmniCamera(label_FrontWarning, label_BackWarning, label_LeftSideWarning, label_RightSideWarning, text_field_OmniCamera);
        omniCamera.omniCameraColorControl();
    }
    
     /**
     * Method for instantiating an StereoCamera object and calling is control method for setting label colors according to input
     */
    public void stereoCameraControl(){
        StereoCamera stereoCamera = new StereoCamera(label_OdLeWarning,  label_OdCenterWarning,  label_OdLdWarning,  label_OpLeWarning,  label_OpCenterWarning,  label_OpLdWarning,  label_P1Warning,  label_P2Warning,   label_P3Warning,  text_field_StereoCamera);
        stereoCamera.stereoCameraColorControl();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
}
