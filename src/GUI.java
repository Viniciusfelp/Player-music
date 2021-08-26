
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.listaMusica;
import Classes.music;
import Classes.playList;
import Metodos.*;

public class GUI implements ActionListener {

   //Auxiliares
   int indexOfselectedMusicToAdd=-1;
   int indexOfselectedMusicToRemove=-1;
   music getSelectedMusicToAdd=null;
   music getSelectedMusicToRemove=null;
   playList playerDeMusicas = new playList();//instância do player de músicas que recebe os métodos(add,remove,skip,etc)
   listaMusica listaDeMusicas = new listaMusica();
   //DefaultListModel playlistModel = new DefaultListModel();
   //JList actualPlaylist = new JList(playlistModel);

   boolean pauseFlag = false;
   boolean isAvancarAfterRemove=false;
   boolean isRandom = false;

   //Threads
   Thread play_thread = new Thread();
   Thread avancar_thread = new Thread();
   Thread voltar_thread = new Thread();
   Thread playTimer = new Thread();

   ///Frames
   JFrame guiFrame = new JFrame();

   //Panels
   JPanel eastPanel = new JPanel();
   JPanel westPanel = new JPanel();
   JPanel centerPanel = new JPanel();
   JPanel progressBarPanel = new JPanel();
   JPanel actionBtnsPanel = new JPanel();

   //Progress Bar
   JProgressBar progressBar = new JProgressBar();

   //Scroller da Lista de musicas
   JScrollPane listScroller;
   JScrollPane playlistScroller;

   //Model da lista de musicas
   DefaultListModel listaDeMusicasModel = new DefaultListModel();
   JList musicList = new JList();
   JList finalMusicList = new JList();

   //Model da playlist
   DefaultListModel playlistModel = new DefaultListModel();
   JList actualPlaylist = new JList(playlistModel);

   //Botão Add music
   JButton add = new JButton("Add Music");

   //Botão Modo de reprodução
   JButton reproductionModeBtn = new JButton("Normal/Random");

   //Botão Nova Music
   JButton newMusic = new JButton("New Music");

   //Label Infos da Música
   JLabel nomeLabel = new JLabel();
   JLabel autorLabel = new JLabel();
   JLabel duracaoLabel = new JLabel();

   //Botão Remove music
   JButton rmv = new JButton("Remove Music");

   JButton play_btn = new JButton("Play");
   JButton pause_btn = new JButton("Pause");
   JButton step_forward = new JButton("Avançar");
   JButton step_backward = new JButton("Voltar");

   //New Music component
   JFrame newMusicFrame = new JFrame("New Music:");
   JPanel newMusicPanel = new JPanel();
   JLabel musicName =new JLabel();
   JLabel authorName =new JLabel();
   JTextField musicNameText = new JTextField();
   JTextField authorNameText = new JTextField();
   JButton addNewMusic = new JButton("Ok");

