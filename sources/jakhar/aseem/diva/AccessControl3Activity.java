package jakhar.aseem.diva;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccessControl3Activity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0319R.layout.activity_access_control3);
        if (!PreferenceManager.getDefaultSharedPreferences(this).getString(getString(C0319R.string.pkey), "").isEmpty()) {
            ((Button) findViewById(C0319R.C0321id.aci3viewbutton)).setVisibility(0);
        }
    }

    public void addPin(View view) {
        Editor spedit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        String pin = ((EditText) findViewById(C0319R.C0321id.aci3Pin)).getText().toString();
        if (pin == null || pin.isEmpty()) {
            Toast.makeText(this, "Please Enter a valid pin!", 0).show();
            return;
        }
        Button vbutton = (Button) findViewById(C0319R.C0321id.aci3viewbutton);
        spedit.putString(getString(C0319R.string.pkey), pin);
        spedit.commit();
        if (vbutton.getVisibility() != 0) {
            vbutton.setVisibility(0);
        }
        Toast.makeText(this, "PIN Created successfully. Private notes are now protected with PIN", 0).show();
    }

    public void goToNotes(View view) {
        startActivity(new Intent(this, AccessControl3NotesActivity.class));
    }
}
