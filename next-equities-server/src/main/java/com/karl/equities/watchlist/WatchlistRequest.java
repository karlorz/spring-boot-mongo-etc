package com.karl.equities.watchlist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WatchlistRequest {

    private Integer id;
    private String issue;
}
