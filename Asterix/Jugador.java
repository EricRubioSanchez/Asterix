package Objectes.Asterix;

public class Jugador extends Personatge implements Moviment{
    public Jugador(char nom){
        super(nom);
        setVelocitat(1);
    }
    public void prenPocio(){
        energia=10;
        setVelocitat(2);
    }

    /**
     * Es mou cap adalt movent les seves coordenades i crida a controlarEnergia
     */
    public void mouW(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila()-velocitat,c.getColumna());
        setCord(nouC);
        controlarEnergia();
    }
    /**
     * Es mou cap esquerra movent les seves coordenades i crida a controlarEnergia
     */
    public void mouA(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila(),c.getColumna()-velocitat);
        setCord(nouC);
        controlarEnergia();
    }
    /**
     * Es mou cap avall movent les seves coordenades i crida a controlarEnergia
     */
    public void mouS(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila()+velocitat,c.getColumna());
        setCord(nouC);
        controlarEnergia();
    }
    /**
     * Es mou cap dreta movent les seves coordenades i crida a controlarEnergia
     */
    public void mouD(){
        Coordenades c=getCord(),nouC;
        nouC =new Coordenades(c.getFila(),c.getColumna()+velocitat);
        setCord(nouC);
        controlarEnergia();

    }

    /**
     * Si la energia es major que zero se li resta 1 i si ha arribat a zero li canviem la velocitat a 1.
     */
    private void controlarEnergia(){
        if (energia>0) {
            --energia;
            if(energia==0){
                setVelocitat(1);
            }
        }
        System.out.println("Turns amb energia: "+energia);
    }
    public int getEnergia(){
        return energia;
    }
}
