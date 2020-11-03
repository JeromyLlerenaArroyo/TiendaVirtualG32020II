package tienda.models;

public class Lavadora extends Producto {
    public Lavadora(Marca marca, Modelo modelo) {
        super(marca, modelo);
    }
    @Override
    double getPrecio(){
        return 900.00;
    }

    @Override
    String getNombre(){
        return "Lavadora";
    }
}