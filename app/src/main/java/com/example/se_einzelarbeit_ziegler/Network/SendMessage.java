// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler.Network;

import android.widget.TextView;
import com.example.se_einzelarbeit_ziegler.MainActivity;
import com.example.se_einzelarbeit_ziegler.Util.ActivityUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class for message sending and retrieving via TCP Socket
 */
public class SendMessage implements Runnable {
    //Main Activity
    private final MainActivity context;
    //Matrikel number or however it is called in english, studentid?
    private final String matno;
    //Element which the output should be written to
    private final int textViewID;

    // Network stuff
    private Socket clientSocket;
    private DataOutputStream output;
    private BufferedReader input;

    /**
     * Constructor for SendMessage Class
     *
     * @param context acivity
     * @param matno   the matrikelnumber
     */
    public SendMessage(MainActivity context, String matno, int textViewID) {
        this.context = context;
        this.matno = matno;
        this.textViewID = textViewID;
    }

    /**
     * Standard runnable run function
     * This function handles all of the magic,
     * connection -> writing to server -> reading from server(as string) -> closing connection
     */
    @Override
    public void run() {
        TextView outputTextView = (TextView) ActivityUtil.getView(context, textViewID);
        outputTextView.setText("initializing connection...");
        try {
            //Initialize Connection
            initializeConnection();
            outputTextView.setText("fetching please be patient...");

            //write bytes (send message)
            output.writeBytes(this.matno + "\n");

            //write return to the textview
            outputTextView.setText(input.readLine());

            //Close connection
            closeConnection();
        } catch (Exception ex) {
            delegateErrorDialog(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Initializes the network socket as well as the I/O Reader/Writer
     */
    private void initializeConnection() {
        try {
            clientSocket = new Socket(MainActivity.HOST, MainActivity.PORT);

            output = new DataOutputStream(clientSocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception ex) {
            delegateErrorDialog(ex.getMessage());
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Closes the connection socket as well as the input and output readers/writers
     */
    private void closeConnection() {
        if (input != null && output != null && clientSocket != null) {
            try {
                input.close();
                output.close();
                clientSocket.close();
            } catch (Exception ex) {
                delegateErrorDialog(ex.getMessage());
                System.out.println(ex.getMessage());
            }
        } else {
            delegateErrorDialog("The connection was not initialized correctly...");
            System.out.println("The connection was not initialized correctly, can't close whats not open!");
        }
    }

    /**
     * Delegate the show error dialog to the main ui thread to show the user his wrongdoing
     *
     * @param errorMsg the exception message
     */
    private void delegateErrorDialog(String errorMsg) {
        //run on main thread, otherwise the program makes boom boom
        context.runOnUiThread(() -> context.displayError(errorMsg));

    }
}
