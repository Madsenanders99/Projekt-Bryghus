package gui;

public class tmpOrdrelinje {

    private String produktNavn = "ikke angivet";
    private double pris = 0.0;
    private double rabatProcent = 0.0;
    private int antal = 0;

    public String getNavn() {
        return produktNavn;
    }

    public void setNavn(String produktNavn) {
        this.produktNavn = produktNavn;
    }

    public double getStkPris() {
        return pris;
    }

    public void setStkPris(double pris) {
        this.pris = pris;
    }

    public double getSamletPris()
    {
        return this.pris * this.antal;
    }


    public double getRabat() {
        return this.rabatProcent;
    }

    public void setRabat(double rabat) {
        this.rabatProcent = rabat;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }
}
