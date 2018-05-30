package com.example.tanvirhasan.bsmrstupayment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tanvir hasan on 1/29/2016.
 */
public class PaycodeAdaptar extends ArrayAdapter<PayCodeItem > {


    public PaycodeAdaptar(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(PayCodeItem object) {
        super.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= LayoutInflater.from(getContext());

        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);
        PayCodeItem singleFood= getItem(position);


        TextView dept= (TextView) customView.findViewById(R.id.deptCode);
        TextView year= (TextView) customView.findViewById(R.id.yearTvCode);
        TextView semister= (TextView) customView.findViewById(R.id.semisterTvCode);
        TextView amount= (TextView) customView.findViewById(R.id.amountTvCode);
        TextView paycodetv= (TextView) customView.findViewById(R.id.paycodeTvCode);

        dept.setText(singleFood.get_dept());
        year.setText(singleFood.get_year());
        semister.setText(singleFood.get_semister());
        amount.setText(singleFood.get_amount());
        paycodetv.setText(singleFood.get_paycode());


        return customView;
    }
}
