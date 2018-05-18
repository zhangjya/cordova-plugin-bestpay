package com.zjy.plugin.bestpay;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.*;

import java.util.HashMap;

/**
 * This class echoes a string called from JavaScript.
 */
public class BestPay extends CordovaPlugin {

    private CallbackContext mCallbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("payment")) {
            mCallbackContext = callbackContext;
            String payStr = args.getString(0);

            Intent intent = new Intent(cordova.getActivity(),BestPayActivity.class);
            intent.putExtra("payStr", payStr);

            cordova.setActivityResultCallback(this);
            cordova.startActivityForResult(this,intent,200);
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;
        if (requestCode == 200){
            HashMap status = new HashMap();
            status.put("code",resultCode);
            status.put("result",data.getStringExtra("result"));

            JSONObject obj = new JSONObject(status);
            PluginResult result;

            switch (resultCode) {
                case -1:
                    result = new PluginResult(PluginResult.Status.OK, obj);
                    mCallbackContext.sendPluginResult(result);
                    break;
                default:
                    result = new PluginResult(PluginResult.Status.ERROR, obj);
                    mCallbackContext.sendPluginResult(result);
                    break;
            }
        }
    }
}