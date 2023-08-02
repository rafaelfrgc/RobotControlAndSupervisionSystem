package ApplicationControl;

import static java.lang.Character.getNumericValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Class responsible for controlling Stereo camera warning labels and text field input based on the binary digit code from the input
 * @author Rafael
 */
public class StereoCamera {
    
    // Warning labels for Stereo camera
    private Label label_OdLeWarning;
    private Label label_OdCenterWarning;
    private Label label_OdLdWarning;
    private Label label_OpLeWarning;
    private Label label_OpCenterWarning;
    private Label label_OpLdWarning;
    
    // Text field for Stereo camera input
    private TextField text_field_StereoCamera;
    
    // Priority labels 
    private Label label_P1Warning;
    private Label label_P2Warning;
    private Label label_P3Warning;

    /**
     * Constructor for StereoCamera class
     * @param label_OdLeWarning
     * @param label_OdCenterWarning
     * @param label_OdLdWarning
     * @param label_OpLeWarning
     * @param label_OpCenterWarning
     * @param label_OpLdWarning
     * @param label_P1Warning
     * @param label_P2Warning
     * @param label_P3Warning
     * @param text_field_StereoCamera
     */
    public StereoCamera(Label label_OdLeWarning, Label label_OdCenterWarning, Label label_OdLdWarning, Label label_OpLeWarning, Label label_OpCenterWarning, Label label_OpLdWarning, Label label_P1Warning, Label label_P2Warning, Label  label_P3Warning, TextField text_field_StereoCamera) {
        this.label_OdLeWarning = label_OdLeWarning;
        this.label_OdCenterWarning = label_OdCenterWarning;
        this.label_OdLdWarning = label_OdLdWarning;
        this.label_OpLeWarning = label_OpLeWarning;
        this.label_OpCenterWarning = label_OpCenterWarning;
        this.label_OpLdWarning = label_OpLdWarning;
        this.text_field_StereoCamera = text_field_StereoCamera;
        this.label_P1Warning = label_P1Warning;
        this.label_P2Warning = label_P2Warning;
        this.label_P3Warning = label_P3Warning;
    }
    
/**
     * Method responsible for setting Stereo camera warning labels color, style, and possible error messages based on the binary digit code from the input
     * @param label_toChange label to be changed
     * @param codeDigit Binary digit from code to set label to valid or invalid
     */
    private void changeStereoLabelColor(Label label_toChange, char codeDigit){       
        switch(codeDigit){
            case '0':
                label_toChange.setStyle("-fx-background-color: green; -fx-border-color: black;");
                break;
            case '1':
                label_toChange.setStyle("-fx-background-color: red;  -fx-border-color: black;");
                break;
            default:
                 changeAllStereoLabelsToError();
                 break;
        }
    }
    
    /**
     * Method for setting all labels in their error state
     */
    private void changeAllStereoLabelsToError(){
         
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
    private void resetAllStereoLabels(int lastDigit){
        
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
     * Method for changing a priority label to a certain color given the code received from the cameras
     * @param priorityLabelToChange Label object related to that label 
     * @param color String of the color to change to.
     */
    private void changePriorityLabels(Label priorityLabelToChange, String color){
        priorityLabelToChange.setStyle("-fx-background-color: " + color + "; -fx-border-color: black;");       
    }
    
    /**
     * Method for verifying Stereo Camera labels and changing its state accordingly
     * @param lastDigit Integer that marks the last Binary digit of the input code 
     * @param currentIndex current index of digit being verified 
     * @param codeDigit current index code digit 
     */
    private void verifyStereoLabel(int lastDigit, int currentIndex, char codeDigit){
        switch(lastDigit){
            case 0:
                resetAllStereoLabels(lastDigit);
                changePriorityLabels(label_P1Warning, "lightblue");
                changePriorityLabels(label_P2Warning, "yellow");
                changePriorityLabels(label_P3Warning, "lightblue");
                switch(currentIndex){
                    case 0://OdLeWarning
                        changeStereoLabelColor(label_OdLeWarning, codeDigit);
                        break;
                    case 1://OdCenterWarning
                        changeStereoLabelColor(label_OdCenterWarning, codeDigit);
                        break;
                    case 2://OdLdWarning
                        changeStereoLabelColor(label_OdLdWarning, codeDigit);
                        break;
                    default:
                        changeAllStereoLabelsToError();
                        break;
                    }
                break;
            case 1:
                resetAllStereoLabels(lastDigit);
                changePriorityLabels(label_P1Warning, "lightblue");
                changePriorityLabels(label_P2Warning, "lightblue");
                changePriorityLabels(label_P3Warning, "red");
                switch(currentIndex){
                    case 0://OpLeWarning
                        changeStereoLabelColor(label_OpLeWarning, codeDigit);
                        break;
                    case 1://OpCenterWarning
                        changeStereoLabelColor(label_OpCenterWarning, codeDigit);
                        break;
                    case 2://OpLdWarning
                        changeStereoLabelColor(label_OpLdWarning, codeDigit);
                        break;
                    default:
                        changeAllStereoLabelsToError();
                        break;
                    }
                break;
            default:
                changePriorityLabels(label_P1Warning, "green");
                changePriorityLabels(label_P2Warning, "lightblue");
                changePriorityLabels(label_P3Warning, "lightblue");
                changeAllStereoLabelsToError();
                break;
        }
    }

    /**
     * Method for controlling Stereo camera warning labels
     */
    public void stereoCameraColorControl(){
         String code = text_field_StereoCamera.getText();       
         if(code.length() == 4){
            char[] numbers = new char[4]; //Code character array
            code.getChars(0, 4, numbers, 0);
            int lastIndex = getNumericValue(numbers[3]);

            for(int i = 0; i < 3;  i++){
                verifyStereoLabel(lastIndex, i, numbers[i]);
            }
        }
        else changeAllStereoLabelsToError();
     }
}