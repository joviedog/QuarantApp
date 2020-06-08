package com.example.quarantapp;

// Clase comentario.
public class Comentario {
    private String titulo;
    private String contenido;
    private String mUserID;

    public Comentario() {
        // Required empty constructor
    }


    public Comentario(String titulo, String contenido, String mUserID) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.mUserID = mUserID;
    }

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
