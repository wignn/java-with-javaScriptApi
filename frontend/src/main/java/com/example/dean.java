package com.example;

public class dean {
    public int tambah(){
        int a = 10;
        int b = 20;
        return a + b;
    }

    void xx (){
        int a = 10;
        int b = 20;
        int c = a + b;
        System.out.println(c);
    }
    
    public static void main(String[] args) {
        var d = new dean();

        System.out.println(d.tambah());
        d.xx();
        
    }
    
}
