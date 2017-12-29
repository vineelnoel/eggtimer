package vineel.noel.com.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class EggTimer extends AppCompatActivity {
    SeekBar sbTimeSeek;
    TextView tvTimer;
    Button btnTimeController;
    boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        tvTimer.setText("0:30");
        sbTimeSeek.setProgress(30);
        countDownTimer.cancel();
        btnTimeController.setText("GO!");
        sbTimeSeek.setEnabled(true);
        counterIsActive = false;
    }
    public void updateTimer(int i){
        int minutes = i/60;
        int seconds = i - minutes * 60;
        String secString = Integer.toString(seconds);
        if(seconds <= 9){
            secString = "0"+secString;
        }
        tvTimer.setText(Integer.toString(minutes)+":"+secString);
    }
    public void controlTimer(View view){
        if(counterIsActive == false) {
            counterIsActive = true;
            sbTimeSeek.setEnabled(false);
            btnTimeController.setText("Stop!");
            countDownTimer = new CountDownTimer(sbTimeSeek.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egg_timer_layout);
        sbTimeSeek = (SeekBar) findViewById(R.id.sbTimeSeek);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        btnTimeController = (Button) findViewById(R.id.btnTimeController);
        sbTimeSeek.setMax(600);
        sbTimeSeek.setProgress(30);

        sbTimeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
