package frc.team449.scoutingappframe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import frc.team449.scoutingappframe.R;
import frc.team449.scoutingappframe.helpers.SubmitHelper;
import frc.team449.scoutingappframe.model.Match;

public class Endgame extends BaseActivity {

    private EditText comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame_page);

        comments = findViewById(R.id.comments);
        comments.setText(Match.getInstance().getComments());
    }

    private void saveData(){
        // Save values to Database
        Match.getInstance().setComments(comments.getText().toString());
    }

    // Calls activity to go to auto page
    public void toTeleop(View v) {
        saveData();
        // Switches pages
        Intent toTeleop = new Intent(this, Teleop.class);
        startActivity(toTeleop);
    }

    // Calls activity to go to endgame page
    public void submit(View v) {
        saveData();

        // Submit
        SubmitHelper.submit(this);
    }

}