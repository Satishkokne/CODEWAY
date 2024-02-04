import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

class Question {
    private String questionText;
    private String[] options;
    private int correctOptionIndex;

    public Question(String questionText, String[] options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Timer {
    private int timeRemaining;
    private TimerListener listener;

    public Timer(int seconds, TimerListener listener) {
        this.timeRemaining = seconds;
        this.listener = listener;
    }

    public void start() {
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                listener.onTimerTick(timeRemaining);
                if (timeRemaining == 0) {
                    listener.onTimerFinish();
                }
            }
        });
        timer.start();
    }
}

interface TimerListener {
    void onTimerTick(int secondsRemaining);

    void onTimerFinish();
}

public class QuizApplication extends JFrame implements TimerListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JLabel timerLabel; // Added timer label
    private Timer questionTimer;
    private int currentQuestionIndex;
    private int score;
    private ArrayList<Question> questions;

    private static final int QUESTION_TIME = 30;

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        timerLabel = new JLabel("Time Remaining: " + QUESTION_TIME + " seconds");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(timerLabel); // Added timer label
        panel.add(questionLabel);

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            panel.add(options[i]);
        }

        panel.add(nextButton);

        add(panel);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                loadNextQuestion();
            }
        });

        initializeQuestions();
        loadNextQuestion();
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "Berlin", "London", "Rome"}, 0));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0));
        questions.add(new Question("What is the largest mammal in the world?", new String[]{"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"}, 1));

        Collections.shuffle(questions);
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getQuestionText());

            String[] questionOptions = currentQuestion.getOptions();
            for (int i = 0; i < 4; i++) {
                options[i].setText(questionOptions[i]);
                options[i].setSelected(false);
            }

            questionTimer = new Timer(QUESTION_TIME, this);
            questionTimer.start();

            currentQuestionIndex++;
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && i == currentQuestion.getCorrectOptionIndex()) {
                score++;
                break;
            }
        }
    }

    private void finishQuiz() {
        if (questionTimer != null) {
            questionTimer = null;
        }

        JOptionPane.showMessageDialog(this, "Quiz Completed!\nYour Score: " + score);
        System.exit(0);
    }

    @Override
    public void onTimerTick(int secondsRemaining) {
        timerLabel.setText("Time Remaining: " + secondsRemaining + " seconds");
    }

    @Override
    public void onTimerFinish() {
        if (questionTimer != null) {
            questionTimer = null;
        }

        loadNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
