package com.gavriden.sulzerservice;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Torque90111Fragment extends DialogFragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.eu_screw);
        View v = inflater.inflate(R.layout.torque90111_layout, null);

        // make dialog itself transparent
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 192, 192, 192)));
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // remove background dim
        //getDialog().getWindow().setDimAmount(0);

        return v;

    }

}
