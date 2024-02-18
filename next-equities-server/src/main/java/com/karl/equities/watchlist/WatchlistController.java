package com.karl.equities.watchlist;

import com.karl.equities.watchlist.Watchlist;
import com.karl.equities.watchlist.WatchlistRequest;
import com.karl.equities.watchlist.WatchlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/watchlists")
@RequiredArgsConstructor
@Slf4j
public class WatchlistController {

    private final WatchlistService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody WatchlistRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Watchlist>> findAllWatchlists() {
        log.info("## Get watchlist");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watchlist> findWatchlistById(@PathVariable("id") Integer id) {
        Optional<Watchlist> watchlist = service.findById(id);
        return watchlist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWatchlistById(@PathVariable("id") Integer id) {
        Optional<Watchlist> watchlist = service.findById(id);
        if (watchlist.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
