package tienda.models;

public abstract class Producto {
    Marca marca;
    Modelo modelo;

    public Producto(Marca marca, Modelo modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    abstract double getPrecio();
    abstract String getNombre();

    public void impresion() {
        System.out.println(
            "----------------------------------------------------" + "\n" +
            "Producto: " + this.getNombre() + "\n" +
            "Marca: " + this.marca.getNombre() + "\n" +
            "Modelo: " + this.modelo.getNombre() + "\n" +
            "Precio: " + this.getPrecio() + "\n" +
            "----------------------------------------------------"
        );
    }
}