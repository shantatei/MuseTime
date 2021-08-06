package com.example.p03music1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    EditText email;
    EditText pass;
    Button login;
    TextView forgetPass;
    TextView musetime;
    float v=0;
    @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        email=root.findViewById(R.id.email);
        pass=root.findViewById(R.id.pass);
        forgetPass=root.findViewById(R.id.forgetPass);
        login = root.findViewById(R.id.loginBtn);
        musetime = root.findViewById(R.id.museTime);

        email.setTranslationY(800);
        pass.setTranslationY(800);
        forgetPass.setTranslationY(800);
        login.setTranslationY(800);
        musetime.setTranslationY(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);
        musetime.setAlpha(v);

        musetime.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(200).start();
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomeScreen.class);
                startActivity(intent);

            }
        });
        return root;
    }


}
