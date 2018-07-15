# 多行文本末尾添加图片排版问题解决
最近在项目中需要在某个多行英文文本末尾增加一个图片，尝试了很多方法，最后用了一个比较Tricky的方法解决了，当然这种方法不一定是最好最优的解决办法，记录一下。
## 问题
如果直接使用drawableRight或者drawableEnd来将图片放置到文本末尾，结果会是这样：

![](img/drawable_padding.png)

图片会在TextView右边竖直方向的中间位置显示，而不是我们期望的在最后一行位置显示。
这时我们可以尝试使用ImageSpan来将图片放置在最后一行:

        final TextView text = findViewById(R.id.text);
        final String string = "dkffkdjkfjdkfjkdjfdjfkjdkfjkdjkdjfkjdkjk";
        //创建一个SpannableString对象，后面增加空格是预留给图片的
        SpannableString spannableString = new SpannableString(string + "  ");
        //创建图片的Drawable对象
        Drawable drawable = getResources().getDrawable(R.mipmap.copy);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //创建图片的ImageSpan对象
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        //设置ImageSpan，将其位置设置在spannableString最后面，起始位置start=spannableString.length() - 1，结束位置为end=spannableString.length(),
        // SPAN_INCLUSIVE_EXCLUSIVE表示包含start不包含end
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text.setText(spannableString);

效果如下：

![](img/image_span.png)

图片确实是放在了最后面，但是英文文本的显示出现了凌乱。
## 原因
实际上最后的效果是TextView绘制出来的，原因当然是TextView绘制咯。TextView的Span绘制主要是使用DynamicLayout里面的方法来计算行数，
调用其draw方法（DynamicLayout父类Layout实现）完成绘制。具体实现细节这里就不在阐述，有兴趣的朋友可以自行研究下。

## 解决
这里使用了比较讨巧的一种方式，这里主要是文本换行并没有按照我们期望的样子进行，而是换行之后后面还留了一大半空白。后面发现如果字符串中包含了空格的话，
就会自动以空格的位置进行换行。所以解决思路就是按照TextView的宽度，计算每一行能够容纳的字符个数，在每一行的最后插入一个空格，就能解决换行凌乱了。
具体实现如下：

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

这里使用的是post方法延后处理，当然也可以使用ViewTreeObserver监听布局完成再进行处理，最后的效果，完美:

![](img/space_insert.png)