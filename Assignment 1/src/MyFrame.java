import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.border.Border;

/*
  Anything that is related to GUI must be declared here otherwise marks will be deducted.
  You can include game logic here or you can create new classes to handle game logic – 
  this is optional you will not gain or lose marks for this. Add a comment to explain your choice.
*/

public class MyFrame extends JFrame {
  JFrame frame = new JFrame("Math Game");
  JPanel startPanel = new JPanel();
  JPanel secondPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  JPanel endPanel = new JPanel();
  JPanel endSubPanel = new JPanel();
  JLabel welcomeLabel, instruction, blankLabel, blankLabel1, score, endTitle, endData;
  JButton startButton, endButton;
  JButton nums[];
  static int win, lose, answers = 0;
  static int count = 2;
  int randomNumber;
  LinkedList<Integer> correct = new LinkedList<>();
  LinkedList<Integer> wrong = new LinkedList<>();

  // Create a standard frame for the game
  MyFrame() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(500, 500);

    welcomeLabel = new JLabel("Welcome to mini math game!", SwingConstants.CENTER);
    welcomeLabel.setPreferredSize(new Dimension(500, 50));

    startButton = new JButton("Start");
    startButton.setPreferredSize(new Dimension(200, 50));
    startButton.setFocusable(false);
    startButton.setBorder(new RoundBorder(20));
    startButton.addActionListener(new ButtonListener());

    startPanel.add(welcomeLabel);
    startPanel.add(startButton);

