package com.gavriden.sulzerservice;

import androidx.fragment.app.DialogFragment;
import android.content.DialogInterface;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class TorqueDialogFragment extends DialogFragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.impeller_screw);
        View v = inflater.inflate(R.layout.torque_layout, null);

        // make dialog itself transparent
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(50, 192, 192, 192)));
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // remove background dim
        //getDialog().getWindow().setDimAmount(0);

        return v;

    }

}