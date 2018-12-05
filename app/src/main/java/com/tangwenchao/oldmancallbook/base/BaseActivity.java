package com.tangwenchao.oldmancallbook.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/10/12 11:30
 * @描述 父级activity,所有activity共用方法及属性写在这里
 */
public abstract class BaseActivity extends Activity {

    private final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
    }

    /**
     * 设置布局
     * @return
     */
    public abstract int initLayout();

    /**
     * 初始化布局
     */
    public abstract  void initView();

    /**
     * 初如化数据
     */
    public abstract void initData();

    /**
     * 用户点击返回键时的操作
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * activity销毁时的操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 返回当时activity名字
     * @return
     */
    private String getTAG() {
        return  this.TAG;
    }

}
