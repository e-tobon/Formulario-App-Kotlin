package com.example.formulario.Model

import android.widget.Toast
import java.util.Date

class Alumno(private var name:String,
             private var lastName:String,
             ) {

    var numeroDeCuenta:String? = null
    set(value){
        if (value?.length == 9) {
            field = value
        }
    }

    var email:String? = null
    set(value){
        if (value!!.contains('@')){
            field = value
        }
    }

    //Mark:- Getters and Setters Methods
    fun setName(n:String){
        this.name = n
    }
    fun getName():String{
        return this.name
    }
    fun setLastName(ln:String){
        this.lastName = ln
    }
    fun getLastName():String{
        return this.lastName
    }


    companion object{
        var CarrerasIngenieria:ArrayList<String> = arrayListOf(
            "Ingeniería Aeroespacial",
            "Ingenieria Civil",
            "Ingeniería Geomática",
            "Ingeniería Ambiental",
            "Ingeniería Geofísica",
            "Ingeniería Geológica",
            "Ingeniería Petrolera",
            "Ingeniería de Minas y Metalurgia",
            "Ingeniería en Computación",
            "Ingeniería Eléctrica Electrónica",
            "Ingeniería en Telecomunicaciones",
            "Ingeniería Mecánica",
            "Ingeniería Industrial",
            "Ingeniería Mecatrónica",
            "Ingeniería en Sistemas Biomédicos"

            )

    }

}
