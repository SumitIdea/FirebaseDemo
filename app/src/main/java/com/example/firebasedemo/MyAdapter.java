package com.example.firebasedemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Activity obj;
    int res;
    ArrayList<User> arrayList=new ArrayList<>();
    public MyAdapter(Activity obj, int res, ArrayList<User> arrayList)
    {
        this.obj=obj;
        this.res=res;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(obj).inflate(res,parent,false);
        User u=arrayList.get(position);

        TextView tv1=view.findViewById(R.id.tv_nem);
        TextView tv2=view.findViewById(R.id.tv_add);
        TextView tv3=view.findViewById(R.id.tv_contact_num);
        TextView tv4=view.findViewById(R.id.tv_occu);
        TextView tv5=view.findViewById(R.id.tv_pincode);

        tv1.setText(u.getName());
        tv2.setText(u.getAddress());
        tv3.setText(u.getPhone());
        tv4.setText(u.getOccupation());
        tv5.setText(u.getPincode());

        return view;
    }
}
