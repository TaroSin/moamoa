package com.javaproject.employinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.name = cell.findViewById(R.id.name);
            viewHolder.title = cell.findViewById(R.id.title);
            viewHolder.nameDetail = cell.findViewById(R.id.name_detail);
            viewHolder.titleDetail = cell.findViewById(R.id.title_detail);
            viewHolder.loclong = cell.findViewById(R.id.loclong);
            viewHolder.locshort = cell.findViewById(R.id.locshort_detail);
            viewHolder.exp = cell.findViewById(R.id.exp);
            viewHolder.work = cell.findViewById(R.id.work);
            viewHolder.expDetail = cell.findViewById(R.id.exp_detail);
            viewHolder.workDetail = cell.findViewById(R.id.work_detail);
            viewHolder.date = cell.findViewById(R.id.date);
            viewHolder.edu = cell.findViewById(R.id.edu);
            viewHolder.eduDetail = cell.findViewById(R.id.edu_detail);
            viewHolder.link = cell.findViewById(R.id.link);
            viewHolder.etc = cell.findViewById(R.id.etc_detail);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.name.setText(item.getName());
        viewHolder.nameDetail.setText(item.getName());
        viewHolder.title.setText(item.getTitle());
        viewHolder.titleDetail.setText(item.getTitle());
        viewHolder.date.setText(item.getDate());
        viewHolder.loclong.setText(item.getLoclong());
        viewHolder.locshort.setText(item.getLocshort());
        viewHolder.exp.setText(item.getExp());
        viewHolder.work.setText(item.getWork());
        viewHolder.expDetail.setText(item.getExp());
        viewHolder.workDetail.setText(item.getWork());
        viewHolder.edu.setText(item.getEdu());
        viewHolder.eduDetail.setText(item.getEdu());
        viewHolder.link.setText(item.getLink());
        viewHolder.etc.setText(item.getEtc());

        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.link.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.link.setOnClickListener(defaultRequestBtnClickListener);
        }

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView nameDetail;
        TextView title;
        TextView titleDetail;
        TextView loclong;
        TextView locshort;
        TextView exp;
        TextView work;
        TextView expDetail;
        TextView workDetail;
        TextView date;
        TextView edu;
        TextView eduDetail;
        TextView link;
        TextView etc;
    }
}
