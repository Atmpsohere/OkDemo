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

public class haopingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JavaBean.HBean> list;

    public haopingAdapter(Context context, ArrayList<JavaBean.HBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.haoping_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        JavaBean.HBean hBean = list.get(position);
        Picasso.with(context).load(hBean.getBig_img_url()).into(vh.haoping_image);
        vh.haoping_text.setText(hBean.getLesson_name());
        return convertView;
    }

    class ViewHolder {
        ImageView haoping_image;
        TextView haoping_text;

        public ViewHolder(View v) {
            haoping_image = (ImageView) v.findViewById(R.id.haoping_image);
            haoping_text = (TextView) v.findViewById(R.id.haoping_text);
        }
    }
}
