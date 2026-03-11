package view;
import model.Logic;
import javax.swing.*;
import java.awt.*;

public class GridView {
    private JFrame boardFrame;
    private JTextField[][] fields;
    private Logic board;
    private int size;

    public GridView(int size) {
        this.size = size;
        this.board = new Logic(size);

        boardFrame = new JFrame("Sudoku Solver");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setLayout(new BorderLayout());


        JPanel gridPanel = new JPanel(new GridLayout(size, size));
        gridPanel.setBackground(new Color(35,35,19));
        fields = new JTextField[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new JTextField();
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setBackground(new Color(220, 220, 220));
                fields[i][j].setForeground(Color.BLACK);
                gridPanel.add(fields[i][j]);
                int top = 1, left = 1, bottom = 1, right = 1;
                if (i % 3 == 0) top = 3;
                if (j % 3 == 0) left = 3;
                if (i == size - 1) bottom = 3;
                if (j == size - 1) right = 3;
                fields[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
            }
        }


        JButton btnSolve = new JButton("Resolver");
        btnSolve.setBackground(new Color(139, 69, 19));
        btnSolve.setForeground(Color.WHITE);
        btnSolve.addActionListener(e -> startSolve());

        boardFrame.add(gridPanel, BorderLayout.CENTER);
        boardFrame.add(btnSolve, BorderLayout.SOUTH);
        boardFrame.setSize(500, 500);
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setVisible(true);
    }

    private void startSolve() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String txt = fields[i][j].getText();
                if (!txt.isEmpty()) {

                    try {
                        int num = Integer.parseInt(txt);
                        if(num<1||num>size){
                            JOptionPane.showMessageDialog(boardFrame, "Apenas números entre 1 e"+size);
                            return;
                        }
                        board.setNumbers(num, i, j);
                    } catch (NumberFormatException e) {

                    }
                }
            }
        }

        if (board.solve()) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String textoOriginal = fields[i][j].getText();

                    fields[i][j].setText(String.valueOf(board.getNumber(i, j)));
                    fields[i][j].setEditable(false);
                    if (textoOriginal.isEmpty()) {

                        fields[i][j].setBackground(new Color(249,229,160));
                        fields[i][j].setForeground(Color.BLACK);
                    } else {

                        fields[i][j].setBackground(new Color(207, 200, 94));
                        fields[i][j].setForeground(Color.BLACK);

                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(boardFrame, "Não há solução para este Sudoku!");
        }
    }
}