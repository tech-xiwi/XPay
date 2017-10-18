package tech.xiwi.xpay.wxapi;

import tech.xiwi.xpay.wechat.BaseWXPayEntryActivity;

/**
 * Created by xiwi on 2017/10/17.
 */
public class WXPayEntryActivity extends BaseWXPayEntryActivity {
    public static final String APP_ID = "wxb51b89cba83263de";

    @Override
    public String appId() {
        return APP_ID;
    }
}
