package com.proficiency.exercise.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.proficiency.utils.Api
import com.proficiency.exercise.model.Details
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class DetailsViewmodel : ViewModel {

    var title=""
    var description=""
    var imageHref=""

    var dataListMuttable= MutableLiveData<ArrayList<DetailsViewmodel>>()
    var dataList=ArrayList<DetailsViewmodel>()


    var app_title: String? = null


    constructor() : super()

    constructor(details: Details) : super() {
        this.title = details.title
        this.description = details.description
        this.imageHref = details.imageHref
    }





    fun getArrayList(): MutableLiveData<ArrayList<DetailsViewmodel>> {

        return gettingDetails()//Getting MuttableLivedata arraylist
    }


//Network calling function
    fun gettingDetails(): MutableLiveData<ArrayList<DetailsViewmodel>> {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    val api = retrofit.create(Api::class.java!!)

        val call = api.getString()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("Response_String", response.body().toString())
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString())

                        val jsonresponse = response.body().toString()
                        val obj = JSONObject(jsonresponse)
                        app_title=obj.getString("title")
                        val dataArray = obj.getJSONArray("rows")
                        dataList.clear()
                        for (i in 0 until dataArray.length()) {
                            val dataobj = dataArray.getJSONObject(i)
                            val categories1=Details(dataobj.getString("title"),dataobj.getString("description"),dataobj.getString("imageHref"))
                            val calegoryViewModel:DetailsViewmodel= DetailsViewmodel(categories1)
                            dataList.add(calegoryViewModel)

                        }
                        dataListMuttable.value=dataList


                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        )//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(
                    "onEmptyResponse",
                    "Returned empty response"
                )
            }
        })

        return dataListMuttable
    }



}

