package com.example.formulario.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.formulario.Model.Alumno
import com.example.formulario.Model.DatePicker
import com.example.formulario.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.IOError
import java.io.IOException
import java.util.Calendar


class FormFragment : Fragment() {
    private lateinit var tvDia: TextView
    private lateinit var tvMes: TextView
    private lateinit var tvAño: TextView
    private lateinit var etNombre: TextInputEditText
    private lateinit var etCorreo: TextInputEditText
    private lateinit var etNumeroDeCuento:TextInputEditText
    private lateinit var carrerasCompleteTV:AutoCompleteTextView
    private lateinit var alumno: Alumno
    private lateinit var carreraAdapter: ArrayAdapter<String>
    private val carrerasList = Alumno.CarrerasIngenieria
    private var indexCarrera:Int? = null
    private var edadAlumno: Int? = null
    private var signoZodiacal:String? = null
    private var signoChino:String? =null
    private var fechaValida:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_form, container, false)

        etNombre = view.findViewById(R.id.tiNombre)
        etCorreo = view.findViewById(R.id.tiCorreo)
        etNumeroDeCuento = view.findViewById(R.id.tiNumeroDeCuenta)


        carrerasCompleteTV= view.findViewById(R.id.DropCarreras)
        carreraAdapter = ArrayAdapter(view.context,android.R.layout.simple_spinner_dropdown_item,carrerasList)
        carrerasCompleteTV.setAdapter(carreraAdapter)
        carrerasCompleteTV.setOnItemClickListener{adapterView,view,i,l ->
            indexCarrera = i
        }




        println(carrerasCompleteTV.text)
         tvDia = view.findViewById(R.id.tvDia)
         tvMes = view.findViewById(R.id.tvMes)
         tvAño = view.findViewById(R.id.tvAño)



        val buttonCalendario: Button = view.findViewById(R.id.btnCalendario)
        val buttonResultado = view.findViewById<AppCompatButton>(R.id.BtnAvanzar)



        buttonCalendario.setOnClickListener {
            showDatePickerCalendar()
        }

        buttonResultado.setOnClickListener{
            try{
                CrearAlumno()
                AlumnoCreado(alumno)

            }
            catch (e: Exception){
                Toast.makeText(context, "Ingresa tu nombre", Toast.LENGTH_SHORT).show()
            }


        }


        return view

    }




    private fun Navegacion(alumnoInstance: Alumno){
        val resultFragment = ResultFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FormFragmentContainer,ResultFragment.newInstance(alumnoInstance))
        transaction.addToBackStack("")
        transaction.commit()


    }

    private fun AlumnoCreado(alumnoInput:Alumno){
        if (alumnoInput.email != null
            && alumnoInput.edad != null
            && alumnoInput.Ingenieria != null
            && alumnoInput.numeroDeCuenta !=null
            && alumnoInput.horoscopoChino != null)  {
            Navegacion(alumnoInput)
        }

    }

    private fun CrearAlumno(){

        if(!etNombre.text.isNullOrEmpty()){
            alumno = Alumno(etNombre.text.toString())
        }else{
            Toast.makeText(context, "Ingresa tu nombre", Toast.LENGTH_SHORT).show()
        }

        if(!etCorreo.text.isNullOrEmpty()){
            alumno.email = etCorreo.text.toString()
        }

        if(alumno.email == null){
            Toast.makeText(context, "Ingresa un correo electronico valido", Toast.LENGTH_SHORT).show()
        }

        if(!etNumeroDeCuento.text.isNullOrEmpty()){
            alumno.numeroDeCuenta = etNumeroDeCuento.text.toString()
        }

        if(alumno.numeroDeCuenta == null){
            Toast.makeText(context, "Ingresa un numero de cuenta valido", Toast.LENGTH_SHORT).show()
        }

        if(indexCarrera!=null){
            alumno.Ingenieria = carrerasList[indexCarrera!!]

        }
        else{
            Toast.makeText(context, "Seleccione la ingeniería que estudia", Toast.LENGTH_SHORT).show()
        }

        if(edadAlumno!=null){
            alumno.edad = edadAlumno

        }
        else{
            Toast.makeText(context, "Ingrese su fecha de nacimiento", Toast.LENGTH_SHORT).show()
        }

        if(signoZodiacal!=null){
            alumno.signoZodiaco = signoZodiacal
        }

        if(signoChino != null){
            alumno.horoscopoChino = signoChino

        }



    }

    private fun showDatePickerCalendar() {
        val datePicker: DatePicker = DatePicker{ dia, mes, año -> onDateSelect(dia,mes, año) }
        datePicker.show(parentFragmentManager,"datePicker")
    }
    private fun onDateSelect(dia:Int,mes:Int,año:Int){
        var Calendario = Calendar.getInstance()
        var diaActual:Int  = Calendario.get(Calendar.DAY_OF_MONTH)
        var mesActual:Int = Calendario.get(Calendar.MONTH)
        var añoActual:Int = Calendario.get(Calendar.YEAR)

        if( año< añoActual){
            fechaValida = true
        }
        if(año == añoActual && mes<mesActual){
            fechaValida = true
        }
        if(año == añoActual && mes==mesActual && dia <= diaActual){
            fechaValida = true
        }

        if(fechaValida== true){
            fechaValida = false
            calcularEdad(dia,mes,año)

            signoZodiacal = calcularZignoZodiaco(dia,mes) ?: null
            signoChino = signoHoroscopoChino(año) ?: null

            tvDia.text = dia.toString()
            when(mes){
                0 -> tvMes.text = "Enero"
                1 -> tvMes.text = "Febrero"
                2 -> tvMes.text = "Marzo"
                3 -> tvMes.text = "Abril"
                4 -> tvMes.text = "Mayo"
                5 -> tvMes.text = "Junio"
                6 -> tvMes.text = "Julio"
                7 -> tvMes.text = "Agosto"
                8 -> tvMes.text = "Septiembre"
                9 -> tvMes.text = "Octubre"
                10 -> tvMes.text = "Noviembre"
                11 -> tvMes.text = "Diciembre"
                else -> tvMes.text = "Mes invalido"
            }
            tvAño.text = año.toString()
        }else{
            Toast.makeText(context, "La fecha no es valida", Toast.LENGTH_SHORT).show()
        }




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


    private fun calcularZignoZodiaco(diaDeNacimiento:Int,mesDeNacimineto:Int):String? {
        if(mesDeNacimineto == 0 && diaDeNacimiento>=22 || mesDeNacimineto == 1 && diaDeNacimiento <=18 ){ return "Acuario"}
        if(mesDeNacimineto == 2 && diaDeNacimiento>=21 || mesDeNacimineto == 3 && diaDeNacimiento <=21 ){ return "Aries"}
        if(mesDeNacimineto == 5 && diaDeNacimiento>=22 || mesDeNacimineto == 6 && diaDeNacimiento <=21 ){ return "Cancer"}
        if(mesDeNacimineto == 11 && diaDeNacimiento>=22 || mesDeNacimineto == 0 && diaDeNacimiento <=21 ){ return "Capricornio"}
        if(mesDeNacimineto == 9 && diaDeNacimiento>=22 || mesDeNacimineto == 10 && diaDeNacimiento <=21 ){ return "Escorpio"}
        if(mesDeNacimineto == 4 && diaDeNacimiento>=22 || mesDeNacimineto == 5 && diaDeNacimiento <=21 ){ return "Geminis"}
        if(mesDeNacimineto == 6 && diaDeNacimiento>=22 || mesDeNacimineto == 7 && diaDeNacimiento <=21 ){ return "Leo"}
        if(mesDeNacimineto == 8 && diaDeNacimiento>=24 || mesDeNacimineto == 9 && diaDeNacimiento <=21 ){ return "Libra"}
        if(mesDeNacimineto == 1 && diaDeNacimiento>=19 || mesDeNacimineto == 2 && diaDeNacimiento <=20 ){ return "Piscis"}
        if(mesDeNacimineto == 10 && diaDeNacimiento>=22 || mesDeNacimineto == 11 && diaDeNacimiento <=21 ){ return "Sagitario"}
        if(mesDeNacimineto == 3 && diaDeNacimiento>=22 || mesDeNacimineto == 4 && diaDeNacimiento <=21 ){ return "Tauro"}
        if(mesDeNacimineto == 7 && diaDeNacimiento>=22 || mesDeNacimineto == 8 && diaDeNacimiento <=23 ){ return "Virgo"}

        else{
            return null
        }
    }

    private fun signoHoroscopoChino(añoDeNacimiento: Int):String?{
       var residuo = añoDeNacimiento%12
        when(residuo){
            0 -> return "MONO"
            1 -> return "GALLO"
            2 -> return "PERRO"
            3 -> return "CERDO"
            4 -> return "RATA"
            5 -> return "BUEY"
            6 -> return "TIGRE"
            7 -> return "CONEJO"
            8 -> return "DRAGON"
            9 -> return "SERPIENTE"
            10 -> return "CABALLO"
            11 -> return "CABRA"
            else -> return null

        }
    }


}