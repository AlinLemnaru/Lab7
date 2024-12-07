import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Pb2_Main {
    public static void scriereJSON(Set<InstrumentMuzical> set) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            File file = new File("src/main/resources/instrumente.json");
            mapper.writeValue(file, set);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citireJSON() {
        try {
            File file = new File("src/main/resources/instrumente.json");
            ObjectMapper mapper = new ObjectMapper();
            Set<InstrumentMuzical> set = mapper
                    .readValue(file, new TypeReference<Set<InstrumentMuzical>>() {
                    });
            return set;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // -- 1 --
        System.out.println("\nEx 1:");

        Set<InstrumentMuzical> instrumente = new HashSet<>();

        instrumente.add(new Chitara("Yamaha", 1500, TipChitara.ELECTRICA, 6));
        instrumente.add(new Chitara("Fender", 2000, TipChitara.ACUSTICA, 6));
        instrumente.add(new Chitara("Gibson", 3000, TipChitara.CLASICA, 7));
        instrumente.add(new SetTobe("Roland", 2000, TipTobe.ELECTRONICE, 5, 3));
        instrumente.add(new SetTobe("Pearl", 2900, TipTobe.ACUSTICE, 4, 2));
        instrumente.add(new SetTobe("Yamaha", 3300, TipTobe.ACUSTICE, 6, 4));

        System.out.println("Adaugarea a 3 chitari si 3 seturi de tobe");

        // -- 2 --
        System.out.println("\nEx 2:");

        scriereJSON(instrumente);

        System.out.println("Salvarea in fisierul JSON");

        // -- 3 --
        System.out.println("\nEx 3:");

        Set<InstrumentMuzical> setInstrumente = new HashSet<>();
        setInstrumente = citireJSON();

        System.out.println("Citirea din fisierul JSON");

        // -- 4 --
        System.out.println("\nEx 4:");

        System.out.println("\nImplementare utilizata: " + setInstrumente.getClass().getName());

        System.out.println("\nAfisare instrumente:\n");

        for (InstrumentMuzical instr : setInstrumente) {
            instr.descriereInstrument();
            System.out.println();
        }

        // -- 5 --
        System.out.println("\nEx 5:");

        // Chitara

        Chitara c1 = new Chitara("Yamaha", 1500, TipChitara.ELECTRICA, 6);
        Chitara c2 = new Chitara("Yamaha", 1500, TipChitara.ELECTRICA, 6);

        if (c1.equals(c2))
            System.out.println("Egale\n");
        else
            System.out.println("Diferite\n");

        boolean isAdded = setInstrumente.add(c1);

        if (isAdded)
            System.out.println("Instrumentul a fost adaugat!");
        else
            System.out.println("Instrumentul nu a fost adaugat!");

        // Tobe

        SetTobe t1 = new SetTobe("Pearl", 3500, TipTobe.ACUSTICE, 4, 2);
        SetTobe t2 = new SetTobe("Pearl", 3500, TipTobe.ACUSTICE, 4, 2);

        if (t1.equals(t2))
            System.out.println("\nEgale\n");
        else
            System.out.println("\nDiferite\n");

        isAdded = setInstrumente.add(c1);

        if (isAdded)
            System.out.println("Instrumentul a fost adaugat!");
        else
            System.out.println("Instrumentul nu a fost adaugat!");

        System.out.println("\nAfisare instrumente:\n");

        for (InstrumentMuzical instr : setInstrumente) {
            instr.descriereInstrument();
            System.out.println();
        }

        // -- 6 --
        System.out.println("\nEx 6:");

        setInstrumente.removeIf((a) -> a.getPret() > 3000);

        System.out.println("\nAfisare instrumente:\n");

        for (InstrumentMuzical instr : setInstrumente) {
            instr.descriereInstrument();
            System.out.println();
        }

        // -- 7 --
        System.out.println("\nEx 7:");

        setInstrumente.stream()
                .filter(instr -> instr instanceof Chitara)
                .map(instr -> (Chitara) instr)
                .forEach(System.out::println);

        // -- 8 --
        System.out.println("\nEx 8:");

        setInstrumente.stream()
                .filter(instr -> instr.getClass() == SetTobe.class)
                .map(instr -> (SetTobe) instr)
                .forEach(System.out::println);

        // -- 9 --
        System.out.println("\nEx 9:");

        Optional<Chitara> chitaraMaxCorzi = setInstrumente.stream()
                .filter(instr -> instr instanceof Chitara)
                .map(instr -> (Chitara) instr)
                .max((a, b) -> Integer.compare(a.getNr_corzi(), b.getNr_corzi()));

        chitaraMaxCorzi.ifPresentOrElse(
                chitara -> {
                    System.out.println("Chitara cu cele mai multe corzi este:");
                    chitara.descriereInstrument();
                },
                () -> System.out.println("Nu exista.")
        );

        // -- 10 --
        System.out.println("\nEx 10:");

        setInstrumente.stream()
                .filter(instr -> instr instanceof SetTobe)
                .map(instr -> (SetTobe) instr)
                .filter(tobe -> tobe.getTip_tobe() == TipTobe.ACUSTICE)
                .sorted((a, b) -> Integer.compare(a.getNr_tobe(), b.getNr_tobe()))
                .forEach(SetTobe::descriereInstrument);
    }
}
