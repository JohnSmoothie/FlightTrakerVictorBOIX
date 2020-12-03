package com.example.flighttraker

import android.os.Bundle
import android.widget.LinearLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class FlightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight)

        var lL: LinearLayout = findViewById<LinearLayout>(R.id.listevols)




        val extras: Bundle? = intent.extras

        var aeroportDep = extras?.get("aeroportDep").toString()
        var aeroportArr = extras?.get("aeroportArr").toString()
        var dateDebut = extras?.get("dateDebut").toString()
        var dateFin = extras?.get("dateFin").toString()
        var aeroportDepPresent = extras?.get("dateFin").toString()
        var aeroportArrPresent = extras?.get("dateFin").toString()
        var depArr = extras?.get("dateFin").toString()


        val infoVolsView = InfoVolsView(lL.context, Utils.getVilleFromIcao(aeroportDep), dateDebut, Utils.getVilleFromIcao(aeroportArr), dateFin)

        lL.addView(infoVolsView)



    }
}