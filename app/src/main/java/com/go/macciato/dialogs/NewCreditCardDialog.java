package com.go.macciato.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.go.macciato.R;
import com.go.macciato.model.CreditCard;

/**
 * Created by MAV1GA on 21/04/2017.
 */

public class NewCreditCardDialog {

    public static Dialog build(Context context,int resTitle, int resMessage,
                                             final RequiredNewCreditCardOps callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.dialog_new_card, null);
        final EditText edtCardName = (EditText)dialogView.findViewById(R.id.edt_credit_card_name);
        builder.setView(dialogView)
                .setTitle(context.getString(resTitle))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cardName = edtCardName.getText().toString();
                        CreditCard card = new CreditCard();
                        card.setCardName(cardName);
                        callback.onNewCreditCard(card);
                    }
                });
        return builder.create();
    }

    public interface RequiredNewCreditCardOps{
        void onNewCreditCard(CreditCard card);
    }

}
