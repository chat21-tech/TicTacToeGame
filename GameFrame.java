import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private boolean gameOver;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        lblStatus = new JLabel("Giliran kamu! (X)", SwingConstants.CENTER);
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));
        boardPanel.setBackground(new Color(30, 30, 30));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 36));
            buttons[i].setBackground(new Color(50, 50, 50));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(new Color(30, 30, 30));
        centerPanel.add(lblStatus, BorderLayout.NORTH);
        centerPanel.add(boardPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton btnBack = new JButton("Kembali ke Menu");
        btnBack.setBackground(new Color(255, 80, 80));
        btnBack.setForeground(Color.BLACK);
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnBack.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            dispose();
        });

        mainPanel.add(btnBack, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void handlePlayerMove(int index) {
        if (gameOver) return;

        boolean moved = gameLogic.makeMove(index, 'X');
        if (!moved) {
            JOptionPane.showMessageDialog(this, "Sel sudah terisi! Pilih yang lain.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        buttons[index].setText("X");
        buttons[index].setForeground(new Color(0, 200, 255));

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Komputer sedang berpikir...");

        int compIndex = gameLogic.computerMove();
        gameLogic.makeMove(compIndex, 'O');
        buttons[compIndex].setText("O");
        buttons[compIndex].setForeground(new Color(255, 80, 80));

        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Giliran kamu! (X)");
    }

    private void finishGame(String result) {
        gameOver = true;
        playerService.updateStatistics(currentPlayer, result);

        String message;
        if (result.equals("WIN")) {
            message = "Selamat! Kamu MENANG! +10 poin";
            lblStatus.setText("Kamu Menang! 🎉");
        } else if (result.equals("LOSE")) {
            message = "Kamu KALAH! Coba lagi!";
            lblStatus.setText("Kamu Kalah! 😢");
        } else {
            message = "SERI! +3 poin";
            lblStatus.setText("Seri! 🤝");
        }

        JOptionPane.showMessageDialog(this, message, "Game Selesai", JOptionPane.INFORMATION_MESSAGE);

        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        dispose();
    }
}