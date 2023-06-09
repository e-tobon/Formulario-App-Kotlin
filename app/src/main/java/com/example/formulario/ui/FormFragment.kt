package com.example.formulario.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.formulario.Model.Alumno
import com.example.formulario.Model.DatePicker
import com.example.formulario.R
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class FormFragment : Fragment() {
    private  var tvDia: String? = null
    private  var tvMes: String? = null
    private  var tvAño: String? = null
    private lateinit var inputFecha: TextInputEditText
    private lateinit var etNombre: TextInputEditText
    private lateinit var etCorreo: TextInputEditText
    private lateinit var etNumeroDeCuento:TextInputEditText
    private lateinit var carrerasCompleteTV:AutoCompleteTextView
    private lateinit var alumno: Alumno
    private lateinit var carreraAdapter: ArrayAdapter<String>
    private var indexCarrera:Int? = null
    private var edadAlumno: Int? = null
    private var signoZodiacal:String? = null
    private var signoChino:String? =null
    private var fechaValida:Boolean = false
    var carreras2List = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
             carreras2List = arrayListOf<String>(
            getString(R.string.AEROESPACIAL),
            getString(R.string.CIVIL),
            getString(R.string.GEOMATICA),
            getString(R.string.AMBIENTAL),
            getString(R.string.GEOFISICA),
            getString(R.string.GEOLOGICA),
            getString(R.string.PETROLERA),
            getString(R.string.MINAS_METALURGIA),
            getString(R.string.COMPUTACION),
            getString(R.string.ELECTRICA_ELECTRONICA),
            getString(R.string.TELECOMUNICACIONES),
            getString(R.string.MECANICA),
            getString(R.string.INDUSTRIAL),
            getString(R.string.MECATRONICA),
            getString(R.string.SISTEMAS_BIOMEDICOS))
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        inputFecha = view.findViewById(R.id.cumpleaños)
        etNombre = view.findViewById(R.id.tiNombre)
        etCorreo = view.findViewById(R.id.tiCorreo)
        etNumeroDeCuento = view.findViewById(R.id.tiNumeroDeCuenta)
        carrerasCompleteTV= view.findViewById(R.id.DropCarreras)
        carreraAdapter = ArrayAdapter(requireActivity().applicationContext,android.R.layout.simple_spinner_dropdown_item,carreras2List)
        carrerasCompleteTV.setAdapter(carreraAdapter)
        carrerasCompleteTV.setOnItemClickListener{_,_,i,_ ->
            indexCarrera = i


        }




        println(carrerasCompleteTV.text)




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
                Toast.makeText(context, getString(R.string.toas_nombre), Toast.LENGTH_SHORT).show()
            }


        }


        return view

    }




    private fun Navegacion(alumnoInstance: Alumno){
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
            Toast.makeText(context, getString(R.string.toast_nombre), Toast.LENGTH_SHORT).show()
        }

        if(!etCorreo.text.isNullOrEmpty()){
            alumno.email = etCorreo.text.toString()
        }

        if(alumno.email == null){
            Toast.makeText(context, getString(R.string.toast_correo), Toast.LENGTH_SHORT).show()
        }

        if(!etNumeroDeCuento.text.isNullOrEmpty()){
            alumno.numeroDeCuenta = etNumeroDeCuento.text.toString()
        }

        if(alumno.numeroDeCuenta == null){
            Toast.makeText(context, getString(R.string.toast_cuenta), Toast.LENGTH_SHORT).show()
        }

        if(indexCarrera!=null){
            alumno.Ingenieria = carreras2List[indexCarrera!!]

        }
        else{
            Toast.makeText(context, getString(R.string.toast_ingenieria), Toast.LENGTH_SHORT).show()
        }

        if(edadAlumno!=null){
            alumno.edad = edadAlumno

        }
        else{
            Toast.makeText(context, getString(R.string.ToasNaciemiento), Toast.LENGTH_SHORT).show()
        }

        if(signoZodiacal!=null){
            alumno.signoZodiaco = signoZodiacal
        }

        if(signoChino != null){
            alumno.horoscopoChino = signoChino

        }



    }

    private fun showDatePickerCalendar() {
        val datePicker = DatePicker{ dia, mes, año -> onDateSelect(dia,mes, año) }
        datePicker.show(parentFragmentManager,"datePicker")
    }
    private fun onDateSelect(dia:Int,mes:Int,año:Int){
        val Calendario = Calendar.getInstance()
        val diaActual:Int  = Calendario.get(Calendar.DAY_OF_MONTH)
        val mesActual:Int = Calendario.get(Calendar.MONTH)
        val añoActual:Int = Calendario.get(Calendar.YEAR)


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
            tvAño =año.toString()
            fechaValida = false
            calcularEdad(dia,mes,año)

            signoZodiacal = calcularZignoZodiaco(dia,mes)
            signoChino = signoHoroscopoChino(año)

            tvDia = dia.toString()
            when(mes){
                0 -> tvMes = getString(R.string.ENERO)
                1 -> tvMes = getString(R.string.FEBRERO)
                2 -> tvMes = getString(R.string.MARZO)
                3 -> tvMes = getString(R.string.ABRIL)
                4 -> tvMes = getString(R.string.MAYO)
                5 -> tvMes = getString(R.string.JUNIO)
                6 -> tvMes = getString(R.string.JULIO)
                7 -> tvMes = getString(R.string.AGOSTO)
                8 -> tvMes = getString(R.string.SEPTIEMBRE)
                9 -> tvMes = getString(R.string.OCTUBRE)
                10 -> tvMes = getString(R.string.NOVIEMBRE)
                11 -> tvMes = getString(R.string.DICIEMBRE)
                else -> tvMes = null
            }
           inputFecha.setText("$tvDia de $tvMes  $tvAño")
        }else{
            Toast.makeText(context, getString(R.string.toast_date), Toast.LENGTH_SHORT).show()
        }


    }

        private fun calcularEdad(diaDeNacimiento:Int,mesDeNacimineto:Int,añoDeNacimiento:Int){

            val Calendario = Calendar.getInstance()
            val diaActual:Int  = Calendario.get(Calendar.DAY_OF_MONTH)
            val mesActual:Int = Calendario.get(Calendar.MONTH)
            val añoActual:Int = Calendario.get(Calendar.YEAR)


            if(mesActual > mesDeNacimineto){
                if(diaDeNacimiento>diaActual){
                    edadAlumno = (añoActual - añoDeNacimiento)
                }
                else if(diaDeNacimiento<=diaActual){
                    edadAlumno = (añoActual-añoDeNacimiento)
                }
            }
            else{
                if(diaActual<diaDeNacimiento){
                    edadAlumno = (añoActual-añoDeNacimiento)-1
                }
                else{
                    edadAlumno = (añoActual-añoDeNacimiento)
                }

            }
            println("$edadAlumno")
        }


    private fun calcularZignoZodiaco(diaDeNacimiento:Int,mesDeNacimineto:Int):String? {
        if(mesDeNacimineto == 0 && diaDeNacimiento >= 22 || mesDeNacimineto == 1 && diaDeNacimiento <=18 ){ return getString(R.string.ACUARIO)}
        if(mesDeNacimineto == 2 && diaDeNacimiento>=21 ||  mesDeNacimineto == 3 && diaDeNacimiento <=21 ){ return getString(R.string.ARIES)}
        if(mesDeNacimineto == 5 && diaDeNacimiento>=22 || mesDeNacimineto == 6 && diaDeNacimiento <=21 ){ return getString(R.string.CANCER)}
        if(mesDeNacimineto == 11 && diaDeNacimiento>=22 || mesDeNacimineto == 0 && diaDeNacimiento <=21 ){ return getString(R.string.CAPRICORNIO)}
        if(mesDeNacimineto == 9 && diaDeNacimiento>=22 || mesDeNacimineto == 10 && diaDeNacimiento <=21 ){ return getString(R.string.ESCORPIO)}
        if(mesDeNacimineto == 4 && diaDeNacimiento>=22 || mesDeNacimineto == 5 && diaDeNacimiento <=21 ){ return getString(R.string.GEMINIS)}
        if(mesDeNacimineto == 6 && diaDeNacimiento>=22 || mesDeNacimineto == 7 && diaDeNacimiento <=21 ){ return getString(R.string.LEO)}
        if(mesDeNacimineto == 8 && diaDeNacimiento>=24 || mesDeNacimineto == 9 && diaDeNacimiento <=21 ){ return getString(R.string.LIBRA)}
        if(mesDeNacimineto == 1 && diaDeNacimiento>=19 || mesDeNacimineto == 2 && diaDeNacimiento <=20 ){ return getString(R.string.PISCIS)}
        if(mesDeNacimineto == 10 && diaDeNacimiento>=22 || mesDeNacimineto == 11 && diaDeNacimiento <=21 ){ return getString(R.string.SAGRITARIO)}
        if(mesDeNacimineto == 3 && diaDeNacimiento>=22 || mesDeNacimineto == 4 && diaDeNacimiento <=21 ){ return getString(R.string.TAURO)}
        if(mesDeNacimineto == 7 && diaDeNacimiento>=22 || mesDeNacimineto == 8 && diaDeNacimiento <=23 ){ return getString(R.string.VIRGO)}

        else{
            return null
        }
    }

    private fun signoHoroscopoChino(añoDeNacimiento: Int):String?{
       var residuo = añoDeNacimiento%12
        when(residuo){
            0 -> return getString(R.string.MONO)
            1 -> return getString(R.string.GALLO)
            2 -> return getString(R.string.PERRO)
            3 -> return getString(R.string.CERDO)
            4 -> return getString(R.string.RATA)
            5 -> return getString(R.string.BUEY)
            6 -> return getString(R.string.TIGRE)
            7 -> return getString(R.string.CONEJO)
            8 -> return getString(R.string.DRAGON)
            9 -> return getString(R.string.SERPIENTE)
            10 -> return getString(R.string.CABALLO)
            11 -> return getString(R.string.CABRA)
            else -> return null

        }
    }


}