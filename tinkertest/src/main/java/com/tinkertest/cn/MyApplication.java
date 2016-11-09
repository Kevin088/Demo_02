package com.tinkertest.cn;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
/**
 * Created by xll on 2016/11/8.
 */
@DefaultLifeCycle(
        application = "com.tinkertest.cn.Application",
        flags = ShareConstants.TINKER_ENABLE_ALL
)
public class MyApplication extends DefaultApplicationLike{
    public MyApplication(Application application,
                         int tinkerFlags,
                         boolean tinkerLoadVerifyFlag,
                         long applicationStartElapsedTime,
                         long applicationStartMillisTime,
                         Intent tinkerResultIntent,
                         Resources[] resources,
                         ClassLoader[] classLoader,
                         AssetManager[] assetManager) {
        super(application,
                tinkerFlags,
                tinkerLoadVerifyFlag,
                applicationStartElapsedTime,
                applicationStartMillisTime,
                tinkerResultIntent,
                resources,
                classLoader,
                assetManager);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerInstaller.install(this);
    }
}
