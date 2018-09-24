package pamtech.com.zooapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * DB for animals and their different categories
 */
public class DatabaseHelper  extends SQLiteOpenHelper{


    private static final String TAG = "DBHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ZOOM";

    //table name
    private static final String  TABLE_ANIMAL ="animal";

    //animal table columns
    private static final String CATEGORY_NAME = "category";
    public static final String ANIMAL_NAME = "name";
    public static final String ANIMAL_DESCRIPTION = "description";
    public static final String SOUND_PATH = "sound";
    public static final String IMG_PATH = "img";

    //create table statement
    private static final String CREATE_TABLE_ANIMAL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ANIMAL + "("
            + CATEGORY_NAME + " TEXT PRIMARY KEY,"
            + ANIMAL_NAME + " TEXT,"
            + ANIMAL_DESCRIPTION + " TEXT,"
            + SOUND_PATH + " TEXT,"
            + IMG_PATH + " TEXT"
            + ")";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ANIMAL);
        Log.d(TAG, "onCreate: animal table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL);

        onCreate(sqLiteDatabase);
    }

    /**
     * query to get all the categories inside the database
     * @return all the categories in the database
     */
    public List<String> getCategories(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select DISTINCT category from " + TABLE_ANIMAL;
        Cursor cursor = database.rawQuery(query,null);

        List<String> categories = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                String category = cursor.getString(0);
                categories.add(category);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    /**
     * query to add animals to the database
     * @param animal new animal being added to the database
     */
    public void addAnimal(Animal animal){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CATEGORY_NAME, animal.getCategory());
        values.put(ANIMAL_NAME, animal.getName());
        values.put(ANIMAL_DESCRIPTION, animal.getDescription());
        values.put(SOUND_PATH, animal.getSoundPath());
        values.put(IMG_PATH, animal.getImg_path());

        database.insert(TABLE_ANIMAL,null,values);
        Log.d(TAG, "Animal created");
        database.close();
    }

    /**
     * query to get all the animals in the database in a particular category
     * @param cat_name animal category
     * @return all animals in the category
     */
    public List<Animal> getAnimals(String cat_name){
        SQLiteDatabase database = this.getReadableDatabase();


        String query = "SELECT * FROM " + TABLE_ANIMAL + " WHERE " + CATEGORY_NAME + " = " + "\'" + cat_name + "\'";

        Cursor cursor = database.rawQuery(query, null);

        List<Animal> animals = new ArrayList<>();

        if(cursor.moveToNext()){
            do{
                Animal animal = new Animal(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
                animals.add(animal);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return animals;
    }

    public Animal getAnimal(String name){
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ANIMAL + " WHERE name = " + name;
        
        Cursor cursor = database.rawQuery(query, null);
        
        Animal target = null;
        
        if(cursor.moveToNext()){
            do{
                target = new Animal(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return target;
    }

}
