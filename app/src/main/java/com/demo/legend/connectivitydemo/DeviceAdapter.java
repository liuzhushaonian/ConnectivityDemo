package com.demo.legend.connectivitydemo;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by legend on 2018/3/21.
 */

public class DeviceAdapter extends BaseAdapter<DeviceAdapter.ViewHolder> {

    List<BluetoothDevice> deviceList;
    ItemClickListener listener;

    public void setDeviceList(List<BluetoothDevice> deviceList) {
        this.deviceList = deviceList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.device_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);

        viewHolder.view.setOnClickListener(v -> {
            int position=viewHolder.getAdapterPosition();
            BluetoothDevice device=deviceList.get(position);
            SocketManager.getSocketManager().makeClientSocket(device);
            if (listener!=null){
                listener.onClickListener(viewHolder.view);
            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (this.deviceList==null){
            return;
        }

        BluetoothDevice device=this.deviceList.get(position);
        holder.textView.setText(device.getName());

    }

    @Override
    public int getItemCount() {

        if (this.deviceList!=null){
            return this.deviceList.size();
        }

        return super.getItemCount();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {

        View view;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            view=itemView;
            textView=itemView.findViewById(R.id.name);
        }
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }
}
