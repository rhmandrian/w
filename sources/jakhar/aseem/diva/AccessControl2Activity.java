package jakhar.aseem.diva;

import android.content.Intent;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class AccessControl2Activity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0319R.layout.activity_access_control2);
    }

    public void viewAPICredentials(View view) {
        RadioButton rbregnow = (RadioButton) findViewById(C0319R.C0321id.aci2rbregnow);
        Intent i = new Intent();
        boolean chk_pin = rbregnow.isChecked();
        i.setAction("jakhar.aseem.diva.action.VIEW_CREDS2");
        i.putExtra(getString(C0319R.string.chk_pin), chk_pin);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
            return;
        }
        Toast.makeText(this, "Error while getting Tveeter API details", 0).show();
        Log.e("Diva-aci1", "Couldn't resolve the Intent VIEW_CREDS2 to our activity");
    }
}
