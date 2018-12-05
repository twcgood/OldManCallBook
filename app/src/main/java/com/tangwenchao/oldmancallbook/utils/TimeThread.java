package com.tangwenchao.oldmancallbook.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/4 15:11
 * @描述 TODO
 */
public class TimeThread extends Thread {

    private static final int msgKey = 0x1;
    private TextView tv;
    private TimeThreadHandler mTimeThreadHandler;

    public TimeThread(TextView tv) {
        this.tv = tv;
        mTimeThreadHandler = new TimeThreadHandler(tv);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Message msg = new Message();
                msg.what = msgKey;
                mTimeThreadHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    /**
     * 获取今天星期几
     *
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }

    static class TimeThreadHandler extends Handler {

        private TextView tv;

        public TimeThreadHandler(TextView tv) {
            this.tv = tv;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey:

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String date = sdf.format(new Date());

                    tv.setText(date + " " + getWeek());

                    break;

                default:
                    break;
            }
        }
    }

}
