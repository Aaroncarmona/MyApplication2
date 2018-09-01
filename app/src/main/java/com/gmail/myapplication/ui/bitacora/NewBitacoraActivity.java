package com.gmail.myapplication.ui.bitacora;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gmail.myapplication.R;

public class NewBitacoraActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.gmail.myapplication.insertbitacora.REPLY";
    public static final String EXTRA_TIPO = "EXTRA_TIPO";
    public static final String EXTRA_NOTA = "EXTRA_NOTA";

    private TextInputEditText nota;
    private TextView tipo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora_new);
        nota = findViewById(R.id.tietnota);
        tipo = findViewById(R.id.tipo);

        tipo.setText((getIntent().getIntExtra(EXTRA_TIPO, -1) == 0 ? getString(R.string.entrada) : getString(R.string.salida)));

        findViewById(R.id.fabSave).setOnClickListener( (View v ) ->{
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(nota.getText().toString())){
                setResult(RESULT_CANCELED , replyIntent);
            }else{
                String snota = nota.getText().toString();
                int tipo = getIntent().getIntExtra(EXTRA_TIPO , -1);
                replyIntent.putExtra(EXTRA_NOTA , snota);
                replyIntent.putExtra(EXTRA_TIPO , tipo);
                setResult(RESULT_OK , replyIntent);
            }
            finish();
        });
    }
}
