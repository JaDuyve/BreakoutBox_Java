package domein;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sessie {
    @Id
    private String naam;
    @Temporal(TemporalType.DATE)
    private Date startDatum;
    private int code;
    private boolean contactLeer;
    @Transient
    private static SecureRandom random = new SecureRandom();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Groep> groepen = new ArrayList<Groep>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Bob bob;

    public Sessie(String naam, Date startDatum, Bob bob, File groepen, boolean contactLeer) {
        setNaam(naam);
        setStartDatum(startDatum);
        setContactLeer(contactLeer);
        setBob(bob);
        setCode(random.nextInt(10000) + 1000);

        if (groepen == null) {
            throw new IllegalArgumentException("Excel met groepen moet worden toegevoegd.");
        }
        groepenToevoegen(groepen);
    }

    protected Sessie() {
    }

    private void groepenToevoegen(File groepen) {
        try {
            List<String> leerlingen;
            String lName;
            String klas;
            String naam;
            FileInputStream fis = new FileInputStream(groepen.getPath());
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet excel = wb.getSheetAt(0);
            for (int i = 1; i <= 20; i++) {
                naam = excel.getRow(2).getCell(i).getStringCellValue();
                leerlingen = new ArrayList<>();
                boolean bool = !naam.isEmpty();
                if (naam != null && !naam.isEmpty()) {
                    klas = excel.getRow(3).getCell(i).getStringCellValue();
                    for (int j = 4; j < 35; j++) {
                        lName = excel.getRow(j).getCell(i).getStringCellValue();
                        if (lName == null || lName.isEmpty()) {
                            break;
                        }
                        leerlingen.add(lName);
                    }
                    this.groepen.add(new Groep(naam, klas, leerlingen, bob, contactLeer));
                } else {
                    break;
                }
            }

            if (this.groepen.size() == 0) {
                throw new IllegalArgumentException("Er moeten groepen zitten in de Excel file.");
            }
            System.out.print(this.groepen);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public Bob getBob() {
        return bob;
    }

    public void setBob(Bob bob) {
        if (bob == null) {
            throw new IllegalArgumentException("Bob mag niet leeg zijn");
        }
        this.bob = bob;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam.isEmpty() || naam.equals("")) {
            throw new IllegalArgumentException("Naam Sessie mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        if (startDatum == null) {
            throw new IllegalArgumentException("startDatum mag niet leeg zijn");
        }
        this.startDatum = startDatum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isContactLeer() {
        return contactLeer;
    }

    public void setContactLeer(boolean contactLeer) {
        this.contactLeer = contactLeer;
    }
}
