import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import javax.swing.Timer;

public class Players2 extends JFrame {
    protected ArrayList<String> words = new ArrayList<>();
    protected ArrayList<String> hints = new ArrayList<>();

    protected int points = 0;

    protected int points2 = 0;

    protected int randomNum;

    private JTextField txtGuess;
    protected JButton btnStart;
    protected JButton exitButton;
    protected JLabel lblInfo;
    protected JLabel lblGuess;
    protected JButton hintButton;
    protected JPanel mainPanel;
    protected JButton nextButton;
    protected JButton guessButton;
    protected JLabel lblp1points;
    protected JLabel lblp2points;
    protected JLabel lblTimer;
    private JLabel answerInfo;

    private javax.swing.Timer t;
    private int timeLeft = 30;
    String currentPlayer = "Player1";
    int count = 0;


    public Players2() {
        lblp1points.setText(Integer.toString(points));
        lblp2points.setText(Integer.toString(points2));
        setTitle("2 Players");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        fetchFile();

        t = new Timer(1000, new ActionListener() {



            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                lblTimer.setText(Integer.toString(timeLeft));
                timeLeft--;
                if (timeLeft < 0 || answerInfo.getText().equals("Correct answer")) {
                    t.stop();
                    lblTimer.setText("Time's up");
                }
            }


        });
        t.stop();

        btnStart.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lblInfo.setText("Player1");
                String guess_word = started();

                lblGuess.setText(guess_word);


                t.start();


            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String playerInfo = lblInfo.getText();
                if (playerInfo.equals("Player1")) {
                    points -= 5;
                } else if (playerInfo.equals("Player2")) {
                    points2 -= 5;
                }
                String hint = hints.get(randomNum);
                JOptionPane.showMessageDialog(hintButton, hint, "This is the hint", JOptionPane.INFORMATION_MESSAGE);
                lblp1points.setText(Integer.toString(points));
                lblp2points.setText(Integer.toString(points2));

            }
        });


        nextButton.addActionListener(new ActionListener() {
//            String timeLeft = lblTimer.getText();

            @Override

            public void actionPerformed(ActionEvent actionEvent) {



                    String guess_word;
                    if (lblTimer.getText().equals("Time's up")) {
                        count++;
                        lblInfo.setText("");
                        lblInfo.setText(currentPlayer);
                        guess_word = started();
                        timeLeft = 30;
                        t.start();

                        answerInfo.setText("");
                        lblGuess.setText(guess_word);
                    }
                    if(count == 10){
                        String p1 = lblp1points.getText();
                        String p2 = lblp2points.getText();

                        int p1mod = Integer.parseInt(p1);
                        int p2mod = Integer.parseInt(p2);

                        String winner;
                        if(p1mod > p2mod){
                            winner = "Player1";
                        }
                        else{
                            winner = "Player2";
                        }

                        JOptionPane.showMessageDialog(btnStart,winner , "Winner", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                }


//            }
        });

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String GuessText;
                String playerInfo;
                playerInfo = lblInfo.getText();
                GuessText = txtGuess.getText();
                if (GuessText.equals(words.get(randomNum))) {
                    answerInfo.setText("Correct answer");
                    if (t != null)
                        t.stop();
                    if (playerInfo.equals("Player1")) {
                        System.out.println("Hello gee");
                        points += 10;
                        lblp1points.setText(Integer.toString(points));
                        t.stop();
                        lblTimer.setText("Time's up");
                        currentPlayer = "Player2";

                    } else if (playerInfo.equals("Player2")) {
                        points2 += 10;
                        lblp2points.setText(Integer.toString(points2));
                        t.stop();
                        lblTimer.setText("Time's up");
                        currentPlayer = "Player1";
                    }
                }


            }
        });
    }

    public String Shuffle(String guess_word) {
        List<String> characters = Arrays.asList(guess_word.split(""));
        Collections.shuffle(characters);

        String afterShuffle = "";
        for (String character : characters) {
            afterShuffle += character;
        }

        if (afterShuffle == guess_word) {
            Shuffle(guess_word);
        }
        return afterShuffle;
    }


    private void fetchFile() {
        String target_file = "Word Jumble.txt";
        File file = new File(target_file);

        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        try {
            Scanner sn = new Scanner(file);

            while (sn.hasNextLine()) {
                String word = sn.nextLine();
                String hint = sn.nextLine();
                String space = sn.nextLine();
                word.strip();
                words.add(word);
                hints.add(hint);

            }
            sn.close();
        } catch (Exception e) {
            System.out.print("The code above cannot be run");
        }

    }

    public String started() {
        Random numberGenerator = new Random(); //Creates Random object
        randomNum = numberGenerator.nextInt(100);

        String guessWord = words.get(randomNum);
        String afterShuffle;
        afterShuffle = Shuffle(guessWord);

        return afterShuffle;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
    }

}

