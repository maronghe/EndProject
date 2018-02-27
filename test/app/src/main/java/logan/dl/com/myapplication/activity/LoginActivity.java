package logan.dl.com.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.fingerLogin.utils.FingerprintAuthenticationHelper;

public class LoginActivity extends AppCompatActivity {

    // Declare a string variable for the key we’re going to use in our fingerprint authentication
    private static final String KEY_NAME = "THE_SUPER_SECRET_KEY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Initialize TextView to be used for simple feedback to the user
        TextView feedbackTextView = findViewById(R.id.tvFeedbackTextView);

        // Implement Fingerprint Authentication
        FingerprintAuthenticationHelper fingerprintAuthenticationHelper = new FingerprintAuthenticationHelper(KEY_NAME);
        fingerprintAuthenticationHelper.completeFingerprintAuthentication(this, feedbackTextView);

    }


}
