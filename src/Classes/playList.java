package Classes;

import java.util.ArrayList;
import java.util.Collections;

public class playList {
    private ArrayList<music>playlist;
    ArrayList<music> playlistBackup = new ArrayList<>();
    private int musicaAtual;
    private boolean random;

    public playList(){ //construtor
        this.playlist= new ArrayList<>();
       // this.playlistBackup = playlist;
        this.musicaAtual = 0;
        this.random = false;

    }
    public void addMusic(music musica){ //adicionar música no arraylist
        playlist.add(musica);
        System.out.println(musica.getNomeMusica()+" Adicionada com sucesso!");
    }

    public void removeMusic (int i){ //remover música do arrayList
        if(playlist.isEmpty()){
            System.out.println("A playList está vazia");
        }else if(i<0||i>playlist.size()-1){ //checa se o index passado pra remoção é válido
            System.out.println("Música não encontrada!");
        }else{
            System.out.println(playlist.get(i).getNomeMusica()+" removida com sucesso!");
            playlist.remove(i);
        }
    }
    public int buscaDuracao(int i){
        if(playlist.isEmpty()){
            System.out.println("A playList está vazia");
        }else if(i<0||i>playlist.size()-1){
            System.out.println("Música não encontrada!");
        }else{
            System.out.println(playlist.get(i).getNomeMusica()+"");
        }
        return playlist.get(i).getDuracaoMusica();
    }

    public void getPlaylistString() { //printa a playList atual
        for (int i =0;i<playlist.size();i++){
            System.out.println(i+"-"+playlist.get(i).getNomeMusica());
        }
    }

    public int getMusicaAtual() {
        return musicaAtual;
    }

    public void setMusicaAtual(int musicaAtual) {
        this.musicaAtual = musicaAtual;
    }

    public ArrayList<music> getPlaylist() {
        return this.playlist;
    }

    public void setPlaylistRandom(){
        playlistBackup.clear();
       this.playlist.forEach(music -> {
           playlistBackup.add(music);
       });
      Collections.shuffle(this.playlist);
    }
    public void setPlaylistNormal(){
        this.playlist.clear();
        playlistBackup.forEach(music -> {
            this.playlist.add(music);
        });
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
}

