package com.example.tanvirhasan.bsmrstupayment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanvir hasan on 4/4/2016.
 */
public class StatusListViewAdapter extends ArrayAdapter<StatusItem> {

    List list=new ArrayList();
    @Override
    public StatusItem getItem(int position) {
        return (StatusItem) list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void add( StatusItem object) {
        super.add(object);
        list.add(object);
    }

    public StatusListViewAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ConductHolder holder;

        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.custom_row,parent,false);

            holder=new ConductHolder();
            holder.amount=(TextView)row.findViewById(R.id.amountTv);

            holder.tnx_tv=(TextView)row.findViewById(R.id.tnxidTv);
            holder.year=(TextView)row.findViewById(R.id.yearTv);
            holder.semi=(TextView)row.findViewById(R.id.semisterTv);
            row.setTag(holder);
        }
        else{
            holder=(ConductHolder)row.getTag();

        }

        StatusItem statusItem= (StatusItem) this.getItem(position);

        holder.tnx_tv.setText(statusItem.get_tnx());
        holder.year.setText(statusItem.get__year());
        holder.semi.setText(statusItem.get_semister());
        holder.amount.setText(statusItem.get_amount());


        return row;
    }

    static class ConductHolder{
        TextView tnx_tv,year,semi,amount,date;
    }
}
