/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.wheelview;

import android.graphics.Path;

interface Renderer {
    Path buildPath(float animationProgress, float animationSeek);
}
