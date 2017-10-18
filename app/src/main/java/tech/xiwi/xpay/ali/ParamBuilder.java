package tech.xiwi.xpay.ali;

/**
 * Created by xiwi on 2017/10/18.
 */

public class ParamBuilder {
    /**
     * 设置商户PID
     */
    private String partner = "";
    // 商户收款账号
    private String seller = "";
    // 商户私钥，pkcs8格式
    private String rsaPrivate = "";
    // 支付宝公钥
    private String rsaPublic = "";
    private int sdkPayFlag = 6406;

    private String orderTitle = "";
    private String subTitle = "";
    private String price = "";
    private String notifyURL = "";

    /**
     * 设置商户PID
     */
    public ParamBuilder setPartner(String partner) {
        this.partner = partner;
        return this;
    }

    /**
     * 设置商户收款账号
     */
    public ParamBuilder setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    /**
     * 设置商户私钥，pkcs8格式
     */
    public ParamBuilder setRsaPrivate(String rsaPrivate) {
        this.rsaPrivate = rsaPrivate;
        return this;
    }

    /**
     * 设置支付宝公钥
     */
    public ParamBuilder setRsaPublic(String rsaPublic) {
        this.rsaPublic = rsaPublic;
        return this;
    }

    /**
     * 设置商品名称
     */
    public ParamBuilder setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
        return this;
    }

    /**
     * 设置商品详情
     */
    public ParamBuilder setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    /**
     * 设置商品价格
     */
    public ParamBuilder setPrice(String price) {
        this.price = price;
        return this;
    }

    /**
     * 设置支付宝支付成功后通知的地址，可以填写你公司的地址
     */
    public ParamBuilder setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
        return this;
    }
}
