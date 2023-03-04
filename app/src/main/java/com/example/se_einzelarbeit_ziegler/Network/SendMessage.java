// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler.Network;

import android.widget.TextView;
import com.example.se_einzelarbeit_ziegler.MainActivity;
import com.example.se_einzelarbeit_ziegler.R;
import com.example.se_einzelarbeit_ziegler.Util.ActivityUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class for message sending and retrieving via TCP Socket
 */
public class SendMessage implements Runnable {
    private final MainActivity context;
    private final boolean correctInput;
    private final String matno;

    /**
     * Constructor for SendMessage Class
     *
     * @param context      acivity
     * @param correctInput boolean for correct number input
     * @param matno        the matrikelnumber
     */
    public SendMessage(MainActivity context, boolean correctInput, String matno) {
        this.context = context;
        this.correctInput = correctInput;
        this.matno = matno;
    }

    /**
     * Standard runnable run function
     * This function handles all of the magic,
     * connection -> writing to server -> reading from server(as string) -> closing connection
     */
    @Override
    public void run() {
        TextView outputTextView = (TextView) ActivityUtil.getView(context, R.id.txtOutput);

        if (correctInput) {
            outputTextView.setText((String)"initializing connection...");
            try {
                Socket clientSocket = new Socket(MainActivity.HOST, MainActivity.PORT);

                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                outputTextView.setText((String)"fetching please be patient...");

                output.writeBytes(this.matno + "\n");

                outputTextView.setText(input.readLine());
                input.close();
                output.close();
                clientSocket.close();
            } catch (Exception ex) {
                delegateErrorDialog(ex.getMessage());
                System.out.println(ex.getMessage());
            }
        } else outputTextView.setText((String)"Matrikelnumber was too short, please try again!");
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
