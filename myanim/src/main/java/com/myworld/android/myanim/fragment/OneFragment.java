package com.myworld.android.myanim.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.myworld.android.myanim.Bean.JavaBean;
import com.myworld.android.myanim.MoreActivity;
import com.myworld.android.myanim.R;
import com.myworld.android.myanim.Utils.HttpUtils;
import com.myworld.android.myanim.Utils.JsonUtils;
import com.myworld.android.myanim.adapter.haopingAdapter;
import com.myworld.android.myanim.adapter.mingshiAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2017/12/4.
 */

public class OneFragment extends Fragment implements HttpUtils.LoadData {
    private ArrayList<JavaBean.EBean> list1 = new ArrayList<>();
    private ArrayList<JavaBean.LBean> list2 = new ArrayList<>();
    private ArrayList<JavaBean.CBean> mingshiList = new ArrayList<>();
    private ArrayList<JavaBean.HBean> keList = new ArrayList<>();
    private String path = "http://api.dameiketang.com/manager.php?m=Admin&c=Threevesion&a=IndexPageData";
    private View v;
    private MyAdapter adapter2;
    private ArrayList<View> views1 = new ArrayList<>();
    private ArrayList<View> views2 = new ArrayList<>();
    private MyAdapter adapter1;
    private ViewPager viewPager1;
    private ViewPager viewPager2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 111) {
                int index = viewPager1.getCurrentItem();
                index++;
                handler.sendEmptyMessageDelayed(111, 2000);
                viewPager1.setCurrentItem(index);
            } else if (msg.what == 222) {
                int index = viewPager2.getCurrentItem();
                index++;
                handler.sendEmptyMessageDelayed(222, 2000);
                viewPager2.setCurrentItem(index);
            }
        }
    };
    private mingshiAdapter teacherAdapter;
    private haopingAdapter keAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_one, null);
        initView();
        HttpUtils utils = new HttpUtils(getContext(), this);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "6894681b-ad8b-47e4-9f17-1cf07324464c");
        utils.postRequest(path, map);
        return v;
    }

    private void initView() {
        ScrollView scrollView = (ScrollView) v.findViewById(R.id.scrollView);
        viewPager1 = (ViewPager) v.findViewById(R.id.viewPager_frist);
        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter1 = new MyAdapter(views1);
        viewPager1.setAdapter(adapter1);
        viewPager2 = (ViewPager) v.findViewById(R.id.viewPager_shuang);
        adapter2 = new MyAdapter(views2);
        viewPager2.setAdapter(adapter2);
        GridView grid1 = (GridView) v.findViewById(R.id.gridView1);
        teacherAdapter = new mingshiAdapter(mingshiList, getContext());
        grid1.setAdapter(teacherAdapter);
        GridView grid2 = (GridView) v.findViewById(R.id.gridView2);
        keAdapter = new haopingAdapter(getContext(), keList);
        grid2.setAdapter(keAdapter);
        Button dingzhi = (Button) v.findViewById(R.id.dingzhi);

        dingzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoreActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void load(String data) {
        if (data == null) {
            return;
        }
        Log.i("tag", "======" + data);
        //开始解析数据
        JsonUtils ju = new JsonUtils();
        JavaBean bean = ju.getData(data);
        if (bean != null) {
            ArrayList<JavaBean.EBean> beans1 = (ArrayList<JavaBean.EBean>) bean.getE();
            list1.addAll(beans1);
            for (int i = 0; i < list1.size(); i++) {
                ImageView imageView_pager_first = new ImageView(getContext());
                Picasso.with(getContext()).load(list1.get(i).getImgurl()).into(imageView_pager_first);
                views1.add(imageView_pager_first);
            }

            adapter1.notifyDataSetChanged();
            handler.sendEmptyMessage(111);
            ArrayList<JavaBean.LBean> beans2 = (ArrayList<JavaBean.LBean>) bean.getL();
            list2.addAll(beans2);
            for (int i = 0; i < list2.size(); i++) {
                ImageView shuang_view = new ImageView(getContext());
                Picasso.with(getContext()).load(list2.get(i).getImg()).into(shuang_view);
                views2.add(shuang_view);
            }

            adapter2.notifyDataSetChanged();
            handler.sendEmptyMessage(222);
            ArrayList<JavaBean.CBean> beans3 = (ArrayList<JavaBean.CBean>) bean.getC();
            for (int i = 0; i < 4; i++) {
                mingshiList.addAll(beans3);
            }
            teacherAdapter.notifyDataSetChanged();
            for (int i = 0; i < 4; i++) {
                ArrayList<JavaBean.HBean> bean4 = (ArrayList<JavaBean.HBean>) bean.getH();
                keList.addAll(bean4);
                keAdapter.notifyDataSetChanged();
            }

        }

    }

    class MyAdapter extends PagerAdapter {
        private ArrayList<View> imgs;

        public MyAdapter(ArrayList<View> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            if (imgs.size() > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= imgs.size();
            View vs = imgs.get(position);
            //获取父控件
            ViewPager vp = (ViewPager) vs.getParent();
            if (vp != null) {
                vp.removeView(vs);
            }
            container.addView(vs);

            return vs;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}
