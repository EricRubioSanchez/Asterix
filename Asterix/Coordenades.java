package Objectes.Asterix;

import java.util.Objects;

public class Coordenades {
    int fila,columna;
    public Coordenades(){
        this.fila=0;
        this.columna=0;
    }
    public Coordenades(int fila,int columna){
        this.fila=fila;
        this.columna=columna;
    }
    public Coordenades(int minF, int maxF, int minC, int maxC){
        this.fila =(int)(Math.random()*(maxF-1)+minF);
        this.columna =(int)(Math.random()*(maxC-1)+minC);

    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Coordenades{" +
                "fila=" + fila +
                ", columna=" + columna +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenades that = (Coordenades) o;
        return fila == that.fila && columna == that.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}
