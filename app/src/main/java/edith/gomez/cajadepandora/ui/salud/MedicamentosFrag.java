package edith.gomez.cajadepandora.ui.salud;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edith.gomez.cajadepandora.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentosFrag extends Fragment {


    public MedicamentosFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicamentos, container, false);
    }

}
