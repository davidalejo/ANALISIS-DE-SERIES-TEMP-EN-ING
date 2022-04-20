package com.mycompany.claseacp;

import java.util.Random;
import javax.swing.JOptionPane;

public class Algoritmo_2_1_B {20020

    static int v[], w[];
    static int tam; // Tamaño de los vectores 

    public static void main(String[] args) {

        tam = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese tamaño de vectores: "));

        // Redimensionar vectores
        v = new int[tam];
        w = new int[tam];
        System.out.println("Tamaño vectores " + v.length);

        v = aleatorios(tam);
        w = aleatorios(tam);

        long tiempo1 = 0;
        long tiempo2 = 0;
        long tiempo3 = 0;

        // PRODUCTO ESCALAR SIMPLE
        long startTime1 = System.nanoTime();
        pEscalarNormal(tam);
        long endTime1 = System.nanoTime();
        tiempo1 = endTime1 - startTime1;
        System.out.println("NORMAL         " + tiempo1 + "    ns");

        // PRODUCTO ESCALAR CHAINING
        long startTime2 = System.nanoTime();
        pEscalarChaining(tam);
        long endTime2 = System.nanoTime();
        tiempo2 = endTime2 - startTime2;
        System.out.println("CHAINING       " + tiempo2 + "    ns");

        // PRODUCTO ESCALAR DESENROLLADO
        long startTime3 = System.nanoTime();
        pEscalarDenrolado(tam);
        long endTime3 = System.nanoTime();
        tiempo3 = endTime3 - startTime3;
        System.out.println("DESENRROLLADO  " + tiempo3 + "    ns");

    }

    static int[] aleatorios(int tm) {
        int desde = 1;
        int hasta = 20;
        int[] numeros = new int[tam];
        Random rnd = new Random();
        for (int i = 0; i < tm; i++) {
            numeros[i] = rnd.nextInt(hasta - desde + 1) + desde;
        }
        return numeros;
    }

    // Metodo para hallar el producto escalar
    /*
   Encadenado de un sumados y un multiplicador (Chaining)
   Unidad segmentada en dos etapas que permite hacer de forma eficiente 
   operaciones de este tipo. 
   Evitando accesos innecesarios a memoria en cada paso del bucle.
     */
 /*
        - Producto Escalar sin aplicar ninguna técnica de paralalelismo
     */
    static void pEscalarNormal(int tamano) {
        int xEscalar = 0;
        int temp = 0;
        int xTem[] = new int[tamano];
        for (int i = 0; i < v.length; i++) {
            xTem[i] = v[i] * w[i];
        }

        for (int i = 0; i < v.length; i++) {
            temp = temp + xTem[i];
        }
    }

    /*
        - Producto Escalar aplicando chaining 
     */
    static double pEscalarChaining(int tam) {
        int xEscalar = 0;
        for (int i = 0; i < tam; i++) {
            xEscalar = xEscalar + (v[i] * w[i]);
        }
        return xEscalar;
    }

    /*
        - Producto Escalar aplicando desenrrolado 
     */
    static double pEscalarDenrolado(int tam) {
        int xEscalar = 0;
        for (int i = 0; i < tam; i += 2) {
            //try {
                xEscalar = xEscalar + (v[i] * w[i]);
                xEscalar = xEscalar + (v[i + 1] * w[i + 1]);
            //} catch (ArrayIndexOutOfBoundsException e) {}

        }
        return xEscalar;
    }

}
