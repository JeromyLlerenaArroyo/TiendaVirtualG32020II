package tienda.models;

public class Televisor extends Producto {
    public Televisor(Marca marca, Modelo modelo) {
        super(marca, modelo);
    }

    @Override
    double getPrecio(){
        return 700.00;
    }

    @Override
    String getNombre(){
        return "Televisor";
    }
}