package com.example.formulario.ui

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.formulario.Model.Alumno
import com.example.formulario.R

private var mactivity:MainActivity? = null

private var alumnoFI:Alumno? =  null

class ResultFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alumnoFI = requireArguments().getParcelable(MIALUMNO)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_result, container, false)
        var tvnombreResultado: TextView = view.findViewById(R.id.tvNombreResultado)
        var tvEdadResultado: TextView = view.findViewById(R.id.tvEdadResultado)
        var tvSignoZodiacoResultado: TextView = view.findViewById(R.id.tvSignoZodiacalResultado)
        var tvSignoChinoResultado: TextView = view.findViewById(R.id.tvSignoHoroscopoChinoResultado)
        var tvCorreo: TextView = view.findViewById(R.id.tvCorreoResultado)
        var tvNumeroDeCuenta: TextView = view.findViewById(R.id.tvNumeroCuentaResultado)
        var tvCarrera: TextView = view.findViewById(R.id.CarreraResultado)
        var ivIngenieria: ImageView = view.findViewById(R.id.ivCarrera)

        tvnombreResultado.text = alumnoFI?.getName() ?: "null"
        if(alumnoFI?.edad==1){
            tvEdadResultado.text = "${alumnoFI?.edad.toString()} " + getString(R.string.año_cumplido) ?: "null"
        }
        else{
            tvEdadResultado.text = "${alumnoFI?.edad.toString()} "+ getString(R.string.años_cumplidos) ?: "null"
        }

        tvSignoZodiacoResultado.text = alumnoFI?.signoZodiaco
        tvSignoChinoResultado.text = alumnoFI?.horoscopoChino
        tvCorreo.text = alumnoFI?.email
        tvNumeroDeCuenta.text = alumnoFI?.numeroDeCuenta.toString()
        tvCarrera.text = alumnoFI?.Ingenieria ?: null

        when(alumnoFI!!.Ingenieria){

            getString(R.string.AEROESPACIAL) -> ivIngenieria.setImageResource(R.drawable.aeroespacial)
            getString(R.string.CIVIL) ->ivIngenieria.setImageResource(R.drawable.civil)
            getString(R.string.GEOMATICA)->ivIngenieria.setImageResource(R.drawable.geomatica)
            getString(R.string.AMBIENTAL) ->ivIngenieria.setImageResource(R.drawable.ambiental)
            getString(R.string.GEOFISICA) ->ivIngenieria.setImageResource(R.drawable.geofisica)
            getString(R.string.GEOLOGICA)->ivIngenieria.setImageResource(R.drawable.geologica)
            getString(R.string.PETROLERA) ->ivIngenieria.setImageResource(R.drawable.petrolera)
            getString(R.string.MINAS_METALURGIA) ->ivIngenieria.setImageResource(R.drawable.minas)
            getString(R.string.COMPUTACION) ->ivIngenieria.setImageResource(R.drawable.computacion)
            getString(R.string.ELECTRICA_ELECTRONICA) ->ivIngenieria.setImageResource(R.drawable.electrica)
            getString(R.string.TELECOMUNICACIONES) ->ivIngenieria.setImageResource(R.drawable.telecom)
            getString(R.string.MECANICA)->ivIngenieria.setImageResource(R.drawable.mecanica)
            getString(R.string.INDUSTRIAL) ->ivIngenieria.setImageResource(R.drawable.industrial)
            getString(R.string.MECATRONICA)->ivIngenieria.setImageResource(R.drawable.mecatronica)
            getString(R.string.SISTEMAS_BIOMEDICOS) ->ivIngenieria.setImageResource(R.drawable.biomedicos)
            else -> {
                println("No carrera")
            }
        }

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mactivity = activity as? MainActivity
        mactivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mactivity?.supportActionBar?.title = getString(R.string.Resultado)



    }



    override fun onDestroy() {
        mactivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mactivity?.supportActionBar?.title = getString(R.string.Formulario)
        super.onDestroy()
    }

    companion object {
        private const val MIALUMNO = "alumno"
        fun newInstance(alumno: Alumno) = ResultFragment().apply {
            arguments = bundleOf(MIALUMNO to alumno)
        }
    }

}