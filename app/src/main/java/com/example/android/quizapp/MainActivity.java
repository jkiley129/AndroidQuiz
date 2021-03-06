package com.example.android.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // MARK: - Properties
    TextView titleTextView;
    TextView question1TextView;
    TextView question2TextView;
    TextView question3TextView;
    TextView question4TextView;
    TextView question5TextView;
    TextView question6TextView;
    EditText question1EditText;
    EditText question3EditText;
    EditText question6EditText;
    RadioGroup question2RadioGroup;
    RadioGroup question5RadioGroup;
    Button validateButton;
    int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViews();
        setupViews();
    }

    // MARK: - Setup
    private void configureViews() {
        titleTextView = findViewById(R.id.title);
        question1TextView = findViewById(R.id.question1Title);
        question2TextView = findViewById(R.id.question2Title);
        question3TextView = findViewById(R.id.question3Title);
        question4TextView = findViewById(R.id.question4Title);
        question5TextView = findViewById(R.id.question5Title);
        question6TextView = findViewById(R.id.question6Title);
        question1EditText = findViewById(R.id.question1Response);
        question2RadioGroup = findViewById(R.id.question2RadioGroup);
        question3EditText = findViewById(R.id.question3Response);
        question5RadioGroup = findViewById(R.id.question5RadioGroup);
        question6EditText = findViewById(R.id.question6Response);
        validateButton = findViewById(R.id.validate);
        titleTextView.setText(getString(R.string.app_title));
        question1TextView.setText(getText(R.string.question_1_prompt));
        question2TextView.setText(getText(R.string.question_2_prompt));
        question3TextView.setText(getText(R.string.question_3_prompt));
        question4TextView.setText(getText(R.string.question_4_prompt));
        question5TextView.setText(getText(R.string.question_5_prompt));
        question6TextView.setText(getText(R.string.question_6_prompt));
        validateButton.setText(getText(R.string.validate_button_text));
    }

    private void setupViews() {
        setupQuestion2();
        setupQuestion4();
        setupQuestion5();
    }

    private void setupQuestion2() {
        int i = 0;
        while (i < question2RadioGroup.getChildCount()) {
            String textToSet = "";
            if (i == 0) {
                textToSet = getString(R.string.question2_choice1);
            } else if (i == 1) {
                textToSet = getString(R.string.question2_choice2);
            } else if (i == 2) {
                textToSet = getString(R.string.question2_choice3);
            } else if (i == 3) {
                textToSet = getString(R.string.question2_choice4);
            }
            ((RadioButton) question2RadioGroup.getChildAt(i)).setText(textToSet);
            i++;
        }
    }

    private void setupQuestion4() {
        CheckBox checkbox1 = findViewById(R.id.question4Response1);
        CheckBox checkbox2 = findViewById(R.id.question4Response2);
        CheckBox checkbox3 = findViewById(R.id.question4Response3);
        CheckBox checkbox4 = findViewById(R.id.question4Response4);
        checkbox1.setText(getText(R.string.question4_choice1));
        checkbox2.setText(getText(R.string.question4_choice2));
        checkbox3.setText(getText(R.string.question4_choice3));
        checkbox4.setText(getText(R.string.question4_choice4));
    }

    private void setupQuestion5() {
        int i = 0;
        while (i < question5RadioGroup.getChildCount()) {
            String textToSet = "";
            if (i == 0) {
                textToSet = getString(R.string.question5_choice1);
            } else if (i == 1) {
                textToSet = getString(R.string.question5_choice2);
            } else if (i == 2) {
                textToSet = getString(R.string.question5_choice3);
            } else if (i == 3) {
                textToSet = getString(R.string.question5_choice4);
            }
            ((RadioButton) question5RadioGroup.getChildAt(i)).setText(textToSet);
            i++;
        }
    }

    // MARK: - Actions
    public void validateAnswers(View v) {
        correctAnswers = 0;
        dismissKeyboard();
        Boolean question1Correct = validateQuestion1();
        Boolean question2Correct = validateQuestion2();
        Boolean question3Correct = validateQuestion3();
        Boolean question4Correct = validateQuestion4();
        Boolean question5Correct = validateQuestion5();
        Boolean question6Correct = validateQuestion6();
        if (question1Correct &&
                question2Correct &&
                question3Correct &&
                question4Correct &&
                question5Correct &&
                question6Correct) {
            showSuccessToast();
        } else {
            showFailureToast();
        }
    }

    private void showSuccessToast() {
        Context context = getApplicationContext();
        CharSequence text = getText(R.string.success_alert_title);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        View view = toast.getView();
        view.setBackgroundColor(Color.parseColor("#4CAF50"));
        TextView newText = view.findViewById(android.R.id.message);
        newText.setTextColor(Color.parseColor("#FFFFFF"));
        toast.show();
    }

    private void showFailureToast() {
        Context context = getApplicationContext();
        CharSequence text = getText(R.string.failure_alert_title) + " " + Integer.toString(correctAnswers) + "/" + "6 correct";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        View view = toast.getView();
        view.setBackgroundColor(Color.parseColor("#F44336"));
        TextView newText = view.findViewById(android.R.id.message);
        newText.setTextColor(Color.parseColor("#FFFFFF"));
        toast.show();
    }

    private void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(question1EditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(question3EditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(question6EditText.getWindowToken(), 0);
    }

    private Boolean validateQuestion1()    {
        if (question1EditText.getText().toString().equals(getText(R.string.question_1_answer))) {
            question1EditText.setBackgroundColor(getResources().getColor(R.color.correctColor));
            correctAnswers += 1;
            return true;
        } else {
            question1EditText.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
            return false;
        }
    }

    private Boolean validateQuestion2() {
        // This initial check is to make sure that the app does not crash if a negative value is passed through.
        // By default a negative value populates if no radio button is selected
        if (question2RadioGroup.getCheckedRadioButtonId() != -1) {
            int selectedRadioButtonID = question2RadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
            if (selectedRadioButton.getText().equals(getText(R.string.question_2_answer))) {
                question2RadioGroup.setBackgroundColor(getResources().getColor(R.color.correctColor));
                correctAnswers += 1;
                return true;
            } else {
                question2RadioGroup.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
                return false;
            }
        } else {
            question2RadioGroup.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
            return false;
        }
    }

    private Boolean validateQuestion3() {
        if (question3EditText.getText().toString().equals(getText(R.string.question_3_answer))) {
            question3EditText.setBackgroundColor(getResources().getColor(R.color.correctColor));
            correctAnswers += 1;
            return true;
        } else {
            question3EditText.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
            return false;
        }
    }

    private Boolean validateQuestion4() {
        CheckBox checkbox1 = findViewById(R.id.question4Response1);
        CheckBox checkbox2 = findViewById(R.id.question4Response2);
        CheckBox checkbox3 = findViewById(R.id.question4Response3);
        CheckBox checkbox4 = findViewById(R.id.question4Response4);
        if (checkbox1.isChecked()
                && checkbox1.getText().toString().equals(getText(R.string.question_4_answer_part_1))) {
            checkbox1.setBackgroundColor(getResources().getColor(R.color.correctColor));
        } else {
            checkbox1.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
        }
        if (checkbox3.isChecked()
                && checkbox3.getText().toString().equals(getText(R.string.question_4_answer_part_2))) {
            checkbox3.setBackgroundColor(getResources().getColor(R.color.correctColor));
        } else {
            checkbox3.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
        }
        if (checkbox2.isChecked()) {
            checkbox2.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
        }
        if (checkbox4.isChecked()) {
            checkbox4.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
        }
        if (checkbox1.isChecked() && !checkbox2.isChecked() && checkbox3.isChecked() && !checkbox4.isChecked()) {
            correctAnswers += 1;
            return true;
        } else {
            return false;
        }
    }

    private Boolean validateQuestion5() {
        // This initial check is to make sure that the app does not crash if a negative value is passed through.
        // By default a negative value populates if no radio button is selected
        if (question5RadioGroup.getCheckedRadioButtonId() != -1) {
            int selectedRadioButtonID = question5RadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
            if (selectedRadioButton.getText().equals(getText(R.string.question_5_answer))) {
                question5RadioGroup.setBackgroundColor(getResources().getColor(R.color.correctColor));
                correctAnswers += 1;
                return true;
            } else {
                question5RadioGroup.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
                return false;
            }
        } else {
            question5RadioGroup.setBackgroundColor(getResources().getColor(R.color.incorrectColor));
            return false;
        }
    }

    private Boolean validateQuestion6() {
        if (question6EditText.getText().toString().equals(getText(R.string.question_6_answer))) {
            question6EditText.setBackgroundColor(getResources().getColor(R.color.correctColor));
            correctAnswers += 1;
            return true;
        } else {
            question6EditText.setBackgroundColor(getResources().getColor(R.color.correctColor));
            return false;
        }
    }
}
