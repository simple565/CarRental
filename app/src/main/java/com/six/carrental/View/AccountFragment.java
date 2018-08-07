package com.six.carrental.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.six.carrental.R;

import java.nio.file.FileVisitOption;
import java.util.Objects;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        super ();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_account, container, false );
        iniView ( view );
        return view;
    }

    private void iniView(View view) {
        RelativeLayout userInfo = view.findViewById ( R.id.account_userInfo );
        userInfo.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getActivity (), SetInfoActivity.class ) );
            }
        } );

        RelativeLayout myReservation = view.findViewById ( R.id.account_reservation );
        myReservation.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getActivity (), MyReservationActivity.class ) );
            }
        } );
        RelativeLayout myPublish = view.findViewById ( R.id.account_publish );
        myPublish.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getActivity (), PublishActivity.class ) );
            }
        } );
        RelativeLayout about = view.findViewById ( R.id.account_aboutUs );

        Button logout = view.findViewById ( R.id.account_logout );
        logout.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( getActivity (), LoginActivity.class ) );
                Objects.requireNonNull ( getActivity () ).finish ();
            }
        } );

    }
}
