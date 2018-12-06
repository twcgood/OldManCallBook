package com.tangwenchao.oldmancallbook.base;

import android.app.Application;

import com.tangwenchao.oldmancallbook.utils.TextToSpeechUtil;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/10/12 11:40
 * @描述 自定义application，与应用程序相关的方法及属性
 */
public class OMCBApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTextToSpeech();
    }

    private void initTextToSpeech() {
        TextToSpeechUtil.getInstance().init(getApplicationContext());
    }

}
