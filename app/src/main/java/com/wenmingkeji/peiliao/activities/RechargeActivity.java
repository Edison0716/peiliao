package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.alipay.AlipayContect;
import com.wenmingkeji.peiliao.alipay.Keys;
import com.wenmingkeji.peiliao.alipay.PayResult;
import com.wenmingkeji.peiliao.utils.ToastUtil;
import com.wenmingkeji.peiliao.view.SmoothCheckBox;
import com.wenmingkeji.peiliao.wechat.Constants;
import com.wenmingkeji.peiliao.wechat.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 10564 on 2016/7/19.
 */
public class RechargeActivity extends Activity implements View.OnClickListener{
    private final int SUCCESSED = 1;
    private final int FAILED = 2;
    private IWXAPI api;
    private Map<String, String> resultunifiedorder;
    private static final int SDK_ALPAY_FLAG = 1;
    private ImageButton ib_back;
    RelativeLayout rl_alipay,rl_weChat;
    SmoothCheckBox scb1,scb2;

    private Button button1,button2,button3,button4,button5,button6,button7,button8;
    private EditText rechargeCount;
    private Button bt_confirm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recharge);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        rechargeCount = (EditText) findViewById(R.id.rechargeCount);
        initBack();
        initSingleChoice();
        initReCharge();
        initconfirm();
    }

    private void initconfirm() {
        bt_confirm = (Button) findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = rechargeCount.getText().toString();
                if(count.equals("￥0.00")==false){
                if (scb1.isChecked()==true){
                    AlipayContect ac = new AlipayContect(Keys.PARTNER, Keys.SELLER, Keys.RSA_PRIVATE);
                    try {
                        final String info = ac.getOrderInfo("商品名称", "商品详情", rechargeCount.getText().toString().trim());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 构造PayTask 对象
                                PayTask alipay = new PayTask(RechargeActivity.this);
                                // 调用支付接口，获取支付结果
                                String result = alipay.pay(info, true);
                                Message msg = new Message();
                                msg.what = SDK_ALPAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (scb2.isChecked()==true){
                    boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                    if (!isPaySupported)
                    {
                        Toast.makeText(RechargeActivity.this, "当前微信版本不符合或没有安装", Toast.LENGTH_LONG).show();
                        return;
                    }


                    ///最好增加一个联网请求的判断
                    new Thread(new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            String url = String.format(Util.URL);
                            String entity = genProductArgs();
                            byte[] buf = Util.httpPost(url, entity);

                            String content = new String(buf);
                            Map<String, String> xml = Util.decodeXml(content);
                            Message message = new Message();
                            resultunifiedorder = xml;
                            if (!TextUtils.isEmpty(xml.get("prepay_id")))
                            {
                                message.what = SUCCESSED;
                            } else
                            {
                                message.obj=xml.get("return_code");
                                message.what = FAILED;

                            }
                            mHandler1.sendMessage(message);

                        }
                    }).start();
                    //ToastUtil.show("微信支付");
                    Toast.makeText(RechargeActivity.this, "微信支付", Toast.LENGTH_SHORT).show();
                }
                }else {
                    Toast.makeText(RechargeActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initReCharge() {
        button1 = (Button) findViewById(R.id.recharge20);
        button2 = (Button) findViewById(R.id.recharge50);
        button3 = (Button) findViewById(R.id.recharge100);
        button4 = (Button) findViewById(R.id.recharge300);
        button5 = (Button) findViewById(R.id.recharge500);
        button6 = (Button) findViewById(R.id.recharge1000);
        button7 = (Button) findViewById(R.id.recharge3000);
        button8 = (Button) findViewById(R.id.user_define);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        rechargeCount.setEnabled(false);
    }
    private void initSingleChoice() {
        scb1 = (SmoothCheckBox) findViewById(R.id.scb);
        scb2 = (SmoothCheckBox) findViewById(R.id.scb1);
        rl_alipay = (RelativeLayout) findViewById(R.id.usingAlipay);
        rl_weChat = (RelativeLayout) findViewById(R.id.usingWechat);
        scb1.setChecked(true);
        scb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb1.isChecked()==false){
                scb1.setChecked(true,true);
                scb2.setChecked(false,true);
                }
            }
        });
        rl_alipay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(scb1.isChecked()==false){
                   scb1.setChecked(true,true);
                   scb2.setChecked(false,true);
               }
           }
       });
        rl_weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb2.isChecked()==false){
                scb1.setChecked(false,true);
                scb2.setChecked(true,true);
                }
            }
        });
        scb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb2.isChecked()==false){
                    scb1.setChecked(false,true);
                    scb2.setChecked(true,true);
                }
            }
        });

    }
    private void initBack() {
        ib_back = (ImageButton) findViewById(R.id.ic_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recharge20 :
                rechargeCount.setText("20");
                rechargeCount.setEnabled(false);
                break;
            case R.id.recharge50 :
                rechargeCount.setText("50");
                rechargeCount.setEnabled(false);
                break;
            case R.id.recharge100 :
                rechargeCount.setText("100");
                rechargeCount.setEnabled(false);

                break;
            case R.id.recharge300 :
                rechargeCount.setText("300");
                rechargeCount.setEnabled(false);
                break;
            case R.id.recharge500 :
                rechargeCount.setText("500");
                rechargeCount.setEnabled(false);
                break;
            case R.id.recharge1000 :
                rechargeCount.setText("1000");
                rechargeCount.setEnabled(false);
                break;
            case R.id.recharge3000 :
                rechargeCount.setText("3000");
                rechargeCount.setEnabled(false);
                break;
            case R.id.user_define:
                rechargeCount.setText("");
                rechargeCount.setEnabled(true);
                //ToastUtil.show("请输入金额！");
                Toast.makeText(RechargeActivity.this,"请输入金额！",Toast.LENGTH_SHORT).show();
                break;
        }

    }
    private Handler mHandler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESSED:
                    PayReq req = new PayReq();
                    req.appId = Constants.APP_ID;
                    req.partnerId = Constants.APP_PID;
                    req.prepayId = resultunifiedorder.get("prepay_id");

                    req.nonceStr = Util.genNonceStr();// 随机字符串
                    req.timeStamp = String.valueOf(Util.genTimeStamp());// 时间戳
                    req.packageValue = "Sign=WXPay";// 暂时填写的固定值
                    List<NameValuePair> signParams = new LinkedList<NameValuePair>();
                    signParams.add(new BasicNameValuePair("appid", req.appId));
                    signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
                    signParams.add(new BasicNameValuePair("package", req.packageValue));
                    signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
                    signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
                    signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

                    req.sign = Util.genAppSign(signParams);//将app进行签名


                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.registerApp(Constants.APP_ID);
                    api.sendReq(req);

                    break;
                case FAILED:

                    Toast.makeText(RechargeActivity.this, "失败原因："+(String)msg.obj, Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        };
    };
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_ALPAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(RechargeActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    private String genProductArgs()
    {
        StringBuffer xml = new StringBuffer();

        try
        {
            String nonceStr = Util.genNonceStr();
            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
            packageParams.add(new BasicNameValuePair("body", "商品信息描述"));
            packageParams.add(new BasicNameValuePair("mch_id", Constants.APP_PID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", "http://121.40.35.3"));
            packageParams.add(new BasicNameValuePair("out_trade_no", Util.genOutTradNo()));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));
            packageParams.add(new BasicNameValuePair("total_fee", rechargeCount.getText().toString().trim()));//总金额数
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));

            String sign = Util.genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));

            String xmlstring = Util.toXml(packageParams);

            return xmlstring;

        } catch (Exception e)
        {
            return null;
        }

    }
}
