package ru.mail.aslanisl.testsvgpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

public class DrawView extends android.support.v7.widget.AppCompatImageView {

    Paint paint;
    Path path;
    Canvas canvas;

    private String[] arrayStringNumbers;
    private String[] arrayStringLetters;

    public DrawView(Context context) {
        super(context);

        paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawPath(path, paint);
    }

    public void onClick(int color) {
        paint.setColor(color);
        this.invalidate();
    }

    public void parsing(String svgPath) {

        arrayStringLetters = new String[svgPath.length()];
        arrayStringNumbers = new String[svgPath.length()];

        arrayStringNumbers = svgPath.split("[a-zA-Z]+");
        arrayStringLetters = svgPath.split("[^a-zA-Z]+");

        float x = Float.parseFloat(arrayStringNumbers[1].split(",")[0]);
        float y = Float.parseFloat(arrayStringNumbers[1].split(",")[1]);
        float current = 0;
        String[] arrayCurrentString = new String[4];

        path.reset();
        path.moveTo(x, y);

        for (int i = 1; i < arrayStringLetters.length; i++) {

            if (i + 1 < arrayStringLetters.length) {

                try {
                    if (arrayStringNumbers[i + 1].contains(",")) {
                        arrayCurrentString = arrayStringNumbers[i + 1].split(",");
                    } else current = Float.parseFloat(arrayStringNumbers[i + 1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            switch (arrayStringLetters[i]) {
                case "h":
                    x += current;
                    path.lineTo(x, y);
                    break;
                case "H":
                    x = current;
                    path.lineTo(current, y);
                    break;
                case "v":
                    y += current;
                    path.lineTo(x, y);
                    break;
                case "V":
                    y = current;
                    path.lineTo(x, current);
                    break;
                case "l":
                    x += Float.parseFloat(arrayCurrentString[0]);
                    y += Float.parseFloat(arrayCurrentString[1]);
                    path.lineTo(x, y);
                case "L":
                    x = Float.parseFloat(arrayCurrentString[0]);
                    y = Float.parseFloat(arrayCurrentString[1]);
                    path.lineTo(x, y);
                case "z":
                case "Z":
                    path.close();
                    break;
            }
            Log.d("TAG", x + " " + y);
        }
        path.offset(500, 100);

    }
}
