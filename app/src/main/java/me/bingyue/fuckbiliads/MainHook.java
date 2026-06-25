package me.bingyue.fuckbiliads;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    private static final String TAG = "FuckBiliAds";
    private static final String TARGET_PACKAGE = "tv.danmaku.bili";
    private static final String TARGET_CLASS = "tv.danmaku.bili.ui.splash.ad.model.Splash";
    private static final String TARGET_METHOD = "isValid";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!TARGET_PACKAGE.equals(lpparam.packageName)) return;
        try {
            XposedHelpers.findAndHookMethod(
                    TARGET_CLASS,
                    lpparam.classLoader,
                    TARGET_METHOD,
                    XC_MethodReplacement.returnConstant(false)
            );
        } catch (Throwable t) {
            XposedBridge.log("[" + TAG + "] hook failed: " + t);
        }
    }
}
