package com.example.formulario.Model

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.formulario.R
import com.example.formulario.ui.MainActivity
import java.util.Calendar

class DatePicker(val listener:(day:Int,month:Int,year:Int)-> Unit):DialogFragment(),DatePickerDialog.OnDateSetListener{
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth,month,year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario = Calendar.getInstance()
        val diaActual = calendario.get(Calendar.DAY_OF_MONTH)
        val mesActual = calendario.get(Calendar.MONTH)
        val añoActual = calendario.get(Calendar.YEAR)

        val picker = DatePickerDialog(activity as Context, R.style.datePickerT,this, añoActual,mesActual,diaActual)
        return  picker
    }
}