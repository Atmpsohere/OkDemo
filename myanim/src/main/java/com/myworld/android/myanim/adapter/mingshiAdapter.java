package com.myworld.android.myanim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myworld.android.myanim.Bean.JavaBean;
import com.myworld.android.myanim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/6.
 */

public class mingshiAdapter extends BaseAdapter {
    private ArrayList<JavaBean.CBean> list;
    private Context context;

    public mingshiAdapter(ArrayList<JavaBean.CBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mingshi_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        JavaBean.CBean cBean = list.get(position);
        vh.mingshi_text.setText(cBean.getTeacher_name());
        Picasso.with(context).load(cBean.getTeacher_img()).into(vh.mingshi_image);
        return convertView;

    }

    class ViewHolder {
        ImageView mingshi_image;
        TextView mingshi_text;

        public ViewHolder(View v) {
            mingshi_image = (ImageView) v.findViewById(R.id.mingshi_image);
            mingshi_text = (TextView) v.findViewById(R.id.mingshi_text);
        }
    }
}
