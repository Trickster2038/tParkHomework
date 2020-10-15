package com.park.homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;



public class ShowFragment extends Fragment {

    String text;

    public ShowFragment(String txt) {
        this.text = txt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        TextView textView = view.findViewById(R.id.showTextView);
        textView.setText(text);
        if (Integer.parseInt(text) % 2 == 0)
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        else
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
        return  view;
    }



}
