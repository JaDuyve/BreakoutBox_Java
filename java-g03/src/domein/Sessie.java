package domein;

import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
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
        setCode(random.nextInt(10000));
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
            throw new IllegalArgumentException("Naam sessie mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        if (startDatum == null) {
            throw new IllegalArgumentException("Startatum mag niet leeg zijn");
        }
        Date vandaag = new Date();
        if (startDatum.before(vandaag)) {
            throw new IllegalArgumentException("De startdatum mag niet in het verleden liggen");
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

    public List<Groep> getGroepen() {
        return groepen;
    }

    public void setGroepen(List<Groep> groepen) {
        this.groepen = groepen;
    }

    @Override
    public String toString() {
        return this.getNaam().toLowerCase();
    }

    public void generateSessieOverzichtPdf() {

        Comparator<Groep> byGroepNaam = (o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam());
        SortedList<Groep> sortGroep = new SortedList<>(FXCollections.observableList(this.groepen), byGroepNaam);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        int teller;
        try {
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();

            contentStream.setLeading(16);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

            contentStream.newLineAtOffset(65, 700);
            contentStream.showText("Sessie " + this.getNaam());
            contentStream.newLine();
            contentStream.showText("Sessie code " + this.getCode());
            contentStream.newLine();
            contentStream.newLine();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.showText("Paden");
            contentStream.newLine();
            contentStream.newLine();

            String tab = "     ";
            String tab2 = "          ";

            for (Groep groep : sortGroep) {
                teller = 0;

                contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                contentStream.showText("\u25CF"); // bullet
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.showText(tab + groep.getNaam());
                contentStream.newLine();
                contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                contentStream.showText(tab2 + "\u25CF"); // arrow
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.showText("Klas: " + groep.getKlas());
                contentStream.newLine();

                contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                contentStream.showText(tab2 + "\u25CF"); // arrow
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.showText("Leerlingen");
                contentStream.newLine();

                for (String leerling : groep.getLeerlingen()) {
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText("Leerling: " + leerling);
                    contentStream.newLine();
                }
                contentStream.newLine();


                contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                contentStream.showText(tab2 + "\u25CF"); // arrow
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.showText("Oefenigen");
                contentStream.newLine();

                for (Pad pad : groep.getPaden().values()) {
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText("Oefening: " + pad.getOefening());
                    contentStream.newLine();
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText("Groepsbewerking: " + pad.getGroepsbewerking());
                    contentStream.newLine();
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText("Antwoord: " + pad.getAntwoord());
                    contentStream.newLine();
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText("Actie: " + (pad.getActie() == null ? new Actie("Zoek de schatkist", "Zoek de schatkist in de klas") : pad.getActie()));
                    contentStream.newLine();
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText("Toegangscode: " + pad.getToegangscode());
                    contentStream.newLine();
                    contentStream.showText("------------------------------------------------------------");
                    contentStream.newLine();
                    teller++;
                    if(teller%3 == 0 )
                    {
                        if(teller != groep.getPaden().size())
                        {
                            contentStream.endText();
                            contentStream.close();
                            page = new PDPage();
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);

                            contentStream.beginText();
                            contentStream.newLineAtOffset(65, 700);
                            contentStream.setLeading(16);
                        }

                    }

                }
                contentStream.newLine();

                contentStream.endText();
                contentStream.close();

                if (!groep.equals(sortGroep.get(sortGroep.size() - 1))) {
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);

                    contentStream.beginText();
                    contentStream.newLineAtOffset(65, 700);
                    contentStream.setLeading(16);
                }
            }

            String fileName = this.getNaam() + ".pdf";
            // Saving Document
            document.save(fileName);

            document.close();

            Desktop.getDesktop().open(new File(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
