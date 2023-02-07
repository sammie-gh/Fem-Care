package com.sammiegh.femcare.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sammiegh.femcare.model.MoodNote;
import com.sammiegh.femcare.model.Moods;
import com.sammiegh.femcare.model.Mucus;
import com.sammiegh.femcare.model.MucusNote;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.model.Pills;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.SymptomNote;
import com.sammiegh.femcare.model.Symptoms;
import com.sammiegh.femcare.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JCGSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EvaPeriodTracker.db";
    private static final String MOODS_COL_IDMOOD = "idmood";
    private static final String MUCUS_COL_IDMUCUS = "idmucus";
    private static final String NOTE_COL_DIASTOLIC = "diastolic";
    private static final String NOTE_COL_FIANCHI = "fianchi";
    private static final String NOTE_COL_GOMMO = "gommo";
    private static final String NOTE_COL_HEIGHT = "height";
    private static final String NOTE_COL_INTIMATE = "intimate";
    private static final String NOTE_COL_NOTES = "notes";
    private static final String NOTE_COL_NUMORGASM = "numorgasm";
    private static final String NOTE_COL_OVULATION_TEST = "ovulationtest";
    private static final String NOTE_COL_PILL = "pill";
    private static final String NOTE_COL_PRESSURE = "pressure";
    private static final String NOTE_COL_SECRET_NOTES = "secretnotes";
    private static final String NOTE_COL_SENO = "seno";
    private static final String NOTE_COL_SEXTIMES = "sextimes";
    private static final String NOTE_COL_SYSTOLIC = "systolic";
    private static final String NOTE_COL_TEMPERATURE = "temperature";
    private static final String NOTE_COL_TEST_GRAVIDANZA = "testgravidanza";
    private static final String NOTE_COL_VITA = "vita";
    private static final String NOTE_COL_WEIGHT = "weight";
    private static final String NOTIFICATIONS_COL_DATE_ENTRY = "dateentry";
    private static final String NOTIFICATIONS_COL_FREQUENCY = "frequency";
    private static final String NOTIFICATIONS_COL_IDNOT = "idnot";
    private static final String NOTIFICATIONS_COL_TIME_HOUR = "timehour";
    private static final String NOTIFICATIONS_COL_TIME_MIN = "timemin";
    private static final String NOTIFICATIONS_COL_USED = "used";
    private static final String PERIOD_COL_COMPLETO = "completo";
    private static final String PERIOD_COL_DAYS_CICLO = "daysciclo";
    private static final String PERIOD_COL_DAYS_MESTRUAZIONI = "daysmestruazioni";
    private static final String PERIOD_COL_DAYS_OVULATION = "daysovulation";
    private static final String PERIOD_COL_PREGNANCY = "pregnancy";
    private static final String PILLS_COL_IDPILL = "idpill";
    private static final String PILLS_COL_UID = "uid";
    private static final String SETTINGS_COL_ID = "id";
    private static final String SETTINGS_COL_KEY = "key";
    private static final String SYMPTOMS_COL_IDSYMPTOM = "idsymptom";
    private static final String TABLE_NOTE = "note";
    private static final String TABLE_NOTIFICATIONS = "notifications";
    private static final String TABLE_PERIOD = "period";
    private static final String TABLE_PILLS = "pills";
    private static final String TABLE_SETTINGS = "settings";
    private static final String TABLE_USER = "user";
    private static final String USER_COL_ANSWER = "answer";
    private static final String USER_COL_AVATAR = "avatar";
    private static final String USER_COL_QUESTION = "question";
    private static final String USER_COL_THEME = "theme";
    private static final String USER_COL_USERNAME = "username";
    int dim;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");

    public JCGSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE settings( id INTEGER, uid INTEGER, key TEXT, value TEXT, PRIMARY KEY (uid, key))");
        db.execSQL("CREATE TABLE notifications( id INTEGER, idnot INTEGER, type INTEGER, uid INTEGER, name TEXT, used INTEGER, custom INTEGER, frequency INTEGER, timehour INTEGER, timemin INTEGER, dateentry TEXT, PRIMARY KEY (idnot, type, uid))");
        db.execSQL("CREATE TABLE user( id INTEGER, uid INTEGER DEFAULT 0, status INTEGER, username TEXT, password TEXT, email TEXT, question TEXT, answer TEXT, theme INTEGER, avatar INTEGER)");
        db.execSQL("CREATE TABLE period( id INTEGER, uid INTEGER, type INTEGER, date TEXT, completo INTEGER, daysmestruazioni INTEGER, daysciclo INTEGER, daysovulation INTEGER, pregnancy INTEGER, PRIMARY KEY (uid, date))");
        db.execSQL("CREATE TABLE note( id INTEGER, uid INTEGER, date TEXT, notes TEXT, secretnotes TEXT, symptoms TEXT, mucus TEXT, moods TEXT, intimate INTEGER, gommo INTEGER, sextimes INTEGER, numorgasm INTEGER, pill TEXT, ovulationtest INTEGER, temperature REAL, weight REAL, height REAL, seno REAL, vita REAL, fianchi REAL, systolic INTEGER, diastolic INTEGER, pressure INTEGER, testgravidanza INTEGER, PRIMARY KEY (uid, date))");
        db.execSQL("CREATE TABLE moods( id INTEGER, idmood INTEGER, uid INTEGER, name TEXT, customname TEXT, imagename TEXT, nused INTEGER, hidden INTEGER, custom INTEGER, PRIMARY KEY (idmood, uid))");
        db.execSQL("CREATE TABLE symptoms( id INTEGER, idsymptom INTEGER, uid INTEGER, name TEXT, customname TEXT, imagename TEXT, nused INTEGER, hidden INTEGER, custom INTEGER, PRIMARY KEY (idsymptom, uid))");
        db.execSQL("CREATE TABLE mucus( id INTEGER, idmucus INTEGER, uid INTEGER, name TEXT, customname TEXT, imagename TEXT, nused INTEGER, hidden INTEGER, custom INTEGER, PRIMARY KEY (idmucus, uid))");
        db.execSQL("CREATE TABLE pills( id INTEGER, uid INTEGER, idpill INTEGER, name TEXT, customname TEXT, imagename TEXT, nused INTEGER, hidden INTEGER, custom INTEGER, PRIMARY KEY (idpill, uid))");
        db.execSQL("CREATE TABLE moonphases( id INTEGER, date TEXT PRIMARY KEY, moonphase TEXT)");
        db.execSQL("CREATE INDEX ixperiod1 ON period (uid, date)");
        db.execSQL("CREATE INDEX ixsettings1 ON settings (uid, key)");
        db.execSQL("CREATE INDEX ixmoods1 ON moods (uid, idmood)");
        db.execSQL("CREATE INDEX ixsymptoms1 ON symptoms (uid, idsymptom)");
        db.execSQL("CREATE INDEX ixmucus1 ON mucus (uid, idmucus)");
        db.execSQL("CREATE INDEX ixnote1 ON note (uid, date)");
        db.execSQL("CREATE INDEX ixpills1 ON pills (uid, idpill)");
        db.execSQL("CREATE INDEX ixmoonphase1 ON moonphases (date)");
        db.execSQL("CREATE INDEX ixnotifications1 ON notifications (uid, idnot, type)");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (1, '20140101', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (2, '20140108', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (3, '20140116', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (4, '20140124', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (5, '20140130', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (6, '20140206', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (7, '20140214', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (8, '20140222', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (9, '20140301', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (10, '20140308', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (11, '20140316', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (12, '20140324', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (13, '20140330', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (14, '20140407', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (15, '20140415', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (16, '20140422', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (17, '20140429', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (18, '20140507', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (19, '20140514', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (20, '20140521', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (21, '20140528', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (22, '20140605', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (23, '20140613', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (24, '20140619', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (25, '20140627', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (26, '20140705', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (27, '20140712', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (28, '20140719', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (29, '20140726', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (30, '20140804', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (31, '20140810', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (32, '20140817', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (33, '20140825', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (34, '20140902', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (35, '20140909', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (36, '20140916', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (37, '20140924', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (38, '20141001', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (39, '20141008', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (40, '20141015', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (41, '20141023', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (42, '20141031', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (43, '20141106', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (44, '20141114', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (45, '20141122', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (46, '20141129', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (47, '20141206', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (48, '20141214', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (49, '20141222', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (50, '20141228', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (51, '20150105', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (52, '20150113', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (53, '20150120', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (54, '20150127', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (55, '20150203', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (56, '20150212', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (57, '20150218', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (58, '20150225', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (59, '20150305', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (60, '20150313', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (61, '20150320', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (62, '20150327', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (63, '20150404', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (64, '20150412', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (65, '20150418', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (66, '20150425', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (67, '20150504', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (68, '20150511', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (69, '20150518', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (70, '20150525', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (71, '20150602', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (72, '20150609', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (73, '20150616', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (74, '20150624', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (75, '20150702', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (76, '20150708', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (77, '20150716', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (78, '20150724', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (79, '20150731', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (80, '20150807', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (81, '20150814', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (82, '20150822', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (83, '20150829', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (84, '20150905', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (85, '20150913', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (86, '20150921', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (87, '20150928', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (88, '20151004', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (89, '20151013', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (90, '20151020', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (91, '20151027', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (92, '20151103', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (93, '20151111', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (94, '20151119', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (95, '20151125', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (96, '20151203', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (97, '20151211', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (98, '20151218', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (99, '20151225', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (100, '20160102', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (101, '20160110', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (102, '20160116', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (103, '20160124', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (104, '20160201', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (105, '20160208', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (106, '20160215', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (107, '20160222', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (108, '20160301', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (109, '20160309', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (110, '20160315', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (111, '20160323', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (112, '20160331', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (113, '20160407', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (114, '20160414', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (115, '20160422', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (116, '20160430', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (117, '20160506', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (118, '20160513', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (119, '20160521', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (120, '20160529', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (121, '20160605', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (122, '20160612', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (123, '20160620', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (124, '20160627', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (125, '20160704', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (126, '20160712', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (127, '20160719', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (128, '20160726', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (129, '20160802', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (130, '20160810', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (131, '20160818', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (132, '20160825', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (133, '20160901', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (134, '20160909', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (135, '20160916', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (136, '20160923', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (137, '20161001', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (138, '20161009', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (139, '20161016', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (140, '20161022', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (141, '20161030', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (142, '20161107', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (143, '20161114', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (144, '20161121', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (145, '20161129', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (146, '20161207', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (147, '20161214', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (148, '20161221', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (149, '20161229', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (150, '20170105', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (151, '20170112', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (152, '20170119', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (153, '20170128', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (154, '20170204', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (155, '20170211', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (156, '20170218', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (157, '20170226', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (158, '20170305', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (159, '20170312', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (160, '20170320', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (161, '20170328', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (162, '20170403', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (163, '20170411', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (164, '20170419', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (165, '20170426', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (166, '20170503', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (167, '20170510', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (168, '20170519', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (169, '20170525', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (170, '20170601', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (171, '20170609', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (172, '20170617', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (173, '20170624', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (174, '20170701', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (175, '20170709', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (176, '20170716', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (177, '20170723', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (178, '20170730', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (179, '20170807', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (180, '20170815', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (181, '20170821', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (182, '20170829', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (183, '20170906', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (184, '20170913', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (185, '20170920', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (186, '20170928', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (187, '20171005', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (188, '20171012', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (189, '20171019', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (190, '20171027', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (191, '20171104', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (192, '20171110', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (193, '20171118', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (194, '20171126', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (195, '20171203', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (196, '20171210', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (197, '20171218', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (198, '20171226', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (199, '20180102', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (200, '20180108', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (201, '20180117', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (202, '20180124', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (203, '20180131', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (204, '20180207', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (205, '20180215', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (206, '20180223', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (207, '20180302', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (208, '20180309', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (209, '20180317', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (210, '20180324', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (211, '20180331', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (212, '20180408', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (213, '20180416', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (214, '20180422', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (215, '20180430', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (216, '20180508', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (217, '20180515', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (218, '20180522', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (219, '20180529', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (220, '20180606', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (221, '20180613', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (222, '20180620', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (223, '20180628', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (224, '20180706', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (225, '20180713', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (226, '20180719', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (227, '20180727', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (228, '20180804', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (229, '20180811', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (230, '20180818', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (231, '20180826', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (232, '20180903', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (233, '20180909', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (234, '20180916', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (235, '20180925', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (236, '20181002', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (237, '20181009', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (238, '20181016', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (239, '20181024', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (240, '20181031', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (241, '20181107', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (242, '20181115', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (243, '20181123', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (244, '20181130', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (245, '20181207', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (246, '20181215', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (247, '20181222', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (248, '20181229', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (249, '20190106', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (250, '20190114', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (251, '20190121', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (252, '20190127', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (253, '20190204', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (254, '20190212', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (255, '20190219', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (256, '20190226', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (257, '20190306', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (258, '20190314', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (259, '20190321', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (260, '20190328', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (261, '20190405', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (262, '20190412', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (263, '20190419', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (264, '20190426', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (265, '20190504', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (266, '20190512', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (267, '20190518', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (268, '20190526', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (269, '20190603', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (270, '20190610', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (271, '20190617', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (272, '20190625', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (273, '20190702', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (274, '20190709', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (275, '20190716', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (276, '20190725', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (277, '20190801', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (278, '20190807', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (279, '20190815', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (280, '20190823', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (281, '20190830', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (282, '20190906', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (283, '20190914', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (284, '20190922', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (285, '20190928', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (286, '20191005', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (287, '20191013', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (288, '20191021', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (289, '20191028', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (290, '20191104', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (291, '20191112', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (292, '20191119', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (293, '20191126', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (294, '20191204', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (295, '20191212', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (296, '20191219', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (297, '20191226', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (298, '20200103', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (299, '20200110', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (300, '20200117', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (301, '20200124', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (302, '20200202', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (303, '20200209', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (304, '20200215', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (305, '20200223', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (306, '20200302', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (307, '20200309', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (308, '20200316', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (309, '20200324', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (310, '20200401', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (311, '20200408', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (312, '20200414', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (313, '20200423', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (314, '20200430', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (315, '20200507', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (316, '20200514', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (317, '20200522', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (318, '20200530', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (319, '20200605', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (320, '20200613', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (321, '20200621', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (322, '20200628', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (323, '20200705', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (324, '20200712', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (325, '20200720', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (326, '20200727', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (327, '20200803', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (328, '20200811', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (329, '20200819', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (330, '20200825', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (331, '20200902', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (332, '20200910', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (333, '20200917', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (334, '20200924', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (335, '20201001', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (336, '20201010', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (337, '20201016', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (338, '20201023', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (339, '20201031', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (340, '20201108', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (341, '20201115', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (342, '20201122', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (343, '20201130', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (344, '20201208', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (345, '20201214', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (346, '20201221', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (347, '20201230', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (348, '20210106', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (349, '20210113', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (350, '20210120', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (351, '20210128', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (352, '20210204', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (353, '20210211', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (354, '20210219', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (355, '20210227', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (356, '20210306', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (357, '20210313', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (358, '20210321', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (359, '20210328', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (360, '20210404', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (361, '20210412', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (362, '20210420', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (363, '20210427', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (364, '20210503', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (365, '20210511', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (366, '20210519', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (367, '20210526', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (368, '20210602', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (369, '20210610', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (370, '20210618', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (371, '20210624', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (372, '20210701', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (373, '20210710', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (374, '20210717', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (375, '20210724', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (376, '20210731', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (377, '20210808', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (378, '20210815', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (379, '20210822', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (380, '20210830', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (381, '20210907', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (382, '20210913', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (383, '20210920', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (384, '20210929', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (385, '20211006', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (386, '20211013', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (387, '20211020', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (388, '20211028', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (389, '20211104', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (390, '20211111', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (391, '20211119', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (392, '20211127', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (393, '20211204', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (394, '20211211', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (395, '20211219', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (396, '20211227', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (397, '20220102', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (398, '20220109', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (399, '20220117', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (400, '20220125', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (401, '20220201', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (402, '20220208', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (403, '20220216', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (404, '20220223', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (405, '20220302', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (406, '20220310', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (407, '20220318', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (408, '20220325', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (409, '20220401', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (410, '20220409', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (411, '20220416', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (412, '20220423', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (413, '20220430', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (414, '20220509', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (415, '20220516', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (416, '20220522', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (417, '20220530', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (418, '20220607', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (419, '20220614', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (420, '20220621', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (421, '20220629', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (422, '20220707', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (423, '20220713', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (424, '20220720', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (425, '20220728', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (426, '20220805', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (427, '20220812', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (428, '20220819', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (429, '20220827', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (430, '20220903', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (431, '20220910', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (432, '20220917', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (433, '20220925', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (434, '20221003', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (435, '20221009', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (436, '20221017', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (437, '20221025', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (438, '20221101', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (439, '20221108', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (440, '20221116', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (441, '20221123', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (442, '20221130', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (443, '20221208', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (444, '20221216', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (445, '20221223', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (446, '20221230', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (447, '20230106', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (448, '20230115', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (449, '20230121', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (450, '20230128', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (451, '20230205', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (452, '20230213', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (453, '20230220', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (454, '20230227', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (455, '20230307', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (456, '20230315', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (457, '20230321', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (458, '20230329', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (459, '20230406', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (460, '20230413', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (461, '20230420', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (462, '20230427', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (463, '20230505', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (464, '20230512', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (465, '20230519', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (466, '20230527', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (467, '20230604', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (468, '20230610', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (469, '20230618', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (470, '20230626', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (471, '20230703', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (472, '20230710', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (473, '20230717', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (474, '20230725', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (475, '20230801', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (476, '20230808', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (477, '20230816', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (478, '20230824', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (479, '20230831', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (480, '20230906', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (481, '20230915', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (482, '20230922', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (483, '20230929', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (484, '20231006', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (485, '20231014', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (486, '20231022', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (487, '20231028', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (488, '20231105', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (489, '20231113', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (490, '20231120', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (491, '20231127', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (492, '20231205', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (493, '20231212', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (494, '20231219', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (495, '20231227', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (496, '20240104', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (497, '20240111', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (498, '20240118', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (499, '20240125', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (500, '20240202', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (501, '20240209', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (502, '20240216', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (503, '20240224', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (504, '20240303', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (505, '20240310', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (506, '20240317', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (507, '20240325', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (508, '20240402', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (509, '20240408', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (510, '20240415', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (511, '20240423', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (512, '20240501', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (513, '20240508', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (514, '20240515', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (515, '20240523', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (516, '20240530', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (517, '20240606', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (518, '20240614', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (519, '20240622', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (520, '20240628', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (521, '20240705', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (522, '20240713', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (523, '20240721', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (524, '20240728', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (525, '20240804', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (526, '20240812', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (527, '20240819', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (528, '20240826', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (529, '20240903', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (530, '20240911', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (531, '20240918', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (532, '20240924', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (533, '20241002', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (534, '20241010', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (535, '20241017', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (536, '20241024', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (537, '20241101', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (538, '20241109', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (539, '20241115', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (540, '20241123', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (541, '20241201', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (542, '20241208', 'FQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (543, '20241215', 'FM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (544, '20241222', 'LQ');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (545, '20241230', 'NM');");
        db.execSQL("INSERT INTO moonphases (id, date, moonphase) VALUES (546, '20250106', 'FQ');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS settings");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS moods");
        db.execSQL("DROP TABLE IF EXISTS moonphases");
        db.execSQL("DROP TABLE IF EXISTS note");
        db.execSQL("DROP TABLE IF EXISTS period");
        db.execSQL("DROP TABLE IF EXISTS pills");
        db.execSQL("DROP TABLE IF EXISTS symptoms");
        db.execSQL("DROP TABLE IF EXISTS mucus");
        db.execSQL("DROP TABLE IF EXISTS notifications");
        onCreate(db);
    }

    public void insertSettings(Settings settings) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(settings.getId()));
        values.put("uid", Integer.valueOf(settings.getUid()));
        values.put(SETTINGS_COL_KEY, settings.getKey());
        values.put("value", settings.getValueKey());
        db.insert(TABLE_SETTINGS, null, values);
        db.close();
    }

    public void insertNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(note.getId()));
        values.put("uid", Integer.valueOf(note.getUid()));
        values.put("date", note.getDate());
        values.put(NOTE_COL_NOTES, note.getNotes());
        values.put(NOTE_COL_SECRET_NOTES, note.getSecretNotes());
        values.put("symptoms", note.getSymptoms());
        values.put("mucus", note.getMucus());
        values.put("moods", note.getMoods());
        values.put(NOTE_COL_INTIMATE, Integer.valueOf(note.getIntimate()));
        values.put(NOTE_COL_GOMMO, Integer.valueOf(note.getGommo()));
        values.put(NOTE_COL_SEXTIMES, Integer.valueOf(note.getSextimes()));
        values.put(NOTE_COL_NUMORGASM, Integer.valueOf(note.getNumorgasm()));
        values.put(NOTE_COL_PILL, note.getPill());
        values.put(NOTE_COL_OVULATION_TEST, Integer.valueOf(note.getOvulationtest()));
        values.put(NOTE_COL_TEMPERATURE, Float.valueOf(note.getTemperature()));
        values.put(NOTE_COL_WEIGHT, Float.valueOf(note.getWeight()));
        values.put(NOTE_COL_HEIGHT, Float.valueOf(note.getHeight()));
        values.put(NOTE_COL_SENO, Float.valueOf(note.getSeno()));
        values.put(NOTE_COL_VITA, Float.valueOf(note.getVita()));
        values.put(NOTE_COL_FIANCHI, Float.valueOf(note.getFianchi()));
        values.put(NOTE_COL_SYSTOLIC, Integer.valueOf(note.getSystolic()));
        values.put(NOTE_COL_DIASTOLIC, Integer.valueOf(note.getDiastolic()));
        values.put(NOTE_COL_PRESSURE, Integer.valueOf(note.getPressure()));
        values.put(NOTE_COL_TEST_GRAVIDANZA, Integer.valueOf(note.getTestgravidanza()));
        db.insert(TABLE_NOTE, null, values);
        db.close();
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(note.getId()));
        values.put("uid", Integer.valueOf(note.getUid()));
        values.put("date", note.getDate());
        values.put(NOTE_COL_NOTES, note.getNotes());
        values.put(NOTE_COL_SECRET_NOTES, note.getSecretNotes());
        values.put("symptoms", note.getSymptoms());
        values.put("mucus", note.getMucus());
        values.put("moods", note.getMoods());
        values.put(NOTE_COL_INTIMATE, Integer.valueOf(note.getIntimate()));
        values.put(NOTE_COL_GOMMO, Integer.valueOf(note.getGommo()));
        values.put(NOTE_COL_SEXTIMES, Integer.valueOf(note.getSextimes()));
        values.put(NOTE_COL_NUMORGASM, Integer.valueOf(note.getNumorgasm()));
        values.put(NOTE_COL_PILL, note.getPill());
        values.put(NOTE_COL_OVULATION_TEST, Integer.valueOf(note.getOvulationtest()));
        values.put(NOTE_COL_TEMPERATURE, Float.valueOf(note.getTemperature()));
        values.put(NOTE_COL_WEIGHT, Float.valueOf(note.getWeight()));
        values.put(NOTE_COL_HEIGHT, Float.valueOf(note.getHeight()));
        values.put(NOTE_COL_SENO, Float.valueOf(note.getSeno()));
        values.put(NOTE_COL_VITA, Float.valueOf(note.getVita()));
        values.put(NOTE_COL_FIANCHI, Float.valueOf(note.getFianchi()));
        values.put(NOTE_COL_SYSTOLIC, Integer.valueOf(note.getSystolic()));
        values.put(NOTE_COL_DIASTOLIC, Integer.valueOf(note.getDiastolic()));
        values.put(NOTE_COL_PRESSURE, Integer.valueOf(note.getPressure()));
        values.put(NOTE_COL_TEST_GRAVIDANZA, Integer.valueOf(note.getTestgravidanza()));
        int i = db.update(TABLE_NOTE, values, "uid = ? AND date = ? ", new String[]{String.valueOf(note.getUid()), String.valueOf(note.getDate())});
        db.close();
        return i;
    }

    public void insertPeriod(Period period) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(period.getId()));
        values.put("uid", Integer.valueOf(period.getUid()));
        values.put("type", Integer.valueOf(period.getType()));
        values.put("date", period.getDate());
        values.put(PERIOD_COL_COMPLETO, Integer.valueOf(period.getCompleto()));
        values.put(PERIOD_COL_DAYS_MESTRUAZIONI, Integer.valueOf(period.getDaysMestruazioni()));
        values.put(PERIOD_COL_DAYS_CICLO, Integer.valueOf(period.getDaysCiclo()));
        values.put(PERIOD_COL_DAYS_OVULATION, Integer.valueOf(period.getDaysOvulation()));
        values.put(PERIOD_COL_PREGNANCY, Integer.valueOf(period.getPregnancy()));
        db.insert(TABLE_PERIOD, null, values);
        db.close();
    }

    public int updatePeriod(Period period) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(period.getId()));
        values.put("uid", Integer.valueOf(period.getUid()));
        values.put("type", Integer.valueOf(period.getType()));
        values.put("date", period.getDate());
        values.put(PERIOD_COL_COMPLETO, Integer.valueOf(period.getCompleto()));
        values.put(PERIOD_COL_DAYS_MESTRUAZIONI, Integer.valueOf(period.getDaysMestruazioni()));
        values.put(PERIOD_COL_DAYS_CICLO, Integer.valueOf(period.getDaysCiclo()));
        values.put(PERIOD_COL_DAYS_OVULATION, Integer.valueOf(period.getDaysOvulation()));
        values.put(PERIOD_COL_PREGNANCY, Integer.valueOf(period.getPregnancy()));
        int i = db.update(TABLE_PERIOD, values, "uid = ? AND date = ? ", new String[]{String.valueOf(period.getUid()), String.valueOf(period.getDate())});
        db.close();
        return i;
    }

    public void insertMoods(Moods moods) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(moods.getId()));
        values.put(MOODS_COL_IDMOOD, Integer.valueOf(moods.getIdMood()));
        values.put("uid", Integer.valueOf(moods.getUid()));
        values.put("name", moods.getName());
        values.put("customname", moods.getCustomName());
        values.put("imagename", moods.getImageName());
        values.put("nused", Integer.valueOf(moods.getNused()));
        values.put("hidden", Integer.valueOf(moods.getHidden()));
        values.put("custom", Integer.valueOf(moods.getCustom()));
        db.insert("moods", null, values);
        db.close();
    }

    public void insertSymptoms(Symptoms symptoms) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(symptoms.getId()));
        values.put("uid", Integer.valueOf(symptoms.getUid()));
        values.put(SYMPTOMS_COL_IDSYMPTOM, Integer.valueOf(symptoms.getIdSymptoms()));
        values.put("name", symptoms.getName());
        values.put("customname", symptoms.getCustomName());
        values.put("imagename", symptoms.getImageName());
        values.put("nused", Integer.valueOf(symptoms.getNused()));
        values.put("hidden", Integer.valueOf(symptoms.getHidden()));
        values.put("custom", Integer.valueOf(symptoms.getCustom()));
        db.insert("symptoms", null, values);
        db.close();
    }

    public void insertMucus(Mucus mucus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(mucus.getId()));
        values.put("uid", Integer.valueOf(mucus.getUid()));
        values.put(MUCUS_COL_IDMUCUS, Integer.valueOf(mucus.getIdMucus()));
        values.put("name", mucus.getName());
        values.put("customname", mucus.getCustomName());
        values.put("imagename", mucus.getImageName());
        values.put("nused", Integer.valueOf(mucus.getNused()));
        values.put("hidden", Integer.valueOf(mucus.getHidden()));
        values.put("custom", Integer.valueOf(mucus.getCustom()));
        db.insert("mucus", null, values);
        db.close();
    }

    public void insertPills(Pills pills) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(pills.getId()));
        values.put("uid", Integer.valueOf(pills.getUid()));
        values.put(PILLS_COL_IDPILL, Integer.valueOf(pills.getIdPill()));
        values.put("name", pills.getName());
        values.put("customname", pills.getCustomName());
        values.put("imagename", pills.getImageName());
        values.put("nused", Integer.valueOf(pills.getNused()));
        values.put("hidden", Integer.valueOf(pills.getHidden()));
        values.put("custom", Integer.valueOf(pills.getCustom()));
        db.insert(TABLE_PILLS, null, values);
        db.close();
    }

    public void insertNotifications(Notifications notifications) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(notifications.getId()));
        values.put(NOTIFICATIONS_COL_IDNOT, Integer.valueOf(notifications.getIdnotifications()));
        values.put("type", Integer.valueOf(notifications.getType()));
        values.put("uid", Integer.valueOf(notifications.getUid()));
        values.put("name", notifications.getName());
        values.put(NOTIFICATIONS_COL_USED, Integer.valueOf(notifications.getUsed()));
        values.put("custom", Integer.valueOf(notifications.getCustom()));
        values.put(NOTIFICATIONS_COL_FREQUENCY, Integer.valueOf(notifications.getFrequency()));
        values.put(NOTIFICATIONS_COL_TIME_HOUR, Integer.valueOf(notifications.getTimeHour()));
        values.put(NOTIFICATIONS_COL_TIME_MIN, Integer.valueOf(notifications.getTimeMin()));
        values.put(NOTIFICATIONS_COL_DATE_ENTRY, notifications.getDateEntry());
        db.insert(TABLE_NOTIFICATIONS, null, values);
        db.close();
    }

    public void insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(user.getId()));
        values.put("uid", Integer.valueOf(user.getUid()));
        values.put("status", Integer.valueOf(user.getStatus()));
        values.put(USER_COL_USERNAME, user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put(USER_COL_QUESTION, user.getQuestion());
        values.put(USER_COL_ANSWER, user.getAnswer());
        values.put(USER_COL_THEME, Integer.valueOf(user.getTheme()));
        values.put(USER_COL_AVATAR, Integer.valueOf(user.getAvatar()));
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public Cursor readAllUser() {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM user WHERE status!= 1 ORDER BY uid ASC", null);
    }

    public Cursor readAllMoods(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM moods WHERE uid = " + uidActive + " ORDER BY " + MOODS_COL_IDMOOD + " ASC", null);
    }

    public Cursor readAllPills(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM pills WHERE uid = " + uidActive + " ORDER BY " + PILLS_COL_IDPILL + " ASC", null);
    }

    public Cursor readAllAlarmsType(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM notifications WHERE uid = " + uidActive + " AND " + NOTIFICATIONS_COL_IDNOT + " = " + 0 + " ORDER BY " + NOTIFICATIONS_COL_IDNOT + " ASC", null);
    }

    public Cursor readDetailsAlarm(int uidActive, int Type) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM notifications WHERE uid = " + uidActive + " AND " + "type" + " = " + Type + " ORDER BY " + "type" + " ASC", null);
    }

    public int updateNotificationCheck(int uidTarget, int idType, int idNot, int check) {
        SQLiteDatabase db = getWritableDatabase();
        if (check == 1) {
            db.execSQL("UPDATE notifications SET used = " + 1 + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " = " + idType + " AND " + NOTIFICATIONS_COL_IDNOT + " = " + idNot);
        } else {
            db.execSQL("UPDATE notifications SET used = " + 0 + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " = " + idType + " AND " + NOTIFICATIONS_COL_IDNOT + " = " + idNot);
        }
        return 1;
    }

    public Cursor readAllAlarmsUSED(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM notifications WHERE uid = " + uidActive + " AND " + NOTIFICATIONS_COL_USED + " = " + 1 + " ORDER BY " + NOTIFICATIONS_COL_IDNOT + " ASC", null);
    }

    public Cursor readAllMoodsNONHidden(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM moods WHERE uid = " + uidActive + " AND " + "hidden" + " = " + 1 + " ORDER BY " + MOODS_COL_IDMOOD + " ASC", null);
    }

    public Cursor readAllPillsNONHidden(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM pills WHERE uid = " + uidActive + " AND " + "hidden" + " = " + 1 + " ORDER BY " + PILLS_COL_IDPILL + " ASC", null);
    }

    public Cursor readAllBaseMoods(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM moods WHERE uid = " + uidActive + " ORDER BY " + MOODS_COL_IDMOOD + " ASC LIMIT 76", null);
    }

    public Cursor readAllBasePills(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM pills WHERE uid = " + uidActive + " ORDER BY " + PILLS_COL_IDPILL + " ASC LIMIT 21", null);
    }

    public int selectMaxMoodUid(int uidActive) {
        Cursor c = getReadableDatabase().rawQuery("SELECT MAX(idmood) FROM moods WHERE uid = " + uidActive, null);
        c.moveToFirst();
        int idMAX = c.getInt(0);
        c.close();
        return idMAX;
    }

    public int selectMAXNotification(int uidActive) {
        Cursor c = getReadableDatabase().rawQuery("SELECT MAX(type) FROM notifications WHERE uid = " + uidActive, null);
        c.moveToFirst();
        int idMAX = c.getInt(0);
        c.close();
        return idMAX;
    }

    public String selectNameNotification(int uidActive, int typePos) {
        Cursor c = getReadableDatabase().rawQuery("SELECT name FROM notifications WHERE uid = " + uidActive + " AND " + "type" + " = " + typePos, null);
        String str = "";
        c.moveToFirst();
        String idName = c.getString(0);
        c.close();
        return idName;
    }

    public int selectMaxPillUid(int uidActive) {
        Cursor c = getReadableDatabase().rawQuery("SELECT MAX(idpill) FROM pills WHERE uid = " + uidActive, null);
        c.moveToFirst();
        int idMAX = c.getInt(0);
        c.close();
        return idMAX;
    }

    public Cursor readAllSymptoms(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM symptoms WHERE uid = " + uidActive + " ORDER BY " + SYMPTOMS_COL_IDSYMPTOM + " ASC", null);
    }

    public Cursor readAllSymptomsNONHidden(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM symptoms WHERE uid = " + uidActive + " AND " + "hidden" + " = " + 1 + " ORDER BY " + SYMPTOMS_COL_IDSYMPTOM + " ASC", null);
    }

    public Cursor readAllMucus(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM mucus WHERE uid = " + uidActive + " ORDER BY " + MUCUS_COL_IDMUCUS + " ASC", null);
    }

    public Cursor readAllBaseSymptoms(int uidActive) {
        return getWritableDatabase().rawQuery("SELECT rowid _id, * FROM symptoms WHERE uid = " + uidActive + " ORDER BY " + SYMPTOMS_COL_IDSYMPTOM + " ASC LIMIT 55", null);
    }

    public int selectMaxSymptomUid(int uidActive) {
        Cursor c = getReadableDatabase().rawQuery("SELECT MAX(idsymptom) FROM symptoms WHERE uid = " + uidActive, null);
        c.moveToFirst();
        int idMAX = c.getInt(0);
        c.close();
        return idMAX;
    }

    public Settings readSettings(int uid) {
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM settings WHERE uid = " + uid, null);
        Settings settings = new Settings();
        if (c != null) {
            c.moveToFirst();
        }
        settings.setId(c.getInt(c.getColumnIndex("id")));
        settings.setUid(c.getInt(c.getColumnIndex("uid")));
        settings.setKey(c.getString(c.getColumnIndex(SETTINGS_COL_KEY)));
        settings.setValueKey(c.getString(c.getColumnIndex("value")));
        return settings;
    }

    public Period readPeriod(int uid, String dateRef) {
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM period WHERE uid = " + uid + " AND " + "date" + " = " + dateRef, null);
        Period period = new Period();
        if (c != null) {
            c.moveToFirst();
        }
        period.setId(c.getInt(c.getColumnIndex("id")));
        period.setUid(c.getInt(c.getColumnIndex("uid")));
        period.setType(c.getInt(c.getColumnIndex("type")));
        period.setDate(c.getString(c.getColumnIndex("date")));
        period.setCompleto(c.getInt(c.getColumnIndex(PERIOD_COL_COMPLETO)));
        period.setDaysMestruazioni(c.getInt(c.getColumnIndex(PERIOD_COL_DAYS_MESTRUAZIONI)));
        period.setDaysCiclo(c.getInt(c.getColumnIndex(PERIOD_COL_DAYS_CICLO)));
        period.setDaysOvulation(c.getInt(c.getColumnIndex(PERIOD_COL_DAYS_OVULATION)));
        period.setPregnancy(c.getInt(c.getColumnIndex(PERIOD_COL_PREGNANCY)));
        return period;
    }

    public int countRowsNote(int uid, String date) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM note WHERE uid = " + uid + " AND " + "date" + " = " + date, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsPeriod(int uid, String date) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM period WHERE uid = " + uid + " AND " + "date" + " = " + date, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsAssPeriod(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM period WHERE uid = " + uid, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsAssPeriodChart(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM period WHERE uid = " + uid + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsAssNote(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM note WHERE uid = " + uid, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public Note readNote(int uid, String date) {
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM note WHERE uid = " + uid + " AND " + "date" + " = " + date, null);
        Note note = new Note();
        if (c != null) {
            c.moveToFirst();
        }
        note.setId(c.getInt(c.getColumnIndex("id")));
        note.setUid(c.getInt(c.getColumnIndex("uid")));
        note.setDate(c.getString(c.getColumnIndex("date")));
        note.setNotes(c.getString(c.getColumnIndex(NOTE_COL_NOTES)));
        note.setSecretNotes(c.getString(c.getColumnIndex(NOTE_COL_SECRET_NOTES)));
        note.setSymptoms(c.getString(c.getColumnIndex("symptoms")));
        note.setMucus(c.getString(c.getColumnIndex("mucus")));
        note.setMoods(c.getString(c.getColumnIndex("moods")));
        note.setIntimate(c.getInt(c.getColumnIndex(NOTE_COL_INTIMATE)));
        note.setGommo(c.getInt(c.getColumnIndex(NOTE_COL_GOMMO)));
        note.setSextimes(c.getInt(c.getColumnIndex(NOTE_COL_SEXTIMES)));
        note.setNumorgasm(c.getInt(c.getColumnIndex(NOTE_COL_NUMORGASM)));
        note.setPill(c.getString(c.getColumnIndex(NOTE_COL_PILL)));
        note.setOvulationtest(c.getInt(c.getColumnIndex(NOTE_COL_OVULATION_TEST)));
        note.setTemperature(c.getFloat(c.getColumnIndex(NOTE_COL_TEMPERATURE)));
        note.setWeight(c.getFloat(c.getColumnIndex(NOTE_COL_WEIGHT)));
        note.setHeight(c.getFloat(c.getColumnIndex(NOTE_COL_HEIGHT)));
        note.setSeno(c.getFloat(c.getColumnIndex(NOTE_COL_SENO)));
        note.setVita(c.getFloat(c.getColumnIndex(NOTE_COL_VITA)));
        note.setFianchi((float) c.getInt(c.getColumnIndex(NOTE_COL_FIANCHI)));
        note.setSystolic(c.getInt(c.getColumnIndex(NOTE_COL_SYSTOLIC)));
        note.setDiastolic(c.getInt(c.getColumnIndex(NOTE_COL_DIASTOLIC)));
        note.setPressure(c.getInt(c.getColumnIndex(NOTE_COL_PRESSURE)));
        note.setTestgravidanza(c.getInt(c.getColumnIndex(NOTE_COL_TEST_GRAVIDANZA)));
        return note;
    }

    public User readUser(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM user WHERE uid = " + uid, null);
        User user = new User();
        if (c != null) {
            c.moveToFirst();
        }
        user.setId(c.getInt(c.getColumnIndex("id")));
        user.setUid(c.getInt(c.getColumnIndex("uid")));
        user.setStatus(c.getInt(c.getColumnIndex("status")));
        user.setUsername(c.getString(c.getColumnIndex(USER_COL_USERNAME)));
        user.setPassword(c.getString(c.getColumnIndex("password")));
        user.setEmail(c.getString(c.getColumnIndex("email")));
        user.setQuestion(c.getString(c.getColumnIndex(USER_COL_QUESTION)));
        user.setAvatar(c.getInt(c.getColumnIndex(USER_COL_AVATAR)));
        user.setAnswer(c.getString(c.getColumnIndex(USER_COL_ANSWER)));
        user.setTheme(c.getInt(c.getColumnIndex(USER_COL_THEME)));
        return user;
    }

    public String readPwdUser() {
        Cursor c = getReadableDatabase().rawQuery("SELECT password FROM user WHERE status = " + 1, null);
        c.moveToFirst();
        String pwdUser = c.getString(0);
        c.close();
        return pwdUser;
    }

    public int readActiveUID() {
        Cursor c = getWritableDatabase().rawQuery("SELECT uid FROM user WHERE status = " + 1, null);
        c.moveToFirst();
        int UIDactive = c.getInt(0);
        c.close();
        return UIDactive;
    }

    public String readKeySetting(int uid, String key) {
        Cursor c = getReadableDatabase().rawQuery("SELECT value FROM settings WHERE uid = " + uid + " AND " + SETTINGS_COL_KEY + " = '" + key + "'", null);
        c.moveToFirst();
        String valueRet = c.getString(0);
        c.close();
        return valueRet;
    }

    public int updateSettings(Settings settings) {
        getWritableDatabase().execSQL("UPDATE settings SET value = '" + settings.getValueKey() + "' WHERE " + "uid" + " = " + settings.getUid() + " AND " + SETTINGS_COL_KEY + " = '" + settings.getKey() + "'");
        return 1;
    }

    public int deleteUser(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM user WHERE uid = " + uidUser);
        return 1;
    }

    public int deleteNotifications(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM notifications WHERE uid = " + uidUser);
        return 1;
    }

    public int deleteMoods(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM moods WHERE uid = " + uidUser);
        return 1;
    }

    public int deleteMucus(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM mucus WHERE uid = " + uidUser);
        return 1;
    }

    public int deleteSymptoms(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM symptoms WHERE uid = " + uidUser);
        return 1;
    }

    public int deleteNotes(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM note WHERE uid = " + uidUser);
        return 1;
    }

    public int deletePills(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM pills WHERE uid = " + uidUser);
        return 1;
    }

    public int deleteAllPeriods(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM period WHERE uid = " + uidUser);
        return 1;
    }

    public int deletePeriod(int uidUser, String datePeriod) {
        getWritableDatabase().execSQL("DELETE FROM period WHERE uid = " + uidUser + " AND " + "date" + " = " + datePeriod);
        return 1;
    }

    public int deleteSettings(int uidUser) {
        getWritableDatabase().execSQL("DELETE FROM settings WHERE uid = " + uidUser);
        return 1;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(user.getId()));
        values.put("uid", Integer.valueOf(user.getUid()));
        values.put("status", Integer.valueOf(user.getStatus()));
        values.put(USER_COL_USERNAME, user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put(USER_COL_QUESTION, user.getQuestion());
        values.put(USER_COL_ANSWER, user.getAnswer());
        values.put(USER_COL_AVATAR, Integer.valueOf(user.getAvatar()));
        values.put(USER_COL_THEME, Integer.valueOf(user.getTheme()));
        int i = db.update(TABLE_USER, values, "uid = ?", new String[]{String.valueOf(user.getUid())});
        db.close();
        return i;
    }

    public int updateMoodName(int uidTarget, int idMood, String newMoodName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE moods SET custom = '" + 1 + "' WHERE " + "uid" + " = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood);
        db.execSQL("UPDATE moods SET customname = '" + newMoodName + "' WHERE " + "uid" + " = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood);
        return 1;
    }

    public int updatePillName(int uidTarget, int idMood, String newMoodName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE pills SET custom = '" + 1 + "' WHERE " + "uid" + " = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idMood);
        db.execSQL("UPDATE pills SET customname = '" + newMoodName + "' WHERE " + "uid" + " = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idMood);
        return 1;
    }

    public int updateCicloDays(int uidTarget, String dateTarget, int newCicloDays) {
        getWritableDatabase().execSQL("UPDATE period SET daysciclo = " + newCicloDays + " WHERE " + "uid" + " = " + uidTarget + " AND " + "date" + " = " + dateTarget);
        return 1;
    }

    public int updateMestruoDays(int uidTarget, String dateTarget, int newMestruoDays) {
        getWritableDatabase().execSQL("UPDATE period SET daysmestruazioni = " + newMestruoDays + ", " + PERIOD_COL_COMPLETO + " = " + 1 + " WHERE " + "uid" + " = " + uidTarget + " AND " + "date" + " = " + dateTarget);
        return 1;
    }

    public int updateMoodCheck(int uidTarget, int idMood, int check) {
        SQLiteDatabase db = getWritableDatabase();
        if (check == 1) {
            db.execSQL("UPDATE moods SET hidden = " + 1 + " WHERE " + "uid" + " = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood);
        } else {
            db.execSQL("UPDATE moods SET hidden = " + 0 + " WHERE " + "uid" + " = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood);
        }
        return 1;
    }

    public int updatePillCheck(int uidTarget, int idPill, int check) {
        SQLiteDatabase db = getWritableDatabase();
        if (check == 1) {
            db.execSQL("UPDATE pills SET hidden = " + 1 + " WHERE " + "uid" + " = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idPill);
        } else {
            db.execSQL("UPDATE pills SET hidden = " + 0 + " WHERE " + "uid" + " = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idPill);
        }
        return 1;
    }

    public int findCustomMoodName(int uidTarget, int idMood) {
        Cursor c = getReadableDatabase().rawQuery("SELECT custom FROM moods WHERE uid = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood, null);
        if (c != null) {
            c.moveToFirst();
        }
        int customField = c.getInt(0);
        c.close();
        return customField;
    }

    public int findCustomPillName(int uidTarget, int idPill) {
        Cursor c = getReadableDatabase().rawQuery("SELECT custom FROM pills WHERE uid = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idPill, null);
        if (c != null) {
            c.moveToFirst();
        }
        int customField = c.getInt(0);
        c.close();
        return customField;
    }

    public String selectMoodName(int uidTarget, int idMood) {
        Cursor c = getReadableDatabase().rawQuery("SELECT customname FROM moods WHERE uid = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public String selectFirstStartPeriod(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " ASC LIMIT 1", null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customPeriod = c.getString(0);
        c.close();
        return customPeriod;
    }

    public String selectAfterPeriodRow(int uidTarget, String rowDate) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " AND " + "date" + " > " + rowDate + " ORDER BY " + "date" + " ASC LIMIT 1", null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customPeriod = c.getString(0);
        c.close();
        return customPeriod;
    }

    public int countAfterPeriodRow(int uidTarget, String rowDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(*) FROM period WHERE uid = " + uidTarget + " AND " + "date" + " >= " + rowDate + " ORDER BY " + "date" + " ASC LIMIT 1";

        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countBeforePeriodRow(int uidTarget, String rowDate) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(*) FROM period WHERE uid = " + uidTarget + " AND " + "date" + " <= " + rowDate + " ORDER BY " + "date" + " DESC LIMIT 1", null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public String selectBeforePeriodRow(int uidTarget, String rowDate) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " AND " + "date" + " <= " + rowDate + " ORDER BY " + "date" + " DESC LIMIT 1", null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customPeriod = c.getString(0);
        c.close();
        return customPeriod;
    }

    public String selectBeforePeriodRowMain(int uidTarget, String rowDate) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " AND " + "date" + " <= " + rowDate + " ORDER BY " + "date" + " DESC LIMIT 1", null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customPeriod = c.getString(0);
        c.close();
        return customPeriod;
    }

    public String selectLastStartPeriod(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 1", null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customPeriod = c.getString(0);
        c.close();
        return customPeriod;
    }

    public int selectMinMaxStartPeriod(int uidTarget, int input) {
        String selectQuery;
        SQLiteDatabase db = getReadableDatabase();
        if (input == 0) {
            selectQuery = "SELECT MIN(daysciclo) FROM period WHERE uid = " + uidTarget;
        } else {
            selectQuery = "SELECT MAX(daysciclo) FROM period WHERE uid = " + uidTarget;
        }
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        int customPeriod = c.getInt(0);
        c.close();
        return customPeriod;
    }

    public int selectCountNumAlarm(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT MAX(type) FROM notifications WHERE uid = " + uidTarget, null);
        if (c != null) {
            c.moveToFirst();
        }
        int customPeriod = c.getInt(0);
        c.close();
        return customPeriod;
    }

    public String selectLastMestruoPeriod(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT daysmestruazioni FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 1", null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customPeriod = c.getString(0);
        c.close();
        return customPeriod;
    }

    public String[] selectALLPeriods(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        String[] periodValue = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                periodValue[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return periodValue;
    }

    public String[] selectALLPeriodsCharts(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " ASC", null);
        String[] periodValue = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                periodValue[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return periodValue;
    }

    public int[] selectALLCycles(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT daysciclo FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        int[] cycleValue = new int[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                cycleValue[k] = c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return cycleValue;
    }

    public int[] selectALLMestrui(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT daysmestruazioni FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        int[] cycleValue = new int[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                cycleValue[k] = c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return cycleValue;
    }

    public int[] selectALLCyclesCharts(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT daysciclo FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " ASC", null);
        int[] cycleValue = new int[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                cycleValue[k] = c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return cycleValue;
    }

    public int[] selectALLMestruiCharts(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT daysmestruazioni FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " ASC", null);
        int[] cycleValue = new int[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                cycleValue[k] = c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return cycleValue;
    }

    public String[] selectDateBuoneNota(int uidTarget, int opzione) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "";
        if (opzione == 0) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 1) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_TEMPERATURE + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 2) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " != 0 AND " + NOTE_COL_HEIGHT + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 3) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_SYSTOLIC + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 4) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_DIASTOLIC + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 5) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_PRESSURE + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 6) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_SENO + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 7) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_VITA + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 8) {
            selectQuery = "SELECT date FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_FIANCHI + " != 0 ORDER BY " + "date" + " ASC";
        }
        Cursor c = db.rawQuery(selectQuery, null);
        String[] datePiene = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                datePiene[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public int countDateBuoneNota(int uidTarget, int opzione) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "";
        if (opzione == 0) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 1) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_TEMPERATURE + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 2) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " != 0 AND " + NOTE_COL_HEIGHT + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 3) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_SYSTOLIC + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 4) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_DIASTOLIC + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 5) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_PRESSURE + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 6) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_SENO + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 7) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_VITA + " != 0 ORDER BY " + "date" + " ASC";
        }
        if (opzione == 8) {
            selectQuery = "SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_FIANCHI + " != 0 ORDER BY " + "date" + " ASC";
        }
        Cursor c = db.rawQuery(selectQuery, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countDateNotaPRINT(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM note WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public String[] selectDateBuoneNotaPRINT(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM note WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        String[] datePiene = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePiene[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public String[] selectSymptomsBuoneNotaPRINT(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT symptoms FROM note WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        String[] datePiene = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePiene[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public String[] selectMucusBuoneNotaPRINT(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT mucus FROM note WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        String[] datePiene = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePiene[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public String[] selectPillsBuoneNotaPRINT(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT pill FROM note WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC", null);
        String[] datePiene = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePiene[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public float[] selectBust(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT seno FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_SENO + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateOut = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                dateOut[k] = c.getFloat(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return dateOut;
    }

    public float[] selectWaist(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT vita FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_VITA + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateOut = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                dateOut[k] = c.getFloat(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return dateOut;
    }

    public float[] selectHip(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT fianchi FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_FIANCHI + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateOut = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                dateOut[k] = c.getFloat(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return dateOut;
    }

    public float[] selectDiastolic(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT diastolic FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_DIASTOLIC + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateDia = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                dateDia[k] = (float) c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return dateDia;
    }

    public float[] selectSystolic(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT systolic FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_SYSTOLIC + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateSys = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                dateSys[k] = (float) c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return dateSys;
    }

    public float[] selectPulse(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT pressure FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_PRESSURE + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] datePulse = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePulse[k] = (float) c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePulse;
    }

    public float[] selectBMI(int uidTarget) {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        Cursor c = db.rawQuery("SELECT weight FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " != 0 AND " + NOTE_COL_HEIGHT + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateW = new float[c.getCount()];
        float[] BMI = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                dateW[k] = c.getFloat(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        String str3 = "";
        Cursor c2 = db.rawQuery("SELECT height FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " != 0 AND " + NOTE_COL_HEIGHT + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] dateH = new float[c.getCount()];
        float[] AltezzaSaveDEFBMI = new float[c.getCount()];
        int z = 0;
        this.dim = c2.getCount();
        if (c2.getCount() != 0) {
            c2.moveToFirst();
            do {
                String str4 = "";
                dateH[z] = c2.getFloat(0);
                z++;
            } while (c2.moveToNext());
        }
        c2.close();
        for (int o = 0; o < this.dim; o++) {
            AltezzaSaveDEFBMI[o] = CMtoM(dateH[o]);
            BMI[o] = dateW[o] / (AltezzaSaveDEFBMI[o] * AltezzaSaveDEFBMI[o]);
        }
        return BMI;
    }

    public float[] selectTemp(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT temperature FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_TEMPERATURE + " != 0 ORDER BY " + "date" + " ASC", null);
        float[] datePiene = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePiene[k] = c.getFloat(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public String[] selectChartMoods(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT moods FROM note WHERE uid = " + uidTarget, null);
        String[] umoriOut = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                umoriOut[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return umoriOut;
    }

    public String[] selectChartSymptoms(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT symptoms FROM note WHERE uid = " + uidTarget, null);
        String[] sintomiOut = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                sintomiOut[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return sintomiOut;
    }

    public float[] selectWeight(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT weight FROM note WHERE uid = " + uidTarget + " AND " + NOTE_COL_WEIGHT + " ORDER BY " + "date" + " ASC", null);
        float[] datePiene = new float[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                datePiene[k] = c.getFloat(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return datePiene;
    }

    public String[] selectChartMucus(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT mucus FROM note WHERE uid = " + uidTarget, null);
        String[] sintomiOut = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                sintomiOut[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return sintomiOut;
    }

    public String[] selectChartPill(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT pill FROM note WHERE uid = " + uidTarget, null);
        String[] sintomiOut = new String[c.getCount()];
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                sintomiOut[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return sintomiOut;
    }

    public String[] selectLastFourStartPeriod(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT date FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 4", null);
        String[] lastFourPeriod = {"", "", "", ""};
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                lastFourPeriod[k] = c.getString(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return lastFourPeriod;
    }

    public float avgRapportibyWeekLast12M(int uidTarget, int richiesta) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        float average = 0.0f;
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        String annoPASSATO = String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT date FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int j = 0;
        int columnCount = cursor1.getColumnCount();
        int row = cursor1.getCount();
        String[] dateNote2 = new String[row];
        if (cursor1.getCount() != 0) {
            cursor1.moveToFirst();
            do {
                dateNote2[j] = cursor1.getString(0);
                j++;
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        Cursor cursor2 = db.rawQuery("SELECT sextimes FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int row2 = cursor2.getCount();
        int[] botteNote = new int[row2];
        int j2 = 0;
        if (cursor2.getCount() != 0) {
            cursor2.moveToFirst();
            do {
                botteNote[j2] = cursor2.getInt(0);
                j2++;
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        Calendar cal = Calendar.getInstance();
        int[] giornoANNO = new int[row2];
        int settimaneEFFETTIVE = 0;
        int[] settimanaANNO = new int[53];
        for (int i = 0; i < row; i++) {
            try {
                cal.setTime(this.formatodata.parse(dateNote2[i]));
                giornoANNO[i] = cal.get(6);
                int s = giornoANNO[i] / 7;
                settimanaANNO[s] = settimanaANNO[s] + botteNote[i];
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (int z = 0; z < 53; z++) {
            if (settimanaANNO[z] > 0) {
                settimaneEFFETTIVE++;
            }
        }
        int sum = 0;
        for (int z2 = 0; z2 < 53; z2++) {
            sum += settimanaANNO[z2];
        }
        if (richiesta == 0) {
            average = ((float) sum) / ((float) settimaneEFFETTIVE);
        }
        if (richiesta == 1) {
            return ((float) sum) / ((float) 53);
        }
        return average;
    }

    public float avgOrgasmibyWeekLast12M(int uidTarget, int richiesta) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        float average = 0.0f;
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        String annoPASSATO = String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT date FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int j = 0;
        int columnCount = cursor1.getColumnCount();
        int row = cursor1.getCount();
        String[] dateNote2 = new String[row];
        if (cursor1.getCount() != 0) {
            cursor1.moveToFirst();
            do {
                dateNote2[j] = cursor1.getString(0);
                j++;
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        Cursor cursor2 = db.rawQuery("SELECT numorgasm FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int row2 = cursor2.getCount();
        int[] botteNote = new int[row2];
        int j2 = 0;
        if (cursor2.getCount() != 0) {
            cursor2.moveToFirst();
            do {
                botteNote[j2] = cursor2.getInt(0);
                j2++;
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        Calendar cal = Calendar.getInstance();
        int[] giornoANNO = new int[row2];
        int settimaneEFFETTIVE = 0;
        int[] settimanaANNO = new int[53];
        for (int i = 0; i < row; i++) {
            try {
                cal.setTime(this.formatodata.parse(dateNote2[i]));
                giornoANNO[i] = cal.get(6);
                int s = giornoANNO[i] / 7;
                settimanaANNO[s] = settimanaANNO[s] + botteNote[i];
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (int z = 0; z < 53; z++) {
            if (settimanaANNO[z] > 0) {
                settimaneEFFETTIVE++;
            }
        }
        int sum = 0;
        for (int z2 = 0; z2 < 53; z2++) {
            sum += settimanaANNO[z2];
        }
        if (richiesta == 0) {
            if (settimaneEFFETTIVE != 0) {
                average = ((float) sum) / ((float) settimaneEFFETTIVE);
            } else {
                average = 0.0f;
            }
        }
        if (richiesta != 1) {
            return average;
        }
        if (53 != 0) {
            return ((float) sum) / ((float) 53);
        }
        return 0.0f;
    }

    public float avgRapportibyMonthLast12M(int uidTarget, int richiesta) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        float average = 0.0f;
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        String annoPASSATO = String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT date FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int j = 0;
        int columnCount = cursor1.getColumnCount();
        int row = cursor1.getCount();
        String[] dateNote2 = new String[row];
        if (cursor1.getCount() != 0) {
            cursor1.moveToFirst();
            do {
                dateNote2[j] = cursor1.getString(0);
                j++;
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        Cursor cursor2 = db.rawQuery("SELECT sextimes FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int row2 = cursor2.getCount();
        int[] botteNote = new int[row2];
        int j2 = 0;
        if (cursor2.getCount() != 0) {
            cursor2.moveToFirst();
            do {
                botteNote[j2] = cursor2.getInt(0);
                j2++;
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        Calendar cal = Calendar.getInstance();
        int[] giornoANNO = new int[row2];
        int mesiEFFETTIVI = 0;
        int[] mesiANNO = new int[12];
        for (int i = 0; i < row; i++) {
            try {
                cal.setTime(this.formatodata.parse(dateNote2[i]));
                giornoANNO[i] = cal.get(6);
                int s = giornoANNO[i] / 30;
                mesiANNO[s] = mesiANNO[s] + botteNote[i];
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (int z = 0; z < 12; z++) {
            if (mesiANNO[z] > 0) {
                mesiEFFETTIVI++;
            }
        }
        int sum = 0;
        for (int z2 = 0; z2 < 12; z2++) {
            sum += mesiANNO[z2];
        }
        if (richiesta == 0) {
            if (mesiEFFETTIVI != 0) {
                average = ((float) sum) / ((float) mesiEFFETTIVI);
            } else {
                average = 0.0f;
            }
        }
        if (richiesta != 1) {
            return average;
        }
        if (12 != 0) {
            return ((float) sum) / ((float) 12);
        }
        return 0.0f;
    }

    public float avgOrgasmibyMonthLast12M(int uidTarget, int richiesta) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        float average = 0.0f;
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        String annoPASSATO = String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT date FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int j = 0;
        int columnCount = cursor1.getColumnCount();
        int row = cursor1.getCount();
        String[] dateNote2 = new String[row];
        if (cursor1.getCount() != 0) {
            cursor1.moveToFirst();
            do {
                dateNote2[j] = cursor1.getString(0);
                j++;
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        Cursor cursor2 = db.rawQuery("SELECT numorgasm FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int row2 = cursor2.getCount();
        int[] botteNote = new int[row2];
        int j2 = 0;
        if (cursor2.getCount() != 0) {
            cursor2.moveToFirst();
            do {
                botteNote[j2] = cursor2.getInt(0);
                j2++;
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        Calendar cal = Calendar.getInstance();
        int[] giornoANNO = new int[row2];
        int mesiEFFETTIVI = 0;
        int[] mesiANNO = new int[12];
        for (int i = 0; i < row; i++) {
            try {
                cal.setTime(this.formatodata.parse(dateNote2[i]));
                giornoANNO[i] = cal.get(6);
                int s = giornoANNO[i] / 30;
                mesiANNO[s] = mesiANNO[s] + botteNote[i];
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (int z = 0; z < 12; z++) {
            if (mesiANNO[z] > 0) {
                mesiEFFETTIVI++;
            }
        }
        int sum = 0;
        for (int z2 = 0; z2 < 12; z2++) {
            sum += mesiANNO[z2];
        }
        if (richiesta == 0) {
            if (mesiEFFETTIVI != 0) {
                average = ((float) sum) / ((float) mesiEFFETTIVI);
            } else {
                average = 0.0f;
            }
        }
        if (richiesta != 1) {
            return average;
        }
        if (12 != 0) {
            return ((float) sum) / ((float) 12);
        }
        return 0.0f;
    }

    public float probabilitaORGASMO(int uidTarget) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        String annoPASSATO = String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT date FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int j = 0;
        int columnCount = cursor1.getColumnCount();
        int row = cursor1.getCount();
        String[] dateNote2 = new String[row];
        if (cursor1.getCount() != 0) {
            cursor1.moveToFirst();
            do {
                dateNote2[j] = cursor1.getString(0);
                j++;
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        Cursor cursor2 = db.rawQuery("SELECT numorgasm FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int row2 = cursor2.getCount();
        int[] orgasmNote = new int[row2];
        int j2 = 0;
        if (cursor2.getCount() != 0) {
            cursor2.moveToFirst();
            do {
                orgasmNote[j2] = cursor2.getInt(0);
                j2++;
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        Cursor cursor3 = db.rawQuery("SELECT sextimes FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + annoPASSATO + " ORDER BY " + "date" + " DESC", null);
        int count = cursor3.getCount();
        int[] botteNote = new int[row2];
        int j3 = 0;
        if (cursor3.getCount() != 0) {
            cursor3.moveToFirst();
            do {
                botteNote[j3] = cursor3.getInt(0);
                j3++;
            } while (cursor3.moveToNext());
        }
        cursor3.close();
        float sumProb = 0.0f;
        int o = 0;
        for (int i = 0; i < row; i++) {
            if (botteNote[i] != 0) {
                o++;
                float divTemp = (float) (orgasmNote[i] / botteNote[i]);
                if (divTemp >= 1.0f) {
                    divTemp = 1.0f;
                }
                sumProb += divTemp;
            }
        }
        return (sumProb / ((float) o)) * 100.0f;
    }

    public int[] selectLastFourStartGiorniMestruo(int uidTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT daysciclo FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 4", null);
        int[] lastFourPeriod = {0, 0, 0, 0};
        int k = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str = "";
                lastFourPeriod[k] = c.getInt(0);
                k++;
            } while (c.moveToNext());
        }
        c.close();
        return lastFourPeriod;
    }

    public int totRapportiLASTYEAR(int uidTarget) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        Cursor c = getReadableDatabase().rawQuery("SELECT SUM(sextimes) FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + (String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8)) + " ORDER BY " + "date" + " DESC", null);
        if (c != null) {
            c.moveToFirst();
        }
        int customPeriod = c.getInt(0);
        c.close();
        return customPeriod;
    }

    public int totOrgasmiLASTYEAR(int uidTarget) {
        String sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int annoPassato = Integer.parseInt(sOggiDateCheck.substring(0, 4)) - 1;
        String mese = sOggiDateCheck.substring(4, 6);
        Cursor c = getReadableDatabase().rawQuery("SELECT SUM(numorgasm) FROM note WHERE uid = " + uidTarget + " AND " + "date" + " > " + (String.valueOf(annoPassato) + mese + sOggiDateCheck.substring(6, 8)) + " ORDER BY " + "date" + " DESC", null);
        if (c != null) {
            c.moveToFirst();
        }
        int customPeriod = c.getInt(0);
        c.close();
        return customPeriod;
    }

    public String[] returnStringArray() {
        return new String[]{"a", "b", "c"};
    }

    public String selectPillName(int uidTarget, int idPill) {
        Cursor c = getReadableDatabase().rawQuery("SELECT customname FROM pills WHERE uid = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idPill, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public int countRowsMood(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM moods WHERE uid = " + uid, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsSymptoms(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM symptoms WHERE uid = " + uid, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsMucus(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM mucus WHERE uid = " + uid, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public int countRowsPills(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM pills WHERE uid = " + uid, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public String selectCustomMoodName(int uidTarget, int idMood) {
        Cursor c = getReadableDatabase().rawQuery("SELECT name FROM moods WHERE uid = " + uidTarget + " AND " + MOODS_COL_IDMOOD + " = " + idMood, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public String selectCustomPillName(int uidTarget, int idPill) {
        Cursor c = getReadableDatabase().rawQuery("SELECT name FROM pills WHERE uid = " + uidTarget + " AND " + PILLS_COL_IDPILL + " = " + idPill, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public int updateSymptomName(int uidTarget, int idSymptom, String newSymptomName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE symptoms SET custom = '" + 1 + "' WHERE " + "uid" + " = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom);
        db.execSQL("UPDATE symptoms SET customname = '" + newSymptomName + "' WHERE " + "uid" + " = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom);
        return 1;
    }

    public int updateSymptomCheck(int uidTarget, int idSymptom, int check) {
        SQLiteDatabase db = getWritableDatabase();
        if (check == 1) {
            db.execSQL("UPDATE symptoms SET hidden = " + 1 + " WHERE " + "uid" + " = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom);
        } else {
            db.execSQL("UPDATE symptoms SET hidden = " + 0 + " WHERE " + "uid" + " = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom);
        }
        return 1;
    }

    public int findCustomSymptomName(int uidTarget, int idSymptom) {
        Cursor c = getReadableDatabase().rawQuery("SELECT custom FROM symptoms WHERE uid = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom, null);
        if (c != null) {
            c.moveToFirst();
        }
        int customField = c.getInt(0);
        c.close();
        return customField;
    }

    public int countMoonPhase(String dateTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM moonphases WHERE date = " + dateTarget, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public String selectMoonPhase(String dateTarget) {
        Cursor c = getReadableDatabase().rawQuery("SELECT moonphase FROM moonphases WHERE date = " + dateTarget, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public String selectSymptomName(int uidTarget, int idSymptom) {
        Cursor c = getReadableDatabase().rawQuery("SELECT customname FROM symptoms WHERE uid = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public float selectAvgPeriodTime(int uidTarget, int optionAvg) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "";
        String TempTable = "TT";
        if (optionAvg == 0) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_MESTRUAZIONI + ") FROM (SELECT " + PERIOD_COL_DAYS_MESTRUAZIONI + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 3) " + TempTable;
        }
        if (optionAvg == 1) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_MESTRUAZIONI + ") FROM (SELECT " + PERIOD_COL_DAYS_MESTRUAZIONI + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 6) " + TempTable;
        }
        if (optionAvg == 2) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_MESTRUAZIONI + ") FROM (SELECT " + PERIOD_COL_DAYS_MESTRUAZIONI + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 12) " + TempTable;
        }
        if (optionAvg == 3) {
            selectQuery = "SELECT AVG(daysmestruazioni) FROM period WHERE uid = " + uidTarget;
        }
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectAvgCycleTime(int uidTarget, int optionAvg) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "";
        String TempTable = "TT";
        if (optionAvg == 0) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_CICLO + ") FROM (SELECT " + PERIOD_COL_DAYS_CICLO + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " DESC LIMIT 3) " + TempTable;
        }
        if (optionAvg == 1) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_CICLO + ") FROM (SELECT " + PERIOD_COL_DAYS_CICLO + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " DESC LIMIT 6) " + TempTable;
        }
        if (optionAvg == 2) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_CICLO + ") FROM (SELECT " + PERIOD_COL_DAYS_CICLO + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " DESC LIMIT 12) " + TempTable;
        }
        if (optionAvg == 3) {
            selectQuery = "SELECT AVG(daysciclo) FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0;
        }
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectAvgFloatCycleTime(int uidTarget, int optionAvg) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "";
        String TempTable = "TT";
        if (optionAvg == 0) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_CICLO + ") FROM (SELECT " + PERIOD_COL_DAYS_CICLO + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " DESC LIMIT 3) " + TempTable;
        }
        if (optionAvg == 1) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_CICLO + ") FROM (SELECT " + PERIOD_COL_DAYS_CICLO + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " DESC LIMIT 6) " + TempTable;
        }
        if (optionAvg == 2) {
            selectQuery = "SELECT AVG(" + TempTable + "." + PERIOD_COL_DAYS_CICLO + ") FROM (SELECT " + PERIOD_COL_DAYS_CICLO + " FROM " + TABLE_PERIOD + " WHERE " + "uid" + " = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " ORDER BY " + "date" + " DESC LIMIT 12) " + TempTable;
        }
        if (optionAvg == 3) {
            selectQuery = "SELECT AVG(daysciclo) FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0;
        }
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectSmartAvgCycleTime(int uidTarget, int min, int max) {
        String str = "";
        String str2 = "TT";
        Cursor c = getReadableDatabase().rawQuery("SELECT AVG(daysciclo) FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " AND " + PERIOD_COL_DAYS_CICLO + " >= " + min + " AND " + PERIOD_COL_DAYS_CICLO + " <=  " + max, null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectSuperSmartAvgPeriodTime(int uidTarget, int min, int max, int threshold) {
        String str = "";
        String str2 = "TT";
        Cursor c = getReadableDatabase().rawQuery("SELECT AVG(daysmestruazioni) FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " AND " + PERIOD_COL_DAYS_MESTRUAZIONI + " >= " + min + " AND " + PERIOD_COL_DAYS_MESTRUAZIONI + " <=  " + max + " AND " + PERIOD_COL_DAYS_MESTRUAZIONI + " <= (SELECT AVG(" + PERIOD_COL_DAYS_MESTRUAZIONI + ") + " + threshold + " FROM " + TABLE_PERIOD + ") AND " + PERIOD_COL_DAYS_MESTRUAZIONI + " >= (SELECT AVG(" + PERIOD_COL_DAYS_MESTRUAZIONI + ") - " + threshold + " FROM " + TABLE_PERIOD + ")", null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectSuperSmartAvgCycleTime(int uidTarget, int min, int max, int threshold) {
        String str = "";
        String str2 = "TT";
        Cursor c = getReadableDatabase().rawQuery("SELECT AVG(daysciclo) FROM period WHERE uid = " + uidTarget + " AND " + "type" + " != " + 1 + " AND " + PERIOD_COL_PREGNANCY + " = " + 0 + " AND " + PERIOD_COL_DAYS_CICLO + " >= " + min + " AND " + PERIOD_COL_DAYS_CICLO + " <=  " + max + " AND " + PERIOD_COL_DAYS_CICLO + " <= (SELECT AVG(" + PERIOD_COL_DAYS_CICLO + ") + " + threshold + " FROM " + TABLE_PERIOD + ") AND " + PERIOD_COL_DAYS_CICLO + " >= (SELECT AVG(" + PERIOD_COL_DAYS_CICLO + ") - " + threshold + " FROM " + TABLE_PERIOD + ")", null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectSmartAvgPeriodTime(int uidTarget, int min, int max) {
        String str = "";
        String str2 = "TT";
        Cursor c = getReadableDatabase().rawQuery("SELECT AVG(daysmestruazioni) FROM period WHERE uid = " + uidTarget + " AND " + PERIOD_COL_DAYS_MESTRUAZIONI + " >= " + min + " AND " + PERIOD_COL_DAYS_MESTRUAZIONI + " <=  " + max, null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public float selectMax12AvgPeriodTime(int uidTarget) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT AVG(daysmestruazioni) FROM period WHERE uid = " + uidTarget + " ORDER BY " + "date" + " DESC LIMIT 12", null);
        if (c != null) {
            c.moveToFirst();
        }
        float avgCompute = c.getFloat(0);
        c.close();
        return avgCompute;
    }

    public String selectCustomSymptomName(int uidTarget, int idSymptom) {
        Cursor c = getReadableDatabase().rawQuery("SELECT name FROM symptoms WHERE uid = " + uidTarget + " AND " + SYMPTOMS_COL_IDSYMPTOM + " = " + idSymptom, null);
        String str = "";
        if (c != null) {
            c.moveToFirst();
        }
        String customName = c.getString(0);
        c.close();
        return customName;
    }

    public int countNotificationsSelected(int uid) {
        Cursor c = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM notifications WHERE uid = " + uid + " AND " + NOTIFICATIONS_COL_USED + " = 1", null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        c.close();
        return numRows;
    }

    public Notifications[] readUsedNotifications(int uid) {
        int y = 0;
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM notifications WHERE uid = " + uid + " AND " + NOTIFICATIONS_COL_USED + " = 1", null);
        Notifications[] notificationsOutput = new Notifications[c.getCount()];
        if (c != null) {
            c.moveToFirst();
        }
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                notificationsOutput[y] = new Notifications(c.getInt(c.getColumnIndex("id")), c.getInt(c.getColumnIndex(NOTIFICATIONS_COL_IDNOT)), c.getInt(c.getColumnIndex("type")), c.getInt(c.getColumnIndex("uid")), c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex(NOTIFICATIONS_COL_USED)), c.getInt(c.getColumnIndex("custom")), c.getInt(c.getColumnIndex(NOTIFICATIONS_COL_FREQUENCY)), c.getInt(c.getColumnIndex(NOTIFICATIONS_COL_TIME_HOUR)), c.getInt(c.getColumnIndex(NOTIFICATIONS_COL_TIME_MIN)), c.getString(c.getColumnIndex(NOTIFICATIONS_COL_DATE_ENTRY)));
                y++;
            } while (c.moveToNext());
        }
        return notificationsOutput;
    }

    public MoodNote[] selectSIMBOLIMoodsNOTA(int uidTarget, String date) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT moods FROM note WHERE uid = " + uidTarget + " AND " + "date" + " = " + date, null);
        String moodsOut = "";
        int count = c.getCount();
        int y = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                moodsOut = c.getString(0);
                y++;
            } while (c.moveToNext());
        }
        int lungMoods = moodsOut.length();
        MoodNote[] moodNOTA = new MoodNote[lungMoods];
        for (int i = 0; i < lungMoods; i++) {
            moodNOTA[i] = new MoodNote(i, String.valueOf(Character.getNumericValue(moodsOut.charAt(i))));
        }
        c.close();
        return moodNOTA;
    }

    public SymptomNote[] selectSIMBOLISymptomsNOTA(int uidTarget, String date) {
        Cursor c = getReadableDatabase().rawQuery("SELECT symptoms FROM note WHERE uid = " + uidTarget + " AND " + "date" + " = " + date, null);
        String moodsOut = "";
        int y = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                moodsOut = c.getString(0);
                y++;
            } while (c.moveToNext());
        }
        int lungMoods = moodsOut.length();
        SymptomNote[] symptomNote = new SymptomNote[lungMoods];
        for (int i = 0; i < lungMoods; i++) {
            symptomNote[i] = new SymptomNote(i, String.valueOf(Character.getNumericValue(moodsOut.charAt(i))));
            Log.e("aaaaaa",""+symptomNote[i]);

        }
        c.close();
        Log.e("aaaaaa",""+symptomNote.length);

        return symptomNote;
    }

    public MucusNote[] selectSIMBOLIMucusNOTA(int uidTarget, String date) {
        String str = "";
        Cursor c = getReadableDatabase().rawQuery("SELECT mucus FROM note WHERE uid = " + uidTarget + " AND " + "date" + " = " + date, null);
        String moodsOut = "";
        int count = c.getCount();
        int y = 0;
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String str2 = "";
                moodsOut = c.getString(0);
                y++;
            } while (c.moveToNext());
        }
        int lungMoods = moodsOut.length();
        MucusNote[] mucusNote = new MucusNote[lungMoods];
        for (int i = 0; i < lungMoods; i++) {
            mucusNote[i] = new MucusNote(i, String.valueOf(Character.getNumericValue(moodsOut.charAt(i))));
        }
        c.close();
        return mucusNote;
    }

    public int updateUsers(int uidTarget) {
        getWritableDatabase().execSQL("UPDATE user SET status = '" + 0 + "' WHERE " + "uid" + " != " + uidTarget);
        return 1;
    }

    public int switchUser(int uidTarget) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE user SET status = '" + 0 + "' WHERE " + "uid" + " != " + uidTarget);
        db.execSQL("UPDATE user SET status = '" + 1 + "' WHERE " + "uid" + " = " + uidTarget);
        return 1;
    }

    public int selectMaxUidUser() {
        Cursor c = getReadableDatabase().rawQuery("SELECT MAX(uid) FROM user", null);
        if (c != null) {
            c.moveToFirst();
        }
        int numMaxUid = c.getInt(0);
        c.close();
        return numMaxUid;
    }

    public void deleteNote(Note note, String DelDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NOTE, "uid = ? AND date = ? ", new String[]{String.valueOf(note.getUid()), DelDate});
        db.close();
    }

    public static float CMtoM(float input) {
        return input / 100.0f;
    }
}