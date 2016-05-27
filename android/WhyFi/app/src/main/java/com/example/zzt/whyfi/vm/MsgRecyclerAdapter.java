package com.example.zzt.whyfi.vm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzt.whyfi.BR;
import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.model.Message;

import java.util.List;

/**
 * Created by zzt on 5/27/16.
 * <p/>
 * Usage:
 */
public class MsgRecyclerAdapter extends RecyclerView.Adapter<MsgRecyclerAdapter.BindingHolder> {
    private List<Message> messages;

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public MsgRecyclerAdapter(List<Message> recyclerUsers) {
        this.messages = recyclerUsers;
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Message message = messages.get(position);
        holder.getBinding().setVariable(BR.msg, message);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}

