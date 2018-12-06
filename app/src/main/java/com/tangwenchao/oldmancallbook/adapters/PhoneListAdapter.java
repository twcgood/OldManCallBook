package com.tangwenchao.oldmancallbook.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangwenchao.oldmancallbook.R;
import com.tangwenchao.oldmancallbook.interfaces.Delegte;
import com.tangwenchao.oldmancallbook.utils.LogUtil;

import java.util.ArrayList;

/**
 * @创建者 tangwenchao
 * @创建时间 2018/12/5 13:24
 * @描述 TODO
 */
public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneListViewHolder> {

    private static final String TAG = "PhoneListAdapter";
    private Context ctx;
    private ArrayList<String> phoneList;
    private int mPosition = -1;
    private Delegte mDelegte;
    private RecyclerView mRecyclerView;

    public PhoneListAdapter(Context ctx, ArrayList<String> phoneList, RecyclerView rcPhoneList, Delegte delegte) {
        this.ctx = ctx;
        this.phoneList = phoneList;
        this.mDelegte = delegte;
        this.mRecyclerView = rcPhoneList;
    }

    public void update(ArrayList<String> phoneList) {
        this.phoneList = phoneList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhoneListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_list_item, parent, false);
        PhoneListViewHolder phoneListViewHolder = new PhoneListViewHolder(view);
        return phoneListViewHolder;
    }

    @Override
    public void onBindViewHolder(final PhoneListViewHolder holder, final int position) {
        final String phone = phoneList.get(position);
        holder.tv.setText(phone);
        if (mPosition != position) {
            holder.itemView.setBackgroundColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i(TAG, "phoneList item 被点击了");

                if (mPosition == position) {
                    mDelegte.exec(phone);
                    return;
                }
                if (mPosition != -1) {
                    RecyclerView.ViewHolder viewHolderForAdapterPosition = mRecyclerView.findViewHolderForAdapterPosition(mPosition);
                    if (viewHolderForAdapterPosition != null) {
                        viewHolderForAdapterPosition.itemView.setBackgroundColor(Color.WHITE);
                    }
                }
                mPosition = position;
                holder.itemView.setBackgroundColor(Color.GREEN);
                mDelegte.exec(phone);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    public int getPosition() {
        return mPosition;
    }

    public ArrayList<String> getPhoneList() {
        return phoneList;
    }

    static class PhoneListViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public PhoneListViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_phone);
        }
    }
}
