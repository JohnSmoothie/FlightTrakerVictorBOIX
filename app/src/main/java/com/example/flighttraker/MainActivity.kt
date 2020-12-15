package com.example.flighttraker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.savvi.rangedatepicker.CalendarPickerView
import java.util.*
import kotlin.collections.ArrayList

//val airportList = Utils.generateAirportList()

lateinit var viewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialisation du viewModel
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //listes d'aéroport
        val airportNameList = ArrayList<String>()

        for (airport in viewModel.getAirportListLiveData().value!!) {
            airportNameList.add(airport.getFormattedName())
        }

        val adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, airportNameList)

        val autoCompleteTextViewDepart = findViewById<Spinner>(R.id.autoCompleteTextView6)
        autoCompleteTextViewDepart.adapter = adapter

        val autoCompleteTextViewArrive = findViewById<Spinner>(R.id.autoCompleteTextView5)
        autoCompleteTextViewArrive.adapter = adapter

        //calendrier
        val calendar_view = findViewById<CalendarPickerView>(R.id.calendar_view)
        val last_year = Calendar.getInstance()
        last_year.add(Calendar.YEAR, -10)
        val tomorow = Calendar.getInstance()
        tomorow.add(Calendar.DAY_OF_MONTH, 1)
        calendar_view.init(last_year.time, tomorow.time) //
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(Date())

        val lab = findViewById<TextView>(R.id.textView4)

        lab.text = calendar_view.selectedDate.time.toString().dropLast(3)

        //definission du clique sur le bouton recherche
        val buttonRecherche = findViewById<Button>(R.id.button)

        buttonRecherche.setOnClickListener() { recherche() }

    }

    private fun recherche() {
        var aeroportDep = viewModel.getAirportListLiveData().value!![findViewById<Spinner>(R.id.autoCompleteTextView6).selectedItemPosition].icao
        var aeroportArr = viewModel.getAirportListLiveData().value!![findViewById<Spinner>(R.id.autoCompleteTextView5).selectedItemPosition].icao
        //recupere aéroport arrivé et départ
        val dateRange = findViewById<CalendarPickerView>(R.id.calendar_view).selectedDates
        //recuperation des timestamp des dates de début et de fin
        val dateDebut: String = dateRange.first().time.toString().dropLast(3)
        val dateFin: String = dateRange.last().time.toString().dropLast(3)
        //recupérer date de début et de fin

        var aeroportDepPresent = false
        var aeroportArrPresent = false

        val ignorerDep : Switch = findViewById<Switch>(R.id.ignorerDep)
        val ignorerArr : Switch = findViewById<Switch>(R.id.ignorerArr)

        if(!ignorerDep.isChecked) {
            aeroportDepPresent = true
        }
        if(!ignorerArr.isChecked) {
            aeroportArrPresent = true
        }

        //demarer une activité et lui passer les infos

        val intent = Intent(this, FlightActivity::class.java).apply {
            putExtra("aeroportDep", aeroportDep)
            putExtra("aeroportArr", aeroportArr)
            putExtra("dateDebut", dateDebut)
            putExtra("dateFin", dateFin)
            putExtra("aeroportDepPresent", aeroportDepPresent)
            putExtra("aeroportArrPresent", aeroportArrPresent)
        }

        startActivity(intent)


    }


}