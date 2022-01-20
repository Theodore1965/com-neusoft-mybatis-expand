package com.theodore;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface FeignProxy {

//    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("GET /doctor/one/{id}")
    Object getDoctor(@Param("id") String id);

    @RequestLine("GET /aptPatient/all")
    Object getAptPatient(@QueryMap AptPatientQto qto);
}
