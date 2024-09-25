package com.practica.info2024_primera_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //anotacion a nivel de clase
public class BookController {
    //Get --> Obtener
    //http://localhost:8080/aplicacion/v1/despedida
    @GetMapping("/aplicacion/v1/despedida") //anotacion a nivel de metodo
    public String goodByeWorld(){
        return "Adios mundo cruel";
    }
    //http://localhost:8080/aplicacion/v1/saludo?nombre=Marcos
    @GetMapping("/aplicacion/v1/saludo")
    public String helloWorld(@RequestParam(required = true, name="nombre") String nombre){
        //(@RequestParam(required = true, name="nombre") String nombre) anotacion a nivel de atributo
        return "Hola " + nombre;
    }

}
