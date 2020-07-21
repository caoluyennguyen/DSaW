package com.nguyenhongphuc98.dsaw.ui.admin_warning;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.nguyenhongphuc98.dsaw.MainActivity;
import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Warning;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AdminWarning extends Fragment {
    private AdminWarningViewModel mViewModel;

    Switch mSwitch;
    LinearLayout mCmndLayout;
    EditText mTextContent;
    EditText mTextCmnd;
    Button mButton;

    public static AdminWarning newInstance() {
        return new AdminWarning();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_warning, container, false);
        InitComponent(view);
        InitEvent();
        Log.d("show", "View created");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("show", "View created");
        super.onActivityCreated(savedInstanceState);
        createNotificationChannel();
        mViewModel = ViewModelProviders.of(this).get(AdminWarningViewModel.class);
        // TODO: Use the ViewModel
        RegisterDataLiveListener();
    }

    public void InitComponent(View view)
    {
        mSwitch = (Switch) view.findViewById(R.id.stateSwitch);
        mCmndLayout = view.findViewById(R.id.cmnd);
        mTextContent = view.findViewById(R.id.contentEdit);
        mTextCmnd = view.findViewById(R.id.cmndEdit);
        mButton = view.findViewById(R.id.summit_warning_button);
    }

    public void InitEvent()
    {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Button is clicked",Toast.LENGTH_SHORT).show();

                mViewModel.setContent(mTextContent.getText().toString());
                Warning warning = new Warning("Cảnh báo nguy hiểm", mTextContent.getText().toString(), DataCenter.currentUser.getUsername());

                //mViewModel.CreateWarning(warning);

                /*Notification notification = new NotificationCompat.Builder(getContext(), "CHANNEL_ID")
                        .setSmallIcon(R.drawable.warning_icon)
                        .setContentTitle("Canh bao tu luyenprocool")
                        .setContentText(mTextContent.getText())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();*/
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "CHANNEL_ID")
                        .setSmallIcon(R.drawable.warning_icon)
                        .setContentTitle("Cảnh báo nguy hiểm")
                        .setContentText(mTextContent.getText())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, builder.build());

                Log.d("show", "Warning created");

                // Get token
                // [START retrieve_current_token]
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }

                                // Get new Instance ID token
                                String token = task.getResult().getToken();

                                // Log and toast
                                String msg = getString(R.string.msg_token_fmt, token);
                                Log.d(TAG, msg);
                                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                // [END retrieve_current_token]
            }
        });

        mSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) mCmndLayout.setVisibility(View.VISIBLE);
                else mCmndLayout.setVisibility(View.GONE);
            }
        });
    }

    public void RegisterDataLiveListener() {
        mViewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextContent.setText(String.valueOf(s));
            }
        });
        mViewModel.getCMND().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextCmnd.setText(String.valueOf(s));
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
