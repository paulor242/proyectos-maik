import java.util.ArrayList;

// Clase base Vehiculo
class Vehiculo {
    private String modelo;
    private String marca;
    private int velocidad;  // mejor int que Integer

    // Constructor
    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = 0; // inicia detenido
    }

    // Getters y Setters
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getVelocidad() { return velocidad; }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    // Método mostrar info
    public void mostrarInfo() {
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Velocidad: " + getVelocidad() + " km/h");
    }

    // Método genérico acelerar
    public void acelerar() {
        System.out.println("El vehículo está acelerando...");
        velocidad += 10;
    }
}

// Subclase Carro
class Carro extends Vehiculo {
    public Carro(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void acelerar() {
        System.out.println("🚗 El carro acelera suavemente...");
        setVelocidad(getVelocidad() + 20);
    }
}

// Subclase Moto
class Moto extends Vehiculo {
    public Moto(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void acelerar() {
        System.out.println(" La moto acelera rápidamente...");
        setVelocidad(getVelocidad() + 40);
    }
}

// Clase principal
public class Vehiculos {
    public static void main(String[] args) {
        // Crear objetos
        Vehiculo generico = new Vehiculo("Genérico", "Base");
        Carro carro = new Carro("Toyota", "Corolla");
        Moto moto = new Moto("Yamaha", "MT-09");

        // Guardarlos en una lista
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        listaVehiculos.add(generico);
        listaVehiculos.add(carro);
        listaVehiculos.add(moto);

        // Recorrer lista y probar acelerar
        for (Vehiculo v : listaVehiculos) {
            v.mostrarInfo();
            v.acelerar();
            v.mostrarInfo();
            System.out.println("----------------------------");
        }
    }
}
