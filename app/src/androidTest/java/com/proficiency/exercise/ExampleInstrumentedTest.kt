package com.proficiency.exercise

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.proficiency.exercise.adapter.DetailsAdapter
import com.proficiency.exercise.viewmodel.DetailsViewmodel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proficiency.exercise.model.Details
import com.proficiency.utils.Api
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.proficiency.exercise", appContext.packageName)
    }



    @Test
    fun postsAdapterViewRecyclingCaption() {
        var dataList=ArrayList<DetailsViewmodel>()

        val categories1= Details("vinod","llllllllllllllllll","http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg")
        val calegoryViewModel:DetailsViewmodel= DetailsViewmodel(categories1)
        dataList.add(calegoryViewModel)
        dataList.add(calegoryViewModel)
        dataList.add(calegoryViewModel)

        val adapter = DetailsAdapter(InstrumentationRegistry.getTargetContext(),dataList)
        val rvParent = RecyclerView(InstrumentationRegistry.getTargetContext())
        rvParent.layoutManager = LinearLayoutManager(InstrumentationRegistry.getTargetContext())
        // Run test
        val viewHolder = adapter.onCreateViewHolder(rvParent, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        // JUnit Assertion
        assertEquals(View.VISIBLE, viewHolder.textViewName!!.visibility)
        assertEquals(View.VISIBLE, viewHolder.iimage!!.visibility)

        // AssertJ-Android Assertion
        assert(viewHolder.textViewName!!.isGone)
        assert(viewHolder.iimage!!.isGone)

        adapter.onBindViewHolder(viewHolder, 1)








    }




}