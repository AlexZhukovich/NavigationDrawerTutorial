package com.alexzh.navigationdrawertutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CountryFragment extends Fragment {

    private final static String COUNTRY = "country";

    public static Fragment newInstance(String country) {
        Fragment fragment = new CountryFragment();

        Bundle args = new Bundle();
        args.putString(COUNTRY, country);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        TextView countryTextView = (TextView) view.findViewById(R.id.country_textView);
        countryTextView.setText(getArguments().getString(COUNTRY));

        return view;
    }

}
