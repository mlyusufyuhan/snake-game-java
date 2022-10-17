import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.Jpanel;


public class GamePanel extends Jpanel implements ActionListener {

    static final int SCREEN_WIDTH = 600 ;
    static final int SCREEN_HEIGHT = 600 ;
    static final int UNIT_SIZE = 25 ;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE ;
    final int x[] = new int [GAME_UNITS];
    final int y[] = new int [GAME_UNITS];
    int bodyParts = 6;
    int orangesEaten ;
    int orangeX ;
    int orangeY ;
    char Direction = 'R';
    boolean running = false ;
    Timer timer;
    Random random;




    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }
    
    public void startGame(){
        newOrange();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start(); 
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){
        if(running){
           
        for (int i=0 ;i< SCREEN_HEIGHT/UNIT_SIZE;I++) {
            g.drawline(i*UNIT_SIZE,0, I*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawline(0,i*UNIT_SIZE,SCREEN_WIDTH, i*UNIT_SIZE);

        }
        
        g.setColor(Color.red);
        g.fillOval(orangeX,orangeY,UNIT_SIZE,UNIT_SIZE);

        for (int i=0 ; i< bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green) ;
                g.fillrect(X[i],Y[I],UNIT_SIZE,UNIT_SIZE);
            }
            else {
                g.setColor(new Color(45,180,0));
                g.fillrect(X[i],Y[I],UNIT_SIZE,UNIT_SIZE);
            }
        }
    }
    else{
        gameOver(g);
    }

    }

    public void newOrange(){
        orangeX = random.nextIin((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE ;
        orangeY = random.nextIin((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE ;        
    }

    /**
     * 
     */
    public void move(){
        for (int i = bodyParts;i>0;i--) {
            x[i] = [x-1];
            y[i] = [y-1];

        }

        switch(direction) {
            case 'U':
            Y[0] = y[0] - UNIT_SIZE;
            break;

            case 'D':
            Y[0] = y[0] + UNIT_SIZE;
            break;

            case 'L':
            X[0] = X[0] - UNIT_SIZE;
            break;

            case 'R':
            X[0] = X[0] + UNIT_SIZE;
            break;
        }



    }

    public void checkOrange(){
        if((X[0] == orangeX) && (Y[0] == orangeY)){
            bodyParts++;
            orangesEaten++;
            orange();
        }

    }

    public void checkImpact(){
        for(int i=bodyParts ; i> 0 ; i-- ){
            if ((X[0]==X[i]) && Y[0] == Y[i]){
            running = false ; 
            }
        }
        if (X[0] < 0) {
            running = false ; 
        }

        if (x[0] > SCREEN_WIDTH){
            running = false ;
        }
        if (Y[0] <  0) {
            running = false ;
        }
        if (Y[0] > SCREEN_WIDTH) {
            running = false ;
        }
        if(!running){
            timer.stop();
        }

    }

    public void gameOver(graphics g){
        //score
        g.setColor(Color.red);
        g.setFont(new Font ("Ink Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:" +orangesEaten,(SCREEN_WIDTH- metrics1.stringWidth("Score:" +orangesEaten))/2,SCREEN_HEIGHT/2);
        //Gameover text
        g.setColor(Color.red);
        g.setFont(new Font ("Ink Free",Font.BOLD,40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score:" +orangesEaten,(SCREEN_WIDTH- metrics2.stringWidth("Score:" +orangesEaten))/2,SCREEN_HEIGHT/2);

    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(running){
            move();
            checkOrange();
            checkImpact();
        }
        repaint();
        
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void KeyPressed (KeyEvent e){

        }
    } 

}