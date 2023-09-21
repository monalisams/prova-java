package br.com.confidencecambio.javabasico.entity;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Nameable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(StringUtils.isEmpty(name.trim())){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public String getFirstName() {
        String[] name = this.name.split(" ");

        return name[0];
    }

    public String getLastName() {
        String[] name = this.name.split(" ");

        return name[name.length - 1];
    }

    public String getNameUpperCase() {
        return this.name.toUpperCase();
    }

    public String getShortName() {

        final Set<String> PREPOSICOES = new HashSet<>(Arrays.asList("de", "da", "do", "dos", "e"));

        String[] name = this.name.split(" ");
        if (name.length < 2){
            return this.name;
        }

        StringBuilder shortName = new StringBuilder();

        for (int i = 0; i < name.length; i++) {
            String word = name[i];

            if (i > 0 ) {
                shortName.append(" ");
            }
            if(i == 0 || PREPOSICOES.contains(word) || i == name.length - 1){
                shortName.append(word);
                continue;
            }

            shortName.append(word.charAt(0)).append(".");

        }

        return shortName.toString();
    }
}
