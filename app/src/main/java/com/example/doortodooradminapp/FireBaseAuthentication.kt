package com.example.doortodooradminapp

import android.content.Context
import com.google.firebase.auth.FirebaseAuth

class FireBaseAuthentication
{   private var mContext:Context?=null
    private var mauth:FirebaseAuth?=null
    constructor(mContext:Context)
    {   mauth=FirebaseAuth.getInstance()
        this.mContext=mContext
    }
    fun siginEmailPasswordAuth(email:String,password:String):Boolean
    {
        var returnValue=false

        return returnValue
    }

}