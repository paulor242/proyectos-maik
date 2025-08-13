import java.util.Scanner;

public class cuentaBancaria  {
    private String titular;
    private String numeroCuenta;
    private Integer saldo;

    // Constructor
    public cuentaBancaria (String titular, String numeroCuenta, Integer saldoInicial) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Mostrar datos
    public void mostrarDatos() {
        System.out.println("Titular: " + titular);
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo: $" + saldo);
    }

    // Depositar
    public void depositar(Integer monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que $0.");
            return;
        }
        saldo += monto;
        System.out.println("Depósito exitoso. Nuevo saldo: $" + saldo);
    }

    

    // Retirar
    public boolean retirar(Integer monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que $0.");
            return false;
        }
        if (monto > saldo) {
            System.out.println("Fondos insuficientes.");
            return false;
        }
        saldo -= monto;
        System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
        return true;
    }

    // Transferir
    public boolean transferir(Integer monto, cuentaBancaria destino) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que $0.");
            return false;
        }
        if (monto > saldo) {
            System.out.println("Fondos insuficientes.");
            return false;
        }
        saldo -= monto;
        destino.saldo += monto;
        System.out.println("Transferencia realizada a " + destino.titular + ". Nuevo saldo: $" + saldo);
        return true;
    }


    
    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear primera cuenta
        System.out.println("Ingrese el nombre del titular de la cuenta 1:");
        String titular1 = scanner.nextLine();
        System.out.println("Ingrese el número de cuenta 1:");
        String numCuenta1 = scanner.nextLine();
        System.out.println("Ingrese el saldo inicial de la cuenta 1:");
        int saldo1 = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        cuentaBancaria cuenta1 = new cuentaBancaria(titular1, numCuenta1, saldo1);

        // Crear segunda cuenta
        System.out.println("Ingrese el nombre del titular de la cuenta 2:");
        String titular2 = scanner.nextLine();
        System.out.println("Ingrese el número de cuenta 2:");
        String numCuenta2 = scanner.nextLine();
        System.out.println("Ingrese el saldo inicial de la cuenta 2:");
        int saldo2 = scanner.nextInt();
        scanner.nextLine();
        cuentaBancaria cuenta2 = new cuentaBancaria(titular2, numCuenta2, saldo2);

        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mostrar datos cuenta 1");
            System.out.println("2. Mostrar datos cuenta 2");
            System.out.println("3. Depositar en cuenta 1");
            System.out.println("4. Retirar de cuenta 1");
            System.out.println("5. Transferir de cuenta 1 a cuenta 2");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cuenta1.mostrarDatos();
                    break;
                case 2:
                    cuenta2.mostrarDatos();
                    break;
                case 3:
                    System.out.print("Ingrese el monto a depositar en cuenta 1: ");
                    int dep = scanner.nextInt();
                    cuenta1.depositar(dep);
                    break;
                case 4:
                    System.out.print("Ingrese el monto a retirar de cuenta 1: ");
                    int ret = scanner.nextInt();
                    cuenta1.retirar(ret);
                    break;
                case 5:
                    System.out.print("Ingrese el monto a transferir de cuenta 1 a cuenta 2: ");
                    int trans = scanner.nextInt();
                    cuenta1.transferir(trans, cuenta2);
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}