package Metodos;

import Classes.playList;

public class Play extends Thread{
    private final playList listaDeReproducao;
    public Play(playList listaDeReproducao) {
        this.listaDeReproducao = listaDeReproducao;
    }
    public void run() {
        try {
            sleep(listaDeReproducao.play(listaDeReproducao.getMusicaAtual()));
            new avancar(listaDeReproducao);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
