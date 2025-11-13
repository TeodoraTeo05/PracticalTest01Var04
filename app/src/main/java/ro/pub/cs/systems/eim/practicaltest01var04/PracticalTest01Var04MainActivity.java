package ro.pub.cs.systems.eim.practicaltest01var04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private EditText studentNameEditText, studentGroupEditText, studentInfo;
    private CheckBox studentButton, groupButton;
    private Button displayInformation;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private static final String STUDENT_INFO = "student_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        studentNameEditText = findViewById(R.id.number1_edit_text);
        studentGroupEditText = findViewById(R.id.number2_edit_text);
        studentInfo = findViewById(R.id.student_text);
        studentButton = findViewById(R.id.number1_checkbox);
        groupButton = findViewById(R.id.number2_checkbox);
        displayInformation = findViewById(R.id.display_button);
        displayInformation.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STUDENT_INFO)) {
                studentInfo.setText(savedInstanceState.getString(STUDENT_INFO));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STUDENT_INFO, studentInfo.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String numeStudent = studentNameEditText.getText().toString().trim();
            String groupStudent = studentGroupEditText.getText().toString().trim();
            boolean currentCheck1 = studentButton.isChecked();
            boolean currentCheck2 = groupButton.isChecked();

            if (view.getId() == R.id.display_button) {
                if (!currentCheck1 && !currentCheck2) {
                    studentInfo.setText("");
                }
                if (currentCheck1 && !currentCheck2) {
                    if (numeStudent.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Completează numele studentului!", Toast.LENGTH_SHORT).show();
                        studentInfo.setText("");
                        return;
                    }
                    studentInfo.setText(numeStudent);
                } else if (!currentCheck1 && currentCheck2) {
                    if (groupStudent.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Completează grupa studentului!", Toast.LENGTH_SHORT).show();
                        studentInfo.setText("");
                        return;
                    }
                    studentInfo.setText(groupStudent);
                } else if (currentCheck1 && currentCheck2) {
                    if (numeStudent.isEmpty() || groupStudent.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Completează toate câmpurile!", Toast.LENGTH_SHORT).show();
                        studentInfo.setText("");
                        return;
                    }
                    studentInfo.setText(numeStudent + " " + groupStudent);
                }
            }
        }
    }
}
