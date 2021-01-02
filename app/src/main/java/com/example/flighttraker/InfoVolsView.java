package com.example.flighttraker;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.flighttraker.Utils;

public class InfoVolsView extends LinearLayout {

    private TextView aeroDep;
    private TextView dateDep;
    private TextView aeroArr;
    private TextView dateArr;
    private TextView tmpVol;
    private TextView idVol;
    private ImageView logoCompanie;

    public InfoVolsView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.flight_info, this, true);

        this.aeroDep = (TextView) findViewById(R.id.aeroDep);


        this.dateDep = (TextView) findViewById(R.id.dateDep);


        this.aeroArr = (TextView) findViewById(R.id.aeroArr);


        this.dateArr = (TextView) findViewById(R.id.dateArr);


        this.tmpVol = (TextView) findViewById(R.id.tmpVol);


        this.idVol = (TextView) findViewById(R.id.idVol);


    }


    public void afficherInfos(FlightModel flightModel) {



        this.aeroDep.setText(flightModel.getEstDepartureAirport());
        this.dateDep.setText(Utils.Companion.timestampToString(Long.parseLong(flightModel.getFirstSeen())));
        this.aeroArr.setText(flightModel.getEstArrivalAirport());
        this.dateArr.setText(Utils.Companion.timestampToString(Long.parseLong(flightModel.getLastSeen())));
        //this.tmpVol.setText(flightModel.getLastSeen()) - Integer.parseInt(flightModel.getFirstSeen()));
        this.idVol.setText(flightModel.getCallsign());
    }


}
