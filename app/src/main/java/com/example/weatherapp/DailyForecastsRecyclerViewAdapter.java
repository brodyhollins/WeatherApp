package com.example.weatherapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.model.DailyForecasts;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DailyForecasts}.
 * TODO: Replace the implementation with code for your data type.
 */
//--------------------------------------------------------------//
//Adapter of the fragment, used to show the list
public class DailyForecastsRecyclerViewAdapter extends RecyclerView.Adapter<DailyForecastsRecyclerViewAdapter.ViewHolder> {

    private final List<DailyForecasts> mValues;

    public DailyForecastsRecyclerViewAdapter(List<DailyForecasts> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DailyForecasts item = mValues.get(position);
        holder.mItem = item;
        holder.tvDate.setText(item.getDate().substring(0, 10));
        holder.tvMinimum.setText(item.getTemperature().getMinimum().getValue()+
                " "+ item.getTemperature().getMinimum().getUnit());
        holder.tvMaximum.setText(item.getTemperature().getMaximum().getValue()+
                " "+ item.getTemperature().getMaximum().getUnit());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvDate;
        public final TextView tvMinimum;
        public final TextView tvMaximum;
        public final TextView mContentView;
        public DailyForecasts mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            tvMinimum = (TextView) view.findViewById(R.id.tv_minimum);
            tvMaximum = (TextView) view.findViewById(R.id.tv_maximum);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvDate.getText() + "'";
        }
    }
}
//--------------------------------------------------------------end of file--------------------------------------------------------------//