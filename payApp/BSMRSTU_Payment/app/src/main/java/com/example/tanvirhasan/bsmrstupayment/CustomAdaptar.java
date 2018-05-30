package com.example.tanvirhasan.bsmrstupayment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tanvir hasan on 1/29/2016.
 */
public class CustomAdaptar extends ArrayAdapter<PayCodeItem > {

    public CustomAdaptar(Context context,List<PayCodeItem> list) {
        super(context,R.layout.custom_paycode_row ,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= LayoutInflater.from(getContext());

        View customView = layoutInflater.inflate(R.layout.custom_paycode_row, parent, false);
        PayCodeItem payCodeItem= getItem(position);

        TextView dept= (TextView) customView.findViewById(R.id.deptCode);
        TextView year= (TextView) customView.findViewById(R.id.yearTvCode);
        TextView semister= (TextView) customView.findViewById(R.id.semisterTvCode);
        TextView amount= (TextView) customView.findViewById(R.id.amountTvCode);
        TextView paycodetv= (TextView) customView.findViewById(R.id.paycodeTvCode);

        dept.setText(payCodeItem.get_dept());
        year.setText(payCodeItem.get_year());
        semister.setText(payCodeItem.get_semister());
        amount.setText(payCodeItem.get_amount());
        paycodetv.setText(payCodeItem.get_paycode());

        return customView;
    }
}
