package com.quyvx.main_server.shared.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaginationMeta {
    private Long offset;

    private Integer limit;

    private Long total;
}
