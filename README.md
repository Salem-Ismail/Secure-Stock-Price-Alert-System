# Secure-Stock-Price-Alert-System

A real-time stock price alert system built in Java, focused on clean OOP design, the Observer pattern, multithreading, reliability (retry with exponential backoff), and extensibility.

### Architecture (high level)
- A `PriceDataProvider` fetches `PriceQuote` snapshots for a `Symbol`
- A `PricePublisher` broadcasts `PriceQuote` updates to `PriceSubscriber`s (Observer pattern)
- Alert evaluation will implement `PriceSubscriber`, creating `AlertEvent`s and sending them to an `AlertSink`

### Current status
- Domain model: `Symbol`, `PriceQuote`, `AlertEvent`
- Core interfaces: `AlertRule`, `PriceDataProvider`, `AlertSink`, `PricePublisher`, `PriceSubscriber`
- In-memory publisher implementation: `InMemoryPricePublisher`

### Planned milestones
- Multithreaded per-symbol polling workers
- Retry logic with exponential backoff + jitter
- Threshold-based alerts first, then additional alert types
- Pluggable providers and sinks via interfaces/factories

### Notes
- Learning Project
- Secrets will be loaded from environment variables or local config files later on
