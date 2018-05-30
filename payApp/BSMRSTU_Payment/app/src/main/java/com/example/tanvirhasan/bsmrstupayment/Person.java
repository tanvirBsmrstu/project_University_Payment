package com.example.tanvirhasan.bsmrstupayment;

/**
 * Created by tanvir hasan on 2/16/2016.
 */
public class Person {   ///for store single data

   private String _name , _id , _pass , _mob , _email , _method , _toUrl,_data;

    public Person(String _name, String _id, String _pass, String _mob, String _email, String _method, String _toUrl,String _data) {
        this._name = _name;
        this._id = _id;
        this._pass = _pass;
        this._mob = _mob;
        this._email = _email;
        this._method = _method;
        this._toUrl = _toUrl;
        this._data = _data;
    }

    public Person() {
        _name=null;_pass=null;_mob=null;_email=null;_id=null;_method=null;_toUrl=null;
    }

    public String get_method() {
        return _method;
    }

    public void set_method(String _method) {
        this._method = _method;
    }


    public String get_toUrl() {
        return _toUrl;
    }

    public void set_toUrl(String _toUrl) {
        this._toUrl = _toUrl;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String _pass) {
        this._pass = _pass;
    }

    public String get_mob() {
        return _mob;
    }

    public void set_mob(String _mob) {
        this._mob = _mob;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }
}
