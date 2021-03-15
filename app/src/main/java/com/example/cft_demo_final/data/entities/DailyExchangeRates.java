package com.example.cft_demo_final.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class DailyExchangeRates {
    @SerializedName("Date")
    private Date date;

    @SerializedName("Timestamp")
    private Date timestamp;

    @SerializedName("Valute")
    private Map<String, ExchangeRate> valute;

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public Date getTimestamp () {
        return timestamp;
    }

    public void setTimestamp (Date timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, ExchangeRate> getValute () {
        return valute;
    }

    public void setValute (Map<String, ExchangeRate> valute) {
        this.valute = valute;
    }
}
