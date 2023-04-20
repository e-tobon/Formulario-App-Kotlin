package com.example.formulario.Model

import android.app.Notification.CarExtender
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.formulario.R
import java.util.Calendar
import kotlin.properties.Delegates


class FormFragment : Fragment() {
    private lateinit var tvDia: TextView
    private lateinit var tvMes: TextView
    private lateinit var tvAño: TextView
    private var edadAlumno : Int = 0
    private var fechaActivada:Boolean = false
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_form, container, false)

        var carrerasCompleteTV:AutoCompleteTextView = view.findViewById(R.id.DropCarreras)
        val carrerasList = Alumno.CarrerasIngenieria


        val carreraAdapter = ArrayAdapter(view.context,android.R.layout.simple_spinner_dropdown_item,carrerasList)
        carrerasCompleteTV.setAdapter(carreraAdapter)
        carrerasCompleteTV.setOnItemClickListener{adapterView,view,i,l ->
            println(carrerasList[i])
        }

        println(carrerasCompleteTV.text)
         tvDia = view.findViewById(R.id.tvDia)
         tvMes = view.findViewById(R.id.tvMes)
         tvAño = view.findViewById(R.id.tvAño)

        val buttonCalendario: Button = view.findViewById(R.id.btnCalendario)
        buttonCalendario.setOnClickListener {
            showDatePickerCalendar()
        }

        return view

    }

    private fun showDatePickerCalendar() {
        val datePicker:DatePicker = DatePicker{dia,mes,año -> onDateSelect(dia,mes, año) }
        datePicker.show(parentFragmentManager,"datePicker")
    }
    private fun onDateSelect(dia:Int,mes:Int,año:Int){
        tvDia.text = dia.toString()
        if(mes.toString().length==1){
            tvMes.text = "0" + (mes + 1).toString()
        }
        else{
            tvMes.text = (mes + 1).toString()
        }
        tvAño.text = año.toString()

        fechaActivada = true
        calcularEdad(dia,mes,año)


    }

        private fun calcularEdad(diaDeNacimiento:Int,mesDeNacimineto:Int,añoDeNacimiento:Int){

            var Calendario = Calendar.getInstance()
            var diaActual:Int  = Calendario.get(Calendar.DAY_OF_MONTH)
            var mesActual:Int = Calendario.get(Calendar.MONTH)
            var añoActual:Int = Calendario.get(Calendar.YEAR)

            if(diaActual>=diaDeNacimiento && mesActual >= mesDeNacimineto){
                edadAlumno = añoActual - añoDeNacimiento
            }
            else{
                edadAlumno = (añoActual-añoDeNacimiento)-1
            }
            println("$edadAlumno")
        }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}