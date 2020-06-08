package com.example.quarantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    // Crea una variable de tipo contexto para el view holder
    private Context mContext;
    // Lista de cada una de las recetas, obtenidas de la base de datos
    private List<Upload> mUploads;

    // Declara una interfaz que permite asignar un evento de OnClick a cada item que se renderiza
    private OnItemClicked onClick;

    // Crea la interfaz e inicializa la funcipn
    public interface OnItemClicked{
        void onItemClick(int position);
    }
    // Constructor
    public ImageAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    // Crea un View Holder que se va a llenar posteriormente con los datos obtenidos de la base de datos
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }
    // Obtiene uno por uno los valores de cada comentario desde la base de datos y extrae los datos
    // principales
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewTitle.setText(uploadCurrent.getmName());
        holder.textViewShortDescription.setText(uploadCurrent.getmShortDescription());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);

        // Define el metodo on click para cada uno de los items renderizados
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });

        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }
    // Longitud de los datos
    @Override
    public int getItemCount() {
        return mUploads.size();
    }


    // Clase base del View Holder para ser llenada
    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public ImageView imageView;
        public TextView textViewShortDescription;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            // Llena el view holder con los datos obtenidos de la base de datos.
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewShortDescription = itemView.findViewById(R.id.text_view_recipe_short_description);
            imageView = itemView.findViewById(R.id.image_view_upload);

        }
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }

}
