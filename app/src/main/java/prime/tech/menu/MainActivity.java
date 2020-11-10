package prime.tech.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnQr;
    Button data;
    Button analysis;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_main);
        btnQr= findViewById(R.id.btnQr);
        data=findViewById(R.id.data);
        analysis=findViewById(R.id.analysisx);
        searchView=findViewById(R.id.place_autocomplete_search_button);


        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Analysis.class);
                startActivity(intent);
            }
        });
        btnQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Qr_scan.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"QR SCAN",Toast.LENGTH_SHORT).show();
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),DatabaseHelper.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return menuChoice(item);
    }

    public void createMenu(Menu menu){
        MenuItem mnu1 = menu.add(0,0,0, "Profile");{
            mnu1.setAlphabeticShortcut('a');
        }
        MenuItem mnu2 = menu.add(0,1,1, "Theme");{
            mnu2.setAlphabeticShortcut('b');
        }
        MenuItem mnu3 = menu.add(0,2,2, "About Us");{
            mnu3.setAlphabeticShortcut('c');
        }
        MenuItem mnu4 = menu.add(0,3,3, "Log out");{
            mnu4.setAlphabeticShortcut('c');
        }
    }
    private boolean menuChoice(MenuItem item){
        switch (item.getItemId()){
            case 0:
                Intent intent=new Intent(this,Profile.class);
                this.startActivity(intent);
                Toast.makeText(this,"You have clicked on Profile",
                Toast.LENGTH_SHORT).show();
                return true;
            case 1:
                Intent intent01= new Intent(getBaseContext(),Theme.class);
                startActivity(intent01);
                Toast.makeText(this,"You have clicked on Theme",
                Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this,"You have clicked on About us",
                Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(this,"You have on Logged out",
                Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;

    }
}
