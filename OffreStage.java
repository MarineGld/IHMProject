

public class OffreStage {

    private int id;
    private String nomStructure;
    private String sujetStage;
    private int duree;
    private String debutStage;
    private int promo;

    public OffreStage(int id, String nomStructure, String sujetStage, int duree, String debutStage, int promo){

        this.id = id;
        this.nomStructure = nomStructure;
        this.sujetStage = sujetStage;
        this.duree = duree;
        this.debutStage = debutStage;
        this.promo = promo;

    }

    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public String getSujetStage() {
        return sujetStage;
    }

    public void setSujetStage(String sujetStage) {
        this.sujetStage = sujetStage;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDebutStage() {
        return debutStage;
    }

    public void setDebutStage(String debutStage) {
        this.debutStage = debutStage;
    }

    public String getPromo() {
        if (promo == 1) {
            return "L3";
        }
        else if (promo == 2) {
            return "M1";
        }
        else {
            return "M2";
        }
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    public int getId() {return id;}

    @Override
    public String toString() {
        return nomStructure;
    }
}
