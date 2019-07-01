package com.proficiency.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proficiency.exercise.adapter.DetailsAdapter
import com.proficiency.exercise.viewmodel.DetailsViewmodel


class MainActivity : AppCompatActivity() {

    private var recyclerView:RecyclerView?=null
    private var detailsAdapter: DetailsAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recycl)
        recyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        //Instanciate Viewmodel
        var detailsViewModel: DetailsViewmodel =ViewModelProviders.of(this).get(DetailsViewmodel::class.java)

        //Getiing data from Viewmodel Instance
        detailsViewModel.getArrayList().observe(this, Observer { categoryViewModel->

            detailsAdapter =DetailsAdapter(this,categoryViewModel!!)

            //Binding data to the recyclerview
            recyclerView!!.adapter=detailsAdapter
            getSupportActionBar()?.setTitle(detailsViewModel.app_title);






        })

    }
}
