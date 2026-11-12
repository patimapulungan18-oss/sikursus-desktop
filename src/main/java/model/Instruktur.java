package model;

public class Instruktur extends Orang {

    // Field khusus instruktur
    private String keahlian;

    // Constructor
    public Instruktur(int id, String nama, String noHp,
                      String keahlian) {
        super(id, nama, noHp);
        setKeahlian(keahlian);
    }

    // Getter
    public String getKeahlian() {
        return keahlian;
    }

    // Setter dengan validasi
    public void setKeahlian(String keahlian) {
        if (keahlian == null || keahlian.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Keahlian wajib diisi");
        }

        this.keahlian = keahlian.trim();
    }

    // Override getInfo()
    @Override
    public String getInfo() {
        return "INSTRUKTUR | " + super.getInfo()
                + " | Keahlian: " + getKeahlian();
    }
}