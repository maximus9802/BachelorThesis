package com.quyvx.main_server.shared.utils;

import com.quyvx.main_server.shared.constants.ProjectConstants;

public class PaginationUtils {
    private PaginationUtils() {

    }

    public static Long getOffset(Long offset) {
        if (offset == null || offset <0) {
            return 0L;
        }
        return offset;
    }

    public static int getLimit(Integer limit) {
        if (limit == null || limit <0) {
            return 0;
        }
        if (limit > ProjectConstants.MAX_LIMIT_SIZE) {
            return ProjectConstants.MAX_LIMIT_SIZE;
        }
        return limit;
    }
}
