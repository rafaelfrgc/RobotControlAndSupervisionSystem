package ApplicationControl;

import java.io.IOException;
import org.nepas.sbg.*;

/**
 * Java application Main Class to test SBG Communication Library.
 * For more information regarding SBG output data, please refer to the SBG documentation
 * 
 * @author	Alexandre José Tiberti
 * @version	1.0, 2023-05-10
 */
public class SBG implements SBGComListener {
    private SystemController controller;
    private SBGComClass sbgCom;
    
    public void setController(SystemController controller){
        this.controller = controller;
    }

    /** 
     * Main method 
     *
     * @param args	command line arguments
     */
    public void connect() {
        String sComPort = "COM1";
        long liBaudRate = 115200;
        sbgCom = new SBGComClass();

        try {
            sbgCom.connect(sComPort, liBaudRate);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Start continuous output of Euler and Gyroscopes Raw data 
     */
    public void resume(){
        try {
            sbgCom.startContinuousOutput(this, SBGOutput.OUTPUT_EULER | SBGOutput.OUTPUT_GYROSCOPES_RAW, 500);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Stop continuous output of Euler and Gyroscopes Raw data
     */
    public void pause(){
        try{
            sbgCom.stopContinuousOutput();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }    
    }
    
    // Close connection..
    public void close(){
        try{
            sbgCom.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } 
        catch (Exception e) {
            e.printStackTrace(System.out);
        }    
    }

    /**
     * Print SGB output data to standard output
     *
     * @param sbgOutput	output data to print
     */
    public static void showOutput(SBGOutput sbgOutput) {
        System.out.printf("Mask 0x%08x\n", sbgOutput.outputMask);
        System.out.printf("Euler data is %.3f (row) %.3f (pitch) %.3f (yaw).\n", sbgOutput.stateEuler[0], sbgOutput.stateEuler[1], sbgOutput.stateEuler[2]);
        System.out.printf("Gyro Raw is [%d, %d, %d].\n", sbgOutput.gyroscopesRaw[0], sbgOutput.gyroscopesRaw[1], sbgOutput.gyroscopesRaw[2]);
    }

    /**
     * Show application help.
     */
    public static void showHelp() {
        System.out.println(
                "Use SGBTest <ComPort> [BaudRate]\n"
                + "  <ComPort>  communication port to open\n"
                + "  [BaudRate] baud rate used to communicate with the device, default is 115200"
        );
    }

    /**
     * Listener event to handle SBG error on get output data. Print error to
     * standard output
     */
    @Override
    public void sbgError(SBGComClass sbgComObj, int error) {
        System.out.println("Error: " + sbgComObj.getErrorMsg(error));
    }

    /**
     * Listener event to handle SBG output data. Print data to standard output
     */
    @Override
    public void sbgOutput(SBGComClass sbgComObj, SBGOutput sbgOutput) {
        controller.updateLabel(sbgOutput);
        showOutput(sbgOutput);
    }
}