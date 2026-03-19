package view;
import javax.swing.*;
import java.awt.*;

public class Menu {
    private JFrame menuFrame;

    public Menu() {
        menuFrame = new JFrame("Sudoku Solver");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(52, 66, 36));

        ImageIcon icon = new ImageIcon("dino.png");
        JLabel dino = new JLabel(icon);
        menuPanel.add(dino);
        dino.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnStart = new JButton("Iniciar");
        btnStart.addActionListener(e-> start());
        menuPanel.add(btnStart);
        btnStart.setBackground(new Color(139, 69, 19));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setFont(new Font("Arial", Font.BOLD, 16));
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuFrame.setSize(700, 700);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.add(menuPanel,BorderLayout.CENTER);
        menuFrame.setVisible(true);
    }
    public void start(){
        menuFrame.dispose();
        UIManager.put("OptionPane.background", new Color(35, 35, 19));
        UIManager.put("Panel.background", new Color(35, 35, 19));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        String input = JOptionPane.showInputDialog("Informe o tamanho do tabuleiro (ex: 4 ou 9):");

        if (input != null && !input.isEmpty()) {
            int size = Integer.parseInt(input);
            new GridView(size);
        }
    }
}
