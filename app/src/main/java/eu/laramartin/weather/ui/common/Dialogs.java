package eu.laramartin.weather.ui.common;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import eu.laramartin.weather.R;
import eu.laramartin.weather.ui.cities.CitiesListPresenter;

/**
 * Created by Lara on 19/11/2016.
 */

public class Dialogs {

    public static void showInputCityDialog(Context context, final CitiesListPresenter presenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog_add_city, null);
        final EditText editText = ButterKnife.findById(view, R.id.input_edit_text_add_city);
        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String inputCity = editText.getText().toString().trim();
                presenter.addCityIfExists(inputCity);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showCityNotFoundDialog(Context context, final CitiesListPresenter presenter,
                                              String inputCity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog_add_city, null);
        builder.setMessage(context.getString(R.string.city_not_found_message, inputCity));
        final EditText editText = ButterKnife.findById(view, R.id.input_edit_text_add_city);
        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String inputCity = editText.getText().toString().trim();
                presenter.addCityIfExists(inputCity);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showErrorServerDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.error_server_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showDeleteCityConfirmationDialog(Context context, final int itemId, final CitiesListPresenter presenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_city_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteItem(itemId);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showPreferencesItemListDialog(Context context,
                                                     @StringRes int title,
                                                     @ArrayRes int unitsStringArray,
                                                     DialogInterface.OnClickListener onClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(unitsStringArray, onClickListener)
                .create();
        alertDialog.show();
    }
}
