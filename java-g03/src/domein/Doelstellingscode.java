package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Doelstellingscode {
    @Id
    private String code;

    public Doelstellingscode(String code){
        setCode(code);
    }

    protected Doelstellingscode(){

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.isEmpty()){
            throw new IllegalArgumentException("Code mag niet leeg gelaten worden van een Doelstellingscode");
        }
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
