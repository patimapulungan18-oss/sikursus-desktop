package latihan;

public class LatihanBidangDatar {
    public static void main(String[] args) {

        int panjang = 600;
        int lebar = 500;

        int luas = panjang * lebar;
        int keliling = 2 * (panjang + lebar);

        System.out.println("=== BIDANG DATAR PERSEGI PANJANG ===");
        System.out.println("Panjang  : " + panjang + " cm");
        System.out.println("Lebar    : " + lebar + " cm");
        System.out.println("Luas     : " + luas + " cm2");
        System.out.println("Keliling : " + keliling + " cm");
    }
}