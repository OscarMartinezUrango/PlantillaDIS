package es.ufv.dis.final2022.back;

public class Persona {


    public String nombre;
    public String categoria;
    public int precio;
    public String ean13;


    public Persona(String nombre, String categoria, int precio, String ean13) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.ean13 = ean13;
    }
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }


    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public String getEan13() {
        return ean13;
    }





}
