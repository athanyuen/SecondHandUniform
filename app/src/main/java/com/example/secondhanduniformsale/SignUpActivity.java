package com.example.secondhanduniformsale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signUpEmail, signUpPassword, firstNameInput, lastNameInput;
    private TextView loginRedirectText;
    private Button signUpButton, signUpWithGoogleButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signUpEmail = findViewById(R.id.signup_email);
        signUpPassword = findViewById(R.id.signup_password);
        firstNameInput = findViewById(R.id.first_name);
        lastNameInput = findViewById(R.id.last_name);
        signUpButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signUpWithGoogleButton = this.findViewById(R.id.signup_google_button);
        setupGoogleSignIn();

        signUpWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        db.setFirestoreSettings(settings);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = signUpEmail.getText().toString().trim();
                String password = signUpPassword.getText().toString().trim();
                String firstName = firstNameInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();

                if(userEmail.isEmpty()){
                    signUpEmail.setError("Email cannot be empty");
                }
                if(password.isEmpty()){
                    signUpPassword.setError("Password cannot be empty");
                }
                if(firstName.isEmpty()){
                    firstNameInput.setError("Name cannot be empty");
                }
                if(lastName.isEmpty()){
                    lastNameInput.setError("Name cannot be empty");
                }
                if(password.length() < 8){
                    signUpPassword.setError("Password length has to be longer than 8");



                    auth.createUserWithEmailAndPassword(userEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = auth.getCurrentUser();
                                User newUser = new User(firstName, lastName, userEmail, password);
                                db.collection("users").document(user.getUid()).set(newUser);
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(SignUpActivity.this, "Signup Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }


            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
            }
        });
    }

    private void setupGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(SignUpActivity.this, "Google sign-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task ->{
                    if(task.isSuccessful()){
                        Log.d("Google Sign In", "signInWithCredential:success");
                        Toast.makeText(SignUpActivity.this, "Google Sign-In Successful!", Toast.LENGTH_SHORT).show();

                        FirebaseUser user = auth.getCurrentUser();
                        if(user != null){
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        if(user != null){
                            String userID = user.getUid();
                            String userEmail = user.getEmail();
                            String userName = user.getDisplayName();

                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("uid", userID);
                            userInfo.put("email", userEmail);
                            userInfo.put("name", userName); // Might be errors here



                            db.collection("users").document(userID).set(userInfo)
                                    .addOnSuccessListener(aVoid -> Log.d("Firestore", "User info successfully written!"))
                                    .addOnFailureListener(e -> Log.w("Firestore", "Error writing user info", e));
                        }
                    }
                    else{
                        Log.w("Google Sign In", "signInWithCredential:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}