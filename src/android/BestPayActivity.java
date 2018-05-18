package com.zjy.plugin.bestpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bestpay.app.PaymentTask;

/**
 * Created by zjy on 2018/5/18.
 */

public class BestPayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String payStr = this.getIntent().getStringExtra("payStr");
        PaymentTask bestPay = new PaymentTask(this);
        bestPay.pay(payStr);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        this.setResult(resultCode, data);
        this.finish();
    }
}
