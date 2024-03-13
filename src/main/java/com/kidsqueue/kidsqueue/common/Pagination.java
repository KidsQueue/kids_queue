package com.kidsqueue.kidsqueue.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

    private Integer page;
    private Integer size;
    private Integer currentElements;
    private Integer totalPage;
    private Long totalElements;

}
