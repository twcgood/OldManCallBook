package com.tangwenchao.oldmancallbook.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/6 13:12
 * @描述 TODO
 */
public class TextToSpeechUtil implements TextToSpeech.OnInitListener {

    private static final String TAG = "TextToSpeechUtil";
    private static TextToSpeechUtil instance = null;
    private TextToSpeech mTextToSpeech = null;
    private Context mContext = null;

    private TextToSpeechUtil() {
    }

    public static TextToSpeechUtil getInstance() {
        if (null == instance) {
            instance = new TextToSpeechUtil();
        }
        return instance;
    }

    public void init(Context context) {
        mContext = context;
        if (mTextToSpeech == null) {
            mTextToSpeech = new TextToSpeech(mContext, this);
        }
    }

    public void speak(String s) {
        if (mContext == null) {
            LogUtil.i(TAG, "TextToSpeech 未初始化");
            return;
        }

        if (mTextToSpeech.isSpeaking()) {
            mTextToSpeech.stop();
        }

        mTextToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTextToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(mContext, "数据丢失或不支持该语言", Toast.LENGTH_SHORT).show();
                return;
            }
            mTextToSpeech.setPitch(1f);
            mTextToSpeech.setSpeechRate(0.6f);
        }
    }

    public void clear() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
    }
}
