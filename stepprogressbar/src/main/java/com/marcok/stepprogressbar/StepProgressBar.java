package com.marcok.stepprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephen on 15-07-10.
 */
public class StepProgressBar extends View {

    private int inactiveColor;
    private int activeColor;
    private Drawable inactiveDrawable;
    private Drawable activeDrawable;

    private float dotSize;
    private float dotSpacing;

    private int maxNumDots;
    private int currentlyActiveDot;
    private boolean cumulativeDots;

    private Paint mPaint;

    private static final int MIN_DOTS = -1;
    private static final String OUT_OF_BOUNDS_ERROR = "Progress bar out of bounds!";

    public StepProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StepProgressBar, 0, 0);
        try {
            inactiveColor = ta.getInt(R.styleable.StepProgressBar_inactiveDotColor, 0);
            activeColor = ta.getInt(R.styleable.StepProgressBar_activeDotColor, 0);
            inactiveDrawable = ta.getDrawable(R.styleable.StepProgressBar_inactiveDotIcon);
            activeDrawable = ta.getDrawable(R.styleable.StepProgressBar_activeDotIcon);
            dotSize = ta.getDimensionPixelSize(R.styleable.StepProgressBar_dotSize, 15);
            dotSpacing = ta.getDimensionPixelSize(R.styleable.StepProgressBar_spacing, 15);
            maxNumDots = ta.getInt(R.styleable.StepProgressBar_numberDots, 5);
            currentlyActiveDot = ta.getInt(R.styleable.StepProgressBar_activeDotIndex, 0);
            cumulativeDots = ta.getBoolean(R.styleable.StepProgressBar_cumulativeDots, false);

        } finally {
            ta.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = (int)dotSize + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(widthMeasureSpec, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Centering the dots in the middle of the canvas
        float singleDotSize = dotSpacing+dotSize;
        float combinedDotSize = singleDotSize * maxNumDots - dotSpacing;
        int startingX = (int)((canvas.getWidth() - combinedDotSize)/2);

        for (int i = 0; i < maxNumDots; i++){
            int x = (int)(startingX + i*singleDotSize);
            if ((cumulativeDots && i < currentlyActiveDot) || i == currentlyActiveDot){
                if (activeDrawable != null){
                    activeDrawable.setBounds(x,getPaddingTop(),(int)(x+dotSize), getPaddingTop()+(int)dotSize);
                    activeDrawable.draw(canvas);
                } else {
                    mPaint.setColor(activeColor);
                    canvas.drawCircle(x + dotSize/2, getPaddingTop() + dotSize / 2, dotSize / 2, mPaint);
                }
            } else {
                if (inactiveDrawable != null){
                    inactiveDrawable.setBounds(x,getPaddingTop(),(int)(x+dotSize), getPaddingTop()+(int)dotSize);
                    inactiveDrawable.draw(canvas);
                } else {
                    mPaint.setColor(inactiveColor);
                    canvas.drawCircle(x + dotSize/2, getPaddingTop() + dotSize / 2, dotSize / 2, mPaint);
                }
            }
        }
    }

    public void next(){
        if (currentlyActiveDot == maxNumDots - 1){
            throw new IndexOutOfBoundsException(OUT_OF_BOUNDS_ERROR);
        }
        currentlyActiveDot++;
        invalidate();
    }
    public void previous(){
        if (currentlyActiveDot == MIN_DOTS){
            throw new IndexOutOfBoundsException(OUT_OF_BOUNDS_ERROR);
        }
        currentlyActiveDot--;
        invalidate();
    }

    public void setCurrentProgressDot(int i){
        if (i >= maxNumDots || i < MIN_DOTS){
            throw new IndexOutOfBoundsException(OUT_OF_BOUNDS_ERROR);
        }
        currentlyActiveDot = i;
        invalidate();
    }

    public int getCurrentProgressDot(){
        return currentlyActiveDot;
    }

    public void setNumDots(int i) {
        if (i < 0){
            throw new IndexOutOfBoundsException(OUT_OF_BOUNDS_ERROR);
        }
        if (i <= currentlyActiveDot) {
            currentlyActiveDot = -1;
        }
        maxNumDots = i;
        invalidate();
    }

    public int getNumDots(){
        return maxNumDots;
    }

    public void setCumulativeDots(boolean c){
        cumulativeDots = c;
        invalidate();
    }

    public void setInactiveColor(int color){
        inactiveColor = color;
        invalidate();
    }

    public void setActiveColor(int color){
        activeColor = color;
        invalidate();
    }

    public void setInactiveDrawable(Drawable d){
        inactiveDrawable = d;
        invalidate();
    }

    public void setActiveDrawable(Drawable d){
        activeDrawable = d;
        invalidate();
    }
}
