package com.example.tanvirhasan.bsmrstupayment;

/**
 * Created by tanvir hasan on 4/4/2016.
 */
public class PayCodeItem {
    private String _dept,_year,_semister,_amount,_paycode;
    public PayCodeItem(String _paycode, String _dept, String _year, String _semister, String _amount) {
        this._paycode = _paycode;
        this._dept = _dept;
        this._year = _year;
        this._semister = _semister;
        this._amount = _amount;
    }



    public PayCodeItem() {
    }

    public String get_dept() {
        return _dept;
    }

    public void set_dept(String _dept) {
        this._dept = _dept;
    }

    public String get_year() {
        return _year;
    }

    public void set_year(String _year) {
        this._year = _year;
    }

    public String get_semister() {
        return _semister;
    }

    public void set_semister(String _semister) {
        this._semister = _semister;
    }

    public String get_amount() {
        return _amount;
    }

    public void set_amount(String _amount) {
        this._amount = _amount;
    }

    public String get_paycode() {
        return _paycode;
    }

    public void set_paycode(String _paycode) {
        this._paycode = _paycode;
    }
}
