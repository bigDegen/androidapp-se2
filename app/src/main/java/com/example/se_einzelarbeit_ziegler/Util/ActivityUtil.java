// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler.Util;

import android.view.View;
import com.example.se_einzelarbeit_ziegler.MainActivity;

/**
 * Utils for the Acivity
 */
public class ActivityUtil {

    /**
     * Gets the view by the R.id
     * @param context activity
     * @param id the R.id
     * @return the view of the id
     */
    public static View getView(MainActivity context, int id) {
        return context.findViewById(id);
    }
}
