package com.saslebengy.goldenmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.saslebengy.goldenmenu.barTab.BarActivity;
import com.saslebengy.goldenmenu.soloPageVin.BiereActivity;
import com.saslebengy.goldenmenu.soloPageVin.ChampagneActivity;
import com.saslebengy.goldenmenu.soloPageVin.MagnumActivity;
import com.saslebengy.goldenmenu.soloPageVin.RoseActivity;
import com.saslebengy.goldenmenu.soloPageVin.VinAuVerreActivity;
import com.saslebengy.goldenmenu.vinsBlancTab.VinBlancActivity;
import com.saslebengy.goldenmenu.R;


/* loaded from: base/dex/classes2.dex */
public class AccueilVins extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_vins);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButtonRouge);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButtonBlanc);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imageButtonRose);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButtonVerre);
        //ImageButton imageButton5 = (ImageButton) findViewById(R.id.imageButtonDemi);
        ImageButton imageButton6 = (ImageButton) findViewById(R.id.imageButtonMagnum);
        ImageButton imageButton7 = (ImageButton) findViewById(R.id.imageButtonChampagne);
        ImageButton imageButton8 = (ImageButton) findViewById(R.id.imageButtonBiere);
        ImageButton imageButton9 = (ImageButton) findViewById(R.id.imageButtonBar);
        getWindow().addFlags(128);
    }

    public void clickOnRouge(View view) {
        startActivity(new Intent(this, CarteVinsActivity.class));
    }

    public void clickOnBar(View view) {
        startActivity(new Intent(this, BarActivity.class));
    }

    public void clickOnMagnum(View view) {
        startActivity(new Intent(this, MagnumActivity.class));
    }

    public void clickOnDemi(View view) {
        //startActivity(new Intent(this, DemiBouteillesActivity.class));
    }

    public void clickOnRose(View view) {
        startActivity(new Intent(this, RoseActivity.class));
    }

    public void clickOnBiere(View view) {
        startActivity(new Intent(this, BiereActivity.class));
    }

    public void clickOnChampagne(View view) {
        startActivity(new Intent(this, ChampagneActivity.class));
    }

    public void clickOnVinVerre(View view) {
        startActivity(new Intent(this, VinAuVerreActivity.class));
    }

    public void clickOnBlanc(View view) {
        startActivity(new Intent(this, VinBlancActivity.class));
    }
}