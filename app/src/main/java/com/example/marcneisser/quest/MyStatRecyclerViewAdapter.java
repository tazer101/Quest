package com.example.marcneisser.quest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.marcneisser.quest.MyStatsFragment.OnListFragmentInteractionListener;
import com.example.marcneisser.quest.Stat;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStatRecyclerViewAdapter extends RecyclerView.Adapter<MyStatRecyclerViewAdapter.ViewHolder> {

    private final List<Stat> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyStatRecyclerViewAdapter(List<Stat> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem=mValues.get(position);
        holder.mStatName.setText(mValues.get(position).getStatName());
        holder.mStatLevel.setText(Integer.toString(mValues.get(position).getStatLevel()));
        holder.mStatProgress.setMax(mValues.get(position).getStatExpNeed());
        holder.mStatProgress.setProgress(mValues.get(position).getStatExpCurrent());
        holder.mExpCurrent.setText(Integer.toString(mValues.get(position).getStatExpCurrent()));
        holder.mExpNeeded.setText(Integer.toString(mValues.get(position).getStatExpNeed()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
//need to modify this viewholder to fit my stuff
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mStatName;
        public final TextView mStatLevel;
        public final ProgressBar mStatProgress;
        public final TextView mExpCurrent;
        public final TextView mExpNeeded;
        public Stat mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mStatName = (TextView) view.findViewById(R.id.stat_name);
            mStatLevel = (TextView) view.findViewById(R.id.stat_level);
            mStatProgress =(ProgressBar) view.findViewById(R.id.stat_progress);
            mExpCurrent =(TextView) view.findViewById(R.id.exp_current);
            mExpNeeded =(TextView) view.findViewById(R.id.exp_needed);
        }


    }
}
