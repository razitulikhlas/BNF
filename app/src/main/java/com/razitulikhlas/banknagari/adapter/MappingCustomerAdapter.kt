package com.razitulikhlas.banknagari.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.razitulikhlas.banknagari.databinding.ItemMapsBinding
import com.razitulikhlas.core.BuildConfig
import com.razitulikhlas.core.data.source.remote.response.DataItemPetaBusiness
import com.razitulikhlas.core.domain.mapping.model.DataMapCustomer
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MappingCustomerAdapter : RecyclerView.Adapter<MappingCustomerAdapter.ViewHolder>() {

    private lateinit var mappingCallback: MappingCallback

    fun setListener(mappingCallback: MappingCallback){
        this.mappingCallback = mappingCallback
    }

    fun setData(listCustomer: List<DataItemPetaBusiness>){
        this.listCustomer.clear()
        this.listCustomer.addAll(listCustomer)
    }

    class ViewHolder(bind: ItemMapsBinding) :RecyclerView.ViewHolder(bind.root){
        val bin = bind

    }

    private var listCustomer = ArrayList<DataItemPetaBusiness>()
    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMaps = ItemMapsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMaps)
    }

    override fun getItemCount(): Int = listCustomer.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer: DataItemPetaBusiness = listCustomer[position]
        with(holder.bin){
            tvName.text = customer.pemohon
            tvPlatfond.text = formatRupiahh(customer.plafond!!.toDouble())
            tvAo.text = customer.name
            tvSkim.text = customer.skimKredit
            btnNav.setOnClickListener {
                mappingCallback.onClick(customer)
            }
            root.setOnClickListener {
                mappingCallback.onClickDetail(customer)
            }
            Glide
                .with(holder.itemView.context)
                .load(BuildConfig.BASE_URL_IMAGE +customer.image1)
//            .centerCrop()
                .into(ivBusiness)
        }
    }

    fun formatRupiahh(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(amount)
    }

    interface MappingCallback {
        fun onClick(customer: DataItemPetaBusiness)
        fun onClickDetail(id: DataItemPetaBusiness)
    }

}