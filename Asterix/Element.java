package Objectes.Asterix;

public abstract class Element {
    Coordenades cord;
    char nom;
    public Element(char nom){
        this.nom=nom;
    }
    public Coordenades getCord() {
        return cord;
    }

    public void setCord(Coordenades cord) {
        this.cord = cord;
    }

    public char getNom() {
        return nom;
    }
}
