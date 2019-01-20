package frc.team449.scoutingappframe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import frc.team449.scoutingappframe.R;
import frc.team449.scoutingappframe.model.Match;


public class Prematch extends BaseActivity implements AdapterView.OnItemSelectedListener {

    // Team and match #
    private static String matchNumberValue;
    private static String teamNumberValue;

    // Input fields
    private EditText scoutNameText;
    private CheckBox noShowBox;
    private Spinner teamNumber;
    private ArrayAdapter<CharSequence> teamAdapter;
    private Spinner matchNumber;
    private ArrayAdapter<CharSequence> matchAdapter;

    // Displays prematch page on activity call
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prematch_page);
        // Creates and sets data trackers to Match values
        scoutNameText = findViewById(R.id.scoutName);
        scoutNameText.setText(Match.getInstance().getScoutName());
        noShowBox = findViewById(R.id.noShow);
        noShowBox.setChecked(Match.getInstance().isNoShow());
        teamNumber = findViewById(R.id.teamNumber);
        teamAdapter = ArrayAdapter.createFromResource(this, R.array.teams, R.layout.dropdown);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamNumber.setAdapter(teamAdapter);
        teamNumber.setOnItemSelectedListener(this);
        teamNumber.setSelection(Match.getInstance().getTeamNumber());
        matchNumber = findViewById(R.id.matchNumber);
        matchAdapter = ArrayAdapter.createFromResource(this, R.array.matches, R.layout.dropdown);
        matchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matchNumber.setAdapter(matchAdapter);
        matchNumber.setOnItemSelectedListener(this);
        matchNumber.setSelection(Match.getInstance().getMatchNumber());
        teamNumberValue = teamNumber.getItemAtPosition(0).toString();
        matchNumberValue = matchNumber.getItemAtPosition(0).toString();

        findViewById(R.id.matchTitle).setVisibility(View.GONE);
        findViewById(R.id.teamTitle).setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // Checks which dropdown was selected and changes the Match value accordingly
        if (parent.getId() == R.id.matchNumber) {
            Match.getInstance().setMatchNumber(pos);
            matchNumberValue = parent.getItemAtPosition(pos).toString();
        }
        else if (parent.getId() == R.id.teamNumber) {
            Match.getInstance().setTeamNumber(pos);
            teamNumberValue = parent.getItemAtPosition(pos).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Checks which dropdown was selected and changes the Match value accordingly
        if (parent.getId() == R.id.matchNumber) {
            Match.getInstance().setMatchNumber(0);
            matchNumberValue = parent.getItemAtPosition(0).toString();
        }
        else if (parent.getId() == R.id.teamNumber) {
            Match.getInstance().setTeamNumber(0);
            teamNumberValue = parent.getItemAtPosition(0).toString();
        }
    }

    public static String getTeamNum() {
        return teamNumberValue;
    }

    public static String getMatchNum() {
        return matchNumberValue;
    }

    // Calls activity to go to auto page
    public void toAuto(View v) {
        // Saves values to Match
        Match.getInstance().setScoutName(scoutNameText.getText().toString());
        Match.getInstance().setNoShow(noShowBox.isChecked());
        // Switches pages
        Intent toAuto = new Intent(this, Auto.class);
        startActivity(toAuto);
    }
}