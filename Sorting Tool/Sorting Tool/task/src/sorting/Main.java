package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static String outputFile;
    public static String inputFilePath;

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        String sortType = "natural";
        String dataType = "word";

        Set<String> sortTypes = Set.of("natural", "bycount");
        Set<String> dataTypes = Set.of("word", "long", "line");

        for (int i = 0; i < args.length; i++) {
            if ("-sortingType".equals(args[i])) {
                if (i + 1 >= args.length || !sortTypes.contains(args[i + 1].toLowerCase())) {
                    print("No sorting type defined!");
                    return;
                }
                sortType = args[++i];

            } else if ("-dataType".equals(args[i])) {
                if (i + 1 >= args.length || !dataTypes.contains(args[i + 1].toLowerCase())) {
                    print("No data type defined!");
                    return;
                }
                dataType = args[++i];

            } else if (args[i].equals("-inputFile")) {
                inputFilePath = args[++i];
            } else if (args[i].equals("-outputFile")) {
                outputFile = args[++i];
            } else {
                print("\"" + args[i] + "\" isn't a valid parameter. It's skipped.");
            }
        }


        if(inputFilePath != null) {
            try (Scanner scanner1 = new Scanner(new File(inputFilePath))){
                switch (sortType) {
                    case "natural":
                        sortNatural(dataType, scanner);
                        break;
                    case "byCount":
                        sortByCount(dataType, scanner);
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            switch (sortType) {
                case "natural":
                    sortNatural(dataType, scanner);
                    break;
                case "byCount":
                    sortByCount(dataType, scanner);
                    break;
            }
        }
    }

    public static void print(String output){
        if(outputFile == null) {
            System.out.printf(output);
        } else {
            try {
                PrintWriter fw = new PrintWriter(outputFile);
                fw.write(output);
                fw.close();
            } catch (IOException e) {
                System.out.print("RIP");
            }
        }
    }


    public static void sortNatural(String option, Scanner scanner) {
        if (option.equals("long")) {
            ArrayList<Long> list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(scanner.nextLong());
            }
            Collections.sort(list);
            String output = "";
            for(Long l:list) {
                output += l + " ";
            }
            print(String.format("Total numbers: %d\nSorted data: %s", list.size(), output));
        } else if (option.equals("word")) {
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            Collections.sort(list);
            String output = "";
            for(String s:list) {
                output += s + " ";
            }
            print(String.format("Total numbers: %d\nSorted data: %s", list.size(), output));
        } else if (option.equals("line")) {
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            Collections.sort(list);
            String output = "";
            for(String s:list) {
                output += s + " ";
            }
            print(String.format("Total numbers: %d\nSorted data: %s", list.size(), output));
        }
    }

    public static void sortByCount(String option, Scanner scanner) {
        if (option.equals("long")) {
            List<Long> sortedDataEntries = new ArrayList<Long>();
            while (scanner.hasNextLong()) {
                sortedDataEntries.add(scanner.nextLong());
            }
            Map<Long, Integer> dataEntryToCount = new TreeMap<Long, Integer>();
            for(long l: sortedDataEntries) {
                dataEntryToCount.put(l,dataEntryToCount.getOrDefault(l, 0) + 1);
            }
            Set<Integer> counts = new TreeSet<Integer>();
            for(int i: dataEntryToCount.values()) {
                counts.add(i);
            }
            Map<Integer, Set<Long>> countToDataEntries = new LinkedHashMap<>();

            print("Total numbers: " + sortedDataEntries.size() + ".\n");
            for(int i: counts) {
                Set<Long> set = new LinkedHashSet();
                for(var entry: dataEntryToCount.entrySet()) {
                    if(i==entry.getValue()) {
                        set.add(entry.getKey());
                    }
                }
                countToDataEntries.put(i,set);
            }
            for(var entry: countToDataEntries.entrySet()) {
                int percentage = entry.getKey() * 100 / sortedDataEntries.size();
                for(Long l:entry.getValue()) {
                    print(l + ": " + entry.getKey() + " time(s), " + percentage + "%%\n");
                }
            }
        } else if (option.equals("word")) {
            List<String> sortedDataEntries = new ArrayList<String>();
            while (scanner.hasNext()) {
                sortedDataEntries.add(scanner.next());
            }
            Map<String, Integer> dataEntryToCount = new TreeMap<>();
            for(String l: sortedDataEntries) {
                dataEntryToCount.put(l,dataEntryToCount.getOrDefault(l, 0) + 1);
            }
            Set<Integer> counts = new TreeSet<Integer>();
            for(int i: dataEntryToCount.values()) {
                counts.add(i);
            }
            Map<Integer, Set<String>> countToDataEntries = new LinkedHashMap<>();

            print("Total words: " + sortedDataEntries.size() + ".\n");
            for(int i: counts) {
                Set<String> set = new LinkedHashSet();
                for(var entry: dataEntryToCount.entrySet()) {
                    if(i==entry.getValue()) {
                        set.add(entry.getKey());
                    }
                }
                countToDataEntries.put(i,set);
            }
            for(var entry: countToDataEntries.entrySet()) {
                int percentage = entry.getKey() * 100 / sortedDataEntries.size();
                for(String l:entry.getValue()) {
                    print(l + ": " + entry.getKey() + " time(s), " + percentage + "%%\n");
                }
            }
        } else if (option.equals("line")) {
            List<String> sortedDataEntries = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                sortedDataEntries.add(scanner.nextLine());
            }
            Map<String, Integer> dataEntryToCount = new TreeMap<String, Integer>();
            for(String l: sortedDataEntries) {
                dataEntryToCount.put(l,dataEntryToCount.getOrDefault(l, 0) + 1);
            }
            Set<Integer> counts = new TreeSet<Integer>();
            for(int i: dataEntryToCount.values()) {
                counts.add(i);
            }
            Map<Integer, Set<String>> countToDataEntries = new LinkedHashMap<>();

            print("Total lines: " + sortedDataEntries.size() + ".\n") ;
            for(int i: counts) {
                Set<String> set = new LinkedHashSet();
                for(var entry: dataEntryToCount.entrySet()) {
                    if(i==entry.getValue()) {
                        set.add(entry.getKey());
                    }
                }
                countToDataEntries.put(i,set);
            }
            for(var entry: countToDataEntries.entrySet()) {
                int percentage = entry.getKey() * 100 / sortedDataEntries.size();
                for(String l:entry.getValue()) {
                    print(l + ": " + entry.getKey() + " time(s), " + percentage + "%%\n");
                }
            }
        }
    }
}