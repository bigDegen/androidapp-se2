package com.example.se_einzelarbeit_ziegler.Util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.example.se_einzelarbeit_ziegler.MainActivity;

public class DialogUtil {
    static public AlertDialog createDialog(String title, String msg, MainActivity context){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //für später eventuell
                    }
                }).create();

        return dialog;
    }
}
