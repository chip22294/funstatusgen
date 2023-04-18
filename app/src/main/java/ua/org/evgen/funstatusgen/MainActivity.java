package ua.org.evgen.funstatusgen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    //private ImageButton runImageButton, trushImageButton, shareImageButton;
    private RadioButton manRadioButton, womanRadioButton;
    private TextView textView;

    private FunStatusGuide funStatusGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        runImageButton = (ImageButton) findViewById(R.id.runImageButton);
        trushImageButton = (ImageButton) findViewById(R.id.trushImageButton);
        shareImageButton = (ImageButton) findViewById(R.id.shareImageButton);
        */
        manRadioButton = (RadioButton) findViewById(R.id.manRadioButton);
        womanRadioButton = (RadioButton) findViewById(R.id.womanRadioButton);
        textView = (TextView) findViewById(R.id.textView);

        int funPartsCount = getResources().getStringArray(R.array.man_part1).length;
        funStatusGuide = new FunStatusGuide(funPartsCount);
        FunStatusGuide.setContext(getApplicationContext());
        FunStatusGuide.setForwho(FunStatusGuide.ForWho.Man);
/*
        statusTextView.setText(
                funStatusGuide.getStatus(FunStatusGuide.ForWho.Woman, 2, 4, 10));
*/
        //statusTextView.setText(funStatusGuide.generate());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        textView.setText(funStatusGuide.generate());
    }

    public void manRadioButtonClick (View view) {
        womanRadioButton.setChecked(false);
        FunStatusGuide.setForwho(FunStatusGuide.ForWho.Man);
    }

    public void womanRadioButtonClick (View view) {
        manRadioButton.setChecked(false);
        FunStatusGuide.setForwho(FunStatusGuide.ForWho.Woman);
    }

    public void runImageButtonClick (View view) {
        textView.setText(funStatusGuide.generate());
    }

    public void trushImageButtonClick (View view) {
        textView.setText("");
    }

    public void shareImageButtonClick (View view) {
        String shareContent = textView.getText().toString();

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, shareContent);
        startActivity(Intent.createChooser(share, getString(R.string.share_label)));
    }
}
