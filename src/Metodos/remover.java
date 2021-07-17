package Metodos;

import Classes.playList;

public class remover extends lock implements Runnable {
    private final playList listaDeReproducao;
    private final int indiceMusicaEscolhida;

    public remover(int indice,playList listaDeReproducao) {//Passei a playList como parâmetro do construtor
        this.indiceMusicaEscolhida = indice;               //Dessa forma a playList que ta instanciada no Main não reseta
        this.listaDeReproducao = listaDeReproducao;        //toda vez que esse método aqui for chamado
    }
    public void run() {
        getAcessLock().lock();//Bloqueia esse objeto
        try {
            while (isOcupado()) getCondition().await();//Espera até que a thread possa escrever na playlist
            setOcupado(true);//Indica que outra thread nao pode usar a funcao
            listaDeReproducao.removeMusic(indiceMusicaEscolhida);
            setOcupado(false);
            getCondition().signalAll();//sinaliza para a thread que está esperando
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Desbloqueia esse Objeto
            getAcessLock().unlock();
        }
    }
}