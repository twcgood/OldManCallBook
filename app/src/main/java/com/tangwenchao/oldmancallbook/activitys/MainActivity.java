package com.tangwenchao.oldmancallbook.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangwenchao.oldmancallbook.R;
import com.tangwenchao.oldmancallbook.base.BaseActivity;
import com.tangwenchao.oldmancallbook.contracts.MainActivityContract;
import com.tangwenchao.oldmancallbook.dialogs.DeleteSMSDialog;
import com.tangwenchao.oldmancallbook.presenterImpls.MainActivityPresenterImpl;
import com.tangwenchao.oldmancallbook.utils.LogUtil;
import com.tangwenchao.oldmancallbook.utils.PermissionsUtil;
import com.tangwenchao.oldmancallbook.utils.TextToSpeechUtil;
import com.tangwenchao.oldmancallbook.utils.TimeThread;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityView, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private MainActivityContract.MainActivityPresenter mMainActivityPresenter;
    private MainActivityPresenterImpl mMainActivityPresenterImpl;
    private TextView mTime;
    private TextView mWeek;
    private ImageView mPhone;
    private ImageView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mTime = findViewById(R.id.time);
        mWeek = findViewById(R.id.week);
        mPhone = findViewById(R.id.phone);
        mEmail = findViewById(R.id.email);
    }

    @Override
    public void initData() {
        PermissionsUtil.requestPerms(this);
        mPhone.setOnClickListener(this);
        mEmail.setOnClickListener(this);
        mMainActivityPresenterImpl = new MainActivityPresenterImpl(this, this);
        setDate();
        mTime.setOnClickListener(this);
        mWeek.setOnClickListener(this);
    }

    @Override
    public void setPresenter(MainActivityContract.MainActivityPresenter presenter) {
        mMainActivityPresenter = presenter;
    }

    @Override
    public boolean isAlive() {
        return !isDestroyed();
    }

    @Override
    public void setDate() {
        TimeThread timeThread = new TimeThread(mTime, mWeek);
        timeThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone:
                LogUtil.i(TAG, "打开电话列表");
                Intent intent = new Intent(this, PhoneListActivity.class);
                startActivity(intent);
                break;
            case R.id.email:
                LogUtil.i(TAG, "打开短信");
                DeleteSMSDialog deleteSMSDialog = new DeleteSMSDialog(this);
                deleteSMSDialog.show();
                break;
            case R.id.time:
            case R.id.week:
                TextToSpeechUtil.getInstance().speak(mWeek.getText().toString().trim() + " " + mTime.getText().toString().trim());
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 重写返回键操作 防止老人退出程序
     */
    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TextToSpeechUtil.getInstance().clear();
    }
}
