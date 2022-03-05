package com.gavriden.sulzerservice;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

public class Torque90162Fragment extends DialogFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.sf_screw);
        View v = inflater.inflate(R.layout.torque90162_layout, null);

        // make dialog itself transparent
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 192, 192, 192)));
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // remove background dim
        //getDialog().getWindow().setDimAmount(0);

        return v;

    }

}
