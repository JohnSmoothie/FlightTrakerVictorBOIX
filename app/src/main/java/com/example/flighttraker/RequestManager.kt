package com.example.flighttraker

import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class RequestManager {

        suspend fun sendGetRequestSuspended(url: String) : String? {

            try {
                val mURL = URL(url)
                val result = StringBuilder()

                val httpURLConnection = mURL.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connectTimeout = 10000
                httpURLConnection.readTimeout = 10000

                val reader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result.append(line)
                }
                reader.close()
                return result.toString()

            } catch (e: IOException) {
                Log.e(
                    "RequestManager",
                    "Error while doing GET request (url: " + url + ") - " + e.message
                )
            }

            return null
        }
}