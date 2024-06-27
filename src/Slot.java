import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Slot {


    int boardWidth = 1200;
    int boardHeight = 400;

    ///// mesto za sličice

    JFrame frame = new JFrame("Fruity Slot");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton spinButton = new JButton("SPIN TO WIN!");

    JButton[][] board = new JButton[1][3];

    int currentMoney = 20;
    //boolean SPIN = true;
    boolean GAMEOVER = false;
    int count = 0;

    Slot() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Current Cash (" + currentMoney + "$)");
        textLabel.setOpaque(true);

        ///spin button
        spinButton.setFocusable(false);
        buttonPanel.add(spinButton);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.PAGE_START);

        boardPanel.setLayout(new GridLayout(1, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 1; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;

                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

            }
        }

        spinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gatekeeper
                if (GAMEOVER == true) {
                    return;
                }

                //Plaćanje
                Score();

                //RNG
                RNG();

                //Provera+dobitak
                checkWinner();
            }
        });
    }

    void checkWinner() {
        if (board[0][0].getIcon().toString().equals(board[0][1].getIcon().toString()) &&
                board[0][1].getIcon().toString().equals(board[0][2].getIcon().toString())) {
            currentMoney = currentMoney + 20;
            textLabel.setText("SCORE!!! (" + currentMoney + "$) +20$");

            for (int i = 0; i < 3; i++) {
                board[0][i].setBackground(Color.gray);
                //board[0][i].setForeground(Color.yellow);
                textLabel.setBackground(Color.orange);
                textLabel.setForeground(Color.white);
            }

        }
        if (GAMEOVER == true) {
            for (int i = 0; i < 3; i++) {
                board[0][i].setBackground(Color.gray);
                //board[0][i].setForeground(Color.white);
                textLabel.setBackground(Color.black);
                textLabel.setForeground(Color.white);
            }
        }
    }

    void Score() {
        // resetuje boje
        for (int i = 0; i < 3; i++) {
            board[0][i].setBackground(Color.darkGray);
            //board[0][i].setForeground(Color.white);
            textLabel.setBackground(Color.darkGray);
            textLabel.setForeground(Color.white);
        }
        currentMoney = currentMoney - 1;
        textLabel.setText("Current Cash (" + currentMoney + "$)");
        if (0 >= currentMoney) {
            textLabel.setText("GAME OVER! (" + currentMoney + "$)");
            GAMEOVER = true;
        }
    }

    void RNG() {

        for (int i = 0; i < 3; i++) {
            Random rando = new Random();
            int broj;
            broj = rando.nextInt(0, 3);
            //String kombinacija = String.valueOf(broj);
            //board[0][i].setText(kombinacija);

            if (broj == 0) {
                Icon icon = new ImageIcon(getClass().getResource("0.png"));
                board[0][i].setIcon(icon);
            }
            if (broj == 1) {
                Icon icon = new ImageIcon(getClass().getResource("1.png"));
                board[0][i].setIcon(icon);
            }
            if (broj == 2) {
                Icon icon = new ImageIcon(getClass().getResource("2.png"));
                board[0][i].setIcon(icon);
            }
        }
    }
}