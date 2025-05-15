package komposisi;

class Kamar{
    String tipe;

    Kamar(String tipe){
        this.tipe = tipe;
    }
    void info(){
        System.out.println("kamar tipe" + tipe);
    }
}

class Rumah{
    private Kamar kamar;

    Rumah (){
        kamar = new Kamar("Mandi");
    }

    void lihatkamar(){  
        kamar.info();
    }
}


public class Main {
    public static void main(String[] args){
        Rumah rumah = new Rumah();
        rumah.lihatkamar();
    }
}
