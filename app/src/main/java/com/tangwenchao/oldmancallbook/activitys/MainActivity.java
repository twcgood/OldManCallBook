package com.tangwenchao.oldmancallbook.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangwenchao.oldmancallbook.R;
import com.tangwenchao.oldmancallbook.base.BaseActivity;
import com.tangwenchao.oldmancallbook.contracts.MainActivityContract;
import com.tangwenchao.oldmancallbook.presenterImpls.MainActivityPresenterImpl;
import com.tangwenchao.oldmancallbook.utils.LogUtil;
import com.tangwenchao.oldmancallbook.utils.PermissionsUtil;
import com.tangwenchao.oldmancallbook.utils.TimeThread;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityView, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private MainActivityContract.MainActivityPresenter mMainActivityPresenter;
    private MainActivityPresenterImpl mMainActivityPresenterImpl;
    private TextView mTime;
    private ImageView mPhone;

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
        mPhone = findViewById(R.id.phone);
    }

    @Override
    public void initData() {
        PermissionsUtil.requestPerms(this);
        mPhone.setOnClickListener(this);
        mMainActivityPresenterImpl = new MainActivityPresenterImpl(this, this);
        setDate();
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
        TimeThread timeThread = new TimeThread(mTime);
        timeThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone:
                LogUtil.i(TAG, "打开电话列表");
                Intent intent = new Intent(MainActivity.this, PhoneListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
