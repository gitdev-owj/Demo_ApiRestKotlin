package com.demobackend.restapi.utils

class Constants {
    companion object {
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION  = "/v1"
        private const val URL_BASE = URL_API_BASE+ URL_API_VERSION
        //Base api endpoint para persons
        const val URL_BASE_PERSONS = "$URL_BASE/persons"
    }
}