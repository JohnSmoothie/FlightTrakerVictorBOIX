package com.example.flighttraker

data class FlightModel (
        val icao24: String,
        val firstSeen: String,
        val estDepartureAirport: String,
        val lastSeen: String,
        val estArrivalAirport: String,
        val callsign: String,
        val estDepartureAirportHorizDistance: String,
        val estDepartureAirportVertDistance: String,
        val estArrivalAirportHorizDistance: String,
        val estArrivalAirportVertDistance: String,
        val departureAirportCandidatesCount: String,
        val arrivalAirportCandidatesCount: String
)