    frame.add(startPanel);
    frame.setVisible(true);
  }

  // Get random number
  private static int numberGenerator() {
    return (int) ((Math.random() * (18 - 2)) + 2);
  }

  // Game page for player to play
  void secondPage() {
    randomNumber = numberGenerator();
    instruction = new JLabel("Select two numbers to add up to " + randomNumber, SwingConstants.CENTER);
    instruction.setPreferredSize(new Dimension(500, 50));

    score = new JLabel("Win: " + win + " , Lose: " + lose, SwingConstants.CENTER);
    score.setPreferredSize(new Dimension(500, 50));

    blankLabel = new JLabel();
    blankLabel.setPreferredSize(new Dimension(500, 50));

    secondPanel.add(instruction);
    secondPanel.add(score);
    secondPanel.add(blankLabel);

    buttonPanel.setLayout(new GridLayout(3, 3));
    buttonPanel.setPreferredSize(new Dimension(150, 150));

    nums = new JButton[9];
    for (int i = 0; i < 9; i++) {
      nums[i] = new JButton(String.valueOf(i + 1));
      nums[i].setPreferredSize(new Dimension(50, 50));
      nums[i].setBorder(new RoundBorder(20));
      nums[i].setFocusable(false);
      nums[i].addActionListener(new ButtonListener());
    }

    for (int i = 0; i < nums.length; i++) {
      buttonPanel.add(nums[i]);
    }

    secondPanel.add(buttonPanel);

    blankLabel1 = new JLabel();
    blankLabel1.setPreferredSize(new Dimension(500, 50));
    secondPanel.add(blankLabel1);

    endButton = new JButton("End Game");
    endButton.setPreferredSize(new Dimension(200, 50));
    endButton.setFocusable(false);
    endButton.setBorder(new RoundBorder(20));
    endButton.addActionListener(new ButtonListener());

    secondPanel.add(endButton);

    frame.add(secondPanel);
  }

  // Summary score page
  void dataPage() {

    checkEmpty();

    endPanel.add(endData);

    frame.add(endPanel);

  }

  //Calculate the % win for numbers >= 10
  double percentageOfWinEqualOrGreaterThanTen(LinkedList<Integer> correct, LinkedList<Integer> wrong) {
    double totalCorrect = 0;
    double totalWrong = 0;

    for (int i = 0; i < correct.size(); i++) {
      if (correct.get(i) >= 10) {
        totalCorrect++;
      }
    }

    for (int i = 0; i < wrong.size(); i++) {
      if (wrong.get(i) >= 10) {
        totalWrong++;
      }
    }

    if (totalCorrect == 0) {
      return 0;
    }

    return (totalCorrect / (totalCorrect + totalWrong));
  }

  //Calculate the % win for numbers < 10
  double percentageOfWinlessThanTen(LinkedList<Integer> correct, LinkedList<Integer> wrong) {
    double totalCorrect = 0;
    double totalWrong = 0;

    for (int i = 0; i < correct.size(); i++) {
      if (correct.get(i) < 10) {
        totalCorrect++;
      }
    }

    for (int i = 0; i < wrong.size(); i++) {
      if (wrong.get(i) < 10) {
        totalWrong++;
      }
    }

    if (totalCorrect == 0) {
      return 0;
    }

    return (totalCorrect / (totalCorrect + totalWrong));
  }

  // Check if the basket (linkedlist) is empty and set the print out based on
  // result
  void checkEmpty() {
    if (!correct.isEmpty() && percentageOfWinEqualOrGreaterThanTen(correct, wrong) != 0
        && percentageOfWinlessThanTen(correct, wrong) != 0) {
      endData = new JLabel("<html><br/>Game Session Data Analysed:" +
          "<br/><br/>Number you got correct: " + correct +
          "<br/>Number you got wrong: " + wrong +
          "<br/>Percentage of win for numbers greater than or equal to 10: "
          + percentageOfWinEqualOrGreaterThanTen(correct, wrong) * 100 +
          "<br/>Percentage of win for numbers less than 10: " + percentageOfWinlessThanTen(correct, wrong) * 100
          + "<html>");

    } else if (!correct.isEmpty() && percentageOfWinEqualOrGreaterThanTen(correct, wrong) != 0) {
      endData = new JLabel("<html><br/>Game Session Data Analysed:" +
          "<br/><br/>Number you got correct: " + correct +
          "<br/>Number you got wrong: " + wrong +
          "<br/>Percentage of win for numbers greater than or equal to 10: "
          + percentageOfWinEqualOrGreaterThanTen(correct, wrong) * 100 +
          "<html>");
    } else if (!correct.isEmpty() && percentageOfWinlessThanTen(correct, wrong) != 0) {
      endData = new JLabel("<html><br/>Game Session Data Analysed:" +
          "<br/><br/>Number you got correct: " + correct +
          "<br/>Number you got wrong: " + wrong +
          "<br/>Percentage of win for numbers less than 10: " + percentageOfWinlessThanTen(correct, wrong) * 100
          + "<html>");
    } else {
      endData = new JLabel("<html><br/>Game Session Data Analysed:" +
          "<br/><br/>Number you got correct: " + correct +
          "<br/>Number you got wrong: " + wrong + "<html>");

    }

    endData.setPreferredSize(new Dimension(450, 450));
    endData.setVerticalAlignment(JLabel.TOP);

  }

  // Where buttons get a minds of their own
  private class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      if (event.getSource() == startButton) {
        startPanel.setVisible(false);
        secondPage();
      }

      if (event.getSource() == endButton) {
        secondPanel.setVisible(false);
        dataPage();
      }

      for (int i = 0; i < nums.length; i++) {
        if (event.getSource() == nums[i]) {
          if (count != 1) {
            answers += Integer.parseInt(nums[i].getText());
            count--;
          } else {
            answers += Integer.parseInt(nums[i].getText());
            if (answers == randomNumber) {
              correct.add(randomNumber);

              randomNumber = numberGenerator();

              if (randomNumber == correct.peekLast()) {
                randomNumber = numberGenerator();
              }

              instruction.setText("Select two numbers to add up to " + randomNumber);

              win++;
              score.setText("Win: " + win + " , Lose: " + lose);

              answers = 0;
              count = 2;
            } else {
              wrong.add(randomNumber);
              randomNumber = numberGenerator();

              if (randomNumber == wrong.peekLast()) {
                randomNumber = numberGenerator();
              }

              instruction.setText("Select two numbers to add up to " + randomNumber);

              lose++;
              score.setText("Win: " + win + " , Lose: " + lose);

              answers = 0;
              count = 2;
            }
          }
        }
      }

    }
  }

  // Implementing stylings for the number buttons using RoundBorder
  private static class RoundBorder implements Border {

    private int r;

    RoundBorder(int r) {
      this.r = r;
    }

    public Insets getBorderInsets(Component c) {
      return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
    }

    public boolean isBorderOpaque() {
      return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
      g.drawRoundRect(x, y, w - 1, h - 1, r, r);
    }
  }
}
