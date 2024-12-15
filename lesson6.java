import java.util.Objects;

public class Notebook {
    private String brand;
    private int ram;              
    private int storage;          
    private String os;            
    private String color;

    public Notebook(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}


import java.util.*;
import java.util.stream.Collectors;

public class NotebookStore {
    private Set<Notebook> notebooks;

    public NotebookStore() {
        notebooks = new HashSet<>();
        // Пример добавления ноутбуков
        notebooks.add(new Notebook("HP", 8, 256, "Windows", "Black"));
        notebooks.add(new Notebook("Dell", 16, 512, "Windows", "Silver"));
        notebooks.add(new Notebook("Apple", 16, 256, "macOS", "Grey"));
        notebooks.add(new Notebook("Acer", 4, 128, "Linux", "Black"));
        notebooks.add(new Notebook("Lenovo", 8, 512, "Windows", "Blue"));
    }

    public void filterNotebooks() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> filterCriteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ (GB)");
        System.out.println("2 - Объем ЖД (GB)");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        // Запрос критериев фильтрации
        while (true) {
            int criteria = scanner.nextInt();
            if (criteria < 1 || criteria > 4) {
                System.out.println("Неверный критерий. Попробуйте снова.");
                continue;
            }

            System.out.print("Введите минимальное значение: ");
            if (criteria == 1 || criteria == 2) {
                int value = scanner.nextInt();
                filterCriteria.put(criteria == 1 ? "ram" : "storage", value);
            } else {
                String value = scanner.next();
                filterCriteria.put(criteria == 3 ? "os" : "color", value);
            }

            System.out.print("Хотите добавить еще критерий? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("no")) {
                break;
            }
        }

        // Фильтрация ноутбуков
        Set<Notebook> filteredNotebooks = notebooks.stream()
                .filter(notebook -> filterCriteria.entrySet().stream()
                        .allMatch(entry -> {
                            switch (entry.getKey()) {
                                case "ram":
                                    return notebook.getRam() >= entry.getValue();
                                case "storage":
                                    return notebook.getStorage() >= entry.getValue();
                                case "os":
                                    return notebook.getOs().equalsIgnoreCase((String) entry.getValue());
                                case "color":
                                    return notebook.getColor().equalsIgnoreCase((String) entry.getValue());
                                default:
                                    return true;
                            }
                        }))
                .collect(Collectors.toSet());

        // Вывод отфильтрованных ноутбуков
        if (filteredNotebooks.isEmpty()) {
            System.out.println("Ноутбуков не найдено.");
        } else {
            System.out.println("Найденные ноутбуки:");
            filteredNotebooks.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        NotebookStore store = new NotebookStore();
        store.filterNotebooks();
    }
}