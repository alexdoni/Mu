package com.xsdk.ab_firstbase.statisics.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.facebook.internal.security.CertificateUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes3.dex */
public class DatePickerDialogUtil implements DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private final Activity mActivity;
    private DatePicker mDateChoose;
    private String mDateTime;
    private AlertDialog mDialog;
    private String mInitDateTime;
    private TimePicker mTimeChoose;
    private String showDate;
    private String tempDate;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public boolean isShow = false;

    public DatePickerDialogUtil(Activity activity, String str) {
        this.mActivity = activity;
        this.mInitDateTime = str;
    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (datePicker == null || "".equals(this.mInitDateTime)) {
            this.mInitDateTime = calendar.get(5) + RemoteSettings.FORWARD_SLASH_STRING + calendar.get(2) + RemoteSettings.FORWARD_SLASH_STRING + calendar.get(1);
        }
        if (!TextUtils.isEmpty(this.tempDate)) {
            try {
                Date parse = new SimpleDateFormat("dd/MM/yyyy").parse(this.tempDate);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(parse);
                init(calendar2);
                return;
            } catch (Exception unused) {
                init(calendar);
                return;
            }
        }
        init(calendar);
    }

    private void init(Calendar calendar) {
        this.mDateChoose.init(calendar.get(1), calendar.get(2), calendar.get(5), this);
        this.mTimeChoose.setCurrentHour(Integer.valueOf(calendar.get(11)));
        this.mTimeChoose.setCurrentMinute(Integer.valueOf(calendar.get(12)));
    }

    public AlertDialog datePickerDialog(final TextView textView) {
        this.tempDate = textView.getText().toString().trim();
        LinearLayout linearLayout = (LinearLayout) this.mActivity.getLayoutInflater().inflate(Util.getIdByName(this.mActivity, "layout", "drhw_datepicker_layout"), (ViewGroup) null);
        DatePicker datePicker = (DatePicker) linearLayout.findViewById(Util.getIdByName(this.mActivity, "id", "date_choose"));
        this.mDateChoose = datePicker;
        datePicker.setCalendarViewShown(false);
        this.mTimeChoose = (TimePicker) linearLayout.findViewById(Util.getIdByName(this.mActivity, "id", "time_choose"));
        Button button = (Button) linearLayout.findViewById(Util.getIdByName(this.mActivity, "id", "btn_ok"));
        Button button2 = (Button) linearLayout.findViewById(Util.getIdByName(this.mActivity, "id", "btn_cancel"));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.xsdk.ab_firstbase.statisics.util.DatePickerDialogUtil.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                textView.setText(DatePickerDialogUtil.this.mDateTime);
                DatePickerDialogUtil.this.isShow = false;
                DatePickerDialogUtil.this.mDialog.cancel();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.xsdk.ab_firstbase.statisics.util.DatePickerDialogUtil.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DatePickerDialogUtil.this.isShow = false;
                DatePickerDialogUtil.this.mDialog.cancel();
            }
        });
        init(this.mDateChoose, this.mTimeChoose);
        this.mTimeChoose.setIs24HourView(true);
        this.mTimeChoose.setOnTimeChangedListener(this);
        this.showDate = this.sdf.format(Long.valueOf(System.currentTimeMillis()));
        AlertDialog show = new AlertDialog.Builder(this.mActivity).setTitle(this.mInitDateTime).setView(linearLayout).show();
        this.mDialog = show;
        show.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.xsdk.ab_firstbase.statisics.util.DatePickerDialogUtil.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                DatePickerDialogUtil.this.isShow = false;
            }
        });
        try {
            this.mDialog.getWindow().setLayout(DensityUtil.dip2px(this.mActivity, 350.0f), -2);
        } catch (Exception unused) {
        }
        onDateChanged(null, 0, 0, 0);
        return this.mDialog;
    }

    @Override // android.widget.TimePicker.OnTimeChangedListener
    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        onDateChanged(null, 0, 0, 0);
    }

    @Override // android.widget.DatePicker.OnDateChangedListener
    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.mDateChoose.getYear(), this.mDateChoose.getMonth(), this.mDateChoose.getDayOfMonth(), this.mTimeChoose.getCurrentHour().intValue(), this.mTimeChoose.getCurrentMinute().intValue());
        String format = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
        this.mDateTime = format;
        this.mDialog.setTitle(format);
    }

    public static String spliteString(String str, String str2, String str3, String str4) {
        int lastIndexOf;
        if (str3.equalsIgnoreCase(FirebaseAnalytics.Param.INDEX)) {
            lastIndexOf = str.indexOf(str2);
        } else {
            lastIndexOf = str.lastIndexOf(str2);
        }
        if (str4.equalsIgnoreCase("front")) {
            if (lastIndexOf != -1) {
                return str.substring(0, lastIndexOf);
            }
            if (lastIndexOf != -1) {
                return str.substring(lastIndexOf + 1, str.length());
            }
        }
        return "";
    }

    private Calendar getCalendarByInitDate(String str) {
        Calendar calendar = Calendar.getInstance();
        String spliteString = spliteString(this.mInitDateTime, "日", FirebaseAnalytics.Param.INDEX, "front");
        String spliteString2 = spliteString(this.mInitDateTime, "日", FirebaseAnalytics.Param.INDEX, "back");
        String spliteString3 = spliteString(spliteString, "年", FirebaseAnalytics.Param.INDEX, "front");
        String spliteString4 = spliteString(spliteString, "年", FirebaseAnalytics.Param.INDEX, "back");
        calendar.set(Integer.valueOf(spliteString3.trim()).intValue(), Integer.valueOf(spliteString(spliteString4, "月", FirebaseAnalytics.Param.INDEX, "front").trim()).intValue(), Integer.valueOf(spliteString(spliteString4, "月", FirebaseAnalytics.Param.INDEX, "back").trim()).intValue(), Integer.valueOf(spliteString(spliteString2, CertificateUtil.DELIMITER, FirebaseAnalytics.Param.INDEX, "front").trim()).intValue(), Integer.valueOf(spliteString(spliteString2, CertificateUtil.DELIMITER, FirebaseAnalytics.Param.INDEX, "back").trim()).intValue());
        return calendar;
    }
}
