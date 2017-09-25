package com.ppandroid.app.widget.linechart;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.ppandroid.app.R;
import com.ppandroid.app.widget.graphical.common.SysinfoHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 自定义折线图 代码减减变得恶心，别问我为什么跑得起来，我也不知道
 * You may think you know what the following code does. But you don't. Trust me
 * Fiddle with it, and you'll spend many a sleepless night cursing the moment
 * you thought you'd be clever enough to "optimize" the code below. Now close
 * this file and go play with something else. God bless you!!! The God's name is
 * YeQinFu
 */
public class ChartView extends View {
	//xy坐标轴颜色
	private int						xylinecolor	= 0xffe2e2e2;
	//xy坐标轴宽度
	private int						xylinewidth	= dpToPx(1);
	//xy坐标轴文字颜色
	private int						xytextcolor	= 0xff7e7e7e;
	//xy坐标轴文字大小
	private int						xytextsize	= spToPx(12);
	//折线图中折线的颜色
	private int						linecolor	= 0xff02bbb7;
	//x轴各个坐标点水平间距
	private int						interval	= dpToPx(50);
	//背景颜色
	private int						bgcolor		= 0xffffffff;
	//是否在ACTION_UP时，根据速度进行自滑动，没有要求，建议关闭，过于占用GPU
	private boolean					isScroll	= false;
	//绘制XY轴坐标对应的画笔
	private Paint					xyPaint;
	//绘制XY轴的文本对应的画笔
	private Paint					xyTextPaint;
	//画折线对应的画笔
	private Paint					linePaint;
	//画折线对应的画笔
	private Paint					blackPaint;
	//画折线对应的画笔
	private Paint					valuePaint;
	private int						width;
	private int						height;
	//x轴的原点坐标
	private int						xOri;
	//y轴的原点坐标
	private int						yOri;
	//第一个点X的坐标
	private float					xInit;
	//第一个点对应的最大Y坐标
	private float					maxXInit;
	//第一个点对应的最小X坐标
	private float					minXInit;
	//x轴坐标对应的数据
	private List<String>			xValue		= new ArrayList<>();
	//y轴坐标对应的数据
	private List<Double>			yValue		= new ArrayList<>();
	//折线对应的数据
	private Map<String, Double>	value		= new LinkedHashMap<>();
	//折线对应的数据2
	private Map<String, Double>	value2		= new LinkedHashMap<>();
	//点击的点对应的X轴的第几个点，默认1
	private int						selectIndex	= -1;
	private int						selectIndex2	= -1;
    private int selectType=0;//0，表示selectIndex 有效，1表示selectIndex2有效
	//X轴刻度文本对应的最大矩形，为了选中时，在x轴文本画的框框大小一致
	private Rect					xValueRect;
	//速度检测器
	private VelocityTracker			velocityTracker;

	public ChartView(Context context) {
		this(context, null);
        //禁用硬件加速
        disableHardwareAccelerated();
	}

