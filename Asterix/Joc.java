package Objectes.Asterix;

import java.awt.image.PackedColorModel;
import java.util.Locale;
import java.util.Scanner;

public class Joc {
    private static Jugador asterix = new Jugador('J');
    private static Taulell bosc;
    static final int COLUMNES = 20, FILES = 20;
    private static boolean fi = false;
    private static Enemic legionari = new Enemic('L'), legionari2 = new Enemic('L'), legionari3 = new Enemic('L'), legionari4 = new Enemic('L');
    private static Enemic[] legionaris = {legionari, legionari2, legionari3, legionari4};
    private static Pocio pocio = new Pocio('P');
    private static Scanner scan = new Scanner(System.in);
    private static Coordenades c;
    private static String resposta;

    public Joc() {
        bosc = new Taulell(FILES, COLUMNES);
        setCoordenades(asterix);
        setCoordenades(pocio);
        setCoordenades(legionari);
        legionaris[0] = legionari;
        setCoordenades(legionari2);
        legionaris[1] = legionari2;
        setCoordenades(legionari3);
        legionaris[2] = legionari3;
        setCoordenades(legionari4);
        legionaris[3] = legionari4;
        bosc.init(pocio, asterix, legionaris);
        bosc.dibu();
        while (!fi) {
            if (mouJugador()) fi = true;
            if (trepitjar()) fi = true;
            mouEnemics();
            if (trepitjar()) fi = true;
            bosc.dibu();
        }
    }

    /**
     * Serveix per comprobar si el jugador esta en la mateixa casella que el legionari en cas que ho sigui se elimina
     * un dels dos dependent de la energia.
     *
     * @return retorna true si has trepitjat a tots el legionaris o t'ha trepitjan a ti.
     */
    public static boolean trepitjar() {
        if (legionari != null && asterix.getCord().equals(legionari.getCord()) && asterix.getEnergia() > 0) {
            legionari = null;
            bosc.setCelula(asterix.getCord().getFila(), asterix.getCord().getColumna(), asterix.getNom());
        } else if (legionari != null && asterix.getCord().equals(legionari.getCord())) {
            System.out.println("Has Perdut");
            return true;
        }
        if (legionari2 != null && asterix.getCord().equals(legionari2.getCord()) && asterix.getEnergia() > 0) {
            legionari2 = null;
            bosc.setCelula(asterix.getCord().getFila(), asterix.getCord().getColumna(), asterix.getNom());
        } else if (legionari2 != null && asterix.getCord().equals(legionari2.getCord())) {
            System.out.println("Has Perdut");
            return true;
        }
        if (legionari3 != null && asterix.getCord().equals(legionari3.getCord()) && asterix.getEnergia() > 0) {
            legionari3 = null;
            bosc.setCelula(asterix.getCord().getFila(), asterix.getCord().getColumna(), asterix.getNom());
        } else if (legionari3 != null && asterix.getCord().equals(legionari3.getCord())) {
            System.out.println("Has Perdut");
            return true;
        }
        if (legionari4 != null && asterix.getCord().equals(legionari4.getCord()) && asterix.getEnergia() > 0) {
            legionari4 = null;
            bosc.setCelula(asterix.getCord().getFila(), asterix.getCord().getColumna(), asterix.getNom());
        } else if (legionari4 != null && asterix.getCord().equals(legionari4.getCord())) {
            System.out.println("Has Perdut");
            return true;
        }
        if (legionari4 == null && legionari3 == null && legionari2 == null) legionaris = new Enemic[]{legionari};
        else if (legionari4 == null && legionari3 == null && legionari == null) legionaris = new Enemic[]{legionari2};
        else if (legionari4 == null && legionari == null && legionari2 == null) legionaris = new Enemic[]{legionari3};
        else if (legionari == null && legionari3 == null && legionari2 == null) legionaris = new Enemic[]{legionari4};
        else if (legionari4 == null && legionari3 == null) legionaris = new Enemic[]{legionari, legionari2};
        else if (legionari4 == null && legionari2 == null) legionaris = new Enemic[]{legionari, legionari3};
        else if (legionari2 == null && legionari3 == null) legionaris = new Enemic[]{legionari, legionari4};
        else if (legionari4 == null && legionari == null) legionaris = new Enemic[]{legionari3, legionari2};
        else if (legionari == null && legionari3 == null) legionaris = new Enemic[]{legionari4, legionari2};
        else if (legionari == null && legionari2 == null) legionaris = new Enemic[]{legionari3, legionari4};
        else if (legionari4 == null) legionaris = new Enemic[]{legionari, legionari2, legionari3};
        else if (legionari3 == null) legionaris = new Enemic[]{legionari, legionari2, legionari4};
        else if (legionari2 == null) legionaris = new Enemic[]{legionari, legionari4, legionari3};
        else if (legionari == null) legionaris = new Enemic[]{legionari4, legionari2, legionari3};
        if (buscarEnemics()) {
            return false;
        }
        System.out.println("Felicitats has trepitjat a tots els legionaris");
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Bones Asterix has d'agafar la poció per podre vence als legionaris rivals. ");
        Joc joc = new Joc();
    }

