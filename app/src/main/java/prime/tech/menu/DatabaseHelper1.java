package prime.tech.menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptGroup;

import com.google.android.gms.flags.Flag;

import java.util.ArrayList;

public class DatabaseHelper1 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="database.db";
    public static final String TABLE_NAME="table_name";
    public static final String COL_1="REG_NO";
    public static final String COL_2="TIME";
    public static final String COL_3="BUS_STOP";
    public static final String COL_4="FARE";
    public static final String COL_5="PASSENGERS";

    public DatabaseHelper1(Context context){super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable="create table " + TABLE_NAME+"(REG_NO CHAR PRIMARY KEY,TIME TEXT," +
                "BUS_STOP TEXT,FARE TEXT,PASSENGERS TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
      onCreate(db);

    }
    public boolean btnsave(String reg_no, String time, String bus_stop, String fare, String passengers){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,reg_no);
        contentValues.put(COL_2,time);
        contentValues.put(COL_3,bus_stop);
        contentValues.put(COL_4,fare);
        contentValues.put(COL_5,passengers);
        //add values into table
        Long result =sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("Select* from "+TABLE_NAME,null);
        return res;
    }
    public boolean upDateData(String reg_no,String time,String bus_stop,String fare,String passengers){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,reg_no);
        contentValues.put(COL_2,time);
        contentValues.put(COL_3,bus_stop);
        contentValues.put(COL_4,fare);
        contentValues.put(COL_5,passengers);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"REG_NO=?",new String[]{reg_no});
        return true;
    }
    public Integer DeleteData(String reg_no){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"REG_NO=?",new String[]{reg_no});


    }

}
