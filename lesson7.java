public class Complex {
    private final double real; // Действительная часть
    private final double imaginary; // Мнимая часть

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }
}

public interface ComplexOperation {
    Complex execute(Complex a, Complex b);
}

public class Addition implements ComplexOperation {
    @Override
    public Complex execute(Complex a, Complex b) {
        return new Complex(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
    }
}

public class Multiplication implements ComplexOperation {
    @Override
    public Complex execute(Complex a, Complex b) {
        double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
        double imaginary = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
        return new Complex(real, imaginary);
    }
}

public class Division implements ComplexOperation {
    @Override
    public Complex execute(Complex a, Complex b) {
        double denominator = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
        if (denominator == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        double real = (a.getReal() * b.getReal() + a.getImaginary() * b.getImaginary()) / denominator;
        double imaginary = (a.getImaginary() * b.getReal() - a.getReal() * b.getImaginary()) / denominator;
        return new Complex(real, imaginary);
    }
}
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE_PATH = "calculator.log";

    public void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(message + "\n");
            System.out.println(message); // Для отображения в консоли
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.util.Scanner;

public class Calculator {
    private final Logger logger;

    public Calculator() {
        this.logger = new Logger();
    }

    public Complex performOperation(Complex a, Complex b, ComplexOperation operation) {
        Complex result = operation.execute(a, b);
        logger.log("Operation result: " + result);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Введите действительную и мнимую части первого комплексного числа:");
        Complex a = new Complex(scanner.nextDouble(), scanner.nextDouble());

        System.out.println("Введите действительную и мнимую части второго комплексного числа:");
        Complex b = new Complex(scanner.nextDouble(), scanner.nextDouble());

        System.out.println("Выберите операцию: 1 - Сложение, 2 - Умножение, 3 - Деление");
        int choice = scanner.nextInt();

        ComplexOperation operation = null;
        switch (choice) {
            case 1 -> operation = new Addition();
            case 2 -> operation = new Multiplication();
            case 3 -> operation = new Division();
            default -> System.out.println("Неверный выбор");
        }

        if (operation != null) {
            Complex result = calculator.performOperation(a, b, operation);
            System.out.println("Результат: " + result);
        }

        scanner.close();
    }
}