	public ChartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
        //禁用硬件加速
        disableHardwareAccelerated();
	}

	public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
        //禁用硬件加速
        disableHardwareAccelerated();
		init(context, attrs, defStyleAttr);
		initPaint();
	}
    /**
     * 禁用硬件加速. 原因:android自3.0引入了硬件加速，即使用GPU进行绘图,但它并不能完善的支持所有的绘图，
     * 通常表现为内容(如Rect或Path)不可见，异常或渲染错误。所以类了保证图表的正常显示，强制禁用掉.
     */
    protected void disableHardwareAccelerated() {
        //View.isHardwareAccelerated()
        //Canvas.isHardwareAccelerated()

        if (SysinfoHelper.getInstance().supportHardwareAccelerated()) {
            //是否开启了硬件加速,如开启将其禁掉
            if (!isHardwareAccelerated()) {
                //setLayerType(View.LAYER_TYPE_NONE,null);  //LAYER_TYPE_SOFTWARE
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }
    }

	/**
	 * 初始化畫筆
	 */
	private void initPaint() {
		xyPaint = new Paint();
		xyPaint.setAntiAlias(true);
		xyPaint.setStrokeWidth(xylinewidth);
		xyPaint.setStrokeCap(Paint.Cap.ROUND);
		xyPaint.setColor(xylinecolor);

		xyTextPaint = new Paint();
		xyTextPaint.setAntiAlias(true);
		xyTextPaint.setTextSize(xytextsize);
		xyTextPaint.setStrokeCap(Paint.Cap.ROUND);
		xyTextPaint.setColor(xytextcolor);
		xyTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);

		linePaint = new Paint();
		linePaint.setAntiAlias(true);
		linePaint.setStrokeWidth(xylinewidth);
		linePaint.setStrokeCap(Paint.Cap.ROUND);
		linePaint.setColor(linecolor);
		linePaint.setStyle(Paint.Style.STROKE);


        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setStrokeWidth(dpToPx(0.5f));
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL);


        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setStrokeWidth(xylinewidth);
        valuePaint.setStrokeCap(Paint.Cap.ROUND);
        valuePaint.setColor(Color.parseColor("#883ACDFF"));
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);


	}

	/**
	 * 初始化
	 *
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	private void init(Context context, AttributeSet attrs, int defStyleAttr) {
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.chartView, defStyleAttr, 0);
		int count = array.getIndexCount();
		for (int i = 0; i < count; i++) {
			int attr = array.getIndex(i);
			switch (attr) {
			case R.styleable.chartView_xylinecolor://xy坐标轴颜色
				xylinecolor = array.getColor(attr, xylinecolor);
				break;
			case R.styleable.chartView_xylinewidth://xy坐标轴宽度
				xylinewidth = (int) array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, xylinewidth, getResources().getDisplayMetrics()));
				break;
			case R.styleable.chartView_xytextcolor://xy坐标轴文字颜色
				xytextcolor = array.getColor(attr, xytextcolor);
				break;
			case R.styleable.chartView_xytextsize://xy坐标轴文字大小
				xytextsize = (int) array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, xytextsize, getResources().getDisplayMetrics()));
				break;
			case R.styleable.chartView_linecolor://折线图中折线的颜色
				linecolor = array.getColor(attr, linecolor);
				break;
			case R.styleable.chartView_interval://x轴各个坐标点水平间距
				interval = (int) array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, interval, getResources().getDisplayMetrics()));
				break;
			case R.styleable.chartView_bgcolor: //背景颜色
				bgcolor = array.getColor(attr, bgcolor);
				break;
			case R.styleable.chartView_isScroll://是否在ACTION_UP时，根据速度进行自滑动
				isScroll = array.getBoolean(attr, isScroll);
				break;
			}
		}
		array.recycle();

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		if (changed) {
			//这里需要确定几个基本点，只有确定了xy轴原点坐标，第一个点的X坐标值及其最大最小值
			width = getWidth();
			height = getHeight();
			//Y轴文本最大宽度
			float textYWdith = getTextBounds("000", xyTextPaint).width();
			for (int i = 0; i < yValue.size(); i++) {//求取y轴文本最大的宽度
				float temp = getTextBounds(yValue.get(i) + "", xyTextPaint).width();
				if (temp > textYWdith)
					textYWdith = temp;
			}
			int dp2 = dpToPx(2);
			int dp3 = dpToPx(3);
			xOri = (int) (dp2 + textYWdith + dp2 + xylinewidth);//dp2是y轴文本距离左边，以及距离y轴的距离
			//            //X轴文本最大高度
			xValueRect = getTextBounds("000", xyTextPaint);
			float textXHeight = xValueRect.height();
			for (int i = 0; i < xValue.size(); i++) {//求取x轴文本最大的高度
				Rect rect = getTextBounds(xValue.get(i) + "", xyTextPaint);
				if (rect.height() > textXHeight)
					textXHeight = rect.height();
				if (rect.width() > xValueRect.width())
					xValueRect = rect;
			}
			yOri = (int) (height - dp2 - textXHeight - dp3 - xylinewidth);//dp3是x轴文本距离底边，dp2是x轴文本距离x轴的距离
			xInit =  xOri+dpToPx(5);
			minXInit = width - (width - xOri) * 0.1f - interval * (xValue.size() - 1);//减去0.1f是因为最后一个X周刻度距离右边的长度为X轴可见长度的10%
			maxXInit = xInit;
		}
		super.onLayout(changed, left, top, right, bottom);
	}
	/**是否显示折线图1*/
	private boolean isShowValue1=true;
    /**是否显示折线图2*/
	private boolean isShowValue2=true;


    public boolean isShowValue1() {
        return isShowValue1;
    }

    public void setShowValue1(boolean showValue1) {
        isShowValue1 = showValue1;
        invalidate();
    }

    public boolean isShowValue2() {
        return isShowValue2;
    }

    public void setShowValue2(boolean showValue2) {
        isShowValue2 = showValue2;
        invalidate();
    }

    @Override
	protected void onDraw(Canvas canvas) {
		//x轴刻度自适应
		interval = (getWidth()-xOri) / (xValue.size() + 1);
		canvas.drawColor(bgcolor);
		drawXY(canvas);
        if (isShowValue1){
            drawBrokenLineAndPoint(canvas);
        }
        if (isShowValue2){
            drawBrokenLineAndPoint2(canvas);
        }
        drawAvg(canvas);
	}

    private void drawAvg(Canvas canvas) {
        if (isShowAvg){//显示平均线
            Paint  mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(getResources().getColor(R.color.color_02));
            mPaint.setStrokeWidth(3);
            mPaint.setPathEffect(new DashPathEffect(new float[] {5, 5}, 0));

            if (avgValue!=0){
                float startx = xInit + interval * 0;
                float starty = (float) (yOri - yOri * (1 - 0.1f) * avgValue / yValue.get(yValue.size() - 1));
                float endx=xInit+interval*xValue.size();
                drawFloatTextBox(canvas,endx-interval,starty,avgText);
                canvas.drawLine(startx, starty, endx,starty, mPaint);
            }
        }
    }


    /**是否显示平均值*/
    private boolean isShowAvg=true;
    private double avgValue=0;
    private String avgText="";

    public String getAvgText() {
        return avgText;
    }

    public void setAvgText(String avgText) {
        this.avgText = avgText;
    }

    public double getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(double avgValue) {
        this.avgValue = avgValue;
    }

    public boolean isShowAvg() {
        return isShowAvg;
    }

    public void setShowAvg(boolean showAvg) {
        isShowAvg = showAvg;
    }


    /**
	 * 绘制折线和折线交点处对应的点
	 *
	 * @param canvas
	 */
	private void drawBrokenLineAndPoint(Canvas canvas) {
		if (xValue.size() <= 0)
			return;
		//重新开一个图层
		int layerId = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
		drawBrokenLine(canvas);
		drawBrokenPoint(canvas);

		// 将折线超出x轴坐标的部分截取掉
		linePaint.setStyle(Paint.Style.FILL);
		linePaint.setColor(bgcolor);
		linePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		RectF rectF = new RectF(0, 0, xOri, height);
		canvas.drawRect(rectF, linePaint);
		linePaint.setXfermode(null);
		//保存图层
		canvas.restoreToCount(layerId);
	}
    /**
     * 绘制折线和折线交点处对应的点2
     *
     * @param canvas
     */
    private void drawBrokenLineAndPoint2(Canvas canvas) {
        if (xValue.size() <= 0)
            return;
        //重新开一个图层
        int layerId = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        drawBrokenLine2(canvas);
        drawBrokenPoint2(canvas);

        // 将折线超出x轴坐标的部分截取掉
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(bgcolor);
        linePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        RectF rectF = new RectF(0, 0, xOri, height);
        canvas.drawRect(rectF, linePaint);
        linePaint.setXfermode(null);
        //保存图层
        canvas.restoreToCount(layerId);
    }

	/**
	 * 绘制折线对应的点
	 *
	 * @param canvas
	 */
	private void drawBrokenPoint(Canvas canvas) {
		float dp2 = dpToPx(2);
		float dp4 = dpToPx(4);
		float dp7 = dpToPx(7);
		//绘制节点对应的原点
		for (int i = 0; i < value.size(); i++) {
			float x = xInit + interval * i;
			float y = (float) (yOri - yOri * (1 - 0.1f) * value.get(xValue.get(i)) / yValue.get(yValue.size() - 1));
            //当前点如果是选中点，绘制一个十字定位
            if (i == selectIndex - 1&&selectType==0) {
                drawFloatTextBox(canvas, x, y - dp7, value.get(xValue.get(i))+"");
                canvas.drawLine(0,y,width,y,blackPaint);
                canvas.drawLine(x,0,x,yOri,blackPaint);
            }
			/*//绘制选中的点
			if (i == selectIndex - 1) {
				linePaint.setStyle(Paint.Style.FILL);
				linePaint.setColor(0xffd0f3f2);
				canvas.drawCircle(x, y, dp7, linePaint);
				linePaint.setColor(0xff81dddb);
				canvas.drawCircle(x, y, dp4, linePaint);
				drawFloatTextBox(canvas, x, y - dp7, value.get(xValue.get(i)));
			}*/
			//绘制普通的节点
		/*	linePaint.setStyle(Paint.Style.FILL);
			linePaint.setColor(Color.WHITE);
			canvas.drawCircle(x, y, dp2, linePaint);*/
			linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
			linePaint.setColor(linecolor);
			canvas.drawCircle(x, y, dp2, linePaint);

		}
	}

    /**
     * 绘制折线对应的点
     *
     * @param canvas
     */
    private void drawBrokenPoint2(Canvas canvas) {
        float dp2 = dpToPx(2);
        float dp4 = dpToPx(4);
        float dp7 = dpToPx(7);
        //绘制节点对应的原点
        for (int i = 0; i < value2.size(); i++) {
            float x = xInit + interval * i;
            float y = (float) (yOri - yOri * (1 - 0.1f) * value2.get(xValue.get(i)) / yValue.get(yValue.size() - 1));
            //当前点如果是选中点，绘制一个十字定位
            if (i == selectIndex2 - 1&&selectType==1) {
                drawFloatTextBox(canvas, x, y - dp7, value2.get(xValue.get(i))+"");
                canvas.drawLine(0,y,width,y,blackPaint);
                canvas.drawLine(x,0,x,yOri,blackPaint);
            }
			/*//绘制选中的点
			if (i == selectIndex - 1) {
				linePaint.setStyle(Paint.Style.FILL);
				linePaint.setColor(0xffd0f3f2);
				canvas.drawCircle(x, y, dp7, linePaint);
				linePaint.setColor(0xff81dddb);
				canvas.drawCircle(x, y, dp4, linePaint);
				drawFloatTextBox(canvas, x, y - dp7, value.get(xValue.get(i)));
			}*/
            //绘制普通的节点
		/*	linePaint.setStyle(Paint.Style.FILL);
			linePaint.setColor(Color.WHITE);
			canvas.drawCircle(x, y, dp2, linePaint);*/

        }
    }

	/**
	 * 绘制显示Y值的浮动框
	 *
	 * @param canvas
	 * @param x
	 * @param y
	 * @param text
	 */
	private void drawFloatTextBox(Canvas canvas, float x, float y, String text) {
		int dp6 = dpToPx(6);
		int dp18 = dpToPx(18);
		/*//p1
		Path path = new Path();
		path.moveTo(x, y);
		//p2
		path.lineTo(x - dp6, y - dp6);
		//p3
		path.lineTo(x - dp18, y - dp6);
		//p4
		path.lineTo(x - dp18, y - dp6 - dp18);
		//p5
		path.lineTo(x + dp18, y - dp6 - dp18);
		//p6
		path.lineTo(x + dp18, y - dp6);
		//p7
		path.lineTo(x + dp6, y - dp6);
		//p1
		path.lineTo(x, y);
		canvas.drawPath(path, linePaint);*/
		//linePaint.setColor(Color.WHITE);
        blackPaint.setTextSize(spToPx(12));
		Rect rect = getTextBounds(text + "", blackPaint);
        float posY=y - dp6 - (dp18 - rect.height()) / 2;
        if (posY< rect.height()){//说明显示看不见了，改成显示在坐标底部
            posY=y + dp18 +rect.height();

        }
		canvas.drawText(text + "", x - rect.width() / 2,posY , blackPaint);
	}

	/**
	 * 绘制折线
	 *
	 * @param canvas
	 */
	private void drawBrokenLine(Canvas canvas) {
		linePaint.setStyle(Paint.Style.STROKE);
		linePaint.setColor(linecolor);
		//绘制折线
		Path path = new Path();
		float x = xInit + interval * 0;
		float y = (float) (yOri - yOri * (1 - 0.1f) * value.get(xValue.get(0)) / yValue.get(yValue.size() - 1));
		path.moveTo(x, y);
		for (int i = 1; i < value.size(); i++) {
			x = xInit + interval * i;
			y = (float) (yOri - yOri * (1 - 0.1f) * value.get(xValue.get(i)) / yValue.get(yValue.size() - 1));
			path.lineTo(x, y);
		}
		canvas.drawPath(path, linePaint);
	}

	/**
     * * 绘制折线
	 *
     * @param canvas
	 */
    private void drawBrokenLine2(Canvas canvas) {
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(linecolor);
        //绘制折线
        Path path = new Path();
        float x = xInit + interval * 0;
        float y = (float) (yOri - yOri * (1 - 0.1f) * value2.get(xValue.get(0)) / yValue.get(yValue.size() - 1));

        path.moveTo(x, y);
        for (int i = 1; i < value2.size(); i++) {
            x = xInit + interval * i;
            y = (float) (yOri - yOri * (1 - 0.1f) * value2.get(xValue.get(i)) / yValue.get(yValue.size() - 1));
            path.lineTo(x, y);

        }
        canvas.drawPath(path, linePaint);
        //填充折线 这边x，y的值一定是最后一个折线的坐标
        path.lineTo(x, yOri);
        path.lineTo(xInit, yOri);
        path.close();
        canvas.drawPath(path,valuePaint);

    }

    private boolean isNeedSplit=false;


    public boolean isNeedSplit() {
        return isNeedSplit;
    }

    public void setNeedSplit(boolean needSplit) {
        isNeedSplit = needSplit;
    }

    /**
	 * 绘制XY坐标
	 *
	 * @param canvas
	 */
	private void drawXY(Canvas canvas) {
		int length = dpToPx(4);//刻度的长度
		//绘制Y坐标
		// canvas.drawLine(xOri - xylinewidth / 2, 0, xOri - xylinewidth / 2, yOri, xyPaint);
		//绘制y轴箭头
		//xyPaint.setStyle(Paint.Style.STROKE);
		//Path path = new Path();
		//path.moveTo(xOri - xylinewidth / 2 - dpToPx(5), dpToPx(12));
		//path.lineTo(xOri - xylinewidth / 2, xylinewidth / 2);
		//path.lineTo(xOri - xylinewidth / 2 + dpToPx(5), dpToPx(12));
		//canvas.drawPath(path, xyPaint);
		//绘制y轴刻度
		int yLength = (int) (yOri * (1 - 0.1f) / (yValue.size() - 1));//y轴上面空出10%,计算出y轴刻度间距
		for (int i = 0; i < yValue.size(); i++) {
			//绘制Y轴刻度
			//   canvas.drawLine(xOri, yOri - yLength * i + xylinewidth / 2, xOri + length, yOri - yLength * i + xylinewidth / 2, xyPaint);
			xyTextPaint.setColor(xytextcolor);
            //绘制Y轴文本
            String text = yValue.get(i) + "";
            Rect rect = getTextBounds(text, xyTextPaint);
            canvas.drawText(text, 0, text.length(), xOri - xylinewidth - dpToPx(2) - rect.width(), yOri - yLength * i + rect.height() / 2, xyTextPaint);



		}
		//绘制X轴坐标
		canvas.drawLine(xOri, yOri + xylinewidth / 2, width, yOri + xylinewidth / 2, xyPaint);
		//绘制x轴箭头
		//  xyPaint.setStyle(Paint.Style.STROKE);
		//Path path = new Path();
		//整个X轴的长度
		//   float xLength = xInit + interval * (xValue.size() - 1) + (width - xOri) * 0.1f;
		// if (xLength < width)
		//  xLength = width;
		//path.moveTo(xLength - dpToPx(12), yOri + xylinewidth / 2 - dpToPx(5));
		//path.lineTo(xLength - xylinewidth / 2, yOri + xylinewidth / 2);
		//path.lineTo(xLength - dpToPx(12), yOri + xylinewidth / 2 + dpToPx(5));
		//canvas.drawPath(path, xyPaint);
		//绘制x轴刻度
		for (int i = 0; i < xValue.size(); i++) {
			float x = xInit + interval * i;
			if (x >= xOri) {//只绘制从原点开始的区域
				xyTextPaint.setColor(xytextcolor);
				//   canvas.drawLine(x, yOri, x, yOri - length, xyPaint);
                if (isNeedSplit&&i%2==1){

                }else{
                    //绘制X轴文本
                    String text = xValue.get(i);
                    Rect rect = getTextBounds(text, xyTextPaint);
                    canvas.drawText(text, 0, text.length(), x - rect.width() / 2, yOri + xylinewidth + dpToPx(2) + rect.height(), xyTextPaint);
                }

				/*if (i == selectIndex - 1) {
					xyTextPaint.setColor(linecolor);
					canvas.drawText(text, 0, text.length(), x - rect.width() / 2, yOri + xylinewidth + dpToPx(2) + rect.height(), xyTextPaint);
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
						canvas.drawRoundRect(x - xValueRect.width() / 2 - dpToPx(3), yOri + xylinewidth + dpToPx(1), x + xValueRect.width() / 2 + dpToPx(3),
								yOri + xylinewidth + dpToPx(2) + xValueRect.height() + dpToPx(2), dpToPx(2), dpToPx(2), xyTextPaint);
					}
				}
				else {
					canvas.drawText(text, 0, text.length(), x - rect.width() / 2, yOri + xylinewidth + dpToPx(2) + rect.height(), xyTextPaint);
				}*/
			}
		}
	}

	private float startX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isScrolling)
			return super.onTouchEvent(event);
		this.getParent().requestDisallowInterceptTouchEvent(true);//当该view获得点击事件，就请求父控件不拦截事件
		obtainVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			if (interval * xValue.size() > width - xOri) {//当期的宽度不足以呈现全部数据
				float dis = event.getX() - startX;
				startX = event.getX();
				if (xInit + dis < minXInit) {
					xInit = minXInit;
				}
				else if (xInit + dis > maxXInit) {
					xInit = maxXInit;
				}
				else {
					xInit = xInit + dis;
				}
				invalidate();
			}
			break;
		case MotionEvent.ACTION_UP:
			clickAction(event);
			scrollAfterActionUp();
			this.getParent().requestDisallowInterceptTouchEvent(false);
			recycleVelocityTracker();
			break;
		case MotionEvent.ACTION_CANCEL:
			this.getParent().requestDisallowInterceptTouchEvent(false);
			recycleVelocityTracker();
			break;
		}
		return true;
	}

	//是否正在滑动
	private boolean isScrolling = false;

	/**
	 * 手指抬起后的滑动处理
	 */
	private void scrollAfterActionUp() {
		if (!isScroll)
			return;
		final float velocity = getVelocity();
		float scrollLength = maxXInit - minXInit;
		if (Math.abs(velocity) < 10000)//10000是一个速度临界值，如果速度达到10000，最大可以滑动(maxXInit - minXInit)
			scrollLength = (maxXInit - minXInit) * Math.abs(velocity) / 10000;
		ValueAnimator animator = ValueAnimator.ofFloat(0, scrollLength);
		animator.setDuration((long) (scrollLength / (maxXInit - minXInit) * 1000));//时间最大为1000毫秒，此处使用比例进行换算
		animator.setInterpolator(new DecelerateInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				float value = (float) valueAnimator.getAnimatedValue();
				if (velocity < 0 && xInit > minXInit) {//向左滑动
					if (xInit - value <= minXInit)
						xInit = minXInit;
					else
						xInit = xInit - value;
				}
				else if (velocity > 0 && xInit < maxXInit) {//向右滑动
					if (xInit + value >= maxXInit)
						xInit = maxXInit;
					else
						xInit = xInit + value;
				}
				invalidate();
			}
		});
		animator.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {
				isScrolling = true;
			}

			@Override
			public void onAnimationEnd(Animator animator) {
				isScrolling = false;
			}

			@Override
			public void onAnimationCancel(Animator animator) {
				isScrolling = false;
			}

			@Override
			public void onAnimationRepeat(Animator animator) {

			}
		});
		animator.start();

	}

	/**
	 * 获取速度
	 *
	 * @return
	 */
	private float getVelocity() {
		if (velocityTracker != null) {
			velocityTracker.computeCurrentVelocity(1000);
			return velocityTracker.getXVelocity();
		}
		return 0;
	}

	/**
	 * 点击X轴坐标或者折线节点
	 *
	 * @param event
	 */
	private void clickAction(MotionEvent event) {
		int dp8 = dpToPx(8);
		float eventX = event.getX();
		float eventY = event.getY();
		for (int i = 0; i < xValue.size(); i++) {
            if (i<value.size()){
                //节点
                float x = xInit + interval * i;
                float y = (float) (yOri - yOri * (1 - 0.1f) * value.get(xValue.get(i)) / yValue.get(yValue.size() - 1));
                if (eventX >= x - dp8 && eventX <= x + dp8 && eventY >= y - dp8 && eventY <= y + dp8 && selectIndex != i + 1) {//每个节点周围8dp都是可点击区域
                    selectIndex = i + 1;
                    selectType=0;
                    invalidate();
                    return;
                }
            }


			if (i<value2.size()){
                //节点2
                float x2 = xInit + interval * i;
                float y2 = (float) (yOri - yOri * (1 - 0.1f) * value2.get(xValue.get(i)) / yValue.get(yValue.size() - 1));
                if (eventX >= x2 - dp8 && eventX <= x2 + dp8 && eventY >= y2 - dp8 && eventY <= y2 + dp8 && selectIndex2 != i + 1) {//每个节点周围8dp都是可点击区域
                    selectIndex2 = i + 1;
                    selectType=1;
                    invalidate();
                    return;
                }
            }

			//X轴刻度
			/*String text = xValue.get(i);
			Rect rect = getTextBounds(text, xyTextPaint);
			x = xInit + interval * i;
			y = yOri + xylinewidth + dpToPx(2);
			if (eventX >= x - rect.width() / 2 - dp8 && eventX <= x + rect.width() + dp8 / 2 && eventY >= y - dp8 && eventY <= y + rect.height() + dp8 && selectIndex != i + 1) {
				selectIndex = i + 1;
				invalidate();
				return;
			}*/
		}
	}

	/**
	 * 获取速度跟踪器
	 *
	 * @param event
	 */
	private void obtainVelocityTracker(MotionEvent event) {
		if (!isScroll)
			return;
		if (velocityTracker == null) {
			velocityTracker = VelocityTracker.obtain();
		}
		velocityTracker.addMovement(event);
	}

	/**
	 * 回收速度跟踪器
	 */
	private void recycleVelocityTracker() {
		if (velocityTracker != null) {
			velocityTracker.recycle();
			velocityTracker = null;
		}
	}

	public int getSelectIndex() {
		return selectIndex;
	}

	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
		invalidate();
	}

	public void setxValue(List<String> xValue) {
		this.xValue = xValue;
	}

	public void setyValue(List<Double> yValue) {
		this.yValue = yValue;
		invalidate();
	}

	public void setValue(Map<String, Double> value) {
		this.value = value;
		invalidate();
	}

	public void setValue(Map<String, Double> value,Map<String, Double> value2, List<String> xValue, List<Double> yValue,String xLength) {
		this.value = value;
		this.value2 = value2;
		this.xValue = xValue;
		this.yValue = yValue;
        //Y轴文本最大宽度
        float textYWdith = getTextBounds(xLength, xyTextPaint).width();
        int dp2 = dpToPx(2);
        xOri = (int) (dp2 + textYWdith + dp2 + xylinewidth);//dp2是y轴文本距离左边，以及距离y轴的距离
        xInit =  xOri+dpToPx(5);
		invalidate();
	}

	public List<String> getxValue() {
		return xValue;
	}

	public List<Double> getyValue() {
		return yValue;
	}

	public Map<String, Double> getValue() {
		return value;
	}

	/**
	 * 获取丈量文本的矩形
	 *
	 * @param text
	 * @param paint
	 * @return
	 */
	private Rect getTextBounds(String text, Paint paint) {
		Rect rect = new Rect();
		paint.getTextBounds(text, 0, text.length(), rect);
		return rect;
	}

	/**
	 * dp转化成为px
	 *
	 * @param dp
	 * @return
	 */
	private int dpToPx(double dp) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
	}

	/**
	 * sp转化为px
	 *
	 * @param sp
	 * @return
	 */
	private int spToPx(int sp) {
		float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (scaledDensity * sp + 0.5f * (sp >= 0 ? 1 : -1));
	}
}
