package com.jimsimrodev.librosAPI.principal;

import com.jimsimrodev.librosAPI.models.*;
import com.jimsimrodev.librosAPI.repository.BookRepository;
import com.jimsimrodev.librosAPI.services.APIConsumption;
import com.jimsimrodev.librosAPI.services.ConvertirDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
  private Scanner in = new Scanner(System.in);
  private final String URL_BASE = "https://gutendex.com/books/";
  private APIConsumption consumoAPI = new APIConsumption();
  private ConvertirDatos conversor = new ConvertirDatos();
  private int opcion;
  private String menu = "";
  private  List<Book> libros;
  private List<Autor> autores;
  private List<BookData> bookData = new ArrayList<>();
  private BookRepository repositorio;
  Optional<Book> libroBuscado;

  public Main(BookRepository repository){
    this.repositorio = repository;
  }


  public void run() {
    mostrarMenu();
  }

  public void mostrarMenu() {
    while (true) {
      menu = """
          1 ~ Buscar Libro por titulo
          2 ~ Busca Autores por titulo del libro
          3 ~ Mostrar Libros Buscados
          4 ~ Lista Autores
          5 ~ Listar Autores vivos en determinado año
          0 ~ Salir
          """;
      System.out.println(menu);
      opcion = in.nextInt();
      in.nextLine();

      switch (opcion) {
        case 1:
          buscarLibroPorTitulo();
          break;
        case 2:
          autoresPorLibro();
          break;
        case 3:
          mostrarLibrosBuscados();
          break;
        case 4:
          mostrarAutores();
          break;
        case 0:
          System.out.println("Gracias por utilizar el programa Hata pronto👌❤️");
          return;
        default:
          System.out.println("Opcion invalidad");
      }
    }
  }

  private BookResults getBookList(){
    System.out.println("Ingrese el titulo del libro que desea buscar");
    var nombreLibro = in.nextLine();

    var json = consumoAPI.retrieveData(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
    System.out.println(" resultados " + json);
    var datos = conversor.obtenerDatos(json, BookResults.class);

    return datos;
  }
/*
  public void mostrarLibros() {
    BookResults resultado = getBookList();
    BookData datos = resultado.resultados().get(0);
    Book libros = new Book(datos);
    repositorio.save(libros);

    System.out.println(datos);
  }*/

  //Busca libros de la api y se gurada en la base de datos
  private void buscarLibroPorTitulo() {
    mostrarLibrosBuscados();

    System.out.println("Ingresar el titulo del libro que desea buscar");
    var nombreLibro = in.nextLine();
    libroBuscado = repositorio.findByTituloContainsIgnoreCase(nombreLibro);

    if(libroBuscado.isPresent()){
      System.out.println("El libro buscado es: " + libroBuscado.get());
    }else{
      System.out.println("El libro no encontrado");
    }
    //Codigo ehco a puro harcodeado
   /* BookResults datos = getBookList();
    BookData libroEncotrado = datos.resultados().get(0);
    Book book = new Book(libroEncotrado);
    repositorio.save(book);
    System.out.println(libroEncotrado);*/

  }

  private void mostrarLibrosBuscados(){
   libros = repositorio.findAll();
// Ordena libroa por titulo
    libros.stream()
            .sorted(Comparator.comparing(Book::getTitulo))
            .forEach(l -> System.out.println(l.toString()));
  }

  private void autoresPorLibro(){
    mostrarLibrosBuscados();
    System.out.println("Ingrese el nombre del libro para ver sus autoroes");
    var nombreAutores = in.nextLine();

    Optional<Book> libro = libros.stream()
            .filter(b -> b.getTitulo().toLowerCase().contains(nombreAutores.toLowerCase()))
            .findFirst();

    if(libro.isPresent()){
      var libroEncotrado = libro.get();
      List<AutoresData> autoresData = new ArrayList<>();
      var json = consumoAPI.retrieveData(URL_BASE +  "?search=" + libroEncotrado.getTitulo().replace(" ", "%20"));
      var resultado = conversor.obtenerDatos(json, BookResults.class);

      BookData libros = resultado.resultados().get(0);
      bookData.add(libros);

     List<Autor> autores = bookData.stream()
                     .flatMap(l -> l.autores().stream()
                             .map(a -> new Autor( a)))
                             .collect(Collectors.toList());

      libroEncotrado.setAutores(autores);
      Book libroGuradaedo = repositorio.save(libroEncotrado);

      autores.forEach(a -> System.out.println(a.getNombre()));
    }
  }

  public void mostrarAutores() {
    autores = repositorio.mostrarAutores();
    System.out.println("Lista de autores");
    autores.forEach(System.out::println);
  }
}
