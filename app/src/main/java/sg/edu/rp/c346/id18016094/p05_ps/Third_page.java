package sg.edu.rp.c346.id18016094.p05_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Third_page extends AppCompatActivity {

    EditText etID, etName, etTitle, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg1;
    Song data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);

        etID = findViewById(R.id.editTextSongID);
        etName = findViewById(R.id.editTextSinger1);
        etTitle = findViewById(R.id.editTextTitle1);
        etYear = findViewById(R.id.editTextYear1);
        btnCancel = findViewById(R.id.buttonCancel);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText("ID: " + data.get_id());
        etName.setText(data.getSingers());
        etTitle.setText(data.getTitle());
        etYear.setText(data.getYear()+"");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedButtonId = rg1.getCheckedRadioButtonId();
                // Get the radio button object from the Id we had gotten above
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                DBHelper dbh = new DBHelper(Third_page.this);
                data.setSingers(etName.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
           
                dbh.updateSong(data);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Third_page.this);
                dbh.deleteNote(data.get_id());
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
