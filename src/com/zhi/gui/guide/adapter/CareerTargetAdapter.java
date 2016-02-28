package com.zhi.gui.guide.adapter;

import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.data.CareerTarget;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CareerTargetAdapter extends BaseAdapter {

    private List<CareerTarget> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public CareerTargetAdapter(Context context, List<CareerTarget> list) {
        mContext = context;
        setContent(list);
        mInflater = ((LayoutInflater) mContext.getSystemService(Service.LAYOUT_INFLATER_SERVICE));
    }

    public void setContent(List<CareerTarget> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.career_target_list_item, null);
            viewHolder.index = (TextView) convertView.findViewById(R.id.index);
            viewHolder.industry = (TextView) convertView.findViewById(R.id.industry);
            viewHolder.percentage = (TextView) convertView.findViewById(R.id.percentage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CareerTarget careerTarget = (CareerTarget) getItem(i);
        viewHolder.index.setText(String.valueOf(careerTarget.getIndex()));
        viewHolder.industry.setText(careerTarget.getIndustry());
        viewHolder.percentage.setText(String.valueOf(careerTarget.getPercentage()));

        return convertView;
    }

    @Override
    public Object getItem(int i) {
        return mList == null ? 0 : mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        TextView index;
        TextView industry;
        TextView percentage;
    }
}