package model;

public class Kursus {
    private String kode;
    private String nama;
    private String level;
    private double biaya;

    // Constructor kosong
    public Kursus() {
    }

    // Constructor lengkap dengan validasi biaya
    public Kursus(String kode, String nama,
                  String level, double biaya) {
        this.kode = kode;
        this.nama = nama;
        this.level = level;
        setBiaya(biaya);
    }

    // Getter dan setter biaya
    public double getBiaya() {
        return biaya;
    }

    public void setBiaya(double biaya) {
        if (biaya < 0) {
            throw new IllegalArgumentException(
                "Biaya tidak boleh negatif"
            );
        }
        this.biaya = biaya;
    }

    // Method menghitung biaya setelah diskon
    public double hitungBiayaSetelahDiskon(
            double persenDiskon) {
        return biaya - (biaya * persenDiskon / 100.0);
    }
}