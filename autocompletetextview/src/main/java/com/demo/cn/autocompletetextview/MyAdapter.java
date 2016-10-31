package com.demo.cn.autocompletetextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xll on 2016/10/8.
 */

public class MyAdapter extends BaseAdapter implements Filterable{
    private List<String> list;
    private Context context;

    public MyAdapter( Context context, List<String> list) {
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return mOriginalValues.size();
    }

    @Override
    public Object getItem(int i) {
        return mOriginalValues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_autocompletetextview, parent,false);
             holder=new Viewholder();
            holder.tv= (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }
        else{
            holder= (Viewholder) convertView.getTag();
        }
        holder.tv.setText(mOriginalValues.get(position));
        return  convertView;
    }
    class Viewholder{
        TextView tv;
    }
    @Override
    public Filter getFilter() {
        return new MyFilter();
    }
    private List<String> mOriginalValues=new ArrayList<String>();
    private final Object mLock = new Object();
    //自定义过滤器
    private class MyFilter extends Filter
    {
        //得到过滤结果
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            int count = list.size();
            ArrayList<String> values = new ArrayList<String>();
            for(int i = 0;i < count;i++)
            {
                String value = list.get(i);
                //自定义匹配规则
                if(value != null && constraint != null && value.contains(constraint))
                {
                    values.add(value);
                }
            }
            results.values = values;
            results.count = values.size();
            return results;
        }
        //发布过滤结果
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            //把搜索结果赋值给mObject这样每次输入字符串的时候就不必
            //从所有的字符串中查找，从而提高了效率
            mOriginalValues.clear();
            mOriginalValues.addAll((List<String>)results.values);
            if(results.count > 0)
            {
                notifyDataSetChanged();
            }
            else
            {
                notifyDataSetInvalidated();
            }
        }

    }
}
