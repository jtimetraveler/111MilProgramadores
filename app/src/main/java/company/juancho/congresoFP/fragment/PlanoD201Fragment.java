package company.juancho.congresoFP.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;

import company.juancho.congresoFP.R;
import company.juancho.congresoFP.tools.TouchImageView;


public class PlanoD201Fragment extends Fragment {
    private TouchImageView imagen;
    private DecimalFormat df;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_plano_d201, container, false);


    }

    public void imagenTouch(){

    }


}
