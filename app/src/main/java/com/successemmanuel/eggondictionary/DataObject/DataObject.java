package com.successemmanuel.eggondictionary.DataObject;


public class DataObject {
    String word;
    String type;
    String definition;
    String example;

    int id;

    public DataObject() {
    }



    public DataObject(String word, String type, String definition, String example)
    {
        this.word = word;
        this.type = type;
        this.definition = definition;
        this.example= example;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
