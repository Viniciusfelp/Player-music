package Classes;

public class music {
    private String nomeMusica;
    private String nomeArtista;
    private int duracaoMusica;

    public music(String nMsica, String nArtista, int dMusica){
        this.nomeMusica = nMsica;
        this.nomeArtista = nArtista;
        this.duracaoMusica = dMusica;
    }


    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public int getDuracaoMusica() {
        return duracaoMusica;
    }

    public void setDuracaoMusica(int duracaoMusica) {
        this.duracaoMusica = duracaoMusica;
    }

    @Override
    public String toString(){
        return this.nomeMusica;
    }
}

