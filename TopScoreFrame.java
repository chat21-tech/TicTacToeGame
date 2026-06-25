import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScoreFrame extends JFrame {
    private PlayerService playerService;

    public TopScoreFrame() {
        playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - Top 5 Scorers");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("🏆 TOP 5 SCORERS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(255, 200, 0));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"Rank", "Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<Player> topPlayers = playerService.getTopFiveScorers();
        for (int i = 0; i < topPlayers.size(); i++) {
            Player p = topPlayers.get(i);
            model.addRow(new Object[]{
                    "#" + (i + 1),
                    p.getUsername(),
                    p.getWins(),
                    p.getLosses(),
                    p.getDraws(),
                    p.getScore()
            });
        }

        JTable table = new JTable(model);
        table.setBackground(new Color(50, 50, 50));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(0, 200, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setSelectionBackground(new Color(0, 150, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        scrollPane.getViewport().setBackground(new Color(50, 50, 50));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnClose = new JButton("Tutup");
        btnClose.setBackground(new Color(255, 80, 80));
        btnClose.setForeground(Color.BLACK);
        btnClose.setFont(new Font("Arial", Font.BOLD, 14));
        btnClose.setFocusPainted(false);
        btnClose.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnClose.addActionListener(e -> dispose());

        mainPanel.add(btnClose, BorderLayout.SOUTH);
        add(mainPanel);
    }
}
