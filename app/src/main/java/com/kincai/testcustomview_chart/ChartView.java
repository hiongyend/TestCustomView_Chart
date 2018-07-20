package com.kincai.testcustomview_chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by KINCAI
 * <p>
 * Desc TODO
 * <p>
 * Date 2018-7-19 15:29
 */
public class ChartView extends View {
    private final String TAG = this.getClass().getSimpleName();

    // 图表框的画笔
    private Paint mChartPaint;
    private int mChartColor;
    private float mChartStrokeWidth;
    // 曲线画笔
    private Paint mLinePaint;
    private int mLineColor;
    private float mLineStrokeWidth;
    // 字体画笔
    private Paint mFontPaint;
    private int mFontColor;


    // 纵轴几等份
    private int mVerticalAxisCount = 4 ;
    // 横轴几等份
    private int mHorizontalAxisCount = 1;

    private float mFontWidth;
    private float mFontHeight;

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        initChartPaint();
        initLinePaint();
        initFontPaint();
    }

    private void initChartPaint() {
        if(mChartPaint == null) {
            mChartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        mChartColor = Color.parseColor("#EEEEEE");
        mChartStrokeWidth = 3f;
        mChartPaint.setColor(mChartColor);
        mChartPaint.setStrokeWidth(mChartStrokeWidth);
        mChartPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void initLinePaint() {
        if(mLinePaint == null) {
            mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineStrokeWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    private void initFontPaint() {
        if(mFontPaint == null) {
            mFontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        mFontPaint.setColor(mFontColor);
        mFontPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 先画图表框 包括字
        float height = getHeight() - mChartStrokeWidth;
        Path chartPath = new Path();
        // 横线从下往上画
        float value = height / mVerticalAxisCount;
        for (int i = 0; i < mVerticalAxisCount + 1; i++) {
            float currentHeight = height - i * value + mChartStrokeWidth / 2;
            chartPath.moveTo(0, currentHeight);
            chartPath.lineTo(getWidth(), currentHeight);
            chartPath.close();
            canvas.drawPath(chartPath, mChartPaint);
        }

    }
}
