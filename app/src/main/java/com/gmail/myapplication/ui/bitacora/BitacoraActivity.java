package com.gmail.myapplication.ui.bitacora;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gmail.myapplication.R;
import com.gmail.myapplication.adapter.BitacoraAdapter;
import com.gmail.myapplication.repository.database.entity.Bitacora;
import com.gmail.myapplication.util.Util;

import java.util.List;

public class BitacoraActivity extends AppCompatActivity implements BitacoraContract.View{

    private BitacoraViewModel mBitacoraViewModel;
    private BitacoraContract.Presenter mPresenter;
    private BitacoraContract.View mView;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Util.toast(getString(R.string.de_nuevo));
        mPresenter.back();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        initComponents();
        mView = this;
        mPresenter = new BitacoraPresenter(mView,mBitacoraViewModel);
    }

    public void initComponents(){
        RecyclerView recyclerView = findViewById(R.id.rvBitacora);
        final BitacoraAdapter adapter = new BitacoraAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration( new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBitacoraViewModel = ViewModelProviders.of(this).get(BitacoraViewModel.class);
        mBitacoraViewModel.findAll().observe(this, (@Nullable List<Bitacora> bitacoras) ->
            adapter.setData(bitacoras)
        );

        findViewById(R.id.fabEntrada).setOnClickListener( view ->
            mPresenter.clickEntrada(this , false));

        findViewById(R.id.fabSalida).setOnClickListener( view ->
            mPresenter.clickSalida(this , false));

        findViewById(R.id.fabEntrada).setOnLongClickListener(  v -> {
            mPresenter.clickEntrada(this , true);
            return true;
        });

        findViewById(R.id.fabSalida).setOnLongClickListener( v -> {
            mPresenter.clickSalida(this , true);
            return true;
        });


    }


    @Override
    public void back() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode , resultCode , data);
    }

    @Override
    public void showMessage(String text) {
        Util.toast(text);
    }
}
