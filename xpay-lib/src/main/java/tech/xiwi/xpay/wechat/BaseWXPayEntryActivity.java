package tech.xiwi.xpay.wechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import tech.xiwi.xpay.IPay;
import tech.xiwi.xpay.PayApi;

/**
 * Created by xiwi on 2017/10/17.
 */
public abstract class BaseWXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    private static IPay.PayCallback payCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, appId());
        api.handleIntent(getIntent(), this);
    }

    public static void setPayCallback(IPay.PayCallback callback) {
        payCallback = callback;
    }

    /**
     * WeChat_APP_ID
     */
    public abstract String appId();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (api != null && intent != null) {
            api.handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //支付结果回调 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5
        if (baseResp != null) {
            if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

                if (payCallback != null) {
                    if (baseResp.errCode == 0) {//支付成功
                        payCallback.onSuccess();
                    } else if (baseResp.errCode == -1) {//支付失败
                        payCallback.onFailed();
                    } else {//取消
                        payCallback.onCancel();
                    }
                }
            }
            PayApi.isPaying = false;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (api != null) {
            payCallback = null;
            api = null;
        }
    }
}
