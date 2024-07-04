package com.jimsimrodev.librosAPI.models;


import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    @ManyToOne
    private Book book;

    public Autor(){

    }

    public Autor(AutoresData a){
        this.nombre = a.nombre();
        this.anioNacimiento = a.anioNacimiento();
        this.anioMuerte = a.anioMuerte();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + '\'' +
                " anioNacimiento: " + anioNacimiento +
                " anioMuerte: " + anioMuerte;
    }
}
