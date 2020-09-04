package me.wakello.android.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static me.wakello.android.leaderboard.R.drawable.ic_alert;
import static me.wakello.android.leaderboard.R.drawable.ic_check;

public class SubmitActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtEmail;
    EditText txtGithub;

    Dialog popupQuestionDialog;     //For the popup asking if we want to submit (are you sure)
    Dialog popupInformationDialog;  //For popup giving results of the submitting the project
    ImageView imgClosePopup;        //Image used to close the popupQuestionDialog
    Button btnYes;                  //Yes button on popupQuestionDialog

    ImageView imgPopupInfo;
    TextView tvInformation;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mGithub;
    private String mTrack = "Android";

    Retrofit retrofit;
    GadsSubmitInterface submitProject;

    //static final String BASE_FORMS_URL = "https://docs.google.com/forms/u/0/d/e/";    //personal forms
    static final String BASE_FORMS_URL = "https://docs.google.com/forms/d/e/";          //GADS forms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_FORMS_URL)
                .build();
        submitProject = retrofit.create(GadsSubmitInterface.class);

        setContentView(R.layout.activity_submit);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtFirstName = findViewById(R.id.textFirstName);
        txtLastName = findViewById(R.id.textLastName);
        txtEmail = findViewById(R.id.textEmailAddress);
        txtGithub = findViewById(R.id.textGithubLink);

        popupQuestionDialog = new Dialog(this);
        popupInformationDialog = new Dialog(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstName = txtFirstName.getText().toString();
                mLastName = txtLastName.getText().toString();
                mEmail = txtEmail.getText().toString();
                mGithub = txtGithub.getText().toString();
                showQuestionPopup();        //function to ask if we want to submit
            }
        });
    }

    private void showQuestionPopup() {
        popupQuestionDialog.setContentView(R.layout.custom_question_popup);
        imgClosePopup = popupQuestionDialog.findViewById(R.id.imgClosePopup);
        btnYes = popupQuestionDialog.findViewById(R.id.btnConfirm);

        imgClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupQuestionDialog.dismiss();          //close the popup window
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupQuestionDialog.dismiss();          //close the popup window

                Call<Void> submitProjectCall = submitProject.GadsSubmitProject(mFirstName, mLastName, mEmail, mGithub, mTrack);
                submitProjectCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //if(response.code() == 200){
                        if(response.isSuccessful()){
                            txtFirstName.setText("");
                            txtLastName.setText("");
                            txtEmail.setText("");
                            txtGithub.setText("");
                            showInformationPopup(true);
                        }
                        else
                            showInformationPopup(false);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showInformationPopup(false);
                    }
                });


            }
        });

        popupQuestionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupQuestionDialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(popupQuestionDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        popupQuestionDialog.getWindow().setAttributes(layoutParams);
    }

    private void showInformationPopup(Boolean submissionStatus) {
        popupInformationDialog.setContentView(R.layout.custom_information_popup);
        popupInformationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgPopupInfo = popupInformationDialog.findViewById(R.id.imgDisplay);
        tvInformation = popupInformationDialog.findViewById(R.id.tvInformation);
        if(submissionStatus){
            tvInformation.setText(R.string.submission_successful);
            imgPopupInfo.setImageResource(ic_check);
        }
        else{
            tvInformation.setText(R.string.submission_not_successful);
            imgPopupInfo.setImageResource(ic_alert);
        }
        popupInformationDialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(popupInformationDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        popupInformationDialog.getWindow().setAttributes(layoutParams);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupInformationDialog.dismiss();
            }
        },5000);
    }
}