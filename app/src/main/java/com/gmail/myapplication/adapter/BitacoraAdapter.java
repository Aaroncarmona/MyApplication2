package com.gmail.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.myapplication.R;
import com.gmail.myapplication.Vapp;
import com.gmail.myapplication.repository.database.entity.Bitacora;

import java.util.List;

public class BitacoraAdapter extends RecyclerView.Adapter<BitacoraAdapter.BitacoraViewHolder> {

    public class BitacoraViewHolder extends RecyclerView.ViewHolder {
        TextView tvId , tvDate, tvType, tvNota;
        LinearLayout lldata , llnodata;

        public BitacoraViewHolder(View item) {
            super(item);
            tvId = item.findViewById(R.id.tvId);
            tvDate = item.findViewById(R.id.tvDate);
            tvType = item.findViewById(R.id.tvType);
            tvNota = item.findViewById(R.id.tvNota);
            lldata = item.findViewById(R.id.lldata);
            llnodata = item.findViewById(R.id.llnodata);
        }
    }

    private final LayoutInflater mInflater;
    private List<Bitacora> mBitacoras;

    public BitacoraAdapter(){
        mInflater =  LayoutInflater.from(Vapp.getContext());
    }

    @NonNull
    @Override
    public BitacoraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_bitacora,parent , false);
        return new BitacoraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BitacoraViewHolder holder, int position) {
        if ( mBitacoras != null){
            Bitacora currentBitacora = mBitacoras.get(position);
            holder.tvId.setText(String.valueOf(currentBitacora.getId()));
            holder.tvDate.setText(currentBitacora.getDate());
            holder.tvNota.setText(currentBitacora.getNota());
            String type = ( currentBitacora.getTipo() == 0 ? Vapp.getContext().getString(R.string.entrada) :
                Vapp.getContext().getString(R.string.salida));

            holder.tvType.setText( type );

            holder.setIsRecyclable(false);

        }else {
            holder.lldata.setVisibility(View.INVISIBLE);
            holder.llnodata.setVisibility(View.VISIBLE);
        }
    }

    public void setData(List<Bitacora> bitacoras){
        mBitacoras = bitacoras;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if ( mBitacoras != null ) return mBitacoras.size();
        else return 0;
    }

}
