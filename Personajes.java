import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Personajes {
    public String nombre;
    public String rasgos;
    private String[] habilidades = {"fuerza", "magia", "velocidad"};
    private String secretos;

    // constructor
    public Personajes(String nombre, String rasgos, String[] habilidades) {
        this.nombre = nombre;
        this.rasgos = rasgos;
        this.habilidades = habilidades;
    }

    // getters
    public String getNombre() { return nombre; }
    public String getRasgos() { return rasgos; }
    public String[] getHabilidades() { return habilidades; }

    public String getSecretos() { return secretos; }

    // setter
    public void setSecretos(String secretos) { this.secretos = secretos; }

    // mostrar secretos
    public void mostrarSecretos() {
        System.out.println("================== Mostrar secretos =================");
        System.out.println("El secreto de " + nombre + " está protegido");
    }

    // actuar genérico
    public void actuar() {
        // vacío, cada subclase lo define
    }

    // obtener una habilidad al azar
    public String getHabilidadesAleatoria() {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(habilidades.length);
        return habilidades[indiceAleatorio];
    }

    // mostrar todas las habilidades
    protected void mostrarHabilidad() {
        System.out.println("Habilidades disponibles: " + Arrays.toString(habilidades));
    }

    // ================== CLASE GUERRERO ==================
    class Guerrero extends Personajes {
        public Guerrero(String nombre, String rasgos, String[] habilidades) {
            super(nombre, rasgos, habilidades);
        }

        @Override
        public void actuar() {
            System.out.println("=================== Clase Guerrero ====================");
            System.out.println("La habilidad del guerrero es " + getHabilidadesAleatoria());
            System.out.println("El guerrero " + nombre + " está atacando con fuerza bruta");
        }
    }

    // ================== CLASE MAGO ==================
    class Mago extends Personajes {
        public Mago(String nombre, String rasgos, String[] habilidades) {
            super(nombre, rasgos, habilidades);
        }

        @Override
        public void actuar() {
            System.out.println("=================== Clase Mago ====================");
            System.out.println("La habilidad del mago es " + getHabilidadesAleatoria());
            System.out.println("El mago " + nombre + " está lanzando hechizos");
        }
    }

    // ================== MAIN ==================
    public static void main(String[] args) {
        // crear objetos
        Personajes personajes1 = new Personajes("Juan", "poder", new String[]{"fuerza", "magia", "velocidad"});
        Guerrero guerrero1 = personajes1.new Guerrero("Pedro", "guerrero", new String[]{"fuerza", "resistencia"});
        Mago mago1 = personajes1.new Mago("Pablo", "mago", new String[]{"magia", "sabiduría"});

        // crear lista
        ArrayList<Personajes> listaPersonajes = new ArrayList<>();
        listaPersonajes.add(personajes1);
        listaPersonajes.add(guerrero1);
        listaPersonajes.add(mago1);

        // recorrer lista
        for (Personajes P : listaPersonajes) {
            P.mostrarSecretos();
            P.actuar();
            System.out.println("Habilidad aleatoria: " + P.getHabilidadesAleatoria());
            System.out.println("-------------------");
        }
    }
}
