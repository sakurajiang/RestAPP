package com.example.jdk.restapp.CustomizedWidget;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;


/**
 * Created by JDK on 2016/8/16.
 */
public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    private ProgressDialog pd;
    private ProgressDialog materialDialog;
    private Context context;
    private boolean cancelable;
    private progressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, progressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }
    private void initMa(){
        if (materialDialog == null) {
            materialDialog=new ProgressDialog(context);
            materialDialog.setCancelable(cancelable);
            if (cancelable) {
                materialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!materialDialog.isShowing()) {
                materialDialog.show();
            }
        }
    }
    private void initProgressDialog(){
        if (pd == null) {
            pd = new ProgressDialog(context);
//            WindowManager.LayoutParams params = pd.getWindow().getAttributes();
//            pd.getWindow().setGravity(Gravity.BOTTOM);
            pd.dismiss();
            pd.setCancelable(cancelable);
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }
    private void dis(){
        if(materialDialog!=null){
            materialDialog.dismiss();
            materialDialog=null;
        }
    }
    private void dismissProgressDialog(){
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
//                initMa();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
//                dis();
                break;
        }
    }


}
