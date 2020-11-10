package prime.tech.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

public class DatabaseHelper extends AppCompatActivity {
    private EditText editName, time, editStop, editAmount, editPass;
    private Button btnsave,veiw,update,delete;
    private ListView listView;


    DatabaseHelper1 databaseHelper1;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_database_helper);
        databaseHelper1=new DatabaseHelper1(this);

        editName = (EditText)findViewById(R.id.editName);
        time = (EditText)findViewById(R.id.time);
        editStop = (EditText)findViewById(R.id.editStop);
        editAmount = (EditText)findViewById(R.id.editAmount);
        editPass = (EditText)findViewById(R.id.editPass);

        btnsave = (Button) findViewById(R.id.btnSave);
        veiw=(Button)findViewById(R.id.veiw);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);

        searchView=findViewById(R.id.place_autocomplete_search_button);



        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

    }
    public void UpdateData(){
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate=databaseHelper1.upDateData(editName.getText().toString(),editStop.getText().toString(),
                                time.getText().toString(),editAmount.getText().toString(),editPass.getText().toString());
                        String REG_NO = editName.getText().toString();
                        String BUS_STOP = editStop.getText().toString();
                        String TIME = time.getText().toString();
                        String FARE = editAmount.getText().toString();
                        String PASSENGER = editPass.getText().toString();

                        if (!REG_NO.isEmpty()&&!BUS_STOP.isEmpty()&&!TIME.isEmpty()&&!FARE.isEmpty()&&!PASSENGER.isEmpty())
                            Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Data Not Updated",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void DeleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows=databaseHelper1.DeleteData(editName.getText().toString());
                if (deleteRows>0)
                    Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Data Not Deleted",Toast.LENGTH_LONG).show();

            }
        });
    }
 public void AddData(){
    btnsave.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   databaseHelper1.btnsave(editName.getText().toString(),editStop.getText().toString(),
                            time.getText().toString(),editAmount.getText().toString(),editPass.getText().toString());
                    String REG_NO = editName.getText().toString();
                    String BUS_STOP = editStop.getText().toString();
                    String TIME = time.getText().toString();
                    String FARE = editAmount.getText().toString();
                    String PASSENGER = editPass.getText().toString();


                    if (!REG_NO.isEmpty()&&!BUS_STOP.isEmpty()&&!TIME.isEmpty()&&!FARE.isEmpty()&&!PASSENGER.isEmpty())
                        Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),"Data Not inserted",Toast.LENGTH_LONG).show();


                }
            }
    );
 }
 public void ViewAll(){
     veiw.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Cursor res=databaseHelper1.getAllData();
             if (res.getCount()==0){
                 showMessage("Error","No Data");
                 return;
             }
             StringBuffer buffer=new StringBuffer();
             while (res.moveToNext()){
                 buffer.append("REG_NO:"+res.getString(0)+"\n");
                 buffer.append("TIME:"+res.getString(1)+"\n");
                 buffer.append("BUS_STOP:"+res.getString(2)+"\n");
                 buffer.append("FARE:"+res.getString(3)+"\n");
                 buffer.append("PASSENGERS:"+res.getString(4)+"\n\n");
             }
             showMessage("Data",buffer.toString());
         }
     });
 }
 public void showMessage(String title,String Message){
     AlertDialog.Builder builder= new AlertDialog.Builder(this);
     builder.setCancelable(true);
     builder.setTitle(title);
     builder.setMessage(Message);
     builder.show();
 }
}

