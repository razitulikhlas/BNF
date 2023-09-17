package com.razitulikhlas.banknagari.utils

import android.app.Activity
import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog

class Utils {
    lateinit var pDialog: SweetAlertDialog

    fun initDialog(activity: Activity){
        pDialog = SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
    }

    fun showDialog() {
        if(this::pDialog.isInitialized)
            pDialog.show()
    }

    fun hideDialog() {
        if(this::pDialog.isInitialized)
            pDialog.dismiss()
    }
}