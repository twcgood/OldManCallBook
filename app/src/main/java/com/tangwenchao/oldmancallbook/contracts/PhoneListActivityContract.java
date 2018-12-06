package com.tangwenchao.oldmancallbook.contracts;

import com.tangwenchao.oldmancallbook.adapters.PhoneListAdapter;
import com.tangwenchao.oldmancallbook.base.BasePresenter;
import com.tangwenchao.oldmancallbook.base.BaseView;

import java.util.ArrayList;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 14:26
 * @描述 TODO
 */
public interface PhoneListActivityContract {

    interface PhoneListActivityPresenter extends BasePresenter {
        void getPhoneList();

        void callPhone(PhoneListAdapter adapter);
    }

    interface PhoneListActivityView extends BaseView<PhoneListActivityPresenter> {
        void setPhoneList(ArrayList<String> phoneList);
    }

}
