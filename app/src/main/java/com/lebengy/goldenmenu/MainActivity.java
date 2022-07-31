package com.lebengy.goldenmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tomer.fadingtextview.FadingTextView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: base/dex/classes2.dex */
public class MainActivity extends AppCompatActivity {
    private FadingTextView fadingTextView;
    private ArrayList<String> listInfoList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String[] listInit = {"Bienvenue au restaurant"};

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FadingTextView fadingTextView = (FadingTextView) findViewById(R.id.infoTemp);
        this.fadingTextView = fadingTextView;
        fadingTextView.setTexts(this.listInit);
        getWindow().addFlags(128);
        getInfo();
    }

    private void getInfo() {
        this.db.collection("MessageTemp").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.MainActivity.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                } else if (value.size() <= 0) {
                } else {
                    MainActivity.this.listInfoList = new ArrayList();
                    Iterator<QueryDocumentSnapshot> it = value.iterator();
                    while (it.hasNext()) {
                        MainActivity.this.listInfoList.add(it.next().getString("message"));
                    }
                    MainActivity.this.dispoInfo();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoInfo() {
        //this.fadingTextView.setTexts((String[]) this.listInfoList.toArray(new String[R.styleable.ActionBar]));
    }

    public void clickOnVins(View view) {
        startActivity(new Intent(this, AccueilVins.class));
    }

    public void clickOnMenus(View view) {
        //startActivity(new Intent(this, LesMenus.class));
    }
}