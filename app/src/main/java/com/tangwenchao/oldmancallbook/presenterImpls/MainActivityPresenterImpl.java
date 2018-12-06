package com.tangwenchao.oldmancallbook.presenterImpls;

import com.tangwenchao.oldmancallbook.activitys.MainActivity;
import com.tangwenchao.oldmancallbook.contracts.MainActivityContract;

import java.lang.ref.WeakReference;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 14:35
 * @描述 TODO
 */
public class MainActivityPresenterImpl implements MainActivityContract.MainActivityPresenter {

    protected WeakReference<MainActivityContract.MainActivityView> mView;
    protected WeakReference<MainActivity> mMa;

    public MainActivityPresenterImpl(MainActivityContract.MainActivityView view, MainActivity ma) {
        mView = new WeakReference<>(view);
        mMa = new WeakReference<>(ma);
        view.setPresenter(this);
    }

}
