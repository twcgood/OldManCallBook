package com.tangwenchao.oldmancallbook.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tangwenchao.oldmancallbook.R;
import com.tangwenchao.oldmancallbook.adapters.PhoneListAdapter;
import com.tangwenchao.oldmancallbook.base.BaseActivity;
import com.tangwenchao.oldmancallbook.contracts.PhoneListActivityContract;
import com.tangwenchao.oldmancallbook.dialogs.AddPhoneNumberDialog;
import com.tangwenchao.oldmancallbook.interfaces.Delegte;
import com.tangwenchao.oldmancallbook.presenterImpls.PhoneListActivityPresenterImpl;
import com.tangwenchao.oldmancallbook.utils.LogUtil;
import com.tangwenchao.oldmancallbook.utils.TextToSpeechUtil;

import java.util.ArrayList;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 16:37
 * @描述 TODO
 */
public class PhoneListActivity extends BaseActivity implements PhoneListActivityContract.PhoneListActivityView, View.OnClickListener {

    private static final String TAG = "PhoneListActivity";
    private PhoneListActivityContract.PhoneListActivityPresenter mPhoneListActivityPresenter;
    private PhoneListActivityPresenterImpl mPhoneListActivityPresenterImpl;
    private RecyclerView mRcPhoneList;
    private TextView mCall;
    private TextView mBack;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView mAddPhoneNumber;

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
        mCall = findViewById(R.id.call);
        mBack = findViewById(R.id.back);
        mRcPhoneList = findViewById(R.id.phoneList);
        mAddPhoneNumber = findViewById(R.id.addPhoneNumber);
    }

    @Override
    public void initData() {
        mPhoneListActivityPresenterImpl = new PhoneListActivityPresenterImpl(this, this);
        mPhoneListActivityPresenterImpl.getPhoneList();
        mCall.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mAddPhoneNumber.setOnClickListener(this);
    }

    @Override
    public void setPhoneList(final ArrayList<String> phoneList) {
        PhoneListAdapter phoneListAdapter = new PhoneListAdapter(this, phoneList, mRcPhoneList, new Delegte() {
            @Override
            public void exec(String s) {
                TextToSpeechUtil.getInstance().speak(s);
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
                mPhoneListActivityPresenterImpl.callPhone((PhoneListAdapter) mRcPhoneList.getAdapter());
                break;
            case R.id.back:
                LogUtil.i(TAG, "返回上一层");
                finish();
                break;
            case R.id.addPhoneNumber:
                LogUtil.i(TAG, "添加联系人");
                addPhoneNumber();
                break;
        }
    }

    /**
     * 打开添加联系人弹窗 添加联系人
     */
    private void addPhoneNumber() {
        AddPhoneNumberDialog addPhoneNumberDialog = new AddPhoneNumberDialog(this);
        addPhoneNumberDialog.show();
    }

}
