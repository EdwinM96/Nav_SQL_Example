package com.example.edwin.nav_sql_example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.edwin.nav_sql_example.R;
import com.example.edwin.nav_sql_example.modelo.Nota;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NotasViewHolder> {
    private List<Nota> notas;
    private Context context;
    private LayoutInflater layoutInflater;

    public class NotasViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,nota;

        public NotasViewHolder(View itemview){
            super(itemview);
            nombre = itemview.findViewById(R.id.nombre_mostrar);
            nota = itemview.findViewById(R.id.nota_mostrar);
        }
    }

    public RecyclerViewAdapter(Context context, List<Nota> notas){
        this.context = context;
        this.notas = notas;
    }

    @NonNull
    @Override
    public NotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.recycler_viewholder,parent,false);
        NotasViewHolder studentsViewHolder = new NotasViewHolder(v);
        return studentsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotasViewHolder holder, int position) {
        final Nota nota = notas.get(position);
        holder.nombre.setText(nota.getEvaluacion());
        holder.nota.setText(nota.getNota()+"");
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }
}
