package by.gsu.epamlab.model.enums;

import by.gsu.epamlab.model.constants.SQLConstants;

public enum TaskType {
    TODAY {
        @Override
        public String getTaskQuery() {
            return SQLConstants.SHOW_TODAY_TASKS;
        }

    },
    TOMORROW {
        @Override
        public String getTaskQuery() {
            return SQLConstants.SHOW_TOMORROW_TASKS;
        }

    },
    SOMEDAY {
        @Override
        public String getTaskQuery() {
            return SQLConstants.SHOW_SOMEDAY_TASKS;
        }

    },
    FIXED {
        @Override
        public String getTaskQuery() {
            return SQLConstants.SHOW_FIXED_TASKS;
        }

    },
    RECYCLE_BIN {
        @Override
        public String getTaskQuery() {
            return SQLConstants.SHOW_BINNED_TASKS;
        }
    };


    public abstract String getTaskQuery();

}
