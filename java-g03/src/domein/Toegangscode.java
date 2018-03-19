package domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Toegangscode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int code;

    public Toegangscode(int code){
        setCode(code);
    }

    protected Toegangscode(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;

    }

    @Override
    public String toString() {
        return  Integer.toString(code);
    }
}
