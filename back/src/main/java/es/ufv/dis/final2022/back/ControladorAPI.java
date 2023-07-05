package es.ufv.dis.final2022.back;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity; // Generar respuestas HTTP
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ControladorAPI {

    LectorArchivo lectorJSON = new LectorArchivo();
    PDFcreator pdf = new PDFcreator();
    List<Persona> listaProductos = lectorJSON.leerJsonProductos();

    @GetMapping("/ObtenerProductos")
    public List<Persona> obtenerTodosProductos(){ return lectorJSON.leerJsonProductos(); }

    /**
    * get
     */
    @GetMapping("/EncontrarProducto/{producto}")
    public Persona obtenerProducto(@PathVariable("elemento") String producto){
        for(Persona producto_actual:listaProductos){
            if(producto_actual.getNombre().equals(producto)){
                return producto_actual;
            }
        }
        return null;
    }

    /**
    * post
     */
    @PostMapping(path="/addProducto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> addProducto(@RequestBody Persona producto){
        listaProductos = lectorJSON.leerJsonProductos();
        for (Persona p:listaProductos) {
            if (p.getNombre().equals(producto.getNombre())){
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }
        }
        listaProductos.add(producto);
        lectorJSON.actualizarJson(listaProductos);
        pdf.generarPDF(listaProductos);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    /**
     * delete
     */
    @DeleteMapping("/BorrarProducto/{elemento}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Persona>> borrarProducto(@PathVariable("elemento") String nombre){
        for(Persona producto_actual:listaProductos){
            if(producto_actual.getNombre().equals(nombre)){
                listaProductos.remove(producto_actual);
            }
        }
        lectorJSON.actualizarJson(listaProductos);
        pdf.generarPDF(listaProductos);
        return new ResponseEntity<>(listaProductos, HttpStatus.NO_CONTENT);
    }




}
