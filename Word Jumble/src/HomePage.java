import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JButton btnExit;
    private JButton btnplayers2;
    private JPanel mainPanel;
    private JButton instructionsButton;

    private javax.swing.Timer t;

    public HomePage(String title){
        super(title);
        this.setContentPane(mainPanel);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();


        btnplayers2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Players2 p2 = new Players2();
                p2.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(instructionsButton, "Each player has 30 seconds to think of answer\nThere can be two or three players\nEach player has five rounds\nIf you use hint, 5 points will be deducted from your total points", "Instructions", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new HomePage("Word Puzzle");
        frame.setVisible(true);
    }
}
