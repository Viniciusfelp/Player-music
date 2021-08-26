package Metodos;

import Classes.playList;

public class Play extends Thread{
    private playList listaDeReproducao;

    public Play(playList listaDeReproducao) {
        this.listaDeReproducao = listaDeReproducao;

    }
    public void run() {
        try {
            sleep(listaDeReproducao.buscaDuracao(listaDeReproducao.getMusicaAtual()));
            if (listaDeReproducao.getPlaylist().size()>listaDeReproducao.getMusicaAtual()+1){
                new avancar(listaDeReproducao).run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
