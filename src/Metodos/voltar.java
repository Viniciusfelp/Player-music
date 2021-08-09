package Metodos;

import Classes.playList;

public class voltar implements Runnable{
    private final playList listaDeReproducao;
    private final int indiceMusicaEscolhida;
    public voltar (int indice,playList listaDeReproducao){
        this.listaDeReproducao = listaDeReproducao;
        this.indiceMusicaEscolhida = indice;
    }

    @Override
    public void run() {
        new play(indiceMusicaEscolhida-1, listaDeReproducao);
    }
}