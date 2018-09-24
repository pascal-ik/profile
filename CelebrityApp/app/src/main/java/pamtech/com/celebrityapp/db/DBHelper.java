package pamtech.com.celebrityapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pamtech.com.celebrityapp.Celebrity;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CELEBRITY";

    public static final String TABLE_NAME = "celebrity";

    public static final String CELEB_ID = "id";
    public static final String CELEB_NAME = "name";
    public static final String CELEB_DESC = "description";
    public static final String CELEB_IMG = "image";

    //create table statement
    public static final String CREATE_TABLE_CELEBRITY = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + CELEB_ID + " TEXT PRIMARY KEY,"
            + CELEB_NAME + " TEXT,"
            + CELEB_DESC + " TEXT,"
            + CELEB_IMG + " TEXT"
            + ")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TABLE_CELEBRITY);
        Log.d(TAG, "onCreate: Celebrity table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

       onCreate(db);
    }

    /**
     * add a new celeb into the database
     * @param celeb
     */
    public void addCelebrity(Celebrity celeb){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CELEB_ID, celeb.getId());
        cv.put(CELEB_NAME, celeb.getName());
        cv.put(CELEB_DESC, celeb.getDescription());
        cv.put(CELEB_IMG, celeb.getImageResId());

        database.insert(TABLE_NAME, null,cv);

        Log.d(TAG, "addCelebrity: Celebrity added");
        database.close();
    }

    /**
     * get the names of all the celebrities in the database
     * @return
     */
    public List<String> getCelebNames(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select name from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        List<String> names = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                names.add(name);
            }
            while(cursor.moveToNext());
        }
        return names;
    }

    /**
     * get a certain celeb
     * @param Cname
     * @return
     */
    public Celebrity getCeleb(String Cname){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select image, description  from " + TABLE_NAME + " where name = " +  Cname;

        Cursor cursor = database.rawQuery(query, null);

        Celebrity target = null;

        if(cursor.moveToFirst()){
            do{
                target = new Celebrity(cursor.getString(1), cursor.getInt(0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return target;

    }


}
