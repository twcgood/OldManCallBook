package com.tangwenchao.oldmancallbook.presenterImpls;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.tangwenchao.oldmancallbook.activitys.PhoneListActivity;
import com.tangwenchao.oldmancallbook.adapters.PhoneListAdapter;
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

    private static final String TAG = "PLAPresenterImpl";
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

    public void callPhone(PhoneListAdapter adapter) {
        int position = adapter.getPosition();
        if (position != -1) {
            ArrayList<String> phoneList = adapter.getPhoneList();
            String s = phoneList.get(position);
            String[] split = s.split(" ");
            Log.i(TAG, "电话号码是：" + split[1]);
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + split[1]);
            intent.setData(data);
            mPlac.get().startActivity(intent);
        }
    }
}
