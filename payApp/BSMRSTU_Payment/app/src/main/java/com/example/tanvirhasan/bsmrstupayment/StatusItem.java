package com.example.tanvirhasan.bsmrstupayment;

/**
 * Created by tanvir hasan on 4/4/2016.
 */
public class StatusItem {

    String _tnx,_dept,__year,_semister,_amount,_dateTime;

    public StatusItem() {
    }

    public StatusItem(String _tnx, String _dept, String __year, String _semister, String _amount) {
        this._tnx = _tnx;
        this._dept = _dept;
        this.__year = __year;
        this._semister = _semister;
        this._amount = _amount;
        this._dateTime = _dateTime;
    }

    public String get_tnx() {

        return _tnx;
    }

    public void set_tnx(String _tnx) {
        this._tnx = _tnx;
    }

    public String get_dept() {
        return _dept;
    }

    public void set_dept(String _dept) {
        this._dept = _dept;
    }

    public String get__year() {
        return __year;
    }

    public void set__year(String __year) {
        this.__year = __year;
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

    public String get_dateTime() {
        return _dateTime;
    }

    public void set_dateTime(String _dateTime) {
        this._dateTime = _dateTime;
    }
}
