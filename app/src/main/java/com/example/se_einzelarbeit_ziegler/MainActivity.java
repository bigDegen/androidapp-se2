// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.se_einzelarbeit_ziegler.Network.SendMessage;
import com.example.se_einzelarbeit_ziegler.Util.ActivityUtil;
import com.example.se_einzelarbeit_ziegler.Util.DialogUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Server Information
    public final static String HOST = "se2-isys.aau.at";
    public final static int PORT = 53212;
    //Output Text field
    TextView output;
    //GET Information Button
    Button btnFetchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //Initalize OutputField
        output = (TextView) ActivityUtil.getView(this,R.id.txtOutput);

        //Intialization Button
        btnFetchNumber = (Button) ActivityUtil.getView(this, R.id.btnFetchNumber);
        btnFetchNumber.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {
        EditText inputMatNo = (EditText) ActivityUtil.getView(this, R.id.matNo);

        boolean correctInput = true;
        if (inputMatNo.getText().toString().length()<7) {
            DialogUtil.createDialog("Wrong input", "Thy input should be atleast 7 digits!", this).show();
            correctInput=false;
        }

        SendMessage messageSender = new SendMessage(this, correctInput);

        // Sending message to Server, HOST and PORT are defined above
        new Thread(messageSender).start();
    }

    public void displayError(String errorMsg){
        DialogUtil.createDialog("Error while fetching", errorMsg, this).show();
    }
}