package sg.edu.rp.webservices.p11_mydatabook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bioFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnEditBio;
    TextView tvbio;
    EditText etData;

    public bioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static bioFragment newInstance(String param1, String param2) {
        bioFragment fragment = new bioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        pref.getString("bio", null);

        View v = inflater.inflate(R.layout.biofragment, container, false);
        final View viewDialog = inflater.inflate(R.layout.dialog, null);

        etData = viewDialog.findViewById(R.id.etEdit);
        btnEditBio = v.findViewById(R.id.btnEditBio);
        tvbio = v.findViewById(R.id.tvBio);
        btnEditBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(getContext());
                myBuilder.setTitle("Edit Bio");
                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("Cancel", null);
                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String data = etData.getText().toString();
                        tvbio.setText(data);
                    }
                });
                AlertDialog alertDialog = myBuilder.create();
                alertDialog.show();

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("ann", tvbio.getText().toString());
                editor.commit();
            }
        });

        return v;
    }

    public void reload(){
        SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0);
        String bio = pref.getString("bio", null);
        tvbio.setText(bio);
    }

}