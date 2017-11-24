package com.successemmanuel.eggondictionary.mDataBase;



public class Constants {

    //COLUMNS
    public static final String ROW_ID="id";
    public static final String WORD = "word";
    public static final String TYPE = "type";
    public static final String WORD_ID= "word_id";
    public static final String FAV_WORDS="fav_word";
    public static final String EXAMPLES="examples";
    public static final String PART="pos";
    public static final String DEFINITION = "definition";

    //DB
    static final String DB_NAME="word_DB";
    static final String TB_NAME="word_TB";
    static final String MEANING_TB="definition_TB";
    static final String FAVORITE_TB="fav_TB";
    static final String EXAMPLES_TB="example_TB";
    static final String PART_OF_SPEECH_TB="pos_TB";
    static final int DB_VERSION=2;

    //CREATE TB
    static final String CREATE_TB="CREATE TABLE "+TB_NAME+"("+ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ""+WORD+" TEXT NOT NULL, "+TYPE+" TEXT NOT NULL, "+DEFINITION+" TEXT NOT NULL, "+EXAMPLES+" TEXT NOT NULL);";

    static final String CREATE_TB1="CREATE TABLE "+MEANING_TB+"("+ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ""+WORD_ID+" INTEGER FOREIGN KEY , "+DEFINITION+" TEXT NOT NULL);";

    static final String CREATE_TB2="CREATE TABLE "+FAVORITE_TB+"("+ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ""+TYPE+" TEXT NOT NULL, "+FAV_WORDS+" TEXT NOT NULL);";

    static final String CREATE_TB3="CREATE TABLE "+EXAMPLES_TB+"("+ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ""+WORD_ID+" INTEGER FOREIGN KEY , "+EXAMPLES+" TEXT NOT NULL);";

    static final String CREATE_TB4="CREATE TABLE "+PART_OF_SPEECH_TB+"("+ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ""+WORD_ID+" INTEGER FOREIGN KEY , "+PART+" TEXT NOT NULL);";

    //DROP TB
    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;
    static final String DROP_TB1="DROP TABLE IF EXISTS "+MEANING_TB;
    static final String DROP_TB2="DROP TABLE IF EXISTS "+FAVORITE_TB;
    static final String DROP_TB3="DROP TABLE IF EXISTS "+EXAMPLES_TB;
    static final String DROP_TB4="DROP TABLE IF EXISTS "+PART_OF_SPEECH_TB;


}

