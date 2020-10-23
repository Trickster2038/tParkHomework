package com.park.homework;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private RecyclerView listNumber;
    private Button addButton;
    public int elemCnt= 100;
    private ArrayList<Data> resource = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter(resource);
    private final String CNT_STATE="savedInt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null ) {
            elemCnt = savedInstanceState.getInt(CNT_STATE);
        }
        resource.clear();

        for (int j = 1; j <= elemCnt; j++)
            resource.add(new Data(Integer.toString(j)));

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addButton = view.findViewById(R.id.addBtn);
        listNumber = view.findViewById(R.id.recycler);
        listNumber.setAdapter(adapter);

        int col;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) col = getResources().getInteger(R.integer.landscapeCols);
        else col = getResources().getInteger(R.integer.portraitCols);

        listNumber.setLayoutManager(new GridLayoutManager(view.getContext(), col));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elemCnt++;
                resource.add(new Data(Integer.toString(elemCnt)));
                adapter.notifyItemInserted(elemCnt);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CNT_STATE, elemCnt);

        super.onSaveInstanceState(outState);
    }


    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private ArrayList<Data> data;

        public MyAdapter(ArrayList dataArg) {
            this.data = dataArg;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_single, parent, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.numberView.setText(data.get(position).numberText);
            if (position % 2 == 0)
                holder.numberView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
            else
                holder.numberView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView numberView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.singleTextView);
            numberView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = numberView.getText().toString();
                    Fragment numFragment = new ShowFragment(txt);

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.replace(R.id.frameFragLayout, numFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        }
    }

    class Data {
        String numberText;

        public Data(String numberTxt) {
            this.numberText = numberTxt;
        }
    }




}