import java.util.Scanner;

public class DuniaPetualangan {

    static Scanner input = new Scanner(System.in);

    public static int random(int batas) {
        return (int) (System.currentTimeMillis() % 1000 % batas);
    }

    public static boolean sama(String a, String b) {
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    static class Pemain {
        String nama;
        String sandi;

        Pemain(String n, String s) {
            nama = n;
            sandi = s;
        }

        boolean verifikasi(String s) {
            return sama(sandi, s);
        }

        String getNama() {
            return nama;
        }
    }

    static class PengelolaPemain {
        static Pemain[] dataPemain = new Pemain[100];
        static int totalPemain = 0;

        static boolean daftar(String nama, String sandi) {
            for (int i = 0; i < totalPemain; i++) {
                if (sama(dataPemain[i].getNama(), nama)) return false;
            }
            dataPemain[totalPemain++] = new Pemain(nama, sandi);
            return true;
        }

        static Pemain masuk(String nama, String sandi) {
            for (int i = 0; i < totalPemain; i++) {
                if (sama(dataPemain[i].getNama(), nama) && dataPemain[i].verifikasi(sandi)) {
                    return dataPemain[i];
                }
            }
            return null;
        }
    }

    static class Ransel {
        String[] barang = new String[10];
        int potion = 3;
        int jumlah = 0;

        void tampilkan(double darah, double energi) {
            System.out.println("==== Status Pemain ====");
            System.out.println("Darah   : " + darah);
            System.out.println("Energi  : " + energi);
            System.out.println("Potion  : " + potion);
            System.out.println("Barang Lain:");
            for (int i = 0; i < barang.length; i++) {
                System.out.println((i + 1) + ". " + (barang[i] == null ? "null" : barang[i]));
            }
        }

        void ambil(String benda) {
            if (jumlah < barang.length) {
                barang[jumlah++] = benda;
                System.out.println("Kamu mendapatkan item: " + benda);
            }
        }

        boolean gunakanPotion() {
            if (potion > 0) {
                potion--;
                return true;
            }
            return false;
        }
    }

    static class Permainan {
        Ransel tas = new Ransel();
        double darah = 100;
        double energi = 100;
        int tingkat = 1;

        void mulai() {
            boolean main = true;
            while (main) {
                System.out.println("\n1. Buka ransel\n2. Mulai menjelajah\n3. Gunakan potion\n4. Keluar");
                System.out.print("Pilihan: ");
                String pilih = input.nextLine();

                if (sama(pilih, "1")) {
                    tas.tampilkan(darah, energi);
                } else if (sama(pilih, "2")) {
                    jelajah();
                } else if (sama(pilih, "3")) {
                    if (tas.gunakanPotion()) {
                        darah += 30;
                        energi += 10;
                        System.out.println("Potion berhasil digunakan!");
                    } else {
                        System.out.println("Potion habis!");
                    }
                } else if (sama(pilih, "4")) {
                    System.out.println("Keluar dari permainan...");
                    main = false;
                } else {
                    System.out.println("Pilihan tidak dikenal.");
                }
            }
        }

        void jelajah() {
            boolean lanjut = true;
            while (lanjut) {
                System.out.println("\nDarahmu : " + darah);
                System.out.println("Energi  : " + energi);
                System.out.println("Tingkat : " + tingkat);
                System.out.println("1. Maju\n2. Kanan\n3. Kiri\n4. Kembali");
                System.out.print("Arah: ");
                String arah = input.nextLine();

                if (sama(arah, "1") || sama(arah, "2") || sama(arah, "3")) {
                    int kejadian = random(3);
                    if (kejadian == 0) {
                        String[] daftarItem = {"Pedang", "Nasi", "Panah", "Peta"};
                        String dipilih = daftarItem[random(daftarItem.length)];
                        tas.ambil(dipilih);
                    } else if (kejadian == 1) {
                        lawanMusuh();
                    } else {
                        System.out.println("Kamu terperosok ke jebakan!");
                        darah -= 5;
                    }
                } else if (sama(arah, "4")) {
                    System.out.println("Kembali ke menu utama...");
                    lanjut = false;
                } else {
                    System.out.println("Gerakan tidak valid.");
                }
            }
        }

        void lawanMusuh() {
            String[] musuhList = {"Titan Kolosal", "Titan Pendiri"};
            String musuh = musuhList[random(musuhList.length)];
            double hpMusuh = 40 + random(30);
            double manaMusuh = 20 + random(10);
            double serangMusuh = 5 + random(10);

            System.out.println("Musuh muncul!");
            System.out.println("Nama Musuh  : " + musuh);
            System.out.println("HP Musuh    : " + hpMusuh);
            System.out.println("Mana Musuh  : " + manaMusuh);
            System.out.println("Serangan    : " + serangMusuh);
            System.out.println("HP Anda     : " + darah);
            System.out.println("1. Serang\n2. Lari");
            System.out.print("Aksi: ");
            String aksi = input.nextLine();

            if (sama(aksi, "1")) {
                while (hpMusuh > 0 && darah > 0) {
                    double dmgToEnemy = 20 + random(10);
                    double dmgToYou = serangMusuh;
                    hpMusuh -= dmgToEnemy;
                    darah -= dmgToYou;

                    System.out.println("Kamu menyerang musuh: -" + dmgToEnemy);
                    if (hpMusuh > 0) {
                        System.out.println("Musuh menyerang balik: -" + dmgToYou);
                    }
                }

                if (darah > 0) {
                    System.out.println("Kamu menang!");
                    tingkat++;
                    System.out.println("Naik level! Sekarang level " + tingkat);
                } else {
                    System.out.println("Kamu kalah...");
                }

            } else if (sama(aksi, "2")) {
                System.out.println("Kamu berhasil kabur.");
            } else {
                System.out.println("Aksi tidak diketahui.");
            }
        }
    }

    public static void main(String[] args) {
        Pemain pemain = null;
        System.out.println("=== PETUALANGAN DUNIA TERBUKA ===");

        while (pemain == null) {
            System.out.println("\n1. Masuk\n2. Daftar\n3. Keluar");
            System.out.print("Pilihan: ");
            String menu = input.nextLine();

            if (sama(menu, "1")) {
                System.out.print("Nama pengguna: ");
                String n = input.nextLine();
                System.out.print("Kata sandi   : ");
                String s = input.nextLine();
                pemain = PengelolaPemain.masuk(n, s);
                if (pemain == null) {
                    System.out.println("Gagal masuk.");
                } else {
                    System.out.println("Selamat datang, " + pemain.getNama() + "!");
                }

            } else if (sama(menu, "2")) {
                System.out.print("Pilih nama pengguna: ");
                String n = input.nextLine();
                System.out.print("Pilih kata sandi   : ");
                String s = input.nextLine();
                if (PengelolaPemain.daftar(n, s)) {
                    System.out.println("Pendaftaran berhasil. Silakan login.");
                } else {
                    System.out.println("Nama pengguna sudah digunakan.");
                }

            } else if (sama(menu, "3")) {
                System.out.println("Keluar dari game.");
                return;

            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        Permainan p = new Permainan();
        p.mulai();
    }
}
