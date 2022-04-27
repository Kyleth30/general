package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class KeyboardControler implements KeyListener {

    public boolean moveUp,moveDown,moveLeft,moveRight;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {

            moveUp = true;
        }
        if (code == KeyEvent.VK_S) {

            moveDown = true;
        }
        if (code == KeyEvent.VK_A) {

            moveLeft = true;
        }
        if (code == KeyEvent.VK_D) {

            moveRight = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {

            moveUp = false;
        }
        if (code == KeyEvent.VK_S) {

            moveDown = false;
        }
        if (code == KeyEvent.VK_A) {

            moveLeft = false;
        }
        if (code == KeyEvent.VK_D) {

            moveRight = false;
        }
    }
}
