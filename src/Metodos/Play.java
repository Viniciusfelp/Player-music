package Metodos;

import Classes.playList;

public class Play extends Thread{
    private final playList listaDeReproducao;
    private final int indiceMusicaEscolhida;

    public Play(int indice, playList listaDeReproducao) {
        this.indiceMusicaEscolhida = indice;
        this.listaDeReproducao = listaDeReproducao;
    }
    public void run() {
        try {
            sleep(listaDeReproducao.play(listaDeReproducao.getMusicaAtual()));
            new avancar(listaDeReproducao.getMusicaAtual(), listaDeReproducao);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
