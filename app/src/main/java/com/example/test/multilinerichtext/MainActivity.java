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
/*        //创建一个SpannableString对象，后面增加空格是预留给图片的
        SpannableString spannableString = new SpannableString(string + "  ");
        //创建图片的Drawable对象
        Drawable drawable = getResources().getDrawable(R.mipmap.copy);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //创建图片的ImageSpan对象
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        //设置ImageSpan，将其位置设置在spannableString最后面，起始位置start=spannableString.length() - 1，结束位置为end=spannableString.length(),
        // SPAN_INCLUSIVE_EXCLUSIVE表示包含start不包含end
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text.setText(spannableString);*/


        //先设置原始文本
        text.setText(string);
        //使用post方法，在TextView完成绘制流程后在消息队列中被调用
        text.post(new Runnable() {
            @Override
            public void run() {
                //获取第一行的宽度
                float lineWidth = text.getLayout().getLineWidth(0);
                //获取第一行最后一个字符的下标
                int lineEnd = text.getLayout().getLineEnd(0);
                //计算每个字符占的宽度
                float widthPerChar = lineWidth / (lineEnd + 1);
                //计算TextView一行能够放下多少个字符
                int numberPerLine = (int) Math.floor(text.getWidth() / widthPerChar);
                //在原始字符串中插入一个空格，插入的位置为numberPerLine - 1
                StringBuilder stringBuilder = new StringBuilder(string).insert(numberPerLine - 1, " ");

                //SpannableString的构建
                SpannableString spannableString = new SpannableString(stringBuilder.toString() + "  ");
                Drawable drawable = getResources().getDrawable(R.mipmap.copy);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                text.setText(spannableString);
            }

        });
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
