package com.example.hzg.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hzg on 2016/7/20.
 */
public class MyAdapter extends BaseAdapter {

    //布局装载器，将布局装到里面
    private LayoutInflater layoutInflater;
    //映射数据
    private List<ItemBean> itemBeans;

    private long mSumTime;

    public MyAdapter(Context context, List<ItemBean> dataList) {
        layoutInflater = LayoutInflater.from(context);
        this.itemBeans = dataList;//将dataList的数据映射到itemBeans来
    }

    @Override
    public int getCount() {
        return itemBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return itemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//*************没有利用到ListView的缓存机制，对资源的极大浪费***时间：346677951***********************************************
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
//        long start=System.nanoTime();//记录开始的系统纳秒时间
//
//        View view = layoutInflater.inflate(R.layout.item, null);
//        //实例化控件
//        ImageView itemImage = (ImageView) view.findViewById(R.id.imageView);
//        TextView itemTitle = (TextView) view.findViewById(R.id.tv_title);
//        TextView itemContent = (TextView) view.findViewById(R.id.tv_content);
//        //取出itemBean
//        ItemBean bean = itemBeans.get(position);
//        //设置控件的数据
//        itemImage.setImageResource(bean.itemImageResId);
//        itemTitle.setText(bean.itemTitle);
//        itemContent.setText(bean.itemContent);
//
//        long end=System.nanoTime();//记录结束的系统纳秒时间
//        long oneTime=end-start;//一个的时间
//        mSumTime+=oneTime;//累加，达到总的时间
//        Log.d("Main", String.valueOf(mSumTime));
//        return view;
//*************没有利用到ListView的缓存机制，对资源的极大浪费**************************************************

//*************普通式,利用这种方式，可以利用到listView的缓存特性，但是findViewById还是会消耗资源*****时间：13261194*********************************************
//        long start=System.nanoTime();//记录开始的系统纳秒时间
//        if (convertView == null) {
//            convertView = layoutInflater.inflate(R.layout.item, null);
//        }
//        //实例化控件
//        ImageView itemImage = (ImageView) convertView.findViewById(R.id.imageView);
//        TextView itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
//        TextView itemContent = (TextView) convertView.findViewById(R.id.tv_content);
//        //取出itemBean
//        ItemBean bean = itemBeans.get(position);
//        //设置控件的数据
//        itemImage.setImageResource(bean.itemImageResId);
//        itemTitle.setText(bean.itemTitle);
//        itemContent.setText(bean.itemContent);
//        long end=System.nanoTime();//记录结束的系统纳秒时间
//        long oneTime=end-start;//一个的时间
//        mSumTime+=oneTime;//累加，达到总的时间
//        Log.d("Main", String.valueOf(mSumTime));
//        return convertView;
//*************普通式,利用这种方式，可以利用到listView的缓存特性，但是findViewById还是会消耗资源**************************************************

//**********通过viewHolder，避免了多次使用findViewById**********时间：*******************************************
        long start=System.nanoTime();//记录开始的系统纳秒时间
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item, null);
            //实例化控件
            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.itemContent = (TextView) convertView.findViewById(R.id.tv_content);
//            将convertView与viewHolder关联起来
            convertView.setTag(viewHolder);
        } else {
            //不是通过findViewById找的,通过tag来取出ViewHolder
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //取出itemBean
        ItemBean bean = itemBeans.get(position);
        //给ViewHodler中的空间设置控件的数据
        viewHolder.itemImage.setImageResource(bean.itemImageResId);
        viewHolder.itemTitle.setText(bean.itemTitle);
        viewHolder.itemContent.setText(bean.itemContent);
        long end=System.nanoTime();//记录结束的系统纳秒时间
        long oneTime=end-start;//一个的时间
        mSumTime+=oneTime;//累加，达到总的时间
        Log.d("Main", String.valueOf(mSumTime));
        return convertView;
    }

    // ViewHolder用于缓存控件
    class ViewHolder{
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemContent;
    }
}
