package com.team.sahayogi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public static  final String Base_URL="http://192.168.31.191/SahayogiDB/";
    public static Retrofit retrofit = null;

    public  static Retrofit getApiClient()
    {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
