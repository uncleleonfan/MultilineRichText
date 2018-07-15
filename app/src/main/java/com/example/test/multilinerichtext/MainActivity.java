package com.example.test.multilinerichtext;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = findViewById(R.id.text);
        final String string = "dkffkdjkfjdkfjkdjfdjfkjdkfjkdjkdjfkjdkjk";

        text.setText(string);

/*        text.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = text.getLayout().getLineCount();
                float lineWidth = text.getLayout().getLineWidth(0);
                int lineEnd = text.getLayout().getLineEnd(0);
                float widthPerChar = lineWidth / (lineEnd + 1);
                int numberPerLine = (int) Math.floor(text.getWidth() / widthPerChar);
                StringBuilder stringBuilder = new StringBuilder(string).insert(numberPerLine - 1, " ");
                Log.d(TAG, stringBuilder.toString());
                SpannableString spannableString = new SpannableString(stringBuilder.toString() + "  ");
                Drawable drawable = getResources().getDrawable(R.mipmap.copy);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                text.setText(spannableString);
            }

        });*/
//        text.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                text.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                int lineCount = text.getLayout().getLineCount();
//                float lineWidth = text.getLayout().getLineWidth(0);
//                int lineEnd = text.getLayout().getLineEnd(0);
//                float widthPerChar = lineWidth / (lineEnd + 1);
//                int numberPerLine = (int) Math.floor(text.getWidth() / widthPerChar);
//                StringBuilder stringBuilder = new StringBuilder(string).insert(numberPerLine - 1, " ");
//                Log.d(TAG, stringBuilder.toString());
//                SpannableString spannableString = new SpannableString(stringBuilder.toString() + "  ");
//                Drawable drawable = getResources().getDrawable(R.mipmap.copy);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//                spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
////                    int lineEnd = text.getLayout().getLineEnd(i);
////                for (int i = 0; i < lineCount; i++) {
////                    int lineStart = text.getLayout().getLineStart(i);
////                    int lineEnd = text.getLayout().getLineEnd(i);
////                    float lineWidth = text.getLayout().getLineWidth(i);
////                    String lineString = string.substring(lineStart, lineEnd);
////                    Log.d(TAG, "onGlobalLayout: " + lineStart + " " + lineEnd + " " + lineWidth + " " + lineString);
////                }
//                text.setText(spannableString);
//            }
//
//        });
    }
}
