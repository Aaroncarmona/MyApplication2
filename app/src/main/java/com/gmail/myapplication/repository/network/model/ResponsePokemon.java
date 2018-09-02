package com.gmail.myapplication.repository.network.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePokemon implements Parcelable{
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<ResponsePokemon> CREATOR = new Creator<ResponsePokemon>() {
        @Override
        public ResponsePokemon createFromParcel(Parcel parcel) {
            return new ResponsePokemon(parcel);
        }

        @Override
        public ResponsePokemon[] newArray(int i) {
            return (new ResponsePokemon[i]);
        }
    };

    public ResponsePokemon(Parcel parcel) {
        this.name = ((String) parcel.readValue(String.class.getClassLoader()));
    }

    public ResponsePokemon(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        b.append("name : "+name);
        return b.toString();
    }
}
