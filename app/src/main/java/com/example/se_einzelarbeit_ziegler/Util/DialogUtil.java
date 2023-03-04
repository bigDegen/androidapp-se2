// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler.Util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.example.se_einzelarbeit_ziegler.MainActivity;

/**
 * Utils for Dialog based things
 */
public class DialogUtil {
    /**
     * Create a dialog with the given parameters
     *
     * @param title   dialog title
     * @param msg     dialog output message
     * @param context acivity
     * @return the finished dialog
     */
    static public AlertDialog createDialog(String title, String msg, MainActivity context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //mandatory method, but useless
                    }
                }).create();

        return dialog;
    }
}
