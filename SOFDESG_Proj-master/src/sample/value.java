package sample;

import com.opencsv.bean.CsvBindByPosition;

public class value {

    @CsvBindByPosition(position = 1)
    private Integer value;
    @CsvBindByPosition(position = 0)
    private String type;

    value(int value, String identifier){
        this.value = value;
        this.type = identifier;
    }

    void setType(String type){
        this.type = type;
    }

    void setValue(int value){
        this.value = value;
    }

    String getType(){
        return this.type;
    }

    Integer getValue(){
        return this.value;
    }

}
