package com.example.doortodooradminapp

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FireBaseDatabase
{

    private var database:FirebaseDatabase?=null
    private var mContext:Context?=null
    constructor( mContext:Context)
    {
        database= FirebaseDatabase.getInstance()
        this.mContext=mContext
    }
    fun insertServiceRecord(service:ServiceDetails)
    {   val childName:String=convertToProperDatabaseChildName(service.serviceCity)
        database?.reference?.child(childName)?.setValue(service)
            ?.addOnCompleteListener{
                Toast.makeText(mContext,"Service Data Added SuccesFully",Toast.LENGTH_LONG).show()
            }?.addOnFailureListener{
                Toast.makeText(mContext,"Service Data Addition Failed Check your internet connection or try again after sometime",Toast.LENGTH_LONG).show()
        }


    }
    fun convertToProperDatabaseChildName(city:String):String
    {
        val regex=Regex("[^A-Za-z]")
        var properDatavaseChildName:String=city.replace(regex,"")
        return properDatavaseChildName
    }
}