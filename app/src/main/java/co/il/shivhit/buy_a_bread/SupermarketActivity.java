package co.il.shivhit.buy_a_bread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class SupermarketActivity extends AppCompatActivity {

    private ImageView ivHalla;
    private EditText   etPay;
    private Button     btnGoBack;

    private double     monetTaken;
    private boolean    isHalla;
    private Random     random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket);

        monetTaken = getIntent().getDoubleExtra("MONEY", -1);

        InitializeViews();

        random = new Random();
        isHalla = (random.nextInt(2) == 0) ? true : false;

        if (isHalla)
            ivHalla.setImageResource(R.drawable.halla);
        else
            ivHalla.setImageResource((R.drawable.no_halla));
    }

    private void InitializeViews() {
        ivHalla   = findViewById((R.id.ivHalla));
        etPay     = findViewById(R.id.etPay);
        btnGoBack = findViewById(R.id.btnGoBack);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHalla) {
                    Intent intent = new Intent();
                    intent.putExtra("CHANGE", monetTaken - Double.valueOf(etPay.getText().toString()));
                    setResult(RESULT_OK, intent);
                }
                else{
                    setResult(RESULT_CANCELED);
                }
                finish();
            }
        });
    }
}