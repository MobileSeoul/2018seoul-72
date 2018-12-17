package com.example.user.kculture;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.display.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class NewAdapter extends BaseAdapter implements Filterable{
    private Context ctx;
    private ArrayList<MyData> data = new ArrayList<MyData>();
    private ArrayList<MyData> filteredData = new ArrayList<MyData>();

    private int mCategory = 0;

    private ItemFilter mFilter = new ItemFilter();
    private boolean containsFlag(int flagSet, int flag)
    {
        return (flagSet|flag) == flagSet;
    }

    private void rearrageList()
    {
        filteredData.clear();
        boolean bPlace = containsFlag(mCategory, MyData.FILTER_PLACE);
        boolean bFood = containsFlag(mCategory, MyData.FILTER_FOOD);
        boolean bTradition = containsFlag(mCategory, MyData.FILTER_TRADITION);

        for(MyData e:data)
        {

            if(bPlace && containsFlag(e.getCategory(), MyData.FILTER_PLACE)
                    || bFood && containsFlag(e.getCategory(), MyData.FILTER_FOOD)
                    || bTradition && containsFlag(e.getCategory(), MyData.FILTER_TRADITION))
            {

                filteredData.add(e);
            }
        }


    }

    public void addFilter(int filter) {
        mCategory |= filter;

        rearrageList();
    }


    public void deleteFilter(int filter) {
        mCategory  &= (~filter);
        rearrageList();
    }

    class ItemFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if(constraint == null || constraint.length() == 0)
            {
                results.values = data;
                results.count = data.size();
            }
            else
            {
                ArrayList<MyData> itemList = new ArrayList<MyData>();
                String strFilter = constraint.toString();

                if(strFilter.contains("FOOD"))
                {
                    //Toast.makeText(Main2Activity.this,myData[position].name+"선택",
                    //        Toast.LENGTH_SHORT).show();
                    Log.d("Filter", "FOOD");
                }
                else if(strFilter.contains("PLACE"))
                {
                    Log.d("Filter", "PLACE");
                }
                else if(strFilter.contains("TRAD"))
                {
                    Log.d("Filter", "TRAD");
                }

                results.values = itemList;
                results.count = itemList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<MyData>)results.values;
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

    public NewAdapter(Context ctx, MyData[] data) {
        this.ctx = ctx;
        //this.data. data;
        Collections.addAll(this.data, data);
        Collections.addAll(this.filteredData, data);
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int i) {

        return filteredData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView=inflater.inflate(R.layout.kpop_list,parent,false);
        }
        MyData item = filteredData.get(i);
        ImageView image=(ImageView) convertView.findViewById(R.id.image);

        image.setImageResource(item.pic);
        TextView text=(TextView) convertView.findViewById(R.id.text);
        text.setText(item.name);

        TextView exptext=(TextView) convertView.findViewById(R.id.textView2);
        exptext.setText(item.exp);

        return convertView;

    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
