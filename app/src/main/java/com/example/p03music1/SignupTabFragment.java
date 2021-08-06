package com.example.p03music1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {

    EditText email;
    EditText MobileNum;
    EditText pass;
    EditText cfmpass;
    TextView musetime;
    float v=0;

    @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email=root.findViewById(R.id.email);
        pass=root.findViewById(R.id.pass);
        cfmpass=root.findViewById(R.id.cfmPass);
        MobileNum = root.findViewById(R.id.mobileNum);
        musetime = root.findViewById(R.id.museTime);

        email.setTranslationY(800);
        pass.setTranslationY(800);
        cfmpass.setTranslationY(800);
        MobileNum.setTranslationY(800);
        musetime.setTranslationY(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        cfmpass.setAlpha(v);
        MobileNum.setAlpha(v);
        musetime.setAlpha(v);

        musetime.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(200).start();
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        MobileNum.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        cfmpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        return root;
    }
}
