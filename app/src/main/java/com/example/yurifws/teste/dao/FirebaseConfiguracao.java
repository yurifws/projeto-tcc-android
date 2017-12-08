package com.example.yurifws.teste.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by yurifws on 10/20/17.
 */

public class FirebaseConfiguracao {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth firebaseAuth;

    public static DatabaseReference getFirebase(){
        if (databaseReference == null)
            databaseReference = FirebaseDatabase.getInstance().getReference();

        return databaseReference;
    }

    public static FirebaseAuth getFirebaseAutentication(){
        if (firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance();

        return firebaseAuth;

    }
}
