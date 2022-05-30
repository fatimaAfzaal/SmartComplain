package com.example.smartcomplain.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smartcomplain.MainActivity;
import com.example.smartcomplain.MainActivity4;
import com.example.smartcomplain.MainActivity5;
import com.example.smartcomplain.R;
import com.example.smartcomplain.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {
    Activity move;
    Button logout;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home,container,false);

        TextView tv=(TextView) v.findViewById(R.id.textView6);
        //String email=tv.getText().toString();
        move=getActivity();


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}