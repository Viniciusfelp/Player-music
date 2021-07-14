package Metodos;

import Classes.listaMusica;
import Classes.playList;

public class adicionar extends lock implements Runnable {
    private playList listaDeReproducao ;
    private listaMusica lista ;
    private int indiceMusicaEscolhida;

    public adicionar(int indice,playList listaDeReproducao,listaMusica lista){ //Passei a playList e a lista de Música como parâmetro do construtor
        this.indiceMusicaEscolhida = indice;                                   //Dessa forma a playList que ta instanciada no Main não reseta
        this.listaDeReproducao=listaDeReproducao;                              //toda vez que esse método aqui for chamado
        this.lista=lista;
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupado()) {
                getCanWrite().await();
            }
            listaDeReproducao.addMusic(lista.getMusica(indiceMusicaEscolhida));
            setOcupado(true);
            getCanRead().signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}
