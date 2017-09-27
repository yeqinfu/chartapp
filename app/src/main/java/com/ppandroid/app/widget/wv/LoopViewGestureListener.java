/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget.wv;

import android.view.MotionEvent;

// Referenced classes of package com.qingchifan.view:
//            LoopView

final class LoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final LoopView loopView;

    LoopViewGestureListener(LoopView loopview) {
        super();
        loopView = loopview;
    }

    @Override
    public final boolean onDown(MotionEvent motionevent) {
        loopView.cancelFuture();
        return true;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.smoothScroll(velocityY);
        return true;
    }
}
