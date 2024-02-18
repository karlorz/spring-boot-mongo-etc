package com.karl.equities.watchlist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository repository;

    public void save(WatchlistRequest request) {
        var watchlist = Watchlist.builder()
                .id(request.getId())
                .issue(request.getIssue())
                .createdBy(1)
                .build();
        repository.save(watchlist);
    }

    public List<Watchlist> findAll() {
        return repository.findAll();
    }

    public Optional<Watchlist> findById(Integer watchlistId) {
        return repository.findById(watchlistId);
    }

    public void deleteById(Integer watchlistId) {
        repository.deleteById(watchlistId);
    }
}
