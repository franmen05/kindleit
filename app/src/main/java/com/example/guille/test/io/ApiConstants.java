package com.example.guille.test.io;


public class ApiConstants {

//    GET api/Deals?offset={offset}&limit={limit}
//
//    No documentation available.
//    GET api/Deals?description={description}&offset={offset}&limit={limit}

    public static final String URL_BASE="http://edeals.do:4000";
    public static final  String URL_DEALS="/api/deals?limit=400&offset=0";

    public static final  String PARAM_DESC="description";

    public static final  String URL_DEALS_BY_DESC=URL_DEALS+PARAM_DESC+"={desValue}";

}
