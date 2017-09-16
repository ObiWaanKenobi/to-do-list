package by.gsu.epamlab.model.enums;

import by.gsu.epamlab.model.constants.SQLConstants;

public enum MoveTaskType {
    MOVE_TO_BIN {
        @Override
        public String getQuery() {
            return SQLConstants.MOVE_TASK_TO_BIN;
        }
    },
    MOVE_TO_FIXED {
        @Override
        public String getQuery() {
            return SQLConstants.MOVE_TASK_TO_FIXED;
        }
    },
    RESTORE {
        @Override
        public String getQuery() {
            return SQLConstants.RESTORE_TASK;
        }
    },
    RESTORE_FIXED {
        @Override
        public String getQuery() { return SQLConstants.RESTORE_FIXED_TASK; }
    };

    public abstract String getQuery();
}
