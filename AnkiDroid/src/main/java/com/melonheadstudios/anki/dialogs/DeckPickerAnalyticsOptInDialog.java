package com.melonheadstudios.anki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melonheadstudios.anki.AnkiDroidApp;
import com.melonheadstudios.anki.DeckPicker;
import com.melonheadstudios.anki.R;
import com.melonheadstudios.anki.analytics.AnalyticsDialogFragment;
import com.melonheadstudios.anki.analytics.UsageAnalytics;

public class DeckPickerAnalyticsOptInDialog extends AnalyticsDialogFragment {
    public static DeckPickerAnalyticsOptInDialog newInstance() {
        DeckPickerAnalyticsOptInDialog f = new DeckPickerAnalyticsOptInDialog();
        return f;
    }

    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        return new MaterialDialog.Builder(getActivity())
                .title(res.getString(R.string.analytics_dialog_title))
                .content(res.getString(R.string.analytics_summ))
                .checkBoxPrompt(res.getString(R.string.analytics_title), true, null)
                .positiveText(res.getString(R.string.dialog_continue))
                .onPositive((dialog, which) -> {
                    AnkiDroidApp.getSharedPrefs(getContext()).edit()
                            .putBoolean(UsageAnalytics.ANALYTICS_OPTIN_KEY, dialog.isPromptCheckBoxChecked())
                            .apply();
                    ((DeckPicker) getActivity()).dismissAllDialogFragments();
                })
                .cancelable(true)
                .cancelListener(dialog -> ((DeckPicker) getActivity()).dismissAllDialogFragments())
                .show();
    }
}
