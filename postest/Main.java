package postest;

class License {
    String nomor, berlakuSampai;
    License(String nomor, String berlakuSampai) {
        this.nomor = nomor;
        this.berlakuSampai = berlakuSampai;
    }
}

class Vehicle {
    void startEngine() {
        System.out.println("Mesin Nyala");
    }
}

class Car extends Vehicle {
    void startEngine() {
        System.out.println("Mesin Mobil Nyala");
    }
}

class Motorcycle extends Vehicle {
    void startEngine() {
        System.out.println("Mesin Motor Nyala");
    }
}

class Driver {
    String nama;
    Vehicle kendaraan;
    License lisensi;

    Driver(String nama, Vehicle kendaraan, License lisensi) {
        this.nama = nama;
        this.kendaraan = kendaraan;
        this.lisensi = lisensi;
    }

    void tampilkanInfo() {
        System.out.println("Pengemudi: " + nama);
        System.out.println("Jenis Kendaraan: " +
            (kendaraan instanceof Car ? "Mobil" : "Motor"));
        kendaraan.startEngine();
        System.out.println("Nomor Lisensi: " + lisensi.nomor);
        System.out.println("Berlaku Sampai: " + lisensi.berlakuSampai);
    }
}

public class Main {
    public static void main(String[] args) {
        new Driver("Tisam", new Car(), new License("LIC001", "2026-07-01")).tampilkanInfo();
        System.out.println();
        new Driver("nina", new Motorcycle(), new License("LIC002", "2025-05-22")).tampilkanInfo();
    }
}
