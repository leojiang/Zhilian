package com.zhi.gui.guide.adapter;

import java.util.List;

import com.zhi.gui.guide.R;
import com.zhi.gui.guide.data.InternshipBrief;
import com.zhi.gui.guide.data.InternshipFull;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InternshipFullAdapter extends BaseAdapter {

    private List<InternshipFull> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public InternshipFullAdapter(Context context, List<InternshipFull> list) {
        mContext = context;
        setContent(list);
        mInflater = ((LayoutInflater) mContext.getSystemService(Service.LAYOUT_INFLATER_SERVICE));
    }

    public void setContent(List<InternshipFull> list) {
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
            convertView = mInflater.inflate(R.layout.internship_brief_list_item, null);
            viewHolder.companyName = (TextView) convertView.findViewById(R.id.companyName);
            viewHolder.jobTitle = (TextView) convertView.findViewById(R.id.jobTitle);
            viewHolder.location = (TextView) convertView.findViewById(R.id.location);
            viewHolder.baseCompetence = (TextView) convertView.findViewById(R.id.baseCompetence);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InternshipFull internship = (InternshipFull) getItem(i);
        viewHolder.companyName.setText(internship.getCompanyName());
        viewHolder.jobTitle.setText(internship.getJobTitle());
        viewHolder.location.setText(internship.getLocation());
        viewHolder.baseCompetence.setText(String.valueOf(internship.getBaseCompetence()));

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
        TextView companyName;
        TextView jobTitle;
        TextView location;
        TextView baseCompetence;
    }
}