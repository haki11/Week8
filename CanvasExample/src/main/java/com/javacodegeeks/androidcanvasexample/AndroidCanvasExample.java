package com.javacodegeeks.androidcanvasexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AndroidCanvasExample extends Activity {

    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasView = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View view) {
        canvasView.wipeCanvas();
    }
}