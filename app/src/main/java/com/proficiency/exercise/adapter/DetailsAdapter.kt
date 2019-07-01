package com.proficiency.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proficiency.exercise.R
import com.proficiency.exercise.viewmodel.DetailsViewmodel


class DetailsAdapter(private  val context: Context, val arrayList: ArrayList<DetailsViewmodel>):RecyclerView.Adapter<DetailsAdapter.CustomView>()
{






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        val layoutInflater=LayoutInflater.from(parent.context)


        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)


        return CustomView(v)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val calegoryViewModel=arrayList[position]

        holder.bind(calegoryViewModel)
    }

    override fun getItemCount(): Int {

       return arrayList.size
    }

    class CustomView(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textViewName:TextView?=null
        var textViewAddress:TextView?=null
        var img_lyt:LinearLayout?=null
        var main_lyt:LinearLayout?=null
        var iimage:ImageView?=null



        fun bind(calegoryViewModel: DetailsViewmodel){

             textViewName = itemView.findViewById(R.id.title) as TextView
            textViewAddress  = itemView.findViewById(R.id.desc) as TextView
            img_lyt  = itemView.findViewById(R.id.img_lyt) as LinearLayout
             main_lyt  = itemView.findViewById(R.id.main_lyt) as LinearLayout
            iimage  = itemView.findViewById(R.id.img) as ImageView
            println(calegoryViewModel.description)
            if (!calegoryViewModel.title.equals("null")) {
                textViewName!!.visibility=View.VISIBLE
                textViewName!!.text = calegoryViewModel?.title }
            else{
                textViewName!!.text = ""
                textViewName!!.visibility=View.GONE
            }
            if (!calegoryViewModel.description.equals("null")) {
                textViewAddress!!.visibility=View.VISIBLE

                textViewAddress!!.text = calegoryViewModel?.description }else{
              //  textViewAddress.text = ""
                textViewAddress!!.visibility=View.GONE

            }

            if (!calegoryViewModel.imageHref.equals("null")) {
                img_lyt!!.visibility=View.VISIBLE

                Glide.with(itemView.context).load(calegoryViewModel.imageHref)

                    .thumbnail(0.5f)
                    .into(iimage)

            }else{

                img_lyt!!.visibility=View.GONE


            }

            if (calegoryViewModel.description.equals("null")&&calegoryViewModel.title.equals("null")&&calegoryViewModel.imageHref.equals("null")) {
                main_lyt!!.visibility=View.GONE
            }else{
                main_lyt!!.visibility=View.VISIBLE

            }


           // textViewAddress.text = calegoryViewModel?.description



        }
    }




}