package com.example.cft_demo_final.view.adapter;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cft_demo_final.R;
import com.example.cft_demo_final.data.entities.ExchangeRate;

import java.util.ArrayList;
import java.util.Formatter;

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

    public String clampString(String str) {
        String showValueName;

        if (str.length() > 24) {
            showValueName = str.substring(0, 24) + "...";
        } else showValueName = str;

        return showValueName;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder (@NonNull ExchangeRateAdapter.ViewHolder holder, int position) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        ExchangeRate rate = exchangeRates.get(position);
        // Представление вывода
        String valueNameString = clampString(rate.getName());
        formatter.format("%.2f ₽ / %d %s", rate.getValue(), rate.getNominal(), rate.getTicker());

        holder.currencyName.setText(valueNameString);
        holder.currencyRate.setText(formatter.toString());

        // Вычисление процента увеличения/уменьшения стоимости валюты
        Double percent = rate.getValue() * 100 / rate.getPreviousValue() - 100;
        Double per =  Math.abs(Math.round(percent * 100.0) / 100.0);

        holder.currencyPercent.setText(per.toString());

        Resources res = context.getResources();
        // Вывод стрелочки
        if (percent < 0) {
            holder.currencyPercentArrow.setImageResource(R.drawable.arrow_down);
            holder.currencyPercent.setTextColor(res.getColor(R.color.decrease));
        } else {
            holder.currencyPercentArrow.setImageResource(R.drawable.arrow_up);
            holder.currencyPercent.setTextColor(res.getColor(R.color.increase));
        }

        // Поиск флагов соответствующих char code самой валюты
        int id = context.getResources().
                getIdentifier("flag_" + rate.getTicker().toLowerCase(),
                        "drawable",
                        context.getPackageName());

        // Вывод флага страны
        if (id == 0)
            holder.currencyFlag.setImageResource(R.drawable.flag_unknown);
        else holder.currencyFlag.setImageResource(id);

    }

    @Override
    public int getItemCount () {
        return exchangeRates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView currencyName;
        final TextView currencyRate;
        final TextView currencyPercent;
        final ImageView currencyFlag;
        final ImageView currencyPercentArrow;

        public ViewHolder(View view) {
            super(view);
            currencyName = (TextView) view.findViewById(R.id.currency_name);
            currencyRate = (TextView) view.findViewById(R.id.currency_rate);
            currencyPercent = (TextView) view.findViewById(R.id.currency_percent);
            currencyFlag = (ImageView) view.findViewById(R.id.currency_flag);
            currencyPercentArrow = (ImageView) view.findViewById(R.id.currency_percent_arrow);
        }
    }
}
