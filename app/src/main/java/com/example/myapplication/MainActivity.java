package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ChipGroup chipGroup;
    String[] languageList = {"हिन्दी", "ગુજરાતી", "ENGLISH", "मराठी", "தமிழ் (Tamil)",
            "తెలుగు (Telugu)", "ಕನ್ನಡ (Kannada)", "বাংলা (Bengali)", "संस्कृत"};

    //get selected language list
    ArrayList<String> selectedChipItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipGroup = findViewById(R.id.chip_group_main);

        AddItemsInChipGroup();
    }

    public void AddItemsInChipGroup() {
        for (int i = 0; i < languageList.length; i++) {
            chipGroup = findViewById(R.id.chip_group_main);
            Chip entryChip2 = getChip(languageList[i]);
            entryChip2.setId(i);

            //set default selected language
            //entryChip2.setChecked(true);

            chipGroup.addView(entryChip2);
        }
    }

    private Chip getChip(String text) {
        final Chip chip = new Chip(this);
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.my_chip));
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50,
                getResources().getDisplayMetrics()
        );
        chip.setChipBackgroundColorResource(R.color.unselectedColor);
        chip.setTextColor(getResources().getColor(R.color.black));
        chip.setCloseIconVisible(false);
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chip.setChipBackgroundColor(getColorStateList(R.color.selectedColor));
                    chip.setTextColor(getResources().getColor(R.color.white));
                    chip.setChecked(true);
                    selectedChipItems.add(chip.getText().toString());
                } else {
                    chip.setChipBackgroundColor(getColorStateList(R.color.unselectedColor));
                    chip.setTextColor(getResources().getColor(R.color.black));
                    chip.setChecked(false);
                    selectedChipItems.remove(chip.getText().toString());
                }
            }
        });
        return chip;
    }
}