package com.example.flighttraker;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoVolsView extends LinearLayout {

    private TextView aeroDep;
    private TextView dateDep;
    private TextView aeroArr;
    private TextView dateArr;
    private TextView tmpVol;
    private TextView idVol;
    private ImageView logoCompanie;

    public InfoVolsView(Context context, String aeropDep, String dateDepart, String aeropArr, String dateArrive) {
        super(context);
        inflate(context, R.layout.flight_info, this);

        this.aeroDep = (TextView) findViewById(R.id.aeroDep);
        this.aeroDep.setText(aeropDep);

        this.dateDep = (TextView) findViewById(R.id.dateDep);
        this.dateDep.setText(dateDepart);

        this.aeroArr = (TextView) findViewById(R.id.aeroArr);
        this.aeroArr.setText(aeropArr);

        this.dateArr = (TextView) findViewById(R.id.dateArr);
        this.dateArr.setText(dateArrive);
    }

}
