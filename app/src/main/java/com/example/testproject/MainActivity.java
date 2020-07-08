package com.example.testproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import me.jfenn.colorpickerdialog.dialogs.ColorPickerDialog;
import me.jfenn.colorpickerdialog.interfaces.OnColorPickedListener;

public class MainActivity extends AppCompatActivity {
    private PhotoEditor mPhotoEditor;
    private int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentColor = Color.BLACK;
        getSupportActionBar().setTitle("Paint");
        PhotoEditorView mPhotoEditorView = findViewById(R.id.photoEditorView);
        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .build();
        setButtonListeners();

    }

    private void setButtonListeners() {
        Button drawButton = findViewById(R.id.drawButton);
        Button eraseButton = findViewById(R.id.eraseButton);
        final Button colorButton = findViewById(R.id.colorPicker);
        colorButton.setBackgroundColor(currentColor);
        mPhotoEditor.setBrushSize(10);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoEditor.setBrushDrawingMode(true);
            }
        });
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoEditor.brushEraser();
            }
        });
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorPickerDialog()
                        .withColor(currentColor) // the default / initial color
                        .withListener(new OnColorPickedListener<ColorPickerDialog>() {
                            @Override
                            public void onColorPicked(@Nullable ColorPickerDialog dialog, int color) {
                                // a color has been picked; use it
                                currentColor = color;
                                mPhotoEditor.setBrushColor(color);
                                colorButton.setBackgroundColor(color);
                            }
                        })
                        .show(getSupportFragmentManager(), "colorPicker");
            }
        });
    }
}
