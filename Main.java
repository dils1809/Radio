import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Radio {
    private String estado = "apagado"; // Estados posibles: "apagado" u "encendido"
    private String banda = "AM";       // Banda inicial: "AM" o "FM"
    private double frecuencia = (banda.equals("AM")) ? 530 : 87.9;
    private Map<Integer, Emisora> botones = new HashMap<>();

  
    public void encender() {
        estado = "encendido";
        System.out.println("\nLa radio está ahora ENCENDIDA.");
    }

    
    public void apagar() {
        estado = "apagado";
        System.out.println("\nLa radio está ahora APAGADA.");
    }

   
    public void cambiarBanda() {
        banda = (banda.equals("AM")) ? "FM" : "AM";
        System.out.println("\nCambiado a banda " + banda + ".");
    }


    public void sintonizarEmisora(int direccion) {
        if (banda.equals("AM")) {
            frecuencia += 10 * direccion;
            if (frecuencia > 1610) {
                frecuencia = 530;
            }
        } else {
            frecuencia += 0.2 * direccion;
            if (frecuencia > 107.9) {
                frecuencia = 87.9;
            }
        }

        System.out.println("\nSintonizado en " + frecuencia + " " + banda);
    }

 
    public void guardarEmisora(int boton) {
        botones.put(boton, new Emisora(banda, frecuencia));
        System.out.println("\nEmisora guardada en el botón " + boton + ".");
    }


    public void seleccionarEmisora(int boton) {
        Emisora emisora = botones.get(boton);
        if (emisora != null) {
            banda = emisora.getBanda();
            frecuencia = emisora.getFrecuencia();
            System.out.println("\nEmisora seleccionada en el botón " + boton + ": " + frecuencia + " " + banda);
        } else {
            System.out.println("\nNo hay emisora guardada en el botón " + boton + ".");
        }
    }
}


class Emisora {
    private String banda;
    private double frecuencia;


    public Emisora(String banda, double frecuencia) {
        this.banda = banda;
        this.frecuencia = frecuencia;
    }


    public String getBanda() {
        return banda;
    }


    public double getFrecuencia() {
        return frecuencia;
    }
}


public class Main {
    public static void main(String[] args) {
        Radio radio = new Radio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de la Radio:");
            System.out.println("1. Encender la radio");
            System.out.println("2. Cambiar entre AM y FM");
            System.out.println("3. Sintonizar emisora");
            System.out.println("4. Guardar emisora en un botón");
            System.out.println("5. Seleccionar emisora de un botón");
            System.out.println("6. Apagar la radio");
            System.out.print("\nIngrese su elección: ");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    radio.encender();
                    break;
                case 2:
                    radio.cambiarBanda();
                    break;
                case 3:
                    System.out.print("Ingrese la dirección (-1 para bajar, 1 para subir): ");
                    int direccion = scanner.nextInt();
                    radio.sintonizarEmisora(direccion);
                    break;
                case 4:
                    System.out.print("Ingrese el número de botón (1-12): ");
                    int botonGuardar = scanner.nextInt();
                    radio.guardarEmisora(botonGuardar);
                    break;
                case 5:
                    System.out.print("Ingrese el número de botón (1-12): ");
                    int botonSeleccionar = scanner.nextInt();
                    radio.seleccionarEmisora(botonSeleccionar);
                    break;
                case 6:
                    radio.apagar();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nElección inválida. Por favor, ingrese un número entre 1 y 6.");
            }
        }
    }
}
