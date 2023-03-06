package javafxapplication1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Server class that receives DatagramPackets using UDP protocol
 * @author Rafael
 */
public class Server implements Runnable{
    
   private FXMLDocumentController controller;
   private DatagramSocket socket;
   private boolean isRunning;
   
   public void setController(FXMLDocumentController controller){
       this.controller = controller;
   }
 
   public void interrupt(){
       isRunning = false;
       Thread.currentThread().interrupt();
   }
   
   @Override
    public void run() {
        isRunning = true;
        try {

            socket = new DatagramSocket(9876); //receives data from client and binds socket to port 9876
            socket.setSoTimeout(2500);//Sets socket timeout - 5s

            byte[] receiveData = new byte[140];//Data to be received from client

            while(isRunning){
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);//packet that receives data

                for(int tries = 10; tries > 0; tries--){//Tries to receive data for 10 times, if not, closes socket and server thread needs to be restarted
                    try{
                        socket.receive(receivePacket);
                        int MessageFlag_ServerSuccessfull = 0;
                        controller.setWarningMessages(MessageFlag_ServerSuccessfull, -1);
                        break;
                    }
                    catch (IOException ex) {
                        int MessageFlag_ServerNoConnection = 1;
                        controller.setWarningMessages(MessageFlag_ServerNoConnection , tries);
                    }
                    if(tries == 1){
                        int MessageFlag_ServerCouldNotConnect = 2;
                        controller.setWarningMessages(MessageFlag_ServerCouldNotConnect, -1);
                        break;
                    }
                }

                String data = new String(receivePacket.getData());
                String degreeSymbol = "°";

                String[] splitData = data.split(" ");
                splitData[0] = splitData[0].concat(degreeSymbol);
                splitData[1] = splitData[1].concat(degreeSymbol);
                splitData[2] = splitData[2].concat(degreeSymbol);

                controller.updateLabel(splitData);
            }
            int MessageFlag_ServerPaused = 4;
            controller.setWarningMessages(MessageFlag_ServerPaused, -1);
            socket.close();
            controller.resetAllStatsLabels();
        }
        catch (SocketException ex) {
            int MessageFlag_ServerNoSocket = 3;
            controller.setWarningMessages(MessageFlag_ServerNoSocket, -1);
        } 
   }
}