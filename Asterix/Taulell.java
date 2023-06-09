package Objectes.Asterix;

public class Taulell {
    private int files,columnes;
    private static char [][] taulell;
    public Taulell(int files,int columnes){
        this.files=files;
        this.columnes=columnes;
        taulell= new char[files][columnes];
        for (int i = 0; i <= files - 1; i++) {
            for (int y = 0; y <= columnes - 1; y++) {
                taulell[i][y] = '·';
            }
        }
    }

    /**
     * Mostra per pantalla la matriu del taullel.
     */
    public void dibu(){
        System.out.print(" ");
        for (int i = 0; i <= taulell[0].length - 1; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();
        char[] lletres = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i <= taulell.length - 1; i++) {
            System.out.print(lletres[i]);
            for (int y = 0; y <= taulell[i].length - 1; y++) {
                System.out.print("  ");
                System.out.print(taulell[i][y]);
            }
            System.out.println();
        }

    }

    /**
     * Afageix dintre del taullel els parametres.
     * @param p Tipus Pocio, a la celula on les coordenades de la pocio posem el seu nom.
     * @param jug Tipus Jugador, a la celula on les coordenades del jugador posem el seu nom.
     * @param enemics Tipus Enemic[], a la celula on les coordenades de cada un dels enemics posem el seu nom.
     */
    public void init(Pocio p,Jugador jug,Enemic[] enemics){
        Coordenades c;
        c=p.getCord();
        taulell[c.getFila()][c.getColumna()] = p.getNom();
        c=jug.getCord();
        taulell[c.getFila()][c.getColumna()] = jug.getNom();
        for(Enemic enemic:enemics){
            c=enemic.getCord();
            taulell[c.getFila()][c.getColumna()] = enemic.getNom();
        }
    }
    public char getCelula(int fila,int columna){
        return taulell[fila][columna];
    }
    public void setCelula(int fila,int columna,char caracter){
        taulell[fila][columna]=caracter;
    }
}
