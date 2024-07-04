package com.jimsimrodev.librosAPI.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "libros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();

    private List<String> idiomas;
    private Long numeroDeDescargas;

    public Book(){

    }

    public Book(BookData datos){
        this.titulo = datos.titulo();
        this.idiomas = datos.idiomas();
        this.numeroDeDescargas = datos.numeroDeDescargas();
    }



    public Long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }


    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroDeDescargas(Long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a ->a.setBook(this));
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas;
    }
}
