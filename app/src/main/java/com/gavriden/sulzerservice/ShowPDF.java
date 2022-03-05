package com.gavriden.sulzerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ShowPDF extends Activity {

    PDFView pdfView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_p_d_f);

        Bundle bundle = getIntent().getExtras();
        String filePath = bundle.getString("filePath");

        pdfView = findViewById(R.id.pdfView);
        file = new File(filePath);

        pdfView.fromFile(file).load();

    }
}
