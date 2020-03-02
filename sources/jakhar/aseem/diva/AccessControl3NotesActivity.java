package jakhar.aseem.diva;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AccessControl3NotesActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0319R.layout.activity_access_control3_notes);
    }

    public void accessNotes(View view) {
        EditText pinTxt = (EditText) findViewById(C0319R.C0321id.aci3notesPinText);
        Button abutton = (Button) findViewById(C0319R.C0321id.aci3naccessbutton);
        if (pinTxt.getText().toString().equals(PreferenceManager.getDefaultSharedPreferences(this).getString(getString(C0319R.string.pkey), ""))) {
            ((ListView) findViewById(C0319R.C0321id.aci3nlistView)).setAdapter(new SimpleCursorAdapter(this, C0319R.layout.notes_entry, getContentResolver().query(NotesProvider.CONTENT_URI, new String[]{"_id", "title", "note"}, null, null, null), new String[]{"title", "note"}, new int[]{C0319R.C0321id.title_entry, C0319R.C0321id.note_entry}, 0));
            pinTxt.setVisibility(4);
            abutton.setVisibility(4);
            return;
        }
        Toast.makeText(this, "Please Enter a valid pin!", 0).show();
    }
}
