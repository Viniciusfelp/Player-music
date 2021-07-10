import java.util.ArrayList;

public class playList {
    private ArrayList<music>playlist;

    public playList(){ //construtor
        this.playlist= new ArrayList<>();
    }
    public void addMusic(music musica){ //adicionar música no arraylist
        playlist.add(musica);
        System.out.println("Musica Adicionada com sucesso!");
    }
    public void removeMusic (int i){ //remover música do arrayList
         playlist.remove(i);
        System.out.println("Musica removida com sucesso!");

    }
}

