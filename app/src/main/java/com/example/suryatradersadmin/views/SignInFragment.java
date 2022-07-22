package com.example.suryatradersadmin.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.databinding.FragmentSignInBinding;
import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.tokenmanager.TokenManager;
import com.example.suryatradersadmin.utils.Util;
import com.example.suryatradersadmin.viewmodel.AdminViewModel;
import com.example.suryatradersadmin.viewmodel.CustomerViewModel;

import java.util.Iterator;
import java.util.Map;

public class SignInFragment extends Fragment {

    private FrameLayout parentFrameLayout;
    private FragmentSignInBinding fragmentSignInBinding;
    private AdminViewModel adminViewModel;
    private static final String TAG="SignInFragment ";
    AlertDialog alertDialog;
    Admin admin;

    public SignInFragment() {
      
    }

   
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSignInBinding= FragmentSignInBinding.inflate(inflater,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        parentFrameLayout=getActivity().findViewById(R.id.login_frame_layout);
        return  fragmentSignInBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        adminViewModel=new ViewModelProvider(requireActivity()).get(AdminViewModel.class);
      /*  adminViewModel.getCSRFToken().observe(getViewLifecycleOwner(), new Observer<CSRFToken>() {
            @Override
            public void onChanged(CSRFToken csrfToken) {
                if (csrfToken != null)
                {
                    Log.d(TAG, "CSRF Token = " + csrfToken.getCsrf());
                }
                TokenManager tokenManager = new TokenManager(getActivity());
                tokenManager.createCSRFTokenSession(csrfToken);

            }
        });*/
        fragmentSignInBinding.setAdminViewModel(adminViewModel);
        fragmentSignInBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSignInBinding.failedIdentity.setVisibility(View.INVISIBLE);
                adminViewModel.onLoginClick();
                adminViewModel.getIsSuccessful().observe(getViewLifecycleOwner(), new Observer<Boolean>()
                {
                    @Override
                    public void onChanged(@Nullable Boolean isSuccessful) {
                        if(isSuccessful)
                        {
                            final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                            LayoutInflater inflater=getLayoutInflater();
                            builder.setView(inflater.inflate(R.layout.progress_bar_dialog,null));
                            builder.setCancelable(false);
                            alertDialog=builder.create();
                            alertDialog.show();
                            getAccessToken();
                        }
                        else {
                            adminViewModel.getAuthenticationFailure().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String message)
                                {
                                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                                }
                            });
                            fragmentSignInBinding.failedIdentity.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }

        });
    }


    public void getAccessToken()
    {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                alertDialog.dismiss();
            }
        },10000);
        adminViewModel.getAccessToken().observe(getViewLifecycleOwner(), new Observer<Map<String,String>>() {
            @Override
            public void onChanged(Map<String,String> accessToken) {
                String key=null;
                String value=null;
                if(accessToken!=null)
                {
                    Iterator entries = accessToken.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        key = (String)entry.getKey();
                        value = (String)entry.getValue();
                        Log.d(TAG,"Key = " + key + ", Value = " + value);
                    }
                    TokenManager tokenManager=new TokenManager(getActivity());
                    tokenManager.createSession(key,value);
                    Log.d(TAG,"Hello username "+TokenManager.getInstance().getUserName("KEY_USER_NAME", "def"));
                    Util.setIntent(getActivity(), AdminActivity.class);

                }
            }
        });
    }
}