package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Toegangscode {
    @Id
    private int id;
    private int code;

    public Toegangscode(int id, int code){
        setId(id);
        setCode(code);
    }

    protected Toegangscode(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
