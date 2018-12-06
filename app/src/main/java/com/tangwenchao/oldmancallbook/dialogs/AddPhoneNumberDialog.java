package com.tangwenchao.oldmancallbook.dialogs;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tangwenchao.oldmancallbook.R;
import com.tangwenchao.oldmancallbook.utils.TextToSpeechUtil;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/6 10:59
 * @描述 TODO
 */
public class AddPhoneNumberDialog extends Dialog {

    private Context ctx;

    public AddPhoneNumberDialog(Context context) {
        super(context);
        ctx = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDailog();
    }

    private void initDailog() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.add_phone_number_dialog, null);
        setContentView(view);
        TextView cancel = view.findViewById(R.id.cancel);
        TextView add = view.findViewById(R.id.add);
        final EditText name = view.findViewById(R.id.name);
        final EditText phoneNumber = view.findViewById(R.id.phoneNumber);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact(name.getText().toString().trim(), phoneNumber.getText().toString().trim());
            }
        });

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = ctx.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 0.98);
        dialogWindow.setAttributes(lp);

    }

    private void addContact(String name, String phoneNumber) {
        ContentValues values = new ContentValues();

        Uri rawContactUri = ctx.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();

        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        // 内容类型
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        ctx.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 联系人的电话号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        // 电话类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        ctx.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        // 联系人的Email地址
        values.put(ContactsContract.CommonDataKinds.Email.DATA, "zhangphil@xxx.com");
        // 电子邮件的类型
        values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        // 向联系人Email URI添加Email数据
        ctx.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        Toast.makeText(ctx, "联系人数据添加成功", Toast.LENGTH_SHORT).show();

        TextToSpeechUtil.getInstance().speak("联系人" + name + "添加成功");

        dismiss();
    }
}
