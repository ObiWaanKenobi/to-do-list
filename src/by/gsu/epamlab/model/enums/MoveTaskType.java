package by.gsu.epamlab.model.enums;

public enum MoveTaskType {
    MOVE_TO_BIN {
        @Override
        public String getQuery() {
            return "UPDATE tasks SET isBinned = TRUE, isFixed = FALSE WHERE id = ?";
        }
    },
    MOVE_TO_FIXED {
        @Override
        public String getQuery() {
            return "UPDATE tasks SET isFixed = TRUE, isBinned = FALSE WHERE id = ?";
        }
    },
    RESTORE {
        @Override
        public String getQuery() {
            return "UPDATE tasks SET isBinned = FALSE WHERE id = ?";
        }
    },
    DELETE {
        @Override
        public String getQuery() {
            return "DELETE FROM tasks WHERE id = ?";
        }
    };

    public abstract String getQuery();
}
