package com.example.flighttraker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityViewModel : ViewModel() {

    private var airportListLiveData: MutableLiveData<List<Airport>> = MutableLiveData()

    init {
        airportListLiveData.value = Utils.generateAirportList()
    }

    fun getAirportListLiveData(): LiveData<List<Airport>> {
        return airportListLiveData
    }

}