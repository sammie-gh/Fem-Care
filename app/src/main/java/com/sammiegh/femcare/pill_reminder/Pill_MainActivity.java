package com.sammiegh.femcare.pill_reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sammiegh.femcare.R;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class Pill_MainActivity extends AppCompatActivity {

    public LinkedHashMap<Integer, Integer> IDmap = new LinkedHashMap<>();

    public SimpleAdapter mAdapter;
    private FloatingActionButton mAddReminderButton;

    public AlarmReceiver mAlarmReceiver;


    public RecyclerView mList;

    public MultiSelector mMultiSelector = new MultiSelector();

    public TextView mNoReminderView;

    public int mTempPost;

    public ReminderDatabase rb;


    public int getDefaultItemCount() {
        return 100;
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pill_activity_main);
        this.rb = new ReminderDatabase(getApplicationContext());
        this.mAddReminderButton = (FloatingActionButton) findViewById(R.id.add_reminder);
        this.mList = (RecyclerView) findViewById(R.id.reminder_list);
        ImageView ic_back = (ImageView) findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.mNoReminderView = (TextView) findViewById(R.id.no_reminder_text);
        if (this.rb.getAllReminders().isEmpty()) {
            this.mNoReminderView.setVisibility(View.VISIBLE);
        }
        this.mList.setLayoutManager(getLayoutManager());
        registerForContextMenu(this.mList);
        SimpleAdapter simpleAdapter = new SimpleAdapter();
        this.mAdapter = simpleAdapter;
        simpleAdapter.setItemCount(getDefaultItemCount());
        this.mList.setAdapter(this.mAdapter);
        this.mAddReminderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Pill_MainActivity.this.startActivity(new Intent(view.getContext(), ReminderAddActivity.class));
            }
        });
        this.mAlarmReceiver = new AlarmReceiver();
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        getMenuInflater().inflate(R.menu.menu_add_reminder, contextMenu);
    }


    public void selectReminder(int i) {
        String num = Integer.toString(i);
        Intent intent = new Intent(this, ReminderEditActivity.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, num);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mAdapter.setItemCount(getDefaultItemCount());
    }

    public void onResume() {
        super.onResume();
        if (this.rb.getAllReminders().isEmpty()) {
            this.mNoReminderView.setVisibility(View.VISIBLE);
        } else {
            this.mNoReminderView.setVisibility(View.GONE);
        }
        this.mAdapter.setItemCount(getDefaultItemCount());
    }


    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
    }

    public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VerticalItemHolder> {
        private ArrayList<ReminderItem> mItems = new ArrayList<>();

        public SimpleAdapter() {
        }

        public void setItemCount(int i) {
            this.mItems.clear();
            this.mItems.addAll(generateData(i));
            notifyDataSetChanged();
        }

        public void onDeleteItem(int i) {
            this.mItems.clear();
            this.mItems.addAll(generateData(i));
        }

        public void removeItemSelected(int i) {
            if (!this.mItems.isEmpty()) {
                this.mItems.remove(i);
                notifyItemRemoved(i);
            }
        }

        public VerticalItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new VerticalItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pill_recycle_items, viewGroup, false), this);
        }

        public void onBindViewHolder(VerticalItemHolder verticalItemHolder, int i) {
            ReminderItem reminderItem = this.mItems.get(i);
            verticalItemHolder.setReminderTitle(reminderItem.mTitle);
            verticalItemHolder.setReminderDateTime(reminderItem.mDateTime);
            verticalItemHolder.setReminderRepeatInfo(reminderItem.mRepeat, reminderItem.mRepeatNo, reminderItem.mRepeatType);
            verticalItemHolder.setActiveImage(reminderItem.mActive);
            verticalItemHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int intValue = ((Integer) Pill_MainActivity.this.IDmap.get(Integer.valueOf(i))).intValue();
                    Pill_MainActivity.this.rb.deleteReminder(Pill_MainActivity.this.rb.getReminder(intValue));
                    Pill_MainActivity.this.mAdapter.removeItemSelected(i);
                    Pill_MainActivity.this.mAlarmReceiver.cancelAlarm(Pill_MainActivity.this.getApplicationContext(), intValue);
                    Pill_MainActivity.this.mMultiSelector.clearSelections();
                    Pill_MainActivity.this.mAdapter.onDeleteItem(Pill_MainActivity.this.getDefaultItemCount());
                    Toast.makeText(Pill_MainActivity.this.getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public int getItemCount() {
            return this.mItems.size();
        }

        public class ReminderItem {
            public String mActive;
            public String mDateTime;
            public String mRepeat;
            public String mRepeatNo;
            public String mRepeatType;
            public String mTitle;

            public ReminderItem(String str, String str2, String str3, String str4, String str5, String str6) {
                this.mTitle = str;
                this.mDateTime = str2;
                this.mRepeat = str3;
                this.mRepeatNo = str4;
                this.mRepeatType = str5;
                this.mActive = str6;
            }
        }

        public class DateTimeComparator implements Comparator {
            DateFormat f = new SimpleDateFormat("dd/mm/yyyy hh:mm");

            public DateTimeComparator() {
            }

            public int compare(Object obj, Object obj2) {
                try {
                    return this.f.parse(((DateTimeSorter) obj).getDateTime()).compareTo(this.f.parse(((DateTimeSorter) obj2).getDateTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        public class VerticalItemHolder extends SwappingHolder implements View.OnClickListener {
            private ImageView mActiveImage;
            private ImageView iv_delete;
            private SimpleAdapter mAdapter;
            private TextView mDateAndTimeText;
            private TextView mRepeatInfoText;
            private TextView mTitleText;

            public VerticalItemHolder(View view, SimpleAdapter simpleAdapter) {
                super(view, Pill_MainActivity.this.mMultiSelector);
                view.setOnClickListener(this);
                view.setLongClickable(true);
                this.mAdapter = simpleAdapter;
                this.mTitleText = (TextView) view.findViewById(R.id.recycle_title);
                this.mDateAndTimeText = (TextView) view.findViewById(R.id.recycle_date_time);
                this.mRepeatInfoText = (TextView) view.findViewById(R.id.recycle_repeat_info);
                this.mActiveImage = (ImageView) view.findViewById(R.id.active_image);
                this.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);

            }

            public void onClick(View view) {
                if (!Pill_MainActivity.this.mMultiSelector.tapSelection(this)) {
                    int unused = Pill_MainActivity.this.mTempPost = Pill_MainActivity.this.mList.getChildAdapterPosition(view);
                    Pill_MainActivity.this.selectReminder(((Integer) Pill_MainActivity.this.IDmap.get(Integer.valueOf(Pill_MainActivity.this.mTempPost))).intValue());
                } else if (Pill_MainActivity.this.mMultiSelector.getSelectedPositions().isEmpty()) {
                    this.mAdapter.setItemCount(Pill_MainActivity.this.getDefaultItemCount());
                }
            }


            public void setReminderTitle(String str) {
                this.mTitleText.setText(str);
            }

            public void setReminderDateTime(String str) {
                this.mDateAndTimeText.setText(str);
            }

            public void setReminderRepeatInfo(String str, String str2, String str3) {
                if (str.equals("true")) {
                    TextView textView = this.mRepeatInfoText;
                    textView.setText("Every " + str2 + " " + str3 + "(s)");
                } else if (str.equals("false")) {
                    this.mRepeatInfoText.setText("Repeat Off");
                }
            }

            public void setActiveImage(String str) {
                if (!str.equals("true")) {
                    str.equals("false");
                }
            }
        }

        public ReminderItem generateDummyData() {
            return new ReminderItem("1", "2", "3", "4", "5", "6");
        }

        public List<ReminderItem> generateData(int i) {
            ArrayList arrayList = new ArrayList();
            List<Reminder> allReminders = Pill_MainActivity.this.rb.getAllReminders();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            ArrayList arrayList8 = new ArrayList();
            ArrayList<DateTimeSorter> arrayList9 = new ArrayList<>();
            for (Reminder next : allReminders) {
                arrayList2.add(next.getTitle());
                arrayList7.add(next.getDate() + " " + next.getTime());
                arrayList3.add(next.getRepeat());
                arrayList4.add(next.getRepeatNo());
                arrayList5.add(next.getRepeatType());
                arrayList6.add(next.getActive());
                arrayList8.add(Integer.valueOf(next.getID()));
            }
            int i2 = 0;
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                arrayList9.add(new DateTimeSorter(i2, (String) arrayList7.get(i3)));
                i2++;
            }
            Collections.sort(arrayList9, new DateTimeComparator());
            int i4 = 0;
            for (DateTimeSorter index : arrayList9) {
                int index2 = index.getIndex();
                ArrayList arrayList10 = arrayList2;
                ArrayList arrayList11 = arrayList3;
                ArrayList arrayList12 = arrayList4;
                ArrayList arrayList13 = arrayList8;
                ReminderItem reminderItem = new ReminderItem((String) arrayList2.get(index2), (String) arrayList7.get(index2), (String) arrayList3.get(index2), (String) arrayList4.get(index2), (String) arrayList5.get(index2), (String) arrayList6.get(index2));
                arrayList.add(reminderItem);
                Pill_MainActivity.this.IDmap.put(Integer.valueOf(i4), (Integer) arrayList13.get(index2));
                i4++;
                arrayList2 = arrayList10;
                arrayList8 = arrayList13;
                arrayList3 = arrayList11;
                arrayList4 = arrayList12;
            }
            return arrayList;
        }
    }

}
