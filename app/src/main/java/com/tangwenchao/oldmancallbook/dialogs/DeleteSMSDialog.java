package com.tangwenchao.oldmancallbook.dialogs;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tangwenchao.oldmancallbook.R;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/6 10:59
 * @描述 TODO
 */
public class DeleteSMSDialog extends Dialog {

    private Context ctx;
    private Uri SMS_ALL = Uri.parse("content://sms/");  //全部短信

    public DeleteSMSDialog(Context context) {
        super(context);
        ctx = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDailog();
    }

    private void initDailog() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.delete_sms_dialog, null);
        setContentView(view);
        TextView cancel = view.findViewById(R.id.cancel);
        final TextView delete = view.findViewById(R.id.delete);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSMS();
            }
        });

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = ctx.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 0.98);
        dialogWindow.setAttributes(lp);

    }

    /**
     * 删除短信
     */
    public void deleteSMS() {
        ContentResolver cr = ctx.getContentResolver();  //内容解析器(数据共享)——读取短信内容
        Cursor cur = cr.query(SMS_ALL, new String[]{"_id", "thread_id"}, null, null, null);  //获取短信内容
        if (cur != null && cur.moveToFirst()) {
            do {
                long threadId = cur.getLong(1);
                cr.delete(Uri.parse("content://sms/conversations/" + threadId), null, null);
            } while (cur.moveToNext());
        } else {
            cur.close();
            dismiss();
        }
    }
}
