package co.il.shivhit.buy_a_bread;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etMoney;
    private Button btnGoBuy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMoney  = findViewById((R.id.etMoney));
        btnGoBuy = findViewById(R.id.btnGoBuy);
        btnGoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SupermarketActivity.class);
                intent.putExtra("MONEY", Double.valueOf(etMoney.getText().toString()));
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Buy bread")
                .setCancelable(true)
                .setPositiveButton("OK", null);

        if (resultCode == RESULT_OK){
            double change = data.getDoubleExtra("CHANGE", -1);
            builder.setMessage("Cange is: " + change);
            builder.setIcon(R.drawable.halla);
        }
        else {
            builder.setMessage("No halla");
            builder.setIcon(R.drawable.no_halla);
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}