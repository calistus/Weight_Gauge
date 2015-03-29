package com.ibuvast.weightgauge.data;

import android.content.Context;
import android.provider.BaseColumns;

/**
 * Created by calistus on 3/29/2015.
 */
public class HistoryContract {

    public static final class HistoryEntry implements BaseColumns{
        public static final String UID = "_id";
        public static final String TABLE_NAME = "history";
        public static final String TIME = "Time";
        public static final String DATE = "Date";
        public static final String BMI_VALUE = "Values";
        public static final String STATUS = "Status";
    }
}
