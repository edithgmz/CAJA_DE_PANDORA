package edith.gomez.cajadepandora.ui.finanzas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edith.gomez.cajadepandora.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GastosFrag extends Fragment {


    public GastosFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gastos, container, false);
    }

}