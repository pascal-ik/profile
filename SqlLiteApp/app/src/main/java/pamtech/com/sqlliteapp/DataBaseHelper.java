package pamtech.com.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "EDBTag";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmployeeDB";
    private static final String TABLE_NAME = "Employee";
    private static final String EMP_ID = "ID";
    private static final String EMP_FNAME = "firstName";
    private static final String EMP_MI = "middleInitial";
    private static final String EMP_LNAME = "lastName";
    private static final String EMP_PHONE = "phone";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + EMP_ID + " TEXT PRIMARY KEY,"
                + EMP_FNAME + " TEXT,"
                + EMP_MI + " TEXT,"
                + EMP_LNAME + " TEXT, "
                + EMP_PHONE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS " + TABLE_NAME));
        onCreate(sqLiteDatabase);
    }

    public void addNewEmployee(Employee employee) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMP_ID, employee.getId());
        values.put(EMP_FNAME, employee.getFirstName());
        values.put(EMP_MI, employee.getMiddleInitial());
        values.put(EMP_LNAME, employee.getLastName());
        values.put(EMP_PHONE, employee.getPhone());

        database.insert(TABLE_NAME, null, values);
        Log.d(TAG, "Employee Saved to DataBase");
        database.close();

    }

    public List<Employee> getEmployees() {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        List<Employee> employees = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                //use cursor to get each column and instantiate a new employee object
                Employee employee = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                employees.add(employee);//add object to array list
            }
            while(cursor.moveToNext());//continue to do this while columns exist in the db
        }
        return employees;// return the array list of employees
    }
}
