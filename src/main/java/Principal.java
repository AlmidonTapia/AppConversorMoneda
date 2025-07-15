import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ConversorJava conversor = new ConversorJava();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la moneda de origen (ej. PEN, USD, EUR): ");
        String monedaDesde = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese la moneda de destino (ej. PEN, USD, EUR): ");
        String monedaHasta = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese la cantidad a convertir: ");
        double cantidad = scanner.nextDouble();

        try {
            double resultado = conversor.convertir(monedaDesde, monedaHasta, cantidad);
            System.out.printf("Resultado: %.2f %s%n", resultado, monedaHasta);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