    /**
     * Serveix per posarli unes coordenades als Elements del joc.
     *
     * @param e Tipus Element
     */
    private static void setCoordenades(Element e) {
        if (e instanceof Jugador) {
            do {
                c = posicioAleatoria();
            } while (bosc.getCelula(c.getFila(), c.getColumna()) != '·');
            e.setCord(c);
        } else if (e instanceof Pocio) {
            do {
                c = posicioAleatoria();
            } while (bosc.getCelula(c.getFila(), c.getColumna()) != '·');
            e.setCord(c);
        } else if (e instanceof Enemic) {
            do {
                c = posicioAleatoria();
            } while (bosc.getCelula(c.getFila(), c.getColumna()) != '·');
            e.setCord(c);
        }
    }

    /**
     * Serveix per moure al enemics
     */
    public static void mouEnemics() {
        Coordenades c = asterix.getCord(), cLegionari, cPocio = pocio.getCord();
        Coordenades dif, difAmunt, difAvall, difDreta, difEsquerra;
        for (Enemic enemic : legionaris) {
            if (enemic != null) {
                //Agafem les coordenades de les diferencias en cada cas de caminar
                cLegionari = enemic.getCord();
                dif = new Coordenades(cLegionari.getFila() - c.getFila(), cLegionari.getColumna() - c.getColumna());
                if (verificarFilaColumna(cLegionari.getFila() - enemic.getVelocitat(), cLegionari.getColumna())) {
                    difAmunt = new Coordenades(cLegionari.getFila() - 1 - c.getFila(), cLegionari.getColumna() - c.getColumna());
                } else {
                    difAmunt = new Coordenades(19, 19);
                }
                if (verificarFilaColumna(cLegionari.getFila() + enemic.getVelocitat(), cLegionari.getColumna())) {
                    difAvall = new Coordenades(cLegionari.getFila() + 1 - c.getFila(), cLegionari.getColumna() - c.getColumna());
                } else {
                    difAvall = new Coordenades(19, 19);
                }
                if (verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() + enemic.getVelocitat())) {
                    difDreta = new Coordenades(cLegionari.getFila() - c.getFila(), cLegionari.getColumna() + 1 - c.getColumna());
                } else {
                    difDreta = new Coordenades(19, 19);
                }
                if (verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() - enemic.getVelocitat())) {
                    difEsquerra = new Coordenades(cLegionari.getFila() - c.getFila(), cLegionari.getColumna() - 1 - c.getColumna());
                } else {
                    difEsquerra = new Coordenades(19, 19);
                }
                //Fem que els valors siguin positius
                if (dif.getFila() < 0) {
                    dif.setFila((dif.getFila()) - dif.getFila() * 2);
                }
                if (difAmunt.getFila() < 0) {
                    difAmunt.setFila((difAmunt.getFila()) - difAmunt.getFila() * 2);
                }
                if (difAvall.getFila() < 0) {
                    difAvall.setFila((difAvall.getFila()) - difAvall.getFila() * 2);
                }
                if (difDreta.getFila() < 0) {
                    difDreta.setFila((difDreta.getFila()) - difDreta.getFila() * 2);
                }
                if (difEsquerra.getFila() < 0) {
                    difEsquerra.setFila((difEsquerra.getFila()) - difEsquerra.getFila() * 2);
                }
                if (dif.getColumna() < 0) {
                    dif.setColumna((dif.getColumna()) - dif.getColumna() * 2);
                }
                if (difAmunt.getColumna() < 0) {
                    difAmunt.setColumna((difAmunt.getColumna()) - difAmunt.getColumna() * 2);
                }
                if (difAvall.getColumna() < 0) {
                    difAvall.setColumna((difAvall.getColumna()) - difAvall.getColumna() * 2);
                }
                if (difDreta.getColumna() < 0) {
                    difDreta.setColumna((difDreta.getColumna()) - difDreta.getColumna() * 2);
                }
                if (difEsquerra.getColumna() < 0) {
                    difEsquerra.setColumna((difEsquerra.getColumna()) - difEsquerra.getColumna() * 2);
                }
                //Comprovem que aquesta dirrecio sigui en sentit del jugador, si el cami al que anem esta dintre
                // del taullel, que en mig del cami no hi hagi un legionari, comprovem si el jugador te energia,
                // si la te els legionaris fugiran si no la te s'aproparan, fiquem en les antigues coordenades espai
                // o la pocio i en les noves el legionari.
                /*
                System.out.println(dif);
                System.out.println(difAmunt);
                System.out.println(difAvall);
                System.out.println(difDreta);
                System.out.println(difEsquerra);
                 */
                if (((difAmunt.getFila() < dif.getFila()) || (difAmunt.getColumna() < dif.getColumna())) && (verificarFilaColumna(cLegionari.getFila() + enemic.getVelocitat(), cLegionari.getColumna()))) {
                    if (bosc.getCelula(cLegionari.getFila() + enemic.getVelocitat(), cLegionari.getColumna()) != enemic.getNom()) {
                        if (asterix.getEnergia() == 0) {
                            if (cPocio.equals(cLegionari)) {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                            } else {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                            }
                            enemic.mouW();
                        } else {
                            if ((verificarFilaColumna(cLegionari.getFila() - enemic.getVelocitat(), cLegionari.getColumna()))) {
                                if (cPocio.equals(cLegionari)) {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                                } else {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                                }
                                enemic.mouS();
                            }
                        }
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    }
                } else if ((((difAvall.getFila() < dif.getFila())) || ((difAvall.getColumna() < dif.getColumna()))) && (verificarFilaColumna(cLegionari.getFila() - enemic.getVelocitat(), cLegionari.getColumna()))) {
                    if (bosc.getCelula(cLegionari.getFila() - enemic.getVelocitat(), cLegionari.getColumna()) != enemic.getNom()) {
                        if (asterix.getEnergia() == 0) {
                            if (cPocio.equals(cLegionari)) {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                            } else {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                            }
                            enemic.mouS();
                        } else {
                            if ((verificarFilaColumna(cLegionari.getFila() - enemic.getVelocitat(), cLegionari.getColumna()))) {
                                if (cPocio.equals(cLegionari)) {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                                } else {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                                }
                                enemic.mouW();
                            }
                        }
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    }
                } else if ((((difDreta.getFila() < dif.getFila())) || ((difDreta.getColumna() < dif.getColumna()))) && (verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() + enemic.getVelocitat()))) {
                    if (bosc.getCelula(cLegionari.getFila(), cLegionari.getColumna() + enemic.getVelocitat()) != enemic.getNom()) {
                        if (asterix.getEnergia() == 0) {
                            if (cPocio.equals(cLegionari)) {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                            } else {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                            }
                            enemic.mouD();
                        } else {
                            if (verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() - enemic.getVelocitat())) {
                                if (cPocio.equals(cLegionari)) {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                                } else {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                                }
                                enemic.mouA();
                            }
                            cLegionari = enemic.getCord();
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                        }
                    }
                } else if ((((difEsquerra.getFila() < dif.getFila())) || ((difEsquerra.getColumna() < dif.getColumna()))) && (verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() - enemic.getVelocitat()))) {
                    if (bosc.getCelula(cLegionari.getFila(), cLegionari.getColumna() - enemic.getVelocitat()) != enemic.getNom()) {
                        System.out.println(bosc.getCelula(cLegionari.getFila(), cLegionari.getColumna() - enemic.getVelocitat()) != enemic.getNom());
                        if (asterix.getEnergia() == 0) {
                            if (cPocio.equals(cLegionari)) {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                            } else {
                                bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                            }
                            enemic.mouA();
                        } else {
                            if (verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() + enemic.getVelocitat())) {
                                if (cPocio.equals(cLegionari)) {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                                } else {
                                    bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                                }
                                enemic.mouD();
                            }
                        }
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    }
                } else {
                    if (!verificarFilaColumna(cLegionari.getFila() + 1, cLegionari.getColumna())) {
                        if (cPocio.equals(cLegionari)) {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                        } else {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                        }
                        enemic.mouW();
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    } else if (!verificarFilaColumna(cLegionari.getFila() - 1, cLegionari.getColumna())) {
                        if (cPocio.equals(cLegionari)) {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                        } else {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                        }
                        enemic.mouS();
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    } else if (!verificarFilaColumna(cLegionari.getFila(), cLegionari.getColumna() + 1)) {
                        if (cPocio.equals(cLegionari)) {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                        } else {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                        }
                        enemic.mouA();
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    } else if (!verificarFilaColumna(cLegionari.getFila() + 1, cLegionari.getColumna())) {
                        if (cPocio.equals(cLegionari)) {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), pocio.getNom());
                        } else {
                            bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), '·');
                        }
                        enemic.mouD();
                        cLegionari = enemic.getCord();
                        bosc.setCelula(cLegionari.getFila(), cLegionari.getColumna(), enemic.getNom());
                    }
                }
            }
        }
    }

    /**
     * Serveix per controlar el inpput del jugador per moure, el moviment i si trepitja la pocio o al legionari.
     *
     * @return true si ha acabat amb tots els legionaris i false si encara continua el joc.
     */
    public static boolean mouJugador() {
        //Introducio de moviment
        Coordenades c = asterix.getCord(), cPocio = pocio.getCord(), cLegionari;
        do {
            System.out.print("Et vols moure cap amunt(W) cap avall(S) cap a la esquerra(A) o cap a la dreta(D): ");
            resposta = (scan.next().trim()).toUpperCase(Locale.ROOT);
            scan.nextLine();
            if (resposta.equals("W")) {
                if (!verificarFilaColumna(c.getFila() - asterix.getVelocitat(), c.getColumna())) {
                    System.out.println("Asterix no es pot moure cap amunt");
                    resposta = "X";
                }
            } else if (resposta.equals("A")) {
                if (!verificarFilaColumna(c.getFila(), c.getColumna() - asterix.getVelocitat())) {
                    System.out.println("Asterix no es pot moure cap a la esquerra");
                    resposta = "X";
                }
            } else if (resposta.equals("S")) {
                if (!verificarFilaColumna(c.getFila() + asterix.getVelocitat(), c.getColumna())) {
                    System.out.println("Asterix no es pot moure cap avall");
                    resposta = "X";
                }
            } else if (resposta.equals("D")) {
                if (!verificarFilaColumna(c.getFila(), c.getColumna() + asterix.getVelocitat())) {
                    System.out.println("Asterix no es pot moure cap a la dreta");
                    resposta = "X";
                }
            }
        } while (!resposta.equals("W") && !resposta.equals("A") && !resposta.equals("S") && !resposta.equals("D"));
        bosc.setCelula(c.getFila(), c.getColumna(), '·');
        //funcions per cada moviment
        switch (resposta) {
            case "W":
                asterix.mouW();
                c = asterix.getCord();
                bosc.setCelula(c.getFila(), c.getColumna(), asterix.getNom());
                break;
            case "A":
                asterix.mouA();
                c = asterix.getCord();
                bosc.setCelula(c.getFila(), c.getColumna(), asterix.getNom());
                break;
            case "S":
                asterix.mouS();
                c = asterix.getCord();
                bosc.setCelula(c.getFila(), c.getColumna(), asterix.getNom());
                break;
            case "D":
                asterix.mouD();
                c = asterix.getCord();
                bosc.setCelula(c.getFila(), c.getColumna(), asterix.getNom());
                break;
        }
        //Trepitjar Pocio
        if (c.getFila() == cPocio.getFila() && c.getColumna() == cPocio.getColumna()) {
            asterix.prenPocio();
            do {
                c = posicioAleatoria();
            } while (bosc.getCelula(c.getFila(), c.getColumna()) != '·');
            pocio.setCord(c);
            bosc.setCelula(c.getFila(), c.getColumna(), pocio.getNom());
        }
        return false;
    }

    /**
     * Busca per tot el tauler alguna celula amb el nom d'un legionari.
     *
     * @return Tipus boolean, si troba un legionari retorna true en el cas que no ho trobi retorna false.
     */
    public static boolean buscarEnemics() {
        if (legionari == null && legionari2 == null && legionari3 == null & legionari4 == null) {
            return false;
        }
        return true;
    }

    /**
     * Serveix per donar unes coordenades aleatories dintre del tauler
     *
     * @return Tipus Coordenades
     */
    public static Coordenades posicioAleatoria() {
        return new Coordenades(0, COLUMNES, 0, FILES);
    }

    /**
     * Serveix per comprobar que la celula que busques esta dintre dels limits del tauler.
     *
     * @param files    Tipus int, La fila demanada per l'usuari.
     * @param columnes Tipus int, La columna demanada per l'usuari.
     * @return Tipus boolean, retorna true si la posicio que et donem esta dintre dels limits del tauler, false si no ho esta.
     */
    public static boolean verificarFilaColumna(int files, int columnes) {
        boolean verificacio = true;
        if (files < 0 || files > (FILES - 1) || columnes < 0 || columnes > (COLUMNES - 1)) {
            verificacio = false;
        }
        return verificacio;
    }
}