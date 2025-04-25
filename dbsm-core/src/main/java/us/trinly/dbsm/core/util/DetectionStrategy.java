package us.trinly.dbsm.core.util;

public enum DetectionStrategy {
    STRICT,  // Exact match required
    LENIENT, // Ignore cosmetic differences
    CUSTOM   // User-provided comparison rules
}