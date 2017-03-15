package ru.mail.aslanisl.testsvgpath;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String PATH = "M58.375,259.344v-76h526v-66.667h114.667v66.667h194v76h9.333v98.667h-9.333v72H58.375v-72H45.708v-98.667H58.375z";

    ImageView mImageView;
    TextView mTextView;

    DrawView mDrawView;
    Bitmap mBitmap;
    Canvas mCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mTextView = (TextView) findViewById(R.id.textView);

        mDrawView = new DrawView(getApplicationContext());
        mDrawView.parsing(PATH);

        mBitmap = Bitmap.createBitmap(2048/*width*/, 2048/*height*/, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mDrawView.draw(mCanvas);

        mImageView.setImageBitmap(mBitmap);
        mImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView:
                mDrawView.onClick(Color.GREEN);
                mDrawView.draw(mCanvas);
                mImageView.setImageBitmap(mBitmap);
                break;
        }

    }
}
