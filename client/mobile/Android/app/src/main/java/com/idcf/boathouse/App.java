package com.idcf.boathouse;

import android.app.Application;
import android.content.Context;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by JaneConan on 2020/3/3.
 */
public class App extends Application {

    private static final String TAG = "App";

    private static App mContext = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = this;
    }

    public static App context() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppManager.init(this);

        // Fragmentation is recommended to initialize in the Application
        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG) //true 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }
}
