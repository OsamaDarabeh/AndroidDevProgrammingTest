package com.osdar.androiddevprogrammingtest.features.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.osdar.androiddevprogrammingtest.R;
import com.osdar.androiddevprogrammingtest.features.home.models.Item;
import com.osdar.androiddevprogrammingtest.features.home.models.SupItem;

import java.util.List;

public class ItemExpandableAdapter extends BaseExpandableListAdapter {


    private List<Item> expandableListItem;

    ItemExpandableAdapter(List<Item> expandableListItem) {
        this.expandableListItem = expandableListItem;
    }

    @Override
    public SupItem getChild(int listPosition, int expandedListPosition) {
        return this.expandableListItem.get(listPosition).getSupItems().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        SupItem expandedListText = getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, null);
        }
        TextView expandedListTextView = convertView
                .findViewById(R.id.childItem_tv_title);
        expandedListTextView.setText(expandedListText.getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return expandableListItem.get(listPosition).getSupItems().size();
    }


    @Override
    public Item getGroup(int listPosition) {
        return this.expandableListItem.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListItem.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Item listTitle = getGroup(listPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, null);
        }
        TextView listTitleTextView = convertView
                .findViewById(R.id.item_tv_title);
        listTitleTextView.setText(listTitle.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
