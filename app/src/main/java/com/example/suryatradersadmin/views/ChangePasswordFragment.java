package com.example.suryatradersadmin.views;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.databinding.FragmentChangePasswordBinding;
import com.example.suryatradersadmin.viewmodel.AdminViewModel;
import com.example.suryatradersadmin.viewmodel.CustomerViewModel;


public class ChangePasswordFragment extends Fragment {

    private NavController navController;
    private FragmentChangePasswordBinding fragmentChangePasswordBinding;
    private AdminViewModel adminViewModel;

    public ChangePasswordFragment() { }

    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentChangePasswordBinding= FragmentChangePasswordBinding.inflate(inflater,container,false);
        return  fragmentChangePasswordBinding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adminViewModel=new ViewModelProvider(requireActivity()).get(AdminViewModel.class);
        fragmentChangePasswordBinding.setAdminViewModel(adminViewModel);

        fragmentChangePasswordBinding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminViewModel.changePassword();
                adminViewModel.getChangePasswordMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                {
                    public void onChanged(String message)
                    {
                        adminViewModel.getChangePasswordMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                        {
                            public void onChanged(String message)
                            {
                                String msg[];
                                if(message.contains("200")) {
                                    msg = message.split(" 200");
                                    Toast toast = Toast.makeText(getActivity(), msg[0], Toast.LENGTH_SHORT);
                                    View view = toast.getView();
                                    view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                                    toast.show();
                                }
                                else
                                {
                                    msg = message.split(" 1002");
                                    Toast toast = Toast.makeText(getActivity(), msg[0], Toast.LENGTH_SHORT);
                                    View view = toast.getView();
                                    view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                                    toast.show();
                                }
                            }
                        });
                    }
                });
            }
        });

        navController = Navigation.findNavController(view);
    }
}