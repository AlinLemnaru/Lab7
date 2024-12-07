import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Pb1_Main {
    public static Map<Integer, Carte> citireJSON() {
        try {
            File file = new File("src/main/resources/carti.json");
            ObjectMapper mapper = new ObjectMapper();
            Map<Integer, Carte> map = mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {
            });
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void scriereJSON(Map<Integer, Carte> map) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/carti.json");
            mapper.writeValue(file, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayMenu() {
        System.out.println(
                """
                        1. Afisare
                        2. Sterge o carte
                        3. Adauga o carte cu metoda putIfApsent
                        4. Sa se salveze in fisierul JSON
                        5. Colectia SET
                        6. Afisare ordonata dupa titlu
                        7. Datele celei mai vechi carti
                        0. Iesire
                        """
        );
    }

    public static void main(String[] args) {
        // Scanner citire - scriere consola
        Scanner sc = new Scanner(System.in);

        // Colectia de carti
        Map<Integer, Carte> mapCarti = new HashMap<Integer, Carte>();
        mapCarti = citireJSON();

        // Setul de carti
        Set<Map.Entry<Integer, Carte>> set = null;

        // Variabile
        int optiuneMeniu;
        int auxCheie;

        while (true) {
            displayMenu();
            System.out.print("Optiunea dvs: ");
            optiuneMeniu = sc.nextInt();

            switch (optiuneMeniu) {
                case 1:
                    System.out.println("\n\n### Afisare colectie de carti: ###\n");

                    for (var entry : mapCarti.entrySet())
                        System.out.println("CHEIE: " + entry.getKey() + ", VALOARE: " + entry.getValue());

                    System.out.println("\n\n##################################\n");
                    break;
                case 2:
                    System.out.println("\n\n### Sterge o carte din colectie: ###\n");

                    System.out.print("Numarul cartii pe care doriti sa o stergeti: ");
                    auxCheie = sc.nextInt();
                    mapCarti.remove(auxCheie);

                    System.out.println("\nAfisare colectie de carti:\n");
                    for (var entry : mapCarti.entrySet())
                        System.out.println("CHEIE: " + entry.getKey() + ", VALOARE: " + entry.getValue());

                    System.out.println("\n\n####################################\n");
                    break;
                case 3:
                    System.out.println("\n\n### Adaugare o carte in colectie: ###\n");

                    System.out.print("Cheia: ");
                    int cheie = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Titlul: ");
                    String ad_titlul = sc.nextLine();
                    System.out.print("Autorul: ");
                    String ad_autorul = sc.nextLine();
                    System.out.print("Anul aparitiei: ");
                    int ad_anul = sc.nextInt();

                    mapCarti.putIfAbsent(cheie, new Carte(ad_titlul, ad_autorul, ad_anul));

                    System.out.println("\nAfisare colectie de carti:\n");
                    for (var entry : mapCarti.entrySet())
                        System.out.println("CHEIE: " + entry.getKey() + ", VALOARE: " + entry.getValue());

                    System.out.println("\n\n#####################################\n");
                    break;
                case 4:
                    System.out.println("\n\n### Salvare in fisierul JSON: ###\n");

                    scriereJSON(mapCarti);

                    System.out.println("\n\n#################################\n");
                    break;
                case 5:
                    System.out.println("\n\n### Set carti scrise de Yuval Noah Harari: ###\n");

                    set = mapCarti
                            .entrySet()
                            .stream()
                            .filter((a) -> a.getValue().autorul().equals("Yuval Noah Harari"))
                            .collect(Collectors.toSet());

                    System.out.println("\nAfisare set carti scrise de Yuval Noah Harari:");
                    set.forEach(System.out::println);

                    System.out.println("\n\n##############################################\n");
                    break;
                case 6:
                    System.out.println("\n\n### Set carti scrise de Yuval Noah Harari ordonat: ###\n");

                    if (set != null) {
                        set
                                .stream()
                                .sorted((a, b) -> a.getValue().titlul().compareTo(b.getValue().titlul()))
                                .forEach(System.out::println);
                    }

                    System.out.println("\n\n######################################################\n");
                    break;
                case 7:
                    System.out.println("\n\n### Set carti scrise de Yuval Noah Harari ordonat: ###\n");

                    if (set != null) {
                        Optional<Map.Entry<Integer, Carte>> ceaMaiVecheCarte = set
                                .stream()
                                .min(Comparator.comparingInt((a) -> a.getValue().anul()));

                        System.out.println("\nCea mai veche carte scrisa de Yuval Noah Harari:");
                        System.out.println("CHEIA: " + ceaMaiVecheCarte.get().getKey()
                                + ", VALOARE: " + ceaMaiVecheCarte.get().getValue());
                    }

                    System.out.println("\n\n######################################################\n");
                    break;
                case 0:
                    System.out.println("\n\nIesire...\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\nOptiune invalida! Alegeti o optiune valida!\n");
                    break;
            }
        }

    }
}
