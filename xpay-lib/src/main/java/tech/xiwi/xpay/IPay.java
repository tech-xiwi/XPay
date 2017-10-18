package tech.xiwi.xpay;

import android.app.Activity;

/**
 * Created by xiwi on 2017/10/17.
 */

public interface IPay<T> {

    public static final String TAG = "IPay";

    void pay(Activity activity, T param, PayCallback callback);

    void release();

    public static interface PayCallback {
        void onSuccess();

        void onFailed();

        void onCancel();
    }
}
