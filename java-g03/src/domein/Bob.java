package domein;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Entity
public class Bob {

    @Id
    private String naam;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Oefening> lijstOefeningen;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Actie> lijstActies;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Toegangscode> lijstToegangscode;


    public Bob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes) {
        setNaam(naam);
        setLijstOefeningen(oefeningen);
        setLijstActies(acties);
        setLijstToegangscode(toegangscodes);

        controleerGenoegActies();
    }

    protected Bob() {

    }

    public String getNaam() {

        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new IllegalArgumentException("Naam Breakout Box mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public List<Oefening> getLijstOefeningen() {
        return lijstOefeningen;
    }

    public void setLijstOefeningen(List<Oefening> lijstOefeningen) {
        this.lijstOefeningen = lijstOefeningen;
    }

    public List<Actie> getLijstActies() {
        if (lijstActies == null || lijstActies.isEmpty()) {
            throw new IllegalArgumentException("Acties mag niet leeg gelaten worden.");
        }
        return lijstActies;
    }

    public void setLijstActies(List<Actie> lijstActies) {
        this.lijstActies = lijstActies;
    }

    public List<Toegangscode> getLijstToegangscode() {
        return lijstToegangscode;
    }

    public void setLijstToegangscode(List<Toegangscode> lijstToegangscode) {
        this.lijstToegangscode = lijstToegangscode;
    }

    public void controleerGenoegActies() {
        if (lijstOefeningen.size() != lijstActies.size() + 1) {
            throw new IllegalArgumentException("Er moet 1 ACTIE minder zijn dan oefeningen!");
        }
    }

    public void generateBobOverzichtPdf() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            // ------------
            contentStream.setLeading(16);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

            contentStream.newLineAtOffset(65, 700);
            contentStream.showText("Breakout-Box " + this.getNaam());
            contentStream.newLine();
            contentStream.newLine();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.showText("Oefeningen");
            contentStream.newLine();
            contentStream.newLine();

            String tab = "     ";
            String tab2 = "          ";

            for (Oefening oef : this.getLijstOefeningen()) {

                contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                contentStream.showText("\u25CF"); // bullet
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.showText(tab +  oef.getNaam());
                contentStream.newLine();

                for (Doelstellingscode doelstellingscode : oef.getDoelstellingscodes()) {
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText(tab +  doelstellingscode.getCode());
                    contentStream.newLine();
                    // ------------
                }
                contentStream.newLine();
            }

            for (Oefening oef : this.getLijstOefeningen()) {

                contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                contentStream.showText("\u25CF"); // bullet
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.showText(tab +  oef.getNaam());
                contentStream.newLine();

                for (Doelstellingscode doelstellingscode : oef.getDoelstellingscodes()) {
                    contentStream.setFont(PDType1Font.ZAPF_DINGBATS, 8);
                    contentStream.showText(tab2 + "\u27A4"); // arrow
                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.showText(tab +  doelstellingscode.getCode());
                    contentStream.newLine();
                    // ------------
                }
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
            String fileName = this.getNaam() + ".pdf";
            // Saving Document
            document.save(fileName);

            document.close();

            Desktop.getDesktop().open(new File(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return naam;
    }
}
