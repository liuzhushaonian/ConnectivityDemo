package com.demo.legend.connectivitydemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by legend on 2018/3/21.
 */

public class MessageAdapter extends BaseAdapter<MessageAdapter.ViewHolder> {

    List<Message> msgList;

    public void setMsgList(List<Message> msgList) {
        this.msgList = msgList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.message_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (this.msgList==null){
            return;
        }

        Message message=msgList.get(position);

        switch (message.getType()){
            case 1:
                holder.left_layout.setVisibility(View.VISIBLE);
                holder.right_layout.setVisibility(View.GONE);
                holder.left_text.setText(message.getMsg());
                break;
            case 2:
                holder.right_layout.setVisibility(View.VISIBLE);
                holder.left_layout.setVisibility(View.GONE);
                holder.right_text.setText(message.getMsg());

                break;
        }

    }

    @Override
    public int getItemCount() {

        if (this.msgList!=null){
            return msgList.size();
        }

        return super.getItemCount();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {


        View view;

        LinearLayout left_layout,right_layout;

        TextView left_text,right_text;

        ImageView left_tou,right_tou;

        public ViewHolder(View itemView) {
            super(itemView);

            this.left_layout=itemView.findViewById(R.id.left_layout);
            this.right_layout=itemView.findViewById(R.id.right_layout);
            this.left_text=itemView.findViewById(R.id.left_msg);
            this.right_text=itemView.findViewById(R.id.right_msg);
            this.left_tou=itemView.findViewById(R.id.left_tou);
            this.right_tou=itemView.findViewById(R.id.right_tou);

        }
    }

    public void addMsg(Message message){
        if (this.msgList==null){
            this.msgList=new ArrayList<>();
        }

        this.msgList.add(message);
        notifyDataSetChanged();
    }
}
