package Metodos;

import Classes.playList;

public class remover extends lock implements Runnable {
    private playList listaDeReproducao;
    private int indiceMusicaEscolhida;

    public remover(int indice,playList listaDeReproducao) {//Passei a playList como parâmetro do construtor
        this.indiceMusicaEscolhida = indice;               //Dessa forma a playList que ta instanciada no Main não reseta
        this.listaDeReproducao=listaDeReproducao;           //toda vez que esse método aqui for chamado
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupado()) getCanWrite().await();
            listaDeReproducao.removeMusic(indiceMusicaEscolhida);
            setOcupado(true);
            getCanRead().signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}