package com.gmail.myapplication.ui.bitacora;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.gmail.myapplication.R;
import com.gmail.myapplication.adapter.BitacoraAdapter;
import com.gmail.myapplication.repository.database.entity.Bitacora;
import com.gmail.myapplication.repository.database.entity.Pokemon;
import com.gmail.myapplication.util.Util;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.myapplication.ui.bitacora.BitacoraPresenter.NEW_BITACORA_REQUEST_CODE;

public class BitacoraActivity extends AppCompatActivity implements BitacoraContract.View{

    private static final String TAG = BitacoraActivity.class.getSimpleName();

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



    @SuppressLint("CheckResult")
    public void initComponents(){
        RecyclerView recyclerView = findViewById(R.id.rvBitacora);
        final BitacoraAdapter adapter = new BitacoraAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration( new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getBaseContext(), R.anim.layout_animation_fall_down);
        //android:layoutAnimation="@anim/layout_animation_fall_down"
        recyclerView.setLayoutAnimation(controller);


        mBitacoraViewModel = ViewModelProviders.of(this).get(BitacoraViewModel.class);

        mBitacoraViewModel.mPokemon.observe(this, (@Nullable List<Pokemon> pokemons) -> {
            Log.d(TAG , "RANDOM : " + pokemons.size());
            mPresenter.randomPokemon(pokemons);
        });

        mBitacoraViewModel.mBitacoras.observe(this, (@Nullable List<Bitacora> bitacoras) -> {
            adapter.setData(bitacoras);
        });

        Observable.interval( 3 , TimeUnit.SECONDS)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
        ( next ) -> {
            /*
            * si hay internet -> busca y agrega
            * si salio algo mal -> regresa lo que hay en base
            * */
            Log.d(TAG , String.valueOf(next));
            if ( Util.isInternet() ){
                mBitacoraViewModel.findByIdPk(Integer.parseInt(String.valueOf(next)));
            }else {
                mPresenter.randomPokemon(mBitacoraViewModel.mPokemon.getValue());
            }

        } , err -> {
            /*
            * si sale algo mal con el interval
            * */
            mPresenter.randomPokemon(mBitacoraViewModel.mPokemon.getValue());
        });

        findViewById(R.id.fabEntrada).setOnClickListener( view -> mPresenter.clickEntrada(
            new Intent( this , NewBitacoraActivity.class), false)
        );

        findViewById(R.id.fabSalida).setOnClickListener( view -> mPresenter.clickSalida(
            new Intent(this , NewBitacoraActivity.class) , false )
        );

        findViewById(R.id.fabEntrada).setOnLongClickListener(  v -> {
            mPresenter.clickEntrada(null, true);
            return true;
        });

        findViewById(R.id.fabSalida).setOnLongClickListener( v -> {
            mPresenter.clickSalida( null , true);
            return true;
        });
    }


    @Override
    public void back() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode , resultCode , data);
    }

    @Override
    public void showMessage(String text) {
        Util.toast(text);
    }

    @Override
    public void showPokemon(String value) {
        ((TextView) findViewById(R.id.poke_name)).setText(value);
    }

    @Override
    public void toggleButtons(boolean is) {
        findViewById(R.id.fabEntrada).setClickable(is);
        findViewById(R.id.fabSalida).setClickable(is);
    }

    @Override
    public void startActivity(Intent i, int requestcode) {
        startActivityForResult(i , requestcode);
    }
}
