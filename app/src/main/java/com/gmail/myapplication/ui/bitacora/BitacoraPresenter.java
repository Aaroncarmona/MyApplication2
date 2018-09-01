package com.gmail.myapplication.ui.bitacora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.gmail.myapplication.R;
import com.gmail.myapplication.Vapp;
import com.gmail.myapplication.repository.database.entity.Bitacora;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class BitacoraPresenter implements BitacoraContract.Presenter {
    BitacoraContract.View mView;
    BitacoraViewModel mBitacoraViewModel;

    boolean checkBack = false;
    public static final int NEW_BITACORA_REQUEST_CODE = 1;

    public BitacoraPresenter(BitacoraContract.View view , BitacoraViewModel viewModel) {
        this.mView = view;
        this.mBitacoraViewModel = viewModel;
    }

    @Override
    public void back() {
        if ( checkBack ) mView.back();
        checkBack = true;
        Single.timer( 2 , TimeUnit.SECONDS , Schedulers.newThread())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe( (Long aLong)->{
            checkBack = false;
        } , (err) -> {
            mView.showMessage(Vapp.getContext().getString(R.string.error));
        });
    }

    @Override
    public void clickEntrada(AppCompatActivity appCompatActivity , boolean isLong) {
        if ( isLong ) {
            Bitacora b = new Bitacora();
            b.setNota("Sin nota");
            b.setTipo(0);
            b.setDate( new Date().toString());
            mBitacoraViewModel.insert(b);
            return;
        }

        appCompatActivity.startActivityForResult(
            new Intent(appCompatActivity , NewBitacoraActivity.class)
                .putExtra(NewBitacoraActivity.EXTRA_TIPO,0)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP)
                , NEW_BITACORA_REQUEST_CODE);
    }

    @Override
    public void clickSalida(AppCompatActivity appCompatActivity , boolean isLong) {
        if ( isLong ) {
            Bitacora b = new Bitacora();
            b.setNota("Sin nota");
            b.setTipo(1);
            b.setDate( new Date().toString());
            mBitacoraViewModel.insert(b);
            return;
        }

        appCompatActivity.startActivityForResult(
                new Intent(appCompatActivity , NewBitacoraActivity.class)
                .putExtra(NewBitacoraActivity.EXTRA_TIPO,1)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP)
                , NEW_BITACORA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_BITACORA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitacora b = new Bitacora();
            b.setDate(new Date().toString());
            b.setTipo(data.getIntExtra(NewBitacoraActivity.EXTRA_TIPO,-1));
            b.setNota(data.getStringExtra(NewBitacoraActivity.EXTRA_NOTA));
            mBitacoraViewModel.insert(b);
        } else {
            //mView.showMessage(Vapp.getContext().getString(R.string.nodata));
        }
    }

    @Override
    public void backRealize() {

    }
}
