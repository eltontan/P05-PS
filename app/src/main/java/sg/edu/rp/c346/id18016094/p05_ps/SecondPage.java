package sg.edu.rp.c346.id18016094.p05_ps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondPage extends AppCompatActivity {
    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(SecondPage.this);
        al = dbh.getAllSong();
        aa = new SongAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = al.get(position);
                Intent i = new Intent(SecondPage.this,
                        Third_page.class);
                i.putExtra("data", target);
                startActivityForResult(i, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(SecondPage.this);
            al.clear();
            al.addAll(dbh.getAllSong());
            dbh.close();
            aa.notifyDataSetChanged();
        }
    }
}
