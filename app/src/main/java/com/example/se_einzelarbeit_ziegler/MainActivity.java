package com.example.se_einzelarbeit_ziegler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Console;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView output;
    Button btnFetchNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //Initalize OutputField
        output = (TextView) findViewById(R.id.txtOutput);

        //Intialization Button
        btnFetchNumber = (Button) findViewById(R.id.btnFetchNumber);

        btnFetchNumber.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        EditText inputMatNo = (EditText) findViewById(R.id.matNo);
        try {
            int matNo = Integer.parseInt(inputMatNo.getText().toString());
        }catch (Exception e){
            new AlertDialog.Builder(this)
                    .setTitle("Wrong Input")
                    .setMessage("Dear Sir, thy input is not a Number!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            inputMatNo.setText("Wrong input");
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


    }
}