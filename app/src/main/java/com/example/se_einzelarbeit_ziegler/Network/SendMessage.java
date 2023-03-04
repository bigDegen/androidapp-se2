package com.example.se_einzelarbeit_ziegler.Network;

import android.widget.EditText;
import android.widget.TextView;
import com.example.se_einzelarbeit_ziegler.MainActivity;
import com.example.se_einzelarbeit_ziegler.R;
import com.example.se_einzelarbeit_ziegler.Util.ActivityUtil;
import com.example.se_einzelarbeit_ziegler.Util.DialogUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendMessage implements Runnable {
    private MainActivity context;
    private boolean correctInput;

    public SendMessage(MainActivity context, boolean correctInput){
        this.context = context;
        this.correctInput = correctInput;
    }

    @Override
    public void run() {
        EditText inputMatNo = (EditText) ActivityUtil.getView(context, R.id.matNo);
        TextView outputTextView = (TextView) ActivityUtil.getView(context, R.id.txtOutput);

        String matNo = inputMatNo.getText().toString();

        if (correctInput){
                Socket clientSocket = new Socket();
                try {
                    clientSocket = new Socket(context.HOST,context.PORT);

                    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    output.writeBytes(matNo+"\n");

                    outputTextView.setText(input.readLine());
                    input.close();
                    output.close();
                    clientSocket.close();
                }catch (Exception ex){
                    delegateErrorDialog(ex.getMessage().toString());
                    System.out.println(ex.toString());
                }
    }}

    private void delegateErrorDialog(String errorMsg){
        context.displayError(errorMsg);
    }
}
