package com.tangwenchao.oldmancallbook.presenterImpls;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.tangwenchao.oldmancallbook.activitys.PhoneListActivity;
import com.tangwenchao.oldmancallbook.contracts.PhoneListActivityContract;
import com.tangwenchao.oldmancallbook.utils.LogUtil;
import com.tangwenchao.oldmancallbook.utils.PhoneUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 14:35
 * @描述 TODO
 */
public class PhoneListActivityPresenterImpl implements PhoneListActivityContract.PhoneListActivityPresenter {

    private static final String TAG = "PhoneListActivityPresenterImpl";
    protected WeakReference<PhoneListActivityContract.PhoneListActivityView> mView;
    protected WeakReference<PhoneListActivity> mPlac;

    public PhoneListActivityPresenterImpl(PhoneListActivityContract.PhoneListActivityView view, PhoneListActivity plac) {
        mView = new WeakReference<>(view);
        mPlac = new WeakReference<>(plac);
        view.setPresenter(this);
    }

    @Override
    public void getPhoneList() {
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = mPlac.get().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameFieldColumnIndex);
            String number = cursor.getString(numberFieldColumnIndex);
            list.add(name + " " + PhoneUtil.trimTelNum(number));
            LogUtil.i(TAG, name + " " + number);
        }
        LogUtil.i(TAG, "cursor count " + cursor.getCount());
        if (cursor.getCount() > 0) {
            mView.get().setPhoneList(list);
        }
    }
}
