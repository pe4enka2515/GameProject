package com.samoylov.gameproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.R;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter {
    public interface OnCardClickListener {
        void onCardClick(View view, String name, int pos,String tag);
        void onClickBattle(int id);
    }

    private static OnCardClickListener mListener;
    private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;

    public ExpListAdapter(Context context, ArrayList<ArrayList<String>> groups) {
        this.mContext = context;
        mGroups = groups;

    }

    @Override
    public int getGroupCount() {
        return mGroups.size() - 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        if (isExpanded) {
            //Изменяем что-нибудь, если текущая Group раскрыта
        } else {
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText(mGroups.get(3).get(groupPosition));

        return convertView;
    }

    public void setOnCardClickListener(OnCardClickListener listener) {
        mListener = listener;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groupPosition == 1) {
                    mListener.onCardClick(view, mGroups.get(groupPosition).get(childPosition), groupPosition,null);
                } else
                    Toast.makeText(mContext, "button is pressed", Toast.LENGTH_SHORT).show();
            }
        });
        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));

        Button button = (Button) convertView.findViewById(R.id.buttonChild);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groupPosition == 0) {
                    if (childPosition == 0) {

                        mListener.onCardClick(view, mGroups.get(groupPosition).get(childPosition), groupPosition,"b");
                    }

                }
                if (groupPosition == 1) {
                        mListener.onCardClick(view, mGroups.get(groupPosition).get(childPosition), groupPosition,"b");
                    }
                if (groupPosition==2){
//                    mListener.onCardClick(view,mGroups.get(groupPosition).get(childPosition),groupPosition,"Mob");
                    Toast.makeText(mContext, mGroups.get(4).get(childPosition), Toast.LENGTH_SHORT).show();
                    mListener.onClickBattle(Integer.parseInt(mGroups.get(4).get(childPosition)));
                }else
                    Toast.makeText(mContext, "button is pressed", Toast.LENGTH_SHORT).show();
                }
            });

        return convertView;
        }

        @Override
        public boolean isChildSelectable ( int i, int i1){
            return true;
        }
    }
