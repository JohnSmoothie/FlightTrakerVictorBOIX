package com.example.flighttraker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class FlightActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var viewModel: FlightListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight)

        viewModel = ViewModelProvider(this).get(FlightListViewModel::class.java)

        var listView: LinearLayout? = findViewById<LinearLayout>(R.id.linearLayout1)

        val extras: Bundle? = intent.extras

        var aeroportDep = extras?.get("aeroportDep").toString()
        var aeroportArr = extras?.get("aeroportArr").toString()
        var dateDebut = extras?.get("dateDebut").toString()
        var dateFin = extras?.get("dateFin").toString()
        var aeroportDepPresent = extras?.get("aeroportDepPresent").toString()
        var aeroportArrPresent = extras?.get("aeroportArrPresent").toString()

        Log.d("truc1", aeroportDepPresent)
        Log.d("truc2", aeroportArrPresent)

        var url = "https://opensky-network.org/api/flights"

        //var result : List<FlightModel>? = null

        if(aeroportDepPresent == "true") {
            //requeter l'API sur l'aeroport de depart
            url += "/departure?airport=$aeroportDep&begin=$dateDebut&end=$dateFin"
            viewModel.search(url)
            //result = Utils.getFlightListFromString(sendGetRequest(url))
            if(aeroportArrPresent == "true") {
                //trier le resultat de la requete pour ne garder que celles qui on le bon aeroport d'arrivée
            }
        } else if(aeroportArrPresent == "true") {
            //requeter l'API sur les aeroport d'arrivée
            url += "/arrival?airport=$aeroportDep&begin=$dateDebut&end=$dateFin"
            viewModel.search(url)
            //result = Utils.getFlightListFromString(sendGetRequest(url))
        }

        viewModel.flightListLiveData.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                //DISPLAY ERROR
            } else {

                for (flightModel in viewModel.flightListLiveData.value!!) {
                    val infoVolsView = InfoVolsView(listView?.context)
                    infoVolsView.afficherInfos(flightModel)
                    listView?.addView(infoVolsView)
                }

                /*
                val adapter = FlightRecyclerViewAdapter()
                adapter.listVols = it
                adapter.onItemClickListener = this
                recyclerView?.adapter = adapter
                 */
            }
        })






        //val infoVolsView = InfoVolsView(lL.context, Utils.getVilleFromIcao(aeroportDep), dateDebut, Utils.getVilleFromIcao(aeroportArr), dateFin)

        //lL.addView(infoVolsView)


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}

