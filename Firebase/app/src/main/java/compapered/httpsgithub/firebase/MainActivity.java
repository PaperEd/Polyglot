package compapered.httpsgithub.firebase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setTImeClick = (Button)findViewById(R.id.setTimeClick);
        setTImeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker setTime = (TimePicker)findViewById(R.id.setTime);
                int messagingHour;
                int messagingMinute;
                messagingHour = setTime.getHour();
                messagingMinute = setTime.getMinute();
                Toast.makeText(getBaseContext(),"실행 잘되요",Toast.LENGTH_LONG).show();

            }
        });

    }
}
