package es.uah.compubi.pelisubicuas;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import es.uah.compubi.pelisubicuas.app.AppController;
import es.uah.compubi.pelisubicuas.model.Movie;

public class InformacionActivity extends AppCompatActivity {

    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private SeekBar seekbr;
    private int value;
    private TextView result;
    private TextView timpePickerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.enviado, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movieObject = (Movie) getIntent().getSerializableExtra("Movie");

        getSupportActionBar().setTitle(movieObject.getTitle());

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);
        thumbNail.setImageUrl(movieObject.getThumbnailUrl(), imageLoader);

        seekbr = (SeekBar)findViewById(R.id.sbBar);
        result = (TextView)findViewById(R.id.tvResult);

        result.setText("Valor: "+(seekbr.getProgress()+10));

        //set change listener
        seekbr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                value = progress+10;
                result.setText ("Valor: "+value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        timpePickerTV = (TextView) findViewById(R.id.timepickerTV);
        timpePickerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(InformacionActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                timpePickerTV.setText(hourOfDay + ":" + minute + " hrs");
                            }
                        }, 0, 0, true);

                tpd.show();
            }
        });


    }

}
