package com.subasta.kamgmc.subasapp;

import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import io.realm.Realm;

public class LoginFragment extends Fragment {

    private View view;
    private Realm myRealm;
    private EditText email;
    private EditText password;
    private Button login;
    private TextView sign;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_login, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        myRealm = Realm.getDefaultInstance();

        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        login = (Button) view.findViewById(R.id.btnLogin);
        sign = (TextView) view.findViewById(R.id.sign);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern pattern = Patterns.EMAIL_ADDRESS;

                if(pattern.matcher(email.getText().toString()).matches()){
                    if(password.getText().toString().length() >= 8){

                        Encriptador encriptador = new Encriptador();
                        String pass = encriptador.MD5(password.getText().toString());
                        String mail = email.getText().toString();
                        Usuario usuario = myRealm.where(Usuario.class).equalTo("email",mail).findFirst();
                        if(usuario != null){
                            if(usuario.getPassword().equals(pass)){
                                Toast.makeText(getContext(),"Se a logueado",Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(getContext(), "Email y/o contraseña incorrecta", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getContext(),"Email y/o contraseña incorrecta",Toast.LENGTH_LONG).show();

                    }
                    else
                        password.setError( "La contraseña es muy corta" );
                }
                else
                    email.setError("Email invalido");
            }
        });

        return view;
    }

}
