package by.gsu.epamlab.model.enums;

public enum TaskType {
    TODAY {
        @Override
        public String getTaskQuery() {
            return "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE date <= current_date AND isFixed = FALSE AND isBinned = FALSE AND userId=?";
        }

    },
    TOMORROW {
        @Override
        public String getTaskQuery() {
            return "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE date = current_date + INTERVAL 1 DAY AND isFixed = FALSE AND isBinned = FALSE AND userId=?";
        }

    },
    SOMEDAY {
        @Override
        public String getTaskQuery() {
            return "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE date > current_date + INTERVAL 1 DAY AND isFixed = FALSE AND isBinned = FALSE AND userId=?";
        }

    },
    FIXED {
        @Override
        public String getTaskQuery() {
            return "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE isFixed = true AND isBinned = FALSE AND userId=?";
        }

    },
    RECYCLE_BIN {
        @Override
        public String getTaskQuery() {
            return "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE isBinned = true AND isFIXED = FALSE AND userId=?";
        }
    };


    public abstract String getTaskQuery();

}
