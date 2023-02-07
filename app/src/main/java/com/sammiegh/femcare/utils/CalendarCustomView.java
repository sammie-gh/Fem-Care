package com.sammiegh.femcare.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sammiegh.femcare.model.MoodNote;
import com.sammiegh.femcare.model.MucusNote;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.SymptomNote;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.activity.CustomCalendarActivity;
import com.sammiegh.femcare.activity.LegendaCalendarActivity;
import com.sammiegh.femcare.activity.NoteActivity;
import com.sammiegh.femcare.adepter.GridAdapter;
import com.sammiegh.femcare.adepter.MoodNoteAdapter;
import com.sammiegh.femcare.adepter.MucusNoteAdapter;
import com.sammiegh.femcare.adepter.SymptomNoteAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalendarCustomView extends LinearLayout {
    private static final String TAG = "CALLO";
    private int maxCalendarColumn = 35;
    String moodsstringdefault;
    String mucusstringdefault;
    String notaStringFINAL = "";
    String pillstringdefault;
    String switchshodays;
    String switchshofutp;
    String switchshoint;
    String switchshoovul;
    String switchshopreg;
    String switchshowmed;
    String symptomsstringdefault;
    int activeUID;
    private TextView addNote;
    String answer;
    int avatar;

    public Calendar cal;
    private GridView calendarGridView;
    int completo;

    public Context context;
    private TextView currentDate;
    String date;
    String dateNote;
    int dayClick;
    int daysciclo;
    int daysmestruazioni;
    long daysokFINALov1max;
    long daysokFINALov1min;
    long daysokFINALov2max;
    long daysokFINALov2min;
    int daysovulation;
    JCGSQLiteHelper db;
    int delta;
    int diastolic;
    int diffDAYSIntFINALov1max;
    int diffDAYSIntFINALov1min;
    int diffDAYSIntFINALov2max;
    int diffDAYSIntFINALov2min;
    long diffov1max;
    long diffov1min;
    long diffov2max;
    long diffov2min;
    String email;
    float fianchi;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatodataView;
    private SimpleDateFormat formatter;
    int gommo;
    float height;
    int id;
    int id_note;
    int idPer;
    private ImageView imgAddNote;
    private ImageView imgLegenda;
    String initDate;
    String initLanguage;
    String initWeek;
    int intimate;
    String key;
    LinearLayout lNote;
    LinearLayout lNoteHide;
    private GridAdapter mAdapter;
    int monthClick;
    private ListView moodNoteItems;
    String moods;
    String mucus;
    private ListView mucusNoteItems;
    private ImageView nextButton;
    String note;
    int numorgasm;
    Date oggiDateCheck;
    String ov1max;
    Date ov1maxDATA;
    String ov1min;
    Date ov1minDATA;
    String ov2max;
    Date ov2maxDATA;
    String ov2min;
    Date ov2minDATA;
    int ovulationtest;
    String password;
    String pill;
    String popolaDate;
    int pregnancy;
    int pressure;
    private ImageView previousButton;
    String question;
    int rowsNumDateNote;
    int rowsNumDatePeriod;
    int rowsNumMoodUid;
    int rowsNumMucusUid;
    int rowsNumPillUid;
    int rowsNumSymptomsUid;
    String sDateKey;
    String sOggiDateCheck;
    String secretnote;
    Date selectedDATA;
    Note selectedNote;
    Period selectedPeriod;
    Settings selectedSettings;
    User selectedUser;
    float seno;
    int sextimes;
    int status;
    private ListView symptomNoteItems;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    int theme;
    TextView txtData;
    TextView txtDay1;
    TextView txtDay2;
    TextView txtDay3;
    TextView txtDay4;
    TextView txtDay5;
    TextView txtDay6;
    TextView txtDay7;
    TextView txtFertilityLevel;
    TextView txtNote;
    int type;
    int uid;
    int uidNote;
    int uidPer;
    String username;
    String value;
    float vita;
    float weight;
    int yearClick;

    public CalendarCustomView(Context context2) {
        super(context2);
    }

    public CalendarCustomView(Context context2, AttributeSet attrs) {
        super(context2, attrs);
        this.context = context2;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        getLegendaButtonClickEvent();
        getNOTAButtonClickEvent();
        getNOTAPoppoClickEvent();
    }

    public CalendarCustomView(Context context2, AttributeSet attrs, int defStyleAttr) {
        super(context2, attrs, defStyleAttr);
    }

    private void initializeUILayout() {
        Context context2 = this.context;
        Context context3 = this.context;
        View view = ((LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.calendar_layout, this);
        this.previousButton = (ImageView) view.findViewById(R.id.previous_month);
        this.nextButton = (ImageView) view.findViewById(R.id.next_month);
        this.currentDate = (TextView) view.findViewById(R.id.display_current_date);
        this.calendarGridView = (GridView) view.findViewById(R.id.calendar_grid);
        this.txtFertilityLevel = (TextView) view.findViewById(R.id.textFertilityLevel);
        this.txtData = (TextView) view.findViewById(R.id.txtData);
        this.txtNote = (TextView) view.findViewById(R.id.txtNoteView);
        this.txtDay1 = (TextView) view.findViewById(R.id.day1);
        this.txtDay2 = (TextView) view.findViewById(R.id.day2);
        this.txtDay3 = (TextView) view.findViewById(R.id.day3);
        this.txtDay4 = (TextView) view.findViewById(R.id.day4);
        this.txtDay5 = (TextView) view.findViewById(R.id.day5);
        this.txtDay6 = (TextView) view.findViewById(R.id.day6);
        this.txtDay7 = (TextView) view.findViewById(R.id.day7);
        this.imgLegenda = (ImageView) view.findViewById(R.id.imageQuestion);
        this.lNote = (LinearLayout) view.findViewById(R.id.layNotes);
        this.lNoteHide = (LinearLayout) view.findViewById(R.id.layEmptyNotes);
        this.addNote = (TextView) view.findViewById(R.id.txtAddNote);
        this.imgAddNote = (ImageView) view.findViewById(R.id.imgAddNote);
        this.moodNoteItems = (ListView) findViewById(R.id.listDefMoods);
        this.symptomNoteItems = (ListView) findViewById(R.id.listDefSintomi);
        this.mucusNoteItems = (ListView) findViewById(R.id.listDefMucus);
        this.db = new JCGSQLiteHelper(getContext());
        this.activeUID = this.db.readActiveUID();
        this.selectedSettings = this.db.readSettings(this.activeUID);
        initializeSettings();
        this.initWeek = this.db.readKeySetting(this.activeUID, "startdaycaldomlun");
        this.initLanguage = this.db.readKeySetting(this.activeUID, "locale");
        if (this.initLanguage.equals("en")) {
            this.formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
            this.cal = Calendar.getInstance(Locale.ENGLISH);
            this.formatodataView = DateFormat.getDateInstance(3, Locale.ENGLISH);
        } else if (this.initLanguage.equals("it")) {
            this.formatter = new SimpleDateFormat("MMMM yyyy", Locale.ITALIAN);
            this.cal = Calendar.getInstance(Locale.ITALIAN);
            this.formatodataView = DateFormat.getDateInstance(3, Locale.ITALIAN);
        } else if (this.initLanguage.equals("fr")) {
            this.formatter = new SimpleDateFormat("MMMM yyyy", Locale.FRENCH);
            this.cal = Calendar.getInstance(Locale.FRENCH);
            this.formatodataView = DateFormat.getDateInstance(3, Locale.FRENCH);
        } else if (this.initLanguage.equals("es")) {
            Locale spanish = new Locale("es", "ES");
            this.formatter = new SimpleDateFormat("MMMM yyyy", spanish);
            this.cal = Calendar.getInstance(spanish);
            this.formatodataView = DateFormat.getDateInstance(3, spanish);
        } else if (this.initLanguage.equals("de")) {
            this.formatter = new SimpleDateFormat("MMMM yyyy", Locale.GERMAN);
            this.cal = Calendar.getInstance(Locale.GERMAN);
            this.formatodataView = DateFormat.getDateInstance(3, Locale.GERMAN);
        } else if (this.initLanguage.equals("ru")) {
            Locale russian = new Locale("ru", "RU");
            this.formatter = new SimpleDateFormat("MMMM yyyy", russian);
            this.cal = Calendar.getInstance(russian);
            this.formatodataView = DateFormat.getDateInstance(3, russian);
        } else {
            this.formatter = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
            this.cal = Calendar.getInstance(Locale.getDefault());
            this.formatodataView = DateFormat.getDateInstance(3, Locale.getDefault());
        }
        this.initDate = this.db.readKeySetting(this.activeUID, "tempdate");
        this.oggiDateCheck = new Date();
        this.sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(this.oggiDateCheck);
        this.selectedUser = this.db.readUser(this.activeUID);
        initializeUser();

        if (!this.initDate.substring(0, 4).equals(this.sOggiDateCheck.substring(0, 4))) {
            this.txtData.setText(this.initDate.substring(6, 8) + "-" + this.initDate.substring(4, 6) + "-" + this.initDate.substring(0, 4));
        } else if (!this.initDate.substring(4, 6).equals(this.sOggiDateCheck.substring(4, 6))) {
            this.txtData.setText(this.initDate.substring(6, 8) + "-" + this.initDate.substring(4, 6) + "-" + this.initDate.substring(0, 4));
        } else if (this.initDate.substring(6, 8).equals(this.sOggiDateCheck.substring(6, 8))) {
            this.txtData.setText(this.context.getResources().getString(R.string.day_today));
        } else if (Integer.parseInt(this.initDate.substring(6, 8)) == Integer.parseInt(this.sOggiDateCheck.substring(6, 8)) - 1) {
            this.txtData.setText(this.context.getResources().getString(R.string.day_yesterday));
        } else if (Integer.parseInt(this.initDate.substring(6, 8)) == Integer.parseInt(this.sOggiDateCheck.substring(6, 8)) + 1) {
            this.txtData.setText(this.context.getResources().getString(R.string.day_tomorrow));
        } else {
            this.txtData.setText(this.initDate.substring(6, 8) + "-" + this.initDate.substring(4, 6) + "-" + this.initDate.substring(0, 4));
        }
        stracazzoAGGIORNAMAIN();
        this.rowsNumDateNote = this.db.countRowsNote(this.activeUID, this.initDate);
        this.popolaDate = this.initDate;
        popolaNOTA();
    }

    private void getLegendaButtonClickEvent() {
        this.imgLegenda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), LegendaCalendarActivity.class));
            }
        });
    }

    private void getNOTAButtonClickEvent() {
        this.imgAddNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openNote = new Intent(context.getApplicationContext(), NoteActivity.class);
                openNote.putExtra("datekey", popolaDate);
                context.startActivity(openNote);
                ((CustomCalendarActivity) context).finish();
            }
        });
        this.addNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openNote = new Intent(context.getApplicationContext(), NoteActivity.class);
                openNote.putExtra("datekey", popolaDate);
                context.startActivity(openNote);
                ((CustomCalendarActivity) context).finish();
            }
        });
    }

    private void getNOTAPoppoClickEvent() {
        this.txtNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openNote = new Intent(context.getApplicationContext(), NoteActivity.class);
                openNote.putExtra("datekey", popolaDate);
                context.startActivity(openNote);
                ((CustomCalendarActivity) context).finish();
            }
        });
    }

    private void setPreviousButtonClickEvent() {
        this.previousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cal.add(2, -1);
                db.updateSettings(new Settings(id, activeUID, "ov1min", ""));
                db.updateSettings(new Settings(id, activeUID, "ov2min", ""));
                db.updateSettings(new Settings(id, activeUID, "ov1max", ""));
                db.updateSettings(new Settings(id, activeUID, "ov2max", ""));
                setUpCalendarAdapter();
            }
        });
    }

    private void setNextButtonClickEvent() {
        this.nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cal.add(2, 1);
                db.updateSettings(new Settings(id, activeUID, "ov1min", ""));
                db.updateSettings(new Settings(id, activeUID, "ov2min", ""));
                db.updateSettings(new Settings(id, activeUID, "ov1max", ""));
                db.updateSettings(new Settings(id, activeUID, "ov2max", ""));
                setUpCalendarAdapter();
            }
        });
    }

    private void setGridCellClickEvents() {
        this.calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object valueOf;
                Object valueOf2;
                Calendar mCal = (Calendar) cal.clone();
                mCal.set(5, 1);
                int firstDayOfTheMonth = mCal.get(7) - 1;
                delta = position - firstDayOfTheMonth;
                if (initWeek.equals("0")) {
                    if (firstDayOfTheMonth == 6) {
                        delta += 6;
                    } else {
                        CalendarCustomView calendarCustomView = CalendarCustomView.this;
                        calendarCustomView.delta--;
                    }
                }
                if (initWeek.equals("2")) {
                    if (firstDayOfTheMonth == 0) {
                        CalendarCustomView calendarCustomView2 = CalendarCustomView.this;
                        calendarCustomView2.delta -= 6;
                    } else {
                        delta++;
                    }
                }
                mCal.add(5, delta);
                dayClick = mCal.get(5);
                monthClick = mCal.get(2) + 1;
                yearClick = mCal.get(1);
                CalendarCustomView calendarCustomView3 = CalendarCustomView.this;
                StringBuilder append = new StringBuilder().append(yearClick).append("");
                if (monthClick < 10) {
                    valueOf = "0" + monthClick;
                } else {
                    valueOf = Integer.valueOf(monthClick);
                }
                StringBuilder append2 = append.append(valueOf).append("");
                if (dayClick < 10) {
                    valueOf2 = "0" + dayClick;
                } else {
                    valueOf2 = Integer.valueOf(dayClick);
                }
                calendarCustomView3.sDateKey = append2.append(valueOf2).toString();
                selectedSettings = db.readSettings(activeUID);
                ov1min = db.readKeySetting(activeUID, "ov1min");
                ov2min = db.readKeySetting(activeUID, "ov2min");
                ov1max = db.readKeySetting(activeUID, "ov1max");
                ov2max = db.readKeySetting(activeUID, "ov2max");
                db.updateSettings(new Settings(activeUID, activeUID, "olddate", db.readKeySetting(activeUID, "tempdate")));
                db.updateSettings(new Settings(activeUID, activeUID, "tempdate", sDateKey));
                oggiDateCheck = new Date();
                sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(oggiDateCheck);
                try {
                    selectedDATA = formatodata.parse(sDateKey);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String sFormatDate = formatodataView.format(selectedDATA);
                if (!sDateKey.substring(0, 4).equals(sOggiDateCheck.substring(0, 4))) {
                    txtData.setText(sFormatDate);
                } else if (!sDateKey.substring(4, 6).equals(sOggiDateCheck.substring(4, 6))) {
                    txtData.setText(sFormatDate);
                } else if (sDateKey.substring(6, 8).equals(sOggiDateCheck.substring(6, 8))) {
                    txtData.setText(context.getResources().getString(R.string.day_today));
                } else if (Integer.parseInt(sDateKey.substring(6, 8)) == Integer.parseInt(sOggiDateCheck.substring(6, 8)) - 1) {
                    txtData.setText(context.getResources().getString(R.string.day_yesterday));
                } else if (Integer.parseInt(sDateKey.substring(6, 8)) == Integer.parseInt(sOggiDateCheck.substring(6, 8)) + 1) {
                    txtData.setText(context.getResources().getString(R.string.day_tomorrow));
                } else {
                    txtData.setText(sFormatDate);
                }
                try {
                    ov1minDATA = formatodata.parse(ov1min);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                try {
                    ov2minDATA = formatodata.parse(ov2min);
                } catch (ParseException e3) {
                    e3.printStackTrace();
                }
                try {
                    ov1maxDATA = formatodata.parse(ov1max);
                } catch (ParseException e4) {
                    e4.printStackTrace();
                }
                try {
                    ov2maxDATA = formatodata.parse(ov2max);
                } catch (ParseException e5) {
                    e5.printStackTrace();
                }
                supermegacheck();
                rowsNumDateNote = db.countRowsNote(activeUID, sDateKey);
                rowsNumDatePeriod = db.countRowsPeriod(activeUID, sDateKey);
                notaStringFINAL = "";
                popolaDate = sDateKey;
                popolaNOTA();
                setUpCalendarAdapter();
            }
        });
    }


    public void setUpCalendarAdapter() {
        List<Date> dayValueInCells = new ArrayList<>();
        String primoGiorno = this.db.readKeySetting(this.activeUID, "startdaycaldomlun");
        if (primoGiorno.equals("0")) {
            this.txtDay1.setText(this.context.getResources().getString(R.string.sat));
            this.txtDay2.setText(this.context.getResources().getString(R.string.sun));
            this.txtDay3.setText(this.context.getResources().getString(R.string.mon));
            this.txtDay4.setText(this.context.getResources().getString(R.string.tue));
            this.txtDay5.setText(this.context.getResources().getString(R.string.wed));
            this.txtDay6.setText(this.context.getResources().getString(R.string.thu));
            this.txtDay7.setText(this.context.getResources().getString(R.string.fri));
        }
        if (primoGiorno.equals("1")) {
            this.txtDay1.setText(this.context.getResources().getString(R.string.sun));
            this.txtDay2.setText(this.context.getResources().getString(R.string.mon));
            this.txtDay3.setText(this.context.getResources().getString(R.string.tue));
            this.txtDay4.setText(this.context.getResources().getString(R.string.wed));
            this.txtDay5.setText(this.context.getResources().getString(R.string.thu));
            this.txtDay6.setText(this.context.getResources().getString(R.string.fri));
            this.txtDay7.setText(this.context.getResources().getString(R.string.sat));
        }
        if (primoGiorno.equals("2")) {
            this.txtDay1.setText(this.context.getResources().getString(R.string.mon));
            this.txtDay2.setText(this.context.getResources().getString(R.string.tue));
            this.txtDay3.setText(this.context.getResources().getString(R.string.wed));
            this.txtDay4.setText(this.context.getResources().getString(R.string.thu));
            this.txtDay5.setText(this.context.getResources().getString(R.string.fri));
            this.txtDay6.setText(this.context.getResources().getString(R.string.sat));
            this.txtDay7.setText(this.context.getResources().getString(R.string.sun));
        }
        if (this.initWeek.equals("0")) {
            Calendar mCal = (Calendar) this.cal.clone();
            mCal.set(5, 1);
            int i2 = mCal.get(2);
            int firstDayOfTheMonth = mCal.get(7);
            if (firstDayOfTheMonth == 7) {
                firstDayOfTheMonth = mCal.get(7) - 7;
            }
            int maxDaysMonth = mCal.getActualMaximum(5);
            mCal.add(5, -firstDayOfTheMonth);
            if (firstDayOfTheMonth == 6 && (maxDaysMonth == 30 || maxDaysMonth == 31)) {
                this.maxCalendarColumn = 42;
            }
            if (firstDayOfTheMonth == 5 && maxDaysMonth == 31) {
                this.maxCalendarColumn = 42;
            }
            if (firstDayOfTheMonth < 5) {
                this.maxCalendarColumn = 35;
            }
            if (firstDayOfTheMonth == 0 && maxDaysMonth == 28) {
                this.maxCalendarColumn = 28;
            }
            while (dayValueInCells.size() < this.maxCalendarColumn) {
                dayValueInCells.add(mCal.getTime());
                mCal.add(5, 1);
                Log.e(TAG, "Date -->" + mCal.get(5) + "/" + mCal.get(2) + "/" + mCal.get(1));
            }
        }
        if (this.initWeek.equals("1")) {
            Calendar mCal2 = (Calendar) this.cal.clone();
            mCal2.set(5, 1);
            int firstDayOfTheMonth2 = mCal2.get(7) - 1;
            int maxdaysmonth2 = mCal2.getActualMaximum(5);
            mCal2.add(5, -firstDayOfTheMonth2);
            if (firstDayOfTheMonth2 == 6 && (maxdaysmonth2 == 30 || maxdaysmonth2 == 31)) {
                this.maxCalendarColumn = 42;
            }
            if (firstDayOfTheMonth2 == 5 && maxdaysmonth2 == 31) {
                this.maxCalendarColumn = 42;
            }
            if (firstDayOfTheMonth2 < 5) {
                this.maxCalendarColumn = 35;
            }
            if (firstDayOfTheMonth2 == 0 && maxdaysmonth2 == 28) {
                this.maxCalendarColumn = 28;
            }
            while (dayValueInCells.size() < this.maxCalendarColumn) {
                dayValueInCells.add(mCal2.getTime());
                mCal2.add(5, 1);
            }
        }
        if (this.initWeek.equals("2")) {
            Calendar mCal3 = (Calendar) this.cal.clone();
            mCal3.set(5, 1);
            int firstDayOfTheMonth3 = mCal3.get(7) - 2;
            if (firstDayOfTheMonth3 == -1) {
                firstDayOfTheMonth3 = mCal3.get(7) + 5;
            }
            int maxDaysMonth3 = mCal3.getActualMaximum(5);
            mCal3.add(5, -firstDayOfTheMonth3);
            if (firstDayOfTheMonth3 == 6 && (maxDaysMonth3 == 30 || maxDaysMonth3 == 31)) {
                this.maxCalendarColumn = 42;
            }
            if (firstDayOfTheMonth3 == 5 && maxDaysMonth3 == 31) {
                this.maxCalendarColumn = 42;
            }
            if (firstDayOfTheMonth3 < 5) {
                this.maxCalendarColumn = 35;
            }
            if (firstDayOfTheMonth3 == 0 && maxDaysMonth3 == 28) {
                this.maxCalendarColumn = 28;
            }
            while (dayValueInCells.size() < this.maxCalendarColumn) {
                dayValueInCells.add(mCal3.getTime());
                mCal3.add(5, 1);
                Log.e(TAG, "Date -->" + mCal3.get(5) + "/" + mCal3.get(2) + "/" + mCal3.get(1));
            }
        }
        this.currentDate.setText(this.formatter.format(this.cal.getTime()));
        this.mAdapter = new GridAdapter(this.context, dayValueInCells, this.cal);
        this.calendarGridView.setAdapter(this.mAdapter);
    }

    public void initializeSettings() {
        this.id = this.selectedSettings.getId();
        this.uid = this.selectedSettings.getUid();
        this.key = this.selectedSettings.getKey();
        this.value = this.selectedSettings.getValueKey();
    }

    public void initializeNote() {
        this.id_note = this.selectedNote.getId();
        this.uidNote = this.selectedNote.getUid();
        this.dateNote = this.selectedNote.getDate();
        this.note = this.selectedNote.getNotes();
        this.secretnote = this.selectedNote.getSecretNotes();
        this.symptoms = this.selectedNote.getSymptoms();
        this.mucus = this.selectedNote.getMucus();
        this.moods = this.selectedNote.getMoods();
        this.intimate = this.selectedNote.getIntimate();
        this.gommo = this.selectedNote.getGommo();
        this.sextimes = this.selectedNote.getSextimes();
        this.numorgasm = this.selectedNote.getNumorgasm();
        this.pill = this.selectedNote.getPill();
        this.ovulationtest = this.selectedNote.getOvulationtest();
        this.temperature = this.selectedNote.getTemperature();
        this.weight = this.selectedNote.getWeight();
        this.height = this.selectedNote.getHeight();
        this.seno = this.selectedNote.getSeno();
        this.vita = this.selectedNote.getVita();
        this.fianchi = this.selectedNote.getFianchi();
        this.systolic = this.selectedNote.getSystolic();
        this.diastolic = this.selectedNote.getDiastolic();
        this.pressure = this.selectedNote.getPressure();
        this.testgravidanza = this.selectedNote.getTestgravidanza();
    }

    public void initializePeriod() {
        this.idPer = this.selectedPeriod.getId();
        this.uidPer = this.selectedPeriod.getUid();
        this.type = this.selectedPeriod.getType();
        this.date = this.selectedPeriod.getDate();
        this.completo = this.selectedPeriod.getCompleto();
        this.daysmestruazioni = this.selectedPeriod.getDaysMestruazioni();
        this.daysciclo = this.selectedPeriod.getDaysCiclo();
        this.daysovulation = this.selectedPeriod.getDaysOvulation();
        this.pregnancy = this.selectedPeriod.getPregnancy();
    }

    public static String fillString(int count, char c) {
        StringBuilder sb = new StringBuilder(count);
        for (int i2 = 0; i2 < count; i2++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void popolaNOTA() {
        if (this.rowsNumDatePeriod == 1) {
            this.lNote.setVisibility(View.VISIBLE);
            this.lNoteHide.setVisibility(View.GONE);
            this.selectedPeriod = this.db.readPeriod(this.activeUID, this.popolaDate);
            initializePeriod();
            if (!this.date.equals("") && this.completo == 0) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.period_starts) + "\n\n";
            } else if (!this.date.equals("") && this.completo == 1) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.period_ends) + "\n\n";
            }
            this.txtNote.setText(this.notaStringFINAL);
        }
        if (this.rowsNumDateNote != 1) {
            this.notaStringFINAL = "";
            this.lNote.setVisibility(View.GONE);
            this.lNoteHide.setVisibility(View.VISIBLE);
            return;
        }
        this.lNote.setVisibility(View.VISIBLE);
        this.lNoteHide.setVisibility(View.GONE);
        this.db = new JCGSQLiteHelper(getContext());
        this.selectedSettings = this.db.readSettings(this.activeUID);
        initializeSettings();
        String initTempUnit = this.db.readKeySetting(this.activeUID, "temp_unit");
        String initWeightUnit = this.db.readKeySetting(this.activeUID, "weight_unit");
        String initHeightUnit = this.db.readKeySetting(this.activeUID, "height_unit");
        this.switchshodays = this.db.readKeySetting(this.activeUID, "showdayscycle");
        this.switchshowmed = this.db.readKeySetting(this.activeUID, "showmedicines");
        this.switchshoovul = this.db.readKeySetting(this.activeUID, "showovulfertile");
        this.switchshopreg = this.db.readKeySetting(this.activeUID, "showpregnant");
        this.switchshoint = this.db.readKeySetting(this.activeUID, "showmintimate");
        this.switchshofutp = this.db.readKeySetting(this.activeUID, "showfutperiod");
        this.selectedNote = this.db.readNote(this.activeUID, this.popolaDate);
        initializeNote();
        this.rowsNumMoodUid = this.db.countRowsMood(this.activeUID);
        this.rowsNumSymptomsUid = this.db.countRowsSymptoms(this.activeUID);
        this.rowsNumMucusUid = this.db.countRowsMucus(this.activeUID);
        this.rowsNumPillUid = this.db.countRowsPills(this.activeUID);
        this.moodsstringdefault = fillString(this.rowsNumMoodUid, '0');
        this.symptomsstringdefault = fillString(this.rowsNumSymptomsUid, '0');
        this.mucusstringdefault = fillString(this.rowsNumMucusUid, '0');
        this.pillstringdefault = fillString(this.rowsNumPillUid, '0');
        if (!this.note.equals("")) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.menu_note) + ": " + this.note + "\n\n";
        }
        if (this.moods.equals(this.moodsstringdefault)) {
            this.moodNoteItems.setVisibility(View.GONE);
        } else {
            this.moodNoteItems.setVisibility(View.VISIBLE);
            MoodNote[] moodNOTA = this.db.selectSIMBOLIMoodsNOTA(this.activeUID, this.popolaDate);
            ArrayList<Integer> integers = new ArrayList<>();

            int righeEffettive = 0;
            for (MoodNote value2 : moodNOTA) {
                if (!value2.getValue().equals("0")) {
                    integers.add(value2.getId());
                    righeEffettive++;
                }
            }
            int f = 0;
            int[] arrayid = new int[righeEffettive];
            String[] arrayvalue = new String[righeEffettive];
            for (int y = 0; y < righeEffettive; y++) {
                if (!moodNOTA[integers.get(y)].getValue().equals("0")) {
                    arrayid[f] = moodNOTA[integers.get(y)].getId();
                    arrayvalue[f] = moodNOTA[integers.get(y)].getValue();
                    f++;
                }
            }
            MoodNoteAdapter adapter = new MoodNoteAdapter(this.context, arrayvalue, arrayid);
            this.moodNoteItems.setDivider((Drawable) null);
            this.moodNoteItems.setAdapter(adapter);
            this.notaStringFINAL += this.context.getResources().getString(R.string.menu_note_moods) + ": ";
            if (this.moods.charAt(0) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_0) + ", ";
            }
            if (this.moods.charAt(1) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_1) + ", ";
            }
            if (this.moods.charAt(2) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_2) + ", ";
            }
            if (this.moods.charAt(3) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_3) + ", ";
            }
            if (this.moods.charAt(4) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_4) + ", ";
            }
            if (this.moods.charAt(5) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_5) + ", ";
            }
            if (this.moods.charAt(6) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_6) + ", ";
            }
            if (this.moods.charAt(7) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_7) + ", ";
            }
            if (this.moods.charAt(8) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_8) + ", ";
            }
            if (this.moods.charAt(9) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_9) + ", ";
            }
            if (this.moods.charAt(10) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_10) + ", ";
            }
            if (this.moods.charAt(11) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_11) + ", ";
            }
            if (this.moods.charAt(12) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_12) + ", ";
            }
            if (this.moods.charAt(13) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_13) + ", ";
            }
            if (this.moods.charAt(14) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_14) + ", ";
            }
            if (this.moods.charAt(15) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_15) + ", ";
            }
            if (this.moods.charAt(16) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_16) + ", ";
            }
            if (this.moods.charAt(17) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_17) + ", ";
            }
            if (this.moods.charAt(18) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_18) + ", ";
            }
            if (this.moods.charAt(19) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_19) + ", ";
            }
            if (this.moods.charAt(20) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_20) + ", ";
            }
            if (this.moods.charAt(21) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_21) + ", ";
            }
            if (this.moods.charAt(22) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_22) + ", ";
            }
            if (this.moods.charAt(23) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_23) + ", ";
            }
            if (this.moods.charAt(24) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_24) + ", ";
            }
            if (this.moods.charAt(25) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_25) + ", ";
            }
            if (this.moods.charAt(26) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_26) + ", ";
            }
            if (this.moods.charAt(27) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_27) + ", ";
            }
            if (this.moods.charAt(28) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_28) + ", ";
            }
            if (this.moods.charAt(29) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_29) + ", ";
            }
            if (this.moods.charAt(30) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_30) + ", ";
            }
            if (this.moods.charAt(31) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_31) + ", ";
            }
            if (this.moods.charAt(32) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_32) + ", ";
            }
            if (this.moods.charAt(33) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_33) + ", ";
            }
            if (this.moods.charAt(34) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_34) + ", ";
            }
            if (this.moods.charAt(35) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_35) + ", ";
            }
            if (this.moods.charAt(36) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_36) + ", ";
            }
            if (this.moods.charAt(37) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_37) + ", ";
            }
            if (this.moods.charAt(38) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_38) + ", ";
            }
            if (this.moods.charAt(39) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_39) + ", ";
            }
            if (this.moods.charAt(40) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_40) + ", ";
            }
            if (this.moods.charAt(41) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_41) + ", ";
            }
            if (this.moods.charAt(42) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_42) + ", ";
            }
            if (this.moods.charAt(43) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_43) + ", ";
            }
            if (this.moods.charAt(44) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_44) + ", ";
            }
            if (this.moods.charAt(45) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_45) + ", ";
            }
            if (this.moods.charAt(46) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_46) + ", ";
            }
            if (this.moods.charAt(47) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_47) + ", ";
            }
            if (this.moods.charAt(48) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_48) + ", ";
            }
            if (this.moods.charAt(49) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_49) + ", ";
            }
            if (this.moods.charAt(50) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_50) + ", ";
            }
            if (this.moods.charAt(51) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_51) + ", ";
            }
            if (this.moods.charAt(52) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_52) + ", ";
            }
            if (this.moods.charAt(53) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_53) + ", ";
            }
            if (this.moods.charAt(54) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_54) + ", ";
            }
            if (this.moods.charAt(55) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_55) + ", ";
            }
            if (this.moods.charAt(56) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_56) + ", ";
            }
            if (this.moods.charAt(57) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_57) + ", ";
            }
            if (this.moods.charAt(58) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_58) + ", ";
            }
            if (this.moods.charAt(59) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_59) + ", ";
            }
            if (this.moods.charAt(60) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_60) + ", ";
            }
            if (this.moods.charAt(61) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_61) + ", ";
            }
            if (this.moods.charAt(62) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_62) + ", ";
            }
            if (this.moods.charAt(63) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_63) + ", ";
            }
            if (this.moods.charAt(64) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_64) + ", ";
            }
            if (this.moods.charAt(65) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_65) + ", ";
            }
            if (this.moods.charAt(66) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_66) + ", ";
            }
            if (this.moods.charAt(67) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_67) + ", ";
            }
            if (this.moods.charAt(68) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_68) + ", ";
            }
            if (this.moods.charAt(69) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_69) + ", ";
            }
            if (this.moods.charAt(70) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_70) + ", ";
            }
            if (this.moods.charAt(71) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_71) + ", ";
            }
            if (this.moods.charAt(72) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_72) + ", ";
            }
            if (this.moods.charAt(73) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_73) + ", ";
            }
            if (this.moods.charAt(74) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_74) + ", ";
            }
            if (this.moods.charAt(75) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_75) + ", ";
            }
            this.notaStringFINAL += "\n\n";
        }
        if (this.symptoms.equals(this.symptomsstringdefault)) {
            this.symptomNoteItems.setVisibility(View.GONE);
        } else {
            this.symptomNoteItems.setVisibility(View.VISIBLE);
            SymptomNote[] symptomNoteArr = new SymptomNote[this.rowsNumDateNote];
            SymptomNote[] symptomNOTA = this.db.selectSIMBOLISymptomsNOTA(this.activeUID, this.popolaDate);

            ArrayList<Integer> integers = new ArrayList<>();
            Log.e("aaaaaa",""+symptomNOTA.length);
            int righeEffettive2 = 0;
            for (SymptomNote value3 : symptomNOTA) {
                if (!value3.getValue().equals("0")) {
                    integers.add(value3.getId());
                    righeEffettive2++;
                    Log.e("aaaaaabb",""+righeEffettive2);


                }
            }
            int[] arrayid2 = new int[righeEffettive2];
            String[] arrayvalue2 = new String[righeEffettive2];
            for (int y2 = 0; y2 < righeEffettive2; y2++) {
                if (!symptomNOTA[integers.get(y2)].getValue().equals("0")) {
                    arrayid2[y2] = symptomNOTA[integers.get(y2)].getId();
                    Log.e("aaaaaacc",""+arrayid2[y2]);

                    arrayvalue2[y2] = symptomNOTA[integers.get(y2)].getValue();
                }
            }
            SymptomNoteAdapter adapter2 = new SymptomNoteAdapter(this.context, arrayvalue2, arrayid2);
            this.symptomNoteItems.setDivider((Drawable) null);
            this.symptomNoteItems.setAdapter(adapter2);
            this.notaStringFINAL += this.context.getResources().getString(R.string.menu_note_symptoms) + ": ";
            if (this.symptoms.charAt(0) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_0) + " (*), ";
            }
            if (this.symptoms.charAt(1) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_1) + " (*), ";
            }
            if (this.symptoms.charAt(2) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_2) + " (*), ";
            }
            if (this.symptoms.charAt(3) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_3) + " (*), ";
            }
            if (this.symptoms.charAt(4) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_4) + " (*), ";
            }
            if (this.symptoms.charAt(5) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_5) + " (*), ";
            }
            if (this.symptoms.charAt(6) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_6) + " (*), ";
            }
            if (this.symptoms.charAt(7) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_7) + " (*), ";
            }
            if (this.symptoms.charAt(8) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_8) + " (*), ";
            }
            if (this.symptoms.charAt(9) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_9) + " (*), ";
            }
            if (this.symptoms.charAt(10) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_10) + " (*), ";
            }
            if (this.symptoms.charAt(11) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_11) + " (*), ";
            }
            if (this.symptoms.charAt(12) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_12) + " (*), ";
            }
            if (this.symptoms.charAt(13) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_13) + " (*), ";
            }
            if (this.symptoms.charAt(14) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_14) + " (*), ";
            }
            if (this.symptoms.charAt(15) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_15) + " (*), ";
            }
            if (this.symptoms.charAt(16) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_16) + " (*), ";
            }
            if (this.symptoms.charAt(17) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_17) + " (*), ";
            }
            if (this.symptoms.charAt(18) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_18) + " (*), ";
            }
            if (this.symptoms.charAt(19) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_19) + " (*), ";
            }
            if (this.symptoms.charAt(20) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_20) + " (*), ";
            }
            if (this.symptoms.charAt(21) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_21) + " (*), ";
            }
            if (this.symptoms.charAt(22) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_22) + " (*), ";
            }
            if (this.symptoms.charAt(23) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_23) + " (*), ";
            }
            if (this.symptoms.charAt(24) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_24) + " (*), ";
            }
            if (this.symptoms.charAt(25) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_25) + " (*), ";
            }
            if (this.symptoms.charAt(26) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_26) + " (*), ";
            }
            if (this.symptoms.charAt(27) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_27) + " (*), ";
            }
            if (this.symptoms.charAt(28) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_28) + " (*), ";
            }
            if (this.symptoms.charAt(29) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_29) + " (*), ";
            }
            if (this.symptoms.charAt(30) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_30) + " (*), ";
            }
            if (this.symptoms.charAt(31) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_31) + " (*), ";
            }
            if (this.symptoms.charAt(32) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_32) + " (*), ";
            }
            if (this.symptoms.charAt(33) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_33) + " (*), ";
            }
            if (this.symptoms.charAt(34) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_34) + " (*), ";
            }
            if (this.symptoms.charAt(35) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_35) + " (*), ";
            }
            if (this.symptoms.charAt(36) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_36) + " (*), ";
            }
            if (this.symptoms.charAt(37) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_37) + " (*), ";
            }
            if (this.symptoms.charAt(38) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_38) + " (*), ";
            }
            if (this.symptoms.charAt(39) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_39) + " (*), ";
            }
            if (this.symptoms.charAt(40) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_40) + " (*), ";
            }
            if (this.symptoms.charAt(41) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_41) + " (*), ";
            }
            if (this.symptoms.charAt(42) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_42) + " (*), ";
            }
            if (this.symptoms.charAt(43) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_43) + " (*), ";
            }
            if (this.symptoms.charAt(44) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_44) + " (*), ";
            }
            if (this.symptoms.charAt(45) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_45) + " (*), ";
            }
            if (this.symptoms.charAt(46) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_46) + " (*), ";
            }
            if (this.symptoms.charAt(47) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_47) + " (*), ";
            }
            if (this.symptoms.charAt(48) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_48) + " (*), ";
            }
            if (this.symptoms.charAt(49) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_49) + " (*), ";
            }
            if (this.symptoms.charAt(50) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_50) + " (*), ";
            }
            if (this.symptoms.charAt(51) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_51) + " (*), ";
            }
            if (this.symptoms.charAt(52) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_52) + " (*), ";
            }
            if (this.symptoms.charAt(53) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_53) + " (*), ";
            }
            if (this.symptoms.charAt(54) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_54) + " (*), ";
            }
            if (this.symptoms.charAt(55) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_55) + " (*), ";
            }
            if (this.symptoms.charAt(56) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_56) + " (*), ";
            }
            if (this.symptoms.charAt(57) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_57) + " (*), ";
            }
            if (this.symptoms.charAt(0) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_0) + " (**), ";
            }
            if (this.symptoms.charAt(1) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_1) + " (**), ";
            }
            if (this.symptoms.charAt(2) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_2) + " (**), ";
            }
            if (this.symptoms.charAt(3) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_3) + " (**), ";
            }
            if (this.symptoms.charAt(4) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_4) + " (**), ";
            }
            if (this.symptoms.charAt(5) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_5) + " (**), ";
            }
            if (this.symptoms.charAt(6) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_6) + " (**), ";
            }
            if (this.symptoms.charAt(7) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_7) + " (**), ";
            }
            if (this.symptoms.charAt(8) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_8) + " (**), ";
            }
            if (this.symptoms.charAt(9) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_9) + " (**), ";
            }
            if (this.symptoms.charAt(10) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_10) + " (**), ";
            }
            if (this.symptoms.charAt(11) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_11) + " (**), ";
            }
            if (this.symptoms.charAt(12) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_12) + " (**), ";
            }
            if (this.symptoms.charAt(13) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_13) + " (**), ";
            }
            if (this.symptoms.charAt(14) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_14) + " (**), ";
            }
            if (this.symptoms.charAt(15) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_15) + " (**), ";
            }
            if (this.symptoms.charAt(16) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_16) + " (**), ";
            }
            if (this.symptoms.charAt(17) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_17) + " (**), ";
            }
            if (this.symptoms.charAt(18) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_18) + " (**), ";
            }
            if (this.symptoms.charAt(19) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_19) + " (**), ";
            }
            if (this.symptoms.charAt(20) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_20) + " (**), ";
            }
            if (this.symptoms.charAt(21) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_21) + " (**), ";
            }
            if (this.symptoms.charAt(22) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_22) + " (**), ";
            }
            if (this.symptoms.charAt(23) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_23) + " (**), ";
            }
            if (this.symptoms.charAt(24) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_24) + " (**), ";
            }
            if (this.symptoms.charAt(25) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_25) + " (**), ";
            }
            if (this.symptoms.charAt(26) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_26) + " (**), ";
            }
            if (this.symptoms.charAt(27) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_27) + " (**), ";
            }
            if (this.symptoms.charAt(28) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_28) + " (**), ";
            }
            if (this.symptoms.charAt(29) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_29) + " (**), ";
            }
            if (this.symptoms.charAt(30) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_30) + " (**), ";
            }
            if (this.symptoms.charAt(31) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_31) + " (**), ";
            }
            if (this.symptoms.charAt(32) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_32) + " (**), ";
            }
            if (this.symptoms.charAt(33) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_33) + " (**), ";
            }
            if (this.symptoms.charAt(34) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_34) + " (**), ";
            }
            if (this.symptoms.charAt(35) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_35) + " (**), ";
            }
            if (this.symptoms.charAt(36) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_36) + " (**), ";
            }
            if (this.symptoms.charAt(37) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_37) + " (**), ";
            }
            if (this.symptoms.charAt(38) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_38) + " (**), ";
            }
            if (this.symptoms.charAt(39) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_39) + " (**), ";
            }
            if (this.symptoms.charAt(40) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_40) + " (**), ";
            }
            if (this.symptoms.charAt(41) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_41) + " (**), ";
            }
            if (this.symptoms.charAt(42) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_42) + " (**), ";
            }
            if (this.symptoms.charAt(43) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_43) + " (**), ";
            }
            if (this.symptoms.charAt(44) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_44) + " (**), ";
            }
            if (this.symptoms.charAt(45) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_45) + " (**), ";
            }
            if (this.symptoms.charAt(46) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_46) + " (**), ";
            }
            if (this.symptoms.charAt(47) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_47) + " (**), ";
            }
            if (this.symptoms.charAt(48) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_48) + " (**), ";
            }
            if (this.symptoms.charAt(49) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_49) + " (**), ";
            }
            if (this.symptoms.charAt(50) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_50) + " (**), ";
            }
            if (this.symptoms.charAt(51) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_51) + " (**), ";
            }
            if (this.symptoms.charAt(52) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_52) + " (**), ";
            }
            if (this.symptoms.charAt(53) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_53) + " (**), ";
            }
            if (this.symptoms.charAt(54) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_54) + " (**), ";
            }
            if (this.symptoms.charAt(55) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_55) + " (**), ";
            }
            if (this.symptoms.charAt(56) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_56) + " (**), ";
            }
            if (this.symptoms.charAt(57) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_57) + " (**), ";
            }
            if (this.symptoms.charAt(0) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_0) + " (***), ";
            }
            if (this.symptoms.charAt(1) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_1) + " (***), ";
            }
            if (this.symptoms.charAt(2) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_2) + " (***), ";
            }
            if (this.symptoms.charAt(3) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_3) + " (***), ";
            }
            if (this.symptoms.charAt(4) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_4) + " (***), ";
            }
            if (this.symptoms.charAt(5) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_5) + " (***), ";
            }
            if (this.symptoms.charAt(6) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_6) + " (***), ";
            }
            if (this.symptoms.charAt(7) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_7) + " (***), ";
            }
            if (this.symptoms.charAt(8) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_8) + " (***), ";
            }
            if (this.symptoms.charAt(9) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_9) + " (***), ";
            }
            if (this.symptoms.charAt(10) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_10) + " (***), ";
            }
            if (this.symptoms.charAt(11) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_11) + " (***), ";
            }
            if (this.symptoms.charAt(12) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_12) + " (***), ";
            }
            if (this.symptoms.charAt(13) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_13) + " (***), ";
            }
            if (this.symptoms.charAt(14) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_14) + " (***), ";
            }
            if (this.symptoms.charAt(15) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_15) + " (***), ";
            }
            if (this.symptoms.charAt(16) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_16) + " (***), ";
            }
            if (this.symptoms.charAt(17) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_17) + " (***), ";
            }
            if (this.symptoms.charAt(18) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_18) + " (***), ";
            }
            if (this.symptoms.charAt(19) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_19) + " (***), ";
            }
            if (this.symptoms.charAt(20) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_20) + " (***), ";
            }
            if (this.symptoms.charAt(21) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_21) + " (***), ";
            }
            if (this.symptoms.charAt(22) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_22) + " (***), ";
            }
            if (this.symptoms.charAt(23) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_23) + " (***), ";
            }
            if (this.symptoms.charAt(24) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_24) + " (***), ";
            }
            if (this.symptoms.charAt(25) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_25) + " (***), ";
            }
            if (this.symptoms.charAt(26) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_26) + " (***), ";
            }
            if (this.symptoms.charAt(27) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_27) + " (***), ";
            }
            if (this.symptoms.charAt(28) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_28) + " (***), ";
            }
            if (this.symptoms.charAt(29) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_29) + " (***), ";
            }
            if (this.symptoms.charAt(30) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_30) + " (***), ";
            }
            if (this.symptoms.charAt(31) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_31) + " (***), ";
            }
            if (this.symptoms.charAt(32) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_32) + " (***), ";
            }
            if (this.symptoms.charAt(33) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_33) + " (***), ";
            }
            if (this.symptoms.charAt(34) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_34) + " (***), ";
            }
            if (this.symptoms.charAt(35) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_35) + " (***), ";
            }
            if (this.symptoms.charAt(36) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_36) + " (***), ";
            }
            if (this.symptoms.charAt(37) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_37) + " (***), ";
            }
            if (this.symptoms.charAt(38) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_38) + " (***), ";
            }
            if (this.symptoms.charAt(39) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_39) + " (***), ";
            }
            if (this.symptoms.charAt(40) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_40) + " (***), ";
            }
            if (this.symptoms.charAt(41) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_41) + " (***), ";
            }
            if (this.symptoms.charAt(42) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_42) + " (***), ";
            }
            if (this.symptoms.charAt(43) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_43) + " (***), ";
            }
            if (this.symptoms.charAt(44) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_44) + " (***), ";
            }
            if (this.symptoms.charAt(45) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_45) + " (***), ";
            }
            if (this.symptoms.charAt(46) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_46) + " (***), ";
            }
            if (this.symptoms.charAt(47) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_47) + " (***), ";
            }
            if (this.symptoms.charAt(48) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_48) + " (***), ";
            }
            if (this.symptoms.charAt(49) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_49) + " (***), ";
            }
            if (this.symptoms.charAt(50) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_50) + " (***), ";
            }
            if (this.symptoms.charAt(51) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_51) + " (***), ";
            }
            if (this.symptoms.charAt(52) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_52) + " (***), ";
            }
            if (this.symptoms.charAt(53) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_53) + " (***), ";
            }
            if (this.symptoms.charAt(54) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_54) + " (***), ";
            }
            if (this.symptoms.charAt(55) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_55) + " (***), ";
            }
            if (this.symptoms.charAt(56) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_56) + " (***), ";
            }
            if (this.symptoms.charAt(57) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_57) + " (***), ";
            }
            if (this.symptoms.charAt(0) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_0) + " (****), ";
            }
            if (this.symptoms.charAt(1) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_1) + " (****), ";
            }
            if (this.symptoms.charAt(2) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_2) + " (****), ";
            }
            if (this.symptoms.charAt(3) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_3) + " (****), ";
            }
            if (this.symptoms.charAt(4) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_4) + " (****), ";
            }
            if (this.symptoms.charAt(5) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_5) + " (****), ";
            }
            if (this.symptoms.charAt(6) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_6) + " (****), ";
            }
            if (this.symptoms.charAt(7) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_7) + " (****), ";
            }
            if (this.symptoms.charAt(8) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_8) + " (****), ";
            }
            if (this.symptoms.charAt(9) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_9) + " (****), ";
            }
            if (this.symptoms.charAt(10) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_10) + " (****), ";
            }
            if (this.symptoms.charAt(11) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_11) + " (****), ";
            }
            if (this.symptoms.charAt(12) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_12) + " (****), ";
            }
            if (this.symptoms.charAt(13) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_13) + " (****), ";
            }
            if (this.symptoms.charAt(14) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_14) + " (****), ";
            }
            if (this.symptoms.charAt(15) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_15) + " (****), ";
            }
            if (this.symptoms.charAt(16) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_16) + " (****), ";
            }
            if (this.symptoms.charAt(17) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_17) + " (****), ";
            }
            if (this.symptoms.charAt(18) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_18) + " (****), ";
            }
            if (this.symptoms.charAt(19) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_19) + " (****), ";
            }
            if (this.symptoms.charAt(20) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_20) + " (****), ";
            }
            if (this.symptoms.charAt(21) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_21) + " (****), ";
            }
            if (this.symptoms.charAt(22) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_22) + " (****), ";
            }
            if (this.symptoms.charAt(23) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_23) + " (****), ";
            }
            if (this.symptoms.charAt(24) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_24) + " (****), ";
            }
            if (this.symptoms.charAt(25) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_25) + " (****), ";
            }
            if (this.symptoms.charAt(26) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_26) + " (****), ";
            }
            if (this.symptoms.charAt(27) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_27) + " (****), ";
            }
            if (this.symptoms.charAt(28) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_28) + " (****), ";
            }
            if (this.symptoms.charAt(29) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_29) + " (****), ";
            }
            if (this.symptoms.charAt(30) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_30) + " (****), ";
            }
            if (this.symptoms.charAt(31) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_31) + " (****), ";
            }
            if (this.symptoms.charAt(32) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_32) + " (****), ";
            }
            if (this.symptoms.charAt(33) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_33) + " (****), ";
            }
            if (this.symptoms.charAt(34) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_34) + " (****), ";
            }
            if (this.symptoms.charAt(35) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_35) + " (****), ";
            }
            if (this.symptoms.charAt(36) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_36) + " (****), ";
            }
            if (this.symptoms.charAt(37) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_37) + " (****), ";
            }
            if (this.symptoms.charAt(38) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_38) + " (****), ";
            }
            if (this.symptoms.charAt(39) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_39) + " (****), ";
            }
            if (this.symptoms.charAt(40) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_40) + " (****), ";
            }
            if (this.symptoms.charAt(41) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_41) + " (****), ";
            }
            if (this.symptoms.charAt(42) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_42) + " (****), ";
            }
            if (this.symptoms.charAt(43) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_43) + " (****), ";
            }
            if (this.symptoms.charAt(44) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_44) + " (****), ";
            }
            if (this.symptoms.charAt(45) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_45) + " (****), ";
            }
            if (this.symptoms.charAt(46) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_46) + " (****), ";
            }
            if (this.symptoms.charAt(47) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_47) + " (****), ";
            }
            if (this.symptoms.charAt(48) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_48) + " (****), ";
            }
            if (this.symptoms.charAt(49) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_49) + " (****), ";
            }
            if (this.symptoms.charAt(50) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_50) + " (****), ";
            }
            if (this.symptoms.charAt(51) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_51) + " (****), ";
            }
            if (this.symptoms.charAt(52) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_52) + " (****), ";
            }
            if (this.symptoms.charAt(53) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_53) + " (****), ";
            }
            if (this.symptoms.charAt(54) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_54) + " (****), ";
            }
            if (this.symptoms.charAt(55) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_55) + " (****), ";
            }
            if (this.symptoms.charAt(56) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_56) + " (****), ";
            }
            if (this.symptoms.charAt(57) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_57) + " (****), ";
            }
            if (this.symptoms.charAt(0) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_0) + " (*****), ";
            }
            if (this.symptoms.charAt(1) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_1) + " (*****), ";
            }
            if (this.symptoms.charAt(2) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_2) + " (*****), ";
            }
            if (this.symptoms.charAt(3) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_3) + " (*****), ";
            }
            if (this.symptoms.charAt(4) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_4) + " (*****), ";
            }
            if (this.symptoms.charAt(5) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_5) + " (*****), ";
            }
            if (this.symptoms.charAt(6) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_6) + " (*****), ";
            }
            if (this.symptoms.charAt(7) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_7) + " (*****), ";
            }
            if (this.symptoms.charAt(8) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_8) + " (*****), ";
            }
            if (this.symptoms.charAt(9) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_9) + " (*****), ";
            }
            if (this.symptoms.charAt(10) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_10) + " (*****), ";
            }
            if (this.symptoms.charAt(11) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_11) + " (*****), ";
            }
            if (this.symptoms.charAt(12) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_12) + " (*****), ";
            }
            if (this.symptoms.charAt(13) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_13) + " (*****), ";
            }
            if (this.symptoms.charAt(14) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_14) + " (*****), ";
            }
            if (this.symptoms.charAt(15) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_15) + " (*****), ";
            }
            if (this.symptoms.charAt(16) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_16) + " (*****), ";
            }
            if (this.symptoms.charAt(17) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_17) + " (*****), ";
            }
            if (this.symptoms.charAt(18) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_18) + " (*****), ";
            }
            if (this.symptoms.charAt(19) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_19) + " (*****), ";
            }
            if (this.symptoms.charAt(20) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_20) + " (*****), ";
            }
            if (this.symptoms.charAt(21) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_21) + " (*****), ";
            }
            if (this.symptoms.charAt(22) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_22) + " (*****), ";
            }
            if (this.symptoms.charAt(23) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_23) + " (*****), ";
            }
            if (this.symptoms.charAt(24) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_24) + " (*****), ";
            }
            if (this.symptoms.charAt(25) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_25) + " (*****), ";
            }
            if (this.symptoms.charAt(26) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_26) + " (*****), ";
            }
            if (this.symptoms.charAt(27) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_27) + " (*****), ";
            }
            if (this.symptoms.charAt(28) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_28) + " (*****), ";
            }
            if (this.symptoms.charAt(29) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_29) + " (*****), ";
            }
            if (this.symptoms.charAt(30) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_30) + " (*****), ";
            }
            if (this.symptoms.charAt(31) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_31) + " (*****), ";
            }
            if (this.symptoms.charAt(32) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_32) + " (*****), ";
            }
            if (this.symptoms.charAt(33) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_33) + " (*****), ";
            }
            if (this.symptoms.charAt(34) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_34) + " (*****), ";
            }
            if (this.symptoms.charAt(35) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_35) + " (*****), ";
            }
            if (this.symptoms.charAt(36) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_36) + " (*****), ";
            }
            if (this.symptoms.charAt(37) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_37) + " (*****), ";
            }
            if (this.symptoms.charAt(38) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_38) + " (*****), ";
            }
            if (this.symptoms.charAt(39) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_39) + " (*****), ";
            }
            if (this.symptoms.charAt(40) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_40) + " (*****), ";
            }
            if (this.symptoms.charAt(41) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_41) + " (*****), ";
            }
            if (this.symptoms.charAt(42) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_42) + " (*****), ";
            }
            if (this.symptoms.charAt(43) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_43) + " (*****), ";
            }
            if (this.symptoms.charAt(44) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_44) + " (*****), ";
            }
            if (this.symptoms.charAt(45) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_45) + " (*****), ";
            }
            if (this.symptoms.charAt(46) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_46) + " (*****), ";
            }
            if (this.symptoms.charAt(47) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_47) + " (*****), ";
            }
            if (this.symptoms.charAt(48) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_48) + " (*****), ";
            }
            if (this.symptoms.charAt(49) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_49) + " (*****), ";
            }
            if (this.symptoms.charAt(50) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_50) + " (*****), ";
            }
            if (this.symptoms.charAt(51) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_51) + " (*****), ";
            }
            if (this.symptoms.charAt(52) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_52) + " (*****), ";
            }
            if (this.symptoms.charAt(53) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_53) + " (*****), ";
            }
            if (this.symptoms.charAt(54) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_54) + " (*****), ";
            }
            if (this.symptoms.charAt(55) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_55) + " (*****), ";
            }
            if (this.symptoms.charAt(56) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_56) + " (*****), ";
            }
            if (this.symptoms.charAt(57) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.symptom_57) + " (*****), ";
            }
            this.notaStringFINAL += "\n\n";
        }
        if (this.switchshowmed.equals("1") && !this.pill.equals(this.pillstringdefault)) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.pills_title) + ": ";
            if (this.pill.charAt(0) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_0) + ", ";
            }
            if (this.pill.charAt(1) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mood_1) + ", ";
            }
            if (this.pill.charAt(2) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_2) + ", ";
            }
            if (this.pill.charAt(3) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_3) + ", ";
            }
            if (this.pill.charAt(4) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_4) + ", ";
            }
            if (this.pill.charAt(5) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_5) + ", ";
            }
            if (this.pill.charAt(6) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_6) + ", ";
            }
            if (this.pill.charAt(7) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_7) + ", ";
            }
            if (this.pill.charAt(8) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_8) + ", ";
            }
            if (this.pill.charAt(9) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_9) + ", ";
            }
            if (this.pill.charAt(10) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_10) + ", ";
            }
            if (this.pill.charAt(11) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_11) + ", ";
            }
            if (this.pill.charAt(12) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_12) + ", ";
            }
            if (this.pill.charAt(13) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_13) + ", ";
            }
            if (this.pill.charAt(14) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_14) + ", ";
            }
            if (this.pill.charAt(15) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_15) + ", ";
            }
            if (this.pill.charAt(16) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_16) + ", ";
            }
            if (this.pill.charAt(17) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_17) + ", ";
            }
            if (this.pill.charAt(18) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_18) + ", ";
            }
            if (this.pill.charAt(19) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_19) + ", ";
            }
            if (this.pill.charAt(20) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.pill_20) + ", ";
            }
            this.notaStringFINAL += "\n\n";
        }
        if (this.mucus.equals(this.mucusstringdefault)) {
            this.mucusNoteItems.setVisibility(View.GONE);
        } else {
            this.mucusNoteItems.setVisibility(View.VISIBLE);
            MucusNote[] mucusNoteArr = new MucusNote[this.rowsNumDateNote];
            MucusNote[] mucusNOTA = this.db.selectSIMBOLIMucusNOTA(this.activeUID, this.popolaDate);
            int righeEffettive3 = 0;
            for (MucusNote value4 : mucusNOTA) {
                if (!value4.getValue().equals("0")) {
                    righeEffettive3++;
                }
            }
            int f3 = 0;
            int[] arrayid3 = new int[righeEffettive3];
            String[] arrayvalue3 = new String[righeEffettive3];
            for (int y3 = 0; y3 < righeEffettive3; y3++) {
                if (!mucusNOTA[y3].getValue().equals("0")) {
                    arrayid3[f3] = mucusNOTA[y3].getId();
                    arrayvalue3[f3] = mucusNOTA[y3].getValue();
                    f3++;
                }
            }
            MucusNoteAdapter adapter3 = new MucusNoteAdapter(this.context, arrayvalue3, arrayid3);
            this.mucusNoteItems.setDivider((Drawable) null);
            this.mucusNoteItems.setAdapter(adapter3);
            this.notaStringFINAL += this.context.getResources().getString(R.string.menu_note_mucus) + ": ";
            if (this.mucus.charAt(0) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_0) + " (*), ";
            }
            if (this.mucus.charAt(1) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_1) + " (*), ";
            }
            if (this.mucus.charAt(2) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_2) + " (*), ";
            }
            if (this.mucus.charAt(3) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_3) + " (*), ";
            }
            if (this.mucus.charAt(4) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_4) + " (*), ";
            }
            if (this.mucus.charAt(5) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_5) + " (*), ";
            }
            if (this.mucus.charAt(6) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_6) + " (*), ";
            }
            if (this.mucus.charAt(7) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_7) + " (*), ";
            }
            if (this.mucus.charAt(8) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_8) + " (*), ";
            }
            if (this.mucus.charAt(9) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_9) + " (*), ";
            }
            if (this.mucus.charAt(10) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_10) + " (*), ";
            }
            if (this.mucus.charAt(11) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_11) + " (*), ";
            }
            if (this.mucus.charAt(12) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_12) + " (*), ";
            }
            if (this.mucus.charAt(13) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_13) + " (*), ";
            }
            if (this.mucus.charAt(14) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_14) + " (*), ";
            }
            if (this.mucus.charAt(15) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_15) + " (*), ";
            }
            if (this.mucus.charAt(16) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_16) + " (*), ";
            }
            if (this.mucus.charAt(17) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_17) + " (*), ";
            }
            if (this.mucus.charAt(18) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_18) + " (*), ";
            }
            if (this.mucus.charAt(19) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_19) + " (*), ";
            }
            if (this.mucus.charAt(20) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_20) + " (*), ";
            }
            if (this.mucus.charAt(21) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_21) + " (*), ";
            }
            if (this.mucus.charAt(22) == '1') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_22) + " (*), ";
            }
            if (this.mucus.charAt(0) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_0) + " (**), ";
            }
            if (this.mucus.charAt(1) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_1) + " (**), ";
            }
            if (this.mucus.charAt(2) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_2) + " (**), ";
            }
            if (this.mucus.charAt(3) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_3) + " (**), ";
            }
            if (this.mucus.charAt(4) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_4) + " (**), ";
            }
            if (this.mucus.charAt(5) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_5) + " (**), ";
            }
            if (this.mucus.charAt(6) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_6) + " (**), ";
            }
            if (this.mucus.charAt(7) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_7) + " (**), ";
            }
            if (this.mucus.charAt(8) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_8) + " (**), ";
            }
            if (this.mucus.charAt(9) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_9) + " (**), ";
            }
            if (this.mucus.charAt(10) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_10) + " (**), ";
            }
            if (this.mucus.charAt(11) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_11) + " (**), ";
            }
            if (this.mucus.charAt(12) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_12) + " (**), ";
            }
            if (this.mucus.charAt(13) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_13) + " (**), ";
            }
            if (this.mucus.charAt(14) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_14) + " (**), ";
            }
            if (this.mucus.charAt(15) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_15) + " (**), ";
            }
            if (this.mucus.charAt(16) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_16) + " (**), ";
            }
            if (this.mucus.charAt(17) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_17) + " (**), ";
            }
            if (this.mucus.charAt(18) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_18) + " (**), ";
            }
            if (this.mucus.charAt(19) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_19) + " (**), ";
            }
            if (this.mucus.charAt(20) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_20) + " (**), ";
            }
            if (this.mucus.charAt(21) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_21) + " (**), ";
            }
            if (this.mucus.charAt(22) == '2') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_22) + " (**), ";
            }
            if (this.mucus.charAt(0) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_0) + " (***), ";
            }
            if (this.mucus.charAt(1) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_1) + " (***), ";
            }
            if (this.mucus.charAt(2) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_2) + " (***), ";
            }
            if (this.mucus.charAt(3) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_3) + " (***), ";
            }
            if (this.mucus.charAt(4) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_4) + " (***), ";
            }
            if (this.mucus.charAt(5) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_5) + " (***), ";
            }
            if (this.mucus.charAt(6) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_6) + " (***), ";
            }
            if (this.mucus.charAt(7) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_7) + " (***), ";
            }
            if (this.mucus.charAt(8) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_8) + " (***), ";
            }
            if (this.mucus.charAt(9) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_9) + " (***), ";
            }
            if (this.mucus.charAt(10) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_10) + " (***), ";
            }
            if (this.mucus.charAt(11) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_11) + " (***), ";
            }
            if (this.mucus.charAt(12) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_12) + " (***), ";
            }
            if (this.mucus.charAt(13) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_13) + " (***), ";
            }
            if (this.mucus.charAt(14) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_14) + " (***), ";
            }
            if (this.mucus.charAt(15) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_15) + " (***), ";
            }
            if (this.mucus.charAt(16) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_16) + " (***), ";
            }
            if (this.mucus.charAt(17) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_17) + " (***), ";
            }
            if (this.mucus.charAt(18) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_18) + " (***), ";
            }
            if (this.mucus.charAt(19) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_19) + " (***), ";
            }
            if (this.mucus.charAt(20) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_20) + " (***), ";
            }
            if (this.mucus.charAt(21) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_21) + " (***), ";
            }
            if (this.mucus.charAt(22) == '3') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_22) + " (***), ";
            }
            if (this.mucus.charAt(0) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_0) + " (****), ";
            }
            if (this.mucus.charAt(1) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_1) + " (****), ";
            }
            if (this.mucus.charAt(2) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_2) + " (****), ";
            }
            if (this.mucus.charAt(3) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_3) + " (****), ";
            }
            if (this.mucus.charAt(4) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_4) + " (****), ";
            }
            if (this.mucus.charAt(5) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_5) + " (****), ";
            }
            if (this.mucus.charAt(6) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_6) + " (****), ";
            }
            if (this.mucus.charAt(7) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_7) + " (****), ";
            }
            if (this.mucus.charAt(8) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_8) + " (****), ";
            }
            if (this.mucus.charAt(9) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_9) + " (****), ";
            }
            if (this.mucus.charAt(10) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_10) + " (****), ";
            }
            if (this.mucus.charAt(11) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_11) + " (****), ";
            }
            if (this.mucus.charAt(12) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_12) + " (****), ";
            }
            if (this.mucus.charAt(13) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_13) + " (****), ";
            }
            if (this.mucus.charAt(14) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_14) + " (****), ";
            }
            if (this.mucus.charAt(15) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_15) + " (****), ";
            }
            if (this.mucus.charAt(16) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_16) + " (****), ";
            }
            if (this.mucus.charAt(17) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_17) + " (****), ";
            }
            if (this.mucus.charAt(18) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_18) + " (****), ";
            }
            if (this.mucus.charAt(19) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_19) + " (****), ";
            }
            if (this.mucus.charAt(20) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_20) + " (****), ";
            }
            if (this.mucus.charAt(21) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_21) + " (****), ";
            }
            if (this.mucus.charAt(22) == '4') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_22) + " (****), ";
            }
            if (this.mucus.charAt(0) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_0) + " (*****), ";
            }
            if (this.mucus.charAt(1) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_1) + " (*****), ";
            }
            if (this.mucus.charAt(2) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_2) + " (*****), ";
            }
            if (this.mucus.charAt(3) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_3) + " (*****), ";
            }
            if (this.mucus.charAt(4) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_4) + " (*****), ";
            }
            if (this.mucus.charAt(5) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_5) + " (*****), ";
            }
            if (this.mucus.charAt(6) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_6) + " (*****), ";
            }
            if (this.mucus.charAt(7) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_7) + " (*****), ";
            }
            if (this.mucus.charAt(8) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_8) + " (*****), ";
            }
            if (this.mucus.charAt(9) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_9) + " (*****), ";
            }
            if (this.mucus.charAt(10) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_10) + " (*****), ";
            }
            if (this.mucus.charAt(11) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_11) + " (*****), ";
            }
            if (this.mucus.charAt(12) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_12) + " (*****), ";
            }
            if (this.mucus.charAt(13) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_13) + " (*****), ";
            }
            if (this.mucus.charAt(14) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_14) + " (*****), ";
            }
            if (this.mucus.charAt(15) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_15) + " (*****), ";
            }
            if (this.mucus.charAt(16) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_16) + " (*****), ";
            }
            if (this.mucus.charAt(17) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_17) + " (*****), ";
            }
            if (this.mucus.charAt(18) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_18) + " (*****), ";
            }
            if (this.mucus.charAt(19) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_19) + " (*****), ";
            }
            if (this.mucus.charAt(20) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_20) + " (*****), ";
            }
            if (this.mucus.charAt(21) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_21) + " (*****), ";
            }
            if (this.mucus.charAt(22) == '5') {
                this.notaStringFINAL += this.context.getResources().getString(R.string.mucus_22) + " (*****), ";
            }
            this.notaStringFINAL += "\n\n";
        }
        if (this.switchshoint.equals("1")) {
            if (this.intimate == 1 && this.gommo == 0) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.note_intercourse) + " " + this.context.getResources().getString(R.string.gommo_options_unprotected) + ", ";
            }
            if (this.intimate == 1 && this.gommo == 1) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.note_intercourse) + " " + this.context.getResources().getString(R.string.gommo_options_protected) + ", ";
            }
            if (this.intimate == 1 && !this.secretnote.equals("")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.note_partner) + " " + this.secretnote + ", ";
            }
            if (this.sextimes > 0) {
                if (this.numorgasm == 0) {
                    this.notaStringFINAL += this.context.getResources().getString(R.string.gommo_options_times) + ": " + this.sextimes + "\n\n";
                }
                if (this.numorgasm > 0) {
                    this.notaStringFINAL += this.context.getResources().getString(R.string.gommo_options_times) + ": " + this.sextimes + ", " + this.context.getResources().getString(R.string.gommo_num_orgasm) + ": " + this.numorgasm + "\n\n";
                }
            }
        }
        if (this.temperature > 0.0f) {
            if (!initTempUnit.equals("C")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_temperature_title) + ": " + this.temperature + " " + this.context.getResources().getString(R.string.units_temperature_F) + "\n";
            } else {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_temperature_title) + ": " + this.temperature + " " + this.context.getResources().getString(R.string.units_temperature_C) + "\n";
            }
        }
        if (this.weight > 0.0f) {
            if (initWeightUnit.equals("kg")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_weight_title) + ": " + this.weight + " " + this.context.getResources().getString(R.string.units_weight_kg) + "\n";
            }
            if (initWeightUnit.equals("lb")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_weight_title) + ": " + this.weight + " " + this.context.getResources().getString(R.string.units_weight_lb) + "\n";
            }
            if (initWeightUnit.equals("stone")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_weight_title) + ": " + this.weight + " " + this.context.getResources().getString(R.string.units_weight_stone) + "\n";
            }
        }
        if (this.height > 0.0f) {
            if (initHeightUnit.equals("cm")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_height_title) + ": " + this.height + " " + this.context.getResources().getString(R.string.units_height_cm) + "\n";
            }
            if (initHeightUnit.equals("m")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_height_title) + ": " + this.height + " " + this.context.getResources().getString(R.string.units_height_m) + "\n";
            }
            if (initHeightUnit.equals("inch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_height_title) + ": " + this.height + " " + this.context.getResources().getString(R.string.units_height_inch) + "\n";
            }
            if (initHeightUnit.equals("feetinch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_height_title) + ": " + this.height + " " + this.context.getResources().getString(R.string.units_height_ftinch) + "\n";
            }
        }
        if (this.seno > 0.0f) {
            if (initHeightUnit.equals("cm")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_bust_title) + ": " + this.seno + " " + this.context.getResources().getString(R.string.units_height_cm) + "\n";
            }
            if (initHeightUnit.equals("m")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_bust_title) + ": " + this.seno + " " + this.context.getResources().getString(R.string.units_height_m) + "\n";
            }
            if (initHeightUnit.equals("inch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_bust_title) + ": " + this.seno + " " + this.context.getResources().getString(R.string.units_height_inch) + "\n";
            }
            if (initHeightUnit.equals("feetinch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_bust_title) + ": " + this.seno + " " + this.context.getResources().getString(R.string.units_height_ftinch) + "\n";
            }
        }
        if (this.vita > 0.0f) {
            if (initHeightUnit.equals("cm")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_waist_title) + ": " + this.vita + " " + this.context.getResources().getString(R.string.units_height_cm) + "\n";
            }
            if (initHeightUnit.equals("m")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_waist_title) + ": " + this.vita + " " + this.context.getResources().getString(R.string.units_height_m) + "\n";
            }
            if (initHeightUnit.equals("inch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_waist_title) + ": " + this.vita + " " + this.context.getResources().getString(R.string.units_height_inch) + "\n";
            }
            if (initHeightUnit.equals("feetinch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_waist_title) + ": " + this.vita + " " + this.context.getResources().getString(R.string.units_height_ftinch) + "\n";
            }
        }
        if (this.fianchi > 0.0f) {
            if (initHeightUnit.equals("cm")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_hips_title) + ": " + this.fianchi + " " + this.context.getResources().getString(R.string.units_height_cm) + "\n";
            }
            if (initHeightUnit.equals("m")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_hips_title) + ": " + this.fianchi + " " + this.context.getResources().getString(R.string.units_height_m) + "\n";
            }
            if (initHeightUnit.equals("inch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_hips_title) + ": " + this.fianchi + " " + this.context.getResources().getString(R.string.units_height_inch) + "\n";
            }
            if (initHeightUnit.equals("feetinch")) {
                this.notaStringFINAL += this.context.getResources().getString(R.string.other_hips_title) + ": " + this.fianchi + " " + this.context.getResources().getString(R.string.units_height_ftinch) + "\n";
            }
        }
        if (this.systolic > 0) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_systolic_title) + ": " + this.systolic + ", ";
        }
        if (this.diastolic > 0) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_diastolic_title) + ": " + this.diastolic + ", ";
        }
        if (this.pressure > 0) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_pressure_normal_title) + ": " + this.pressure + "\n";
        }
        if (this.ovulationtest == 1) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_ovulation_title) + ": " + this.context.getResources().getString(R.string.positive) + "\n";
            this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
        }
        if (this.ovulationtest == 2) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_ovulation_title) + ": " + this.context.getResources().getString(R.string.negative) + "\n";
        }
        if (this.testgravidanza == 1) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_pregnancy_title) + ": " + this.context.getResources().getString(R.string.positive) + "\n";
        }
        if (this.testgravidanza == 2) {
            this.notaStringFINAL += this.context.getResources().getString(R.string.other_pregnancy_title) + ": " + this.context.getResources().getString(R.string.negative) + "\n";
        }
        this.txtNote.setText(this.notaStringFINAL);
    }

    public void supermegacheck() {
        boolean usableov1min;
        boolean usableov2min;
        boolean usableov1max;
        boolean usableov2max;
        if (!this.ov1min.equals("")) {
            this.diffov1min = this.selectedDATA.getTime() - this.ov1minDATA.getTime();
            this.daysokFINALov1min = TimeUnit.DAYS.convert(this.diffov1min, TimeUnit.MILLISECONDS);
            this.diffDAYSIntFINALov1min = (int) Math.abs(this.daysokFINALov1min);
            usableov1min = true;
        } else {
            usableov1min = false;
        }
        if (!this.ov2min.equals("")) {
            this.diffov2min = this.selectedDATA.getTime() - this.ov2minDATA.getTime();
            this.daysokFINALov2min = TimeUnit.DAYS.convert(this.diffov2min, TimeUnit.MILLISECONDS);
            this.diffDAYSIntFINALov2min = (int) Math.abs(this.daysokFINALov2min);
            usableov2min = true;
        } else {
            usableov2min = false;
        }
        if (!this.ov1max.equals("")) {
            this.diffov1max = this.selectedDATA.getTime() - this.ov1maxDATA.getTime();
            this.daysokFINALov1max = TimeUnit.DAYS.convert(this.diffov1max, TimeUnit.MILLISECONDS);
            this.diffDAYSIntFINALov1max = (int) Math.abs(this.daysokFINALov1max);
            usableov1max = true;
        } else {
            usableov1max = false;
        }
        if (!this.ov2max.equals("")) {
            this.diffov2max = this.selectedDATA.getTime() - this.ov2maxDATA.getTime();
            this.daysokFINALov2max = TimeUnit.DAYS.convert(this.diffov2max, TimeUnit.MILLISECONDS);
            this.diffDAYSIntFINALov2max = (int) Math.abs(this.daysokFINALov2max);
            usableov2max = true;
        } else {
            usableov2max = false;
        }
        if (usableov1min && usableov2min && usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov1max && this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1max && this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.after(this.ov2maxDATA)) {
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov2max == 2 || this.diffDAYSIntFINALov2max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov2max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (usableov1min && usableov2min && usableov1max && !usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2min) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (usableov1min && !usableov2min && usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov1max && this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.after(this.ov2maxDATA)) {
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov2max == 2 || this.diffDAYSIntFINALov2max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov2max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (!usableov1min && usableov2min && usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1max && this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.after(this.ov2maxDATA)) {
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov2max == 2 || this.diffDAYSIntFINALov2max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov2max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (usableov1min && usableov2min && !usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2min && this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1min && this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov2min) {
                if (this.selectedDATA.after(this.ov2maxDATA)) {
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov2max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (usableov1min && !usableov2min && !usableov1max && !usableov2max) {
            if (this.selectedDATA.before(this.ov1minDATA)) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
            } else {
                if (this.diffDAYSIntFINALov1min == 0) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1min == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1min == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1min == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov1min == 4) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov1min == 5) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov1min == 6) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov1min == 7) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1min == 8) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1min == 9) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
            }
        }
        if (!usableov1min && usableov2min && !usableov1max && !usableov2max) {
            if (this.selectedDATA.before(this.ov2minDATA)) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
            } else {
                if (this.diffDAYSIntFINALov2min == 0) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2min == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2min == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2min == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov2min == 4) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov2min == 5) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov2min == 6) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov2min == 7) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2min == 8) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2min == 9) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
            }
        }
        if (!usableov1min && !usableov2min && usableov1max && !usableov2max) {
            if (this.selectedDATA.after(this.ov1maxDATA)) {
                if (this.diffDAYSIntFINALov1max == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                }
            } else {
                if (this.diffDAYSIntFINALov1max == 0) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov1max == 4) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov1max == 5) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov1max == 6) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov1max == 7) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 8) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov1max == 9) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
            }
        }
        if (!usableov1min && !usableov2min && !usableov1max && usableov2max) {
            if (this.selectedDATA.after(this.ov2maxDATA)) {
                if (this.diffDAYSIntFINALov2max == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov2max == 2 || this.diffDAYSIntFINALov2max == 3)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                }
            } else {
                if (this.diffDAYSIntFINALov2max == 0) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov2max == 4) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov2max == 5) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                }
                if (this.diffDAYSIntFINALov2max == 6) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                }
                if (this.diffDAYSIntFINALov2max == 7) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 8) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 9) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
            }
        }
        if (usableov1min && usableov2min && !usableov1max && !usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2min) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1min) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (usableov1min && !usableov2min && usableov1max && !usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov1min) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (usableov1min && !usableov2min && !usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov1min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov1minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov1min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov1min) {
                if (this.selectedDATA.after(this.ov2maxDATA)) {
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov2max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (!usableov1min && usableov2min && usableov1max && !usableov2max) {
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov1max) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2min) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (!usableov1min && usableov2min && !usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov2min <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.before(this.ov2minDATA)) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                } else {
                    if (this.diffDAYSIntFINALov2min == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2min == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2min == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max <= this.diffDAYSIntFINALov2min) {
                if (this.selectedDATA.after(this.ov2maxDATA)) {
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov2max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov2max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov2max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov2max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
        }
        if (!usableov1min && !usableov2min && usableov1max && usableov2max) {
            if (this.diffDAYSIntFINALov1max <= this.diffDAYSIntFINALov2max) {
                if (this.selectedDATA.after(this.ov1maxDATA)) {
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (!(this.diffDAYSIntFINALov1max == 1 || this.diffDAYSIntFINALov1max == 2 || this.diffDAYSIntFINALov1max == 3)) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    }
                } else {
                    if (this.diffDAYSIntFINALov1max == 0) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 1) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 2) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 3) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 4) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 5) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 6) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
                    }
                    if (this.diffDAYSIntFINALov1max == 7) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 8) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                    if (this.diffDAYSIntFINALov1max == 9) {
                        this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                    }
                }
            }
            if (this.diffDAYSIntFINALov2max > this.diffDAYSIntFINALov1max) {
                return;
            }
            if (this.selectedDATA.after(this.ov2maxDATA)) {
                if (this.diffDAYSIntFINALov2max == 1) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 2) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max == 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
                }
                if (this.diffDAYSIntFINALov2max != 1 && this.diffDAYSIntFINALov2max != 2 && this.diffDAYSIntFINALov2max != 3) {
                    this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_low));
                    return;
                }
                return;
            }
            if (this.diffDAYSIntFINALov2max == 0) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
            }
            if (this.diffDAYSIntFINALov2max == 1) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
            }
            if (this.diffDAYSIntFINALov2max == 2) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
            }
            if (this.diffDAYSIntFINALov2max == 3) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
            }
            if (this.diffDAYSIntFINALov2max == 4) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
            }
            if (this.diffDAYSIntFINALov2max == 5) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_very_high));
            }
            if (this.diffDAYSIntFINALov2max == 6) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_high));
            }
            if (this.diffDAYSIntFINALov2max == 7) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
            }
            if (this.diffDAYSIntFINALov2max == 8) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
            }
            if (this.diffDAYSIntFINALov2max == 9) {
                this.txtFertilityLevel.setText(this.context.getResources().getString(R.string.cal_fertility_level_title) + "\n" + this.context.getResources().getString(R.string.cal_fertility_level_medium));
            }
        }
    }

    public void stracazzoAGGIORNAMAIN() {
        this.ov1min = this.db.readKeySetting(this.activeUID, "ov1min");
        this.ov2min = this.db.readKeySetting(this.activeUID, "ov2min");
        this.ov1max = this.db.readKeySetting(this.activeUID, "ov1max");
        this.ov2max = this.db.readKeySetting(this.activeUID, "ov2max");
        this.oggiDateCheck = new Date();
        this.sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(this.oggiDateCheck);
        try {
            this.selectedDATA = this.formatodata.parse(this.sOggiDateCheck);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.ov1minDATA = this.formatodata.parse(this.ov1min);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        try {
            this.ov2minDATA = this.formatodata.parse(this.ov2min);
        } catch (ParseException e3) {
            e3.printStackTrace();
        }
        try {
            this.ov1maxDATA = this.formatodata.parse(this.ov1max);
        } catch (ParseException e4) {
            e4.printStackTrace();
        }
        try {
            this.ov2maxDATA = this.formatodata.parse(this.ov2max);
        } catch (ParseException e5) {
            e5.printStackTrace();
        }
        supermegacheck();
    }

    public void initializeUser() {
        this.id = this.selectedUser.getId();
        this.uid = this.selectedUser.getUid();
        this.status = this.selectedUser.getStatus();
        this.username = this.selectedUser.getUsername();
        this.password = this.selectedUser.getPassword();
        this.email = this.selectedUser.getEmail();
        this.question = this.selectedUser.getQuestion();
        this.answer = this.selectedUser.getAnswer();
        this.theme = this.selectedUser.getTheme();
        this.avatar = this.selectedUser.getAvatar();
    }
}
