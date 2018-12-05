package com.tangwenchao.oldmancallbook.presenterImpls;

import android.Manifest;

import com.tangwenchao.oldmancallbook.activitys.MainActivity;
import com.tangwenchao.oldmancallbook.contracts.MainActivityContract;
import com.tangwenchao.oldmancallbook.utils.LogUtil;

import java.lang.ref.WeakReference;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 14:35
 * @描述 TODO
 */
public class MainActivityPresenterImpl implements MainActivityContract.MainActivityPresenter {

    protected WeakReference<MainActivityContract.MainActivityView> mView;
    protected WeakReference<MainActivity> mMa;

    public MainActivityPresenterImpl(MainActivityContract.MainActivityView view, MainActivity ma) {
        mView = new WeakReference<MainActivityContract.MainActivityView>(view);
        mMa = new WeakReference<MainActivity>(ma);
        view.setPresenter(this);
    }

}
