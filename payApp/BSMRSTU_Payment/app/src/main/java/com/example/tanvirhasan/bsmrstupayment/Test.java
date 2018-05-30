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
 * Created by tanvir hasan on 4/5/2016.
 */
public class Test extends ArrayAdapter <PayCodeItem>{
    List list=new ArrayList();
    public Test(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(PayCodeItem object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public PayCodeItem getItem(int position) {
        return  (PayCodeItem)list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ConductHolder holder;

        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.custom_paycode_row,parent,false);

            holder=new ConductHolder();
            holder.dept= (TextView) row.findViewById(R.id.deptCode);
            holder.year= (TextView) row.findViewById(R.id.yearTvCode);
            holder.semister= (TextView) row.findViewById(R.id.semisterTvCode);
            holder.amount= (TextView) row.findViewById(R.id.amountTvCode);
            holder.paycodetv= (TextView) row.findViewById(R.id.paycodeTvCode);

            row.setTag(holder);
        }
        else{
            holder=(ConductHolder)row.getTag();

        }

        PayCodeItem singleFood= (PayCodeItem) this.getItem(position);



        holder.dept.setText(singleFood.get_dept());
        holder.year.setText(singleFood.get_year());
        holder.semister.setText(singleFood.get_semister());
        holder.amount.setText(singleFood.get_amount());
        holder.paycodetv.setText(singleFood.get_paycode());

        return row;
    }
    static class ConductHolder{
        TextView dept,year,semister,amount, paycodetv;

    }
}
