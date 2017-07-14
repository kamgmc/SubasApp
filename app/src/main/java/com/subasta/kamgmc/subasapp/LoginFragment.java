package com.subasta.kamgmc.subasapp;

import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Sesion sesion;
    private SearchView search;
    private BottomNavigationView navigation;

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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sesion = new Sesion(getActivity());
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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkEmail, checkPass;
                if(validEmail(email.getText().toString()))
                    checkEmail = true;
                else {
                    checkEmail = false;
                    email.setError("Email invalido");
                }
                if(password.getText().toString().length() >= 8)
                    checkPass = true;
                else {
                    checkPass = false;
                    password.setError( "La contraseña es muy corta" );
                }
                if(checkEmail&&checkPass){
                    Encriptador encriptador = new Encriptador();
                    String pass = encriptador.MD5(password.getText().toString());
                    String mail = email.getText().toString();
                    Usuario usuario = myRealm.where(Usuario.class).equalTo("email",mail).equalTo("password",pass).findFirst();
                    if(usuario != null){
                        if(usuario.getPassword().equals(pass)){
                            sesion.iniciar(view.getContext(),usuario);
                            Toast.makeText(getContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                            AccountFragment nextFragment = new AccountFragment();
                            getFragmentManager().beginTransaction().replace(R.id.frame_layout, nextFragment,"AccountTag").addToBackStack(null).commit();
                            //navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
                            //navigation.getMenu().findItem(R.id.navigation_login).setTitle("Cuenta");
                        }
                        else
                            Toast.makeText(getContext(), "Email y/o contraseña incorrecta", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),"Email y/o contraseña incorrecta",Toast.LENGTH_LONG).show();
                }
            }

        });

        return view;
    }

    public boolean validEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;

        if(pattern.matcher(email).matches())
            return true;
        else
            return false;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        search = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        MenuItem icon = (MenuItem)menu.findItem(R.id.action_search);
        icon.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
