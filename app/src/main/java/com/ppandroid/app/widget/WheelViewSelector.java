package com.ppandroid.app.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ppandroid.app.R;
import com.ppandroid.app.bean.mine.energyanalysis.Model;
import com.ppandroid.app.utils.DebugLog;

import java.util.ArrayList;
import java.util.List;

/**
 * You may think you know what the following code does. But you don't. Trust me
 * Fiddle with it, and you'll spend many a sleepless night cursing the moment
 * you thought you'd be clever enough to "optimize" the code below. Now close
 * this file and go play with something else. God bless you!!! The God's name is
 * YeQinFu
 */
public class WheelViewSelector extends ScrollView {
	public static final String TAG = WheelViewSelector.class.getSimpleName();

	public static class OnWheelViewListener {
		public void onSelected(int selectedIndex, String item) {}
	}

	private Context			context;
	//    private ScrollView scrollView;

	private LinearLayout	views;

	public WheelViewSelector(Context context) {
		super(context);
		init(context);
	}

	public WheelViewSelector(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public WheelViewSelector(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	//    String[] items;
	List<Model> items;

	private List<Model> getItems() {
		return items;
	}



	public void setItems(List<Model> list) {
		if (null == items) {
			items = new ArrayList<Model>();
		}
		items.clear();
		items.addAll(list);

		// 前面和后面补全
		for (int i = 0; i < offset; i++) {
            Model model=new Model();
			items.add(0, model);
			items.add(model);
		}

		initData();

	}

	public static final int	OFF_SET_DEFAULT	= 1;
	int						offset			= OFF_SET_DEFAULT;	// 偏移量（需要在最前面和最后面补全）

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	int	displayItemCount;	// 每页显示的数量

	int	selectedIndex	= 1;

	private void init(Context context) {
		this.context = context;

		//        scrollView = ((ScrollView)this.getParent());
		//        Log.d(TAG, "scrollview: " + scrollView);
		Log.d(TAG, "parent: " + this.getParent());
		//        this.setOrientation(VERTICAL);
		this.setVerticalScrollBarEnabled(false);

		views = new LinearLayout(context);
		views.setOrientation(LinearLayout.VERTICAL);
		this.addView(views);

		scrollerTask = new Runnable() {

			public void run() {

				int newY = getScrollY();
				if (initialY - newY == 0) { // stopped
					final int remainder = initialY % itemHeight;
					final int divided = initialY / itemHeight;
					//                    Log.d(TAG, "initialY: " + initialY);
					//                    Log.d(TAG, "remainder: " + remainder + ", divided: " + divided);
					if (remainder == 0) {
						selectedIndex = divided + offset;

						onSeletedCallBack();
					}
					else {
						if (remainder > itemHeight / 2) {
							WheelViewSelector.this.post(new Runnable() {
								@Override
								public void run() {
									WheelViewSelector.this.smoothScrollTo(0, initialY - remainder + itemHeight);
									selectedIndex = divided + offset + 1;
									onSeletedCallBack();
								}
							});
						}
						else {
							WheelViewSelector.this.post(new Runnable() {
								@Override
								public void run() {
									WheelViewSelector.this.smoothScrollTo(0, initialY - remainder);
									selectedIndex = divided + offset;
									onSeletedCallBack();
								}
							});
						}

					}

				}
				else {
					initialY = getScrollY();
					WheelViewSelector.this.postDelayed(scrollerTask, newCheck);
				}
			}
		};

	}

	int			initialY;

	Runnable	scrollerTask;
	int			newCheck	= 50;

	public void startScrollerTask() {

		initialY = getScrollY();
		this.postDelayed(scrollerTask, newCheck);
	}

	private void initData() {
		displayItemCount = offset * 2 + 1;
		for (Model item : items) {
			views.addView(createItemView(item));
		}
		refreshItemView(0);
        scrollTo(0,offset*itemHeight*2);
	}

	int itemHeight = 0;
    private View createItemView(Model item){
        View v= LayoutInflater.from(context).inflate(R.layout.item_wheel_view_selector,null);
        TextView tv_text= (TextView) v.findViewById(R.id.tv_text);
        tv_text.setText(item.getTitle());
        if (TextUtils.isEmpty(item.getTitle())) {
            tv_text.setCompoundDrawables(null, null, null, null);
        }else{
            Drawable drawable = getResources().getDrawable(item.getTopDrawable());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_text.setCompoundDrawables(null, drawable, null, null);
        }
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(v);
            Log.d(TAG, "itemHeight: " + itemHeight);
            views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            lp.gravity = Gravity.CENTER;
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return v;
    }

	private TextView createView(String item) {
		TextView tv = new TextView(context);
		tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		tv.setSingleLine(true);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		tv.setGravity(Gravity.CENTER);
		int padding = dip2px(15);
		tv.setPadding(padding, padding, padding, padding);

		tv.setText(item);
		Drawable drawable = getResources().getDrawable(R.drawable.zuzt);
		DebugLog.d("itemHeight" + drawable.getIntrinsicHeight());
		int drawableH = drawable.getIntrinsicHeight();
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		if (!TextUtils.isEmpty(item)) {
			tv.setCompoundDrawables(null, drawable, null, null);
		}
		if (0 == itemHeight) {
			itemHeight = getViewMeasuredHeight(tv);
			itemHeight += drawableH;
			Log.d(TAG, "itemHeight: " + itemHeight);
			views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
			lp.gravity = Gravity.CENTER;
			this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, ViewGroup.LayoutParams.MATCH_PARENT));
		}
		return tv;
	}



    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }
    private Paint getTextPaint() {
        Paint paint = getPaint();
        paint.setTextSize(30f);
        paint.setColor(getResources().getColor(R.color.color_01));
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    @Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		//        Log.d(TAG, "l: " + l + ", t: " + t + ", oldl: " + oldl + ", oldt: " + oldt);

		//        try {
		//            Field field = ScrollView.class.getDeclaredField("mScroller");
		//            field.setAccessible(true);
		//            OverScroller mScroller = (OverScroller) field.get(this);
		//
		//
		//            if(mScroller.isFinished()){
		//                Log.d(TAG, "isFinished...");
		//            }
		//
		//        } catch (Exception e) {
		//            e.printStackTrace();
		//        }

		refreshItemView(t);

		if (t > oldt) {
			//            Log.d(TAG, "向下滚动");
			scrollDirection = SCROLL_DIRECTION_DOWN;
		}
		else {
			//            Log.d(TAG, "向上滚动");
			scrollDirection = SCROLL_DIRECTION_UP;

		}

	}

	private void refreshItemView(int y) {
		int position = y / itemHeight + offset;
		int remainder = y % itemHeight;
		int divided = y / itemHeight;

		if (remainder == 0) {
			position = divided + offset;
		}
		else {
			if (remainder > itemHeight / 2) {
				position = divided + offset + 1;
			}

			//            if(remainder > itemHeight / 2){
			//                if(scrollDirection == SCROLL_DIRECTION_DOWN){
			//                    position = divided + offset;
			//                    Log.d(TAG, ">down...position: " + position);
			//                }else if(scrollDirection == SCROLL_DIRECTION_UP){
			//                    position = divided + offset + 1;
			//                    Log.d(TAG, ">up...position: " + position);
			//                }
			//            }else{
			////                position = y / itemHeight + offset;
			//                if(scrollDirection == SCROLL_DIRECTION_DOWN){
			//                    position = divided + offset;
			//                    Log.d(TAG, "<down...position: " + position);
			//                }else if(scrollDirection == SCROLL_DIRECTION_UP){
			//                    position = divided + offset + 1;
			//                    Log.d(TAG, "<up...position: " + position);
			//                }
			//            }
			//        }

			//        if(scrollDirection == SCROLL_DIRECTION_DOWN){
			//            position = divided + offset;
			//        }else if(scrollDirection == SCROLL_DIRECTION_UP){
			//            position = divided + offset + 1;
		}

		int childSize = views.getChildCount();
		for (int i = 0; i < childSize; i++) {
			View v =  views.getChildAt(i);
            TextView itemView= (TextView) v.findViewById(R.id.tv_text);
            ImageView iv_img= (ImageView) v.findViewById(R.id.iv_img);
			if (null == itemView) {
				return;
			}
			if (position == i) {
                iv_img.setVisibility(View.VISIBLE);
				itemView.setTextColor(Color.parseColor("#0288ce"));
			}
			else {
                iv_img.setVisibility(View.GONE);
				itemView.setTextColor(Color.parseColor("#bbbbbb"));
			}
		}
	}

	/**
	 * 获取选中区域的边界
	 */
	int[] selectedAreaBorder;

	private int[] obtainSelectedAreaBorder() {
		if (null == selectedAreaBorder) {
			selectedAreaBorder = new int[2];
			selectedAreaBorder[0] = itemHeight * offset;
			selectedAreaBorder[1] = itemHeight * (offset + 1);
		}
		return selectedAreaBorder;
	}


	private int					scrollDirection			= -1;
	private static final int	SCROLL_DIRECTION_UP		= 0;
	private static final int	SCROLL_DIRECTION_DOWN	= 1;

	Paint						paint;
	int							viewWidth;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		DebugLog.d(TAG, "-------->" + heightSize);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.d(TAG, "w: " + w + ", h: " + h + ", oldw: " + oldw + ", oldh: " + oldh);
		viewWidth = w;


		if (viewWidth == 0) {
			viewWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
			Log.d(TAG, "viewWidth: " + viewWidth);
		}

		if (null == paint) {
			paint = new Paint();
			paint.setColor(Color.parseColor("#83cde6"));
			paint.setStrokeWidth(dip2px(1f));
		}
    }

	/**
	 * 选中回调
	 */
	private void onSeletedCallBack() {
		if (null != onWheelViewListener) {
			if (selectedIndex - offset >= 0 && items.size() - offset > selectedIndex) {
				onWheelViewListener.onSelected(selectedIndex - offset, items.get(selectedIndex).getTitle());
			}
		}

	}

	public void setSeletion(int position) {
		final int p = position;
		selectedIndex = p + offset;
		this.post(new Runnable() {
			@Override
			public void run() {
				WheelViewSelector.this.smoothScrollTo(0, p * itemHeight);
			}
		});

	}

	public String getSeletedItem() {
		return items.get(selectedIndex).getTitle();
	}

	public int getSeletedIndex() {
		return selectedIndex - offset;
	}

	@Override
	public void fling(int velocityY) {
		super.fling(velocityY / 3);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			startScrollerTask();
		}
		return super.onTouchEvent(ev);
	}

	private OnWheelViewListener onWheelViewListener;

	public OnWheelViewListener getOnWheelViewListener() {
		return onWheelViewListener;
	}

	public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
		this.onWheelViewListener = onWheelViewListener;
	}

	private int dip2px(float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	private int getViewMeasuredHeight(View view) {
		int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		view.measure(width, expandSpec);
		return view.getMeasuredHeight();
	}

}

