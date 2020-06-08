package com.example.quarantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    // Crea una variable de contexto para el adaptador de Recycler View
    private Context mContext;
    // Crea una variable de tipo Lista para guardar los comentarios que se van a descargar desde
    // la base de datos
    private List<Comentario> mComentario;
    // Constructor

    /**
     *
     * @param context Contexto del adaptados
     * @param comentarios Lista de comentarios por renderizar en la interfaz
     */
    public CommentAdapter(Context context, List<Comentario> comentarios){
        mContext = context;
        mComentario = comentarios;
    }

    // Crea un View Holder que se va a llenar posteriormente con los datos obtenidos de la base de datos
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(v);
    }
    // Obtiene uno por uno los valores de cada comentario desde la base de datos y extrae los datos
    // principales
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comentario currentComentario = mComentario.get(position);
        holder.textViewTitle.setText(currentComentario.getTitulo());
        holder.textViewShortDescription.setText(currentComentario.getContenido());
    }
    // Longitud de los datos.
    @Override
    public int getItemCount() {
        return mComentario.size();
    }
    // Clase de tipo View Holder para el ViewHolder de los comentarios.
    public class CommentViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewShortDescription;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_comment_title);
            textViewShortDescription = itemView.findViewById(R.id.text_view_comment_content);


        }
    }


}
