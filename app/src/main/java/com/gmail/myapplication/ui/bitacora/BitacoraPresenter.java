package com.gmail.myapplication.ui.bitacora;

import android.content.Intent;
import android.util.Log;

import com.gmail.myapplication.R;
import com.gmail.myapplication.Vapp;
import com.gmail.myapplication.repository.database.entity.Bitacora;
import com.gmail.myapplication.repository.database.entity.Pokemon;
import com.gmail.myapplication.util.Util;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class BitacoraPresenter implements BitacoraContract.Presenter {
    public static final String TAG = BitacoraPresenter.class.getSimpleName();

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
    public void clickEntrada(Intent i , boolean isLong) {
        if ( isLong ) {
            mBitacoraViewModel
                .insert(new Bitacora(new Date().toString() , Vapp.getContext().getString(R.string.sin_nota) , 0));
            return;
        }

        mView.toggleButtons(false);
        i.putExtra(NewBitacoraActivity.EXTRA_TIPO,0)
        .addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);

        mView.startActivity(i,NEW_BITACORA_REQUEST_CODE);
    }

    @Override
    public void clickSalida(Intent i, boolean isLong) {
        if ( isLong ) {
            mBitacoraViewModel
                .insert(new Bitacora(new Date().toString() , Vapp.getContext().getString(R.string.sin_nota) , 0));
            return;
        }
        mView.toggleButtons(false);
        i.putExtra(NewBitacoraActivity.EXTRA_TIPO, 1)
        .addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        mView.startActivity(i , NEW_BITACORA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG , "result " + resultCode + " " + requestCode);
        mView.toggleButtons(true);

        if (requestCode == NEW_BITACORA_REQUEST_CODE && resultCode == RESULT_OK) {

            Log.d(TAG , "RESULT");
            mBitacoraViewModel
                .insert(new Bitacora(new Date().toString()
                    , data.getStringExtra(NewBitacoraActivity.EXTRA_NOTA)
                    , data.getIntExtra(NewBitacoraActivity.EXTRA_TIPO,-1)));
        } else {
            //mView.showMessage(Vapp.getContext().getString(R.string.nodata));
        }
    }

    @Override
    public void randomPokemon(List<Pokemon> pokemons) {
        if ( pokemons.size() == 0 ) {
            mView.showPokemon(Vapp.getContext().getString(R.string.no_poke).toUpperCase());
            return;
        }
        mView.showPokemon(pokemons.get(Util.random(0 , pokemons.size()-1)).getName().toUpperCase());
    }

    @Override
    public void findAllPokemon() {


    }



    @Override
    public void backRealize() {

    }
}
