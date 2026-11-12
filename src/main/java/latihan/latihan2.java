package latihan;

public class latihan2 {

    public static void main(String[] args) {

        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";

        double biaya = 3_146_000;
        double registrasi = 500_000;

        // Total sebelum diskon
        double totalSebelumDiskon = biaya + registrasi;

        // Menentukan diskon
        double diskon;

        if (totalSebelumDiskon >= 600_000) {
            diskon = 0.10;
        } else {
            diskon = 0.05;
        }

        // Menghitung potongan
        double potongan = totalSebelumDiskon * diskon;

        // Total akhir
        double total = totalSebelumDiskon - potongan;

        // Menentukan status
        String status;

        if (total >= 600_000) {
            status = "MAHAL";
        } else {
            status = "TERJANGKAU";
        }

        // Menampilkan hasil
        System.out.println("Kode              : " + kode);
        System.out.println("Kursus            : " + nama);
        System.out.printf("Biaya             : Rp%,.0f%n", biaya);
        System.out.printf("Registrasi        : Rp%,.0f%n", registrasi);
        System.out.printf("Total Sebelum     : Rp%,.0f%n", totalSebelumDiskon);
        System.out.println("Diskon            : " + (diskon * 100) + "%");
        System.out.printf("Potongan          : Rp%,.0f%n", potongan);
        System.out.printf("Total Akhir       : Rp%,.0f%n", total);
        System.out.println("Status            : " + status);
    }
}