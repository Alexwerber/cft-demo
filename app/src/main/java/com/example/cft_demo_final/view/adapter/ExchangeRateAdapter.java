package com.example.cft_demo_final.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cft_demo_final.R;
import com.example.cft_demo_final.data.entities.ExchangeRate;

import java.util.ArrayList;

public class ExchangeRateAdapter extends RecyclerView.Adapter<ExchangeRateAdapter.ViewHolder> {
    private ArrayList<ExchangeRate> exchangeRates;
    private FragmentActivity context;
    private LayoutInflater inflater;

    public ExchangeRateAdapter (FragmentActivity context) {
        this.exchangeRates = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData (ArrayList<ExchangeRate> rates) {
        this.exchangeRates = rates;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_exchange_rate_list, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder (@NonNull ExchangeRateAdapter.ViewHolder holder, int position) {
        ExchangeRate rate = exchangeRates.get(position);

        holder.currencyName.setText(rate.getName());
    }

    @Override
    public int getItemCount () {
        return exchangeRates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView currencyName;

        public ViewHolder(View view) {
            super(view);
            currencyName = (TextView) view.findViewById(R.id.currency_name);
        }
    }
}
