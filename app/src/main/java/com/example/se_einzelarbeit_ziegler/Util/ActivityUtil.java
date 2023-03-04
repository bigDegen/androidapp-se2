package com.example.se_einzelarbeit_ziegler.Util;

import android.view.View;
import com.example.se_einzelarbeit_ziegler.MainActivity;

public class ActivityUtil {

    public static View getView(MainActivity context, int id){
        return context.findViewById(id);
    }
}
