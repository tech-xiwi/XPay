package tech.xiwi.xpay;

import android.app.Activity;


import org.json.JSONObject;

import tech.xiwi.xpay.ali.AliPay;
import tech.xiwi.xpay.wechat.WXPay;

/**
 * Created by xiwi on 2017/10/17.
 */
public class PayApi {
    public static volatile boolean isPaying = false;
    private IPay pay;

    public <T> void pay(Activity activity, T param, IPay.PayCallback callback) {
        if (activity != null && param != null && !isPaying) {
            if (param instanceof String) {
                isPaying = true;
                pay = new AliPay();
                pay.pay(activity, param, callback);
            } else if (param instanceof JSONObject) {
                isPaying = true;
                pay = new WXPay();
                pay.pay(activity, param, callback);
            }
        }
    }

    public void release() {
        if (pay != null && isPaying) {
            pay.release();
            pay = null;
        }
    }
}
