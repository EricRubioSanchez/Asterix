package Objectes.Asterix;

public abstract class Personatge extends Element implements Moviment {
    int energia=0,velocitat;
    public Personatge(char nom){
        super(nom);
        this.velocitat=1;
    }
    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    public int getVelocitat() {return velocitat;}
}
