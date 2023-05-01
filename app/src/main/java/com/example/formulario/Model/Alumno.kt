package com.example.formulario.Model

import android.os.Parcelable
import android.widget.Toast
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
class Alumno(private var name:String
             ) : Parcelable {

    var edad:Int? = null
    set(value){
        if(value!! >= 0){
            field = value
        }
    }

    var Ingenieria:String? = null
        set(value){
            field = value!!
            }

    var numeroDeCuenta:String? = null
    set(value){
        if (value?.length == 9) {
            field = value
        }
    }

    var signoZodiaco:String? = null

    var horoscopoChino:String? = null



    var email:String? = null
    set(value){
        if (value!!.contains('@') && value!!.contains(".com")){
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
