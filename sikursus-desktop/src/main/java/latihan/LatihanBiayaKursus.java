package latihan;

public class LatihanBiayaKursus {
    
    public static void main(String[] args) {
        // --- DATA AWAL ---
        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";
        double biaya = 2_700_000;
        boolean aktif = true;
        
        // 1. Tambahkan biaya registrasi Rp25.000
        double biayaRegistrasi = 1_000_000;
        double totalSebelumDiskon = biaya + biayaRegistrasi;
        
        // 2. Logic Diskon Bersyarat (Jika >= 600k dapet 10%, selain itu 5%)
        double rateDiskon;
        if (totalSebelumDiskon >= 750_000) {
            rateDiskon = 0.10; // 10%
        } else {
            rateDiskon = 0.05; // 5%
        }
        
        // Kalkulasi akhir
        double potongan = totalSebelumDiskon * rateDiskon;
        double totalAkhir = totalSebelumDiskon - potongan;
        
        // 3. Tampilkan status MAHAL / TERJANGKAU (Pake ternary operator biar clean)
        String status = (totalAkhir >= 750_000) ? "MAHAL" : "TERJANGKAU";
        
        // --- OUTPUT KE CONSOLE ---
        System.out.println("Kode        : " + kode);
        System.out.println("Kursus      : " + nama);
        System.out.println("Aktif       : " + aktif);
        System.out.printf("Reg Fee     : Rp%,.0f%n", biayaRegistrasi);
        System.out.printf("Subtotal    : Rp%,.0f%n", totalSebelumDiskon);
        System.out.println("Diskon Rate : " + (rateDiskon * 100) + "%");
        System.out.printf("Potongan    : Rp%,.0f%n", potongan);
        System.out.println("---------------------------------");
        System.out.printf("Total Akhir : Rp%,.0f%n", totalAkhir);
        System.out.println("Status      : " + status);
    }
}