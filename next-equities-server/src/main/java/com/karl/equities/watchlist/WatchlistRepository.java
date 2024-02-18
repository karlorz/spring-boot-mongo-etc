package com.karl.equities.watchlist;

import com.karl.equities.watchlist.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
}
