package com.sammiegh.femcare.adepter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GridAdapter extends ArrayAdapter {
    int counterMestruo = 0;
    String moonphasetype;
    String numdayscycle;
    String numdaysmestruo;
    String numdaysovul;
    String pillstringdefault;
    String switchshodays;
    String switchshofutp;
    String switchshoint;
    String switchshomoon;
    String switchshoovul;
    String switchshopreg;
    String switchshowmed;
    String symptomsstringdefault;
    int activeUID;
    TextView cellNumber;
    int completo;
    int countMoon;
    int countPRIMOMESTRUO = 1000;
    String counterFineMese;
    String counterInizioMese;
    private Calendar currentDate;
    String date;
    Date dateActualDATA;
    String dateBefore;
    Date dateBeforeDATA;
    int dateFocusDAY;
    int dateFocusDAYOLD;
    int dateFocusMONTH;
    int dateFocusMONTHOLD;
    int dateFocusYEAR;
    int dateFocusYEAROLD;
    String datePrimoMestruo = "";
    Calendar datePrimoMestruoCALENDAR;
    Date datePrimoMestruoDATA;
    String dateUltimoMestruo = "";
    Date dateUltimoMestruoDATA;
    String dateNote;
    int daysCiclo;
    int daysMestruo;
    int daysOvul;
    int anInt;
    int daysmestruazioni;
    long daysokFINAL;
    long daysokFINALP;
    long daysokFirstFINAL;
    int daysovulation;
    JCGSQLiteHelper db;
    int diastolic;
    long diff;
    int diffDAYSIntFINAL;
    int diffDAYSIntFINALP;
    int diffDAYSIntFirstFINAL;
    long diffFirstMestruo;
    long diffP;
    TextView eventIndicator;
    float fianchi;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    int gommo;
    float height;
    int id;
    int idNote;
    int idPer;
    ImageView imageCalFertile;
    ImageView imageCalFlow;
    ImageView imageCalMoon;
    ImageView imageCalNote;
    ImageView imageCalPill;
    ImageView imageCalSex;
    int intimate;
    String key;
    String lasMestrNumDays;
    private LayoutInflater mInflater;
    int monthBefore;
    private List<Date> monthlyDates;
    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
    int pregnancy;
    int pressure;
    int rowsNumDateNote;
    int rowsNumDatePeriod;
    int rowsNumPillUid;
    int rowsNumSymptomsUid;
    String sDateKeyEvent;
    String secretnote;
    Note selectedNote;
    Period selectedPeriod;
    Settings selectedSettings;
    float seno;
    int sextimes;
    char symptomRecordCharCheck;
    String symptoms;
    int systolic;
    float temperature = 0.0f;
    int testgravidanza;
    int totRowPeriod;
    int type;
    int uid;
    int uidNote;
    int uidPer;
    String value;
    float vita;
    float weight = 0.0f;

    public GridAdapter(Context context, List<Date> monthlyDates2, Calendar currentDate2) {
        super(context, R.layout.single_cell_layout);
        monthlyDates = monthlyDates2;
        currentDate = currentDate2;
        mInflater = LayoutInflater.from(context);
        db = new JCGSQLiteHelper(context);
        activeUID = db.readActiveUID();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object valueOf;
        Object valueOf2;
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(5);
        int displayMonth = dateCal.get(2) + 1;
        int displayYear = dateCal.get(1);
        int currentMonth = currentDate.get(2) + 1;
        int currentYear = currentDate.get(1);
        Calendar calTODAY = Calendar.getInstance();
        calTODAY.get(6);
        int year = calTODAY.get(1);
        int month = calTODAY.get(2) + 1;
        int dayofmonth = calTODAY.get(5);
        calTODAY.getActualMaximum(5);
        calTODAY.getActualMinimum(5);
        StringBuilder append = new StringBuilder().append(displayYear).append("").append(displayMonth < 10 ? "0" + displayMonth : Integer.valueOf(displayMonth)).append("");

        if (dayValue < 10) {
            valueOf = "0" + dayValue;
        } else {
            valueOf = Integer.valueOf(dayValue);
        }
        String todayString = append.append(valueOf).toString();
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        db = new JCGSQLiteHelper(getContext());
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        switchshodays = db.readKeySetting(activeUID, "showdayscycle");
        switchshowmed = db.readKeySetting(activeUID, "showmedicines");
        switchshoovul = db.readKeySetting(activeUID, "showovulfertile");
        switchshopreg = db.readKeySetting(activeUID, "showpregnant");
        switchshoint = db.readKeySetting(activeUID, "showmintimate");
        switchshofutp = db.readKeySetting(activeUID, "showfutperiod");
        switchshomoon = db.readKeySetting(activeUID, "showmoonphases");
        counterFineMese = db.readKeySetting(activeUID, "dmestruofinemese");
        counterInizioMese = db.readKeySetting(activeUID, "dmestruoiniziomese");
        imageCalSex = (ImageView) view.findViewById(R.id.imgCalSex);
        imageCalPill = (ImageView) view.findViewById(R.id.imgCalPill);
        imageCalFertile = (ImageView) view.findViewById(R.id.imgCalFertile);
        imageCalFlow = (ImageView) view.findViewById(R.id.imgCalFlow);
        imageCalNote = (ImageView) view.findViewById(R.id.imgCalNote);
        imageCalMoon = (ImageView) view.findViewById(R.id.imageCalMoon);
        cellNumber = (TextView) view.findViewById(R.id.calendar_date_id);
        eventIndicator = (TextView) view.findViewById(R.id.event_id);
        String dateFocus2 = db.readKeySetting(activeUID, "tempdate");
        dateFocusYEAR = Integer.parseInt(dateFocus2.substring(0, 4));
        dateFocusMONTH = Integer.parseInt(dateFocus2.substring(4, 6));
        dateFocusDAY = Integer.parseInt(dateFocus2.substring(6, 8));
        String dateFocusOld = db.readKeySetting(activeUID, "olddate");
        dateFocusYEAROLD = Integer.parseInt(dateFocusOld.substring(0, 4));
        dateFocusMONTHOLD = Integer.parseInt(dateFocusOld.substring(4, 6));
        dateFocusDAYOLD = Integer.parseInt(dateFocusOld.substring(6, 8));
        totRowPeriod = db.countRowsAssPeriod(activeUID);
        if (totRowPeriod > 0) {
            datePrimoMestruo = db.selectFirstStartPeriod(activeUID);
            dateUltimoMestruo = db.selectLastStartPeriod(activeUID);
            lasMestrNumDays = db.selectLastMestruoPeriod(activeUID);
            try {
                datePrimoMestruoDATA = formatodata.parse(datePrimoMestruo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                dateUltimoMestruoDATA = formatodata.parse(dateUltimoMestruo);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            datePrimoMestruoCALENDAR = Calendar.getInstance();
            datePrimoMestruoCALENDAR.setTime(datePrimoMestruoDATA);
            diff = mDate.getTime() - dateUltimoMestruoDATA.getTime();
            daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            diffDAYSIntFINAL = (int) daysokFINAL;
        }
        StringBuilder append2 = new StringBuilder().append(displayYear).append("").append(displayMonth < 10 ? "0" + displayMonth : Integer.valueOf(displayMonth)).append("");
        if (dayValue < 10) {
            valueOf2 = "0" + dayValue;
        } else {
            valueOf2 = Integer.valueOf(dayValue);
        }
        sDateKeyEvent = append2.append(valueOf2).toString();
        if (switchshomoon.equals("1")) {
            countMoon = db.countMoonPhase(sDateKeyEvent);
            if (countMoon == 1) {
                moonphasetype = db.selectMoonPhase(sDateKeyEvent);
                if (moonphasetype.equals("")) {
                    imageCalMoon.setVisibility(View.GONE);
                }
                if (moonphasetype.equals("NM")) {
                    imageCalMoon.setImageResource(R.mipmap.ic_moon_nm);
                    imageCalMoon.setVisibility(View.VISIBLE);
                }
                if (moonphasetype.equals("FM")) {
                    imageCalMoon.setImageResource(R.mipmap.ic_moon_fm);
                    imageCalMoon.setVisibility(View.VISIBLE);
                }
                if (moonphasetype.equals("FQ")) {
                    imageCalMoon.setImageResource(R.mipmap.ic_moon_fq);
                    imageCalMoon.setVisibility(View.VISIBLE);
                }
                if (moonphasetype.equals("LQ")) {
                    imageCalMoon.setImageResource(R.mipmap.ic_moon_lq);
                    imageCalMoon.setVisibility(View.VISIBLE);
                }
            }
        }
        rowsNumDateNote = db.countRowsNote(activeUID, sDateKeyEvent);
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, sDateKeyEvent);
            initializeNote();
            imageCalNote.setVisibility(View.VISIBLE);
            if (switchshoint.equals("1")) {
                if (intimate == 1 && gommo == 1) {
                    imageCalSex.setImageResource(R.mipmap.ic_sex_gommo);
                    imageCalSex.setVisibility(View.VISIBLE);
                } else if (intimate == 1 && gommo == 0) {
                    imageCalSex.setImageResource(R.mipmap.ic_sex);
                    imageCalSex.setVisibility(View.VISIBLE);
                } else {
                    imageCalSex.setVisibility(View.INVISIBLE);
                }
            }
            if (switchshoovul.equals("1") && ovulationtest == 1) {
                imageCalFertile.setImageResource(R.mipmap.ic_cal_ovul_ins);
                imageCalFertile.setVisibility(View.VISIBLE);
            }
            rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
            rowsNumPillUid = db.countRowsPills(activeUID);
            symptomsstringdefault = fillString(rowsNumSymptomsUid, '0');
            pillstringdefault = fillString(rowsNumPillUid, '0');
            if (switchshowmed.equals("1")) {
                if (!pill.equals(pillstringdefault)) {
                    imageCalPill.setVisibility(View.VISIBLE);
                } else {
                    imageCalPill.setVisibility(View.INVISIBLE);
                }
            }
            if (symptoms.equals(symptomsstringdefault)) {
                imageCalFlow.setVisibility(View.INVISIBLE);
            } else {
                symptomRecordCharCheck = symptoms.charAt(0);
                if (symptomRecordCharCheck == '1') {
                    imageCalFlow.setImageResource(R.mipmap.ic_cal_flow_5);
                    imageCalFlow.setVisibility(View.VISIBLE);
                }
                if (symptomRecordCharCheck == '2') {
                    imageCalFlow.setImageResource(R.mipmap.ic_cal_flow_4);
                    imageCalFlow.setVisibility(View.VISIBLE);
                }
                if (symptomRecordCharCheck == '3') {
                    imageCalFlow.setImageResource(R.mipmap.ic_cal_flow_3);
                    imageCalFlow.setVisibility(View.VISIBLE);
                }
                if (symptomRecordCharCheck == '4') {
                    imageCalFlow.setImageResource(R.mipmap.ic_cal_flow_2);
                    imageCalFlow.setVisibility(View.VISIBLE);
                }
                if (symptomRecordCharCheck == '5') {
                    imageCalFlow.setImageResource(R.mipmap.ic_cal_flow_1);
                    imageCalFlow.setVisibility(View.VISIBLE);
                }
                if (symptomRecordCharCheck == '0') {
                    imageCalFlow.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            imageCalNote.setVisibility(View.INVISIBLE);
        }
        if (displayMonth == month && displayYear == year && dayValue == dayofmonth) {
            view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorToday)));
        }
        if (displayMonth == currentMonth && displayYear == currentYear) {
            ((TextView) view.findViewById(R.id.calendar_date_id)).setText(String.valueOf(dayValue));
            TextView eventIndicator2 = (TextView) view.findViewById(R.id.event_id);
            monthBefore = db.countBeforePeriodRow(activeUID, todayString);
            if (monthBefore > 0) {
                dateBefore = db.selectBeforePeriodRow(activeUID, todayString);
                selectedPeriod = db.readPeriod(activeUID, dateBefore);
                initializePeriod();
                try {
                    dateBeforeDATA = formatodata.parse(dateBefore);
                } catch (ParseException e3) {
                    e3.printStackTrace();
                }
                try {
                    dateActualDATA = formatodata.parse(todayString);
                } catch (ParseException e4) {
                    e4.printStackTrace();
                }
                diffP = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                daysokFINALP = TimeUnit.DAYS.convert(diffP, TimeUnit.MILLISECONDS);
                diffDAYSIntFINALP = ((int) daysokFINALP) + 1;
            }
            if (pregnancy == 1) {
                if (switchshodays.equals("1") && diffDAYSIntFINAL <= anInt) {
                    eventIndicator2.setText(String.valueOf(diffDAYSIntFINAL + 1));
                    imageCalFertile.setVisibility(View.VISIBLE);
                    imageCalFertile.setImageResource(R.mipmap.ic_gravidanza);
                }
            } else if (switchshodays.equals("1")) {
                rowsNumDatePeriod = db.countRowsPeriod(activeUID, todayString);
                if (diffDAYSIntFINAL > 0 || (diffDAYSIntFINAL == 0 && rowsNumDatePeriod == 1 && !datePrimoMestruo.equals(""))) {
                    numdayscycle = db.readKeySetting(activeUID, "n_cycle_days");
                    daysCiclo = Integer.parseInt(numdayscycle);
                    numdaysmestruo = db.readKeySetting(activeUID, "n_mestrual_days");
                    daysMestruo = Integer.parseInt(numdaysmestruo);
                    numdaysovul = db.readKeySetting(activeUID, "n_ovulation_days");
                    daysOvul = Integer.parseInt(numdaysovul);
                    counterMestruo = (diffDAYSIntFINAL % daysCiclo) + 1;
                    if (counterMestruo <= daysMestruo && switchshofutp.equals("1")) {
                        view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorFutureMestruo)));
                    }
                    rowsNumDatePeriod = db.countRowsPeriod(activeUID, todayString);
                    if (rowsNumDatePeriod == 1) {
                        selectedPeriod = db.readPeriod(activeUID, todayString);
                        initializePeriod();
                        countPRIMOMESTRUO = 1;
                    }
                    if (countPRIMOMESTRUO <= daysmestruazioni) {
                        view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorRossoMestruo)));
                        countPRIMOMESTRUO++;
                    }
                    eventIndicator2.setText(String.valueOf(counterMestruo));
                    if (switchshoovul.equals("1")) {
                        int diffLutealv = (daysCiclo - daysOvul) + 1;
                        int diffLutealvRange1 = (daysCiclo - daysOvul) + 2;
                        int diffLutealvRange2 = daysCiclo - daysOvul;
                        int diffLutealvRange3 = (daysCiclo - daysOvul) - 4;
                        if (counterMestruo == diffLutealvRange1) {
                            String ovmaxtemp = db.readKeySetting(activeUID, "ov1max");
                            if (!ovmaxtemp.equals("")) {
                                db.updateSettings(new Settings(id, activeUID, "ov2max", ovmaxtemp));
                                db.updateSettings(new Settings(id, activeUID, "ov1max", todayString));
                            } else {
                                db.updateSettings(new Settings(id, activeUID, "ov1max", todayString));
                            }
                        }
                        if (counterMestruo == diffLutealvRange3) {
                            String ovmintemp = db.readKeySetting(activeUID, "ov1min");
                            if (!ovmintemp.equals("")) {
                                db.updateSettings(new Settings(id, activeUID, "ov2min", ovmintemp));
                                db.updateSettings(new Settings(id, activeUID, "ov1min", todayString));
                            } else {
                                db.updateSettings(new Settings(id, activeUID, "ov1min", todayString));
                            }
                        }
                        if (counterMestruo == diffLutealv) {
                            imageCalFertile.setVisibility(View.VISIBLE);
                            imageCalFertile.setImageResource(R.mipmap.ic_cal_ovul);
                        }
                        if (counterMestruo == diffLutealvRange1) {
                            imageCalFertile.setVisibility(View.VISIBLE);
                            imageCalFertile.setImageResource(R.mipmap.ic_fertile);
                        }
                        if (counterMestruo <= diffLutealvRange2 && counterMestruo >= diffLutealvRange3) {
                            imageCalFertile.setVisibility(View.VISIBLE);
                            imageCalFertile.setImageResource(R.mipmap.ic_fertile);
                        }
                    }
                } else if (diffDAYSIntFINAL < 0 || (diffDAYSIntFINAL == 0 && rowsNumDatePeriod == 0 && !datePrimoMestruo.equals(""))) {
                    diffFirstMestruo = mDate.getTime() - datePrimoMestruoDATA.getTime();
                    daysokFirstFINAL = TimeUnit.DAYS.convert(diffFirstMestruo, TimeUnit.MILLISECONDS);
                    diffDAYSIntFirstFINAL = (int) daysokFirstFINAL;
                    if (diffDAYSIntFirstFINAL > 0) {
                        monthBefore = db.countBeforePeriodRow(activeUID, todayString);
                        if (monthBefore > 0) {
                            dateBefore = db.selectBeforePeriodRow(activeUID, todayString);
                            try {
                                dateBeforeDATA = formatodata.parse(dateBefore);
                            } catch (ParseException e5) {
                                e5.printStackTrace();
                            }
                            try {
                                dateActualDATA = formatodata.parse(todayString);
                            } catch (ParseException e6) {
                                e6.printStackTrace();
                            }
                            diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                            daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            diffDAYSIntFINAL = ((int) daysokFINAL) + 1;
                            rowsNumDatePeriod = db.countRowsPeriod(activeUID, todayString);
                            if (rowsNumDatePeriod == 1) {
                                diffDAYSIntFINAL = 1;
                            }
                            eventIndicator2.setText(String.valueOf(diffDAYSIntFINAL));
                            selectedPeriod = db.readPeriod(activeUID, dateBefore);
                            initializePeriod();
                            if (diffDAYSIntFINAL <= daysmestruazioni) {
                                view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorRossoMestruo)));
                            }
                            if (switchshoovul.equals("1")) {
                                int diffLutealv2 = (anInt - daysovulation) + 1;
                                int diffLutealvRange12 = (anInt - daysovulation) + 2;
                                int diffLutealvRange22 = anInt - daysovulation;
                                int diffLutealvRange32 = (anInt - daysovulation) - 4;
                                if (counterMestruo == diffLutealvRange12) {
                                    String ovmaxtemp2 = db.readKeySetting(activeUID, "ov1max");
                                    if (!ovmaxtemp2.equals("")) {
                                        db.updateSettings(new Settings(id, activeUID, "ov2max", ovmaxtemp2));
                                        db.updateSettings(new Settings(id, activeUID, "ov1max", todayString));
                                    } else {
                                        db.updateSettings(new Settings(id, activeUID, "ov1max", todayString));
                                    }
                                }
                                if (counterMestruo == diffLutealvRange32) {
                                    String ovmintemp2 = db.readKeySetting(activeUID, "ov1min");
                                    if (!ovmintemp2.equals("")) {
                                        db.updateSettings(new Settings(id, activeUID, "ov2min", ovmintemp2));
                                        db.updateSettings(new Settings(id, activeUID, "ov1min", todayString));
                                    } else {
                                        db.updateSettings(new Settings(id, activeUID, "ov1min", todayString));
                                    }
                                }
                                if (diffDAYSIntFINAL == diffLutealv2) {
                                    imageCalFertile.setVisibility(View.VISIBLE);
                                    imageCalFertile.setImageResource(R.mipmap.ic_cal_ovul);
                                }
                                if (diffDAYSIntFINAL == diffLutealvRange12) {
                                    imageCalFertile.setVisibility(View.VISIBLE);
                                    imageCalFertile.setImageResource(R.mipmap.ic_fertile);
                                }
                                if (diffDAYSIntFINAL <= diffLutealvRange22 && diffDAYSIntFINAL >= diffLutealvRange32) {
                                    imageCalFertile.setVisibility(View.VISIBLE);
                                    imageCalFertile.setImageResource(R.mipmap.ic_fertile);
                                }
                            }
                        } else if (monthBefore == 0) {
                            eventIndicator2.setText(String.valueOf(diffDAYSIntFirstFINAL + 1));
                            selectedPeriod = db.readPeriod(activeUID, dateBefore);
                            initializePeriod();
                            if (diffDAYSIntFINAL <= daysmestruazioni) {
                                view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorRossoMestruo)));
                            }
                        }
                    } else if (diffDAYSIntFirstFINAL == 0) {
                        rowsNumDatePeriod = db.countRowsPeriod(activeUID, todayString);
                        if (rowsNumDatePeriod == 1) {
                            eventIndicator2.setText("1");
                            view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorRossoMestruo)));
                        }
                    }
                }
            }
            Calendar.getInstance();
        } else {
            TextView cellNumber2 = (TextView) view.findViewById(R.id.calendar_date_id);
            cellNumber2.setText(String.valueOf(dayValue));
            cellNumber2.setTextColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorTextOutMonth)));
            cellNumber2.setTypeface((Typeface) null, Typeface.NORMAL);
        }
        if (displayMonth == dateFocusMONTH && displayYear == dateFocusYEAR && dayValue == dateFocusDAY) {
            view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorDisplay)));
        }
        if (!dateFocusOld.equals("") && !dateFocusOld.equals(dateFocus2) && (!(displayMonth == month && displayYear == year && dayValue == dayofmonth) && displayMonth == dateFocusMONTHOLD && displayYear == dateFocusYEAROLD && dayValue == dateFocusDAYOLD)) {
            if (counterMestruo > daysMestruo + 1 || counterMestruo <= 0) {
                view.setBackgroundColor(0);
            } else if (switchshofutp.equals("1")) {
                view.setBackgroundColor(Color.parseColor("#" + getContext().getResources().getString(R.string.colorFutureMestruo)));
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    public void initializePeriod() {
        idPer = selectedPeriod.getId();
        uidPer = selectedPeriod.getUid();
        type = selectedPeriod.getType();
        date = selectedPeriod.getDate();
        completo = selectedPeriod.getCompleto();
        daysmestruazioni = selectedPeriod.getDaysMestruazioni();
        anInt = selectedPeriod.getDaysCiclo();
        daysovulation = selectedPeriod.getDaysOvulation();
        pregnancy = selectedPeriod.getPregnancy();
    }

    public void initializeNote() {
        idNote = selectedNote.getId();
        uidNote = selectedNote.getUid();
        dateNote = selectedNote.getDate();
        note = selectedNote.getNotes();
        secretnote = selectedNote.getSecretNotes();
        symptoms = selectedNote.getSymptoms();
        mucus = selectedNote.getMucus();
        moods = selectedNote.getMoods();
        intimate = selectedNote.getIntimate();
        gommo = selectedNote.getGommo();
        sextimes = selectedNote.getSextimes();
        numorgasm = selectedNote.getNumorgasm();
        pill = selectedNote.getPill();
        ovulationtest = selectedNote.getOvulationtest();
        temperature = selectedNote.getTemperature();
        weight = selectedNote.getWeight();
        height = selectedNote.getHeight();
        seno = selectedNote.getSeno();
        vita = selectedNote.getVita();
        fianchi = selectedNote.getFianchi();
        systolic = selectedNote.getSystolic();
        diastolic = selectedNote.getDiastolic();
        pressure = selectedNote.getPressure();
        testgravidanza = selectedNote.getTestgravidanza();
    }

    public static String fillString(int count, char c) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
