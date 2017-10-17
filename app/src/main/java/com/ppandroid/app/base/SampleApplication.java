/*
 * Created by yeqinfu on 17-10-17 上午9:28
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.base;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by yeqinfu on 2017/10/12.
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.ppandroid.app.base.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}