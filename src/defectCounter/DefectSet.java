/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package defectCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author quanto
 */
public class DefectSet {

    public String prefix;
    private final HashSet<String> defectSet;

    private DefectSet() {
        defectSet = new HashSet<>();
        this.prefix = "";
    }

    public DefectSet(String prefix) {
        defectSet = new HashSet<>();
        this.prefix = prefix;
    }

    public boolean load(String input, String prefix, String max) {
        /**
         * Read an input stream of text, token all number as Defect
         *
         * @param input
         * @param prefix
         * @param min
         * @param max
         * @return true if no exception
         * @return false if exception.
         */
        String[] words;
        input = input.replaceAll("[^a-zA-Z0-9-]+", " ");

        /*
         Set prefix to detect defect
         */
        if (max.isEmpty()) {
            max = prefix + "99999";
        }
        Integer minNumber = Integer.MIN_VALUE;
        Integer maxNumber = Integer.MAX_VALUE;
        try {
            minNumber = Integer.parseInt(prefix);
            maxNumber = Integer.parseInt(max);
        } catch (NumberFormatException e) {
        }

        if (prefix.isEmpty() || minNumber > 0) { // All defects are numbers 
            words = input.split("\\D|\\s+");

            for (String word : words) {
                if (!word.isEmpty()) {

                    try {
                        Integer aNumber = Integer.parseInt(word); // if the word is the number, can parse, take it, done. 
                        if (aNumber > minNumber && aNumber <= maxNumber) {
                            defectSet.add(word);
                        }
                    } catch (NumberFormatException e) {
                        // System.out.println(e.toString() + " " + word + " is not a number."); // If this is not a ##### then ECOA- compare to pattern                         
                    }
                }
            }
        } else {
            words = input.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {

                    if (word.length() > prefix.length()
                            && word.compareTo(prefix) > 0 && word.compareTo(max) <= 0) {
                        // possible this an ECOA- or prefixed defects
                        defectSet.add(word);
                    }
                }
            }
        }
        return true;

    }

    public String listAll() {
        ArrayList<String> finalResult = new ArrayList<>();
        String output = "";
        for (String number : defectSet) {
            finalResult.add(number);
        }
        Collections.sort(finalResult);
        for (String result : finalResult) {
            output = output + result + ", ";
        }
        return output;
    }

    public DefectSet subtract(DefectSet subtrahendSet) {
        DefectSet result = new DefectSet(prefix);
        ArrayList<String> minuend = new ArrayList<>();
        ArrayList<String> subtrahend = new ArrayList<>();
        for (String number : defectSet) {
            minuend.add(number);
        }
        for (String number : subtrahendSet.defectSet) {
            subtrahend.add(number);
        }

        for (int i = 0; i < minuend.size(); i++) {
            if (subtrahend.contains(minuend.get(i))) {
                minuend.remove(i);
                i--;
            }
        }
        for (String minuend1 : minuend) {
            result.defectSet.add(minuend1);
        }
        return result;
    }

    public DefectSet intersect(DefectSet subtrahendSet) {
        DefectSet result = new DefectSet(prefix);
        ArrayList<String> minuend = new ArrayList<>();
        ArrayList<String> subtrahend = new ArrayList<>();
        for (String number : defectSet) {
            minuend.add(number);
        }
        for (String number : subtrahendSet.defectSet) {
            subtrahend.add(number);
        }
        for (String minuend1 : minuend) {
            if (subtrahend.contains(minuend1)) {
                result.defectSet.add(minuend1);
            }
        }
        return result;
    }

    /**
     * @return the numberSet
     */
    public Integer size() {
        return defectSet.size();
    }
}
