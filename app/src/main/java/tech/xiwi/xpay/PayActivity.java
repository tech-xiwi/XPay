package tech.xiwi.xpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Map;

import tech.xiwi.xpay.ali.OrderInfoUtil2_0;
import tech.xiwi.xpay.wechat.WXPay;
import tech.xiwi.xpay.wxapi.WXPayEntryActivity;

public class PayActivity extends AppCompatActivity implements IPay.PayCallback {
    private static final String TAG = "PayActivity";
    PayApi payApi = new PayApi();
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        findViewById(R.id.wx_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WXPay.ParamBuilder builder = new WXPay.ParamBuilder();
                builder.setAppId(WXPayEntryActivity.APP_ID)
                        .setNonceStr("3tdfsdf2342")
                        .setPartnerId("1486219452")
                        .setPrepayId("fsdfjkdfkldsfla")
                        .setSign("dsfjhdbu454das5d4ew5r")
                        .setTimeStamp("1505461043");

                payApi.pay(PayActivity.this, builder.create(), PayActivity.this);
            }
        });
        findViewById(R.id.ali_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * orderInfo的获取必须来自服务端；
                 */
                boolean rsa2 = (RSA2_PRIVATE.length() > 0);
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

                String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
                String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                final String orderInfo = orderParam + "&" + sign;

                payApi.pay(PayActivity.this, orderInfo, PayActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payApi.release();
        payApi = null;
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, " onSuccess: ");
    }

    @Override
    public void onFailed() {
        Log.d(TAG, " onFailed: ");
    }

    @Override
    public void onCancel() {
        Log.d(TAG, " onCancel: ");
    }
}
