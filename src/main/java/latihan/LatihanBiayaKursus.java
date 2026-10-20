
package latihan;

public class LatihanBiayaKursus {

    public static void main(String[] args) {

        String kode = "JAVA-BSC";
        String namaKursus = "Java Desktop Fundamental";
        boolean aktif = true;

        double regFee = 1000000;
        double biayaTambahan = 2700000;

        double subtotal = regFee + biayaTambahan;
        double diskonRate = 0.10;
        double potongan = subtotal * diskonRate;
        double totalAkhir = subtotal - potongan;

        String status;

        if (totalAkhir >= 3000000) {
            status = "MAHAL";
        } else if (totalAkhir >= 1500000) {
            status = "STANDAR";
        } else {
            status = "EKONOMIS";
        }

        System.out.println("Kode        : " + kode);
        System.out.println("Kursus      : " + namaKursus);
        System.out.println("Aktif       : " + aktif);
        System.out.printf("Reg Fee     : Rp%,.0f%n", regFee);
        System.out.printf("Subtotal    : Rp%,.0f%n", subtotal);
        System.out.printf("Diskon Rate : %.1f%%%n", diskonRate * 100);
        System.out.printf("Potongan    : Rp%,.0f%n", potongan);
        System.out.println("------------------------------");
        System.out.printf("Total Akhir : Rp%,.0f%n", totalAkhir);
        System.out.println("Status      : " + status);
    }
}
