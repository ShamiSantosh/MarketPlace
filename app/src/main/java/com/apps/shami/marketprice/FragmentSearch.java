package com.apps.shami.marketprice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSearch.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentSearch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    AutoCompleteTextView productTextView;
    AutoCompleteTextView marketTextView;
    ImageButton productButton;
    ImageButton marketbutton;
    String productselected;
    String marketselected;
    Button buttonReset;
    Button buttonSearch;
    TableLayout tableLayout;
    static String product;
    static String market;
    static int flag1 = 0;
    static int flag2 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View SearchFragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        productTextView = (AutoCompleteTextView) SearchFragmentView.findViewById(R.id.product_autoCompleteTextView);
        marketTextView = (AutoCompleteTextView) SearchFragmentView.findViewById(R.id.market_autoCompleteTextView);
        productButton = (ImageButton) SearchFragmentView.findViewById(R.id.productButton);
        marketbutton = (ImageButton) SearchFragmentView.findViewById(R.id.marketButton);
        tableLayout = (TableLayout) SearchFragmentView.findViewById(R.id.table1);

        String[] arrayProducts = {  "Apple", "Avocado", "Banana",
                "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
                "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
        String[] arrayMarkets = {"Karnataka", "Tamilnadu", "Maharashtra",
                "Andhra Pradesh", "Kerala","Gujarat","Rajasthan"};

        // Autocomplete Text
        ArrayAdapter<String> productAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, arrayProducts);

        ArrayAdapter<String> marketAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, arrayMarkets);

        productTextView.setAdapter(productAdapter);
        productTextView.setThreshold(1);

        marketTextView.setAdapter(marketAdapter);
        marketTextView.setThreshold(1);

        //Click Image Button for Product List

        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ShowProducts.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        productselected = getActivity().getIntent().getStringExtra("product_key");

        if(productselected != null )
        {
            product = productselected;

        }
        
        productTextView.setText(product);


        //Click Image Button for Market List
        marketbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ShowMarkets.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        marketselected = getActivity().getIntent().getStringExtra("market_key");
        if(marketselected != null )
        {
            market = marketselected;

        }

        marketTextView.setText(market);

        // Reset Button
        buttonReset = (Button) SearchFragmentView.findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productTextView.setText("");
                marketTextView.setText("");
                market = null;
                product = null;
            }
        });

        // Search Button
        tableLayout.setVisibility(View.INVISIBLE);
        buttonSearch = (Button) SearchFragmentView.findViewById(R.id.search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productTextView.getText().toString().equals("") || marketTextView.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter Product and Market", Toast.LENGTH_LONG).show();
                    return;
                }
                tableLayout.setVisibility(View.VISIBLE);
            }
        });
        return SearchFragmentView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // not cleaning up.
        if(productTextView.getText().toString().equals("")){
            product = null;
        }
        else{
            product = productTextView.getText().toString();
        }
        if(marketTextView.getText().toString().equals("")){
            market= null;
        }
        else{
            market = marketTextView.getText().toString();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
