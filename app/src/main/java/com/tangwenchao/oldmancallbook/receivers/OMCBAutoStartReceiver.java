package com.tangwenchao.oldmancallbook.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tangwenchao.oldmancallbook.activitys.MainActivity;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 13:59
 * @描述 TODO
 */
public class OMCBAutoStartReceiver extends BroadcastReceiver {

    public OMCBAutoStartReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

}
