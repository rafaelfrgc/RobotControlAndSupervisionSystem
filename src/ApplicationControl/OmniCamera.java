package ApplicationControl;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Rafael
 * Class responsible for controlling Omni camera warning labels and text field input based on the binary digit code from the input 
 * Observation: future implementations might use or modify this logic to control the Omni camera based on automated input received from the camera
 */
public class OmniCamera {
    
    private Label label_FrontWarning;
    private Label label_BackWarning;
    private Label label_LeftSideWarning;
    private Label label_RightSideWarning;
    private TextField text_field_OmniCamera;

    /**
     * Constructor for OmniCamera class
     * @param label_FrontWarning 
     * @param label_BackWarning
     * @param label_LeftSideWarning
     * @param label_RightSideWarning
     * @param text_field_OmniCamera
     */
    public OmniCamera(Label label_FrontWarning, Label label_BackWarning, Label label_LeftSideWarning, Label label_RightSideWarning, TextField text_field_OmniCamera) {
        this.label_FrontWarning = label_FrontWarning;
        this.label_BackWarning = label_BackWarning;
        this.label_LeftSideWarning = label_LeftSideWarning;
        this.label_RightSideWarning = label_RightSideWarning;
        this.text_field_OmniCamera = text_field_OmniCamera;
    }
    
     /**
     * Method responsible for setting Omni camera warning labels color, style, and possible error messages based on the binary digit code from the input
     * @param label_toChange label to be changed
     * @param codeDigit Binary digit from code to set label to valid or invalid
     */
    private void changeOmniLabelColor(Label labelToChange, char codeDigit){
        switch(codeDigit){ 
            case '0':
                labelToChange.setStyle("-fx-background-color: red;  -fx-border-color: black;");
                break;
            case '1':
                labelToChange.setStyle("-fx-background-color: green; -fx-border-color: black;");
                break;
            default:
                changeOmniAllLabelsError();
                break;
        }
    }
    
     /**
     * Method for setting all labels in their error state
     */
    private void changeOmniAllLabelsError(){
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
     * Method for verifying which label to change state based on the binary digit received
     * @param currentIndex Index of binary digit being evaluated
     * @param codeDigit binary digit being evaluated
     */
    private void verifiyOmniLabel(int currentIndex, char codeDigit){
        switch(currentIndex){//Verifies which label to change state based on the binary digit received 
            case 0:
                changeOmniLabelColor(label_FrontWarning, codeDigit);
                break;
            case 1:
                changeOmniLabelColor(label_RightSideWarning, codeDigit);
                break;
            case 2:
                changeOmniLabelColor(label_BackWarning, codeDigit);
                break;
            case 3:
                changeOmniLabelColor(label_LeftSideWarning, codeDigit);
                break;
            default:
                changeOmniAllLabelsError();
                break;
        }
    }  
 
    /**
     * Method for controlling Omnidirectional camera warning labels
     */
    public void omniCameraColorControl(){
        
        String code = text_field_OmniCamera.getText(); //Code string from text field input
        
        if(code.length() == 4){ //If code is 4 digits long proceed
            char[] numbers = new char[4]; //Code character array
            code.getChars(0, 4, numbers, 0); //Code string to character array conversion 
        
            for(int i = 0; i < 4;  i++) verifiyOmniLabel(i, numbers[i]); //Verifies which label to change state based on the binary digit received 
        }
        else changeOmniAllLabelsError(); //If code is not 4 digits long set all labels to error state 
    }    
}