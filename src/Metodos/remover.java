package Metodos;

import Classes.playList;

public class remover implements Runnable{
    playList listaDeReproducao = new playList();
    private final int indiceMusicaEscolhida;
    public remover(int indice){
        this.indiceMusicaEscolhida = indice;
    }
    public void run() {
        listaDeReproducao.removeMusic(indiceMusicaEscolhida);
    }
}
