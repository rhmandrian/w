package jakhar.aseem.diva;

import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.widget.TextView;

public class APICredsActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0319R.layout.activity_apicreds);
        ((TextView) findViewById(C0319R.C0321id.apicTextView)).setText("API Key: 123secretapikey123\nAPI User name: diva\nAPI Password: p@ssword");
    }
}
