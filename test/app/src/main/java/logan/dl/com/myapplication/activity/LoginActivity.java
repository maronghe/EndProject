package logan.dl.com.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.fingerLogin.fingerPrintAuth;
import logan.dl.com.myapplication.fingerLogin.utils.FingerprintAuthenticationHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare a string variable for the key we’re going to use in our fingerprint authentication
    private static final String KEY_NAME = "THE_SUPER_SECRET_KEY_NAME";

    private ImageButton ib_login,ib_finger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ib_finger = findViewById(R.id.ib_finger);

        ib_login = findViewById(R.id.ib_login);

        ib_finger.setOnClickListener(this);
        ib_login.setOnClickListener(this);

        // Initialize TextView to be used for simple feedback to the user
        TextView feedbackTextView = findViewById(R.id.tvFeedbackTextView);

        // Implement Fingerprint Authentication
//        FingerprintAuthenticationHelper fingerprintAuthenticationHelper = new FingerprintAuthenticationHelper(KEY_NAME);
//        fingerprintAuthenticationHelper.completeFingerprintAuthentication(this, feedbackTextView);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_finger:
                Intent intent = new Intent(LoginActivity.this,fingerPrintAuth.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ib_login:
                startActivity(new Intent(LoginActivity.this,TotalActivity.class));
                break;
        }
    }
}
