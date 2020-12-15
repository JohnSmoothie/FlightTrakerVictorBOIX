package com.example.flighttraker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlightListViewModel : ViewModel() {


    val flightListLiveData: MutableLiveData<List<FlightModel>> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val RequestsManager : RequestManager = RequestManager()

    fun search(url: String) {

        viewModelScope.launch {
            //start loading
            isLoadingLiveData.value = true
            val result = withContext(Dispatchers.IO) {
                RequestsManager.sendGetRequestSuspended(url)
            }
            //end loading
            isLoadingLiveData.value = false
            if (result == null) {
                Log.e("Request", "problem")

            } else {
                val flightList = Utils.getFlightListFromString(result)
                Log.d("models list", flightList.toString())
                flightListLiveData.value = flightList
            }

        }
        // SearchFlightsAsyncTask(this).execute(searchDataModel)

    }

}