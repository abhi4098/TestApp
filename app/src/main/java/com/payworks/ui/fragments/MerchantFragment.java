package com.payworks.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payworks.R;
import com.payworks.ui.activities.EditProfileActivity;
import com.payworks.ui.activities.MyDonationsActivity;
import com.payworks.ui.activities.MyInvoicesActivity;
import com.payworks.ui.activities.MyProductActivity;
import com.payworks.ui.activities.MySubscriptionsActivity;
import com.payworks.ui.activities.MyTicketsActivity;
import com.payworks.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Abhinandan on 18/8/17.
 */
public class MerchantFragment extends Fragment {

    private static final String TAG = LogUtils.makeLogTag(MerchantFragment.class);

    @OnClick(R.id.my_product)
    public void myProduct() {
        Intent activityChangeIntent = new Intent(getActivity(), MyProductActivity.class);
        startActivity(activityChangeIntent);
    }

    @OnClick(R.id.my_donation)
    public void myDonation() {
        Intent activityChangeIntent = new Intent(getActivity(), MyDonationsActivity.class);
        startActivity(activityChangeIntent);
    }

    @OnClick(R.id.my_tickets)
    public void myTicket() {
        Intent activityChangeIntent = new Intent(getActivity(), MyTicketsActivity.class);
        startActivity(activityChangeIntent);
    }

    @OnClick(R.id.my_subscription)
    public void mySubscriptions() {
        Intent activityChangeIntent = new Intent(getActivity(), MySubscriptionsActivity.class);
        startActivity(activityChangeIntent);
    }

    @OnClick(R.id.my_invoices)
    public void myInvoices() {
        Intent activityChangeIntent = new Intent(getActivity(), MyInvoicesActivity.class);
        startActivity(activityChangeIntent);
    }

    public MerchantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_merchant, container, false);
        ButterKnife.bind(this,rootView);
       // setUpRestAdapter();
       // getMyProfileDetails();
        return rootView;
    }



}
