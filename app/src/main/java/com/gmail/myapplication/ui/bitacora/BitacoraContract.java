package com.gmail.myapplication.ui.bitacora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.gmail.myapplication.repository.database.entity.Pokemon;

import java.util.List;

public class BitacoraContract {
    protected interface Presenter{
        void back();
        void clickEntrada(Intent i,boolean isLong);
        void clickSalida(Intent i, boolean isLong);
        void onActivityResult(int requestCode , int resultCode , Intent data);
        void randomPokemon(List<Pokemon> pokemons);
        void findAllPokemon();
        void backRealize();

    }

    protected interface View{
        void back();
        void showMessage(String text);
        void showPokemon(String value);
        void toggleButtons(boolean is);
        void startActivity(Intent i , int requestcode);
    }

    protected interface Interactor{
        void randomPokemon(List<Pokemon> pokemonList);
    }
}
