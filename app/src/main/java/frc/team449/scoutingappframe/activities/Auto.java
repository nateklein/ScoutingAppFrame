package frc.team449.scoutingappframe.activities;

import android.os.Bundle;
import android.widget.CheckBox;

import frc.team449.scoutingappframe.R;
import frc.team449.scoutingappframe.activities.base_activites.InmatchBaseActivity;
import frc.team449.scoutingappframe.model.Match;

public class Auto extends InmatchBaseActivity {

    // Text fields that display current value
    // Input fields
    private CheckBox noAuto;
    private CheckBox movedForward;

    // Displays auto page on activity call
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autonomous_page);

        noAuto = findViewById(R.id.noAuto);
        noAuto.setChecked(Match.getInstance().isNoAuto());
        movedForward = findViewById(R.id.movedForward);
        movedForward.setChecked(Match.getInstance().isMovedForward());

    }

    @Override
    protected void setupNavButtons(){
        prevButton.setText("Prematch");
        nextButton.setText("Teleop");
        prevActivity = Prematch.class;
        nextActivity = Teleop.class;
    }

    @Override
    public void saveData(){
        Match.getInstance().setNoAuto(noAuto.isChecked());
        Match.getInstance().setMovedForward(movedForward.isChecked());
    }


}
