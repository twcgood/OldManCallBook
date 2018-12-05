package com.tangwenchao.oldmancallbook.contracts;

import com.tangwenchao.oldmancallbook.base.BasePresenter;
import com.tangwenchao.oldmancallbook.base.BaseView;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 14:26
 * @描述 TODO
 */
public interface MainActivityContract {

    interface MainActivityPresenter extends BasePresenter {

    }

    interface MainActivityView extends BaseView<MainActivityPresenter> {
        void setDate();
    }

}
