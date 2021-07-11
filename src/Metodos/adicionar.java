package Metodos;

import Classes.listaMusica;
import Classes.playList;

public class adicionar implements Runnable {
    playList listaDeReproducao = new playList();
    listaMusica lista = new listaMusica();
    private final int indiceMusicaEscolhida;
    public adicionar(int indice){
        this.indiceMusicaEscolhida = indice;
    }
    public void run() {
        listaDeReproducao.addMusic(lista.getMusica(indiceMusicaEscolhida));
    }
}
