package jakhar.aseem.diva;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class NotesProvider extends ContentProvider {
    static final String AUTHORITY = "jakhar.aseem.diva.provider.notesprovider";
    static final Uri CONTENT_URI = Uri.parse("content://jakhar.aseem.diva.provider.notesprovider/notes");
    static final String CREATE_TBL_QRY = " CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, note TEXT NOT NULL);";
    static final String C_ID = "_id";
    static final String C_NOTE = "note";
    static final String C_TITLE = "title";
    static final String DBNAME = "divanotes.db";
    static final int DBVERSION = 1;
    static final String DROP_TBL_QRY = "DROP TABLE IF EXISTS notes";
    static final int PATH_ID = 2;
    static final int PATH_TABLE = 1;
    static final String TABLE = "notes";
    static final UriMatcher urimatcher = new UriMatcher(-1);
    SQLiteDatabase mDB;

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, NotesProvider.DBNAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(NotesProvider.DROP_TBL_QRY);
            db.execSQL(NotesProvider.CREATE_TBL_QRY);
            db.execSQL("INSERT INTO notes(title,note) VALUES ('office', '10 Meetings. 5 Calls. Lunch with CEO');");
            db.execSQL("INSERT INTO notes(title,note) VALUES ('home', 'Buy toys for baby, Order dinner');");
            db.execSQL("INSERT INTO notes(title,note) VALUES ('holiday', 'Either Goa or Amsterdam');");
            db.execSQL("INSERT INTO notes(title,note) VALUES ('Expense', 'Spent too much on home theater');");
            db.execSQL("INSERT INTO notes(title,note) VALUES ('Exercise', 'Alternate days running');");
            db.execSQL("INSERT INTO notes(title,note) VALUES ('Weekend', 'b333333333333r');");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }

    static {
        urimatcher.addURI(AUTHORITY, TABLE, 1);
        urimatcher.addURI(AUTHORITY, "notes/#", 2);
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        switch (urimatcher.match(uri)) {
            case 1:
                count = this.mDB.delete(TABLE, selection, selectionArgs);
                break;
            case 2:
                count = this.mDB.delete(TABLE, "_id = " + uri.getLastPathSegment() + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Divanotes(delete): Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public String getType(Uri uri) {
        switch (urimatcher.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/vnd.jakhar.notes";
            case 2:
                return "vnd.android.cursor.item/vnd.jakhar.notes";
            default:
                throw new IllegalArgumentException("Divanotes: Unsupported URI: " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        long row = this.mDB.insert(TABLE, "", values);
        if (row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Divanotes: Fail to add a new record into " + uri);
    }

    public boolean onCreate() {
        this.mDB = new DBHelper(getContext()).getWritableDatabase();
        if (this.mDB == null) {
            return false;
        }
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        switch (urimatcher.match(uri)) {
            case 1:
                break;
            case 2:
                queryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Divanotes(query): Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = C_TITLE;
        }
        Cursor cursor = queryBuilder.query(this.mDB, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count;
        switch (urimatcher.match(uri)) {
            case 1:
                count = this.mDB.update(TABLE, values, selection, selectionArgs);
                break;
            case 2:
                count = this.mDB.update(TABLE, values, "_id = " + uri.getLastPathSegment() + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Divanotes(update): Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
