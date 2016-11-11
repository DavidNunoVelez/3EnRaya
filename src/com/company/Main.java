package com.company;


import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static Boolean finDePartida=false;
    static String[][] tablero = new String[3][3];
    static int jugadorInt =1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        //el tablero esta inicializado pero tiene valores nulos
        InicializarTablero(tablero);
        //el tablero ya tiene valores "espacio"
        DibujarTablero(tablero);

        while (!finDePartida) {

            //saber si ha jugado bien
            if (pedirJugar(jugadorInt)) {
                finDePartida=esFinPartida(tablero);

                //cambiar de jugador
                cambiarDeJugador(jugadorInt);
            }

            DibujarTablero(tablero);
        }
        System.out.println("JUGADOR"+jugadorInt + " HAS GANADO.");

    }

    private static void cambiarDeJugador(int jugador) {
        if (jugadorInt==1){
            jugadorInt=2;
        } else{
            jugadorInt =1;
        }
    }

    private static void InicializarTablero(String[][] tablero) {
        tablero[0][0]=" ";
        tablero[0][1]=" ";
        tablero[0][2]=" ";
        tablero[1][0]=" ";
        tablero[1][1]=" ";
        tablero[1][2]=" ";
        tablero[2][0]=" ";
        tablero[2][1]=" ";
        tablero[2][2]=" ";
    }

    private static void DibujarTablero(String[][] tablero) {
        System.out.println("#########################");
        System.out.println("#       #       #       #");
        System.out.println("#  " + tablero[0][0] + "    #   " + tablero[0][1] + "   #   " + tablero[0][2] + "   #");
        System.out.println("#       #       #       #");
        System.out.println("#########################");
        System.out.println("#       #       #       #");
        System.out.println("#  " + tablero[1][0] + "    #   " + tablero[1][1] + "   #   " + tablero[1][2] + "   #");
        System.out.println("#       #       #       #");
        System.out.println("#########################");
        System.out.println("#       #       #       #");
        System.out.println("#  " + tablero[2][0] + "    #   " + tablero[2][1] + "   #   " + tablero[2][2] + "   #");
        System.out.println("#       #       #       #");
        System.out.println("#########################");

    }

    private static boolean pedirJugar(int jugador) {
        System.out.println("---JUGADOR"+jugador);
        System.out.println("Escribe tu jugada (x-y)");
        Scanner scanner = new Scanner(System.in);
        String jugada = scanner.next();
        if (!cerrarJugada(jugada,jugador)){
            System.out.println("JUGADOR"+jugador + " esa ficha ya está jugada. Prueba otra.");
            return false;
        }

        return true;

    }

    private static boolean cerrarJugada(String jugada, int jugador) {
        if (jugadaBuena (jugada,tablero)){

            actualizarTablero(jugada,tablero,jugador);
            return true;
        }
        return false;
    }

    private static void actualizarTablero(String jugada, String[][] tablero,int jugador) {
        char[] jugadaChr= jugada.toCharArray();
        String filaStr = new StringBuilder().append(jugadaChr[0]).toString();

        int fila = Integer.parseInt(filaStr);
        String columnStr = new StringBuilder().append(jugadaChr[2]).toString();

        int columna  = Integer.parseInt(columnStr );

        if (jugador==1) {
            tablero[fila][columna] = "x";
        }
        else
        {
            tablero[fila][columna] = "0";

        }
    }


    //aqui nos quedamos
    private static boolean jugadaBuena(String jugada, String[][] tablero) {
        char[] jugadaChr= jugada.toCharArray();

        String filaStr = new StringBuilder().append(jugadaChr[0]).toString();
        int fila = Integer.parseInt(filaStr);

        String columnStr = new StringBuilder().append(jugadaChr[2]).toString();
        int columna  = Integer.parseInt(columnStr);



        if(tablero[fila][columna]!=" "){
            return false;
        } else{return true;}
    }

    private static boolean esFinPartida(String[][] tablero) {
        if (comprobarHorizontales(tablero) || comprobarVerticales(tablero) || comprobarDiagonales(tablero)) return true;
        return false;
    }

    private static boolean comprobarDiagonales(String[][] tablero) {
        return igualesDiagonal1(tablero) || igualesDiagonal2();
    }

    private static boolean igualesDiagonal2() {
        if (tablero[0][2] != " " && tablero[2][0] != " ")
            return ((tablero[0][2] == tablero[1][1]) && (tablero[1][1] == tablero[2][0]));
        return false;
    }

    private static boolean igualesDiagonal1(String[][] tablero) {
        if (tablero[0][0] != " " && tablero[2][2] != " ")
            return ((tablero[0][2] == tablero[1][1]) && (tablero[1][1] == tablero[2][0]));
        return false;
    }

    private static boolean comprobarVerticales(String[][] tablero) {
        return igualesVertical1(tablero) || igualesVertical2()|| igualesVertical3();


    }

    private static boolean igualesVertical3() {
        if (tablero[0][2] != " ")
            return ((tablero[0][2] == tablero[1][2]) && (tablero[1][2] == tablero[2][2]));
        return false;
    }

    private static boolean igualesVertical2() {
        if (tablero[0][1] != " ")
            return ((tablero[0][1] == tablero[1][1]) && (tablero[1][1] == tablero[2][1]));
        return false;
    }

    private static boolean igualesVertical1(String[][] tablero) {
        if (tablero[0][0] != " ")
            return ((tablero[0][0] == tablero[1][0]) && (tablero[1][0] == tablero[2][0]));
        return false;
    }

    private static boolean comprobarHorizontales(String[][] tablero) {
        return igualesHorizontal1(tablero) || igualesHorizontal2()|| igualesHorizontal3();

    }

    private static boolean igualesHorizontal3() {
        if (tablero[2][0] != " ")
            return ((tablero[2][0] == tablero[2][1]) && (tablero[2][1] == tablero[2][2]));
        return false;
    }

    private static boolean igualesHorizontal2() {
        if (tablero[1][0] != " ")
            return ((tablero[1][0] == tablero[1][1]) && (tablero[1][1] == tablero[1][2]));
        return false;
    }

    private static boolean igualesHorizontal1(String[][] tablero) {
        if (tablero[0][0] != " ")
            return ((tablero[0][0] == tablero[0][1]) && (tablero[0][1] == tablero[0][2]));
        return false;
    }
}