package com.tangwenchao.oldmancallbook.activitys;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tangwenchao.oldmancallbook.R;
import com.tangwenchao.oldmancallbook.adapters.PhoneListAdapter;
import com.tangwenchao.oldmancallbook.base.BaseActivity;
import com.tangwenchao.oldmancallbook.contracts.PhoneListActivityContract;
import com.tangwenchao.oldmancallbook.interfaces.Delegte;
import com.tangwenchao.oldmancallbook.presenterImpls.PhoneListActivityPresenterImpl;
import com.tangwenchao.oldmancallbook.utils.LogUtil;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 16:37
 * @描述 TODO
 */
public class PhoneListActivity extends BaseActivity implements PhoneListActivityContract.PhoneListActivityView, View.OnClickListener, TextToSpeech.OnInitListener {

    private static final String TAG = "PhoneListActivity";
    private PhoneListActivityContract.PhoneListActivityPresenter mPhoneListActivityPresenter;
    private PhoneListActivityPresenterImpl mPhoneListActivityPresenterImpl;
    private RecyclerView mRcPhoneList;
    private TextView mCall;
    private TextView mBack;
    private LinearLayoutManager mLinearLayoutManager;
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_phone_list;
    }

    @Override
    public void initView() {
        mRcPhoneList = findViewById(R.id.phoneList);
        mCall = findViewById(R.id.call);
        mBack = findViewById(R.id.back);

    }

    @Override
    public void initData() {
        mTextToSpeech = new TextToSpeech(this, this);
        mPhoneListActivityPresenterImpl = new PhoneListActivityPresenterImpl(this, this);
        mPhoneListActivityPresenterImpl.getPhoneList();
        mCall.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void setPhoneList(final ArrayList<String> phoneList) {
        PhoneListAdapter phoneListAdapter = new PhoneListAdapter(this, phoneList, mRcPhoneList, new Delegte() {
            @Override
            public void exec(String s) {
                speak(s);
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcPhoneList.setLayoutManager(mLinearLayoutManager);
        mRcPhoneList.setAdapter(phoneListAdapter);
    }

    @Override
    public void setPresenter(PhoneListActivityContract.PhoneListActivityPresenter presenter) {
        mPhoneListActivityPresenter = presenter;
    }

    @Override
    public boolean isAlive() {
        return !isDestroyed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call:
                LogUtil.i(TAG, "拨打电话");
                break;
            case R.id.back:
                LogUtil.i(TAG, "返回上一层");
                finish();
                break;
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTextToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持该语言", Toast.LENGTH_SHORT).show();
                return;
            }
            mTextToSpeech.setPitch(1f);
            mTextToSpeech.setSpeechRate(0.6f);
        }
    }

    private void speak(String text) {
        if (mTextToSpeech != null) {
            if (mTextToSpeech.isSpeaking()) {
                mTextToSpeech.stop();
            }
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTextToSpeech.stop();
        mTextToSpeech.shutdown();
    }

}
