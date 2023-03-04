// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.se_einzelarbeit_ziegler.Network.SendMessage;
import com.example.se_einzelarbeit_ziegler.Util.ActivityUtil;
import com.example.se_einzelarbeit_ziegler.Util.DialogUtil;
import com.example.se_einzelarbeit_ziegler.Util.MathUtil;

/**
 * This class servers as the code behind the main_act View
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Server Information host+port
    public final static String HOST = "se2-isys.aau.at";
    public final static int PORT = 53212;
    //Output Text field
    TextView output;
    //GET Information Button
    Button btnFetchNumber, btnCalcDivisor;
    //Input field
    EditText inputMatNo;

    /**
     * Initialization of the View and its components
     * @param savedInstanceState Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //Initalize OutputField
        output = (TextView) ActivityUtil.getView(this, R.id.txtOutput);

        //Intialization Buttons
        btnFetchNumber = (Button) ActivityUtil.getView(this, R.id.btnFetchNumber);
        btnFetchNumber.setOnClickListener(MainActivity.this);

        btnCalcDivisor = (Button) ActivityUtil.getView(this, R.id.btnCalcDivisor);
        btnCalcDivisor.setOnClickListener(MainActivity.this);

        //Initialize Matrikelnumber input field
        inputMatNo = (EditText) ActivityUtil.getView(this, R.id.matNo);

    }

    /**
     * This function serves as the Event handler for Button clicks
     * @param v the corrosponding view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnFetchNumber:
                onBTNgetinformationClick();
                break;
            case R.id.btnCalcDivisor:
                onBTNcalcdivisorClick();
                break;
            default:
                break;

        }
    }

    /**
     * ***************************************************************************************************
     * This function gets invoked when the Get common divisor button is pressed
     * It goes throw the matrikelnumber and tries to calculate a common divisor,
     * two numbers get passed into the MathUtil.class - getGcd funtion
     * ***************************************************************************************************
     * (i could not extract from the assignement if the order is of relevance so i did not start the second
     * for loop at position i but rather skipped the same index numbers)
     */
    private void onBTNcalcdivisorClick() {
        output.setText("calculating common divisor...");
        int firstnumber=0, secondnumber=0, divisor=1;
        char[] matrikelnumberCharArr = inputMatNo.getText().toString().toCharArray();
        //go through the matrikelnumber and find a common divisor by calculating gcd
        for (int i =0; i<matrikelnumberCharArr.length; i++){
            if (divisor>1)break;
            firstnumber = Character.getNumericValue(matrikelnumberCharArr[i]);
            for (int k = 0; k<matrikelnumberCharArr.length;k++){
                if (divisor>1)break;
                if (i!=k) {
                    secondnumber = Character.getNumericValue(matrikelnumberCharArr[k]);
                    divisor = MathUtil.getGcd(firstnumber, secondnumber);
                }
            }
        }
        output.setText("The number "+firstnumber+" and "+secondnumber+" in the Matrikelnumber "+inputMatNo.getText().toString()+", can be divided by "+divisor+"!");
    }

    /**
     * This function gets invoked when the Get Information button is pressed
     * It validates the Matrikelnumber and sends it to the SendMessage class which handles the TCP
     * connection aswell as the sending of the message
     */
    private void onBTNgetinformationClick() {
        boolean correctInput = true;
        if (inputMatNo.getText().toString().length() < 7) {
            correctInput = false;
        }

        SendMessage messageSender = new SendMessage(this, correctInput, inputMatNo.getText().toString());

        // Sending message to Server, HOST and PORT are defined above
        try {
            new Thread(messageSender).start();
        } catch (Exception ex) {
            displayError(ex.getMessage());
        }
    }

    /**
     * Function for showing dialog fields for error messages
     * The error message gets passed in the DialogUtil class
     * @param errorMsg an error message from an exception
     */
    public void displayError(String errorMsg) {
        DialogUtil.createDialog("Error while fetching", errorMsg, this).show();
    }
}