   public GUI() {
      //Configs da guiFrame
      guiFrame.setTitle("Malvadeza Music");
      guiFrame.setSize(800, 300);
      guiFrame.setLocation(500, 300);
      guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      guiFrame.setLayout(new BorderLayout(10, 10));

      //Config westPanel
      westPanel.setLayout(new BoxLayout(this.westPanel, BoxLayout.PAGE_AXIS));
      westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      //Botão AddMusic
      westPanel.add(add);
      add.addActionListener(this);

      //Config Lista de Musicas
      //musicList = new JList(listaDeMusicas.lista.toArray());
      listaDeMusicas.lista.forEach(music->{
         listaDeMusicasModel.addElement(music);
      });
      musicList = new JList(listaDeMusicasModel);
      musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      finalMusicList = musicList;
      finalMusicList.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            getSelectedMusicToAdd= (music) finalMusicList.getSelectedValue();
            indexOfselectedMusicToAdd= finalMusicList.getSelectedIndex();
            // selectedMusicToAdd= (music) finalMusicList.getSelectedValue();

         }
      });
      listScroller = new JScrollPane(finalMusicList);
      listScroller.setPreferredSize(new Dimension(200, 80));
      westPanel.add(this.listScroller, finalMusicList);

      //Botão Add nova música ao banco de músicas
      westPanel.add(newMusic);
      newMusic.addActionListener(this);


      //Config eastPanel
      eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
      eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      //Botão RemoveMusic
      eastPanel.add(rmv);
      rmv.addActionListener(this);

      //Config Playlist atual
      JList finalPlaylist = actualPlaylist;

      finalPlaylist.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            getSelectedMusicToRemove= (music) actualPlaylist.getSelectedValue();
            indexOfselectedMusicToRemove= actualPlaylist.getSelectedIndex();
            //System.out.println(finalActualPlaylist.getSelectedValue());
         }
      });
      playlistScroller = new JScrollPane(finalPlaylist);
      playlistScroller.setPreferredSize(new Dimension(200, 80));
      eastPanel.add(playlistScroller, finalPlaylist);

      //Config centerPanel
      centerPanel.setLayout(new BorderLayout());

      //Config progressBar
      progressBar.setStringPainted(true);
      progressBar.setMaximum(30000);
      progressBar.setValue(0);
      //progressBar.setSize(200, 25);
      progressBarPanel.add(progressBar);

      //Botão Voltar
      actionBtnsPanel.add(step_backward);
      step_backward.addActionListener(this);

      //Botão Play
      actionBtnsPanel.add(play_btn);
      play_btn.addActionListener(this);

      //Botão Pause
      actionBtnsPanel.add(pause_btn);
      pause_btn.addActionListener(this);

      //Botão Avançar
      actionBtnsPanel.add(step_forward);
      step_forward.addActionListener(this);

      actionBtnsPanel.add(reproductionModeBtn);
      reproductionModeBtn.addActionListener(this);

      //Configs do popup New Music
      musicName.setText("Music Name:");
      authorName.setText("Author Name:");
      newMusicFrame.setSize(300,250);
      newMusicFrame.setLocation(800,300);
      newMusicPanel.setLayout(new BoxLayout(newMusicPanel,BoxLayout.PAGE_AXIS));
      newMusicPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
      musicNameText.setColumns(3);
      authorNameText.setColumns(3);
      newMusicPanel.add(musicName);
      newMusicPanel.add(musicNameText);
      newMusicPanel.add(authorName);
      newMusicPanel.add(authorNameText);
      addNewMusic.addActionListener(this);
      newMusicPanel.add(addNewMusic);
      newMusicFrame.add(newMusicPanel);


      //Config Labels
      JPanel labelsPanel = new JPanel();
      labelsPanel.setLayout(new GridLayout(3,1));
      nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
      autorLabel.setHorizontalAlignment(SwingConstants.CENTER);
      duracaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
      labelsPanel.add(nomeLabel);
      labelsPanel.add(autorLabel);
      labelsPanel.add(duracaoLabel);


      //Configs finais
      centerPanel.add(progressBarPanel, BorderLayout.NORTH);
      centerPanel.add(actionBtnsPanel, BorderLayout.CENTER);
      centerPanel.add(labelsPanel,BorderLayout.SOUTH);
      guiFrame.add(centerPanel, BorderLayout.CENTER);
      guiFrame.add(eastPanel, BorderLayout.EAST);
      guiFrame.add(westPanel, BorderLayout.WEST);
      guiFrame.setVisible(true);

   }
   public void addAtPlaylist(music musicAdded){
      actualPlaylist.setModel(playlistModel);
      playlistModel.addElement(musicAdded);
   }
   public void addAtMusicList (music musicAdded){
      musicList.setModel(listaDeMusicasModel);
      listaDeMusicasModel.addElement(musicAdded);
   }
   public void removeOfPlaylist(music musicRemoved){
      actualPlaylist.setModel(playlistModel);
      playlistModel.removeElement(musicRemoved);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Thread add_thread = new Thread();
      Thread rmv_thread = new Thread();
      switch (e.getActionCommand()){
         case "Ok": //Botão do Pop-up que salva a nova música na playlist
            music musicToBeAdded = new music(musicNameText.getText(),authorNameText.getText(),30000);
            listaDeMusicas.lista.add(musicToBeAdded);
            addAtMusicList(musicToBeAdded);
            newMusicFrame.setVisible(false);
            break;
         case "New Music": //Botão que abre o pop-up para adicionar novas músicas
            newMusicFrame.setVisible(true);
            break;
         case"Add Music":
            if (indexOfselectedMusicToAdd>=0){
               addAtPlaylist(getSelectedMusicToAdd);
               add_thread = new Thread(new adicionar(indexOfselectedMusicToAdd,playerDeMusicas,listaDeMusicas));
               add_thread.start();
            }
            break;
         case"Remove Music":
            if (indexOfselectedMusicToRemove>=0){
               rmv_thread=new Thread(new remover(indexOfselectedMusicToRemove));
               rmv_thread.start();
               removeOfPlaylist(getSelectedMusicToRemove);
            }
            break;
         case"Play":  //Play
            if (pauseFlag){ //Checa estado de Pause
               play_thread.resume();
               playTimer.resume();
               pauseFlag = false;
            }else{
               if (!playerDeMusicas.getPlaylist().isEmpty()&&!play_thread.isAlive()){ //Checa se não há outra música sendo tocada ou playlist vazia
                  progressBar.setValue(0);
                  play_thread = new Thread(new Play(playerDeMusicas));
                  play_thread.start();
                  playTimer = new Thread(new Timer());
                  playTimer.start();
               }
            }
            break;
         case"Pause": //Pause
            if (play_thread.isAlive()){
               pauseFlag=true;
               play_thread.suspend();
               playTimer.suspend();
            }
            break;
         case"Voltar": //Voltar
            if (playerDeMusicas.getMusicaAtual()>0){ //Checa se está na primeira posição da playlist
               play_thread.stop();
               playTimer.stop();
               voltar_thread=new Thread(new voltar(playerDeMusicas));
               playTimer = new Thread(new Timer());
               playTimer.start();
               voltar_thread.start();
            }
            break;
         case"Avançar": //Avançar
            if (playerDeMusicas.getMusicaAtual()+1<playerDeMusicas.getPlaylist().size()){ //Checaq se está na última posição da playlist
               play_thread.stop();
               playTimer.stop();
               avancar_thread=new Thread(new avancar(playerDeMusicas,isAvancarAfterRemove));
               playTimer = new Thread(new Timer());
               playTimer.start();
               avancar_thread.start();
            }
            break;
         case "Normal/Random":
            //AQUI VAI A LÓGICA DE ALTERAR O MODO DE REPRODUÇÃO ENTRE RANDOM E NORMAL
            if(playerDeMusicas.isRandom()){
               playerDeMusicas.setRandom(false); //SEMPRE QUE O BOTÃO É CLICADO ELE CAI EM UM DOS SEGUINTES CONDICIONAIS E INVERTE O VALOR POIS O MODO FOI ALTERADO
            }else{
               playerDeMusicas.setRandom(true);
            }
            break;
      }


   }
   //Thread Play
   public class Play extends Thread{
      public Play(playList listaDeReproducao) {
         playerDeMusicas = listaDeReproducao;
      }
      public void run() {
         try {
            nomeLabel.setText("Nome: "+playerDeMusicas.getPlaylist().get(playerDeMusicas.getMusicaAtual()).getNomeMusica());
            autorLabel.setText("Artista: "+playerDeMusicas.getPlaylist().get(playerDeMusicas.getMusicaAtual()).getNomeArtista());
            duracaoLabel.setText("Duração(ms): "+Integer.toString(playerDeMusicas.getPlaylist().get(playerDeMusicas.getMusicaAtual()).getDuracaoMusica()));
            sleep(playerDeMusicas.buscaDuracao(playerDeMusicas.getMusicaAtual()));
            if (playerDeMusicas.getPlaylist().size()>playerDeMusicas.getMusicaAtual()+1){
               avancar_thread = new Thread(new avancar(playerDeMusicas,isAvancarAfterRemove));
               avancar_thread.start();
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

   }

   //Thread remover
   public class remover extends lock implements Runnable {
      int selectedMusicIndex;
      public remover (int indexOfSelectedMusicToRemove){
         selectedMusicIndex=indexOfSelectedMusicToRemove;
      }
      public void run() {
         getAcessLock().lock();//Bloqueia esse objeto
         try {
            while (isOcupado()) getCondition().await();//Espera até que a thread possa escrever na playlist
            setOcupado(true);//Indica que outra thread nao pode usar a funcao
            playerDeMusicas.removeMusic(selectedMusicIndex);
            if (playerDeMusicas.getMusicaAtual()==selectedMusicIndex){
               if (playerDeMusicas.getMusicaAtual()+1<playerDeMusicas.getPlaylist().size()+1){
                  isAvancarAfterRemove = true;
                  playTimer.stop();
                  play_thread.stop();
                  avancar_thread=new Thread(new avancar(playerDeMusicas, isAvancarAfterRemove));
                  playTimer=new Thread(new Timer());
                  playTimer.start();
                  avancar_thread.start();
               }else{
                  playTimer.stop();
                  progressBar.setValue(0);
                  play_thread.stop();
                  nomeLabel.setText("Nome: ");
                  autorLabel.setText("Artista: ");
                  duracaoLabel.setText("Duração(ms): ");
               }

            }
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

   //Thread avançar
   public class avancar implements Runnable {
      boolean isAvancarAfterRemoveCurrentMusic;
      public avancar(playList listaDeReproducao,boolean isAvancarAfterRemove){
         isAvancarAfterRemoveCurrentMusic=isAvancarAfterRemove;
         playerDeMusicas = listaDeReproducao;
      }
      @Override
      public void run() {
         System.out.println(isAvancarAfterRemoveCurrentMusic);
         if (isAvancarAfterRemoveCurrentMusic){
            playerDeMusicas.setMusicaAtual(playerDeMusicas.getMusicaAtual());
            isAvancarAfterRemove=false;
         }else {
            playerDeMusicas.setMusicaAtual(playerDeMusicas.getMusicaAtual() + 1);
         }
         playTimer.stop();
         play_thread.stop();
         play_thread=new Thread(new Play(playerDeMusicas));
         playTimer=new Thread(new Timer());
         playTimer.start();
         play_thread.start();
      }
   }
   //Thread voltar
   public class voltar implements Runnable{
      public voltar (playList listaDeReproducao){
         playerDeMusicas= listaDeReproducao;
      }
      @Override
      public void run() {
         playerDeMusicas.setMusicaAtual(playerDeMusicas.getMusicaAtual()-1);
         play_thread=new Thread(new Play(playerDeMusicas));
      }
   }
   //Thread barra de progresso
   public class Timer extends Thread{  //Essa thread serve apenas para simular o andamento da barra de progresso
      public void run(){               //já que a classe que roda o sleep com o timer da musica não consegue
         progressBar.setValue(0);                              //interferir no progressBar.value
         while(progressBar.getValue()<30000&&!avancar_thread.isAlive()&&play_thread.isAlive()){
            try {
               sleep(1000);
               progressBar.setValue(progressBar.getValue()+1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         progressBar.setValue(0);
      }
   }
}







