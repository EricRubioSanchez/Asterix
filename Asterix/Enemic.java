package Objectes.Asterix;

public class Enemic extends Personatge implements Moviment{
    public Enemic(char nom){
        super(nom);
        setVelocitat(1);
    }
    /**
     * Es mou cap avall movent les seves coordenades
     */
    public void mouW(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila()-velocitat,c.getColumna());
        setCord(nouC);
    }
    /**
     * Es mou cap esquerra movent les seves coordenades
     */
    public void mouA(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila(),c.getColumna()-velocitat);
        setCord(nouC);
    }
    /**
     * Es mou cap avall movent les seves coordenades
     */
    public void mouS(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila()+velocitat,c.getColumna());
        setCord(nouC);
    }
    /**
     * Es mou cap dreta movent les seves coordenades
     */
    public void mouD(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila(),c.getColumna()+velocitat);
        setCord(nouC);
    }
}
