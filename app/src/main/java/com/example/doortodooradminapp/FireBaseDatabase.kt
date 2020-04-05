package com.example.doortodooradminapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
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
    fun insertServiceRecord(service:ServiceDetails):Boolean
    {   val firstChildName:String=convertToProperDatabaseChildName(service.serviceCity)
        var secondChildName:String=convertToProperDatabaseChildName(service.serviceName)
        var returnValue:Boolean=false
        database?.reference?.child(firstChildName)?.child(secondChildName)?.setValue(service)
            ?.addOnCompleteListener{
                Toast.makeText(mContext,"Service Data Added SuccesFully",Toast.LENGTH_LONG).show()
                returnValue=true
            }?.addOnFailureListener{
                Toast.makeText(mContext,"Service Data Addition Failed Check your internet connection or try again after sometime",Toast.LENGTH_LONG).show()
                returnValue=false
        }
        return returnValue
    }
    fun checkDuplicateServiceName(city:String,serviceName:String):Boolean
    {
        val childName:String=convertToProperDatabaseChildName(city)
        var checkString:String=convertToProperDatabaseChildName(serviceName)
        var returnValue:Boolean=false
        database?.reference?.child(childName)?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val item:Iterator<DataSnapshot> = dataSnapshot.children.iterator()
                while(item.hasNext())
                {
                    if((item.next().value.toString())==(checkString))
                    {
                        returnValue=true
                        break;
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("databe error","The read failed: "+ databaseError.code)
                //Toast.makeText(mContext,"Failed to connect to server check your internet connection or try after sometime",Toast.LENGTH_LONG).show()
                returnValue=false
            }
        })
        return returnValue
    }
    fun deleteService(city: String,serviceName: String):Boolean
    {   val firstChildName:String=convertToProperDatabaseChildName(city)
        val secondChildName:String=convertToProperDatabaseChildName(serviceName)
        var returnValue:Boolean=false
        database?.reference?.child(firstChildName)?.child(secondChildName)?.removeValue()
            ?.addOnCompleteListener{
                Toast.makeText(mContext,"Service:+ "+secondChildName+"in city: "+firstChildName+" deleted SuccesFully",Toast.LENGTH_LONG).show()
                returnValue=true
            }?.addOnFailureListener{
                Toast.makeText(mContext,"Service Data Deletion Failed Check your internet connection or try again after sometime",Toast.LENGTH_LONG).show()
                returnValue=false
            }
        return returnValue
    }
    fun updateServiceValues(city: String,serviceName: String,attributeName:String,valueTobeChanged:String):Boolean
    {  val firstChildName:String=convertToProperDatabaseChildName(city)
        val secondChildName:String=convertToProperDatabaseChildName(serviceName)
        var returnValue:Boolean=false
        database?.reference?.child(firstChildName)?.child(secondChildName)?.child(attributeName)?.setValue(valueTobeChanged)
            ?.addOnCompleteListener{
                Toast.makeText(mContext,"Attribute "+attributeName+"for Service:+ "+secondChildName+"in city: "+firstChildName+" updated SuccesFully",Toast.LENGTH_LONG).show()
                returnValue=true
            }?.addOnFailureListener{
                Toast.makeText(mContext,"Service Data UpdateFailed Check your internet connection or try again after sometime",Toast.LENGTH_LONG).show()
                returnValue=false
            }
        return returnValue
    }

   private fun convertToProperDatabaseChildName(city:String):String
    {
        val regex=Regex("[^A-Za-z0-9]")
        var properDatavaseChildName:String=city.replace(regex,"")
        return properDatavaseChildName.toLowerCase().trim()
    }

}