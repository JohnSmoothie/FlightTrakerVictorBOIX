package com.example.flighttraker

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import org.apache.commons.io.IOUtils
import org.json.JSONArray
import java.io.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by sergio on 2019-11-10
 */

const val TAG: String = "Utils"

class Utils() {

    companion object {

        fun generateAirportList(): List<Airport>{
            val airportList = ArrayList<Airport>()

            for (airportObject in getAirportsListJson()) {
                airportList.add(Gson().fromJson(airportObject.asJsonObject, Airport::class.java))
            }

            return airportList
        }

        fun getAirportsListJson() : JsonArray{
            var input: InputStream? = null
            input = FlightApplication.appAssetManager.open("airports.json")
            val parser = JsonParser()
            val jsonElement = parser.parse(getTextFromStream(input))
            return jsonElement.asJsonArray
        }

        fun getVilleFromIcao(icao: String) : String {
            var res : String = ""
            for (airport in this.getAirportsListJson()) {
                if(airport.asJsonObject["icao"].asString == icao) {
                    res = airport.asJsonObject["name"].asString
                }
            }

            return res
        }


        fun getText(filename: String): String? {
            val f = File(getRootDirectory(), filename)

            return getTextFromfile(f)
        }

        fun getTextFromfile(f: File): String? {
            var content: String? = null
            val filename = f.name

            if (f.exists()) {
                try {
                    val inputStream = FileInputStream(f)

                    content = getTextFromStream(inputStream)
                } catch (e: Exception) {
                    Log.e(TAG, "Error while opening to open text file in cache :$filename", e)
                }

            } else {
                Log.e(TAG, "Text file does not exist in cache :$filename")
            }

            return content;
        }

        fun getTextFromStream(inputStream: InputStream): String? {
            return try {
                val writer = StringWriter()
                IOUtils.copy(inputStream, writer, Charset.forName("UTF-8"))
                writer.toString()
            } catch (e: Exception) {
                Log.e(TAG, "Can't read text from stream", e)
                null
            }

        }

        private fun getRootDirectory(): File {
            return FlightApplication.appContext!!.getDir("appCache", Context.MODE_PRIVATE);
        }

        fun _saveData(data: ByteArray, filename: String): Boolean {
            try {
                val f = File(getRootDirectory(), "tempsJson")

                val fos = FileOutputStream(f)
                fos.write(data)
                fos.close()
                Log.i(TAG, "File written to disk (${f.getAbsolutePath()}")

                return true
            } catch (e: Exception) {
                Log.e(TAG, "Impossible to save  $filename", e)
                return false
            }
        }

        fun getFlightListFromString(arrayAsString: String) : List<FlightModel> {
            val flightJsonArray = convertStringToJsonArray(arrayAsString)
            val flightModelList = ArrayList<FlightModel>()
            for(flightJson in flightJsonArray) {
                flightModelList.add(Gson().fromJson(flightJson.asJsonObject, FlightModel::class.java))
            }

            return flightModelList
        }

        private fun convertStringToJsonArray(arrayAsString: String) : JsonArray {
            val jsonElement = JsonParser.parseString(arrayAsString)
            return jsonElement.asJsonArray
        }


        fun getStandardDateFormat(): SimpleDateFormat {
            val format = "dd/MM/yy"
            return SimpleDateFormat(format, Locale.US)
        }

        fun getCompactDateFormat(): SimpleDateFormat {
            val format = "dd/MM"
            return SimpleDateFormat(format, Locale.US)
        }

        fun dateToString(date: Date?): String {
            return dateToString(date, false)
        }

        fun dateToString(
                date: Date?,
                compactFormat: Boolean
        ): String {
            if(date == null)
                return ""
            return if (compactFormat) getCompactDateFormat()
                    .format(date) else getStandardDateFormat()
                    .format(date)
        }

        fun timestampToString(time: Long): String? {
            return timestampToString(time, false)
        }

        fun timestampToString(
                time: Long,
                compactFormat: Boolean
        ): String? {
            val date = Date(time)
            return if (compactFormat) dateToString(date, true) else dateToString(date)
        }

        /*

        fun _makeJsonAirportLight() {
            var input: InputStream? = null

            input = FlightApplication.appAssetManager.open("airports.json")

            val filteredAirports = JSONArray()

            val jsonArray = JSONArray(getTextFromStream(input))
            for (i in 0 until jsonArray.length()) {
                val airportJson = jsonArray.getJSONObject(i)
                if (airportJson.optInt("direct_flights", 0) > 100) {
                    filteredAirports.put(airportJson)
                }
            }

            _saveData(filteredAirports.toString().toByteArray(), "tempJson")
        }

        */
    }
}
