package lthn8;

class HealthRecord {
    String nomor, tanggal;
    HealthRecord(String n, String t) { 
        nomor = n; tanggal = t;
     }
}

class Animal {
    HealthRecord rekam;
    Animal(String n, String t) { 
        rekam = new HealthRecord(n, t);
     }
    void makeSound() {
         System.out.println("Suara hewan"); 
        }
}

class Dog extends Animal {
    Dog(String n, String t) { 
        super(n, t); 
    }
    void makeSound() { 
        System.out.println("Guk-Guk!");
     }
}

class Cat extends Animal {
    Cat(String n, String t) { super(n, t); }
    void makeSound() { 
        System.out.println("Meong!");
     }
}

class Owner {
    String nama;
    Animal hewan;
    Owner(String n, Animal h) {
         nama = n; hewan = h; 
        }

    void tampil() {
        System.out.println("Pemilik: " + nama);
        System.out.println("Jenis hewan: " + (hewan instanceof Dog ? "Anjing" : "Kucing"));
        hewan.makeSound();
        System.out.println("No rekam medis: " + hewan.rekam.nomor);
        System.out.println("Tanggal rekam medis: " + hewan.rekam.tanggal);
    }
}

public class Main {
    public static void main(String[] args) {
        new Owner("Tisam", new Dog("22", "2025-05-14")).tampil();
        System.out.println();
        new Owner("Nina", new Cat("200", "2025-05-15")).tampil();
    }
}
